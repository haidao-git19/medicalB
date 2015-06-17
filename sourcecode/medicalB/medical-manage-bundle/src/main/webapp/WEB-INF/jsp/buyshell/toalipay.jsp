<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.netbull.shop.manage.common.util.RequestUtils"%>
<%@ page import="com.netbull.shop.common.util.StringTools"%>
<%@ page import="com.netbull.shop.common.util.StringUtil"%>
<%@ page import="com.netbull.shop.common.config.ConfigLoadUtil"%>
<%@ page import="com.netbull.shop.manage.common.util.HttpXmlUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝手机网页支付</title>
	</head>
	<%
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		/***
		 * 开始价格签名校验
		 */
		 boolean result = StringTools.verifiProductPrice(requestMap);
		if(!result){
			out.println("数字签名验证失败");
			return;
		}
		String shopPrice = StringUtil.getString(requestMap.get("shopPrice"));
		String productNum = StringUtil.getString(requestMap.get("productNum"));
		requestMap.put("totalFee", Float.parseFloat(shopPrice)*Integer.parseInt(productNum)+"");
		
		String url = ConfigLoadUtil.loadConfig().getPropertie("alipayapi"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		out.println(resp);
	%>
	<body>
	</body>
</html>
