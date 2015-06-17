<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
<title>健康常识</title>
<link href="http://wn101.com/resources/css/health/healthKnowlegeTemplate.css" rel="stylesheet">
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js?ver=${version}" type="text/javascript"></script>
<script type="text/javascript">
window.onload=function(){
	if(isWeiXin()){
		document.getElementById("contact_1").href="weixin://profile/gh_fb80e176b46e";
		document.getElementById("contact_2").setAttribute("onclick","window.open('weixin://profile/gh_fb80e176b46e')");
	}
}
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    var result=ua.match(/micromessenger/g);
    if(result == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}
</script>
</head>
<body>
	<div class="title">${healthKnowlege.title}</div>
	<div class="smalltilte"><span>${healthKnowlege.createTime}</span><a id="contact_1" href="#">网牛</a></div>
	<div class="wnbox">
	<div class="wn"><img src="http://wn101.com/resources/upload/hua_03.png"></div>
	</div>
	<div class="neirong">${healthKnowlege.content}</div>
	<div class="tuiguang">推广</div>
	<div class="bottombox" id="contact_2"><img src="http://wn101.com/resources/upload/222.png"></div>
</body>
</html>