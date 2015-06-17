<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>常规管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		
		<script type="text/javascript" src="${ctx }/resources/js/newborn/newBorn.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/newborn/editNewBorn.js"></script>
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
				<li class="active">新生儿登记</li>
			</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="mamu">母亲姓名:</label> 
						<input id="mamu" name="mamu" type="text" class="input-text-medium" placeholder="母亲姓名">
					</div>
					<div class="control-group inline">
						<label class="inline" for="father">父亲姓名:</label> 
						<input id="father" name="father" type="text" class="input-text-medium" placeholder="父亲姓名">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/newBorn/initAddOrUpdate'">新增</button>
        	</div>
	        <table id="patient_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>新生儿命名</th>
						<th>母亲</th>
						<th>母亲证件号</th>
						<th>出生地类型</th>
						<th>接受机构名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>

</body>
</html>