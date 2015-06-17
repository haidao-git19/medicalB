<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/medical-tags" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单确认_医保宝_网上药店领导者_网上购买药品的放心药房网</title>
	
	<link href='${ctx}/resources/third-party/css/head.css' rel='stylesheet' type='text/css' />
	<link href='${ctx}/resources/third-party/css/orderConfirm2014.css' rel='stylesheet' type='text/css' />
	
	<script type="text/javascript" src="${ctx}/resources/web/js/buy/city_data.js"></script>
	<script type="text/javascript" src="${ctx}/resources/web/js/buy/selector.js"></script>
	<script type="text/javascript" src="${ctx}/resources/web/js/buy/buy.js"></script>
</head>

<body>
<!--header-->
<jsp:include page="/WEB-INF/jsp/common/head_of_cart.jsp"></jsp:include>

<div class="c_content">
	<div class="flow-steps">
		<ol class="num5">
			<li class="done current-prev"><span>1. 我的购物车</span></li>
			<li class="current"><span>2. 填写核对订单信息</span></li>
			<li class="last"><span>3. 成功提交订单</span></li>
		</ol>
	</div>

	<div class="cart-first-bar"><ul><li class="mycarttitle">填写核对订单信息</li></ul></div>
	
	<form id="orderForm">
	<div id="userAddressDiv" class="address here">
		<h3>收货地址</h3>
		<div class="addressBox">
			<ul id="useraddress" class="address-list"></ul>
		</div>
	
		<div id="addressForm" style="display: block;" class="other-address">
			<ul class="clearfix">
				<li>
					<span class="title"> <label><em class="h">*</em>收货地区：</label></span>
					<p>
						<select class="sifttxt" style="width: 80px;" id="selProvince"></select>
						<select class="sifttxt" style="width: 80px;" id="selCity"></select>
						<select class="sifttxt" style="width: 80px;" id="selArea"></select>
						
						<input type="hidden" id="_province" name="province">
						<input type="hidden" id="_city" name="city">
						<input type="hidden" id="_area" name="area">
					</p>
				</li>
				
				<li>
					<span class="title"><label for="send_ParticularAddress"><em class="h">*</em>详细地址：</label></span>
					<p><input type="text" maxlength="50" class="address-txt" id="send_ParticularAddress" name="particularAddress"></p>
				</li>
					
				<li>
					<span class="title"><label for="sendPeople"><em class="h">*</em>收货人：</label></span>
					<p>
						<input type="text" id="sendPeople" maxlength="10" name="sendPeople">
					</p>
				</li>
					
				<li>
					<span class="title"> <label for="send_ContactMobile"><em class="h">*</em>手机号码：</label></span>
					<p class="phone">
						<input type="text" id="send_ContactMobile" size="20" maxlength="20" name="contactMobile">
					</p>
				</li>
			</ul>
		</div>
	</div>
	
	<div id="userPayMent" class="payment">
		<h3>支付信息</h3>
		<div onclick="changePayMent(1);" id="payMent_1" class="online selected">
			<div class="online-info">
				<input type="radio" class="check_add" id="radio_int_bank" checked="checked" name="payMode" value="1">
				<label class="pay_info"><b>在线支付</b>支持支付宝平台支付。支持多家银行借记卡、信用卡。</label>
			</div>
			<div style="display: block;" id="orderBanksShow">
				<div class="p_t">支付平台</div>
				<ul class="pay_select clearfix">
					<li class="zhifu bank "><input type="radio" id="pay_bank_500" class="ra_bank" name="payPlatformCode" value="ALIPAY"></li>
				</ul>
				<div class="p_t">网上银行</div>
				<ul class="pay_select clearfix">
					<li class="zhaoshang bank"><input type="radio" id="pay_bank_502" class="ra_bank" name="payPlatformCode" value="CMB"></li>
					<li class="gongshang bank"><input type="radio" id="pay_bank_516" class="ra_bank" name="payPlatformCode" value="ICBC"></li>
				</ul>
			</div>
		</div>
	</div>
	</form>
	
	<div id="orderItemListInfo" class="commodityInfor">
		<h3 class="infoTitle">商品信息 <a href="${ctx}/anon/buy/cart" class="gotocart">返回购物车修改</a></h3>
		<div id="orderItemDiv">
			<table cellspacing="0" cellpadding="0" class="showInfoOrder">
				<colgroup>
					<col width="10%"><col width="55%"><col width="20%">
				</colgroup>
				<tbody>
					<tr>
						<th colspan="2" class="first_td">
							<b>商品清单</b>
							<dl class="express_way02 clearfix"><dt>配送方式：</dt><dd><span>普通快递</span></dd></dl>
						</th>
						<th>数量</th><th class="end_td">金额</th>
					</tr>
					
					<c:choose>
						<c:when test="${empty cart.items}">
							<tr><td class="first_td" colspan="4">无商品发现！</td></tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="_item" items="${cart.items}">
								<tr>
									<td class="first_td"><img width="50" height="50" src="${_item.value.goods.images[0].bigImg}"></td>
									<td class="taLeft">${_item.value.goods.goods.brandName} ${_item.value.goods.goods.goodsName} ${_item.value.goods.goods.spec}</td>
									<td>${_item.value.amount}</td>
									<td class="end_td"><em class="f_a">¥</em>${m:round(_item.value.goods.goods.shopPrice * _item.value.amount, 2)}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="order_box clearfix">
		<div class="box_r">
			<table cellspacing="0" cellpadding="0" class="check_table">
				<tbody>
					<tr>
						<td class="c_name">共计<span class="red">${fn:length(cart.items)}</span>件，总商品金额：</td>
						<td class="c_price"><em class="f_a">¥</em> <label id="theItemAllMoney">${cart.totalPrice}</label></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div id="pay_floatdiv" class="pay_float clearfix">
		<div class="p_b clearfix">
			<div class="p_row1">
				<span class="pay_lable">应付金额：</span>
				<div class="p_sum"><span class="pay_allmoney"><em class="f_a">¥</em><em id="theAllMoney">${cart.totalPrice}</em></span></div>
			</div>
		</div>
		<div class="p_row2 clearfix"></div>
		<div class="p_row3 clearfix">
			<div class="clearfix">
				<a onclick="submitOrder();" id="orderSubmitButton" href="javascript:;" class="pay_submit">提交订单</a>
			</div>
		</div>
	</div>

</div>
<!--footer-->
<jsp:include page="/WEB-INF/jsp/common/foot.jsp"></jsp:include>

</body>
</html>