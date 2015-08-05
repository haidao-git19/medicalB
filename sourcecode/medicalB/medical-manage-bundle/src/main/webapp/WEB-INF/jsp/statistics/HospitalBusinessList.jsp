<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/js/statistics/hospitalBusinessList.js"></script>
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
						<label class="inline" for="time">时间段:</label> 
						<input id="startTime" name="startTime" class="Wdate"  style="width:30%;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />-
						<input id="endTime" name="endTime" class="Wdate"  style="width:30%;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					</div>
					<div class="control-group inline">
						<label class="inline" for="hospitalID">医院:</label> 
						<select id="hospitalID" name="hospitalID">
							<option value="">--选择医院--</option>
							<c:forEach items="${hospitalList}" var="hospital">
								<option value="${hospital.hospitalID}">${hospital.hospitalName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
	       </form>				
		</div>
	    <table id="_hospital_business_table" class="table table-hover table-bordered table-condensed table-striped">
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>