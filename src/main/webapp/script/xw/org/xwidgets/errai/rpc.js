package("org.xwidgets.errai");

var rpcReplyCount = 0;

org.xwidgets.errai.Rpc = function() {
  xw.NonVisual.call(this);
  this._className = "org.xwidgets.errai.Rpc";
  this.registerProperty("service");
};

org.xwidgets.errai.Rpc.prototype = new xw.NonVisual();

org.xwidgets.errai.Rpc.prototype.open = function() {
  if (!xw.Sys.isDefined(errai)) {
    var that = this;
    var o = {fire: function() { that.init(); }};
    xw.Event.registerObserver("erraiOnLoad", o);
  } else {
    this.init();
  }
};

org.xwidgets.errai.Rpc.prototype.init = function() {
  this.bus = new errai.MsgBus();
};

org.xwidgets.errai.Rpc.prototype.invoke = function(value) {
{
    var busRef = this.bus;
    var replySvc = "rpc:methodReply:" + (rpcReplyCount++);
    this.bus.subscribe(replySvc, function (msg) {
       alert(msg.MethodReply);
        busRef.unsubscribeAll(replySvc);
    });


  var payload = {CommandType : "sayHello:java.lang.String:",
    MethodParms: [ value ],
     ReplyTo:replySvc
//    ErrorTo:
    };

  this.bus.send(this.service, payload);

};
}

org.xwidgets.errai.Rpc.prototype.toString = function() {
  return "org.xwidgets.errai.Rpc[" + this.service + "]";
};


