<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${childMenu}</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/third-party/js/content/contentList.js"></script>
		
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
				<!-- 左边导航菜单 begin -->
				<jsp:include page="/WEB-INF/jsp/navLeft.jsp"></jsp:include>
				<!-- 左边导航菜单 end -->
			</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="title">内容标题:</label> 
						<input id="title" name="title" type="text" class="input-text-medium" placeholder="内容标题">
					</div>
					<div class="control-group inline">
						<label class="inline" for="contentType">内容类型:</label> 
						<input type="hidden" name="type" id="type">
						<select id="contentType" name="contentType" class="input-medium" placeholder="内容类型" onchange="document.getElementById('type').value=this.value">
							<option value="">--选择类型--</option>
							<option value="0">通用</option>
							<option value="1">独立</option>
						</select>
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
				<div class="row-fluid toolbar">
	        		<button type="button" class="btn btn-primary" onclick="openAddOrModifyContent('')">新增</button>   		
	        	</div>
			</form>
			<!-- <div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="openAddOrModifyMaterial('')">新增</button>
        	</div> -->
	        <table id="_user_table" class="table table-hover table-bordered table-condensed table-striped">
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>