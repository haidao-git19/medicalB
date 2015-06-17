<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>

<style>
html,body{
	width:100%;
	height:100%;
}
iframe{
	width:620px;
	height:450px;
	border:none; !important
}
</style>
<script type="text/javascript">

 function setLocation(hotelAddress,hotelLocationX,hotelLocationY){
	 var origin = artDialog.open.origin;
	 origin.document.getElementById('physicsAddress').value= hotelAddress;
     origin.document.getElementById('locationX').value = hotelLocationX;//经度
     origin.document.getElementById('locationY').value = hotelLocationY;//经度
     baiduMapDialog.close();
 }
 
</script>
</head>
<body>
	<iframe id="main_frame" name="main_frame" src="${ctx}/hotel/baiduMap" frameborder="0" width="100%" height="100%"></iframe>
</body>
</html>
