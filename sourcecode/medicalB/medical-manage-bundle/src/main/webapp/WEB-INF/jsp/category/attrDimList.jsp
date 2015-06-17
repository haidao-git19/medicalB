<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>属性维度管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/category/attrDimList.js"></script>
		
		<style type="text/css">
		#user_table {
			min-width : 800px;
		}
		</style>
	</head>
	<script type="text/javascript">
		var categoryCode='${categoryCode}';
		var attrCode='${attrCode}';
	</script>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			
			<ul class="breadcrumb">
				<li class="active">属性管理 <span class="divider">/</span></li>
				<li class="active">属性维度管理</li>
			</ul>
			
			<form id="queryForm" action="javascript:table.fnDraw();">
				<input type="hidden" id="attrCode" name="attrCode" value="${attrCode }">
				<input type="hidden" id="categoryCode" name="categoryCode" value="${categoryCode }">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="attrShowValue">维度值:</label> 
						<input id="attrShowValue" name="attrShowValue" type="text" class="input-text-medium" placeholder="维度值">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="javascript:window.location.href='${ctx}/categoryAttr/editAttrDim?categoryCode=${categoryCode}&attrCode=${attrCode}';">新增</button>
				<button type="button" onclick="javascript:history.go(-1);" class="btn btn-primary">返回</button>
        	</div>
	        <table id="attr_dim_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>序号</th>
						<th>类目编号</th>
						<th>属性编号</th>
						<th>维度值</th>
						<th>属性值</th>
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