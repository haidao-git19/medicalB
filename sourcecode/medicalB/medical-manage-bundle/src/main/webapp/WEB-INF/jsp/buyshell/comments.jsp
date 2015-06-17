<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
<title>评论晒单</title>
<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
<link href="${ctx}/resources/third-party/css/buyshell/comments.css" rel="stylesheet">
<script src="${ctx }/resources/third-party/js/scrollpagination.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/buyshell/comments.js" type="text/javascript"></script>
<script type="text/javascript">
	var goodsCode='${param.goodsCode}';
</script>
</head>
<body>
	<div id="commentsContainer" style="display:none;">
		<div class="plbox">
			<div class="pltop">{1}
				<a class="name">{2}</a>
			    <a class="time">{0}</a>
			</div>
			<div class="plcontent">{3}</div>
		</div>
	</div>
	<div id="noComments" style="display:none;">
		<img src="${ctx }/resources/third-party/images/pinlun.png" width="140" height="140" style=" display:block;margin-left:auto; margin-right:auto; margin-top:40px; margin-bottom:10px;">
		<p style=" text-align:center; font-size:16px; color:#808080;margin:0px; margin-bottom:20px;">当前还没有评价哦，快来评价吧！</p>
	</div>
	<input type="hidden" id="p" value="0"/>
	<div class="loading" id="loading">亲，请稍等，正在加载中哦!</div>
	<div class="loading" id="nomoreresults"></div>
	
	<div class="footerbox">
		<div class="add">
			<input id="comment" class="shuru" name="contents" value="请输入评论内容..." onfocus="if (value =='请输入评论内容...'){value =''} this.style.color='#4d4d4d'" onblur="if (value ==''){this.style.color='#c9c9c9';value='请输入评论内容...'}">
	     	<div id="sendBtn" class="send">发送</div>
	    </div>
	</div>
</body>
</html>