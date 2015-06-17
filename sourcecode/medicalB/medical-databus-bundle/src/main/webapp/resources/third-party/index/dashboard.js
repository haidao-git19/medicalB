//给Date类型增加格式化原型方法
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"H+" : this.getHours(), // hour
		"h+" : this.getHours(),
		//"h+" : this.getHours()%12, //12 hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(m, i) {
		return args[i];
	});
}

String.prototype.replaceAll = function stringReplaceAll(AFindText, ARepText) {
	raRegExp = new RegExp(AFindText, "g");
	return this.replace(raRegExp, ARepText);
};

String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length)
		return false;
	if (this.substring(this.length - str.length) == str)
		return true;
	else
		return false;
	return true;
}
String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
}

var g_menus = [];

$(function(){
	window.parent.hideSidebar();
	initMenu();
	
	$(document).on("click",".sys-icons ul li",function(){
		var $this = $(this);
		if(!$.browser.msie){
			$this.css("opacity","0.5");
		}
		setTimeout(function(){
			try{
				var id = $this.find("a").attr("id");
				window.parent.showSidebar();
				window.parent.loadMenu(window.parent.findMenuById(id));
				window.parent.showBlank();
			}catch(e){
			}
			$this.css("opacity","1");
		},200);
	})
})

function initMenu() {
	var roots = window.parent.findRootMenus();
	$('.sys-icons ul').empty();
	$.each(roots, function(i, root) {
		var iconUrl = root.iconUrl?root.iconUrl : ctx + '/resources/auth/index/Browser.png';
		$('.sys-icons ul').append("<li><img src='{0}'/><a href='javascript:void(0);' id='{1}'>{2}</a></li>".format(iconUrl,root.id,root.name));
	})
	$('.sys-icons ul').promptumenu({
		'height' : $(window).height(),
		'rows': 3,
		'columns': 3,
		'direction': 'horizontal',
		'pages': true
	});
}