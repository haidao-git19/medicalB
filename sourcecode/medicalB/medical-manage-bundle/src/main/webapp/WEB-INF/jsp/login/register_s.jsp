<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
<link href="${ctx }/resources/third-party/css/user/register_s.css?ver=${version}" rel="stylesheet">
<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/resources/third-party/js/form.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js?ver=${version}" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/third-party/js/login/register_s.js"></script>
<script type="text/javascript">
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '${appId}', // 必填，公众号的唯一标识
    timestamp: '${timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
    signature: '${signature}',// 必填，签名，见附录1
    jsApiList: ['checkJsApi',
                'chooseImage',
                'previewImage',
                'uploadImage',
                'downloadImage'
      ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

var serverId = null;
wx.ready(function () {
	   var images = {
		    localId: [],
		    serverId: []
	   };
	  // 5 图片接口
	  // 5.1 拍照、本地选图
	  document.querySelector('#preview').onclick = function () {
	    wx.chooseImage({
	      success: function (res) {
	        images.localId = res.localIds;
	        $("#iconImg").attr('src',images.localId[0]);
	        wx.uploadImage({
		        localId: images.localId[0], // 需要上传的图片的本地ID，由chooseImage接口获得
		        isShowProgressTips: 1,
		        success: function (res) {
		        	serverId = res.serverId;
		        },
		        fail: function (res) {
			        alert(JSON.stringify(res));
			   }
		 });
	    }
	  });
	};
});
</script>
<style type="text/css">
	.file{  
	    width:0px;  
	    height:0px;  
	    opacity:0;  
	    filter:alpha(opacity=0);  
	    cursor:pointer;  
    }  
</style>
</head>
<body>
	<div id="preview" class="photobox">
		<img id="iconImg" src="${ctx}/resources/third-party/img/user/touxiang_03_03_03.png" width="100" height="100" />
	</div>
	<form id="registerSecondForm" method="post" enctype="multipart/form-data">
		<input type="hidden" id="loginName" name="loginName" value="${loginName}">
		<input type="hidden" id="userId" name="userId">
		<input type="hidden" id="randPref" name="randPref" value="${randPref}">
		<div class="nc">
		  <span>昵称</span>
		  <input name="nickName" type="text" id="nickName" />
		</div>
		<div class="sex">
			<span>性别</span>
			   <label><input name="sex" type="radio" id="RadioGroup1_0" value="1" checked="checked" />男</label>
			   <label><input name="sex" type="radio" id="RadioGroup1_1" value="2" />女</label>
		</div>
		<div class="nc">
		  <span>登录密码</span>
		  <input name="password" type="password" id="password" />
		</div>
		<div class="nc">
		  <span>确认密码</span>
		  <input name="rpassword" type="password" id="rpassword" />
		</div>
		<div class="login" id="registerDoneBtn"><a id="finish" href="javascript:void(0);">完成</a></div>
	</form>
	<form id="iconForm" enctype="multipart/form-data" method="post">
		<input type="hidden" id="userId" name="userId">
		<input type="file" accept="image/*" name="file" class="file" id="file" onchange="previewImage(this);"/>
	</form>
</body>
</html>