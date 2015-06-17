<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>菜单管理</title>
		<jsp:include page="/WEB-INF/views/auth/common.jsp"></jsp:include>
		
		<link href="${ctx }/resources/auth/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
		<script type="text/javascript" src="${ctx }/resources/auth/zTree/js/jquery.ztree.all-3.5.min.js"></script>
		<script type="text/javascript" src="${ctx }/resources/auth/js/menu/menu.js"></script>
		
		<style type="text/css">
			#menu_tree {
				min-height : 320px;
				max-height : 480px;
			}
			#menu_tree_block .block-body{
				margin : 5px;
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
			
			#rMenu {
				position: absolute;
				visibility: hidden;
				top: 0;
				background-color: rgb(244,244,244);
				text-align: left;
				padding: 2px;
				border : 1px solid #a6a6a6;
			}
			
			#rMenu ul {
				margin : 0;
			}
			
			#rMenu ul li {
				margin: 1px 0;
				padding: 2px 8px;
				cursor: pointer;
				list-style: none outside none;
				font-size: 12px;
				font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
				color : #0088cc;
			}
			#rMenu ul li:hover {
				color : #005580;
			}
			
			#icon_upload_wrap {
				width : 80px;
				height : 80px;
				position : relative;
				border : 1px solid #ccc;
				background-image: url(${ctx}/resources/auth/img/upload.png);
				background-repeat:no-repeat;
				background-position: center center;
			}
			#icon_upload_wrap img {
				width : 100%;
				height : 100%;
				display : block;
				background-color: #FFF;
			}
			#icon_upload_wrap .clear {
				width : 100%;
				height : 50%;
				position : absolute;
				bottom : 0;
				background-color: rgba(0,0,0,0.6);
				filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);
				-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
				z-index : 10;
				display : none;
			}
			#icon_upload_wrap .clear button{
				margin : 10px auto;
				width : 50px;
				display : block;
			}
			.uploadify-queue {
				position: fixed;
				margin:auto;
				left:0;top:0;right:0;bottom:0;
				width:300px;height:120px;
			}
		</style>
	</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">权限管理 <span class="divider">/</span></li>
				<li class="active">菜单管理</li>
			</ul>
			
			<div class="block span5" id="menu_tree_block">
				<p class="block-heading">菜单树</p>
				<div class="block-body">
					<div class="ztree" id="menu_tree"></div>
				</div>
			</div>
			<div class="block span5" id="menu_info_block">
				<p class="block-heading">编辑菜单项</p>
				<div id="menu_info" class="block-body">
					<form id="menu_form" action="javascript:submit();">
					<div class="form-horizontal" style="overflow-x:hidden;">
						<input type="hidden" id="id" name="id">
						<input type="hidden" id="parentId" name="parent.id">
						<input type="hidden" id="menuOrder" name="menuOrder">
						<input type="hidden" id="menuType" name="menuType">
						<div class="control-group">
							<label class="control-label" for="name">菜单名称:<span class="help-inline">*</span></label> 
							<div class="controls">
								<input type="text" id="name" name="name" style="width:50%;">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="link">菜单链接:<span class="help-inline">&nbsp;</span></label> 
							<div class="controls">
								<input type="text" id="link" name="link" style="width:95%;">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="description">菜单描述:<span class="help-inline">&nbsp;</span></label> 
							<div class="controls">
								<textarea id="description" name="description" style="width:95%;"></textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">菜单图标:<span class="help-inline">&nbsp;</span></label> 
							<div class="controls">
								<div id="icon_upload_wrap">
									<div id="icon_upload"></div>
									<div class="clear">
										<button class="btn btn-danger btn-mini">删除</button>
									</div>
								</div>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<label class="inline checkbox">
									<input type="checkbox" id="visible" name="visible">
									是否可见
								</label>
							</div>
						</div>
					</div>
					<div class="form-btns">
						<button type="button" id="btn_save" class="btn btn-primary">保存</button>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>


<div id="rMenu">
	<ul>
		<li id="m_refresh" onclick="menu_refreshTreeNode();"><i class="icon-refresh"></i> 刷新</li>
		<li id="m_add_folder" onclick="menu_addTreeNode(1);"><i class="icon-folder-open"></i> 新增目录</li>
		<li id="m_add_page" onclick="menu_addTreeNode(2);"><i class="icon-file"></i> 新增页面</li>
		<li id="m_add_action" onclick="menu_addTreeNode(3);"><i class="icon-tasks"></i> 新增操作</li>
		<li id="m_del" onclick="menu_removeTreeNode();"><i class="icon-minus-sign"></i> 删除</li>
	</ul>
</div>
</body>
</html>