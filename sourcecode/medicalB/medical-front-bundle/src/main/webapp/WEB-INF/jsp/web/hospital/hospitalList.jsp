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
  

  
  <c:forEach items="${newareaList}" var="area">
  <c:if test="${area.counthospital>0}">
       <!--各市医院列表-->  
			<div class="ctg-box cf">
			          <div class="ctg-title">
			              <span class="ctg-titleL">${area.areaName}</span>
			          </div>
			          <div class="find-hospital">
					<ul>
					<c:forEach items="${area.hospitalList}" var="hos">
					
					<li><a href="${ctx }/web/hospitalDetais?hospitalID=${hos.hospitalID}" target="_blank" class="blue" title="${hos.hospitalName }">${hos.hospitalName }</a><span class="clr-999"> (${hos.hospitalLevel }, 特色:${hos.skill })</span></li>
					
					</c:forEach>
					</ul>
			          </div>
			</div>
  </c:if>
  
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
        
          <c:forEach items="${newareaList}" var="area1">
        <c:if test="${area1.counthospital>0}">
         <li><span class="clr-08c">${area1.areaName} </span>${area1.counthospital}</li>
          </c:if>
          </c:forEach>
        </ul>
        </div>
    </div>

</div><!--wealink-right-->

</div><!--main end-->

<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>
</body>
</html>