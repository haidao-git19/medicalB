<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="versionBean" class="com.netbull.shop.entity.Version" />
<c:set var="version" value="${versionBean.version}" scope="request" />
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/js/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.cookie.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/mobile.js?ver=${version}"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var version = "${version}";
	var manage = "http://wn101.com/manage";
</script>