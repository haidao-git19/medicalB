<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/third-party/js/user/userAcceptInfoList.js"></script>
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
						<label class="inline" for="userId">用户编号:</label> 
						<input id="userId" name="userId" type="text" class="input-text-medium" placeholder="用户编号">
					</div>
					<div class="control-group inline">
						<label class="inline" for="acceptName">收货人:</label> 
						<input id="acceptName" name="acceptName" type="text" class="input-text-medium" placeholder="收货人">
					</div>
					<div class="control-group inline">
						<label class="inline" for="phone">手机号:</label> 
						<input id="phone" name="phone" type="text" class="input-text-medium" placeholder="手机号">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
	       </form>				
		</div>
	    <table id="_userAcceptInfo_table" class="table table-hover table-bordered table-condensed table-striped">
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>