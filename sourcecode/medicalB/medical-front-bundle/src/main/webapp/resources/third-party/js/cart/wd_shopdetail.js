/**
 * ������ղ� status==1
 */
function loginCheck(e,backurl,status,id){
		var curl="http://www.111.com.cn/front/check/addFavorites.action?t="+new Date()+"&status="+status+"&addtoFavorites.pids="+id;
		userLoginCheck(e, backurl, status, id, curl);
}

function loginCheckMini(e,backurl,status,id){
		var curl="http://www.111.com.cn/front/check/addFavorites.action?t="+new Date()+"&status="+status+"&addtoFavorites.pids="+id+"&venderid=2011120210520001";
		userLoginCheck(e, backurl, status, id, curl);
}

function userLoginCheck(e, backurl, status, id, curl){
		var e =  window.event;
		
		if(!createUser()){
			if(status == '1'){
				backurl += "&aa="+id;
			}
			testMessageBox(e,backurl);
			return;
		}
		
        if(status != '1'){
        	backurl = 'http://buy.111.com.cn/interfaces/order/orderSure.action';
        	window.location.href = backurl;
          	return ;
       	}
       	  
       	addFavorites(backurl,curl);
}
            
            
/**
 * �첽�����ղ�
 */
function addFavorites(backurl,curl){
	
$.getScript(curl, function(result){
  	if(res==null){
        backurl+="&aa="+id;
        testMessageBox(e,backurl);
    }else{
        var loginCheck = res.split("?");
		if (loginCheck[1] == "error") {
			backurl += "&aa=" + id;
			testMessageBox(e, backurl);
		}
		if (loginCheck[1] == "0") {
			window.location = backurl;
		}
		if (loginCheck[1] == "1") {
			var u = backurl.split("&aa=");
			// u[0]=decodeURIComponent(u[0]);
			openNewDiv('http://p4.maiyaole.com/images/ConSc.jpg', '�ղسɹ�     <a href="http://my.111.com.cn/ucenter/myFavorite.action">�鿴�ղؼ�>></a>', u[0]);
		}
		if (loginCheck[1] == "2") {
			// alert("ϵͳ��æ,���Ժ�����");
		}
		if (loginCheck[1] == "3") {
			var u1 = backurl.split("&aa=");
			// u1[0]=decodeURIComponent(u1[0]);
			openNewDiv('http://p4.maiyaole.com/images/Boderh2Bg.gif',
					'���Ѿ��ղع���', u1[0]);
		}      
    }
});
       	  
}
            
            
function checkLogin(){
	return false;
}


// ��֤�û���¼Cookies
function ppkRead(name, second) {
if (!second) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
} else {
    var val = ppkRead(name);
    if (val) {
        var arr = val.split('&');
        for (var i = 0, len = arr.length; i < len; i++) {
            if (arr[i].indexOf(second) > -1) {
                return arr[i].substring(arr[i].indexOf("=") + 1);
                }
            }
            return null;
        }
        return null;
    }
}
        
var loginUser;
//��ȡ�û�Cookies ����loginUser����
function createUser(){
    loginUser = new Object();
    loginUser.UserId = ppkRead("UserInfo", "UserId");//�û�ID 
loginUser.UserName = decodeURIComponent(ppkRead("UserInfo", "UserName"));//�û���
loginUser.Token = ppkRead("UserInfo", "Token");//���ƺ�
loginUser.Security = ppkRead("UserInfo", "Security");//���ܴ�
if(loginUser.UserName == null || loginUser.UserName == '' || loginUser.UserName == 'null'){
    	return false;
    } else {
    	return true;
    }
}
    //�������ֺ͵�¼��
var isIe=(document.all)?true:false;

function showMessageBox(content, src){
    closeWindow();
    var $win = $(window),
    bg = '<div id="back" style="position:absolute;top:0;left:0;background:#000;filter:alpha(opacity=20);-moz-opacity:0.2;opacity: 0.2;z-index:999;"><iframe style="position:absolute;top:0;left:0;width:98%;height:100%;filter:alpha(opacity=0);"></iframe></div>'
    var box = '<div id="mesWindow" style="position:absolute;width:358px;">' + "<input type='button' onclick='closeWindow();' style='background:none;text-decoration:none;border:0 none;cursor:pointer;top:10px;right:5px;position:absolute;font-weight:700;color:#FFF;width:40px;height:17px;filter:alpha(opacity=0);-moz-opacity:0;opacity: 0' title='�ر�' value='�ر�' /><div id='mesWindowContent'></div>";
    var B = $(box).appendTo('body'), BG = $(bg).appendTo('body');
    BG.height($("body").height()).width($win.width()), C = $(content).appendTo($("#mesWindowContent"));
    $("#log_pop_box").attr("src", src);
    var l = ($win.width() - B.width()) / 2,
    t = $win.scrollTop() + $win.height()/2 - 260;
    B.css({
        left : l,
        top : t < 50 ? 50 : t,
        zIndex : 9999
    });
}

//�ñ��������䰵
function showBackground(obj,endInt)
{
    if(isIe)
    {
        obj.filters.alpha.opacity+=5;
        if(obj.filters.alpha.opacity<endInt)
        {
            setTimeout(function(){showBackground(obj,endInt)},5);
        }
    }else{
        var al=parseFloat(obj.style.opacity);al+=0.05;
        obj.style.opacity=al;
        if(al<(endInt/100))
        {setTimeout(function(){showBackground(obj,endInt)},5);}
    }
}

//�رմ���
function closeWindow()
{
    if(document.getElementById('back')!=null)
    {
        document.getElementById('back').parentNode.removeChild(document.getElementById('back'));
    }
    if(document.getElementById('mesWindow')!=null)
    {
        document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
    }

}

//���Ե���
function testMessageBox(ev,backurl){
	backurl=encodeURIComponent(encodeURI(encodeURI(encodeURI(backurl))));
    //messContent="<iframe frameborder='no' id='log_pop_box' allowtransparency='true' style='width: 358px;height: 600px; overflow: hidden;background:none;' scrolling='no'></iframe>";

    src = 'http://passport.111.com.cn/sso/pop.action?ReturnUrl=' +backurl;
    
    popLogin(backurl)
   // showMessageBox(messContent, src);
}

