<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色管理</title>
		<jsp:include page="/WEB-INF/views/auth/common.jsp"></jsp:include>
		
		<link href="${ctx }/resources/auth/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
		
		<script type="text/javascript" src="${ctx }/resources/auth/zTree/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="${ctx }/resources/auth/js/role/role.js"></script>
		
		<style type="text/css">
			#menu_tree {
				min-height : 150px;
				padding : 10px;
			}
			#role_table {
				min-width : 600px;
			}
			.ztree li a:hover {
				text-decoration: none;
			}
			
			.ztree li span.button.folder_ico_close {
			  margin-right: 2px;
			  vertical-align: top;
			  background-position: -384px -120px;
			  background-image: url("${ctx}/resources/auth/jquery-ui-bs/assets/img/glyphicons-halflings.png");
			}
			
			.ztree li span.button.folder_ico_open {
			  margin-right: 2px;
			  vertical-align: top;
			  background-position: -408px -120px;
			  background-image: url("${ctx}/resources/auth/jquery-ui-bs/assets/img/glyphicons-halflings.png");
			}
			
			.ztree li span.button.page_ico_open {
				margin-right: 2px;
				vertical-align: top;
				background-position: -24px -24px;
				background-image: url("${ctx}/resources/auth/jquery-ui-bs/assets/img/glyphicons-halflings.png");
			}
			.ztree li span.button.page_ico_close {
				margin-right: 2px;
				vertical-align: top;
				background-position: -24px -24px;
				background-image: url("${ctx}/resources/auth/jquery-ui-bs/assets/img/glyphicons-halflings.png");
			}
			
			.ztree li span.button.action_ico_docu {
				margin-right: 2px;
				vertical-align: top;
				background-position: -384px -144px;
				background-image: url("${ctx}/resources/auth/jquery-ui-bs/assets/img/glyphicons-halflings.png");
			}
		</style>
	</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">权限管理 <span class="divider">/</span></li>
				<li class="active">角色管理</li>
			</ul>
			<div class="page-wrapper" style="max-width: 1024px;">
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="name">角色名:</label> <input id="name"
							name="name" type="text" class="input-text-medium"
							placeholder="角色名">
					</div>
					<div class="control-group inline">
						<label class="inline"></label>
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
			<div class="row-fluid toolbar">
				<button type="button" class="btn btn-primary"
					onclick="window.location.href='${ctx }/role/edit'">新增</button>
			</div>
			
			<table id="role_table" class="table table-hover table-bordered table-condensed table-striped">
				<thead>
					<tr>
						<th>角色名</th>
						<th>更新人</th>
						<th>更新时间</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>查看</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody></tbody>			
			</table>
			</div>

		</div>
	</div>
</body>
</html>