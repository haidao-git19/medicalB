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
		  <li class="gen-assL"> 　 朝阳医院妇产科展开床位116张，医生34名，护士78名。在院领导的支持下，在核心领导小组的率领下，在妇产科全体人员的努力下，妇产科发展势头持续稳定。圆满超额完成医院的各项指标，成为医院优良医疗指标的贡献者。总手术量、大中手术量、内窥镜手术量稳居全院之首！妇产科坚持医疗与科研、教学并进，注重学科建设。被确定为学校"妇... <a class="clr-08c" href="#" rel="nofollow">展开详情</a> </li>
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
			    <!-- <tr>
			      <td><a href="/mianjijingluan" class="clr-org" target="_blank">面肌痉挛</a><span class="clr-ccc">：</span>
			       	<a href="DE4rO-XCoLUmjq1ccTieBvzKOb" target="_blank" class="blue" title="袁越">袁越</a><span class="clr-ccc">(114票)</span>,
			        <a href="DE4rO-XCoLUmjqb7rPeWduCBgU" target="_blank" class="blue" title="刘如恩">刘如恩</a><span class="clr-ccc">(187票)</span>,
			         <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a><span class="clr-ccc">(44票)</span>, 
			         <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span>,
			         <a href="DE4rO-" target="_blank" class="blue" title="张黎">张黎</a><span class="clr-ccc">(44票)</span>, 
			         <a href="DE4rO-" target="_blank" class="blue" title="李锐">李锐</a><span class="clr-ccc">(24票)</span>
			         <a href="/" target="_blank" class="blue">更多&gt;&gt;</a></td>
			    </tr> -->
			   <!--  <tr>
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
			    </tr> -->
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
              <span class="ctg-titleR"><a href="" target="_blank" class="">我要咨询</a></span>
          </div>
          <div class="salary-table" id="latestConsultContainer">
			<table>
			  <tbody>
			    <!-- <tr>
			      <td width="60%"><a href="superfawn_g_2375108068" target="_blank" class="blue" title="是尖锐湿疣吗？">是尖锐湿疣吗？</a></td>
			      <td class="clr-999" width="40%" style="text-align:right;"><a href="DE4rO-XCoLUmy75Bfmw7E-sSlj" target="_blank" class="clr-666" title="张晓艳">皮肤病与性病科&nbsp;张晓艳</a>&nbsp;回复 </td>
			    </tr> -->
			  <!--   <tr>
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
		<!-- <tr>
			<td class="tda" style="width:80px;">
				<li class="clearfix"><a target="_blank" href="DE4rO-XCoLUXxOYLPQrVFGoqUr.htm" title="张震宇">张震宇</a>
					 <a href="http://drzzy." title="张震宇大夫的个人网站" target="_blank"> <img src="http://i1.hdfimg.com/images/common/iconhome16.gif" id="homepage_512552" align="absmiddle" height="16" width="16"></a>
		             <p>主任医师</p> <p>教授</p><p style="width:90px;overflow:hidden;"></p>
		        </li>
			</td>
			<td class="tdb" style="width:90px;">
				宫颈癌、子宫内膜癌、卵巢癌、外阴癌、阴道癌等妇科良恶性癌瘤，严重子宫...		 <a target="_blank" href="DE4rO-XCoLUXxOYLPQrVFGoqUr.htm">更多&gt;&gt;</a>  
			 </td>
			<td class="tdd" style="width:210px;">
			  <div class="timeup" style="cursor: pointer" onClick="window.open('DE4rO-XCoLUXxOYLPQrVFGoqUr.htm#schedule')">
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
			              <img src="http://i1.hdfimg.com/images/doctor/star_red.gif" title="专家门诊 挂号费：100.00元" align="absmiddle">
			              
			              </li><li>  
			                </li><li>  
			                  </li><li>  
			                    </li>
			      <li class="bg">下</li>
			      <li>  
			        </li><li>  
			          </li><li> 
			            <img src="http://i1.hdfimg.com/images/doctor/star_purple.gif" title="会诊门诊 " align="absmiddle">
			            
			            </li><li>  
			              </li><li> 
			                <img src="http://i1.hdfimg.com/images/doctor/star_yellow.gif" title="特需门诊 挂号费：300.00元" align="absmiddle">
			                
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
				<td class="tde" style="width:80px;">		<li><a class="blue" target="_blank" href="http://drzzy." title="张震宇大夫的个人网站">访问个人网站&gt;&gt;</a></li>
					<li class="mesicon"><a href="http://zixun.ask.php?host_user_id=512552" target="_blank" style="color: green;" rel="nofollow"> 可咨询 </a></li>
		
					<li>两周回复(<span class="clr-org">79</span>)</li>
				</td>
			</tr> -->
			<tr>
			  <td class="tda" style="width:80px;">
			    <li class="clearfix"><a target="_blank" href="DE4r0eJWGqZNGRG0h2ImZ99C2br23VVO.htm" title="李媛">李媛</a>
			      <a href="http://sdliyuan." title="李媛大夫的个人网站" target="_blank"> <img src="http://i1.hdfimg.com/images/common/iconhome16.gif" id="homepage_199944" align="absmiddle" height="16" width="16"></a>
			      <p>主任医师</p> <p>教授</p><p style="width:90px;overflow:hidden;"></p>  </li>
			    </td>
			  <td class="tdb" style="width:90px;">各种不孕不育,试管婴儿问题咨询		&nbsp; 
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
			          <img src="http://i1.hdfimg.com/images/doctor/star_red.gif" title="专家门诊 挂号费：100.00元" align="absmiddle">
			          
			          </li><li>  
			            </li><li> 
			              <img src="http://i1.hdfimg.com/images/doctor/star_red.gif" title="专家门诊 挂号费：100.00元" align="absmiddle">
			              
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
			  <td class="tde" style="width:80px;">		<li><a class="blue" target="_blank" href="http://sdliyuan." title="李媛大夫的个人网站">访问个人网站&gt;&gt;</a></li>
			    <li class="mesicon"><a href="http://zixun.ask.php?host_user_id=199944" target="_blank" style="color: green;" rel="nofollow"> 可咨询 </a></li>
			    
			    <li>两周回复(<span class="clr-org">131</span>)</li>
			    <li class="telicon"><span class="clr-org"> <a href="http://400.haodf/sdliyuan" target="_blank" style="color: #ff6600;" rel="nofollow"> 可通电话 </a> </span></li>
			    <li style="padding-left: 18px; background: url(http://i1.hdfimg.com/space/booking/images/jiahao_03.gif) no-repeat 0px 3px;">
			      <a target="_blank" href="http://jiahao.doctor/sdliyuan/index.htm" rel="nofollow">可加号</a>
			      </li>
			    </td>
			  </tr>
			</tbody>
		</table>

      </div>
   </div>

