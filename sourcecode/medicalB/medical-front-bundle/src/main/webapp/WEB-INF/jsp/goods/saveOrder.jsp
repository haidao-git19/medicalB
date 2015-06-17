<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%@ page import="com.netbull.shop.manage.common.util.ShopConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	Integer rc = (Integer)request.getAttribute("rc");
	String orderNumber = (String)request.getAttribute("orderNumber");
	String totalPrice = (String)request.getAttribute("totalPrice");
	String ctx = (String)request.getAttribute("ctx");
	String sign = DigestUtils.md5Hex(rc+orderNumber+totalPrice+ShopConst.SIGN_KEY);
	
	response.sendRedirect("../../../anon/buy/orderFinish?_r="+rc+"&_n="+orderNumber+"&_p="+totalPrice+"&_sig="+sign);
%>