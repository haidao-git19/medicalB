<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:useBean id="versionBean" class="com.netbull.shop.entity.Version" />
<c:set var="version" value="${versionBean.version}" scope="request" />
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<meta http-equiv="X-UA-Compatible" content="IE=8" >

<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/js/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/js/jquery-ui-1.10.0.custom.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/assets/js/bootstrap.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-validation/1.10.0/jquery.validate.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-validation/1.10.0/messages_bs_zh.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/dataTables/jquery.dataTables.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-multiselect/jquery.multiselect.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-loadmask-0.4/jquery.loadmask.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/uploadify/jquery.uploadify.min.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/My97DatePicker/WdatePicker.js?ver=${version}"></script>

<script type="text/javascript" src="${ctx }/resources/third-party/js/common.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.artDialog.source.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/artDialog.iframeTools.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/ajaxfileupload.js?ver=${version}"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/additional-methods.js?ver=${version}"></script>

<script type="text/javascript">
	var ctx = "${ctx}";
</script>
