// 给Date类型增加格式化原型方法
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

$(function() {
	$(document).ajaxError(function(evt, req, opts, ex) {
		if (req.status === 0 || req.readyState === 0) {
			return;
		}
		if (req.status === 404) {
			return;
		}
		try{
			var result = $.parseJSON(req.responseText);
		}catch(e){
		}
	}).ajaxSend(function(evt, request, settings) {
		var target = settings.maskTarget;
		if (settings.maskTarget) {
			$(settings.maskTarget).unmask();
			$(settings.maskTarget).mask("加载中...", 500);
		}
	}).ajaxComplete(function(event, request, settings) {
		var target = settings.maskTarget;
		if (settings.maskTarget) {
			$(settings.maskTarget).unmask();
		}
	});
});

(function($) {
	$.fn.ellipsis = function() {

		this.css({
			'overflow' : 'hidden',
			'text-overflow' : 'ellipsis',
			'white-space' : 'nowrap'
		});

		this.bind('mouseenter', function() {
			var $this = $(this);
			if (this.offsetWidth < this.scrollWidth) {
				$this.attr('title', $this.text());
			}
		});
	};
})(jQuery);

function getString(data){
	return data==null||data==undefined?'':data;
}

function getNumber(data){
	return data==null||data==undefined?0:data;
}

function queryUserId(){
	var userId = null;
	$.ajax({
		url : ctx + "/anon/queryUserId",
		type : "POST",
		async:false,
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return null;
			}
			userId = data;
		},
		error:function(msg){
			alert(msg);
		}
	});
	return userId;
}

jQuery.focusblur = function(focusid) {
	var focusblurid = $(focusid);
	var defval = focusblurid.val();
	focusblurid.focus(function() {
		var thisval = $(this).val();
		if (thisval == defval) {
			$(this).val("");
		}
	});
	focusblurid.blur(function() {
		var thisval = $(this).val();
		if (thisval == "") {
			$(this).val(defval);
		}
	});
	};
