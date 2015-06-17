var shopPrice = 0;
var goodsStock = 0;
var dataSign = null;
var goodsName =null;
var ischooseAddress = false;
$(function() {
	//清理页面cookie；
	clear();
	
	//按钮操作初始化；
	if(type == '1'){//使用特权更换手机壳
		$("#payDiv").hide();
		$("#submitOrderBut").val("更换");
		$("#addNum").attr('disabled',"true");
		$("#productNum").attr('disabled',"true");
		$("#reduceNum").attr('disabled',"true");
	}else if(type == '4'){//使用特权更换手机壳
		$("#payDiv").hide();
		$("#submitOrderBut").val("兑换");
		$("#addNum").attr('disabled',"true");
		$("#productNum").attr('disabled',"true");
		$("#reduceNum").attr('disabled',"true");
	}else{
		init();
	}
	
	//商品详情查询初始化；
	if(type == 'diy'||type == '3'){//使用特权更换手机壳
		queryDiyGoodsDetail();
	}else{
		queryGoodsDetail();
	}
	
	//地址初始化查询；
	if (acceptId != null && acceptId != '') {
		queryAcceptInfoById(acceptId);
	} else {
		queryAcceptInfoList();
	}
	
	//按钮事件注册；
	$("#submitOrderBut").click(function() {
		if(type != 'diy' && type != '3'){//使用特权更换手机壳
			var productNum = $("#productNum").val();
			if(productNum>goodsStock){
				alert("库存不足");
				return;
			}
		}		
		submitOrder();
	});
	$("#_moreAddress").click(function() {
		toMoreAddress();
	});
});

/*function initWeixinJSBridge(){
	if (typeof WeixinJSBridge == "undefined"){
		if( document.addEventListener ){
		     document.addEventListener('WeixinJSBridgeReady', editAddress, false);
		 }else if (document.attachEvent){
		     document.attachEvent('WeixinJSBridgeReady', editAddress); 
		     document.attachEvent('onWeixinJSBridgeReady', editAddress);
		 }
	}
}
function editAddress(){
	WeixinJSBridge.invoke('editAddress',{
		"appId" : appId,
		"scope" : "jsapi_address",
		"signType" : "sha1",
		"addrSign" : addrSign,
		"timeStamp" : timeStamp,
		"nonceStr" : nonceStr
		},function(res){
			alert(res.err_msg);
		});
}
*/
function init() {
	if(productNum != null && productNum != ''){
		$("#productNum").val(productNum);
		$("#addNum").attr('disabled',"true");
		$("#reduceNum").attr('disabled',"true");
		$("#productNum").attr('disabled',"true");
	}else{
		$("#addNum").click(function() {
			addNum();
		});
		$("#reduceNum").click(function() {
			reduceNum();
		});
	}
	$("#alipay").click(function() {
		$("#alipayRadio").attr("checked", "checked");
		$("#wxpayRadio").removeAttr("checked");
	});
	$("#wxpay").click(function() {
		$("#wxpayRadio").attr("checked", "checked");
		$("#alipayRadio").removeAttr("checked");
	});
}

function addNum(){
	var productNum = $("#productNum").val();
	$("#productNum").val(parseInt(productNum)+1);
	calcTotalFee();
}

function reduceNum(){
	var productNum = $("#productNum").val();
	if(parseInt(productNum) <= 1)
		return;
	$("#productNum").val(parseInt(productNum)-1);
	calcTotalFee();
}

function calcTotalFee(){
	var productNum = $("#productNum").val();
	if(parseInt(productNum) <= 0)
		return;
	var totalFee = parseInt(productNum) * shopPrice;
	$("#totalFee").text("￥"+totalFee);
	return totalFee;
}

