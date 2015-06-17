(function(){
function gid(id){return document.getElementById(id);}
var window = this,undefined,_AreaSelector = window.AreaSelector,

	AreaSelector = window.AreaSelector = function( setting,selProvince,selCity,selArea ) {
		return new AreaSelector.fn.init( setting,selProvince,selCity,selArea);
	}

AreaSelector.fn = AreaSelector.prototype = {
	init: function(setting,selProvince,selCity,selArea) {				
		var selector = 
		{
			selProvinceId: selProvince,	//省市县DOM id
			selCityId : selCity,
		    selAreaId : selArea,
		    data : _ds_data,  	// 省市县json数据,如果你的数据格式跟本例不一样，请搜索调整data相关的代码
		    firstOptionText : "请选择",		// 第一项的字符
		    firstOptionValue: "-1",
		    isLoadOnInit:true,		// 是否init的时候就加载省份
		    onProvinceChange:null,	// 设置省更改后回调
		    onCityChange:null,// 设置地市更改后回调
		    onAreaChange:null// 设置区县更改后回调
		};
		var setting = setting || selector;
		AreaSelector.fn.extend(selector, setting);
		if(selector.onProvinceChange)
			gid(selector.selProvinceId).onchange=function(){this.selector.chgProvince.call(this,selector.onProvinceChange)};
		else
			gid(selector.selProvinceId).onchange=this.chgProvince;
		
		if(selector.onCityChange)
			gid(selector.selCityId).onchange=function(){this.selector.chgCity.call(this,selector.onCityChange)};
		else
			gid(selector.selCityId).onchange=this.chgCity;
		
		if(selector.onAreaChange)
			gid(selector.selAreaId).onchange=selector.onAreaChange;
		
		this.setting = selector;
		gid(selector.selProvinceId).selector=this;
		gid(selector.selCityId).selector=this;
		gid(selector.selAreaId).selector=this;
		if(selector.isLoadOnInit)
			this.initProvince(); // 初始化数据
		return this;
	},
		setting:null,
	resetOptions:function(domID, data, value){
		var G = gid(domID),H;
		var H;
		G.options.length=0;
		if(data==null || data.length==0) return;
		G.options[0]=new Option(this.setting.firstOptionText,this.setting.firstOptionValue);
		var newOption;
		for(i=0;i<data.length;i++){H = data[i];
			newOption = new Option(H.name,H.id);
			if(H.id==value) newOption.selected=true;
			G.options[G.options.length]=newOption;
		}
	},
	initProvince:function(ProvinceId,CityId, AreaId) {
		this.resetOptions(this.setting.selProvinceId,this.setting.data, ProvinceId);
		this.initCity(CityId, AreaId);
	},
	initCity: function(CityId, AreaId){
		var index = gid(this.setting.selProvinceId).selectedIndex;
		if(index <=0 ) {this.resetOptions(this.setting.selCityId); return;}
		var data = this.setting.data[index-1].city;
		this.resetOptions(this.setting.selCityId,data, CityId);
		
		if(AreaId != null)
		    this.initArea(AreaId);
	},
	initArea: function(AreaId){
		var index = gid(this.setting.selCityId).selectedIndex;
		if(index <= 0) {
			gid(this.setting.selAreaId).options.length=0;
			return ;
		}
		var data = this.setting.data[gid(this.setting.selProvinceId).selectedIndex-1].city[index-1].area;	
		this.resetOptions(this.setting.selAreaId,data, AreaId);
	},
	chgProvince: function(callback){
		this.selector.resetOptions(this.selector.setting.selAreaId);
		this.selector.initCity();	
		
		if( callback )
			callback.call(gid(this.selector.setting.selProvinceId));
	},
	chgCity:function(callback){
		this.selector.resetOptions(this.selector.setting.selAreaId);
		this.selector.initArea();
		
		if( callback )
			callback.call(gid(this.selector.setting.selCityId));
	},
	//根据名称初始化选择器
	initProvinceByText:function(provinceName, cityName, areaName) {
		var ps = gid(this.setting.selProvinceId);
		var cs = gid(this.setting.selCityId);
		var as = gid(this.setting.selAreaId);
		for(var i=0;i<ps.options.length;i++){
			var op = ps.options[i];
			if(op.text == provinceName){
				this.initProvince(op.value, 0, 0);
				break;
			}
		}
		for(var i=0;i<cs.options.length;i++){
			var op = cs.options[i];
			if(op.text == cityName){
				this.initCity(op.value, 0);
				break;
			}
		}
		for(var i=0;i<as.options.length;i++){
			var op = as.options[i];
			if(op.text == areaName){
				this.initArea(op.value);
				break;
			}
		}
	}
} // end of prototype
AreaSelector.fn.init.prototype = AreaSelector.fn;
AreaSelector.extend = AreaSelector.fn.extend = function() {
	// copy reference to target object
	var target = arguments[0] || {}, i = 1, length = arguments.length, deep = false, options;

	// Handle a deep copy situation
	if ( typeof target === "boolean" ) {
		deep = target;
		target = arguments[1] || {};
		// skip the boolean and the target
		i = 2;
	}

	// Handle case when target is a string or something (possible in deep copy)
	if ( typeof target !== "object" && !jQuery.isFunction(target) )
		target = {};

	// extend jQuery itself if only one argument is passed
	if ( length == i ) {
		target = this;
		--i;
	}

	for ( ; i < length; i++ )
		// Only deal with non-null/undefined values
		if ( (options = arguments[ i ]) != null )
			// Extend the base object
			for ( var name in options ) {
				var src = target[ name ], copy = options[ name ];

				// Prevent never-ending loop
				if ( target === copy )
					continue;

				// Recurse if we're merging object values
				if ( deep && copy && typeof copy === "object" && !copy.nodeType )
					target[ name ] = jQuery.extend( deep, 
						// Never move original objects, clone them
						src || ( copy.length != null ? [ ] : { } )
					, copy );

				// Don't bring in undefined values
				else if ( copy !== undefined )
					target[ name ] = copy;

			}

	// Return the modified object
	return target;
};
})();