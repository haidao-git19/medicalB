<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>收货地址管理</title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/third-party/js/additional-methods.js?ver=${version}"></script>
    	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js?ver=${version}" type="text/javascript"></script>
    	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=Z4DBZ-5YOWS-NJVOY-6J6PR-WCTE2-ZHBJ6"></script>
    	
    	<link href="${ctx }/resources/third-party/css/global.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/wnclient.css?ver=${version}" rel="stylesheet" type="text/css">
		<style>
			input[type='number'] {
		    	-moz-appearance:textfield;
			}
			input[type=number]::-webkit-inner-spin-button,
			input[type=number]::-webkit-outer-spin-button {
				-webkit-appearance: none;
				margin: 0;
			}
		</style>

		<script type="text/javascript">
			var goodsCode = '${_param.goodsCode}';
	    	var goodsVersion = '${_param.goodsVersion}';
	    	var goodsStock = '${_param.goodsStock}';
	    	var acceptId = '${_param.acceptId}';
	    	var orderCode = '${_param.orderCode}';
	    	var type = '${_param.type}';
	    	var topage = '${_param.topage}';
	    	var flag = '${_param.flag}';
    	
        	var acceptName = '${_param.acceptName}';
        	var phone = '${_param.phone}';
        	var area = '${_param.area}';
        	var address = '${_param.address}';
        	var zipcode = '${_param.zipcode}';
        	var isEidt = '${_param.isEidt}';
        	var isEmpty= '${isEmpty}';
        	wx.config({
	    	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    	    appId: '${appId}', // 必填，公众号的唯一标识
	    	    timestamp: '${timestamp}', // 必填，生成签名的时间戳
	    	    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
	    	    signature: '${signature}',// 必填，签名，见附录1
	    	    jsApiList: ['checkJsApi',
	    	                'openLocation',
	    	                'getLocation'
   	           ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	    	});
        </script>
        <script src="${ctx }/resources/third-party/js/address/address.js?ver=${version}" type="text/javascript"></script>
	</head>
	
<body class="appbg pb60">
	<div class="top"><div class="colors"></div></div>
	<form id="address_form" name="address_form">
		<input name="id" id="id" type="hidden" value="${_param.id}">
		<input name="area" type="hidden" id="_area" value="${_param.area}">
		<div class="p10 form">
		    <h4 class="lh200 f18" id="addressTitle"></h4>
			<div class="mt10 form-item">
		    	<p>
		    	  <input name="acceptName" id="acceptName" type="text" class="w100 ipttxt f14 gray pl8 box-sizing" value="收货人姓名" onfocus="if (value =='收货人姓名'){value ='' ;this.style.color='#4d4d4d';} " onblur=" if (!value){this.style.color='#c9c9c9';value='收货人姓名'}">
		    	</p>
		    </div>
		  	<div class="mt10 form-item">
		    	<p>
		    	  <input type="text" id="phone" name="phone" value="收货人手机号码" onfocus="if (value =='收货人手机号码'){value ='';this.type='number';this.style.color='#4d4d4d'} " onblur=" if (!value){this.type='text';this.style.color='#c9c9c9';value='收货人手机号码'}" class="w100 ipttxt f14 gray pl8  box-sizing"/>
		    	</p>
		    </div>
		  	<div class="mt10">
		    	<p style=" position:relative;">
		    	 <input id="area" type="text" class="w100 ipttxt gray f14 pl8" value="选择收货地区" onfocus="if (value =='选择收货地区'){value ='';this.style.color='#4d4d4d'} " onblur="if (!value){this.style.color='#c9c9c9';value='选择收货地区'}">
		    	 <img src="${ctx }/resources/third-party/images/daohang.png" id="searchLocation" style="position:absolute; top: 5px; right:5px; width:34px; height:34px;">
		    	</p>
		    </div>
		  	<div class="mt10 form-item">
		    	<p>
		    	  <input name="address" id="address" type="text"   class="w100 ipttxt gray f14 pl8  box-sizing" value="街道地址" onfocus="if (value =='街道地址'){value ='';this.style.color='#4d4d4d'} " onblur="if (!value){this.style.color='#c9c9c9';value='街道地址'}"> 
		    	</p>
		    </div>
		  	<div class="mt10 form-item">
		    	<p>
		    	  <input type="text" id="zipcode" name="zipcode" value="邮编" onfocus="if (value =='邮编'){value ='';this.type='number';this.style.color='#4d4d4d'} " onblur=" if (!value){this.type='text';this.style.color='#c9c9c9';value='邮编'}" class="w100 ipttxt f14 gray pl8  box-sizing"/>
		    	</p>
		    </div>
		</div>
		<div class="btmbar p10 box-sizing">
		    <p class="tc"><input name="" type="button" value="确定" class="btn-org"></p> 
		</div>
	</form>
</body>
</html>