function queryDiyGoodsDetail() {
	var goods_detail_div = $("#goods_detail_div").html();
	$("#goods_detail_div").empty();
	
	$.ajax({
		url : ctx + "/anon/diyDetail",
		data : [ {
			name : 'goodsCode',
			value : goodsCode
		}],
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data.code == '1'){
				return ;
			}
			var d = data.diyDetail;
			shopPrice = getNumber(d.shopPrice);
			goodsName = getString(d.goodsName);
			dataSign = getString(d.dataSign);
			var coverImage = '<img src="'+ getString(d.coverImage)+"?ver="+goodsVersion +'"  width=\"88\" height=\"88\">';
			$("#goods_detail_div").append(goods_detail_div.format(coverImage,getString(goodsName),'',shopPrice));
			calcTotalFee();
			$("#goods_detail_div").show();
		}
	});
}

function queryGoodsDetail() {
	var goods_detail_div = $("#goods_detail_div").html();
	$("#goods_detail_div").empty();
	
	$.ajax({
		url : ctx + "/anon/buyDetail",
		data : [ {
			name : 'goodsCode',
			value : goodsCode
		}, {
			name : 'goodsVersion',
			value : goodsVersion
		} ],
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			var d = data.goodsDetail;
			var attrs = "";
			if(d.attrList != undefined && d.attrList != null){
				$.each(d.attrList,function(i, a) {
					attrs = attrs + a.attrKey + ":" + a.attrValue +"</br>";
				});
			}
			shopPrice = getNumber(d.shopPrice);
			goodsStock = getNumber(d.goodsStock);
			dataSign = getString(d.dataSign);
			goodsName = getString(d.goodsName);
			var coverImage = '<img src="'+ getString(d.imageUrl)+"?ver="+goodsVersion +'">';
			$("#goods_detail_div").append(goods_detail_div.format(coverImage,getString(d.goodsName),getString(attrs),shopPrice));
			calcTotalFee();
			$("#goods_detail_div").show();
		}
	});
}

function queryAcceptInfoList() {
	var acceptInfo_div = $("#acceptInfo_div").html();
	$("#acceptInfo_div").empty();
	
	$.ajax({
		url : ctx + "/anon/queryAcceptInfoList",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data == 'noLogin'){
				alert("亲，您还没登录，请先登录。");
				window.location.href =  ctx + '/anon/login?isOauth=1&returnUrl='+callbackUrl;
				return ;
			}
			if(data.code == '0'){
				if(data.acceptList != undefined && data.acceptList != null){
					var a = data.acceptList[0];
					acceptId = a.id;
					ischooseAddress = true;
					var detailAddress = getString(a.province)+getString(a.city)+getString(a.area)+a.address;
					$("#acceptInfo_div").append(acceptInfo_div.format(getString(a.phone),getString(a.acceptName),getString(detailAddress)));
					$("#noAddress").hide();
				}else{
					ischooseAddress = false;
					$("#noAddress").show();
				}
			}
			$("#acceptInfo_div").show();
		}
	});
}

function queryAcceptInfoById(acceptId) {
	var acceptInfo_div = $("#acceptInfo_div").html();
	$("#acceptInfo_div").empty();
	
	$.ajax({
		url : ctx + "/anon/queryAcceptInfo",
		type : "POST",
		data : [ {
			name : 'id',
			value : acceptId
		}],
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data == 'noLogin'){
				alert("亲，您还没登录，请先登录。");
				window.location.href =  ctx + '/anon/login?isOauth=1&returnUrl='+callbackUrl;
				return ;
			}
			if(data.code == '0'){
				if(data.acceptObj != undefined && data.acceptObj != null){
					var a = data.acceptObj;
					acceptId = a.id;
					ischooseAddress = true;
					var detailAddress = getString(a.province)+getString(a.city)+getString(a.area)+a.address;
					$("#acceptInfo_div").append(acceptInfo_div.format(getString(a.phone),getString(a.acceptName),getString(detailAddress)));
					$("#noAddress").hide();
				}else{
					ischooseAddress = false;
					$("#noAddress").show();
				}
			}
			$("#acceptInfo_div").show();
		}
	});
}

