<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>医保宝_网上药店领导者_网上购买药品的放心药房网</title>
	<script src="${ctx }/resources/third-party/js/index/indexList.js?ver=${version}" type="text/javascript"></script>
</head>
<body>
<!--header-->
<%----%>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>

<div class="wrap main_body clearfix">
  <div class="main-l">
    <div class="tsSlide" >
      <div class="pro-switch">
        <div class="slider">
          <div class="flexslider" style="display: none;">
            <ul class="slides">
              <li>
                <div class="img"><a href="{0}"><img src="{1}" height="300" width="710" alt="" /></a></div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <ul class="main_ul clearfix" style="display: none;">
      <li class="last">
        <h4><a title="{0}" target="_blank" href="{5}">{0}</a></h4>
        <div class="pic"> <a target="_blank" href="{5}">{1}</a> </div>
        <div class="title"> <a target="_blank" title="{2}" href="{5}">{2}</a> </div>
        <span class="price_l"><i>&#165;</i>{3}</span><span class="price_r"><i>&#165;</i><del>{4}</del></span> 
      </li>
    </ul>
  </div>
  <div class="main-r">
    <div id="doctor_pop" class="doctor_box">
      <div class="consult">
        <div class="c_b clearfix">
          <div class="consult_title" title="合作伙伴">合作伙伴</div>
          <div class="tel_box"> <i class="iconfont icon-phone"></i>
            <div class="tellnumber">400-007-0958</div>
          </div>
          <div id="doctor_slide" class="c_slide"> <a href="javascript:" class="ccPrev" data-type="prev"></a><a href="javascript:" class="ccNext" data-type="next"></a></div>
          <p id="doctor_a"><a rel="nofollow" data-rel="doctorSlide0" class="on" href="javascript:">&#8226;</a><a rel="nofollow" data-rel="doctorSlide1" class="" href="javascript:">&#8226;</a><a rel="nofollow" data-rel="doctorSlide2" class="" href="javascript:">&#8226;</a><a rel="nofollow" data-rel="doctorSlide3" class="" href="javascript:">&#8226;</a></p>
        </div>
        <div class="doctor_detail" id="doctor_detail">
          <ul id="doctorSlide0" style="display: none;-webkit-transition: -webkit-transform 250ms linear; transition: -webkit-transform 250ms linear; -webkit-backface-visibility: hidden; -webkit-transform: translateX(0%);">
            <li >
	            <a href="javascript:;"  target="_blank">
	            	<span class="imgsection">
	            		<img src="{0}">
	            	</span>
	            </a>
            </li>
          </ul>

          <ul id="doctorSlide3" style="display:none;-webkit-transition: -webkit-transform 250ms linear; transition: -webkit-transform 250ms linear; -webkit-transform: translateX(100%); -webkit-backface-visibility: hidden;">
            <li id="dc_1043"><a href="javascript:;" rel="nofollow" onclick="Detector.floatclick(4,1043);" target="_self"><span class="imgsection wufang"></span><b>吴药师</b></a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="newsbox clearfix" id="newsbox">
      <p class="tabs"> <a class="tb1 cur">优质服务</a> </p>
      <div class="news_list" style="display: block;">
        <div class="service"> <a class="c_0" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page2" rel="nofollow"><i class="icon-rongyu"></i><span>权威荣誉</span></a> <a class="c_1" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page3" rel="nofollow"><i class="icon-pinpai"></i><span>品牌授权</span></a> <a class="c_2" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page4" rel="nofollow"><i class="icon-zhengpin"></i><span>正品保险</span></a> <a class="c_3" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page5" rel="nofollow"><i class="icon-yaojian"></i><span>药监认证</span></a> <a class="c_4" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page6" rel="nofollow"><i class="icon-baibaoyou"></i><span>满百包邮</span></a> <a class="c_5" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page7" rel="nofollow"><i class="icon-30tui"></i><span>30天退换</span></a> <a class="c_6" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page8" rel="nofollow"><i class="icon-yingsibz"></i><span>隐私包装</span></a> <a class="c_7" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page9" rel="nofollow"><i class="icon-jinpaiyaoshi"></i><span>金牌药师</span></a> </div>
      </div>
    </div>
  </div>
  <div class="clearfix h_0"></div>
  <div class="wrap market market-bg clearfix">
    <div class="tabs2" id="tab_list"> <a class="tb1 cur" target="_self" href="javascript:" data-rel="tab_list1"><span style="font-style: italic;"></span>优惠活动<i></i></a> <a href="javascript:" class="prev" data-type="prev"></a><a href="javascript:" class="pause" data-type="pause"></a><a href="javascript:" class="next" data-type="next"></a></div>
    <div class="market_mian">
      <div class="market_box" id="tab_list1" style="display: block; transition: transform 250ms linear 0s; backface-visibility: hidden; transform: translateX(0%);">
        <ul style="display: none;" class="product_list">
          <li class="first">
            <p class="pic"><a href="{0}">{1}</a></p>
            <p class="name"><a href="{0}">{2}</a></p>
            <span class="price_l"><i>&#165;</i>{3}</span><span class="price_r"><i>&#165;</i><del>{4}</del></span></li>
        </ul>
      </div>
    </div>
    <div class="wrap tonglan mt10"><a href="http://www.111.com.cn/cmsPage/2015033103/zhengshi.html"><img onerror="$(this).hide()" src="${url}/resources/third-party/images/1427857341950184.jpg" title="" height="60" width="1190"></a></div>
    
    <div id="floorDiv" style="display: none;">
    	<div class="wrap floor clearfix">
	      <div class="floorNav">
	        <h3 class="floorName"><span>{0}F<a class="ml13">{1}</a></span></h3>
	      </div>
	      
	      <!-- 左侧导航 -->
			<div class="floor_l" id="fl1">
				{2}
			</div>
			<div class="floor_r" id="fr1">
		        <ul class="diseaseType">
		          {3}
		        </ul>
		        <div class="showPro">
		        	{4}
		        </div>
	      </div>
	    </div>
    </div>
    
  </div>
