<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/resources/third-party/js/order/sellRecordList.js"></script>
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
						<label class="inline" for="acceptName">收货人:</label> 
						<input id="acceptName" name="acceptName" type="text" class="input-text-medium" placeholder="收货人">
					</div>
					<div class="control-group inline">
						<label class="inline" for="phone">手机:</label> 
						<input id="phone" name="phone" type="text" class="input-text-medium" placeholder="手机">
					</div>
					<div class="control-group inline">
						<label class="inline" for="telephone">电话:</label> 
						<input id="telephone" name="telephone" type="text" class="input-text-medium" placeholder="电话">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
	       </form>				
		</div>
	    <table id="_order_table" class="table table-hover table-bordered table-condensed table-striped">
			<tbody>
			
			</tbody>
		</table>
	</div>
</body>
</html>