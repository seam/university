package("org.xwidgets.core");

org.xwidgets.core.ViewConfig = function() {
  xw.NonVisual.call(this);
  this._className = "org.xwidgets.core.ViewConfig";
  this.registerProperty("view", null);
  this.registerProperty("container", null);
};

org.xwidgets.core.ViewConfig.prototype = new xw.NonVisual();

org.xwidgets.core.UrlMapping = function() {
  xw.NonVisual.call(this);
  this._className = "org.xwidgets.core.UrlMapping";
  this.registerProperty("pattern", null);
  this.registerProperty("view", null);
};

org.xwidgets.core.UrlMapping.prototype = new xw.NonVisual();

org.xwidgets.core.NavigationManager = function() {
  xw.NonVisual.call(this);
  this._className = "org.xwidgets.core.NavigationManager"; 
};

org.xwidgets.core.NavigationManager.prototype = new xw.NonVisual();
  
org.xwidgets.core.NavigationManager.prototype.open = function(container) {

};

org.xwidgets.core.NavigationManager.prototype.toString = function() {
  return "org.xwidgets.core.NavigationManager[" + this.id + "]";
};
