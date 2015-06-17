/** 定义包 */
var com = {};
var com.yoshu = {};
com.yushu.XHR = function(dataType) {
	this._dataType = dataType;
}
com.yushu.selfQuery = function() {

}

/**
 * ajax jsonp请求 参数 _opions：以{}包括的所有ajax请求 _url:
 * _callbackName:如果请求是jsop时，为回调函数名称，否则为空 _func：如果为其它请求类型，为回调函数对象，例如function(){}
 * 
 */
com.yushu.XHR.prototype.doAjax = function(_url, _callback, _data) {
	//
	console.log("data type is " + this._dataType);
	this.doReq(_url, _callback, _data);

}

/**
 * ajax jsonp请求
 * 
 */
com.yushu.XHR.prototype.doJsonp = function(_url, _callbackName, _data) {
	$.ajax({
		url : _url,
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : "jsonp",
		type : "POST",
		data : _data,
		jsonpCallback : _callbackName,
		cache : false,//
		success : function(json) {
			console.log(json.msg);
		},
		error : function(data, status, err) {
			console.log(err);
		}
	});

}
/**
 * 封装jquery load方法
 */
com.yushu.XHR.prototype.load = function(selector, url, _callback) {
	//
	$(selector).load(url, _callback);

}

/**
 * 封装 load html文件，包含script脚本
 */
com.yushu.XHR.loadHTML = function(selector, url) {
	//
	$(selector).load(url);
}

/**
 * HTML请求
 */
com.yushu.XHR.prototype.doReq = function(_url, _callback, _data) {
	console.log("数据类型：" + this._dataType);
	$.ajax({
		url : _url,
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		dataType : this._dataType,
		type : "POST",
		data : _data,
		cache : false,//
		success : function(json) {
			if (_callback != undefined && _callback != "") {
				console.log("execute callbck..");
				_callback(json);//
			}
		},
		error : function(data, status, err) {
			console.log(err);
		}
	});
}


/**
 * 定义静态方法 com.yushu.XHR.staticFun()
 */
com.yushu.XHR.staticFun = function() {

};

com.yushu.XHR.MOBILE_VESION = "1.0";

/**
 * js实现sleep
 */
function sleep(millis) {
	var date = new Date();
	var curDate = null;
	do {
		curDate = new Date();
	} while (curDate - date < millis);
}

/**
 * 弹出提示对话框； message: 对话框中的信息 (String) alertCallback: 当关闭对话框时候，执行回调方法. (Function)
 * title: 对话框标题 (String) (Optional, Default: "Alert") buttonName: 弹出框上的按钮名称
 * (String) (Optional, 默认: "OK")
 */
function showAlert(message, title, buttonName, alertCallback) {
	navigator.notification.alert(message, // message
	alertCallback, // callback
	title, // title
	buttonName // buttonName
	);
}

/**
 * 弹出提示确认对话框； message: 对话框中的信息 (String) callback: 当关闭对话框时候，执行回调方法. (Function)
 * title: 对话框标题 (String) (Optional, Default: "Alert") buttonLabels: Comma
 * separated string with button labels (String) (Optional, Default: "OK,Cancel")
 */
function showConfirm(message, callback, title, buttonLabels) {
	navigator.notification.confirm(message, // message
	callback, // callback to invoke with index of button pressed
	title, // title
	buttonLabels // buttonLabels
	);
}

/**
 * * 触发android 菜单键
 */
document.addEventListener("menubutton", _menu, false);

function _menu(){
	console.log("触发菜单点击事件......");
	var oDiv=document.createElement("DIV");
	oDiv.innerHTML="<a onClick=\"_exit();\">退出</a>&nbsp;&nbsp;<a onClick=\"_about();\">关于</a>&nbsp;&nbsp;<a href=\"#\" onclick=\"exitLogin();\" >更换号码登陆</a>";
	document.getElementById("mainDiv").appendChild(oDiv);
}

/**
 * * 触发android 回退键
 */
document.addEventListener("backbutton", function() {
	_exit();
}, false);

/**
**退出应用程序
*/
function _exit(){
	navigator.app.exitApp();//退出应用语句
}

/**
**修改密码；
*/
function changePassword() {
	var base64 = new Base64();
	var _custInfo=JSON.parse(window.sessionStorage.getItem("custInfo"));
	var userId = window.sessionStorage.getItem("userId");
	
	id=base64.encode(userId);
	id=rc4Encrypt(_custInfo.secretKey,id);
	id=Data2ASCIIHex(id);
	
	var newpassword=$("#newpassword").val();
	var comfirmpassword=$("#comfirmpassword").val();
	if(comfirmpassword != newpassword){
		showAlert("密码不一致！","信息提示","确定",null);
		return ;
	}
	
	newpassword=base64.encode(newpassword);
	newpassword=rc4Encrypt(_custInfo.secretKey,newpassword);
	newpassword=Data2ASCIIHex(newpassword);
	
	var xhr = new com.yushu.XHR("json");
	xhr.doReq(ChangePassword_URL, successChangePassword_jsonCallback, {
		accessToken : _custInfo.accessToken,
		id : id,
		password : newpassword
	});
	
}

function successChangePassword_jsonCallback(data){
	console.log("更新密码返回编号："+data.code);
	var base64 = new Base64();
	if(data.code==0){
		showAlert("密码修改成功！","信息提示","确定",null);
		window.location.href = "../html/detail.html";
		return;
	} else {
		if(data.code==10){
			showAlert("认证失败！","信息提示","确定",null);
		}
		if(data.code==54){
			showAlert("用户不存在或访问未授权！","信息提示","确定",null);
		}
		if(data.code==43){
			showAlert("弱密码，请选择更安全的密码！","信息提示","确定",null);
		}
	}
}

