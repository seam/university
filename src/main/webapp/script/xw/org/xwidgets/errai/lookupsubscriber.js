package("org.xwidgets.errai");

org.xwidgets.errai.LookupSubscriber = function() {
  xw.NonVisual.call(this);
  this._className = "org.xwidgets.errai.LookupSubscriber";
  this.registerProperty("loaderSubject");
  this.registerProperty("subject");  
  this.values = null;
};

org.xwidgets.errai.LookupSubscriber.prototype = new xw.NonVisual();

org.xwidgets.errai.LookupSubscriber.prototype.open = function() {
  if (!xw.Sys.isDefined(window.errai)) {
    var that = this;
    var o = {fire: function() { that.init(); }};
    xw.Event.registerObserver("erraiOnLoad", o);
  } else {
    this.init();
  }
};

org.xwidgets.errai.LookupSubscriber.prototype.init = function() {
  this.bus = new errai.MsgBus();
  this.bus.automarshal(false);
  
  var that = this;
  var cb = function(msg) { 
    that.onMessage(msg);
  };
     
  this.bus.subscribe(this.subject, cb);
  this.bus.send(this.loaderSubject, {});
};

org.xwidgets.errai.LookupSubscriber.prototype.onMessage = function(msg) {
  alert("Received message: " + msg);
};
    
org.xwidgets.errai.LookupSubscriber.prototype.toString = function() {
  return "org.xwidgets.errai.LookupSubscriber[" + this.service + "]";
};


