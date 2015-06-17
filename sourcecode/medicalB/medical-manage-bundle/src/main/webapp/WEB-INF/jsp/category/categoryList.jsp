<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>类目管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/category/categoryList.js"></script>
		
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
				<li class="active">类目管理 <span class="divider">/</span></li>
				<li class="active">类目管理</li>
			</ul>
			
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="name">类目名称:</label> 
						<input id="name" name="name" type="text" class="input-text-medium" placeholder="类目名称">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/category/initAddOrUpdate'">新增</button>
        	</div>
	        <table id="category_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>序号</th>
						<th>编号</th>
						<th>名称</th>
						<th>别名</th>
						<th>产品类型</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>更新人</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</body>

</html>