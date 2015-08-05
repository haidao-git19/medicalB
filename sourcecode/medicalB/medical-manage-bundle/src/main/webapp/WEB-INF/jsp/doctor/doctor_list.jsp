<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>专家管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/doctor/doctor.js"></script>
		
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
				<li class="active">专家管理 <span class="divider">/</span></li>
				<li class="active">专家管理</li>
			</ul>
			
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="realName">医生姓名:</label> 
						<input id="realName" name="realName" type="text" class="input-text-medium" placeholder="医生姓名">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			
			<div class="row-fluid toolbar">
				<shiro:hasAnyRoles name="平台管理员,医院管理员">
	        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/doctor/initAddOrUpdate'">新增</button>
				</shiro:hasAnyRoles>
        	</div>
	        <table id="doctor_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>医生姓名</th>
						<th>所属科室</th>
						<th>电话</th>
						<th>职称</th>
						<th>网络咨询</th>
						<th>语音咨询</th>
						<th>视频咨询</th>
						<th>转诊</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</body>

</html>