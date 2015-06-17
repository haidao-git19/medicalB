<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${ctx}/resources/auth/css/index.css" rel="stylesheet">
<link href="${ctx }/resources/auth/index/favicon.ico" rel="shortcut icon"/>
<link href="${ctx }/resources/auth/promptumenu/promptumenu.css" rel="stylesheet" type="text/css" />

<script src="${ctx }/resources/auth/jquery-ui-bs/assets/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/jquery-ui-bs/assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/index/jquery-jtemplates.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/promptumenu/jquery.promptu-menu.js" type="text/javascript"></script>
<script src="${ctx }/resources/auth/index/transit.js" type="text/javascript"></script>

<script type="application/javascript">
	var ctx = '${ctx}';
	var loginName = '<shiro:principal property="loginName"/>';
	var service = '${param.service}';
	if(service) {
		window.location.href = '${ctx}/genTicket?service=' + service;
	}
	
	$(document).ready(function(){
	  $(".btn_content").click(function(){
		  $(".leftside_content").toggle();
	  });
	});
</script>
</head>

<body>
<!--头部-->
<div class="header_container">
    <div class="logo_box">
    	<img src="${ctx}/resources/auth/img/index/logo.png" width="150"/>
    </div>
    <div class="menu_box">
    	<ul class="menu_list">
        	<li class="menu01"></li><!--首页-->
        	<li class="menu02"><!--微模块-->
            	<ul class="submenu_list">
                	<li>
                    	<img src="${ctx}/resources/auth/img/index/mircoindex.png" width="24" height="24" />
                        <span>微官网</span>
                    </li>
                	<li>
                        <img src="${ctx}/resources/auth/img/index/microsettings.png" width="24" height="24" />
                        <span>设置</span>
                    </li>
                	<li>
                        <img src="${ctx}/resources/auth/img/index/microevent.png" width="24" height="24" />
                        <span>微活动</span>
                    </li>
                	<li>
                    	<img src="${ctx}/resources/auth/img/index/micromember.png" width="24" height="24" />
                        <span>微会员</span>
                    </li>
                	<li>
                    	<img src="${ctx}/resources/auth/img/index/microui.png" width="24" height="24" />
                        <span>微交互</span>
                    </li>
                </ul>
            </li>
        	<li class="menu01"></li><!--帮助中心-->
        </ul>
    </div>
    <div class="user_box">
    	<ul>
        	<li>MISS酒店</li>
        	<li>退出</li>
        </ul>
    </div>
</div>

<!--身体-->
<div class="bodyer_container" class="content">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="left" valign="top" class="leftside_content">
            	<div class="above_part">
                	<div class="setting_box">
                    	<div class="binding_account">
                        	<img src="${ctx}/resources/auth/img/index/binding_account.png" width="14" height="14" />
                         	   绑定账号
                        </div>
                    	<div class="sys_setting">
                        	<img src="${ctx}/resources/auth/img/index/sys_setting.png" width="14" height="14" />
                         	   系统设置
                        </div>
                    </div>
                </div>
            	<div class="under_part" class="sys-icons">
                    <ul class="micromenuleft">
                    </ul>
                </div>
            </td>
            <td class="btn_content">
            	<img src="${ctx}/resources/auth/img/index/hidebtn.png" width="5" height="30" />
            </td>

            <td class="rightside_content">
            	<div class="intro_content">
                    <div class="intro_above">
                    	<div class="intro_above_l">
                            <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                <tr>
                                    <td align="center">
                                    	<img src="${ctx}/resources/auth/img/index/offenfunc01.png" width="33" height="33" />
                                        <div>常用功能1</div>
                                    </td>
                                    <td align="center">
                                    	<img src="${ctx}/resources/auth/img/index/offenfunc01.png" width="33" height="33" />
                                        <div>常用功能2</div>
                                    </td>
                                    <td align="center">
                                    	<img src="${ctx}/resources/auth/img/index/offenfunc01.png" width="33" height="33" />
                                        <div>常用功能3</div>
                                    </td>
                                    <td align="center">
                                    	<img src="${ctx}/resources/auth/img/index/offenfunc01.png" width="33" height="33" />
                                        <div>常用功能4</div>
                                    </td>
                                    <td align="center">
                                    	<img src="${ctx}/resources/auth/img/index/offenfunc01.png" width="33" height="33" />
                                        <div>常用功能5</div>
                                    </td>
                                    <td align="center">
                                    	<img src="${ctx}/resources/auth/img/index/offenfunc01.png" width="33" height="33" />
                                        <div>常用功能6</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    	<div class="intro_above_r">
                            <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                <tr>
                                    <td width="80" align="center">
                                    	<img src="${ctx}/resources/auth/img/index/userimg.jpg" width="60" height="60" />
                                    </td>
                                    <td align="center">
                                    	<div>0</div>
                                        <div>分享</div>
                                    </td>
                                    <td align="center">
                                    	<div>0</div>
                                        <div>专辑</div>
                                    </td>
                                    <td align="center">
                                    	<div>0</div>
                                        <div>关注</div>
                                    </td>
                                    <td align="center">
                                    	<div>0</div>
                                        <div>粉丝</div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="intro_middle">
                    	<div class="intro_middle_l">
                        	<div class="userbanner_head">
                            	<img style="float:left;" src="${ctx}/resources/auth/img/index/hot.png" width="25" height="25" />
                                <ul class="bannertablist">
                                	<li>1</li>
                                	<li>2</li>
                                	<li>3</li>
                                	<li>4</li>
                                	<li>5</li>
                                	<li>6</li>
                                </ul>
                            </div>
                        	<div class="userbanner_body">
                            	<img src="${ctx}/resources/auth/img/index/banner01.jpg" width="330" height="200" />
                            </div>
                        </div>
                    	<div class="intro_middle_r">
                        	<div class="sys_notice_head">
                            	系统消息
                            </div>
                        	<div class="sys_notice_body">
                            	<table border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td>系统通知</td>
                                    </tr>
                                    <tr>
                                        <td>系统通知</td>
                                    </tr>
                                </table>

                            </div>
                        </div>
                    </div>
                    <div class="intro_under">
                        <table width="100%" border="0" cellspacing="0" cellpadding="10">
                            <tr>
                                <td>
                                	<img src="${ctx}/resources/auth/img/index/offenfuc_big01.jpg" width="115" height="90" />
                                </td>
                                <td>
                                	<img src="${ctx}/resources/auth/img/index/offenfuc_big01.jpg" width="115" height="90" />
                                </td>
                                <td>
                                	<img src="${ctx}/resources/auth/img/index/offenfuc_big01.jpg" width="115" height="90" />
                                </td>
                                <td>
                                	<img src="${ctx}/resources/auth/img/index/offenfuc_big01.jpg" width="115" height="90" />
                                </td>
                                <td>
                                	<img src="${ctx}/resources/auth/img/index/offenfuc_big01.jpg" width="115" height="90" />
                                </td>
                                <td>
                                	<img src="${ctx}/resources/auth/img/index/offenfuc_big01.jpg" width="115" height="90" />
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>            
            </td>
        </tr>
    </table>
    <div class="sidebar-nav accordion" id="side_menu">
    <div class="iframe-wrap">
		<iframe id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%" style="background-color:#fff;"></iframe>
	</div>
</div>
</div>
<!--脚部-->
<div class="footer_container">
	V1.0beta @ 米品科技 版权所有
</div>
</body>
</html>
