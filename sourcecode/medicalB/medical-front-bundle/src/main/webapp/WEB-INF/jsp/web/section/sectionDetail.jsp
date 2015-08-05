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
<title>科室-医保宝</title>
<meta http-equiv='Cache-Control' content='no-siteapp' />
<script src="${ctx }/resources/web/js/section/sectionDetail.js" type="text/javascript"></script>
<link href='${ctx }/resources/web/css/hospital.css' rel='stylesheet' type='text/css' />
<script type="text/javascript">
	var hospitalID='${hospitalDetail.hospitalID}';
	var sectionID='${section.id}';
</script>
</head>
<body>
	<div class="main">
    <div class="top-ch-loc">
        当前位置：
        <a class="clr-08c" href="${ctx }" target="_blank">医保宝</a><span class="clr-ccc"> &gt; </span><a class="clr-08c" href="#" target="_blank">科室</a><span class="clr-ccc"> &gt; </span>			<span class="clr-999">${hospitalDetail.hospitalName} ${section.attrname}</span>
    </div>
            
	<div class="area">
		<div class="contentC">
			<div id="headpA_blue">
	            <div id="ltb">
	                <span><a href="#">${hospitalDetail.hospitalName} ${section.attrname}</a> </span><span style="font-size:18px;"></span>
	            </div>
	        </div>
		</div>
	</div>

	<div class="comp-detail-left">

		<ul class="comp-gen-words cf">
		  <li class="gen-assL"> ${sectionIntroduction} <a class="clr-08c" href="#" rel="nofollow">展开详情</a> </li>
		  <li class="gen-assL">
		    <label for="comment">预约加号：</label>
		    本科室开通加号服务的大夫<span class="clr-org">${subscribe_plus_count}</span>名，已成功加号患者<span class="clr-org">${plus_success_count}</span>人次</li>
		  <li>
		    <label for="shortname">电话咨询：</label>
		    本科室可直接通话大夫<span class="clr-org">${isAudioCT_count}</span>名</li>
		</ul>

		<!--推荐大夫-->
		<div class="ctg-box cf">
          
          <div class="ctg-title">
              <span class="ctg-titleL">推荐专家</span>
          </div>
          
          <div class="salary-table" id="recomDoctorsContainer">
			<table>
			  <tbody>
			    
			  </tbody>
			</table>
          </div>
		</div>

		<!--最新咨询-->
		<div class="ctg-box cf">
          <div class="ctg-title">
              <span class="ctg-titleL">最新咨询</span>
              <span class="ctg-titleR"><a href="${ctx}/anon/consulation" target="_blank" class="">我要咨询</a></span>
          </div>
          <div class="salary-table" id="latestConsultContainer">
			<table>
			  <tbody>
			    
			  </tbody>
			</table>
          </div>
      </div>


		<!--专家列表/门诊时间-->
	<div class="ctg-box cf">
        <div class="ctg-title">
            <span class="ctg-titleL">专家列表/门诊时</span>
        </div>
        <div class="doctor-table">
			<table >
				<tbody>
					<tr>
				        <th class="position-title" style="width: 80px;">大夫</th>
						<th class="position-title" style="width:90px">咨询范围/擅长</th>
						<th class="position-title" style="width:210px;">出诊时间</th>
						<th class="position-title" style="width: 80px;">联系大夫</th>
					</tr>
					
				</tbody>
			</table>
      	</div>
   </div>

