<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
 <input type="hidden" id="hosid" value="${hospitalDetails.hospitalDetail.hospitalID}">
<div class="main">
    <div class="top-ch-loc">
        当前位置：
                <a class="clr-08c" href="${ctx }/web" target="_blank">医保宝</a><span class="clr-ccc"> &gt; </span><a class="clr-08c" href="#" target="_blank">医院</a><span class="clr-ccc"> &gt; </span>			<span class="clr-999">北京安定医院</span>
    </div>
            
            
            
            
<div class="area">
	<div class="contentC">
		<div id="headpA_blue">
            <div id="ltb">
                <span><a href="#">${hospitalDetails.hospitalDetail.hospitalName }</a> </span><span style="font-size:18px;"></span>
            </div>
        </div>
	</div>
</div>

<div class="comp-detail-left">

<ul class="comp-gen-words cf">
  <li class="gen-assL">
    <label for="comment">医院等级：</label>
    <span class="clr-org">${hospitalDetails.hospitalDetail.hospitalLevel }</span></li>
  <li>
    <label for="shortname">电话：</label>
    ${hospitalDetails.hospitalDetail.linkPhone }(预约挂号)</li>

  <li class="comp-addL">
    <label for="address">医院地址：</label>
    <span title=" ${hospitalDetails.hospitalDetail.address }"> ${hospitalDetails.hospitalDetail.address }</span> <a class="clr-08c" target="_blank" href="#" rel="nofollow">地图</a> </li>
  <li class="comp-gen-txarea"> ${hospitalDetails.hospitalDetail.skill } </li>
</ul>





<!--科室列表-->
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">${hospitalDetails.hospitalDetail.hospitalName } 科室列表</span>
              <span class="ctg-titleR">科室
              <span class="clr-org"> ${allcount} </span>个　|　大夫<span class="clr-org"> 23 </span>人</span>
          </div>
          <div class="salary-table">
<table >
  <tbody>
    <c:forEach var="fissection" items="${firstselist}">
    <tr>
      <td class="position-title" width="12%">${fissection.sectionName}</td>
      <td><table id="hosbra" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
          <c:forEach var="subSection" items="${allselist}" varStatus="status">
            <c:if test="${fissection.sectionParentid eq subSection.sectionParentid}">
                <tr>
	              <td width="50%"><a href="${ctx }/anon/web/sectionDetail?hospitalID=${hospitalDetails.hospitalDetail.hospitalID}&sectionID=${subSection.sectionID}" target="_blank" class="blue">${subSection.sectionName}</a> </td>
	            </tr>
            </c:if>
            </c:forEach>
          </tbody>
        </table></td>
    </tr>
    </c:forEach>
    <c:if test="${allcount==0}">
               <tr>
	              <td width="50%">暂无记录</td>
	            </tr>                 
     </c:if>
  </tbody>
</table>
          </div>
      </div>

<!--推荐大夫-->
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">${hospitalDetails.hospitalDetail.hospitalName } 推荐专家</span>
          </div>
          <div class="salary-table">
<table  >
  <tbody>
    <c:forEach items="${disbzlist}" var="disbl">
       <tr>
	      <td>
	      <a href="/mianjijingluan" class="clr-org" target="_blank">${disbl.diseaseName}</a><span class="clr-ccc">：</span> 
	      <c:forEach items="${hosdoclist}" var="hosdoc">
	          <c:if test="${disbl.diseaseID eq hosdoc.diseaseID}">
	            <a href="DE4rO-XCoLUmjq1ccTieBvzKOb" target="_blank" class="blue" title="${hosdoc.doctorName}">${hosdoc.doctorName}</a>&nbsp;&nbsp;
	          </c:if>
	      </c:forEach>
         </td>
	    </tr>
    </c:forEach>

  </tbody>
</table>

          </div>

</div>

<!--最新咨询-->
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">最新咨询</span>
              <span class="ctg-titleR"></span>
          </div>
          <div class="salary-table">
<table>
  <tbody id="zxtdnr">
    
  </tbody>
</table>

          </div>
      </div>



</div><!--comp-detail-left end-->



<div class="wealink-right">

<div class="wlr-box">
        <div class="wlr-box-hd">
            <div class="wlr-box-more">
            </div>
            <h3 class="clr-grn">${hospitalDetails.hospitalDetail.hospitalName }推荐专家</h3>
        </div>
        <div class="wlr-box-bd" id="hostjdoc">
        
	            <div class="comp-right-box cf">
	                <div class="bole-img">
	                  <a href="#" target="_blank">
	                    <img src="../img/nouimg.jpg" title="贾竑晓" alt="">
					  </a>
	                </div>
	                <ul class="bole-info">
	                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="贾竑晓">贾竑晓</a><span  class="icon-id">主任医师</span></li>
	                    <li class="bole-info-zn" title="北京安定医院·精神科">北京安定医院·精神科</li>
	                    <li class="binfo-pn"><a class="clr-grn" title="关注" onclick="loginFirst();" rel="nofollow" href="javascript:void(0)">电话咨询服务评价777条</a>　</li>
	
	                </ul>
	                <p class="bole-nub">
	                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">电话咨询</a>
	                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
	                </p>
	            </div>
	            
	            <div class="comp-right-box cf">
	                <div class="bole-img">
	                  <a href="#" target="_blank">
	                    <img src="../img/nouimg.jpg" title="贾竑晓" alt="">
					  </a>
	                </div>
	                <ul class="bole-info">
	                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="贾竑晓">贾竑晓</a><span  class="icon-id">主任医师</span></li>
	                    <li class="bole-info-zn" title="北京安定医院·精神科">北京安定医院·精神科</li>
	                    <li class="binfo-pn"><a class="clr-grn" title="关注" onclick="loginFirst();" rel="nofollow" href="javascript:void(0)">电话咨询服务评价777条</a>　</li>
	
	                </ul>
	                <p class="bole-nub">
	                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">电话咨询</a>
	                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
	                </p>
	            </div>
            
	            <div class="comp-right-box cf">
	                <div class="bole-img">
	                  <a href="#" target="_blank">
	                    <img src="../img/nouimg.jpg" title="贾竑晓" alt="">
					  </a>
	                </div>
	                <ul class="bole-info">
	                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="贾竑晓">贾竑晓</a><span  class="icon-id">主任医师</span></li>
	                    <li class="bole-info-zn" title="北京安定医院·精神科">北京安定医院·精神科</li>
	                    <li class="binfo-pn"><a class="clr-grn" title="关注" onclick="loginFirst();" rel="nofollow" href="javascript:void(0)">电话咨询服务评价777条</a>　</li>
	
	                </ul>
	                <p class="bole-nub">
	                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">电话咨询</a>
	                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
	                </p>
	            </div>

	            <div class="comp-right-box cf">
	                <div class="bole-img">
	                  <a href="#" target="_blank">
	                    <img src="../img/nouimg.jpg" title="贾竑晓" alt="">
					  </a>
	                </div>
	                <ul class="bole-info">
	                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="贾竑晓">贾竑晓</a><span  class="icon-id">主任医师</span></li>
	                    <li class="bole-info-zn" title="北京安定医院·精神科">北京安定医院·精神科</li>
	                    <li class="binfo-pn"><a class="clr-grn" title="关注" onclick="loginFirst();" rel="nofollow" href="javascript:void(0)">电话咨询服务评价777条</a>　</li>
	
	                </ul>
	                <p class="bole-nub">
	                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">电话咨询</a>
	                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
	                </p>
	            </div>


            
        </div>
    </div>

</div><!--wealink-right-->


</div><!--main end-->


<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>
</body>
</html>