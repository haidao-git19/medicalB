<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>类目属性管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/category/categoryAttrList.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/category/addAttrClass.js"></script>
		<style type="text/css">
		#user_table {
			min-width : 800px;
		}
		</style>
	</head>
	<script type="text/javascript">
		var categoryCode='${categoryCode}';
	</script>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			
			<ul class="breadcrumb">
				<li class="active">类目管理 <span class="divider">/</span></li>
				<li class="active">类目属性管理</li>
			</ul>
			
			<form id="queryForm" action="javascript:table.fnDraw();">
				<input type="hidden" id="categoryCode" name="categoryCode" value="${categoryCode }">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="attrName">属性名称:</label> 
						<input id="attrName" name="attrName" type="text" class="input-text-medium" placeholder="属性名称">
					</div>
					<div class="control-group inline">
						<label class="inline" for="attrClassId">分类标识:</label> 
						<select id="attrClassId" name="attrClassId" class="input-medium" placeholder="分类标识">
							<option value="">---选择分类标识---</option>
							<c:forEach items="${attrClassList}" var="attrClass">
								<option value="${attrClass.id }">${attrClass.attrClassName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="class_add_btn" class="btn btn-primary">添加分类标识</button>
					</div>
				</div>
			</form>
			
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/categoryAttr/edit?categoryCode=${categoryCode}'">新增</button>
        		<button type="button" onclick="javascript:history.go(-1);" class="btn btn-primary">返回</button>
        	</div>
	        <table id="category_attr_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>序号</th>
						<th>类目编号</th>
						<th>分类标识</th>
						<th>名称</th>
						<th>编号</th>
						<th>排序</th>
						<th>录入方式</th>
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
	<jsp:include page="/WEB-INF/jsp/category/addAttrClass.jsp"></jsp:include>
</body>

</html>