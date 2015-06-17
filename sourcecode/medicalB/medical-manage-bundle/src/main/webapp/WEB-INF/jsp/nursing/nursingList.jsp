<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>常规管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		
		<script type="text/javascript" src="${ctx }/resources/js/nursing/editNursing.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/nursing/nursing.js"></script>
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
				<li class="active">常规管理 <span class="divider">/</span></li>
				<li class="active">康复护理</li>
			</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="objectName" for="objectName">发布人:</label> 
						<input id="objectName" name="objectName" type="text" class="input-text-medium" placeholder="发布人">
					</div>
					<div class="control-group inline">
						<label class="inline" for="sectionName">科室名称:</label> 
						<input id="sectionName" name="sectionName" type="text" class="input-text-medium" placeholder="科室名称">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/nursing/initAddOrUpdate'">新增</button>
        	</div>
	        <table id="data_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>发布人</th>
						<th>科室</th>
						<th>主题</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>

</body>
</html>