</div>

	<div id="floorDiv"></div>

	<!--footer start-->
<div class="y_foot">
  <div class="ft_friendlylink wrap">
    <ul class="fri_tit clearfix">
      <li class="cur">品牌旗舰店</li>
    </ul>
    <div class="fri_ct flagship_store clearfix"> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E6%258B%259C%25E8%2580%25B3" rel="nofollow" class="btm_logo01">拜耳</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E4%25B8%2587%25E8%2589%25BE%25E5%258F%25AF" rel="nofollow" class="btm_logo02">万艾可</a> <a href="http://www.111.com.cn/cmsPage/2014031001/index.html" rel="nofollow" class="btm_logo03">妇炎洁</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E9%25BB%2598%25E6%25B2%2599%25E4%25B8%259C&brandFilter=913206" rel="nofollow" class="btm_logo04">默沙东</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E4%25B8%25AD%25E7%25BE%258E%25E5%258F%25B2%25E5%2585%258B " rel="nofollow" class="btm_logo05">中美史克</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E6%2596%25BD%25E8%25B4%25B5%25E5%25AE%259D " rel="nofollow" class="btm_logo06">施贵宝</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E8%25AF%25BA%25E5%258D%258E" rel="nofollow" class="btm_logo07">诺华</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E9%2598%25BF%25E6%2596%25AF%25E5%2588%25A9%25E5%25BA%25B7 " rel="nofollow" class="btm_logo08">阿斯利康</a> <a href="http://www.111.com.cn/list/list.action?category=107&brandFilter=67" rel="nofollow" class="btm_logo09">罗氏</a> <a href="http://www.111.com.cn/cmsPage/2014081301/index.html" rel="nofollow" class="btm_logo10">北京同仁堂</a> <a href="http://www.111.com.cn/list/list.action?category=965326&brandFilter=3802" rel="nofollow" class="btm_logo11">冈本</a> <a href="http://www.111.com.cn/list/list.action?category=955306&amp;brandFilter=25" target="_blank" rel="nofollow" class="btm_logo12">杜蕾斯</a> <a href="http://www.111.com.cn/cmsPage/2014072801/index.html" rel="nofollow" class="btm_logo13">仙卡</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E4%25BA%2591%25E5%258D%2597%25E7%2599%25BD%25E8%258D%25AF&amp;brandFilter=6071" rel="nofollow" class="btm_logo14">云南白药</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E5%25A4%25AA%25E6%259E%2581&amp;brandFilter=904752" target="_blank" rel="nofollow" class="btm_logo15">太极集团</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E6%25A1%2582%25E9%25BE%2599" rel="nofollow" class="btm_logo16">桂龙药业</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E4%25B8%259C%25E9%2598%25BF&amp;brandFilter=911687" rel="nofollow" class="btm_logo17">东阿阿胶</a> <a href="http://www.111.com.cn/cmsPage/2014070901/index.html" target="_blank" rel="nofollow" class="btm_logo18">海昌</a> <a href="http://search.111.com.cn/search/search.action?keyWord=%25E6%25B1%25A4%25E8%2587%25A3%25E5%2580%258D%25E5%2581%25A5" rel="nofollow" class="btm_logo19">汤臣倍健</a> <a target="_blank" href="http://www.111.com.cn/cmsPage/2014070902/index.html" rel="nofollow" class="btm_logo20">海俪恩</a> </div>
  </div>
