<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<html>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户登录-医保宝</title>
		<meta name="keywords" content="用户登录,医保宝" />
		<meta name="Description" content="医保宝" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="${ctx }/resources/auth/css/global-v5.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/resources/auth/css/_login.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx }/resources/auth/jquery-ui-bs/js/jquery-1.8.3.min.js"></script>
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

<body>
<div class="head">
		<div class="header">
			<div class="logo" title="医保宝" alt="医保宝"><a href="#"><img src="${ctx}/resources/auth/img/_login/logo.gif"/></a></div>
			<div class="phone"><a href="#"><i class="icon-phone png"></i><span>手机版</span></a></div>
		</div>		</div>
		<div class="main clearfix">
		<div class="login-header">
			<div class="login-title">
				<h2>用户登录</h2>
				<h3>网上咨询医生、电话咨询医生、康复计划、预约加号！</h3>
			</div>
			<div class="login-contact">
				<h3>客户服务邮箱 service@abc.com</h3>
			</div>
		</div>
			<div class="login-panels">
				<div class="login-main login-main-app">
					<form name="form" id="loginForm" method="post" action="${ctx }/login" target="_top">
						<dl class="logRow">
							<dd><label for="email">邮　箱：</label>
								<input type="text" class="input-email input_tank" id="loginName" name="loginName" value="" placeholder="请输入您的邮箱"
									onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                             		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
                             		onFocus="if (value =='请输入您的邮箱'){value =''}"
                             		onBlur="if (value ==''){value='请输入您的邮箱'}"/>
								<div class="wrong-tip-box" id="id_tips_login_email">
									<c:if test="${not empty l_message}">
							           <strong>错误:</strong>${l_message }</p>
							      	</c:if> 
								</div>
							</dd>
						</dl>
						<dl class="logRow">
							<dd><label for="password">密　码：</label>
								<input type="password" id="password" name="password" value="" class="input-psw input_tank"  placeholder="请输入您的密码" 
									onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                             		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
                            		 onFocus="if (value =='请输入您的密码'){value =''}"
                            		 onBlur="if (value ==''){value='请输入您的密码'}"/>
								<label class="pswtip"></label>
								<div class="wrong-tip-box" id="id_tips_login_pwd">
				                   <c:if test="${not empty p_message}">
							           <strong>错误:</strong>${p_message }</p>
							      	</c:if> 	
								</div>
							</dd>


						</dl>
						<dl>
							<label for="remember-me"><input type="checkbox" checked value="1" id="timeout" name="timeout" class="remember-me"/>下次自动登录</label>
							<label class="forget-psw" for=""><a href="#">忘记密码</a></label>
						</dl>
						<dl>
							<input type="hidden" value="1" name="submitdata" id="submitdata" />
							<input type="hidden" value="" name="md5pwd" id="md5pwd" />
							<input type="hidden" value="#" name="done" id="done" />
							<input onclick="doLogin();" type="button" class="login-btn" value="登  录"/>
						</dl>
					</form>	
				</div>
				<%-- <div class="info_box">
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
            	</div> --%>
				<div class="login-vice">
					<ul>
						<li class="regist-li"><span class="regist-title">没有账号？</span>
							<span class="seeker-regist"><a href=reg.html >注册 >></a></span></li>
						<li class="regist-li"><span class="regist-title">我是医生，我还没有注册？</span>
							<span class="recruiter-regist"><a href="#" >医生注册 >></a></span></li>
					</ul>	
				</div>
			</div>
		</div>
        <div class="login-app-banner">
            <a href="#" title="APP" target="_blank"><img src="${ctx}/resources/auth/img/_login/login-banner2.jpg" alt="APP" /></a>
        </div>
		<div class="foot foot-app">
        
        <p class="company-job-index"> 
				提示：任何关于疾病的建议都不能替代执业医师的面对面诊断。所有门诊时间仅供参考，最终以医院当日公布为准。网友、医生言论仅代表其个人观点，不代表本站同意其说法，请谨慎参阅，本站不承担由此引起的法律责任。
			</p>
    <p>
				<a href="#" target="_blank" title="关于我们" rel="nofollow"> 关于我们 </a><i>|</i>
				<a href="#" target="_blank" title="联系我们" rel="nofollow"> 联系我们 </a><i>|</i>
				<a href="#" target="_blank" title="帮助中心" rel="nofollow"> 帮助中心 </a><i>|</i>
				<a href="#" target="_blank" title="友情链接" rel="nofollow"> 友情链接 </a><i>|</i>
				<a href="#" target="_blank" title="网站地图"> 网站地图 </a>
				
			</p>
			<p class="copyright">
				Copyright<span class="copySymbol">&copy;</span>2015 医保宝
				<a href="http://www.miibeian.gov.cn/" target="_blank" rel="nofollow" class="grayC">皖ICP备000000号</a>
			</p>


</div>
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