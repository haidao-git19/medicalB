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

var g_menus = [];
function initMenu() {
	$.ajax({
		url : ctx + '/anon/getPermissionMenusOnceAll',
		type : 'post',
		data : [ {
			name : 'loginName',
			value : loginName
		} ],
		dataType : 'json',
		success : function(menus) {
			g_menus = menus;
			var roots = findRootMenus();

			//加载子系统
			$('.under_part ul').empty();
			$.each(roots, function(i, root) {
				var iconUrl = root.iconUrl?root.iconUrl : ctx + '/resources/auth/img/index/mircoindex.png';
				$('.under_part ul').append("<li><span><a id='{1}' href='{3}'><img src='{0}' width='24' height='24' /><span class='micromenulname'>{2}</span></a></span></li>".format(iconUrl,root.id,root.name,root.link));
			})
		}
	})
}

function findRootMenus(){
	var roots = [];
	$.each(g_menus,function(i,menu){
		if(!menu.parent){
			roots.push(menu);
		}
	})
	return roots;
}

$(function(){
	initMenu();
})