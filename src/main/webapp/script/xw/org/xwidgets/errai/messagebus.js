package("org.xwidgets.errai");

org.xwidgets.errai.MessageBus = function() {
  xw.NonVisual.call(this);
  this._className = "org.xwidgets.errai.MessageBus";
  this.registerProperty("service");  
};

org.xwidgets.errai.MessageBus.prototype = new xw.NonVisual();

org.xwidgets.errai.MessageBus.prototype.open = function() {
  if (!xw.Sys.isDefined(errai)) {
    var that = this;
    var o = {fire: function() { that.init(); }};
    xw.Event.registerObserver("erraiOnLoad", o);
  } else {
    this.init();
  }
};

org.xwidgets.errai.MessageBus.prototype.init = function() {
  this.bus = new errai.MsgBus();
};
    
org.xwidgets.errai.MessageBus.prototype.sendMessage = function(msg) {
  this.bus.send(this.service, msg);
};

org.xwidgets.errai.MessageBus.prototype.sendTextMessage = function(msg) {
  this.bus.send(this.service, {Value:msg});
};

org.xwidgets.errai.MessageBus.prototype.toString = function() {
  return "org.xwidgets.errai.MessageBus[" + this.service + "]";
};


