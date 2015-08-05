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
	<title>在线咨询 - 医保宝</title>
	<meta http-equiv='Cache-Control' content='no-siteapp' />
	<jsp:include page="/WEB-INF/jsp/web/common.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/web/head.jsp"></jsp:include>
	<link href='${ctx }/resources/third-party/css/global-v5.css' rel='stylesheet' type='text/css' />
	<link href='${ctx }/resources/third-party/css/hospital.css' rel='stylesheet' type='text/css' />
</head>

<body>
<!--头部开始-->
<!-- <div class="top-wrap">
    <div class="top-body clearfix">
      <ul class="top-login-status clearfix" id="top-login-status">
        <li>您好，欢迎来到医保宝网</li>
        <li class="service-phone"><a href="http://180.153.55.124:8091/sso/login">请登录</a> <a href="http://180.153.55.124:8091/sso/login">免费注册</a></li>
      </ul>
      <ul class="media-link clearfix">
      <li><a rel="nofollow" href="#" class="gomhome png">手机版</a></li>
        <li class="my-account"><a href="#" target="_blank">联系我们</a></li>
        <li><a href="#" onClick="addFavorite('http://www.cn','医保宝');">加入收藏</a></li>
      </ul>
    </div>
  </div> -->
<!--头部结束-->
	<c:if test="${not empty doctor}">
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
	</c:if>
<div class="main">
	<c:if test="${not empty doctor}">
	    <div class="top-ch-loc">
	       	当前位置：
	        <a class="clr-08c" href="#" target="_blank">医保宝</a><span class="clr-ccc"> &gt; </span>			<span class="clr-999">网上咨询</span>
	    </div>
	</c:if>
<!--comp-detail-left end-->
<div class="comp-detail-left">
	<c:if test="${not empty doctor}">
		<div class="person-file-card cf" >
		  <div class="card-photo"> 
		  	<c:choose>
		  		<c:when test="${not empty doctor.avatar}">
		  			<img src="${doctor.avatar}" title="${doctor.realName}" alt="${doctor.realName}">
		  		</c:when>
		  		<c:otherwise>
		  			<img src="${ctx }/resources/third-party/images/nouimg.jpg" title="${doctor.realName}" alt="${doctor.realName}">
		  		</c:otherwise>
		  	</c:choose>
		    <p>一天前来过</p>
		  </div>
		  <div class="card-words">
		    <ul class="card-info">
		      <li> <a title="${doctor.realName}" href="${ctx}/anon/web/doctor?hospitalID=${doctor.hospitalID}&sectionID=${doctor.sectionID}&doctorID=${doctor.doctorID}" class=" clr-08c fnt-14">${doctor.realName}</a><span class="city">${doctor.hospitalName}-${doctor.sectionName} ${doctor.doctorLevel}</span> </li>
		      <li class="clr-666">擅长：${doctor.skill} </li>
		      <li class="clr-666">简介：${doctor.experience}</li>
		    </ul>
		  </div>
		</div>
	</c:if>
<!--选择服务内容-->
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">服务流程：</span>
          </div>
<div class="process">
<!---->
<div class="process0">
<img src="${ctx }/resources/third-party/images/process.jpg">
</div>

<div class="process1">
<h1>1、预约开始</h1>
<p class="txt">
用户通过扫描右边的二维码下载医保宝APP，或者通过http://www.*******.链接进入APP下载页面，下载后安装软件。
</p>
<img src="${ctx }/resources/third-party/images/00.jpg" width="140" height="140">
</div>

<div class="process2">
<h1>2、选择医生</h1>
<p class="txt">
用户根据医院或科室找到希望预约的医生。
</p>
<ul>
<li><img src="${ctx }/resources/third-party/images/01.jpg"></li>
<li><img src="${ctx }/resources/third-party/images/02.jpg"></li>
<li><img src="${ctx }/resources/third-party/images/03.jpg"></li>
<li><img src="${ctx }/resources/third-party/images/04.jpg"></li>
</ul>

</div><!--process2-->




<div class="process3">
<h1>3、医生值班表</h1>
<p class="txt">
用户根据医院或科室找到希望预约的医生。
</p>
<ul>
<li><img src="${ctx }/resources/third-party/images/05.jpg"></li>
</ul>

</div><!--process3-->


<div class="process4">
<h1>4、电子病历表</h1>
<p class="txt">
用户根据医院或科室找到希望预约的医生。
</p>
<ul>
<li><img src="${ctx }/resources/third-party/images/06.jpg"></li>
<li><img src="${ctx }/resources/third-party/images/07.jpg"></li>
</ul>

</div><!--process4-->



<div class="process5">
<h1>5、支付咨询费</h1>
<p class="txt">
用户根据医院或科室找到希望预约的医生。
</p>
<ul>
<li><img src="${ctx }/resources/third-party/images/08.jpg"></li>
<li><img src="${ctx }/resources/third-party/images/09.jpg"></li>
</ul>

</div><!--process5-->


<div class="process6">
<h1>6、预约并与医生本人通话</h1>
<p class="txt">
用户根据医院或科室找到希望预约的医生。
</p>
<ul>
<li><img src="${ctx }/resources/third-party/images/10.jpg"></li>
</ul>

</div><!--process6-->


</div><!--process-->
       
       
       
       
       
       
       
       
       

</div>







</div><!--comp-detail-left end-->



<div class="wealink-right">



<!---->
<div class="wlr-pv">共 <b class="clr-org fnt-b">1240</b> 次访问</div>






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