<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no" name="viewport">
<link href="http://localhost:8081/resources/third-party/css/template/recoveryDetail.css" rel="stylesheet" type="text/css" />
<title>${recovery.title}</title>
</head>

<body>
	<div class="tit">
	  	<h2>${recovery.title}</h2>
	  	<span class="titcc">${recovery.publisher}&nbsp;&nbsp;&nbsp;&nbsp;${recovery.createTime}</span> </div>
		<div class="info"> 
			${recovery.content}
		</div>
		<div id="copyright"> Copyright &copy; 2015.SSO All rights reserved. </div>
</body>
</html>
