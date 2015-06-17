<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
<link href="${ctx }/resources/third-party/css/user/login.css?ver=${version}" rel="stylesheet">
<script type="text/javascript" src="${ctx}/resources/third-party/js/login/login.js"></script>
<script type="text/javascript">
	var returnUrl='${returnUrl}';
	var randPref='${randPref}';
</script> 
</head>
<body>
	<div class="tc" style="margin-top:30px;"><span class="tc" style="margin-top:25px;"><img src="${ctx }/resources/third-party/images/login-logo.png" width="90"></span></div>
	<div class="userbox" style="margin-top:10px;">
		<img src="${ctx}/resources/third-party/images/icon1.png" width="24"/>
  		<input type="text" id="loginName" name="loginName" value="请输入手机号码" onfocus="if (value =='请输入手机号码'){value =''} this.type='number';this.style.color='#4d4d4d'" onblur=" if (!value){this.type='text';this.style.color='#c9c9c9';value='请输入手机号码'}"/>
  	</div>
	<div class="passwordbox">
		<img src="${ctx}/resources/third-party/images/icon2.png" width="24"/>
		<input type="password" id="password" name="password" value="请输入密码*" onfocus="if (value =='请输入密码*'){value =''} this.style.color='#4d4d4d'" onblur="if (value ==''){this.style.color='#c9c9c9';value='请输入密码*'}"/>
	</div>
	
	<div id="loginBtn" class="login" style="margin-top:15px;">登录</div>
	<div class="word" style="margin-top:13px;">
		<a href="javascript:window.location.href='${ctx}/anon/register?randPref=${randPref}'" id="zhuce">注册</a>
		<a href="javascript:window.location.href='${ctx}/anon/findPwd?randPref=${randPref}'" id="forget">忘记密码</a>
	</div>
</body>
</html>