<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>电子病历</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=8" >
		<link href="${ctx }/resources/third-party/jquery-ui-bs/assets/css/bootstrap.css?ver=${version}" rel="stylesheet">
	</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			
			<ul class="breadcrumb">
				<li class="active">电子病历 <span class="divider">/</span></li>
				<li class="active">附件详情</li>
			</ul>
			
			<input type="button" value="返回" onclick="window.history.go(-1);" class="btn btn-primary" />
			
			<div style="margin: 0 auto;" align="center">
				<img alt="${emrecordAtta.attaName}" src="${ctx }/showImg?fileName=${emrecordAtta.attaURL}" />
				<br><br>
				<span>(${emrecordAtta.attaName})</span>
			</div>
			
		</div>
	</div>
</body>

</html>