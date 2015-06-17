<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录</title>
		<script type="text/javascript" src="${ctx }/resources/auth/jquery-ui-bs/js/jquery-1.8.3.min.js"></script>
		<link href="${ctx }/resources/auth/css/fl_login.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		.nobg{
			background:#FFF;
		}
		</style>
		<script type="text/javascript">
			var service = '${param.service}';
			var href = window.location.href;
			if(window.top != window){
				window.parent.location.href = href;
			}
			$(function(){
				 $(".input_tank").focus(function(){//聚焦
					 $(this).addClass("nobg");//添加nobg样式
				 })
					
				 $(".input_tank").blur(function(){//失焦
				 	var tankvalue=$(".input_tank").val();//取input的value值
				 	if(tankvalue!=null&&tankvalue!=''){//判断value值为不为空
						return;//直接返回
					}
					 $(this).removeClass("nobg");//为空则将nobg样式清除
					
				 })
			})
		</script>
</head>

<body bgcolor="#f8f4f3">
<img src="${ctx }/resources/auth/img/login/main_bg.jpg" width="100%"/>
<form id="loginForm" method="post" action="${ctx }/login" target="_top">
<table class="mainbody" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="center" valign="top">
        <div class="mainbody_content">
        	<div class="logo_box">
            	<img class="titlepng" src="${ctx }/resources/auth/img/login/title.png" width="365" height="115" />
            </div>
        	<div class="login_box">
            	<div class="input_box">
                	<span class="input_name">用户名:</span>
                     <input type="text" id="loginName" name="loginName" class="input_tank userbg" value="${loginName }"
                             onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                             onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
                             onFocus="if (value =='请输入您的用户名'){value =''}"
                             onBlur="if (value ==''){value='请输入您的用户名'}" >
                </div>
            	<div class="input_box">
                	<span class="input_name">密码:</span>
                     <input type="password" id="password" name="password" class="input_tank passwordbg"
                             onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                             onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
                             onFocus="if (value =='请输入您的密码'){value =''}"
                             onBlur="if (value ==''){value='请输入您的密码'}" />
                </div>
                <button class="login_btn" onmousedown="down();" onmouseup="up();">登录</button>
            </div>
        	<div class="info_box">
            	<div class="verify" style="display:none;">
               		<input class="verify_input" name="" type="text" value="" placeholder="请输入验证码" />
                    <img class="verify_img" src="${ctx }/resources/auth/img/login/verify.jpg" width="80" height="30" />
                </div>
            	<div class="remember_me" style="display:none;">
               		<input name="" type="checkbox" value="" />记住我
                </div>
            	<div class="wrong_info" style="display:;">
                    	<c:if test="${not empty message}">
				      		<div class="ui-widget result">
			                  <div class="ui-state-error ui-corner-all">
			                    <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
			                    <strong>错误:</strong>${message }</p>
			                  </div>
			                </div>
			      		</c:if>
                </div>
            </div>
        	<div class="slink_box">
            	<ul class="slink_ul">
                	<li  class="slink_li" onclick="javascript:window.open('http://www.wzt360.com')">服务协议</li>
                	<li  class="slink_li" onclick="javascript:window.open('http://www.wzt360.com')">权利声明</li>
                	<li  class="slink_li" onclick="javascript:window.open('http://www.wzt360.com')">版本更新</li>
                	<li  class="slink_li" onclick="javascript:window.open('http://www.wzt360.com')">帮助中心</li>
                	<li  class="slink_li" onclick="javascript:window.open('http://www.wzt360.com')">问题反馈</li>
                	<li  class="slink_li" style="border-right:none;" onclick="javascript:window.open('http://www.wzt360.com')">网站地图</li>
                </ul><br>
                <ul class="copyright_ul">
                	<li  class="copyright_li">安徽博领网络科技有限公司版权所有 Copyright © 2011-2014  All Rights Reserved</li>
                </ul>
            </div>
        </div>
	</td></tr>
</table>
</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginName").focus();
			$("input").keypress(function(event){
				if(event.which == 13){
					doLogin();
				}
			});
		});
		function doLogin(){
			$("#loginForm").submit();
		}
		
	</script>
</body>

</html>