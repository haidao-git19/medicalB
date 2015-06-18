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
<title>大夫个人网站-医保宝</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<script src="${ctx }/resources/web/js/doctor/doctor.js" type="text/javascript"></script>
<link href='${ctx }/resources/web/css/hospital.css' rel='stylesheet' type='text/css' />
<script type="text/javascript">
	var hospitalID='${hospitalID}';
	var sectionID='${sectionID}';
	var doctorID='${doctor.doctorID}';
</script>
</head>
<body>
	<div class="main">

<div class="blk20 clear"></div>
       


<div class="comp-detail-left">



<div class="person-file-card cf">
  <div class="card-photo"> 
  	<c:choose>
  		<c:when test="${empty doctor.avatar}">
  			<img src="${ctx }/resources/web/img/nouimg.jpg" title="${doctor.realName}" alt="${doctor.realName }">
  		</c:when>
  		<c:otherwise>
  			<img src="${doctor.avatar}" title="${doctor.realName}" alt="${doctor.realName}">
  		</c:otherwise>
  	</c:choose>
    <p>一天前来过</p>
  </div>
  <div class="card-words">
    <ul class="card-info">
      <li> <a title="${doctor.realName}" href="#" class=" clr-08c fnt-14">${doctor.realName}</a><span class="city">${doctor.hospitalName}-${doctor.sectionName} ${doctor.doctorLevel}</span> </li>
      <li class="clr-666">医院地址：${doctor.address }  </li>
      <li class="clr-666">擅长：${doctor.skill}</li>
      <li class="clr-666"> 简介：${doctor.experience}<a href="#" class="clr-08c">查看完整简&gt;&gt;</a>  </li>
    </ul>
    <ul class="card-setting">
  <a class="entrust-onl" href="" title="在线电话" rel="nofollow">在线电话</a> <span>　<a class="entrust-tel" href="javascript:;"  title="网上预约" rel="nofollow">网上预约</a></span>
    </ul>
    
  </div>
</div>

<!--最新咨询-->
<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">最新咨询</span>
              <span class="ctg-titleR"><a href="" target="_blank">更多咨询>></a></span>
          </div>
          <div class="salary-table" id="latestConsultContainer">
			<table>
			  <tbody>
			    <!-- <tr>
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
			    </tr> -->
			  </tbody>
			</table>
          </div>
      </div>







</div><!--comp-detail-left end-->



<div class="wealink-right">



<!---->
<div class="wlr-box">
        <div class="wlr-box-hd">
            <div class="wlr-box-more">
            </div>
            <h3 class="clr-grn">${doctor.realName }大夫的门诊时间</h3>
        </div>
        <div class="wlr-box-bd">

<div class="menzhen_time ">
  <table class="fs">
    <tbody>
      <tr class="bg_f9">
        <td class="bg_w" width="19%"></td>
        <td width="27%">上午</td>
        <td width="27%">下午</td>
        <td width="27%">夜班</td>
      </tr>
      <tr>
        <td class="bg_f9" height="36">周一</td>
        <td>
        	
        </td>
        <td>
        
        </td>
        <td></td>
      </tr>
      <tr>
        <td class="bg_f9" height="36">周二</td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="bg_f9" height="36">周三</td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="bg_f9" height="36">周四</td>
        <td>
        	
        </td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="bg_f9" height="36">周五</td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="bg_f9" height="36">周六</td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td class="bg_f9" height="36">周日</td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
    </tbody>
  </table>
  <div>
    <div class="comp-right-box cf">
      <ul class="comp-right-info"><p class="clr-org">出诊提示：</p>
      <li> 
		${doctor.remind}
<p class="clr-999">(由${doctor.realName}大夫本人发表于${doctor.remindTime})  </p></li>


<p class="clr-org">备注：</p>

	${hospital.remark}
      
      </ul>
      
    </div>
  </div>
</div>

        </div>
</div>



</div><!--wealink-right-->







</div>
<!--main end-->
<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>
</body>
</html>