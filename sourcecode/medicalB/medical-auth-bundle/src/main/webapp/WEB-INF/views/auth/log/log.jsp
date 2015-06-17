<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>操作记录</title>
	<jsp:include page="/WEB-INF/views/auth/common.jsp"></jsp:include>
	
	<script type="text/javascript" src="${ctx }/resources/auth/js/log/log.js"></script>
</head>
<body>
	<div class="container-fluid">
		<ul class="breadcrumb">
			<li class="active">权限管理 <span class="divider">/</span></li>
			<li class="active">操作记录</li>
		</ul>
		<div class="row-fluid" style="max-width : 1000px;">
			<div class="form-inline">
				<div class="control-group inline">
					<label class="inline field" for="text">模糊查询:</label>
					<input id="text" name="text" type="text" class="input-text-medium" placeholder="用户名/操作内容 ">
				</div>
				<div class="control-group inline">
					<label class="inline field" for="startDate">起止日期:</label>
					<input type="text" class="input-medium date-picker Wdate"
							id="startDate" name="startDate"
							onfocus="WdatePicker({readOnly:true,dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\',{s:-1})}'})">
				</div>
				<div class="control-group inline">
					<input type="text" class="input-medium date-picker Wdate"
							id="endDate" name="endDate"
							onfocus="WdatePicker({ readOnly:true,dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\',{s:+1})}'})">
				</div>
				<div class="control-group inline pull-right">
					<button type="button" id="btn-query" class="btn btn-primary">查询</button>
				</div>
			</div>
	        <table id="log_table" class="table table-hover table-bordered table-condensed table-striped" style="clear:both;">
				<thead>
					<tr>
						<th>操作内容</th>
						<th>登录名</th>
						<th>操作时间</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</body>
</html>