</div>
<div class="wrap ft_commit"> <a class="c_0" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page2"  rel="nofollow" target="_blank"><i class="icon-rongyu"></i><span>权威荣誉</span></a> <a class="c_1" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page3"  rel="nofollow" target="_blank"><i class="icon-pinpai"></i><span>品牌授权</span></a> <a class="c_2" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page4"  rel="nofollow" target="_blank"><i class="icon-zhengpin"></i><span>正品保险</span></a> <a class="c_3" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page5"  rel="nofollow" target="_blank"><i class="icon-yaojian"></i><span>药监认证</span></a> <a class="c_4" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page6"  rel="nofollow" target="_blank"><i class="icon-baibaoyou"></i><span>满百包邮</span></a> <a class="c_5" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page7"  rel="nofollow" target="_blank"><i class="icon-30tui"></i><span>30天退换无忧</span></a> <a class="c_6" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page8"  rel="nofollow" target="_blank"><i class="icon-yingsibz"></i><span>隐私包装</span></a> <a class="c_7" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page9"  rel="nofollow" target="_blank"><i class="icon-jinpaiyaoshi"></i><span>金牌药师</span></a> <a class="c_8" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page10"  rel="nofollow" target="_blank"><i class="icon-zhengpin"></i><span>货到付款</span></a> <a class="c_9" href="http://www.111.com.cn/cmsPage/2015011601/index.html#page11"  rel="nofollow" target="_blank"><i class="icon-yidongfuwu"></i><span>健康管家</span></a> </div>
<div class="foot_navi">
  <div class="wrap bottom_service_link b_bg clearfix">
    <dl>
      <dt>新手入门</dt>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073110/index.html" target="_blank" rel="nofollow">常见问题</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073101/index.html" target="_blank" rel="nofollow">新会员注册</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073106/index.html" target="_blank" rel="nofollow">用户登录</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073108/index.html" target="_blank" rel="nofollow">找回密码</a></dd>
    </dl>
    <dl>
      <dt>购物指南</dt>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073113/index.html" target="_blank" rel="nofollow">购物流程</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073114/index.html" target="_blank" rel="nofollow">订单状态说明</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073111/index.html" target="_blank" rel="nofollow">隐私声明</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013111902/index.html" target="_blank" rel="nofollow">发票制度</a></dd>
    </dl>
    <dl>
      <dt>配送服务</dt>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073115/index.html" target="_blank" rel="nofollow">范围、时间及费用</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073116/index.html" target="_blank" rel="nofollow">商品验货及签收</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2014022401/index.html" target="_blank" rel="nofollow">门店自提</a></dd>
    </dl>
    <dl>
      <dt>支付方式</dt>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073117/index.html" target="_blank" rel="nofollow">货到付款</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073118/index.html" target="_blank" rel="nofollow">网上支付</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073119/index.html" target="_blank" rel="nofollow">银行转账</a></dd>
    </dl>
    <dl>
      <dt>售后服务</dt>
      <dd><a href="http://www.111.com.cn/th" target="_blank" rel="nofollow">退换货政策</a></dd>
      <dd><a href="http://www.111.com.cn/bx" target="_blank" rel="nofollow">正品保障</a></dd>
      <dd><a href="http://www.111.com.cn/cmsPage/2013073122/index.html" target="_blank" rel="nofollow">退款规则</a></dd>
    </dl>
    <dl>
      <dd class="yao_app"><br />
      </dd>
      <dd><a href="http://itunes.apple.com/cn/app/id727578007" target="_blank" class="d_iphone" rel="nofollow"></a><br />
      </dd>
      <dd><a href="http://m.111.com.cn/yaodian2.apk" target="_blank" class="d_android" rel="nofollow"></a><br />
      </dd>
    </dl>
    <dl>
      <dd class="yao_weixin"><br />
      </dd>
      <dd><a href="http://weixin.qq.com/r/K3VMVKPE5-6Yrec39yA5" target="_blank" rel="nofollow">医保宝官方微信</a></dd>
    </dl>
  </div>
