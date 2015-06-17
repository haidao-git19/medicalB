<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>订单确认</title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
		<link href="${ctx }/resources/third-party/css/global.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/wnclient.css?ver=${version}" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			var goodsCode = '${param.goodsCode}';
	    	var goodsVersion = '${param.goodsVersion}';
	    	var acceptId = '${param.acceptId}';
	    	var orderCode = '${param.orderCode}';
	    	var productNum = '${param.productNum}';
	    	var type = '${param.type}';
		</script>
        <script src="${ctx }/resources/third-party/js/buyshell/order.js?ver=${version}" type="text/javascript"></script>
	</head>
	
<body class="appbg pb60">
	<div class="wtbg ct-pro hg88 clearfix" id="_moreAddress">
	    <div class="fr mt25 pr10" >
	    	<img src="${ctx }/resources/third-party/images/icon-arrowr.png" width="56%">
	    </div>
	    <div class="w80 fl lh200" id="acceptInfo_div" style="display:none;">
		    <p class="pl10 pr10 clearfix  f16 pt15">
		    	<span class="fr"> {0}</span>
		    	<span>收货人：{1}</span>
		    	<div class="cb"></div>
		    </p>
		    <p class="pl10 pb10  f14">收货地址：{2}</p>
	    </div>
	    <div id="noAddress" class="w80  lh200 p10 gray f20" style="display:;">请填写收货人信息</div>
	</div>

	<div class="brd-box mt10 p10" style="padding-bottom: 5px;">
		<div class="pb10" id="goods_detail_div">
			<p class="orange f12 fr" style="display: block">￥{3}</p>
			<div class="f14 fl" style="position: relative; width: 80%;">
				<p class="fl showpic picli">{0}</p>
				<p class="f14 lh120" style="padding-left: 108px;">
					<span>{1}</span><br /> <span class="gray f12"
						style="display: inline-block; padding-top: 10px;">{2}</span>
				<div class="cb"></div>
			</div>
			<div class="cb"></div>
		</div>
		<div class="f18 pt10 clearfix" style="height: 44px; border-top: 1px solid #e4e4e4;">
				<p class="fr">
					<a href="#" class="fl btn-num tc box-sizing" id="reduceNum">
						<img src="${ctx }/resources/third-party/images/minus.png" width="16">
					</a>
					<input name="" type="text" class="fl btn-num tc box-sizing" style="background: #fff; border: 1px solid #ccc; padding-top: 0;" value="1" id="productNum">
					<a href="#" class="fl btn-num tc box-sizing" id="addNum">
						<img src="${ctx }/resources/third-party/images/add.png" width="16">
					</a>
				</p>
				<p class="pt10 f16">购买数量</p>
			</div>
	</div>
	
	<div id="payDiv">
		<p class="f16 p10">支付方式</p>
	    <div class="brd-box">
	    	<p class="brdbtm p10 f14" id="alipay" style="display: none;"><input name="payMethod" id="alipayRadio" type="radio" value="0" class="btn-srh"> <span><img src="${ctx }/resources/third-party/images/zfb.png" width="96"></span>支付宝支付</p>
	        <p class=" p10 f14" id="wxpay"><input name="payMethod" id="wxpayRadio" type="radio" class="btn-srhon" value="1" checked> <span><img src="${ctx }/resources/third-party/images/wx.png" width="96"></span>微信支付</p>
	    </div>
	</div>
	<div class="btmbar box-sizing">
	    <p class="tr"><span class="fr"><input name="" type="button" value="付款" class="btn-org" id="submitOrderBut"></span><span class="orange f14 pr15 fr pt8">合计：<i class="f16" id="totalFee">￥200</i></span></p>    
	</div>
</body>
</html>
