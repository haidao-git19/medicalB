if (!window.getCookie) {
    function setCookie (cookieName, cookieValue, nDays) {
        var today = new Date;
        var expire = new Date;
        if (nDays) {
            expire.setTime(today.getTime() + 3600000 * 24 * nDays);
        }
        document.cookie = cookieName + '=' + escape(cookieValue) + 
        (nDays ? ('; expires=' + expire.toGMTString()) : '') + '; path=/; domain=wealink.com';
    }
	
    function getCookie (name) {
        var nameEQ = name + '=';
        var ca = document.cookie.replace(/\s/g, '').split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            if (c.indexOf(nameEQ) == 0)
                return unescape(c.substring(nameEQ.length, c.length).replace(/\+/g, ' '));
        }
        return null;
    }
}
/*
 * JavaScript弹出层插件()
 * @author：hai.qin
 * create at 2011-12-26
 * @参数:
 *    width:	//load页面的宽度
 *    height:	//load页面的长度
 *    url:	//load页面的url
 *    html:	//load直接的HTML代码
 *    注：不能同时load url和html代码
 */
jQuery.fn.cm_dialog = function(options){

    var options = options || {};
    var msgDivWidth = options.width || '680px';
    var msgDivHeight = options.height || '450px';
    var msgDivTop = options.top || '100px';
    var msgDivLeft = options.left || '200px';
    var endOpacityVal = options.endOpacityVal || 0.5 ;
    var msgDivUrl = options.url || '';
    var msgDivHtml = options.html || '';

    if(msgDivUrl != '' && msgDivHtml != '') {
        return false;
    }

    var popupOver     = options.popupOver === undefined ? false:options.popupOver;
    var popupWindowId = options.winId || 'mesWindow';
    var maskId        = options.maskId || 'back';
    var closeOnMaskClick = options.closeOnMaskClick == undefined ? true : options.closeOnMaskClick;
    //点击关闭时，是否关闭页面||是否跳转到首页
    var closePageFlag = options.closePageFlag == undefined ? false : options.closePageFlag;

    $('#'+popupWindowId).remove();
    if(!popupOver)    $('#'+maskId).remove();

    $('input.jptrigger').blur();
    var pageSize = GetPageSize();
    var bodyHeight = pageSize.PageH;

    //----创建遮罩层Div----
    if(popupOver == false){
        var shadowDivObj = $('<div id="'+maskId+'"></div>');
        shadowDivObj.css({
            width: cW(),
            height: bodyHeight,
            top:'0px',
            left:'0px',
            background:"#666",
            position:'absolute',
            zIndex:'100000',
            opacity:'0'
        });
        $(document.body).prepend(shadowDivObj);
        var maskObj        = $('#'+maskId);
        showMask(endOpacityVal);
        $(window).resize(resizeMask).scroll(resizeMask);

        if(closeOnMaskClick)
        maskObj.click(function(){close();$('.alert-window').remove();});
    }

    pageSize = GetPageSize();
    bodyHeight = pageSize.PageH;
    
    //----创建显示层Div----
    $(document.body).append($('<div class="alert-window" id="'+popupWindowId+'" ></div>'));
    var popupWindowObj = $('#'+popupWindowId);
    var loading_html = '';
    loading_html += '<div style="width:385px;margin: 0px auto 0px auto;border: 5px solid #dadada;background-color: #FFFFFF;">';
    loading_html += '<div style="float:left;width:345px;padding: 20px;">';
    loading_html += '<div style="background: url(http://static.wealink.com/v3/images/img_loading.gif) no-repeat center;padding:45px;font-size: 12px;line-height: 22px;width:25px;height:25px;margin: 0px auto 0px auto;vertical-align: middle;"></div>';
    loading_html += '</div>';
    loading_html += '<div style="clear:both;"></div>';
    loading_html += '</div>';

    popupWindowObj.html(loading_html);
    popupWindowObj.css({
        width: msgDivWidth,
        height: msgDivHeight,
        left: '50%',
        top: getPopupTop()+'px',
        marginLeft: -parseInt(msgDivWidth)/2,
        position:'absolute',
        zIndex:'100001'
    });

    if(msgDivUrl != '' && msgDivHtml == '') {
        $.get(msgDivUrl,{},function(data){
            popupWinInit(data);
        });
    }
    
    if(msgDivUrl == '' && msgDivHtml != '') {
        popupWinInit(msgDivHtml);
    }
    var popupHeight = 0;
    function popupWinInit(html){
        var popupObj = $('#'+popupWindowId);
        popupObj.html('').append(html)
            .find('*').click(function(e){e.stopPropagation()}).end()
            .click(function(){if(closeOnMaskClick && !popupOver) close();})
            .find('.popClose,.close,#cm_closeMsg, #cm_closeMsg_cancel').bind('click',close).end();
        popupHeight = popupObj.height();
        resizeMask();
        //add by tony.lu
        b = options.callback;
        if (typeof b == "function") {b();}
    }

    //让背景渐渐变暗
    function showMask(endOpacityVal) {
        var num = parseFloat(maskObj.css('opacity')) + 0.05;
        maskObj.css('opacity',num);
        if(maskObj.css('opacity') < endOpacityVal){
            setTimeout(function(){
                showMask(endOpacityVal)
            },5);
        }
    }

   // 改变遮罩大小
    function resizeMask(){
        if(maskObj)
        maskObj.css({
            width: cW(),
            height: cH(),
            top: (st() + 'px'),
            left: (sl() + 'px')
        });

        popupWindowObj.css({
            top: getPopupTop() + 'px'
        });
    }

    function close(){
        popupWindowObj.remove();
        if(!popupOver)
            maskObj.remove();
        if(closePageFlag){
            window.location.href = "http://www.wealink.com/";
            //closePage();
        }
        return false;
    }

    function getPopupTop(){
        var msgTop     = parseInt(msgDivTop);
        var currentTop = msgTop;
        var scrollTop  = st();
        var popupTop   = msgTop + scrollTop;

        if(popupWindowObj.length){
            var position = popupWindowObj.position();
            currentTop = position.top;
        }

        if(popupTop + popupHeight > bodyHeight){
            return bodyHeight - msgTop - popupHeight;
        }

        if(currentTop - scrollTop > msgTop){
            return msgTop + st();
        }

        if( currentTop + popupHeight > scrollTop && scrollTop <= bodyHeight){
            return currentTop;
        }
        return popupTop;
    }

    function closePage(){
        //FF中需要修改配置window.close方法才能有作用，为了不需要用户去手动修改，所以用一个空白页面显示并且让后退按钮失效
        //Opera浏览器旧版本(小于等于12.16版本)内核是Presto，window.close方法有作用，但页面不是关闭只是跳转到空白页面，后退按钮有效，也需要特殊处理
        var userAgent = navigator.userAgent;
        if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Presto") != -1) {
            window.location.replace("about:blank");
        } else {
            window.opener = null;
            window.open("", "_self");
            window.close();
        }
    }
}

