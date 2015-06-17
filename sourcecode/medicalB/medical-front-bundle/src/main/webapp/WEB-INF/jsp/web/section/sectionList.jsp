<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/web/common.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/web/head.jsp"></jsp:include>
<title>找科室-医保宝</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<script src="${ctx }/resources/web/js/section/section.js" type="text/javascript"></script>
<link href='${ctx }/resources/web/css/hospital.css' rel='stylesheet' type='text/css' />
</head>
<body>

<div class="main">
    <div class="top-ch-loc">
        	当前位置：
        	<a class="clr-08c" href="${ctx }" target="_blank">医保宝</a><span class="clr-ccc"> &gt; 		<span class="clr-999">找科室</span>
    </div>
    
  	<div class="comp-detail-left">
  
		<!--各市医院列表-->  
	<!-- 	<div class="ctg-box cf">
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
					</ul>
				</ul>
	    	</div>
		</div>

 	各市医院列表  
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
					</ul>
				</ul>
			</div>
	</div> -->

</div>
<!--comp-detail-left end-->

<div class="wealink-right">

<div class="wlr-box">
        <div class="wlr-box-hd">
            <div class="wlr-box-more">
            </div>
            <h3 class="clr-grn">科室</h3>
        </div>
        <div class="keshi-box-bd">

	        <ul class="com-right-num">
	          <!-- <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">内科 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">外科 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">口腔科学 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">内科 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">外科 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">口腔科学 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">内科 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">外科 </span></a></li>
	          <li><a rel="nofollow" target="_blank" href="#" class="clr-08c"><span class="clr-08c">口腔科学 </span></a></li> -->
	        </ul>
            
        </div>
    </div>

</div><!--wealink-right-->

</div><!--main end-->

<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>
</body>
</html>