/**
**更新状态；
*/
function changeStatus() {
	var base64 = new Base64();
	var _custInfo=JSON.parse(window.sessionStorage.getItem("custInfo"));
	var userId = window.sessionStorage.getItem("userId");
	var status = window.sessionStorage.getItem("status");
	
	id=base64.encode(userId);
	id=rc4Encrypt(_custInfo.secretKey,id);
	id=Data2ASCIIHex(id);
	
	if(status == 1){
		status = "0";
	}else{
		status = "1";
	}
	console.log("激活与注销状态："+status);
	statusTemp = status;
	
	status=base64.encode(status);
	status=rc4Encrypt(_custInfo.secretKey,status);
	status=Data2ASCIIHex(status);
	
	var xhr = new com.yushu.XHR("json");
	xhr.doReq(ChangeStatus_URL, successChangeStatus_jsonCallback, {
		accessToken : _custInfo.accessToken,
		id : id,
		status : status
	});
	
}

function successChangeStatus_jsonCallback(data){
	console.log("更新状态返回编号："+data.code);
	var base64 = new Base64();
	if(data.code==0){
		if(statusTemp == '0'){
			showAlert("状态注销成功！","信息提示","确定",null);
			statusTag = "<a href='javascript:changeStatus()'><img src='../images/jh.png'>注销</a>";
		}else{
			showAlert("状态激活成功！","信息提示","确定",null);
			statusTag = "<a href='javascript:changeStatus()'><img src='../images/jh.png'>激活</a>";
		}
		$("#statusTag").html(statusTag);
		window.sessionStorage.setItem("statusTag",statusTag);
		return;
	} else {
		showAlert("更新状态失败！","信息提示","确定",null);
		return;
	}
}

/**
**注销应用程序
*/
function loginout(){
	window.location.href = "../html/login.html";
	window.sessionStorage.clear();
	window.sessionStorage.setItem("login","0");
}

/**
**注销应用程序
*/
function goback(index){
	var url = window.sessionStorage.getItem(index);
	console.log("回退到："+url);
	if(index == undefined || index == ''){
		window.location.href = "../html/login.html";
		return;
	}
	window.location.href = url;
}

/**
**页面跳转
*/
function goNextPage(index,url){
	if(url == undefined || url == ''){
		showAlert("页面跳转失败！","信息提示","确定",null);
		return;
	}
	window.sessionStorage.setItem(index,url);
	window.location.href = url;
}
/***
 * 判断传入的参数是否为空；
 */
function isEmpty(obj){
	console.log("参数是否为空，参数值："+obj);
	if(obj == null || obj == undefined || obj == ''){
		return true;
	}
	else{
		return false;
	}
}

/**
 * Encrypt given plain text using the key with RC4 algorithm. All parameters and
 * return value are in binary format.
 * 
 * @param string
 *            key - secret key for encryption
 * @param string
 *            pt - plain text to be encrypted
 * @return string
 */
function rc4Encrypt(key, pt) {
	s = new Array();
	for ( var i = 0; i < 256; i++) {
		s[i] = i;
	}
	var j = 0;
	var x;
	for (i = 0; i < 256; i++) {
		j = (j + s[i] + key.charCodeAt(i % key.length)) % 256;
		x = s[i];
		s[i] = s[j];
		s[j] = x;
	}
	i = 0;
	j = 0;
	var ct = '';
	for ( var y = 0; y < pt.length; y++) {
		i = (i + 1) % 256;
		j = (j + s[i]) % 256;
		x = s[i];
		s[i] = s[j];
		s[j] = x;
		ct += String.fromCharCode(pt.charCodeAt(y) ^ s[(s[i] + s[j]) % 256]);
	}
	return ct;
}

/**
 * Decrypt given cipher text using the key with RC4 algorithm. All parameters
 * and return value are in binary format.
 * 
 * @param string
 *            key - secret key for decryption
 * @param string
 *            ct - cipher text to be decrypted
 * @return string
 */
function rc4Decrypt(key, ct) {
	return rc4Encrypt(key, ct);
}

function Base64() {

	// private property
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

	// public method for encoding
	this.encode = function(input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output + _keyStr.charAt(enc1) + _keyStr.charAt(enc2)
					+ _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	}

	// public method for decoding
	this.decode = function(input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	}

	// private method for UTF-8 encoding
	_utf8_encode = function(string) {
		if(string == undefined || string == ''){
			return string;
		}
		
		string = string.replace(/\r\n/g, "\n");
		var utftext = "";
		for ( var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}
		return utftext;
	}

	// private method for UTF-8 decoding
	_utf8_decode = function(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while (i < utftext.length) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12)
						| ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
}

function Data2ASCIIHex(S) {
	var Result = "";
	var Chars = "0123456789ABCDEF"
	for (i = 0; i < S.length; ++i) {
		Byte = S.charCodeAt(i);
		lo = Byte & 0x0F;
		hi = Byte >> 4;
		Result += Chars.charAt(hi) + Chars.charAt(lo);
	}
	return Result;
}

function ASCIIHex2Data(hex) {
	var str = '';
	for ( var i = 0; i < hex.length; i += 2)
		str += String.fromCharCode(parseInt(hex.substr(i, 2), 16));
	return str;
}
