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
<jsp:include page="/WEB-INF/jsp/web/common.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/web/head.jsp"></jsp:include>
<title>医保宝 - 首页</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<script type="text/javascript" src="${ctx }/resources/web/js/index/index.js?ver=${version}"></script>

    </head>
    <body>
<input type=hidden value="${srcload}" id="srcloadhide"/>
<div class="main">

<div class="mid">

<div class="left">
<!-- 代码 开始 -->
<div class="pic_banner">
    <ul class="pics" >
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
    <div class="wlr-box-ctrl2"> <a href="javascript:;" class="wlr-box-ctrl2-hr btn_top_fans1 active">按医院找大夫</a><a href="javascript:;" class="wlr-box-ctrl2-lt btn_top_fans2 ">按科室找大夫</a></div>
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
<div id="hospitalLatn_${unitList.key}" class="menu_con_bg">
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
    <div class="wlr-box-ctrl2">在线咨询</div>
  </div>
  <div class="wlr-box-bd">
<!--咨询列表-->
<div class="J_tab">
<div class="menu J_menu" id="zxzxks">
</div>
<div class="menu_con J_content" id="docli">
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
<div class="menu J_menu" id="wszxks">
</div>

<div class="menu_con2 J_content" id="zxdoc">

</div>

<div class="zx_more">
    <span class="f_rt">
        总计<span id="zxcounttj">0</span>条咨询&gt;&gt;
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
                                <a href="/ShopsCheckInList.aspx"><span id="rzyf">0</span>家药房入驻818</a></p>
                            万种热销商品供您选择...</li>
                        <li class="bleft3">
                            <p>
                                <a href="javascript:;"><span id="fwsl">0</span>人体验了</a></p>
                            医保宝网的健康服务</li>
                    </ul>
                </div>
                <div class="b_left_b">
                    <div class="b_tile">
                        <h1>公告</h1>
                    </div>
                    <ul id="ulgonggao">
                    </ul>
                </div>
            </div><!--公告 end-->



<div class="popular cf">
  <div class="popular-title"><a class="popular-title-more" href="#" rel="nofollow" target="_blank">更多</a>
    <h2>网上咨询动态...</h2>
  </div>
  <div class="popular-board">
    <ul id="zxtdnr">

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
   <a href="#" title="APP！" target="_blank"><img  src="${ctx}/resources/web/img/appd.jpg" alt="APP！"></a>
</div>




<div class="popular cf">
  <div class="popular-title">
    <h2>专家文章</h2>
  </div>
  <div class="popular-board">
    <ul id="zjwz">
  
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