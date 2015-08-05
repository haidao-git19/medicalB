<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>病人管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/account/account.js"></script>
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
				<li class="active">资金管理 <span class="divider">/</span></li>
				<li class="active">提现处理</li>
			</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="patientCard">证件号:</label> 
						<input id="patientCard" name="patientCard" type="text" class="input-text-medium" placeholder="证件号">
					</div>
					<div class="control-group inline">
						<label class="inline" for="name">时间段:</label> 
						<input id="startTime" name="startTime" class="Wdate"  style="width:30%;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />-
						<input id="endTime" name="endTime" class="Wdate"  style="width:30%;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="#">导出列表</button>
        	</div>
	        <table id="patient_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>序列</th>
						<th>姓名</th>
						<th>证件号</th>
						<th>联系电话</th>
						<th>申请日期</th>
						<th>提现金额</th>
						<th>提现账号</th>
						<th>账号姓名</th>
						<th>支行</th>
						<th>处理状态</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>

</body>
</html>