function submitOrder() {
	if($.cookie('token') == token){
		orderCode = $.cookie('_orderCode');
		if(type == '1'||type == '4'){
			alert("亲，操作已完成。");
			return;
		}
	}
	var userId = queryUserId();
	if(userId == null || userId == ''){
		alert("亲，您还没登录，请先登录。");
		window.location.href =  ctx + '/anon/login?isOauth=1&returnUrl='+callbackUrl;
		return ;
	}
	if(acceptId == null || acceptId == '' || !ischooseAddress){
		alert("亲，您的收货地址还没选择，请选择哦。");
		return ;
	}
	//type=1表示使用特权操作，这里排除特权情况；
	if(type != '1' && type != '4' && orderCode != null && orderCode != ''){
		pay(userId);
		return;
	}
	$("#submitOrderBut").attr('disabled',"true");
	var productNum = $("#productNum").val();
	var cardCode = $.cookie('cardCode');
	$.ajax({
		url : ctx + "/anon/submitOrder",
		data : [ {
			name : 'goodsCode',
			value : goodsCode
		}, {
			name : 'goodsVersion',
			value : goodsVersion
		},{
			name : 'productNum',
			value : productNum
		},{
			name : 'dataSign',
			value : dataSign
		},{
			name : 'shopPrice',
			value : shopPrice
		},{
			name : 'acceptId',
			value : acceptId
		},{
			name : 'type',
			value : type
		},{
			name : 'orderCode',
			value : orderCode
		},{
			name : 'cardCode',
			value : cardCode
		}],
		type : "POST",
		dataType : "json",
		success : function(data) {
			$("#submitOrderBut").removeAttr('disabled');
			if(data == null || data == ''){
				return ;
			}
			if(data == 'dataSignFail'){
				alert("抱歉，下单失败哦，再试试吧。");
				return ;
			}
			if(data.code != '0'){
				alert("抱歉，下单失败哦，再试试吧。");
				return ;
			}
			orderCode = data.orderCode;
			$.cookie('token',token,{path:"/"});
			$.cookie('_orderCode',orderCode,{path:"/"});
			if(type == '1' || type=='4'){//如果是特权更换的，不用支付，直接跳转到更换成功提示页面；
				window.location.href = ctx + '/anon/toChangeSuccess?goodsName='+goodsName;
			}else{
				pay(userId);
			}
		}
	});
}

function pay(userId){
	var productNum = $("#productNum").val();
	var payMethod = $("input[name='payMethod']:checked").val();
	if(orderCode == null || orderCode == ''){
		alert("亲，下单失败哦，再试试吧。");
		return ;
	}
	if(payMethod == '0'){
		//支付宝支付；
		window.location.href = ctx + '/anon/toalipay?goodsName='+goodsName+'&goodsCode='+goodsCode+"&goodsVersion="+goodsVersion+"&shopPrice="+shopPrice+"&productNum="+productNum+"&orderCode="+orderCode+"&dataSign="+dataSign;
	}else if(payMethod == '1'){
		//微信支付；
		window.location.href = dbs + '/anon/towxPay?goodsName='+goodsName+'&goodsCode='+goodsCode+"&goodsVersion="+goodsVersion+"&shopPrice="+shopPrice+"&productNum="+productNum+"&orderCode="+orderCode+"&dataSign="+dataSign+'&userId='+userId;
	}else{
		alert("亲，请选择支付方式哦。");
	}
}

function toMoreAddress(){
	window.location.href = ctx + '/anon/toMoreAddress?goodsCode='+goodsCode+'&goodsVersion='+goodsVersion+'&orderCode='+orderCode+'&type='+type+'&goodsStock='+goodsStock;
}

function clear(){
	$.cookie('goodsCode','',{path:"/"});
	$.cookie('goodsVersion','',{path:"/"});
	$.cookie('goodsStock','',{path:"/"});
	$.cookie('acceptId','',{path:"/"});
	$.cookie('orderCode','',{path:"/"});
	$.cookie('type','',{path:"/"});
	$.cookie('flag','',{path:"/"});
}