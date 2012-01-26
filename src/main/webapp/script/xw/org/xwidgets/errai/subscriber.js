package("org.xwidgets.errai");

org.xwidgets.errai.Subscriber = function() {
  xw.NonVisual.call(this);
  this._className = "org.xwidgets.errai.Subscriber";
  this.registerProperty("topic");  
  this.registerEvent("onmessage");
};

org.xwidgets.errai.Subscriber.prototype = new xw.NonVisual();

org.xwidgets.errai.Subscriber.prototype.open = function() {
  if (!xw.Sys.isDefined(errai)) {
    var that = this;
    var o = {fire: function() { that.init(); }};
    xw.Event.registerObserver("erraiOnLoad", o);
  } else {
    this.init();
  }
};

org.xwidgets.errai.Subscriber.prototype.init = function() {
  this.bus = new errai.MsgBus();
  
  var that = this;
  var cb = function(msg) { 
    that.onmessage.invoke({msg:msg}); };
     
  this.bus.subscribe(this.topic, cb);
};
    
org.xwidgets.errai.Subscriber.prototype.toString = function() {
  return "org.xwidgets.errai.Subscriber[" + this.service + "]";
};


