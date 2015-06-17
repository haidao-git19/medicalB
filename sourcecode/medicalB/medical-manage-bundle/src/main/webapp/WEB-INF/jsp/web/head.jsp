<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
//var urlstr = location.href;
//alert((urlstr + '/').indexOf($(this).attr('href')));
//var urlstatus=false;
//$("#nav").each(function () {
//	alert($(this).html());
//  if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!='') {
//    $(this).addClass('current'); 
//    urlstatus = true;
//  } else {
//    $(this).removeClass('current');
//  }
//});
//alert(urlstatus);
//alert($("#nav").eq(0).html());
//if (!urlstatus) {$("#nav ul").eq(0).addClass('current'); }
</script>

<!--头部开始-->

<div class="top-wrap">
    <div class="top-body clearfix">
      <ul class="top-login-status clearfix" id="top-login-status">
        <li>您好，欢迎来到医保宝网</li>
        <li class="service-phone"><a href="login.html">请登录</a> <a href="${ctx }/web/register">免费注册</a></li>
      </ul>
      <ul class="media-link clearfix">
        <li class="my-account"><a href="/contact.html" target="_blank">联系我们</a></li>
        <li><a href="#" onClick="addFavorite('http://www.cn','医保宝');">加入收藏</a></li>
        

      </ul>
    </div>
  </div>

<!--头部结束-->

<div class="head"> 
  <div class="logo"><a href="/"><img src="${ctx }/resources/web/img/logo.gif" width="311" height="48" alt="网首页"></a></div>
  <div class="search">
    <div class="searchFillIn" id="header_tab_1">
      <form method="get" action="/zhiwei/" id="topSearchForm">
        <div class="dropdown search-mod"> 
          <div class="dropdown-toggle">
            <div class="dropdown-default">综合</div>
          </div>
          <ul class="dropdown-menu" style="display: none;">
            <li placeholder-text="请输入关键字" url="/zhiwei/" class="current">综合</li>
            <li placeholder-text="请输入医院关键字" url="/bole/" class="">医院</li>
            <li placeholder-text="请输入科室关键字" url="/renmai/" class="">科室</li>
            <li placeholder-text="请输入专家关键字" url="/gongsi/" class="">专家</li>
            <li placeholder-text="请输入疾病名称关键字" url="/mianjing/" class="">疾病</li>
            <li placeholder-text="请输入咨询信息关键字" url="/gongzi/" class="">咨询</li>
          </ul>
        </div>
        <div class="search-ipt">
          <input name="kw" value="" id="search_position_key" type="text" placeholder-text="请输入关键字">
        </div>
        <div class="search-btn">
          <button type="submit">搜 索</button>
        </div>
      </form>
    </div>
  </div>
  <div class="clear"></div>
  <div class="nav" > <a rel="nofollow" href="/mobile.php" class="gomhome png">手机版</a>
    <ul id="nav">
      <li  class=""><a href="${ctx }/web">首页</a></li>
      <li ><a href="${ctx }/web/hospital">按医院找</a></li>
      <li ><a href="#">按科室找</a></li>
      <li ><a href="#/">网络咨询</a></li>
      <li ><a href="#">电话咨询</a></li>
      <li ><a href="#">转约预诊</a></li>
      <li ><a href="#">看病攻略</a></li>
    </ul>
    <div class="nav-cj2"></div>
    <div class="nav-cj3"></div>
  </div>
  <div class="ucenter">
    <div> <a rel="nofollow" class="clr-08c" href="http://www.wealink.com/user/home/"></a><a rel="nofollow" id="ucenter-message" class="ucenter-message ucenter-message-no" href="javascript:void(0);">消息</a><a rel="nofollow" class="ucenter-tc" href="http://www.wealink.com/secure/logout/">退出</a>
      <div class="ucenter-message-list" style="display: none;"> <span class="ucenter-message-active">消息</span>
        <ul>
          <li><a href="http://www.wealink.com/user/renmai/index/?type=2" target="_blank"><span id="notify_count_001" class="f-r clr-org"></span>查看粉丝</a></li>
          <li><a href="http://www.wealink.com/user/message/index/" target="_blank"><span id="notify_count_002" class="f-r clr-org"></span>查看私信</a></li>
          <li><a href="http://www.wealink.com/user/comment/index/" target="_blank"><span id="notify_count_004" class="f-r clr-org"></span>查看评价</a></li>
          <li><a href="http://www.wealink.com/user/company/my_company_comment/" target="_blank"><span id="notify_count_006" class="f-r clr-org"></span>查看回复</a></li>
        </ul>
      </div>
      <div class="ucenter-message-new" style="display: none;"> <a href="javascript:void(0);" class="ucenter-message-new-close"><i class="icon-msg-close"></i></a>
        <ul id="ucenter-message-new-list">
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="blk20 clear"></div>

