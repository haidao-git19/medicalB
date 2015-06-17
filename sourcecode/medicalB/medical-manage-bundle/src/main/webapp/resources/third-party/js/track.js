
$(function() {
	userTrack('0','');
});

function getValueForJS(v){
	if(v==''||v=="\"\""||v==null||v=='undefined'||v==undefined){
		return '';
	}
	
	return v;
}

function userTrack(eventType,desc) {
	var openidValue = getValueForJS(getCookie("USERTRACK_OPENID"));
	var sessionId = getValueForJS(getCookie("USERTRACK_SESSIONID"));
	var loginCode = getValueForJS(getCookie("USERTRACK_LOGINCODE"));
	var title = $(document).attr("title");
	var pre = "";
	
	var _s = document.createElement('script');
	if(typeof(type)=="undefined"){
		_s.setAttribute("src", "http://115.28.189.29/track/t?funcId=10021&openId=" + openidValue + "&sessionId=" + sessionId + "&pre=" + pre+"&title="+title+"&eventType="+eventType+"&desc="+desc+"&loginCode="+loginCode+"&type=");
	}else{
		_s.setAttribute("src", "http://115.28.189.29/track/t?funcId=10021&openId=" + openidValue + "&sessionId=" + sessionId + "&pre=" + pre+"&title="+title+"&eventType="+eventType+"&desc="+desc+"&loginCode="+loginCode+"&type="+type);
	}
	_s.setAttribute("id", "track");
	document.getElementsByTagName('head')[0].appendChild(_s);
	
}