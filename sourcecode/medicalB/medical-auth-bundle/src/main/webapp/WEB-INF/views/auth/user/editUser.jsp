<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<jsp:include page="/WEB-INF/views/auth/common.jsp"></jsp:include>
		<script type="text/javascript">
			var readOnly = '${param.readOnly}' === 'true';
			var user = null;
			var userRoleIds = [];
			var userRoles = [];
			var userProducts = [];
			var userPlatforms = [];
			var userProvinceId = null;
			var userCityId = null;
			
			var curUserId = "<shiro:principal property='id'/>";
			var curUserLoginName = "<shiro:principal property='loginName'/>";
		</script>
		<script type="text/javascript" src="${ctx }/resources/auth/js/user/editUser.js"></script>
		
		<style type="text/css">
		.nav-tabs > .active > a ,.nav-tabs > .active > a:hover,.nav-tabs > .active > a:focus{
			background-color: #0088cc;
			color : #fff;
		}
		</style>
	</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">权限管理 <span class="divider">/</span></li>
				<li class="active">用户管理</li>
			</ul>
			<form id="user_form">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="profile_tab">
						<input type="hidden" id="id" name="id" value="${param.id }">
						<div class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="loginName">登录名:<span class="help-inline">*</span></label> 
								<div class="controls">
									<input type="text" id="loginName" name="loginName">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="trueName">用户姓名:<span class="help-inline">*</span></label> 
								<div class="controls">
									<input type="text" id="trueName" name="trueName">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="status">用户状态:</label> 
								<div class="controls">
								<select id="status" name="status" class="input-medium">
									<option value="1">有效</option>
									<option value="0">无效</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">性别:</label>
								<div class="controls">
									<label class="radio inline">
										<input type="radio" id="sex_male" name="sex" value="男"> 男
									</label>
									<label class="radio inline">
										<input type="radio" id="sex_female" name="sex" value="女"> 女
									</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="phone">手机号:</label>
								<div class="controls">
									<input type="text" id="phone" name="phone">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="email">电子邮箱:</label>
								<div class="controls">
									<input type="text" id="email" name="email">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#data_tab" data-toggle="tab">数据权限</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="data_tab">
						<div class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="province">地域权限:</label>
								<div class="controls">
									<select id="province" name="province" class="input-medium" style="width:170px;">
									</select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<select id="city" name="city" class="input-medium" style="width:170px;">
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="productIds">产品权限:</label>
								<div class="controls">
									<select id="productIds" name="productIds" class="input-medium" multiple="multiple">
									</select>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<select id="platformIds" name="platformIds" class="input-medium" multiple="multiple">
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#permission_tab" data-toggle="tab">操作权限</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="permission_tab">
						<div class="form-horizontal">
							<div class="control-group">
								<label class="control-label">操作权限:</label>
								<div class="controls" id="roles">
									<label class="checkbox inline">
										<input type="checkbox" id="role_{0}" name="roleIds" value="{0}"> {1}
									</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-btns">
				<button type="button" class="btn btn-primary" id="btn_save">保存</button>
				<button type="button" class="btn btn-primary" onclick="history.go(-1);">返回</button>
			</div>
			</form>
		</div>
	</div>

</body>
</html>