</div><!--comp-detail-left end-->
<div id="doctorDutyContainer" style="display:none">
	<table>
		<tbody>
		<tr>
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
			          <img src="http://i1.hdfimg.com/images/doctor/star_red.gif" title="专家门诊 挂号费：100.00元" align="absmiddle">
			          
			          </li><li>  
			            </li><li> 
			              <img src="http://i1.hdfimg.com/images/doctor/star_red.gif" title="专家门诊 挂号费：100.00元" align="absmiddle">
			              
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
			  <td class="tde" style="width:80px;">		<li><a class="blue" target="_blank" href="javascript:toDoctorMainPage({0})" title="{1}大夫的个人网站">访问个人网站&gt;&gt;</a></li>
			    <li class="mesicon"><a href="http://zixun.ask.php?host_user_id=199944" target="_blank" style="color: green;" rel="nofollow"> 可咨询 </a></li>
			    
			    <li>两周回复(<span class="clr-org">131</span>)</li>
			    <li class="telicon"><span class="clr-org"> <a href="http://400.haodf/sdliyuan" target="_blank" style="color: #ff6600;" rel="nofollow"> 可通电话 </a> </span></li>
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
		                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="{1}">{1}</a><span  class="icon-id">{2}</span></li>
		                    <li class="bole-info-zn" title="{3}·{4}">{3}·{4}</li>
		                    <li class="binfo-pn"><a class="clr-grn" title="关注" rel="nofollow" href="javascript:void(0)"></a>　</li>
		
		                </ul>
		                <p class="bole-nub">
		                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">在线咨询</a>
		                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
		                </p>
		        </div>
        	</div>
           <%--      <div class="comp-right-box cf">
                <div class="bole-img">
                  <a href="#" target="_blank">
                    <img src="${ctx }/resources/web/img/nouimg.jpg" title="贾竑晓" alt="">
				  </a>
                </div>
                <ul class="bole-info">
                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="贾竑晓">贾竑晓</a><span  class="icon-id">主任医师</span></li>
                    <li class="bole-info-zn" title="北京安定医院·精神科">北京安定医院·精神科</li>

                </ul>
                <p class="bole-nub">
                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">在线咨询</a>
                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
                </p>
            </div>
            
            <div class="comp-right-box cf">
                <div class="bole-img">
                  <a href="#" target="_blank">
                    <img src="${ctx }/resources/web/img/nouimg.jpg" title="贾竑晓" alt="">
				  </a>
                </div>
                <ul class="bole-info">
                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="贾竑晓">贾竑晓</a><span  class="icon-id">主任医师</span></li>
                    <li class="bole-info-zn" title="北京安定医院·精神科">北京安定医院·精神科</li>

                </ul>
                <p class="bole-nub">
                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">在线咨询</a>
                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
                </p>
            </div>

            <div class="comp-right-box cf">
                <div class="bole-img">
                  <a href="#" target="_blank">
                    <img src="${ctx }/resources/web/img/nouimg.jpg" title="贾竑晓" alt="">
				  </a>
                </div>
                <ul class="bole-info">
                    <li class="fnt-14 clr-08c"><a class=" clr-08c fnt-14 " href="#" target="_blank" title="贾竑晓">贾竑晓</a><span  class="icon-id">主任医师</span></li>
                    <li class="bole-info-zn" title="北京安定医院·精神科">北京安定医院·精神科</li>
                    

                </ul>
                <p class="bole-nub">
                    <a class="entrust-onl" target="_blank" href="#" rel="nofollow">在线咨询</a>
                    <a class="entrust-tel" target="_blank" href="#" rel="nofollow">网络咨询</a>
                </p>
            </div> --%>
            
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