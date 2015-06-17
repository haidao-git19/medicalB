<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>专家管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/doctor/doctorFee.js"></script>
		
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
				<li class="active">专家管理<span class="divider">/</span></li>
				<li class="active">资费设置</li>
			</ul>
			
			<form id="queryForm" action="javascript:table.fnDraw();">
				<input id="doctorID" name="doctorID" type="hidden" value="${param.doctorID}" class="input-text-medium" >
				
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="type">资费类型:</label> 
						<select id="type" name="type">
							<option value="0">请选择</option>
							<option value="1">普通咨询</option>
							<option value="2">网络咨询</option>
							<option value="3">语音咨询</option>
							<option value="4">视频咨询</option>
						</select>
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/doctorFee/initAddOrUpdate?doctorID=${param.doctorID}'">新增</button>
        		<input type="button" value="返回" onclick="window.history.go(-1);" class="btn btn-primary" />
        	</div>
	        <table id="doctorFee_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>编号</th>
						<th>资费类型</th>
						<th>资费等级</th>
						<th>资费描述</th>
						<th>资费(单位：元)</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</body>

</html>