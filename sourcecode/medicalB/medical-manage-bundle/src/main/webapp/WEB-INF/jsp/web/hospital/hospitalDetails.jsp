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
              <span class="clr-org"> ${fn:length(section.sectionList)} </span>个　|　大夫<span class="clr-org"> 23 </span>人</span>
          </div>
          <div class="salary-table">
<table >
  <tbody>
    <c:forEach var="section" items="${section.sectionList}">
    <tr>
      <td class="position-title" width="12%">${section.attrname}</td>
      <td><table id="hosbra" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
          <c:forEach var="subSection" items="${section.list}">
            <tr>
              <td width="50%"><a href="/-" target="_blank" class="blue">${subSection.attrname}</a> <span class="clr-999" title="共有12位大夫，其中6人在线">(12人/<a class="clr-999" target="_blank" href="DE4r08xQdKSLVZ0-cvKSvlgzgq2c"> 可咨询6人</a>)</span></td>
            </tr>
            </c:forEach>
          </tbody>
        </table></td>
    </tr>
    </c:forEach>
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
    <tr>
      <td><a href="/mianjijingluan" class="clr-org" target="_blank">面肌痉挛</a><span class="clr-ccc">：</span> <a href="DE4rO-XCoLUmjq1ccTieBvzKOb" target="_blank" class="blue" title="袁越">袁越</a><span class="clr-ccc">(114票)</span>, <a href="DE4rO-XCoLUmjqb7rPeWduCBgU" target="_blank" class="blue" title="刘如恩">刘如恩</a><span class="clr-ccc">(187票), <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a>(44票), <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a>(24票)</span><span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a><span class="clr-ccc">(44票)</span>, <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span><a href="/" target="_blank" class="blue">更多&gt;&gt;</a></td>
    </tr>
    <tr>
      <td><a href="/tuofa" class="clr-org" target="_blank">脱发</a><span class="clr-ccc">：</span> <a href="DE4r08xQdKSLBSLDLhY2Krrpb6FW" target="_blank" class="blue" title="杨顶权">杨顶权</a><span class="clr-ccc">(207票)</span>, <a href="DE4r0BCkuHzdeCqmj9K1qIYC2U8VE" target="_blank" class="blue" title="张立新">张立新</a><span class="clr-ccc">(1票)</span>, <a href="DE4rO-" target="_blank" class="blue" title="汪晨">汪晨</a><span class="clr-ccc">(0票)), <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a>(44票), <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a>(24票)</span> <span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span><span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span><a href="DE4raCNSz6OmG3OUNZWCWNv0/tuofa" target="_blank" class="blue">更多&gt;&gt;</a></td>
    </tr>
    <tr>
      <td><a href="DE4raCNSz6OmG3OUNZWCWNv0/xiaochuan" class="clr-org" target="_blank">哮喘</a><span class="clr-ccc">：</span> <a href="DE4rO-XCoLUmy1568JOrYZEIRi" target="_blank" class="blue" title="林江涛">林江涛</a><span class="clr-ccc">(80票)</span>, <a href="DE4rO-XCoLUmH1rtPQrVFGoqUr" target="_blank" class="blue" title="晁恩祥">晁恩祥</a><span class="clr-ccc">(15票)</span>, <a href="DE4r0BCkuHzduGoMf-DWKEMSMwtPM" target="_blank" class="blue" title="陈欣">陈欣</a><span class="clr-ccc">(14票)</span>, <a href="DE4rO-XCoLUmH178VWVmvC3uh7" target="_blank" class="blue" title="杨道文">杨道文</a><span class="clr-ccc">(20票)</span><span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a><span class="clr-ccc">(44票)</span>, <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span><a href="DE4raCNSz6OmG3OUNZWCWNv0/xiaochuan" target="_blank" class="blue">更多&gt;&gt;</a></td>
    </tr>
    <tr>
      <td><a href="/" class="clr-org" target="_blank">痔疮</a><span class="clr-ccc">：</span> <a href="DE4rO-XCoLUmH51deRtJXK298J" target="_blank" class="blue" title="王晏美">王晏美</a><span class="clr-ccc">(97票)</span>, <a href="DE4r0BCkuHzdeKnjvi8hoLSNZn1O5" target="_blank" class="blue" title="李辉">李辉</a><span class="clr-ccc">(47票)</span>, <a href="DE4rO-XCoLUmHrcew1mnSzFYx4" target="_blank" class="blue" title="范学顺">范学顺</a><span class="clr-ccc">(21票)</span>, <a href="DE4rO-XCoLUmHr9Fh7UcoLDoWk" target="_blank" class="blue" title="郑丽华">郑丽华</a><span class="clr-ccc">(14票)</span> <span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a><span class="clr-ccc">(44票)</span>, <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span><a href="DE4raCNSz6OmG3OUNZWCWNv0/zhichuang" target="_blank" class="blue">更多&gt;&gt;</a></td>
    </tr>
    <tr>
      <td><a href="/tangniaobing" class="clr-org" target="_blank">糖尿病</a><span class="clr-ccc">：</span> <a href="DE4r0BCkuHzdewplGXYj6dZX-ciU9" target="_blank" class="blue" title="洪靖">洪靖</a><span class="clr-ccc">(30票)</span>, <a href="DE4rO-XCoLUmy7r8VWVmvC3uh7" target="_blank" class="blue" title="杨文英">杨文英</a><span class="clr-ccc">(21票)</span>, <a href="DE4rO-XCoLUmH7sFh7UcoLDoWk" target="_blank" class="blue" title="徐远">徐远</a><span class="clr-ccc">(19票)</span>, <a href="DE4rO-XCoLUmH7cVx47TZMZ31t" target="_blank" class="blue" title="李爱国">李爱国</a><span class="clr-ccc">(13票)</span><span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a><span class="clr-ccc">(44票), <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a>(24票)</span><span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span><a href="DE4raCNSz6OmG3OUNZWCWNv0/tangniaobing" target="_blank" class="blue">更多&gt;&gt;</a></td>
    </tr>
    <tr>
      <td><a href="DE4raCNSz6OmG3OUNZWCWNv0/feiai" class="clr-org" target="_blank">肺癌</a><span class="clr-ccc">：</span> <a href="DE4rO-XCoLUmHc7Vx47TZMZ31t" target="_blank" class="blue" title="崔慧娟">崔慧娟</a><span class="clr-ccc">(15票)</span>, <a href="DE4rO-XCoLUmjs5ugURjIHBzPQ" target="_blank" class="blue" title="宋之乙">宋之乙</a><span class="clr-ccc">(11票)</span>, <a href="DE4rO-XCoLUmjsrPTaJfDy6Cbc" target="_blank" class="blue" title="石彬">石彬</a><span class="clr-ccc">(10票)</span>, <a href="DE4rO-XCoLUmj76fJefaGY-Fi8" target="_blank" class="blue" title="郭永庆">郭永庆</a><span class="clr-ccc">(9票)</span><span class="clr-ccc">)</span>, <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a><span class="clr-ccc">(44票)</span>, <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span> <a href="DE4raCNSz6OmG3OUNZWCWNv0/feiai" target="_blank" class="blue">更多&gt;&gt;</a></td>
    </tr>
    <tr>
      <td><a href="yiyuan/DE4raCNSz6OmG3OUNZWCWNv0" target="_blank" class="blue">查看全部推荐专家&gt;&gt;</a></td>
    </tr>

  </tbody>
