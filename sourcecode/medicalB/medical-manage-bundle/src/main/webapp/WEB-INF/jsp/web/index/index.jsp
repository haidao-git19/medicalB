<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/WEB-INF/jsp/web/common.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/web/head.jsp"></jsp:include>
<title>医保宝 - 首页</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<script src="${ctx }/resources/web/js/index/index.js" type="text/javascript"></script>
<script src="${ctx }/resources/web/js/jquery.superslide.2.1.1.js" type="text/javascript"></script>

    </head>
    <body>

<div class="main">

<div class="mid">

<div class="left">
<!-- 代码 开始 -->
<div class="pic_banner">
    <ul class="pics">
    <c:forEach items="${posterList.posterList }" var="poster">
   	 <li><a href="${poster.url }" target="_blank"><img src="${poster.image }" /></a></li>
    </c:forEach>
    </ul>
    <a class="prev" href="javascript:void(0)"></a>
    <a class="next" href="javascript:void(0)"></a>
    <div class="num">
    	<ul></ul>
    </div>
</div>
<script>
/*鼠标移过，左右按钮显示*/
$(".pic_banner").hover(function(){
	$(this).find(".prev,.next").fadeTo("show",0.1);
},function(){
	$(this).find(".prev,.next").hide();
})
/*鼠标移过某个按钮 高亮显示*/
$(".prev,.next").hover(function(){
	$(this).fadeTo("show",0.7);
},function(){
	$(this).fadeTo("show",0.1);
})
$(".pic_banner").slide({ titCell:".num ul" , mainCell:".pics" , effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });
</script>
<!-- 代码 结束 -->

	<div class="show-box"></div>

<div class="blk8 clear"></div>


<div class="wlr-box">
  <div class="wlr-box-hd">
    <div class="wlr-box-ctrl2"> <a href="javascript:;" class="wlr-box-ctrl2-hr btn_top_fans1 active">按医院找大夫</a><a href="javascript:section();" class="wlr-box-ctrl2-lt btn_top_fans2 ">按科室找大夫</a></div>
  </div>
  <div class="wlr-box-bd">
    <ul class="infoimg-list2 cf ul_top_fans1">


<!--按医院找 -->
<div class="J_tab">
<div class="menu J_menu" id="hospitalLatn">
<c:forEach 	var="area" items="${areaList }" varStatus="var">
	<a id="jh_${area.areaID}" 
	<c:choose>
	<c:when test="${area.areaID==551 }">
	class="menu-active"
	</c:when>
	<c:otherwise>
	class="normal"
	</c:otherwise>
	</c:choose>
	 target="_blank" latn="${area.areaID}" href="#">${area.areaName }</a>
</c:forEach>
</div>
<div class="menu_con J_content">

<c:forEach var="unitList" items="${hospitalList.hospitalList }">
<div id="hospitalLatn_${unitList.key}" style="display: block;" class="menu_con_bg">
<ul>
	<c:forEach var="hospital" items="${unitList.list }">
		<li><a href="/hospital/DE4roiYGYZwWG0YFEgXN3cgkJ.htm" target="_blank">${hospital.hospitalName }</a><span class="gray">(111)</span></li>
	</c:forEach>
</ul>
<div class="clear"></div>
</div>

</c:forEach>
<div id="hospitalLatn_none" style="display: block;" class="menu_con_bg">
<ul>
	<li>暂无数据</li>
</ul>
<div class="clear"></div>
</div>

</div>
</div>
<!--按医院找 end-->

    </ul>
    <ul class="infoimg-list2 cf ul_top_fans2" style="display: none;" >


<!--查找科室-->
<div class="find_jb" id="querySection">
</div>

    </ul>
  </div>
</div>

<div class="blk10 clear" style="height:10px;"></div>





<div class="wlr-box">
  <div class="wlr-box-hd">
    <div class="wlr-box-ctrl2">电话咨询</div>
  </div>
  <div class="wlr-box-bd">
<!--咨询列表-->
<div class="J_tab">
<div class="menu J_menu" id="tel_zx">
<c:forEach 	var="area" items="${areaList }" varStatus="var">
	<a id="zx_${area.areaID}" 
	<c:choose>
	<c:when test="${area.areaID==551 }">
	class="menu-active"
	</c:when>
	<c:otherwise>
	class="normal"
	</c:otherwise>
	</c:choose>
	 target="_blank" latn="${area.areaID}" href="#">${area.areaName }</a>
