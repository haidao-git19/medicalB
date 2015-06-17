<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.netbull.shop.common.config.ConfigLoadUtil" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   String srcload=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
   request.setAttribute("srcload",srcload);
 %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>找科室 - 医保宝</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<link href='${ctx }/resources/third-party/css/global-v5.css' rel='stylesheet' type='text/css' />
<link href='${ctx }/resources/third-party/css/hospital.css' rel='stylesheet' type='text/css' />
        
<jsp:include page="/WEB-INF/jsp/web/common.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/web/head.jsp"></jsp:include>
</head>
<body>


<!--头部开始-->

<div class="top-wrap">
    <div class="top-body clearfix">
      <ul class="top-login-status clearfix" id="top-login-status">
        <li>您好，欢迎来到医保宝网</li>
        <li class="service-phone"><a href="login.html">请登录</a> <a href="reg.html">免费注册</a></li>
      </ul>
      <ul class="media-link clearfix">
        <li class="my-account"><a href="/contact.html" target="_blank">联系我们</a></li>
        <li><a href="#" onClick="addFavorite('http://www.cn','医保宝');">加入收藏</a></li>
      </ul>
    </div>
  </div>

<!--头部结束-->
<div class="head"> 
  <div class="ucenter">
    <div> <a rel="nofollow" class="clr-08c" href="user/home/"></a><a rel="nofollow" id="ucenter-message" class="ucenter-message ucenter-message-no" href="javascript:void(0);">消息</a><a rel="nofollow" class="ucenter-tc" href="secure/logout/">退出</a>
      <div class="ucenter-message-list" style="display: none;"> <span class="ucenter-message-active">消息</span>
        <ul>
          <li><a href="user/renmai/index/?type=2" target="_blank"><span id="notify_count_001" class="f-r clr-org"></span>查看粉丝</a></li>
          <li><a href="user/message/index/" target="_blank"><span id="notify_count_002" class="f-r clr-org"></span>查看私信</a></li>
          <li><a href="user/comment/index/" target="_blank"><span id="notify_count_004" class="f-r clr-org"></span>查看评价</a></li>
          <li><a href="user/company/my_company_comment/" target="_blank"><span id="notify_count_006" class="f-r clr-org"></span>查看回复</a></li>
        </ul>
      </div>
      <div class="ucenter-message-new" style="display: none;"> <a href="javascript:void(0);" class="ucenter-message-new-close"><i class="icon-msg-close"></i></a>
        <ul id="ucenter-message-new-list">
        </ul>
      </div>
    </div>
  </div>
</div>

<div class="main">
    <div class="top-ch-loc">
        当前位置：
                <a class="clr-08c" href="#" target="_blank">医保宝</a><span class="clr-ccc"> &gt; 		<span class="clr-999">找科室</span>
    </div>
    
  <div class="comp-detail-left">
  
<!--各市医院列表-->  
<c:forEach items="${sectionFilter.filter}" var="filter" varStatus="index">
<strong>${filter.attrname}</strong>
<ul>
	<c:forEach items="${filter.list}" var="section">
		<li><a target="_blank" class="black_link" href="/jibing/yigan/daifu.htm">${section.attrname}</a></li>
	</c:forEach>
	<li><a class="blue2" target="_blank" href="/jibing/neike/list.htm">更多&gt;&gt;</a></li>
</ul>
<div class="clear"></div>
</c:forEach>

<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">内科</span>
          </div>
          <div class="find-departments">
<ul>
<ul>
  <li><a href="#" title="1469家推荐医院,2600位大夫提供在线咨询" class="black_link">心血管内科</a></li>
  <li><a href="#" title="1398家推荐医院,2028位大夫提供在线咨询" class="black_link">神经内科</a></li>
  <li><a href="#" title="1305家推荐医院,1377位大夫提供在线咨询" class="black_link">消化内科</a></li>
  <li><a href="#" title="1121家推荐医院,1648位大夫提供在线咨询" class="black_link">内分泌科</a></li>
  <li><a href="#" title="404家推荐医院,695位大夫提供在线咨询" class="black_link">免疫科</a></li>
  <li><a href="#" title="1258家推荐医院,1199位大夫提供在线咨询" class="black_link">呼吸科</a></li>
  <li><a href="#" title="980家推荐医院,897位大夫提供在线咨询" class="black_link">肾病内科</a></li>
  <li><a href="#" title="774家推荐医院,824位大夫提供在线咨询" class="black_link">血液科</a></li>
  <li><a href="#" title="617家推荐医院,619位大夫提供在线咨询" class="black_link">感染内科</a></li>
  <li><a href="#" title="51家推荐医院,65位大夫提供在线咨询" class="black_link">过敏反应科</a></li>
