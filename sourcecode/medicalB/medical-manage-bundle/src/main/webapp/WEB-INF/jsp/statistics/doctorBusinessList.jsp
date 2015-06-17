<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/js/statistics/doctorBusinessList.js"></script>
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
				<input type="hidden" id="doctorId" name="doctorId" value="${doctorId }">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="time">时间段:</label> 
						<input id="startTime" name="startTime" class="Wdate"  style="width:30%;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />-
						<input id="endTime" name="endTime" class="Wdate"  style="width:30%;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
					</div>
					<div class="control-group inline">
						<label class="inline" for="orderNumber">订单号:</label> 
						<input id="orderNumber" name="orderNumber" type="text" class="input-text-medium" placeholder="订单号">
					</div>
					<c:if test="${not empty doctorId}">
						<div class="control-group inline">
							<label class="inline" for="payStatus">支付状态:</label> 
							<select id="payStatus" name="payStatus">
								<option value="">--支付状态--</option>
								<option value="0">未支付</option>
								<option value="1">已支付</option>
							</select>
						</div>
					</c:if>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
						<c:if test="${not empty doctorId}">
							<button type="button" onclick="history.go(-1);" class="btn btn-primary">返回</button>
						</c:if>
					</div>
				</div>
	       </form>				
		</div>
	    <table id="_business_table" class="table table-hover table-bordered table-condensed table-striped">
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>