<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="versionBean" class="com.netbull.shop.entity.Version" />
<c:set var="version" value="${versionBean.version}" scope="request" />
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<script type="text/javascript">
			function SetWinHeight(obj) {
				var win = obj;
				if (document.getElementById) {
					if (win && !window.opera) {
						if (win.contentDocument
								&& win.contentDocument.body.offsetHeight)
							win.height = win.contentDocument.body.offsetHeight;
						else if (win.Document && win.Document.body.scrollHeight)
							win.height = win.Document.body.scrollHeight;
					}
				}
			}
		</script>
	</head>
<body>
	<iframe  src="${param.url}?ver=${version}" id='win' onload='Javascript:SetWinHeight(this)' border='0' marginwidth='0' marginheight='0' allowtransparency='yes' frameborder='no' scrolling='no' width='100%' height='100%'></iframe>
</body>
</html>
