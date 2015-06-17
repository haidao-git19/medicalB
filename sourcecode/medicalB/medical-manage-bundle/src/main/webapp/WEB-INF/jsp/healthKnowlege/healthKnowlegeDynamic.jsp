<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
<title>健康常识</title>
<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
<link href="${ctx}/resources/third-party/css/healthKnowlege/healthKnowlegeDynamic.css" rel="stylesheet">
<script src="${ctx }/resources/third-party/js/scrollpagination.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctx}/resources/third-party/js/healthKnowlege/healthKnowlegeDynamic.js"></script>
</head>
<body>

	<div id="dynamicContainer" style="display:none;">
		<div class="newsbox" onclick="javascript:window.location.href='{2}'">
			<img src="{3}" width="120" height="120"> 
			<div class="biaoti">{0} </div>
			<div class="ctbox">
				<div class="content">{1}</div>
			</div>
		</div>
		<div class="block"></div>
	</div>
	
	
	<input type="hidden" id="p" value="0"/>
	<div class="loading" id="loading">亲，请稍等，正在加载中哦!</div>
	<div class="loading" id="nomoreresults"></div>
	
</body>
</html>