// ico 1正确，2警告，3错误
var ye_msg = {
    Counter: 0,
    AlertWindowPrefix: 'WL_ALERT_',
    AlertWindowId: '',
    MaskId:'WL_ALERT_MASK',

    /**
     *
     * @param e  提示信息内容
     * @param d  显示时间
     * @param c  类型：1正确，2警告，3错误
     * @param b  回调函数
     * @param a  是否显示遮罩，默认显示
     */
    open: function(e, d, c, b, a){//(e:html,d:timer,c:type(int),b:callback,a: show mask)
        if (e.indexOf('<script')!=-1) {
            e='正在载入中...'+e;
        }

        var withMask = a === undefined ? true:a;

        this.Counter++;
        this.AlertWindowId = this.AlertWindowPrefix + this.Counter;

        //遮罩层；
        if(withMask){
            var shadowDivObj = $('<div id="'+this.MaskId+'"></div>');
            shadowDivObj.css({
                width: cW(),
                height: cH(),
                background:"#666",
                position:'absolute',
                top: st() + 'px',
                left: sl() + 'px',
                zIndex:'200000',
                opacity:'0.5'
            });
            $('body').append(shadowDivObj);
            var maskObj        = $('#'+this.MaskId);
            showMask(0.5);
            maskObj.click(function(){close();$('.alert-window').remove();});
            $(window).resize(resizeMask).scroll(resizeMask);
        }

        // 消息左侧图标样式；
        var iconClass = '';
        switch(c){
            case 2:
                iconClass = 'resume-confirm-wrong';
                break;
            case 3:
                iconClass = 'resume-confirm-error';
                break;
            default:
                iconClass = 'resume-confirm-ok';
                break;
        }
        //样式定义
        var _bd_img_url = 'http://static.wealink.com/v4/img/';
        var popupHtml = '<style>\
                            .resume-tc { border: 1px solid #fff; background: #fff; }\
                            .resume-tc-head { height: 47px; background: #e7eaee; position: relative; }\
                            .resume-tc-head h3 { line-height: 47px; padding-left: 40px; color: #25af60; font-size: 14px; font-weight: bold; }\
                            .resume-tc-head .close { position: absolute; width: 16px; height: 16px; top: 16px; right: 16px; background: url('+_bd_img_url+'icon.png) no-repeat -334px -62px; }\
                            .resume-tc-head .close:hover { background: url('+_bd_img_url+'icon.png) no-repeat -334px -84px; }\
                            .resume-tc-body { padding: 0 40px; }\
                            .resume-tc-action { padding: 20px 80px; }\
                            .resume-tc-action button { border: 0; margin: 0 16px 0 0; padding: 10px 20px; line-height: 1; font-size: 14px; font-weight: bold; font-family: "Microsoft Yahei"; cursor: pointer; color: #666; background: #eee; }\
                            .resume-tc-action button.grn { color: #fff; background: #6ed373; }\
                            .resume-tc-action2 { padding: 20px; text-align: center; }\
                            .resume-tc-action2 button { border: 0; margin: 0; margin-right: 10px; padding: 10px 20px; line-height: 1; font-size: 14px; font-weight: bold; font-family: "Microsoft Yahei"; cursor: pointer; color: #666; background: #eee; }\
                            .resume-tc-action2 button.grn { color: #fff; background: #6ed373; }\
                            .resume-tc-action2 button.org { color: #fff; background: #ff7300; }\
                            .resume-confirm-ok { width: 46px; height: 32px; margin: 35px 0 35px 210px; background: url('+_bd_img_url+'icon.png) no-repeat -214px -121px; }\
                            .resume-confirm-error { width: 46px; height: 32px; margin: 35px 0 35px 210px; background: url('+_bd_img_url+'icon.png) no-repeat -214px -167px; }\
                            .resume-confirm-wrong{width: 46px;height: 35px;margin: 35px 0 35px 210px;background: url('+_bd_img_url+'icon.png) no-repeat -264px -164px;}\
                            .resume-tc-cnt { text-align: center; color: #333; font-size: 14px; font-weight: bold; padding: 10px 40px; }\
                            .resume-tc-cnt strong.org { color: #ff7300; font-weight: bold; font-size: 14px; }\
                        </style>';
        // 内容浮层；
        popupHtml += '<div class="resume-tc alert-window" id="'+this.AlertWindowId+'" style="width:480px;height:300px;">\
                        <div class="resume-tc-head">\
                            <h3>提示信息</h3><a href="javascript:;" class="close popClose" id="cm_closeMsg"></a>\
                        </div>\
                        <div class="popIcon '+iconClass+'"></div>\
                        <p class="resume-tc-cnt">'+e+'</p>\
                    </div>';
        $('body').append(popupHtml);

        var alertWindowObj = $('#'+this.AlertWindowId);
        $(alertWindowObj).css({
            width: 480,
            height: 300,
            left: '50%',
            top: 200+$(window).scrollTop()+'px',
            marginLeft: -210+'px',
            position:'absolute',
            zIndex:'200001'
        }).click(function(){
                close();
        }).find('*').click(function(e){
                e.stopPropagation();
            });

        // 关闭按钮；
        $('#'+this.AlertWindowId).find('.close, #cm_closeMsg, .popClose').bind('click',close);

        // 自动关闭
        if (d != undefined && d != 0) {
            // 类型为成功的，全局强制1秒自动关闭，其他情况根据参数判断关闭时间；
            d = c==1 ? 1 : d;

            setTimeout(function(){
                close();

            }, d * 1000);
        }

        //让背景渐渐变暗
        function showMask(endOpacityVal) {
            var num = parseFloat(maskObj.css('opacity')) + 0.05;
            maskObj.css('opacity',num);
            if(maskObj.css('opacity') < endOpacityVal){
                setTimeout(function(){
                    showMask(endOpacityVal)
                },5);
            }
        }

        // 关闭浮层；
        function close(){
            alertWindowObj.remove();
            if(withMask==true){
                maskObj.remove();
            }

            // callback;
            if (typeof b == "function") {b();}
        }

        // 改变遮罩大小、浮层位置
        function resizeMask(){
            if(maskObj)
            maskObj.css({
                width: cW(),
                height: cH(),
                top: (st() + 'px'),
                left: (sl() + 'px')
            });


            alertWindowObj.css({
                top : 200 + st() +'px'
            });
        }
    }
};

