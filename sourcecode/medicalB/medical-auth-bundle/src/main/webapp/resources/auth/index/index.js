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

var IframeOnClick = {
	resolution : 200,
	iframes : [],
	interval : null,
	Iframe : function() {
		this.element = arguments[0];
		this.cb = arguments[1];
		this.hasTracked = false;
	},
	track : function(element, cb) {
		this.iframes.push(new this.Iframe(element, cb));
		if (!this.interval) {
			var _this = this;
			this.interval = setInterval(function() {
				_this.checkClick();
			}, this.resolution);
		}
	},
	checkClick : function() {
		if (document.activeElement) {
			var activeElement = document.activeElement;
			for ( var i in this.iframes) {
				if (activeElement === this.iframes[i].element) { // user is in this
																													// Iframe
					if (this.iframes[i].hasTracked == false) {
						this.iframes[i].cb.apply(window, []);
						this.iframes[i].hasTracked = true;
					}
				} else {
					this.iframes[i].hasTracked = false;
				}
			}
		}
	}
};

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
			var tops = findRootMenus();

			$.each(tops,function(i,top){
				var roots = findChildMenus(top.id);
				if(top.name!=null&&top.name.indexOf('左边菜单')>=0){
					//加载子系统
					$('.sys-icons ul').empty();
					$.each(roots, function(i, root) {
						var iconUrl = root.iconUrl?root.iconUrl : ctx + '/resources/auth/index/home.png';
						$('.sys-icons ul').append("<li><a id='{1}'><img src='{0}'/>{2}</a></li>".format(iconUrl,root.id,root.name));
					})
				}else if(top.name!=null&&top.name.indexOf('设置')>=0){
					/*$.each(roots, function(i, root) {
						var iconUrl = root.iconUrl?root.iconUrl : ctx + '/resources/auth/index/home.png';
						$('#subNavDiv1').append("<a class='s2' style='color:black;text-decoration:none;' id='{1}'><span style='background:url({0}) no-repeat;display:block;width:45px;height:45px;'></span><span style='font-size: 0.8em;'>{2}</span></a>".format(iconUrl,root.id,root.name));
					})*/
				}else if(top.name!=null&&top.name.indexOf('功能服务')>=0){
					if(roots.length>1){
						$("#set1").css({"background":"url("+ctx+"/resources/auth/img/login/img_18.jpg) right center no-repeat"});
						$.each(roots, function(i, root) {
							var iconUrl = root.iconUrl?root.iconUrl : ctx + '/resources/auth/index/home.png';
							$('#subNavDiv2').append("<a class='s2' style='color:black;text-decoration:none;' id='{1}'><span style='background:url({0}) no-repeat;display:block;width:45px;height:45px;'></span><span style='font-size: 0.8em;'>{2}</span></a>".format(iconUrl,root.id,root.name));
						})
					}else if(roots.length==1){
						set1Flag=1;
						$("#set1").css({"background":""});
						var iconUrl = roots[0].iconUrl?roots[0].iconUrl : ctx + '/resources/auth/index/home.png';
						$("#parentNav2").empty().append("<a href='javascript:goIndexPage2(this);' style='color: #fff;' target='_top'>"+roots[0].name+"</a>");
						//$('#subNavDiv2').append("<a class='s2' style='color:black;text-decoration:none;' id='{1}'><span style='background:url({0}) no-repeat;display:block;width:45px;height:45px;'></span><span style='font-size: 0.8em;'>{2}</span></a>".format(iconUrl,roots[0].id,roots[0].name));
					}
				}
			});
			/*$('.sys-icons ul').promptumenu({
				'height' : $('.sys-icons').height(),
				'rows': 3,
				'columns': 3,
				'direction': 'horizontal',
				'pages': true
			});*/
		}
	})
}