</table>

          </div>

</div>

<!--最新咨询-->
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">最新咨询</span>
              <span class="ctg-titleR"><a href="" target="_blank">更多咨询>></a></span>
          </div>
          <div class="salary-table">
<table>
  <tbody>
    <tr>
      <td width="60%"><a href="superfawn_g_2375108068" target="_blank" class="blue" title="是尖锐湿疣吗？">是尖锐湿疣吗？</a></td>
      <td class="clr-999" width="40%" style="text-align:right;"><a href="DE4rO-XCoLUmy75Bfmw7E-sSlj" target="_blank" class="clr-666" title="张晓艳">皮肤病与性病科&nbsp;张晓艳</a>&nbsp;回复 </td>
    </tr>
    <tr>
      <td width="60%"><a href="richardwdy_g_2379344327" target="_blank" class="blue" title="使用间充质干细胞治疗_脑梗">使用间充质干细胞治疗_脑梗</a></td>
      <td class="clr-999" width="40%" style="text-align:right;"><a href="DE4r0BCkuHzdexbmMBkTMFHdvkQ4C" target="_blank" class="clr-666" title="吴东宇">物理康复科&nbsp;吴东宇</a>&nbsp;回复 </td>
    </tr>
    <tr>
      <td width="60%"><a href="richardwdy_g_2135118565" target="_blank" class="blue" title="术后，左侧无力问题">术后，左侧无力问题</a></td>
      <td class="clr-999" width="40%" style="text-align:right;"><a href="DE4r0BCkuHzdexbmMBkTMFHdvkQ4C" target="_blank" class="clr-666" title="吴东宇">物理康复科&nbsp;吴东宇</a>&nbsp;回复 </td>
    </tr>
    <tr>
      <td width="60%"><a href="fanbifa_g_2371899529" target="_blank" class="blue" title="孩子头痛的问题">孩子头痛的问题</a></td>
      <td class="clr-999" width="40%" style="text-align:right;"><a href="DE4rO-XCoLUmj15tPQrVFGoqUr" target="_blank" class="clr-666" title="樊碧发">全国疼痛诊疗中心&nbsp;樊碧发</a>&nbsp;回复 </td>
    </tr>
    <tr>
      <td width="60%"><a href="yangkeqin_g_2377017271" target="_blank" class="blue" title="想找到治疗的办法_神经性头疼">想找到治疗的办法_神经性头疼</a></td>
      <td class="clr-999" width="40%" style="text-align:right;"><a href="DE4r08xQdKSLBvQPh3oBq7c5PLvm" target="_blank" class="clr-666" title="杨克勤">全国疼痛诊疗中心&nbsp;杨克勤</a>&nbsp;回复 </td>
    </tr>
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
            <h3 class="clr-grn">北京安定医院可咨询专家</h3>
        </div>
        <div class="wlr-box-bd">
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