</c:forEach>
</div>
<div class="menu_con J_content">
<div id="jhcon_0" style="display: block;" class="menu_con2">
<ul class="homeTelUl">
  <li class="homeTelLi "> <span class="homeTelLi_span0">
    <table>
      <tbody>
        <tr>
          <td><a href="http://400.haodf.com/haodf/mdonglai"><img src="http://n2.hdfimg.com/g1/M00/17/E2/oYYBAFIlwGGAFFDLAAJ66YHO_XI392_58_58_1.jpg?06dd"></a></td>
        </tr>
      </tbody>
    </table>
    </span> <span class="homeTelLi_span1">
    <p> <a class="blue2" href="http://400.haodf.com/haodf/mdonglai">马东来</a> 主任医师 </p>
    <p>北京协和医院</p>
    <p>皮肤科</p>
    </span> <span class="homeTelLi_span1">
    <p> <a href="http://www.haodf.com/wenda/mdonglai_g_1818634760.htm"> 白癜风 </a>&nbsp; </p>
    <p>最近通话： 04-01 19:51</p>
    <p><a href="http://400.haodf.com/haodf/mdonglai#successAnswer" target="_blank" class="blue">查看最新用户分享 &gt;&gt;</a></p>
    </span> <span class="homeTelLi_span3"> <span class="mr5">300元/次(最长10分钟)</span> </span> <span class="homeTelLi_span4"> <a class="orange3" href="http://mdonglai.haodf.com/payment/servicelist">立刻预约</a> </span> </li>
  <li class="homeTelLi gray5"> <span class="homeTelLi_span0">
    <table>
      <tbody>
        <tr>
          <td><a href="http://400.haodf.com/haodf/wanweilin"><img src="http://n2.hdfimg.com/g1/M00/17/C1/oYYBAFIlv26AIRTAAAZ_MU0_tbc049_58_58_1.jpg?a8f7"></a></td>
        </tr>
      </tbody>
    </table>
    </span> <span class="homeTelLi_span1">
    <p> <a class="blue2" href="http://400.haodf.com/haodf/wanweilin">万伟琳</a> 副主任医师 </p>
    <p>北京协和医院</p>
    <p>儿科</p>
    </span> <span class="homeTelLi_span1">
    <p> <a href="http://www.haodf.com/wenda/wanweilin_g_2365570018.htm"> 儿子三周，一到冬天就... </a>&nbsp; </p>
    <p>最近通话： 04-01 21:09</p>
    <p><a href="http://400.haodf.com/haodf/wanweilin#successAnswer" target="_blank" class="blue">查看最新用户分享 &gt;&gt;</a></p>
    </span> <span class="homeTelLi_span3"> <span class="mr5"><span class="show_price">200元/次</span><span class="show_duration">(最长15分钟)</span></span> </span> <span class="homeTelLi_span4"> <a class="orange3" href="http://wanweilin.haodf.com/payment/servicelist">立刻预约</a> </span> </li>
  <li class="homeTelLi "> <span class="homeTelLi_span0">
    <table>
      <tbody>
        <tr>
          <td><a href="http://400.haodf.com/haodf/tongguoqing"><img src="http://n4.hdfimg.com/g2/M00/01/6D/pIYBAFIlwOGAWS1wAA6eBCz54qY716_58_58_1.jpg?4dbb"></a></td>
        </tr>
      </tbody>
    </table>
    </span> <span class="homeTelLi_span1">
    <p> <a class="blue2" href="http://400.haodf.com/haodf/tongguoqing">童国庆</a> 主任医师 </p>
    <p>上海曙光医院东院</p>
    <p>生殖医学中心</p>
    </span> <span class="homeTelLi_span1">
    <p> <a href=""> </a>&nbsp; </p>
    <p>最近通话： 暂无</p>
    <p><a href="http://400.haodf.com/haodf/tongguoqing#successAnswer" target="_blank" class="blue">查看最新用户分享 &gt;&gt;</a></p>
    </span> <span class="homeTelLi_span3"> <span class="mr5"><span class="show_price">200元/次</span><span class="show_duration">(最长10分钟)</span></span> </span> <span class="homeTelLi_span4"> <a class="orange3" href="http://tongguoqing.haodf.com/payment/servicelist">立刻预约</a> </span> </li>
</ul>
<div class="clear"></div>
</div>

</div>
</div>
<!--咨询列表 end-->
  </div>

</div><!--电话咨询 end-->





