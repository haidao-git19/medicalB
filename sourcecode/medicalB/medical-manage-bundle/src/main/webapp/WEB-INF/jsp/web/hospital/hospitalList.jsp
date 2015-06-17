<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/WEB-INF/jsp/web/common.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/web/head.jsp"></jsp:include>
<title>找医院-医保宝</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<script src="${ctx }/resources/web/js/hospital/hospital.js" type="text/javascript"></script>
<script src="${ctx }/resources/web/js/global.js" type="text/javascript"></script>
<link href='${ctx }/resources/web/css/hospital.css' rel='stylesheet' type='text/css' />
<link href='${ctx }/resources/web/css/global-v5.css' rel='stylesheet' type='text/css' />
    </head>
    <body>


<div class="main">
    <div class="top-ch-loc">
        当前位置：
                <a class="clr-08c" href="${ctx }" target="_blank">医保宝</a><span class="clr-ccc"> &gt; 		<span class="clr-999">按医院</span>
    </div>
    
  <div class="comp-detail-left">
  
  
  
  <c:forEach items="${hospitalList.hospitalList }" var="unitList">
  
  <!--各市医院列表-->  
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">${unitList.latnName}</span>
          </div>
          <div class="find-hospital">
		<ul>
		<c:forEach items="${unitList.list }" var="list">
		
		<li><a href="${ctx }/web/hospitalDetais?hospitalID=${list.hospitalID}" target="_blank" class="blue" title="${list.hospitalName }">${list.hospitalName }</a><span class="clr-999"> (${list.hospitalLevel }, 特色:${list.skill })</span></li>
		
		</c:forEach>
		</ul>
          </div>
</div>
  </c:forEach>

</div><!--comp-detail-left end-->

<div class="wealink-right">

<div class="wlr-box">
        <div class="wlr-box-hd">
            <div class="wlr-box-more">
            </div>
            <h3 class="clr-grn">地区</h3>
        </div>
        <div class="dishi-box-bd">

        <ul class="com-right-num">
        
          <c:forEach items="${hospitalList.hospitalList }" var="unitList">
        
         <li><a rel="nofollow" target="_blank" href="/dianpin/"><span class="clr-08c">${unitList.latnName} </span>${fn:length(unitList.list)} </a></li>
          </c:forEach>
        </ul>
        </div>
    </div>

</div><!--wealink-right-->

</div><!--main end-->

<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>
</body>
</html>