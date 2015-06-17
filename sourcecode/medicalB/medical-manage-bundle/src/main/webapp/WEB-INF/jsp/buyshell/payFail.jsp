<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
		<link href="${ctx }/resources/third-party/css/global.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/wnclient.css?ver=${version}" rel="stylesheet" type="text/css">

		<script type="text/javascript">
		$(function() {
			$("#backBut").on('click',function(){
				window.location.href = ctx + '/anon/buyList';
			})
		});
        </script>
	</head>

<body class="appbg">
	<div class="mt20 tc">
		<img src="${ctx }/resources/third-party/images/facefail.png"  width="40%">
		<img src="${ctx }/resources/third-party/images/fail.png" class="face" width="80%"></div>
	<div class="f18 tc orange mt20">
		<input name="" type="button" value="返回首页" class="btn-org" id="backBut">
	</div>
</body>
</html>
