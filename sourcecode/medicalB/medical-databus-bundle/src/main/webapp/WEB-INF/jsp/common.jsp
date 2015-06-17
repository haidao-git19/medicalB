<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:useBean id="versionBean" class="com.netbull.shop.entity.Version" />
<c:set var="version" value="${versionBean.version}" scope="request" />
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<meta http-equiv="X-UA-Compatible" content="IE=8" >
<link href="${ctx }/resources/third-party/jquery-ui-bs/assets/css/bootstrap.css?ver=${version}" rel="stylesheet">
<link href="${ctx }/resources/third-party/jquery-ui-bs/assets/css/bootstrap-responsive.css?ver=${version}" rel="stylesheet">
<link href="${ctx }/resources/third-party/jquery-ui-bs/css/custom-theme/jquery-ui-1.10.0.custom.css?ver=${version}" rel="stylesheet">
<!--[if lt IE 9]>
<link rel="stylesheet" href="${ctx}/resources/third-party/jquery-ui-bs/css/custom-theme/jquery.ui.1.10.0.ie.css"/>
<![endif]-->

<link href="${ctx }/resources/third-party/jquery-validation/1.10.0/validate.css?ver=${version}" rel="stylesheet">
<link href="${ctx }/resources/third-party/dataTables/jquery.dataTables.bs.css?ver=${version}" rel="stylesheet">
<link href="${ctx }/resources/third-party/jquery-ui-multiselect/jquery.multiselect.css?ver=${version}" rel="stylesheet">
<link href="${ctx }/resources/third-party/jquery-loadmask-0.4/jquery.loadmask.css?ver=${version}" rel="stylesheet">
<link href="${ctx }/resources/third-party/uploadify/uploadify.css?ver=${version}" rel="stylesheet" type="text/css"/>
<link href="${ctx }/resources/third-party/My97DatePicker/skin/WdatePicker.css?ver=${version}" rel="stylesheet" type="text/css"/>

<link href="${ctx }/resources/third-party/css/common.css?ver=${version}" rel="stylesheet">

<link rel="shortcut icon" href="http://lingxi.voicecloud.cn/favicon.ico" />

<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/js/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/js/jquery-ui-1.10.0.custom.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/assets/js/bootstrap.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-validation/1.10.0/jquery.validate.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-validation/1.10.0/messages_bs_zh.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/dataTables/jquery.dataTables.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.blockUI.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-multiselect/jquery.multiselect.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-loadmask-0.4/jquery.loadmask.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/uploadify/jquery.uploadify.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/My97DatePicker/WdatePicker.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.cookie.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/cookie.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/common.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.cookie.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.callback.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/ajaxfileupload.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/additional-methods.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/form.js?ver=${version}"></script>

<script type="text/javascript">
	var ctx = "${ctx}";
</script>
