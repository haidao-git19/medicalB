<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>选择收货地区</title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
		<link href="${ctx }/resources/third-party/css/global.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/wnclient.css?ver=${version}" rel="stylesheet" type="text/css">
        <script type="text/javascript">
        	var _id = '${param.id}';
        	var _acceptName = '${param.acceptName}';
        	var _phone = '${param.phone}';
        	var _area = '${param.area}';
        	var _address = '${param.address}';
        	var _zipcode = '${param.zipcode}';
        	var _isEidt = '${param.isEidt}';
        </script>
        <script src="${ctx }/resources/third-party/js/address/chooseArea.js?ver=${version}" type="text/javascript"></script>
	</head>
<body class="appbg pb60">
	<div class="tree">
		<div class="tree_box">
			<h3>我是一级菜单</h3>
			<ul class="tree_one" style="display: block;">
				<li>
					<h4>我是二级菜单</h4>
					<ul class="tree_two">
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="tree_box">
			<h3>我是一级菜单</h3>
			<ul class="tree_one">
				<li>
					<h4>我是二级菜单</h4>
					<ul class="tree_two">
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
					</ul>
				</li>
				<li>
					<h4>我是二级菜单</h4>
					<ul class="tree_two">
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
					</ul>
				</li>
				<li>
					<h4>我是二级菜单</h4>
					<ul class="tree_two">
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
						<li>我是三级菜单</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
