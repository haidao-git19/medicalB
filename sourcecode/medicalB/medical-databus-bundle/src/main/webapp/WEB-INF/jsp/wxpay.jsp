<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>微信支付</title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
	</head>
<body>
</body>
<script>
$(function() {
	if (typeof WeixinJSBridge == "undefined"){
		if( document.addEventListener ){
		     document.addEventListener('WeixinJSBridgeReady', callpay, false);
		 }else if (document.attachEvent){
		     document.attachEvent('WeixinJSBridgeReady', callpay); 
		     document.attachEvent('onWeixinJSBridgeReady', callpay);
		 }
	}else{
		callpay();
	}
});

function callpay(){
	var goodsCode = '${param.goodsCode}';
	var goodsName = '${param.goodsName}';
	var goodsVersion = '${param.goodsVersion}';
	var shopPrice = '${param.shopPrice}';
	var productNum = '${param.productNum}';
	var orderCode = '${param.orderCode}';
	var dataSign = '${param.dataSign}';
	var userId = '${param.userId}';
	
	var param = "goodsName="+goodsName+"&goodsCode="+goodsCode+"&goodsVersion="+goodsVersion+"&shopPrice="+shopPrice+"&productNum="+productNum+"&orderCode="+orderCode+"&dataSign="+dataSign+"&userId="+userId;
	$.ajax({
	type : "POST",
	url : "${ctx}/anon/queryWXPayPrepayid",
	data : param, //参数自己根据业务定义
	dataType : "json",
	success : function callback(data, textStatus) {
	if(data.code == '0'){
		var finalpackage = data.finalpackage;
		var appId = finalpackage.appId;
		var timestamp = finalpackage.timeStamp;
		var nonceStr = finalpackage.nonceStr;
		var packages = finalpackage.package;
		var finalsign = finalpackage.finalsign;
		var orderCode = data.orderCode;
		var goodsName = data.goodsName;
		var goodsCode = data.goodsCode;
		var productNum = data.productNum;
		
			WeixinJSBridge.invoke('getBrandWCPayRequest',{
				"appId" : appId,"timeStamp" : timestamp, "nonceStr" :nonceStr, "package" : packages,"signType" : "MD5", "paySign" : finalsign
			}, function(res) {
				WeixinJSBridge.log(res.err_msg);
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					window.location.href = manage +'/anon/toPaySuccess?goodsName='+goodsName+'&orderCode='+orderCode;
				} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
					window.location.href = manage +'/anon/toPayFail';
				} else {
					window.location.href = manage +'/anon/toPayFail';
				}
		});
	  }
	//自动关闭微信浏览器
	//WeixinJSBridge.call('closeWindow');
  }
});	
}
</script>
</html>