function cH() {
    return document.documentElement.clientHeight;//屏幕的高度
}
function cW() {
    return document.documentElement.clientWidth;//屏幕的宽度
}
function sl() {
    return document.documentElement.scrollLeft;//滚动条距左边的距离
}
function st() {
    //document.body.scrollTop fixed chrome
    return document.documentElement.scrollTop || document.body.scrollTop;//滚动条距顶部的距离
}

function GetPageSize() {
    var scrW, scrH;
    if(window.innerHeight && window.scrollMaxY) {
        // Mozilla
        scrW = window.innerWidth + window.scrollMaxX;
        scrH = window.innerHeight + window.scrollMaxY;
    } else if(document.body.scrollHeight > document.body.offsetHeight){
        // all but IE Mac
        scrW = document.body.scrollWidth;
        scrH = document.body.scrollHeight;
    } else if(document.body) { // IE Mac
        scrW = document.body.offsetWidth;
        scrH = document.body.offsetHeight;
    }

    var winW, winH;
    if(window.innerHeight) { // all except IE
        winW = window.innerWidth;
        winH = window.innerHeight;
    } else if (document.documentElement
        && document.documentElement.clientHeight) {
        // IE 6 Strict Mode
        winW = document.documentElement.clientWidth;
        winH = document.documentElement.clientHeight;
    } else if (document.body) { // other
        winW = document.body.clientWidth;
        winH = document.body.clientHeight;
    }

    // for small pages with total size less then the viewport
    var pageW = (scrW<winW) ? winW : scrW;
    var pageH = (scrH<winH) ? winH : scrH;

    return {PageW:pageW, PageH:pageH, WinW:winW, WinH:winH};
}

var bd_passport_url = 'http://www.wealink.com/secure/';
function loginFirst(to) {
    var option = {      
        'url':bd_passport_url+'login/login_first_dialog/?to='+to+'&now='+new Date().getTime(),
		'width':406
    };
    $("doc").cm_dialog(option); 
}
function login_norefresh_first() {
    var norefresh_cid = $('#norefresh_cid').val();
    var option = {      
        'url':bd_passport_url+'login/login_first_dialog/?cid='+norefresh_cid+'&login_style=norefresh&now='+new Date().getTime(),
		'width':406
    };
    $("doc").cm_dialog(option); 
}
function loginFirstRfss() {
    var option = {      
        'url':bd_passport_url+'login/login_first_dialog/?now='+new Date().getTime() + '&rfss=rfss',
		'width':406
    };
    $("doc").cm_dialog(option); 
}

function canNotDoItSelf() {
    var showTime = 2;
    ye_msg.open('不能对自己执行该操作！',showTime,4);
}