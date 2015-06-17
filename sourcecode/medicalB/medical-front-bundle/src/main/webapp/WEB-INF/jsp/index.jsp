<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<head>
<title>${childMenu}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="x-ua-compatible" content="IE=edge" />
<meta http-equiv="Cache-Control" content="no-transform" />
<title>医保宝_网上药店领导者_网上购买药品的放心药房网</title>
<meta name="keywords" content="网上药店,网上药房,药房网,药品网,网上买药,网上购药,医保宝"/>
<meta name="description" content="医保宝网上药店是全国正规合法网上药店平台，并有国家执业医师提供药品购买免费咨询，为您网上买药提供超越传统药店的贴心服务。"/>

<link href="http://lingxi.voicecloud.cn/favicon.ico" rel="shortcut icon"/>
<link href="${ctx }/resources/third-party/jquery-ui-bs/assets/css/bootstrap.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/index/index.css" rel="stylesheet">

<script src="${ctx }/resources/third-party/jquery-ui-bs/assets/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/resources/third-party/jquery-ui-bs/assets/js/bootstrap.min.js" type="text/javascript"></script>

<script type="text/javascript">
	var ctx = '${ctx}';
	var loginName = '<shiro:principal property="loginName"/>';
	
	function iFrameHeight() {
		var ifm = document.getElementById("main_frame");
		var subWeb = document.frames ? document.frames["main_frame"].document : ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			if(subWeb.body.scrollHeight < 400){
				ifm.height = 562;
			}else{
				ifm.height = subWeb.body.scrollHeight;	
			}
		}
	}
</script>

<script src="${ctx }/resources/third-party/index/index.js" type="text/javascript"></script>

<style type="text/css">
    iframe{
		position:absolute;
		top:0;
		left:0;
		right:0;
		bottom:0;
	}
</style>
</head>
<body>
<div class="navbar">
	<div class="navbar-inner">
		<a class="brand" id="main" href="${ctx }/" target="_top"><span>微商运营平台</span></a>
		<shiro:user>
			<ul class="nav pull-right">
				<li id="btn-user" class="dropdown"><a href="#"
					role="button" class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-user icon-white"></i> <shiro:principal property="name" />，您好！<i class="caret"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a id="auth_link" href="${ctx }/" target="_top"><i class="icon-home"></i> 统一运营</a></li>
						<li><a id="pwd_link" href="${ctx }/user/changePwd" target="main_frame"><i class="icon-lock"></i> 修改密码</a></li>
						<li class="divider"></li>
						<li><a href="${ctx }/logout" target="_top"><i class="icon-trash"></i> 注销</a></li>
					</ul></li>
			</ul>
		</shiro:user>
	</div>
</div>
<div class="sidebar-nav">
	<div class="demo-menu hide">
	<a href="#menu_{0}" class="nav-header collapsed" data-toggle="collapse">{1}</a>
	<ul id="menu_{0}" class="nav nav-list collapse">
	</ul>
</div>

</div>
<div class="content">
	<div class="split left">
		<div class="caret"></div>
	</div>
	<ul class="breadcrumb">
		<li class='active'>&nbsp;</li>
	</ul>
	<div class="iframe-wrap">
		<iframe id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%" ></iframe>
	</div>
</div>

<div class="footer">
	 &nbsp;v1.0beta &nbsp;@版权所有&nbsp; 
</div>
</body>

</html>
