<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:useBean id="versionBean" class="com.netbull.shop.entity.Version" />
<c:set var="version" value="${versionBean.version}" scope="request" />
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<meta http-equiv="X-UA-Compatible" content="IE=8" >

<link href="${ctx}/resources/web/css/global-v5.css?ver=${version}" rel="stylesheet" type="text/css"/>
<link href="${ctx }/resources/web/css/popup.css?ver=${version}" rel="stylesheet" type="text/css"/>

<link href="${ctx }/resources/web/css/index.css?ver=${version}" rel="stylesheet">

<link rel="shortcut icon" href="http://lingxi.voicecloud.cn/favicon.ico" />

<script type="text/javascript" src="${ctx }/resources/web/js/jquery.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/web/js/jquery.cm_dialog.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/web/js/global.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/web/js/jquery.placeholder.1.3.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/web/js/function.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/web/js/jquery.superslide.2.1.1.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/mobile.js?ver=${version}"></script>

<script type="text/javascript">
	var ctx = "${ctx}";
</script>
