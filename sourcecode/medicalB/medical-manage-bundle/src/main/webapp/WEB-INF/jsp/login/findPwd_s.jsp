<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
<link href="${ctx }/resources/third-party/css/user/findPwd_s.css?ver=${version}" rel="stylesheet">
<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/resources/third-party/js/login/findPwd_s.js"></script>
<script type="text/javascript">
	var loginName='${loginName}';
	var randPref='${randPref}';
</script>
</head>
<body>
	<div class="userbox">
		<span>新密码</span>
		<input id="password" name="password" type="password" onfocus="this.style.color='#4d4d4d'" onblur="if (!value){this.style.color='#c9c9c9'}"/>
	</div>
	<div class="passwordbox">
		<span>确认密码</span>
	  	<input id="rpassword" name="rpassword" type="password" onfocus="this.style.color='#4d4d4d'" onblur="if (!value){this.style.color='#c9c9c9'}"/>
	</div>
	<div class="word"></div>
	<div class="login" id="confirmNewPwdBtn">完成</div>
</body>
</html>