</ul></ul>

          </div>

</div>



<!--各市医院列表-->  
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">外科</span>
          </div>
          <div class="find-departments">
<ul>
<ul>
  <li><a href="#" title="1469家推荐医院,2600位大夫提供在线咨询" class="black_link">心血管内科</a></li>
  <li><a href="#" title="1398家推荐医院,2028位大夫提供在线咨询" class="black_link">神经内科</a></li>
  <li><a href="#" title="1305家推荐医院,1377位大夫提供在线咨询" class="black_link">消化内科</a></li>
  <li><a href="#" title="1121家推荐医院,1648位大夫提供在线咨询" class="black_link">内分泌科</a></li>
  <li><a href="#" title="404家推荐医院,695位大夫提供在线咨询" class="black_link">免疫科</a></li>
  <li><a href="#" title="1258家推荐医院,1199位大夫提供在线咨询" class="black_link">呼吸科</a></li>
  <li><a href="#" title="980家推荐医院,897位大夫提供在线咨询" class="black_link">肾病内科</a></li>
  <li><a href="#" title="774家推荐医院,824位大夫提供在线咨询" class="black_link">血液科</a></li>
  <li><a href="#" title="617家推荐医院,619位大夫提供在线咨询" class="black_link">感染内科</a></li>
  <li><a href="#" title="51家推荐医院,65位大夫提供在线咨询" class="black_link">过敏反应科</a></li>
</ul></ul>


          </div>

</div>




</div><!--comp-detail-left end-->



<div class="wealink-right">

<div class="wlr-box">
        <div class="wlr-box-hd">
            <div class="wlr-box-more">
            </div>
            <h3 class="clr-grn">科室</h3>
        </div>
        <div class="keshi-box-bd">


        <ul class="com-right-num">
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">内科 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">外科 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">口腔科学 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">内科 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">外科 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">口腔科学 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">内科 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">外科 </span></a></li>
          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">口腔科学 </span></a></li>
        </ul>




            
        </div>
    </div>

</div><!--wealink-right-->







</div><!--main end-->














<div class="blk20 clear"></div>
<div class="foot">
  <p class="company-job-index"> 提示：任何关于疾病的建议都不能替代执业医师的面对面诊断。所有门诊时间仅供参考，最终以医院当日公布为准。网友、医生言论仅代表其个人观点，不代表本站同意其说法，请谨慎参阅，本站不承担由此引起的法律责任。 </p>
  <p>
  				<a href="#" target="_blank" title="关于我们" rel="nofollow"> 关于我们 </a><i>|</i>
				<a href="#" target="_blank" title="联系我们" rel="nofollow"> 联系我们 </a><i>|</i>
				<a href="#" target="_blank" title="帮助中心" rel="nofollow"> 帮助中心 </a><i>|</i>
				<a href="#" target="_blank" title="友情链接" rel="nofollow"> 友情链接 </a><i>|</i>
				<a href="#" target="_blank" title="网站地图"> 网站地图 </a>
				
			</p>
			<p class="copyright">
				Copyright<span class="copySymbol">&copy;</span>2015 医保宝
				<a href="http://www.miibeian.gov.cn/" target="_blank" rel="nofollow" class="grayC">皖ICP备000000号</a>
			</p>

 
  </div>
</div>
<style type="text/css">
            .ajax-progress{z-index: 2000}
            .ajax-mask{position: fixed;top: 0;right: 0;bottom: 0;left: 0; z-index: 1000; background-color: #000000}
</style>
        
</body>
</html>