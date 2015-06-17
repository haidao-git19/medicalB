<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>内容页</title>
<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
<script src="${resources }/resources/third-party/js/touchslide.js" type="text/javascript"></script>
<script src="${resources}/resources/third-party/js/jquery.lazyload.min.js" type="text/javascript"></script>
<link href="${resources }/resources/third-party/css/global.css" rel="stylesheet" type="text/css">
<link href="${resources }/resources/third-party/css/wnclient.css" rel="stylesheet" type="text/css">
</head>
<body  class="appbg">
<div class="brd-box pt10 pb10">
    <div class="showpic p10">
    	<div>
        	{0}
        </div>
    </div>
</div>
<script type="text/javascript">
	$("img.lazy").lazyload({effect: "fadeIn",placeholder:resources+"/resources/third-party/images/product_default_img.png"});
</script>
</body>
</html>