function loadMenu(root){
	$("#kinMax").hide();
	$("#brand").text(root.description?root.description:root.name);
	$("#brand").data("menu-id",root.id);
	var home = root.link.replace(/\/?#*$/,"");
	var path = home.substr(home.lastIndexOf('/'));
	$("#logout_link").attr("href",home + "/logout");
	var $sidebar = $("#side_menu").empty();
	var $folder = $("<div></div>").setTemplateElement("menu_tmpl");
	var folders = findChildMenus(root.id);
	$.each(folders,function(i,menu){
		var iconUrl = menu.iconUrl?menu.iconUrl : ctx + '/resources/auth/index/home.png';
		menu.iconUrl = iconUrl;
		var $html = $folder.processTemplate(menu);
		var ul = $html.find("ul.nav-list").empty();
		var children = findChildMenus(menu.id);
		$.each(children,function(j,child){
			var link = child.link;
			if(!link.startWith("http") && !link.startWith("https")) {
				if(link.startWith(path)){
					link = link.replace(path,"");
				}
				if(!link.startWith("/")){
					link = "/" + link;
				}
				link = home + link;
			}
			var iconUrl = child.iconUrl?child.iconUrl : ctx + '/resources/auth/index/home.png';
			if(link.indexOf("?") > 0){
				ul.append("<li id='submenu_{0}'><a href='{1}&parentMenu={3}&childMenu={2}' target='main_frame'><img src='{4}' width='14' height='14' style='margin-right:10px;'/>{2}<i class='icon-chevron-right'></i></a></li>".format(child.id,link,child.name,menu.name,iconUrl));
			}else{
				ul.append("<li id='submenu_{0}'><a href='{1}?parentMenu={3}&childMenu={2}' target='main_frame'><img src='{4}' width='14' height='14' style='margin-right:10px;'/>{2}<i class='icon-chevron-right'></i></a></li>".format(child.id,link,child.name,menu.name,iconUrl));
			}
		})
		$html.find("li:first").css("margin-top","1em");
		$html.find("li:last").css("margin-bottom","1em");
		$sidebar.append($html.html());
	});

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

function findChildMenus(pid) {
	var children = [];
	if(!pid) {
		return findRootMenus();
	}
	$.each(g_menus,function(i,menu){
		if(menu.parent && menu.parent.id == pid) {
			children.push(menu);
		}
	}) 
	return children;
}

function findMenuById(id) {
	var ret = null;
	$.each(g_menus,function(i,menu){
		if(menu.id == id) {
			ret = menu;
			return false;
		}
	});
	return ret;
}

function initAuthUrl(){
	$.ajax({
		url : ctx + '/anon/getAuthUrl',
		type : 'POST',
		success : function(data) {
			if(data) {
				$("#auth_link").attr("href",data);
				$("#pwd_link").attr("href",data + '/user/changePwd');
			}
		}
	});
}

function hideBreadcrumb(){
	$(".content .breadcrumb").hide();
	$(".content .iframe-wrap").css("margin-top","0");
}

function showBreadcrumb(){
	var $el = $(".content .breadcrumb").show();
	$(".content .iframe-wrap").css("margin-top",$el.height()+"px");
}

function hideSidebar(){
	$(".content .split").hide();
	$(".content").css({
		"margin-left" : 0,
		"border-left" : 0
	});
}

function showSidebar(){
	$(".content").css({
		"border-left" : "4px solid #999"
	});
	$(".content .split").show();
	$(".content").animate({
		"margin-left" : $(".sidebar-nav").width()
	},function(){
		showFrame();
		$(".sidebar-nav a.nav-header:first").trigger("click");
	});
}

function showFrame(){
	$(".sys-icons").hide();
	$(".iframe-wrap").show();
}

function showBlank(){
	$("#main_frame").removeAttr("src");
}

$(function(){
	
	$(document).on("click",".content .split.left",function(){
		$(this).removeClass("left");
		$(this).addClass("right");
		$(".content").animate({
			'margin-left' : 0
		})
	});
	
	$(document).on("click",".content .split.right",function(){
		$(this).removeClass("right");
		$(this).addClass("left");
		$(".content").animate({
			'margin-left' : $(".sidebar-nav").width()
		})
	});
	
	$(document).on("click",".sidebar-nav .nav-list>li",function(){
		$(".sidebar-nav .nav-list>li").removeClass("active");
		$(this).addClass("active");
		var pText = $(this).closest("ul").prev("a:first").text();
		var text = $(this).find("a:first").text();
		var bread = $(".content .breadcrumb").empty();
		bread.append("<li class='active'>{0}<span class='divider'>/</span></li>".format(pText));
		bread.append("<li class='active'>{0}</li>".format(text));
	});
	
	$(document).on("click",".sys-icons ul li a",function(){
		var $this = $(this);
		if(!$.browser.msie){
			$this.css("opacity","0.5");
		}
		setTimeout(function(){
			try{
				var id = $this.attr("id");
				showSidebar();
				loadMenu(findMenuById(id));
				showBlank();
			}catch(e){
			}
			$(".leftside_content").css("width",0);
			$this.css("opacity","1");
		},200);
	})
	
	$(document).on("click",".subNav a",function(){
		var $this = $(this);
		if(!$.browser.msie){
			$this.css("opacity","0.5");
		}
		setTimeout(function(){
			try{
				var id = $this.attr("id");
				var _content = $this.children("span").last().text();
				modifyNavTitle(_content);
				showSidebar();
				loadMenu(findMenuById(id));
				showBlank();
			}catch(e){
			}
			$this.css("opacity","1");
		},200);
	})
	
	$(document).on("mouseover",".subNav a",function(){  
		$(this).css("background","#f9f9f9");
	}); 
	$(document).on("mouseout",".subNav a",function(){  
		$(this).css("background","none");
	}); 
	
	IframeOnClick.track(document.getElementById("main_frame"), function() { 
		$(document).trigger("click");
	});
	
	initMenu();
})