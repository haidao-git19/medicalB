<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/medical-tags" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>我的购物车_医保宝_网上药店领导者_网上购买药品的放心药房网</title>
	
	<link href="${ctx}/resources/third-party/css/head.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/third-party/css/cart.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/resources/third-party/css/common.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${ctx}/resources/web/js/buy/cart.js"></script>
</head>

<body>
<!--header-->
<jsp:include page="/WEB-INF/jsp/common/head_of_cart.jsp"></jsp:include>

<div class="c_content">
	<div class="flow-steps">
		<ol class="num5">
			<li class="current"><span>1. 我的购物车</span></li>
			<li><span>2. 填写核对订单信息</span></li>
			<li class="last"><span>3. 成功提交订单</span></li>
		</ol>
	</div>
	
	<div class="cart-first-bar">
		<ul>
			<li class="mycarttitle">我的购物车</li>
			<li class="cart_note">为避免选购商品售罄或恢复原价，请尽快结算</li>
		</ul>
	</div>
	
	<div class="cart-main">
		<div class="cart-table-th">
			<div class="wp">
				<div class="th th-chk">
					<div class="select-all J_SelectAll">
						<div>
							<label class="cart-checkbox cart-checkbox-selected">
								<input type="checkbox" checked="checked" id="allselectA" onclick="checkAllItem('allselectA');">
							</label>全选
						</div>
					</div>
				</div>
				<div class="th th-item">商品信息</div>
				<div class="th th-price">金额</div>
				<div class="th th-amount">数量</div>
				<div class="th th-weight">重量</div>
				<div class="th th-location">&nbsp;</div>
				<div class="th th-sum">小计</div>
				<div class="th th-op">操作</div>
			</div>
		</div>
	
		<div class="orderHolder">
			<div class="itemHeShop clearfix">&nbsp;</div>
			
			<c:choose>
				<c:when test="${empty cart.items}">
					<div class="item-body clearfix">
						无商品发现！
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="_item" items="${cart.items}">
						<div class="item-body clearfix">
							<ul class="item-content clearfix">
								<li class="td td-chk">
									<div class="td-inner">
										<label class="cart-checkbox cart-checkbox-selected ">
											<input type="checkbox" name="cart_item" checked="checked" value="${_item.key}">
										</label>
									</div>
								</li>
								<li class="td td-item">
									<div class="td-inner">
										<div class="item-pic"><a target="_blank" href="${ctx}/anon/goods/detail?_gc=${_item.value.goods.goods.goodsCode}"><img class="itempic" alt="${_item.value.goods.goods.brandName} ${_item.value.goods.goods.goodsName} ${_item.value.goods.goods.spec}" src="${_item.value.goods.images[0].bigImg}" ></a></div>
										<div class="item-info"><a class="item-title" target="_blank" href="${ctx}/anon/goods/detail?_gc=${_item.value.goods.goods.goodsCode}">${_item.value.goods.goods.brandName} ${_item.value.goods.goods.goodsName} ${_item.value.goods.goods.spec}</a></div>
									</div>
								</li>
								<li class="td td-price"><div class="td-inner"><em>¥</em>${_item.value.goods.goods.shopPrice}</div></li>
								<li class="td td-amount">
									<div catal="0" limitbuy="0" class="td-inner">
										<div class="item-amount ">
											<input type="button" onclick="updatedItem('${_item.key}', -1)" class="btn-reduce">
											<input type="text" id="product_amount_${_item.key}" value="${_item.value.amount}" maxlength="3" class="text-amount" readonly="readonly" >
											<input type="button" onclick="updatedItem('${_item.key}', 1)" class="btn-plus">
										</div>
									</div>
								</li>
								<li class="td td-weight"><div class="td-inner"><em class="number">${_item.value.goods.goods.weight}</em></div></li>
								<li class="td td-location"></li>
								<li class="td td-sum"><div class="td-inner red"><em>¥</em>${m:round(_item.value.goods.goods.shopPrice * _item.value.amount, 2)}</div></li>
								<li class="td td-op"><div class="td-inner"><a class="deleteButton" onclick="delItem('${_item.key}')" href="javascript:;">删除</a></div></li>
							</ul>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	
			<div class="clearfix">
				<ul class="dianzongji">
					<li>商品金额<span class="red"><b>¥</b>${cart.totalPrice}</span></li>
				</ul>
			</div>
		</div>
	</div>
		
	<ul class="cartOperation">
	    <li class="fr"><a id="shoppingCart2Index" href="#"><span class="gt">&lt;&lt;</span> 继续购物</a></li>
	</ul>
       
	<div id="cart_float" class="float_bar clearfix" style="position: relative;">
		<ul class="cartOperation">
		    <li><label class="cart-checkbox  cart-checkbox-selected"><input type="checkbox" checked="checked"  id="allselectB" onclick="checkAllItem('allselectB');"></label>全选</li>
		    <li class="operations"><a onclick="delItems();return false;" href="javascript:void(0);">删除</a></li>
		    <li class="clearitem"><a id="clearAllCart" onclick="clearCart();" href="javascript:void(0);">清空购物车</a></li>
		</ul>
	    <ul class="c_pay">
	        <li class="wrap_btn"><a href="javascript:void(0);" class="btn_order" onclick="fillOrder();">去结算</a></li>
	        <li class="item">商品总价 <b>¥</b><span>${cart.totalPrice}</span></li>
	    </ul>
	</div>
	
</div>

<!--footer-->
<jsp:include page="/WEB-INF/jsp/common/foot.jsp"></jsp:include>

</body>
</html>