</div><!--comp-detail-left end-->
<div id="doctorDutyContainer" style="display:none">
	<table>
		<tbody>
		<tr id="tr_{0}">
			  <td class="tda" style="width:80px;">
			    <li class="clearfix"><a target="_blank" href="javascript:toDoctorMainPage({0})" title="{1}">{1}</a>
			      <a href="javascript:toDoctorMainPage({0})" title="{1}的个人网站" target="_blank"> <img src="http://i1.hdfimg.com/images/common/iconhome16.gif" id="homepage_199944" align="absmiddle" height="16" width="16"></a>
			      <p>{2}</p> <p style="width:90px;overflow:hidden;"></p>  </li>
			    </td>
			  <td class="tdb" style="width:90px;">{3}		&nbsp; 
			    </td>
			  <td class="tdd" style="width:210px;">
			    <div class="timeup" style="cursor: pointer" onClick="window.open('DE4r0eJWGqZNGRG0h2ImZ99C2br23VVO.htm#schedule')">
			      <ul>
			        <li class="bg"></li>
			        <li class="bg">一</li>
			        <li class="bg">二</li>
			        <li class="bg">三</li>
			        <li class="bg">四</li>
			        <li class="bg">五</li>
			        <li class="bg">六</li>
			        <li class="bg">日</li>
			        <li class="bg">上</li>
			        <li> 
			          </li><li>  
			            </li><li> 
			              </li><li>  
			                </li><li>  
			                  </li><li>  
			                    </li><li>  
			                      </li>
			        <li class="bg">下</li>
			        <li>  
			          </li><li>  
			            </li><li>  
			              </li><li>  
			                </li><li>  
			                  </li><li>  
			                    </li><li>  
			                      </li>
			        <li class="bg">夜</li>
			        <li>  
			          </li><li>  
			            </li><li>  
			              </li><li>  
			                </li><li>  
			                  </li><li>  
			                    </li><li>  
			                      </li>
			        </ul>
			      </div>
			    
			    </td>
			  	<td class="tde" style="width:80px;">		
				  	<li>
				  		<a class="blue" target="_blank" href="javascript:toDoctorMainPage({0})" title="{1}大夫的个人网站">访问个人网站&gt;&gt;</a>
				  	</li>
				   
				    <li class="mesicon" style="display:none">
				    	<a href="http://zixun.ask.php?host_user_id=199944" target="_blank" style="color: green;" rel="nofollow"> 可咨询 </a>
				    </li>
				    
				    <li>两周回复(<span class="clr-org">{4}</span>)</li>
				    
				    <li class="telicon" style="display:none">
				    	<span class="clr-org"> 
				    		<a href="http://400.haodf/sdliyuan" target="_blank" style="color: #ff6600;" rel="nofollow"> 可通电话 </a> 
				    	</span>
				    </li>
				    
				    <li style="padding-left: 18px; background: url(http://i1.hdfimg.com/space/booking/images/jiahao_03.gif) no-repeat 0px 3px;">
				      <a target="_blank" href="http://jiahao.doctor/sdliyuan/index.htm" rel="nofollow">可加号</a>
				    </li>
			    </td>
			  </tr>
		</tbody>
	</table>
</div>


<div class="wealink-right">

<div class="wlr-box">
        <div class="wlr-box-hd">
            <div class="wlr-box-more">
            </div>
            <h3 class="clr-grn">可咨询专家</h3>
        </div>
        <div class="wlr-box-bd">
        	
        	<div  style="display:none" id="isConsultTemp">
		        <div class="comp-right-box cf">
		                <div class="bole-img">
		                  <a href="#" target="_blank">
		                    <img src="{0}" title="{1}" alt="">
						  </a>
		                </div>
		                <ul class="bole-info">
		                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="javascript:toDoctorMainPage({5})" target="_blank" title="{1}">{1}</a><span  class="icon-id">{2}</span></li>
		                    <li class="bole-info-zn" title="{3}·{4}">{3}·{4}</li>
		                    <li class="binfo-pn"><a class="clr-grn" title="关注" rel="nofollow" href="javascript:void(0)"></a>　</li>
		
		                </ul>
		                <p class="bole-nub">
		                    <a class="entrust-onl" target="_blank" href="${ctx}/anon/consulation?doctorID={5}" rel="nofollow">在线咨询</a>
		                    <a class="entrust-tel" target="_blank" href="${ctx}/anon/consulation?doctorID={5}" rel="nofollow">网络咨询</a>
		                </p>
		        </div>
        	</div>
            
        </div>
    </div>

	<!--推荐医院-->
	
	</div>
	<!--wealink-right-->

</div>
<!--main end-->
<jsp:include page="/WEB-INF/jsp/web/foot.jsp"></jsp:include>
</body>
</html>