<div class="wlr-box">
  <div class="wlr-box-hd">
    <div class="wlr-box-ctrl2">网上咨询</div>
  </div>
  <div class="wlr-box-bd">
<!--咨询列表-->
<div class="J_tab">
<div class="menu J_menu">
<a id="jh_0" class="menu-active" target="_blank" href="#">合肥</a>
<a id="jh_1" class="normal" target="_blank" href="#">芜湖</a>
<a id="jh_2" class="normal" target="_blank" href="#">宣城</a>
<a id="jh_3" class="normal" target="_blank" href="#">安庆</a>
<a id="jh_4" class="normal" target="_blank" href="#">蚌埠</a>
<a id="jh_5" class="normal" target="_blank" href="#">亳州</a>
<a id="jh_6" class="normal" target="_blank" href="#">淮南</a>
<a id="jh_7" class="normal" target="_blank" href="#">淮北</a>
<a id="jh_8" class="normal" target="_blank" href="#">阜阳</a>
</div>
<div class="menu_con2 J_content">
<div id="zxcon_0" style="display: block;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">天津总医院眼科&nbsp;&nbsp;陈松回复</span>
    <a href="http://www.haodf.com/wenda/chensongzyy_g_1836022995.htm" target="_blank">小儿白内障</a> 
    <a class="gray" style="text-decoration:none;">2分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">新华医院儿童内分泌科&nbsp;&nbsp;邱文娟回复</span>
    <a href="http://www.haodf.com/wenda/qiuwenjuan_g_2373247727.htm" target="_blank">糖原累积症不吃玉米淀粉</a> 
    <a class="gray" style="text-decoration:none;">2分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_1" style="">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">北京同仁医院耳鼻咽喉头颈外科&nbsp;&nbsp;程晓华回复</span>
    <a href="http://www.haodf.com/wenda/chengxiaohua010_g_2371197410.htm" target="_blank">耳声发射和aabr均通过，宝宝...</a> 
    <a class="gray" style="text-decoration:none;">16分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">北京儿童医院耳鼻咽喉头颈外科&nbsp;&nbsp;陈敏回复</span>
    <a href="http://www.haodf.com/wenda/chenmin121_g_2373173009.htm" target="_blank">听力下降</a> 
    <a class="gray" style="text-decoration:none;">16分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_2" style="display: none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">301医院口腔科&nbsp;&nbsp;徐璐璐回复</span>
    <a href="http://www.haodf.com/wenda/xull_g_2372864588.htm" target="_blank">成人正畸矫正</a> 
    <a class="gray" style="text-decoration:none;">51分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">88医院口腔科&nbsp;&nbsp;田晓光回复</span>
    <a href="http://www.haodf.com/wenda/tianxgdr_g_2372702217.htm" target="_blank">牙齿矫正问题</a> 
    <a class="gray" style="text-decoration:none;">1小时前</a>
    </li>
