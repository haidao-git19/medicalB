function popLogin(url) {
	var docEle = function()
	{
    	return document.getElementById(arguments[0]) || false;
	};
	var m = "mask";
    if (docEle(m)) document.body.removeChild(docEle(m));
    //mask遮罩层
    var newMask = document.createElement("div");
    newMask.id = m;
    newMask.style.position = "absolute";
    newMask.style.zIndex = "20000";
    _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
    _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
    newMask.style.width = _scrollWidth + "px";
    newMask.style.height = _scrollHeight + "px";
    newMask.style.top = "0px";
    newMask.style.left = "0px";
    newMask.style.background = "#000";
    newMask.style.filter = "alpha(opacity=10)";
    newMask.style.opacity = "0.10";
    document.body.appendChild(newMask);
    
    var newDiv = document.createElement("div");
    newDiv.id = "newDiv";
    newDiv.style.position = "absolute";
    newDiv.style.zIndex = "20002";
    newDivWidth = 470;
    newDivHeight = 460;
    newDiv.style.width = newDivWidth + "px";
    newDiv.style.height = newDivHeight + "px";
    var l_st=document.documentElement.scrollTop+document.body.scrollTop;//滚动条距顶部的距离
	var l_sl=document.documentElement.scrollLeft+document.body.scrollLeft;//滚动条距左边的距离
	var l_ch=document.documentElement.clientHeight;//屏幕的高度
	var l_cw=document.documentElement.clientWidth;//屏幕的宽度
	var l_objT=Number(l_st)+(Number(l_ch)-newDivHeight)/2;
	var l_objL=Number(l_sl)+(Number(l_cw)-newDivWidth)/2;
    newDiv.style.left = (l_objL) + "px";
		newDiv.style.top = (l_objT) + "px";
		
	var tDragIframe = document.createElement("iframe");                          
	tDragIframe.id = "DragIframe";                                              
	tDragIframe.src = "http://passport.111.com.cn/sso/pop.action?ReturnUrl="+url;
	tDragIframe.style.width    = "470px";                                            
	tDragIframe.style.height    = "460px";                                           
	tDragIframe.style.left = 0;  
	tDragIframe.style.border = "none 0";                                                    
	tDragIframe.frameborder = 0;                                                     
	tDragIframe.disabled = 0;                                                        
	tDragIframe.scrolling = "no";
	tDragIframe.marginwidth = "0"; 
	tDragIframe.setAttribute("frameborder", "0", 0);
	newDiv.appendChild(tDragIframe); 
	
    document.body.appendChild(newDiv);
    var shadow = document.createElement("div");
    shadow.id = "shadow";
	shadow.style.cssText = "background: none repeat scroll 0 0 #FFF;height:430px;position: absolute;width:470px;top: "+l_objT+"px; left:"+l_objL+"px;  z-index:10004;margin:0; -moz-box-shadow:0px 0px 20px #969696;-webkit-box-shadow:0px 0px 20px #969696;box-shadow:0px 0px 20px #969696;";
	document.body.appendChild(shadow);
}

function tipDialog(content){
	var diag = new Dialog();
	diag.Width = 400;
	diag.Height = 50;
	diag.Modal = false;
	diag.Title = "温馨提示";
	diag.InnerHtml='<div style="text-align:center;color:orange;font-size:14px;padding-top:10px;">'+content+'</div>';
	diag.OKEvent = function(){diag.close();};//点击确定后调用的方法
	diag.show();
}
function commonDialog(width,height,modal,title,url){
	var diag = new Dialog();
	diag.Width = width;
	diag.Height = height;
	diag.Modal = modal;
	diag.Title = title;
	diag.URL = url;
	diag.show();
}
function uploadImgDialog(html){
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 400;
	diag.Title = "编辑头像";
	diag.InnerHtml=html;
	diag.show();
}
function addFavorite(pid){
	commonDialog(468,260,true,"添加收藏夹",encodeURI("http://my.111.com.cn/ucenter/toAddFavorite.action?favorite.pid="+pid));
}

