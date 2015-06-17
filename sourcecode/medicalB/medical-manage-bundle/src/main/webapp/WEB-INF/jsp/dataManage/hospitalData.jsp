<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		
		<link href="${ctx }/resources/third-party/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
		<script type="text/javascript" src="${ctx }/resources/third-party/zTree/js/jquery.ztree.all-3.5.min.js"></script>
		
		<script type="text/javascript">
			var readOnly = '${param.readOnly}' === 'true';
			var role = null;
			var rolemenuKeys = [];
		</script>
		<script type="text/javascript" src="${ctx }/resources/js/dataManage/hospitalDataManage.js"></script>
		
		<style type="text/css">
			#menu_tree_block {
				margin-top : 0px;
				width : 40%;
				min-width : 216px;
			}
			#menu_tree_block .block-body {
				margin : 5px;
			}
			#menu_tree {
				min-height : 320px;
				max-height : 480px;
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
				<li class="active">数据管理 <span class="divider">/</span></li>
				<li class="active">科室配置</li>
			</ul>
			<form id="role_form">
				<input type="hidden" id="id" name="id" value="${param.id }">
				<input type="hidden" id="createTime" name="_createTime">
				<div class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="name">用户名:<span class="help-inline">*</span></label> 
						<div class="controls">
							<input type="text" id="name" name="name">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">数据菜单:<span class="help-inline">&nbsp;</span></label> 
						<div class="controls">
							<div class="block inline" id="menu_tree_block">
								<p class="block-heading">科室菜单树</p>
								<div class="block-body">
									<div class="ztree" id="menu_tree"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"></label> 
						<div class="controls">
							<button type="button" class="btn btn-primary" id="btn_save">保存</button>
							<button type="button" class="btn btn-primary" onclick="history.go(-1);">返回</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>