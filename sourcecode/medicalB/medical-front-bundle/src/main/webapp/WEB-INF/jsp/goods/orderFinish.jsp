<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/medical-tags" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>成功提交_医保宝_网上药店领导者_网上购买药品的放心药房网</title>
	
	<link href='${ctx}/resources/third-party/css/head.css' rel='stylesheet' type='text/css' />
	<link href='${ctx}/resources/third-party/css/orderConfirm2014.css' rel='stylesheet' type='text/css' />
	
	<%--<script type="text/javascript" src="${ctx}/resources/web/js/buy/city_data.js"></script> --%>
</head>

<body>
<!--header-->
<jsp:include page="/WEB-INF/jsp/common/head_of_cart.jsp"></jsp:include>

<div class="c_content">
	<div class="flow-steps">
		<ol class="num5">
			<li class="done current-prev"><span>1. 我的购物车</span></li>
			<li class="done current-prev"><span>2. 填写核对订单信息</span></li>
			<li class="current"><span>3. 成功提交订单</span></li>
		</ol>
	</div>

	<div class="success_box zaixin">
		<div class="s_inner clearfix">
			<div class="s_l">
				<i class="s_ok"></i>
			</div>
			
			<div class="s_r">
				<h4>订单已提交成功，请您尽快付款！</h4>
				<p>请您在提交订单后<b class="red">3天内</b>内完成支付，否则订单会自动取消。</p>
				<div class="s_o">
					<p class="mb10">
						<b>订单编号：</b>
						<a class="blue mr10" target="_blank" href="#">${orderNumber}</a>
						<span class="ml20">
							订单金额：
							<span class="red">&nbsp;<em class="f_a">¥</em>&nbsp;${totalPrice}</span>
						</span>
					</p>
					<a target="_blank" href="http://p1.maiyaole.com/images/order/zhonghang.gif" class="print_box blue">
						<i class="print_icon"></i>打印转账信息
					</a>
					<div class="bank_trans"></div>
				</div>
			</div>
		</div>
	</div>

</div>

<!--footer-->
<jsp:include page="/WEB-INF/jsp/common/foot.jsp"></jsp:include>

</body>
</html>