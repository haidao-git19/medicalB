<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>咨询管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		
		<script type="text/javascript" src="${ctx }/resources/js/consult/consult.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/consult/editConsult.js"></script>
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
				<li class="active">咨询管理 <span class="divider">/</span></li>
				<li class="active">普通问诊咨询</li>
			</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="isFree">是否免费:</label> 
						<input id="isFree" name="isFree" type="text" class="input-text-medium" placeholder="是否免费">
					</div>
					<div class="control-group inline">
						<label class="inline" for="level">问题级别:</label> 
						<input id="level" name="level" type="text" class="input-text-medium" placeholder="问题级别">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/consult/initAddOrUpdate'">新增</button>
        	</div>
	        <table id="consult_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>咨询时间</th>
						<th>咨询</th>
						<th>解答</th>
						<th>是否免费</th>
						<th>患者评价</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>

</body>
</html>