</div>
<div class="gray_box">
  <div class="wrap">
    <p> <a href="http://www.111.com.cn/cmsPage/2015012602/" rel="nofollow">关于医保宝</a> | <a href="http://www.111.com.cn/cmsPage/2015012707/" rel="nofollow">诚征英才</a> | <a href="http://www.111.com.cn/cmsPage/2014022401/index.html?tracker_u=10952156" rel="nofollow">直营实体药房</a> | <a href="http://www.111.com.cn/cmsPage/2013101401/"  rel="nofollow">互联网药品信息服务资格证</a> | <a href="http://www.111.com.cn/cmsPage/2013101402/" rel="nofollow">互联网药品交易资格证：粤C20100001</a> </p>
    <p> <a href="http://www.111.com.cn/cmsPage/2014041703/" rel="nofollow">连锁营业执照</a> | <a href="http://www.111.com.cn/cmsPage/2013101405/" rel="nofollow">药品经营许可证</a> | <a href="http://www.111.com.cn/cmsPage/2013101403/" rel="nofollow">保健食品卫生许可证</a> | <a href="http://www.111.com.cn/cmsPage/2013101407/" rel="nofollow">医疗器械许可证</a> | <a href="http://www.111.com.cn/315Web/"  rel="nofollow">网安备案证</a> | <a href="http://www.111.com.cn/cmsPage/2013101404/ "  rel="nofollow">食品流通许可证</a> | <a href="http://www.111.com.cn/cmsPage/2013101408/"  rel="nofollow">GSP证书</a> | <a href="http://www.111.com.cn/cmsPage/2015032501/"  rel="nofollow">ICP许可证粤B2-20150066</a> </p>
  </div>
</div>
<p class="ta_center"><a href="http://www.111.com.cn/">医保宝网上药店</a>，国家药监局首批认证通过的合法网上药店，中国医药电子商务行业的开拓者领跑者。广东壹号大药房连锁有限公司 020-66391668</p>
<div class="wrap copy_right">Copyright (C) 2014-2016 医保宝版权所有</div>

<!-- fire lzload 延时加载--> 
<script type="text/javascript">
function lzloadFr(){
  try{
      jQuery('[data-original*=""]').lazyload({threshold : 200, effect : "fadeIn", skip_invisible : true});//IE6/IE7
  }catch(e){};
  try{
      jQuery('[data-original]').lazyload({threshold : 200, effect : "fadeIn", skip_invisible : true});//CHROME/FIREFOX/IE8910
  }catch(e){};
}
addLoadEvent(lzloadFr);
</script>
<div class="wrap copyright">Copyright &copy; 2010-2015 医保宝版权所有</div>
<!--footer end--> 

