<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/hospital/hospital.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/hospital/editHospital.js"></script>
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
				<li class="active">医院管理 <span class="divider">/</span></li>
				<li class="active">医院管理</li>
			</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="hospitalName">医院名称:</label> 
						<input id="hospitalName" name="hospitalName" type="text" class="input-text-medium" placeholder="医院管理">
					</div>
					<div class="control-group inline">
						<label class="inline" for="hospitalLevel">医院等级:</label> 
						<select type="text" id="hospitalLevel" name="hospitalLevel">
							<option value="" selected="selected">--选择等级--</option>
								<option value="二级甲等" >二级甲等</option>
								<option value="二级" >二级</option>
								<option value="三级甲等" >三级甲等</option>
								<option value="三级" >三级</option>
								<option value="社区医院" >社区医院</option>								
						</select>
					</div>
					<div class="control-group inline">
						<label class="inline" for="hospitalLevel">本地网:</label> 
						<select type="text" id="areaID" name="areaID"></select>
					</div>
					<div class="control-group inline">
						<label class="inline" for="hospitalLevel">医院类型:</label> 
						<select type="text" id="type" name="type">
							<option value="" selected="selected">--选择类型--</option>
							<option value="1">专家医院</option>
							<option value="0">社区医院</option>
						</select>
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			<shiro:hasPermission name="医院管理:医院管理:新增">
			<div class="row-fluid toolbar">
        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx }/hospital/initAddOrUpdate'">新增</button>
        	</div>
        	</shiro:hasPermission>
	        <table id="hospital_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>医院名称</th>
						<th>联系人</th>
						<th>联系电话</th>
						<th>地址</th>
						<th>本地网</th>
						<th>医院等级</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>

</body>
</html>