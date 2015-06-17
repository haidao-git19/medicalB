<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<jsp:include page="/WEB-INF/views/auth/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/auth/js/user/changePwd.js"></script>
		
		<style type="text/css">
		</style>
	</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">权限管理 <span class="divider">/</span></li>
				<li class="active">修改密码</li>
			</ul>
			<form id="pwd_form">
				<div class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="oldPassword">原密码:<span class="help-inline">*</span></label> 
						<div class="controls">
						<input type="password" id="oldPassword" name="oldPassword">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="newPassword">新密码:<span class="help-inline">*</span></label> 
						<div class="controls">
							<input type="password" id="newPassword" name="newPassword">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="confirmPassword">确认密码:</label> 
						<div class="controls">
						<input type="password" id="confirmPassword" name="confirmPassword">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"></label> 
						<div class="controls">
						<button type="button" class="btn btn-primary" id="btn_save">提交</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>