</ul>
</div>
<div id="zxcon_3" style="display: none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">空军总医院肝胆外科&nbsp;&nbsp;刘承利回复</span>
    <a href="http://www.haodf.com/wenda/liuchengli_g_2364269015.htm" target="_blank">肝胆管细胞癌</a> 
    <a class="gray" style="text-decoration:none;">2分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">南京鼓楼医院肝胆外科&nbsp;&nbsp;施晓雷回复</span>
    <a href="http://www.haodf.com/wenda/njsxl_g_2373241645.htm" target="_blank">血管瘤的治疗</a> 
    <a class="gray" style="text-decoration:none;">4分钟前</a>
    </li>
    <li class="zx_bg1">
        <span class="gray f_rt">南京军区总医院肿瘤内科&nbsp;&nbsp;苏全胜回复</span>
    <a href="http://www.haodf.com/wenda/suquansheng_g_2373208060.htm" target="_blank">求肝癌保守治疗方法</a> 
    <a class="gray" style="text-decoration:none;">10分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_4" style="display:none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">河南省人民医院生殖医学中心&nbsp;&nbsp;张翠莲回复</span>
    <a href="http://www.haodf.com/wenda/luckyzhcl_g_2373217215.htm" target="_blank">抽血化验怀孕了不知道孕酮低...</a> 
    <a class="gray" style="text-decoration:none;">8分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">北大人民医院产科&nbsp;&nbsp;魏俊回复</span>
    <a href="http://www.haodf.com/wenda/weijun90_g_2373211796.htm" target="_blank">超声检查NT值：5.5 孕期10 6</a> 
    <a class="gray" style="text-decoration:none;">9分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_5" style="display:none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">上海华东医院血管外科&nbsp;&nbsp;崔佳森回复</span>
    <a href="http://www.haodf.com/wenda/cuijiasen_g_2371146167.htm" target="_blank">婴儿血管瘤</a> 
    <a class="gray" style="text-decoration:none;">15分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">上海第九人民医院口腔颌面外科&nbsp;&nbsp;范新东回复</span>
    <a href="http://www.haodf.com/wenda/fanxindong_g_2373149074.htm" target="_blank">6个月宝宝 血管瘤</a> 
    <a class="gray" style="text-decoration:none;">19分钟前</a>
    </li>
    <li class="zx_bg1">
        <span class="gray f_rt">珠江医院皮肤科&nbsp;&nbsp;张堂德回复</span>
    <a href="http://www.haodf.com/wenda/tdgz70_g_2373054766.htm" target="_blank">血管瘤</a> 
    <a class="gray" style="text-decoration:none;">21分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_6" style="display: none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">河南省骨科医院足踝损伤科&nbsp;&nbsp;穆世民回复</span>
    <a href="http://www.haodf.com/wenda/mushimin_g_2373247649.htm" target="_blank">距骨坏死脚不能向上勾起怎么办</a> 
    <li class="zx_bg2">
        <span class="gray f_rt">上海华山医院手外科&nbsp;&nbsp;韩栋回复</span>
    <a href="http://www.haodf.com/wenda/genethik_g_2373035073.htm" target="_blank">无名指骨折有碎片</a> 
    <a class="gray" style="text-decoration:none;">11分钟前</a>
    </li>
    <li class="zx_bg1">
        <span class="gray f_rt">深圳市第二人民医院运动医学科&nbsp;&nbsp;欧阳侃回复</span>
    <a href="http://www.haodf.com/wenda/ouyangkan_g_2242860662.htm" target="_blank">踝关节前内侧疼痛</a> 
    <a class="gray" style="text-decoration:none;">16分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_7" style="display: none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">北京儿童医院普通外科&nbsp;&nbsp;李小松回复</span>
    <a href="http://www.haodf.com/wenda/xiaosonglee_g_2373233539.htm" target="_blank">腹股沟疝气</a> 
    <a class="gray" style="text-decoration:none;">3分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">北京儿童医院普通外科&nbsp;&nbsp;李小松回复</span>
    <a href="http://www.haodf.com/wenda/xiaosonglee_g_2373240495.htm" target="_blank">小儿腹股沟斜疝</a> 
    <a class="gray" style="text-decoration:none;">4分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_8" style="display: none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">广医二院呼吸内科&nbsp;&nbsp;林俊源回复</span>
    <a href="http://www.haodf.com/wenda/linjunyuan_g_2371698771.htm" target="_blank">过敏咳嗽久咳不愈</a> 
    <a class="gray" style="text-decoration:none;">17分钟前</a>
    </li>
    <li class="zx_bg2">
        <span class="gray f_rt">广医二院呼吸内科&nbsp;&nbsp;林俊源回复</span>
    <a href="http://www.haodf.com/wenda/linjunyuan_g_2373148981.htm" target="_blank">是不是过敏性咳嗽</a> 
    <a class="gray" style="text-decoration:none;">19分钟前</a>
    </li>
</ul>
</div>
<div id="zxcon_9" style="display:none;">
<ul>
    <li class="zx_bg1">
        <span class="gray f_rt">湖州中心医院外科&nbsp;&nbsp;余胜回复</span>
    <a href="http://www.haodf.com/wenda/lzyusheng_g_2371468979.htm" target="_blank">成人腹股沟疝(小肠疝气)</a> 
    <a class="gray" style="text-decoration:none;">12分钟前</a>
    </li>
</ul>
</div>
</div>

<div class="zx_more">
    <span class="f_rt">
        <a href="http://zixun.haodf.com/dispatched/all.htm" class="blue2" target="_blank">查看全部18,632,602条咨询&gt;&gt;</a>
    </span>
</div>

</div>
<!--咨询列表 end-->
  </div>

</div>






</div><!--left end-->





<div class="right">

