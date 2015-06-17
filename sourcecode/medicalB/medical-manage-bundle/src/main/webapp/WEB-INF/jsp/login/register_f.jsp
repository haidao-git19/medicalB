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
<link href="${ctx }/resources/third-party/css/user/register_f.css?ver=${version}" rel="stylesheet">
<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/resources/third-party/js/login/register_f.js"></script>
<script type="text/javascript">
	var randPref='${randPref}';
</script>
</head>
<body>
	<div class="userbox">
		<div class="userboxleft">国家地区</div>
		<img src="${ctx}/resources/third-party/images/more_03.png" width="16" style="margin-top:9px;"/>
	  	<div class="userboxright">中国大陆</div>
	</div>
	<div class="passwordbox">
	  <span>+86</span>
	  <div class="gray"></div>
	    <input name="loginName" type="text" id="loginName" value="请输入手机号码" onfocus="if (value =='请输入手机号码'){value =''} this.type='number';this.style.color='#4d4d4d'" onblur="if (value ==''){this.type='text';this.style.color='#c9c9c9';value='请输入手机号码'}"/>
	</div>
	<div class="yanzhengma">
		<div class="shuru"><input name="valiCode" type="text" id="valiCode" disabled="disabled" onfocus="if (value){this.type='number';this.style.color='#4d4d4d'} " onblur="if (!value){this.type='text';this.style.color='#c9c9c9';}"/></div>
		<div class="huqu" id="getValiCode"><a>获取验证码</a></div>
	</div>
	<div class="selectbox" id="agreement"><input class="select" id="checkBox" name="" type="checkbox" value="" />同意网牛账号的<span class="blue">用户协议与隐私政策 </span> </div>
	<div class="login" id="registerButton_first">
		<a href="javascript:void(0);">下一步</a>
	</div>
</body>
</html>