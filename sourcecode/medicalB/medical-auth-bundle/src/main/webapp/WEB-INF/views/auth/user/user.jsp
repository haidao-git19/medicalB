<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<jsp:include page="/WEB-INF/views/auth/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/auth/js/user/user.js"></script>
		
		<style type="text/css">
		#user_table {
			min-width : 800px;
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
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="province">所属省份:</label> 
						<select id="province" name="province" class="input-medium">
						</select>
					</div>
					<div class="control-group inline">
						<label class="inline" for="city">所属城市:</label> 
						<select id="city" name="city" class="input-medium">
						</select>
					</div>
					<div class="control-group inline">
						<label class="inline" for="status">用户状态:</label> 
						<select id="status" name="status" class="input-medium">
							<option value="">选择状态</option>
							<option value="1">有效</option>
							<option value="0">无效</option>
						</select>
					</div>
					<div class="control-group inline">
						<label class="inline" for="name">用户名:</label> 
						<input id="name" name="name" type="text" class="input-text-medium" placeholder="用户名/登录名">
					</div>
					<div class="control-group inline">
						<label class="inline" for="roleName">角色名:</label> 
						<input id="roleName" name="roleName" type="text" class="input-text-medium" placeholder="角色名">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/user/edit'">新增</button>
        	</div>
	        <table id="user_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>登录名</th>
						<th>用户姓名</th>
						<th>区域</th>
						<th>角色</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>密码初始化</th>
						<th>查看</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>

</body>
</html>