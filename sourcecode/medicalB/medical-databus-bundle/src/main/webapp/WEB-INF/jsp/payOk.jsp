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
				window.location.href = manage + '/anon/buyList';
			})
		});
        </script>
	</head>

<body class="appbg">
	<div class="mt20">
		<img src="${ctx }/resources/third-party/images/face_03.png" class="face" width="40%"> 
		<img src="${ctx }/resources/third-party/images/ok_03.png" class="face" width="88%">
	</div>
	<div class="f14 tc pl20 mb10">订单号：${param.out_trade_no}</div>
	<div class="ct-ok f12 p10">
		亲，您好已成功购买${param.goodsName}手机壳，我们将在4个工作日内联系您，感谢你的支持。<span>购买成功后,获得终身免费特权</span>，一年拥有两次免费换新机会。
	</div>
	<div class="mt20 tc">
		<input name="" type="button" id="backBut" value="再逛逛" class="btn-org">
	</div>
</body>
</html>
