/**
 * @fileOverview 本插件用于给jQuery方法添加回调函数，可在类方法或实例方法添加任何自定义的回调函数而不影响原方法的行为
 * @dependency jQuery1.7+
 * &nbsp;* @author huhai
 * @since 2013-01-21
 */
(function($){
	$._callbacks = {};
	$._callbacks_ = {};
	$._alias = {};
	$._alias_ = {};

	$.extend({

		/**
		 * @decription 给方法添加回调函数
		 * @param funcName : string 需要添加回调的函数名称
		 * @param callback : function 回调函数（如需移除，不要使用匿名方法）
		 * @param static : boolean 是否是类方法，默认为false
		 */
		addCallback : function(funcName, callback, static){
			if("string" === typeof(funcName) && $.isFunction(callback)){
				if(static === true){
					if($[funcName] && $.isFunction($[funcName])){
						if(!this._callbacks[funcName]){
							this._callbacks[funcName] = $.Callbacks();		
						}
						this._callbacks[funcName].add(callback);
						if(!$._alias[funcName]){
							$._alias[funcName] = $[funcName];//寄存原来的类方法
							
							$[funcName] = function(){//代理类方法；
							var result = $._alias[funcName].apply(this, arguments);
							$._callbacks[funcName].fireWith(this, arguments);
							return result;
							};
						}						
					}
				}else{
					if($.fn[funcName] && $.isFunction($.fn[funcName])){
						if(!this._callbacks_[funcName]){
							this._callbacks_[funcName] = $.Callbacks();		
						}
						this._callbacks_[funcName].add(callback);
						if(!$._alias_[funcName]){
							$._alias_[funcName] = $.fn[funcName];//寄存原来的实例方法
							$.fn[funcName] = function(){//代理实例方法；
								var result = $._alias_[funcName].apply(this, arguments);
								$._callbacks_[funcName].fireWith(this, arguments);
								return result;
							};
						}
					}
				}
			}
		},

		/**
		 * @decription 移除给方法添加的回调函数
		 * @param funcName : string 已添加回调的函数名称
		 * @param callback : function 回调函数
		 * @param static : boolean 是否是类方法，默认为false
		 */
		removeCallback: function(funcName, callback, static){
			if("string" === typeof(funcName) && $.isFunction(callback)){
				if(static === true){
					if($[funcName] && $.isFunction($[funcName])){
						if(this._callbacks[funcName]){
							this._callbacks.remove(callback);
						}
					}
				}else{
					if($.fn[funcName] && $.isFunction($.fn[funcName])){
						if(this._callbacks_[funcName]){
							this._callbacks_.remove(callback);
						}
					}
				}
			}
		}
	});
})(jQuery);