<!-- 底部右侧快捷栏 -->
<div class="float_box"  id="rightFloat" > <a href="javascript:;" onclick="jQuery.top();" class="f_top" target="_self" title="返回顶部" rel="nofollow">top</a> 
  <!--药师咨询-->
  
  <div class="showConsult" style="display:none;">
    <div class="consult" >
      <div class="d_txt"> <a target="_self" rel="nofollow"  href="javascript:;" class="b_consultCol" onclick="Detector.floatclick(3,'',encodeURI('售后咨询'));">售前咨询</a> <a target="_self" rel="nofollow" href="javascript:;" class="b_consultCol" onclick="Detector.floatclick(3,'',encodeURI('售后咨询'));">售后咨询</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(4,'',encodeURI('企业采购'));" class="last">企业采购</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(4,'',encodeURI('全科药师'));">全科药师</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(14,'',encodeURI('风湿骨科'));">风湿骨科</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(14,'',encodeURI('神经科'));" class="last">神经科</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(4,'',encodeURI('男科'));">男科</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(10,'',encodeURI('肿瘤'));">肿瘤</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(11,'',encodeURI('肝病'));" class="last">肝病</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(14,'',encodeURI('心脑血管'));">心脑血管</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(11,'',encodeURI('皮肤性病'));">皮肤性病</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(4,'',encodeURI('糖尿病'));" class="last">糖尿病</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(8,'',encodeURI('散光验配'));">散光验配</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(1,'',encodeURI('营养补充'));">营养补充</a> <a target="_self" rel="nofollow" href="javascript:;" onclick="Detector.floatclick(1,'',encodeURI('医疗器械'));" class="last">医疗器械</a> </div>
    </div>
  </div>
</div>
<script type="text/javascript">
//宽窄屏
    $(document).ready(function(){

    $(".float_box .f_home ,  .float_box .f_home").mouseover(function() {
       $(".float_box .minicart_list").css("display","none");
        $(".float_box .none_tips").css("display","none");
       	$('.float_box .showConsult').css("display","block");
     });
  
    $(".float_box .showConsult").mouseleave(function() {
        $(".float_box .minicart_list").css("display","none");
    	$(".float_box .showConsult").css("display","none");
     });


    $(".f_wei").mouseover(function() {   
 	$(".float_box .showConsult").css("display","none");
    	drawBottomCart();
    });
    
   
  $(".float_box .minicart_list，.float_box .f_wei ").mouseleave(function() {
 	$(".float_box .showConsult").css("display","none");
       $(".float_box .minicart_list").css("display","none");
       $(".float_box .none_tips").css("display","none");
    });

    $(".float_box .f_back,.float_box .f_top").mouseover(function() {
        $(".float_box .minicart_list").css("display","none");
        $(".float_box .none_tips").css("display","none");
       	$('.float_box .showConsult').css("display","none");
     });

     $(".float_box ").mouseleave(function() {
        $(".float_box .showConsult").hide();
     	$('#bottom_minicart_list').hide();
       $(".float_box .none_tips").hide();
    }); 

	loadFloor();
})

function loadFloor() {
	$.ajax({
		type: "GET",
		url: ctx+"/anon/index/floor?rnd="+Math.random(),
		data: "",
		success : function(data) {
			$('#floorDiv').empty().append(data);
			$('#floorDiv').show();
		},
		error : function() {alert('loadFloor error');}
	});
}

$(function(){
	var uk = $.cookie('UserInfo');
	if(!uk)return;
        else{
           $(".none_tips").children("p").html("您的购物车里还没有商品，立刻去购买吧。");
        }
	
});
</script> 
<script type="text/javascript">
if(typeof(item_status) == 'string'
		&& item_status == '2'){
	$("#rightFloat > [name='yao_docuor']").remove();
	$("#rightFloat").height("80")
}
</script>
</body>
</html>