<div class="bner_r">
                <div class="b_left_a">
                    <ul>
                        <li class="bleft1">
                            <p>
                                <a href="javascript:;">首家医药销售平台</a></p>
                            9年追求:客户满意,药品安全</li>
                        <li class="bleft2">
                            <p>
                                <a href="/ShopsCheckInList.aspx">608家药房入驻818</a></p>
                            万种热销商品供您选择...</li>
                        <li class="bleft3">
                            <p>
                                <a href="javascript:;">21155666人体验了</a></p>
                            医保宝网的健康服务</li>
                    </ul>
                </div>
                <div class="b_left_b">
                    <div class="b_tile">
                        <h1>公告</h1>
                    </div>
                    <ul id="ulgonggao">
                    <li><a title="818医药网关于"假客服电话诈骗"一事的官方声明" href="/helpcenter/2015/0121/0000000800.html">818医药网关于"假客服电话诈骗"一事的官方声明</a></li>
                    <li><a title="药监局查处假冒保健品" href="/helpcenter/2013/0521/0000000641.html">药监局查处假冒保健品</a></li>
                    <li><a title="2013年第2期违法广告公告" href="/helpcenter/2013/0805/0000000675.html">2013年第2期违法广告公告</a></li>
                    <li><a title="关注珍菊降压片用药风险" href="/helpcenter/2013/0305/0000000608.html">关注珍菊降压片用药风险</a></li>
                    </ul>
                </div>
            </div><!--公告 end-->



<div class="popular cf">
  <div class="popular-title"><a class="popular-title-more" href="#" rel="nofollow" target="_blank">更多</a>
    <h2>网上咨询动态...</h2>
  </div>
  <div class="popular-board">
    <ul>

<div class="r_bar_doc3">
  <div class="r_bar_doc3_l">
    <table border="0" cellpadding="0" cellspacing="0">
      <tbody>
        <tr>
          <td style="padding: 0px; border: 1px solid #DFDFDF; height: 31px; vertical-align: middle; width: 31px;" align="center" valign="middle"><a target="_blank" href="http://chjh76.haodf.com"><img style="border:none;" src="http://n1.hdfimg.com/g3/M00/43/FE/p4YBAFIwhECAAEfZAAAH4eTfEf0542_31_31_1.jpg?02f0"></a></td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="r_bar_doc3_c"><span style="float:right;">2分钟前</span><a href="http://chjh76.haodf.com" class="blue2" target="_blank">陈建海</a> 副主任医师<br>
    北大人民医院 
    回复了患者 <br>
  </div>
  <div class="clear"></div>
</div>

<div class="r_bar_doc3">
  <div class="r_bar_doc3_l">
    <table border="0" cellpadding="0" cellspacing="0">
      <tbody>
        <tr>
          <td style="padding: 0px; border: 1px solid #DFDFDF; height: 31px; vertical-align: middle; width: 31px;" align="center" valign="middle"><a target="_blank" href="http://chjh76.haodf.com"><img style="border:none;" src="http://n1.hdfimg.com/g3/M00/43/FE/p4YBAFIwhECAAEfZAAAH4eTfEf0542_31_31_1.jpg?02f0"></a></td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="r_bar_doc3_c"><span style="float:right;">2分钟前</span><a href="http://chjh76.haodf.com" class="blue2" target="_blank">陈建海</a> 副主任医师<br>
    北大人民医院 
    回复了患者 <br>
  </div>
  <div class="clear"></div>
</div>

<div class="r_bar_doc3">
  <div class="r_bar_doc3_l">
    <table border="0" cellpadding="0" cellspacing="0">
      <tbody>
        <tr>
          <td style="padding: 0px; border: 1px solid #DFDFDF; height: 31px; vertical-align: middle; width: 31px;" align="center" valign="middle"><a target="_blank" href="http://chjh76.haodf.com"><img style="border:none;" src="http://n1.hdfimg.com/g3/M00/43/FE/p4YBAFIwhECAAEfZAAAH4eTfEf0542_31_31_1.jpg?02f0"></a></td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="r_bar_doc3_c"><span style="float:right;">2分钟前</span><a href="http://chjh76.haodf.com" class="blue2" target="_blank">陈建海</a> 副主任医师<br>
    北大人民医院 
    回复了患者 <br>
  </div>
  <div class="clear"></div>
</div>



<div class="r_bar_doc3">
  <div class="r_bar_doc3_l">
    <table border="0" cellpadding="0" cellspacing="0">
      <tbody>
        <tr>
          <td style="padding: 0px; border: 1px solid #DFDFDF; height: 31px; vertical-align: middle; width: 31px;" align="center" valign="middle"><a target="_blank" href="http://chjh76.haodf.com"><img style="border:none;" src="http://n1.hdfimg.com/g3/M00/43/FE/p4YBAFIwhECAAEfZAAAH4eTfEf0542_31_31_1.jpg?02f0"></a></td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="r_bar_doc3_c"><span style="float:right;">2分钟前</span><a href="http://chjh76.haodf.com" class="blue2" target="_blank">陈建海</a> 副主任医师<br>
    北大人民医院 
    回复了患者 <br>
  </div>
  <div class="clear"></div>
