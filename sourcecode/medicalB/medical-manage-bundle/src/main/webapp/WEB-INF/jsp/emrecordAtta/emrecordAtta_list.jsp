<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>电子病历</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/emrecord/emrecordAtta.js"></script>
		
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
				<li class="active">电子病历<span class="divider">/</span></li>
				<li class="active">附件</li>
			</ul>
			
			<form id="queryForm" action="javascript:table.fnDraw();">
				<input id="recordID" name="recordID" type="hidden" value="${param.recordID}" class="input-text-medium" >
			</form>
			
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/emrecord_atta/initAddOrUpdate?recordID=${param.recordID}'">新增</button>
        		<input type="button" value="返回" onclick="window.history.go(-1);" class="btn btn-primary" />
        	</div>
	        <table id="emrecordAtta_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>编号</th>
						<th>附件名称	</th>
						<th>附件路径</th>
						<th>所属病历编号</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</body>

</html>