<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<jsp:useBean id="versionBean" class="com.netbull.shop.entity.Version" />
<c:set var="version" value="${versionBean.version}" scope="request" />
<c:set var="url" value="${versionBean.url}" scope="request" />
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<meta http-equiv="X-UA-Compatible" content="IE=8" >

<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/head201406.css?ver=${version}">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/head_990.min.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/index201406.min.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/privatecustom.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/guide.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/iconfont.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/head_990.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/jquery.jqzoom.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/common.css?ver=${version}" />

<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/global_top.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/user_login_cookie.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.lazyload.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery-powerSwitch.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/common.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/catalog_201406.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/slider.js?ver=${version}"></script>

<script type="text/javascript">
	var url = "${url}";
	var ctx = "${ctx}";
	var version = "${version}";
	
	$(document).ready(function(){
		loadChannel();
	});
	
	function loadChannel() {
		$.ajax({
			type: "GET",
			url: ctx+"/anon/index/channel?rnd="+Math.random(),
			data: "",
			success : function(data) {
				$('#headChannel').empty().append(data);
			},
			error : function() {alert('loadFloor error');}
		});
	}
	
	function _hover(id) {
		$("#"+id).show();
	}
</script>