$(function(){
	
//	if (window.devicePixelRatio == 2) { //兼容retain浏览器高清logo图片
//	       document.getElementById("yao_logo").src = "http://p1.maiyaole.com/images/201306/images/logo_new_2.gif";
//	}

	
	var count=getItemCount();
	
	if(count== undefined || count<=0){
		$("#in_cart_num").html("0");
	}else{$("#in_cart_num").html(count);}
	
	var userName=createUser();
	if(userName==undefined || userName==null){
		$("#user_name").html("您好");
		$("#logout").hide();
		$("#login").show();
	}else{
		$("#user_name").html(userName);
		$("#logout").show();
		$("#login").hide();
	}
	if(!$.cookie('provinceId'))$.cookie('provinceId', 1,{path: '/', domain: '.111.com.cn', expires: 30});
	var provinceId=getProvinceId();
	
	if(provinceId==undefined || provinceId==null){
		showProvinces();
	}
	else{
		$("#currProvince").text($('#p_' + provinceId).text());
		jQuery('#allProvinces').hide();
	}
	
})

function getProvinceId(){
	var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf('provinceId') == 0) 
       return c.substring('provinceId'.length+1, c.length);
    }
}

// 验证用户登录Cookies
function ppkRead(name, second) {
if (!second) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
} else {
    var val = ppkRead(name);
    if (val) {
        var arr = val.split('&');
        for (var i = 0, len = arr.length; i < len; i++) {
            if (arr[i].indexOf(second) > -1) {
                return arr[i].substring(arr[i].indexOf("=") + 1);
                }
            }
            return null;
        }
        return null;
    }
}

function getItemCount(){
	var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf('cartItemCount') == 0) 
       return c.substring('cartItemCount'.length+1, c.length);
    }
}

var loginUser;
//读取用户Cookies 创建loginUser对象
function createUser(){
    loginUser = new Object();
    loginUser.UserId = ppkRead("UserInfo", "UserId");//用户ID 
loginUser.UserName = decodeURIComponent(ppkRead("UserInfo", "UserName"));//用户名
loginUser.NickName=decodeURIComponent(ppkRead("UserInfo", "NickName"));//用户名
loginUser.Token = ppkRead("UserInfo", "Token");//令牌号
loginUser.Security = ppkRead("UserInfo", "Security");//加密串
if(loginUser.UserName == null || loginUser.UserName == '' || loginUser.UserName == 'null'){
    	return null;
    } else {
    	if(loginUser.NickName == null || loginUser.NickName == '' || loginUser.NickName == 'null')
    		return loginUser.UserName;
    	return loginUser.NickName;
    }
}

function addmaskbox() {//增加遮罩
	jQuery(document.body).append('<div id="maskBoxPop"></div>');
	var bH=$("body").height();
	var bW=$("body").width();
	$("#maskBoxPop").css({width:bW,height:bH,display:"block"});
}

function getObjWh(obj){
	var st=document.documentElement.scrollTop+document.body.scrollTop;//滚动条距顶部的距离
	var sl=document.documentElement.scrollLeft+document.body.scrollLeft;//滚动条距左边的距离
	var ch=document.documentElement.clientHeight;//屏幕的高度
	var cw=document.documentElement.clientWidth;//屏幕的宽度
	var objH=$("#"+obj).height();//浮动对象的高度
	var objW=$("#"+obj).width();//浮动对象的宽度
	var objT=Number(st)+(Number(ch)-Number(objH))/2;
	var objL=Number(sl)+(Number(cw)-Number(objW))/2;
	return objT+"|"+objL;
}
//传id
function resetBg(dialog){
	var maskBoxPop=$("#maskBoxPop").css("display");
	if(maskBoxPop=="block"){
	var bH2=$("body").height();
	var bW2=$("body").width();
	$("#maskBoxPop").css({width:bW2,height:bH2});
	var objV=getObjWh(dialog);
	var tbT=objV.split("|")[0]+"px";
	//var tbL=objV.split("|")[1]+"px";
	$("#"+dialog).css({top:tbT});
	}
}

function removemaskbox() {//去掉遮罩
	$("#maskBoxPop").remove();
}

function showProvinces(){
	addmaskbox();
	var objWH=getObjWh("allProvinces");
	var tbT=objWH.split("|")[0]+"px";
	//var tbL=objWH.split("|")[1]+"px";
	$("#allProvinces").css({top:tbT,display:"block"});
	$(window).scroll(function(){resetBg("allProvinces")});
	$(window).resize(function(){resetBg("allProvinces")});
	//$("#allProvinces").show();
}

function closeProvinces(){
	$("#allProvinces").hide();
	removemaskbox();
}

function setAddressCity(provinceId){
	
	if(provinceId==null ||provinceId==undefined ||provinceId == '' )return;
	
	$("#currProvince").text($('#p_' + provinceId).text());
	$('#allProvinces').hide();
	$.cookie('provinceId', provinceId, {path: '/', domain: '.111.com.cn', expires: 15});
	removemaskbox();
	window.location.reload(true);
	
}