</div>

<div class="r_bar_doc3">
  <div class="r_bar_doc3_l">
    <table border="0" cellpadding="0" cellspacing="0">
      <tbody>
        <tr>
          <td style="padding: 0px; border: 1px solid #DFDFDF; height: 31px; vertical-align: middle; width: 31px;" align="center" valign="middle"><a target="_blank" href="http://chjh76.haodf.com"><img style="border:none;" src="http://n1.hdfimg.com/g3/M00/43/FE/p4YBAFIwhECAAEfZAAAH4eTfEf0542_31_31_1.jpg?02f0"></a></td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="r_bar_doc3_c"><span style="float:right;">2分钟前</span><a href="http://chjh76.haodf.com" class="blue2" target="_blank">陈建海</a> 副主任医师<br>
    北大人民医院 
    回复了患者 <br>
  </div>
  <div class="clear"></div>
</div>




    </ul>
  </div>
</div>




<div class="index-banner-app">
   <a href="#" title="APP！" target="_blank"><img  src="${ctx }/resources/web/img/appd.jpg" alt="APP！"></a>
</div>




<div class="popular cf">
  <div class="popular-title"><a class="popular-title-more" href="#" rel="nofollow" target="_blank">更多</a>
    <h2>专家文章</h2>
  </div>
  <div class="popular-board">
    <ul>
  <li><a href="/xulingmin_2371689198.htm" target="_blank"> 您要求给孩子输液，你小时候输液多吗？...</a></li>
  <li><a href="/TaoX_2368969395.htm" target="_blank"> 如何做一名"好的患者"</a></li>
  <li><a href="/doctorwangxin_2367465749.htm" target="_blank"> 多指畸形，孩子多大时做手术最好？</a></li>
  <li><a href="/zhuyilin_2367463435.htm" target="_blank"> 腹腔镜治疗成人腹股沟疝的四大优势</a></li>
  <li><a href="/zhuyilin_2367456314.htm" target="_blank"> 腹腔镜治疗成人腹股沟疝 实现"量体裁...</a></li>
  <li><a href="/yanzhengsong_2362752035.htm" target="_blank"> 脊柱结核手术 术前术后都需抗结核药!</a></li>
  <li><a href="/yanzhengsong_2362749906.htm" target="_blank"> 疼痛、脓包、脊柱后凸 警惕脊柱结核!</a></li>
  <li><a href="/mmzz_2362537881.htm" target="_blank"> 关于泌乳素---你想知道的</a></li>
  <li><a href="/lym082_2362265696.htm" target="_blank"> 脑血管畸形什么情况容易破裂出血？</a></li>
    </ul>
  </div>
</div>














</div><!--right end-->

      <script type="text/javascript">
            $(function(){
                //右侧一周粉丝榜切换
                $('.btn_top_fans1').click(function() {
                    if($(this).hasClass("active")){
                        return false;
                    }
                    $('.btn_top_fans2').removeClass('active');
                    $('.btn_top_fans3').removeClass('active');
                    $('.btn_top_fans1').addClass('active');
                    $('.ul_top_fans2').hide();
                    $('.ul_top_fans3').hide();
                    $('.ul_top_fans1').show();
                });
                $('.btn_top_fans2').click(function() {
                    if($(this).hasClass("active")){
                        return false;
                    }
                    $('.btn_top_fans1').removeClass('active');
                    $('.btn_top_fans3').removeClass('active');
                    $('.btn_top_fans2').addClass('active');
                    $('.ul_top_fans1').hide();
                    $('.ul_top_fans3').hide();
                    $('.ul_top_fans2').show();
                });
                $('.btn_top_fans3').click(function() {
                    if($(this).hasClass("active")){
                        return false;
                    }
                    $('.btn_top_fans2').removeClass('active');
                    $('.btn_top_fans1').removeClass('active');
                    $('.btn_top_fans3').addClass('active');
                    $('.ul_top_fans2').hide();
                    $('.ul_top_fans1').hide();
                    $('.ul_top_fans3').show();
                });



            })

        </script>







</div>

</div><!--main end-->
<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>

        
</body>
</html>