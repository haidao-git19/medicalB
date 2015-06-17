<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<jsp:useBean id="now" class="java.util.Date" /> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医宝保管理系统</title>
<link href="${ctx }/resources/auth/index/favicon.ico" rel="shortcut icon"/>
<link href="${ctx }/resources/auth/jquery-ui-bs/assets/css/bootstrap.css" rel="stylesheet">
<link href="${ctx }/resources/auth/promptumenu/promptumenu.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/resources/auth/index/index.css" rel="stylesheet">
<link href="${ctx}/resources/auth/css/fl_index.css" rel="stylesheet">
<link href="${ctx}/resources/auth/css/sl_Common2.css" rel="stylesheet">

<script src="${ctx }/resources/auth/jquery-ui-bs/assets/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/jquery-ui-bs/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/index/jquery-jtemplates.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/promptumenu/jquery.promptu-menu.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/index/index.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/js/highcharts.js" type="text/javascript"></script>

<script src="${ctx }/resources/auth/js/carousel/jquery.kinMaxShow-1.1.src.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctx }/resources/auth/js/carousel/jcarousellite.js"></script>

<style>
	.subNav{position:absolute;background:#FFF;color:#000;line-height:20px;margin-right:20px;padding-bottom:20px;left:0; box-shadow:2px 2px 3px #CCC;z-index:999;left:220px;}
	.subNav a{display:block;float:left;margin-top:5px;margin-left:10px;margin-right:10px;cursor:pointer;}
	#set:hover{/* background-color:#FFF; color:#000;*/cursor:pointer;}
	/* #set1:hover{background-color:#FFF;color:#000;} */
	.nav .icon{background:url(${ctx}/resources/auth/img/login/dropdown_menu.png) no-repeat; display:block;width:65px; height:65px;}
	.nav .s2 .icon{background-position:-43px 0;}
	.nav .s1 .icon{background-position:-86px 0;}
	.nav .s3 .icon{background-position:-129px 0;}
	.nav .s4 .icon{background-position:-172px 0;}
	.nav .s5 .icon{background-position:-215px 0;}
	.nav .s6 .icon{background-position:0px 0;}
</style>

<script type="text/javascript">
	var ctx = '${ctx}';
	var loginName = '<shiro:principal property="loginName"/>';
	var service = '${param.service}';
	  
	if(service) {
		window.location.href = '${ctx}/genTicket?service=' + service;
	}
	
	$(document).ready(function(){
		  $(".btn_content").click(function(){
			  $(".leftside_content").toggle(100);
			  $(".sys-icons").toggle(100);
		  });
		 /*  initGeneralMode();
		 
		  initAnnouncement();
		  initCarousel(); */
		  initHighCharts();
		  
	});
	
	$(document).on("mouseover",".bd .newsList>li",function(){
		$(this).css("background-color","#ecf2fd").siblings().css("background-color","#ffffff"); 
	});
	
	function initCarousel(){
		$.ajax({
			url:ctx+"/carousel/queryCarousels",
			type:"post",
			dataType:"json",
			success:function(data){
				$.each(data,function(i){
					$("#kinMaxTitle").append('<a id="t'+i+'" href="javascript:void(0)">'+data[i].title+'</a>');
					$("#kinMax").append('<div class="big"><a href="javascript:void(0)" target="_blank"><img style="width:100%;height: 200px;" src="'+data[i].image+'"/></a></div>');
				})
				 $("#kinMax").kinMaxShow();
			}
		})
	}
	
	function initAnnouncement(){
		$.ajax({
			url : ctx + '/announcement/queryAnnouncements',
			type : 'POST',
			dataType : "json",
			success : function(data) {
				$.each(data,function(i){
					$(".newsList").append("<li><span class='date'>"+data[i].createTime+"</span><a target='_blank' href='"+data[i].url+"'>"+data[i].title+"</a></li>");
				})
			}
		});
	}
	
	function initGeneralMode(){
		var generalModeAreaHtml = $("#generalModeArea").html();
		
		$.ajax({
			url : ctx + '/home/queryGeneralModel',
			type : 'POST',
			dataType : "json",
			success : function(data) {
				if(data != null && data.generalModel.length > 0){
					$("#generalModeArea").empty();
					$.each(data.generalModel, function(i, model) {
						if(model == null || model == ''){
							return ;
						}
						generalModeAreaHtml = generalModeAreaHtml.format(model.url,model.parentName,model.title);
						generalModeAreaHtml = generalModeAreaHtml.replace('undefined','{0}');
						generalModeAreaHtml = generalModeAreaHtml.replace('undefined','{1}');
						generalModeAreaHtml = generalModeAreaHtml.replace('undefined','{2}');
					});
					$("#generalModeArea").html(generalModeAreaHtml);
					$("#generalModeArea").show();
				}
			}
		});
	}
	
	function initHighCharts(){
		$.ajax({
			url : ctx + '/home/queryStatisticsChartData',
			type : 'POST',
			dataType : "json",
			success : function(data) {
				if(data != null && data != ''){
					loadDRVData(data.DRV1,data.DRV2);
					loadMRVData(data.MRV);
					/* loadPVData(data.PV1,data.PV2);
					loadUVData(data.UV1,data.UV2); */
				}
			}
		});
	}
	
	function loadDRVData(DRV1,DRV2){
		$('#container01').highcharts({                                          
	        chart: {                                                          
	        },                                                                
	        title: {                                                          
	            text: '日注册用户数（DRV）',  //图表标题    
	            style:{fontSize: '14px'}
	        },  
			//x轴
	        xAxis: {          
				//这里设置数据项          
	            categories: ['前天', '昨天','今天']
	        },                                                                
	        tooltip: {                                                        
	            formatter: function() {                                       
	            	 return this.x+"注册人数为:"+ this.y +'人';//这里是数据单位;                                                 
	            }                                                             
	        },                                                                
	        labels: {                                                         
	            items: [{                                                     
	                html: '',     //图表注释                     
	                style: {                                                  
	                    left: '40px',                                         
	                    top: '8px',                                           
	                    color: 'black'                                        
	                }                                                         
	            }]                                                            
	        },    
	//以下用来设置图标格式：线条/柱状
	//并填写值，与上方设置的数据项对应
	//柱状图column 
	//线条图spline                                                           
	        series: [{                                                        
	            type: 'spline', //柱状图column                                        
	            name: '当前DRV',                                                 
	            data: DRV1//这里是数据值                                         
	        },{                                                        
	            type: 'spline', //柱状图column                                        
	            name: '上月同期DRV',                                                 
	            data: DRV2//这里是数据值                                         
	        }                                                           
			]                                                                
	    }); 
	}
	
	function loadMRVData(MRV){
		$('#container02').highcharts({                                          
	        chart: {  
	        },                                                                
	        title: {                                                          
	            text: '月注册用户数（MRV）',  //图表标题    
	            style:{fontSize: '14px'}                              
	        },  
			//x轴
	        xAxis: {          
				//这里设置数据项          
	            categories: ['上月','本月']
	        },                                                                
	        tooltip: {                                                        
	            formatter: function() {                                       
	                return this.x+"注册总人数为:"+ this.y +'人';//这里是数据单位;                                                 
	            }                                                             
	        },                                                                
	        labels: {                                                         
	            items: [{                                                     
	                html: '',     //图表注释                     
	                style: {                                                  
	                    left: '40px',                                         
	                    top: '8px',                                           
	                    color: 'black'                                        
	                }                                                         
	            }]                                                            
	        },    
	//以下用来设置图标格式：线条/柱状
	//并填写值，与上方设置的数据项对应
	//柱状图column 
	//线条图spline                                                           
	        series: [{                                                              
	            type: 'column', //柱状图column                                             
	            name: 'MRV',                                                  
	            data: MRV //这里是数据值                                        
	        }
			]                                                                
	    });   
	}
	
	function loadPVData(pv1,pv2){
		$('#container01').highcharts({                                          
	        chart: {                                                          
	        },                                                                
	        title: {                                                          
	            text: '页面访问数（PV）',  //图表标题    
	            style:{fontSize: '14px'}
	        },  
			//x轴
	        xAxis: {          
				//这里设置数据项          
	            categories: ['前天', '昨天','今天']
	        },                                                                
	        tooltip: {                                                        
	            formatter: function() {                                       
	                var s;                                                    
	                if (this.point.name) {  
	                    s = ''+                                               
	                        this.point.name +': '+ this.y +'个';//这里是数据单位         
	                } else {                                                  
	                    s = ''+                                               
	                        this.x  +': '+ this.y;                            
	                }                                                         
	                return s;                                                 
	            }                                                             
	        },                                                                
	        labels: {                                                         
	            items: [{                                                     
	                html: '',     //图表注释                     
	                style: {                                                  
	                    left: '40px',                                         
	                    top: '8px',                                           
	                    color: 'black'                                        
	                }                                                         
	            }]                                                            
	        },    
	//以下用来设置图标格式：线条/柱状
	//并填写值，与上方设置的数据项对应
	//柱状图column 
	//线条图spline                                                           
	        series: [{                                                        
	            type: 'column', //柱状图column                                        
	            name: '当前pv',                                                 
	            data: pv1//这里是数据值                                         
	        },{                                                        
	            type: 'column', //柱状图column                                        
	            name: '上月同期pv',                                                 
	            data: pv2//这里是数据值                                         
	        }                                                           
			]                                                                
	    }); 
	}
	
	function loadUVData(uv1,uv2){
		$('#container02').highcharts({                                          
	        chart: {                                                          
	        },                                                                
	        title: {                                                          
	            text: '用户访问数（UV）',  //图表标题    
	            style:{fontSize: '14px'}                              
	        },  
			//x轴
	        xAxis: {          
				//这里设置数据项          
	            categories: ['前天','昨天', '今天']
	        },                                                                
	        tooltip: {                                                        
	            formatter: function() {                                       
	                var s;                                                    
	                if (this.point.name) {  
	                    s = ''+                                               
	                        this.point.name +': '+ this.y +'个';//这里是数据单位         
	                } else {                                                  
	                    s = ''+                                               
	                        this.x  +': '+ this.y;                            
	                }                                                         
	                return s;                                                 
	            }                                                             
	        },                                                                
	        labels: {                                                         
	            items: [{                                                     
	                html: '',     //图表注释                     
	                style: {                                                  
	                    left: '40px',                                         
	                    top: '8px',                                           
	                    color: 'black'                                        
	                }                                                         
	            }]                                                            
	        },    
	//以下用来设置图标格式：线条/柱状
	//并填写值，与上方设置的数据项对应
	//柱状图column 
	//线条图spline                                                           
	        series: [{                                                              
	            type: 'spline', //柱状图column                                             
	            name: '当前uv',                                                  
	            data: uv1 //这里是数据值                                        
	        },{                                                              
	            type: 'spline', //柱状图column                                             
	            name: '上月同期uv',                                                  
	            data: uv2 //这里是数据值                                        
	        }
			]                                                                
	    });   
	}
	
</script>

<style type="text/css">
    iframe{
		position:absolute;
		top:0;
		left:0;
		right:0;
		bottom:0;
	}
	
</style>

</head>
<body>
<div class="header_container">
    <div class="logo fl">
    	<img src="${ctx}/resources/auth/img/login/img_05.jpg" style="width:156px; height: 45px; "/>
    </div>
    <span class="nav">
				<ul>
					<li class="le" style="margin-left:25px;">
						<img src="${ctx}/resources/auth/img/login/img_03.jpg" width="2" height="60">
					</li>
					<li class="nav-bg   jt-bg" id="set0">
						<a href="javascript:goIndexPage(this);" style="color: #fff;" target="_top">首页</a>
					</li>
					<li class="jt" id="set1">
						<span id="parentNav2">功能服务</span>
						<div class="subNav" id="subNavDiv2" style="display:none">
				        </div> 
					</li>
					<li id="set">
						<a href="javascript:goIndexPage1(this);" style="color: #fff;" target="_top">系统设置</a>
					</li>
					<li class="nav-bg " >
						<a href="javascript:void(0);" style="color: #fff;" target="_top">帮助中心</a>
					</li>
				</ul>
			</span>
    <div class="user_box">
	    <shiro:user>
	    	<ul class="nav pull-right">
				<li id="btn-user" class="dropdown">
					<a href="#" id="userTipMsg" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
						<i class="icon-user icon-white"></i> <shiro:principal property="name" />，您好！<i class="caret" style="border-top-color:#FFF;"></i>
					</a>
					<ul class="dropdown-menu" style="top:90%;">
						<li style="margin-left:10px;height:30px;"><a id="pwd_link" href="${ctx }/user/changePwd" target="main_frame" onclick="showFrame();" style="width:90%;"><i class="icon-lock"></i> 修改密码</a></li>
						<li class="divider"></li>
						<li style="margin-left:10px;height:30px;"><a id="logout_link" href="${ctx }/logout" target="_top"  style="width:90%;"><i class="icon-trash"></i>安全退出</a></li>
					</ul>
				</li>
			</ul>
		</shiro:user>
    </div>
</div>

<div class="sidebar-nav accordion" id="side_menu">
</div>
<textarea id="menu_tmpl" style="display:none;">
	<div class="accordion-group" style="margin:0;border:0;">
		<a href="#menu_{$T.id}" class="nav-header collapsed" data-toggle="collapse" data-parent="#side_menu">
			<img src='{$T.iconUrl}' width='20' height='20' style='margin-right:10px;'/>
			{$T.name}
			<img src='${ctx}/resources/auth/img/login/down4.png' width='14' height='14' style='margin-right:10px;float:right;margin-top:8px;margin-right:10px;'/>
		</a>
		<ul id="menu_{$T.id}" class="nav nav-list collapse">
		</ul>
	</div>
</textarea>

<div class="content">
	<div class="split left">
		<div class="caret"></div>
	</div>
	<ul class="breadcrumb">
		<li class='active'>&nbsp;</li>
	</ul>
	<div class="sys-icons" style="height:820px;width:210px;OVERFLOW-Y:auto;">
					<div class="left-top">
						<div class="faceimg">
							<img src="${ctx}/resources/auth/img/login/tx-img.png" width="50" height="50">
						</div>
						<p style="margin-left:5px;font-size:12px;">
							公众帐号：<shiro:principal property="name" />
							<br/>
							系统版本：基础版
							<%-- <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" /> --%>  
						</p>
					</div>
		<ul></ul>
		<div>
			<div style="margin:40px 40px">
				<p style="margin-bottom:5px"><img width="20" height="20" src="${ctx}/resources/auth/img/login/msgIcon.jpg">&nbsp;在线客服</p>
				<p style="margin-bottom:10px"><img width="20" height="20" src="${ctx}/resources/auth/img/login/phoneIcon.jpg">&nbsp;0551-63667009</p>
				<p style="align:center"><img width="100" height="100" alt="二维码" src="${ctx}/resources/auth/img/login/qrcode.jpg"></p>
			</div>
		</div>
	</div>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="contentTable">
        <tr>
         	<td align="left" valign="top" class="leftside_content">
            </td>
            <td class="btn_content">
            	<img style="cursor:pointer;" src="${ctx}/resources/auth/img/index/hidebtn.png" width="8" height="30" />
            </td>
            <td>
            	<div class="right">
				<div class="concent">
					<div class="c-top">
						<div class="w-s fl" id="generalModeArea" style="display: none;">
							<div class="blue fl"  style="cursor:pointer;" onclick="openPage(this,'{0}','{1}')">
								<div class="b-ico">
									{2}
								</div>
							</div>
							<div class="green fl" style="cursor:pointer;" onclick="openPage(this,'{3}','{4}')">
								<div class="b-ico1">
									{5}
								</div>
							</div>
							<div class="purper fl" style="cursor:pointer;" onclick="openPage(this,'{6}','{7}')">
								<div class="b-ico2">
									{8}
								</div>
							</div>
							<div class="yellow fl" style="cursor:pointer;" onclick="openPage(this,'{9}','{10}')">
								<div class="b-ico3">
									{11}
								</div>
							</div>
						</div>
						<div class="sz fr" style="font-size: 12px;display: none; " id="reportCountArea">
							<div class="sz1">
								<div class="blue-1">
									<img src="${ctx}/resources/auth/img/login/ico1.png" width="42" height="42">
								</div>
								<p style="margin-left:50px;">
									{0}
									<br/>
									关注数
								</p>
							</div>
							<div class="sz1">
								<div class="blue-1">
									<img src="${ctx}/resources/auth/img/login/ico2.png" width="42" height="42">
								</div>
								<p style="margin-left:50px;">
									{1}
									<br/>
									会员数
								</p>
							</div>
							<div class="sz1">
								<div class="blue-1">
									<img src="${ctx}/resources/auth/img/login/ico3.png" width="42" height="42">
								</div>
								<p style="margin-left:50px;">
									{2}
									<br/>
									浏览数
								</p>
							</div>
							<div class="sz1">
								<div class="blue-1">
									<img src="${ctx}/resources/auth/img/login/ico4.png" width="42" height="42">
								</div>
								<p style="margin-left:50px;">
									{3}
									<br/>
									访问数
								</p>
							</div>
						</div>
					</div>
					<div class="r-2">
						<div class="r-l fl">
							<div class="l-1" id="kinMaxTitle">
								<!-- 轮播标题 -->
							</div>
							<div class="l-2" id="kinMax">
								<!-- 轮播图片 -->
							</div>
   		 	 			</div>
						<div class="r-r fr">
							<div class="hd">
								<h4>
									后台公告
								</h4>
								<a class="more1">
									更多
								</a>
							</div>
							<div class="bd">
								<ul class="newsList">
								</ul>
							</div>
						</div>
					</div>
					<div class="r-3">
						<div class="r-3-l fl">
							<div id="container01" style="width:100%;height:340px;"></div>
						</div>
						<div class="r-3-r fr">
							<div id="container02" style="width:100%;height:340px;"></div>
						</div>
					</div>
				</div>
			</div>
            </td>
        </tr>
    </table>
    
	<div class="iframe-wrap">
		<iframe id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%" style="background-color:#fff;"></iframe>
	</div>
</div>

<!-- <div class="footer" style="">
	安徽博领网络科技有限公司 版权所有
</div> -->
 <script>
 	var _currentPoint = 1;
 	var set0 = document.getElementById('set0');
 	var set = document.getElementById('set');
 	set.onmouseover=function(){
 		_currentPoint = 1;
		//this.style.backgroundImage="url(${ctx}/resources/auth/img/login/jt1_03.png)";
		this.getElementsByTagName('div').item(0).style.display="block";
		}
	set.onmouseout=function(){
		this.getElementsByTagName('div').item(0).style.display="none";
		//this.style.backgroundImage="url(${ctx}/resources/auth/img/login/img_18.jpg)";
		}
	set.onclick=function(){
		this.getElementsByTagName('div').item(0).style.display="none";
		//this.style.backgroundImage="url(${ctx}/resources/auth/img/login/img_18.jpg)";
		}
	
	var set1 = document.getElementById('set1');
	var set1Flag=0;
 	set1.onmouseover=function(){
 		_currentPoint = 2;
 		if(set1Flag==0){
			this.style.backgroundImage="url(${ctx}/resources/auth/img/login/img-back_03.jpg)";
			$(this).css({"background-color":"#FFF","color":"#000"});
 		}
		this.getElementsByTagName('div').item(0).style.display="block";
	}
	set1.onmouseout=function(){
		this.getElementsByTagName('div').item(0).style.display="none";
		if(set1Flag==0){
			this.style.backgroundImage="url(${ctx}/resources/auth/img/login/img_18.jpg)";
			$(this).css({"background-color":"","color":""});
		}
	}
	set1.onclick=function(){
		this.getElementsByTagName('div').item(0).style.display="none";
		if(set1Flag==0){
			this.style.backgroundImage="url(${ctx}/resources/auth/img/login/img_18.jpg)";
		}
	}
	
	function modifyNavTitle(val){
		if(_currentPoint == 1){
			addClass(set,"jt-bg");
			removeClass(set1,"jt-bg");
			removeClass(set0,"jt-bg");
			$("#parentNav1").text(val);
			$("#parentNav2").text('功能服务');
		}else if(_currentPoint == 2){
			removeClass(set,"jt-bg");
			removeClass(set0,"jt-bg");
			addClass(set1,"jt-bg");
			//$("#parentNav1").text('系统设置');
			$("#parentNav2").text(val);
		}
	}
	
	function goIndexPage(obj){
		addClass(set0,"jt-bg");
		removeClass(set,"jt-bg");
		removeClass(set1,"jt-bg");
		window.location.href = "${ctx}/";
	}
	
	function goIndexPage1(obj){
		removeClass(set0,"jt-bg");
		addClass(set,"jt-bg");
		removeClass(set1,"jt-bg");
	}
	
	function goIndexPage2(obj){
		removeClass(set0,"jt-bg");
		removeClass(set,"jt-bg");
		addClass(set1,"jt-bg");
	}
	
	var currentNode = null;
	function openPage(obj,url,parentName){
		var currentParentNode = parentName;
		currentNode = $.trim($(obj).children().text());
		$(".sys-icons ul").find("a")
		.each(function() {
			if($(this).text() == parentName){
				$(this).click();
			}
		});
	}
	
	var id = self.setInterval("onloadSubNav()", 3000);
	function onloadSubNav(){
		var obj = $(".sidebar-nav .nav-list").find("a");
		if(obj != null && obj.length > 0){
			obj.each(function() {
				if($(this).text() == currentNode){
					this.click();
				}
			});
			clearInterval(id);
		}
	}
	function hasClass(obj, cls) {  
	    return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));  
	}  
	  
	function addClass(obj, cls) {  
	    if (!this.hasClass(obj, cls)) obj.className += " " + cls;  
	}  
	  
	function removeClass(obj, cls) {  
	    if (hasClass(obj, cls)) {  
	        var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');  
	        obj.className = obj.className.replace(reg, ' ');  
	    }  
	}  
	  
	function toggleClass(obj,cls){  
	    if(hasClass(obj,cls)){  
	        removeClass(obj, cls);  
	    }else{  
	        addClass(obj, cls);  
	    }  
	}  
 </script>
</body>

</html>
