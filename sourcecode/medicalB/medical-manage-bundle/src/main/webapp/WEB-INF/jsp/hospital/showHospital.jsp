<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>医院管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
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
				<li class="active">新增医院  </li>
			</ul>
			<form action="${ctx}/hospital/addOrUpdate">
			<input type="button" value="返回" onclick="window.history.go(-1);" class="btn btn-primary"/>
				<div style="margin:0 auto; width:400px; height:100px;margin-top: 50px;">
				<input type="hidden" name="hospitalID" value="${hospl.hospitalID }"/>
				医院名称：${hospl.hospitalName }<br>
				联系人：${hospl.linkMan }<br>
				联系方式：${hospl.linkPhone }<br>
				医院等级：${hospl.hospitalLevel }<br>
				本地网：${hospl.areaID }<br>
				地址：${hospl.address }<br>
				未知1：${hospl.lng }<br>
				未知2：${hospl.lat }<br>
				未知3：${hospl.ctNum }<br>
				未知4：${hospl.cgNum }<br>
				未知5：${hospl.skill }<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
		</form>	
		</div>
	</div>

</body>
</html>