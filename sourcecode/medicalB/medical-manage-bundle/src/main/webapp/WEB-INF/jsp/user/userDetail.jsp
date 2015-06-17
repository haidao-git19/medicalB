<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">用户详情</li>
			</ul>
			<form id="user_form" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">用户编号</font>
															<input type="text" style="width: 80%"  name="userId" id="userId" value="${userVo.userId}" readonly="readonly"/>
														</label>
													</div>													
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">登录名</font>
															<input type="text" style="width: 80%"  name="loginName" id="loginName" value="${userVo.loginName}" readonly="readonly"/>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">openId</font>
															<input type="text" style="width: 80%"  name="openId" id="openId" value="${userVo.openId}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">昵称</font>
															<input type="text" style="width: 80%"  name="nickName" id="nickName" value="${userVo.nickName}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">真名</font>
															<input type="text" style="width: 80%"  name="realName" id="realName" value="${userVo.realName}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">年龄</font>
															<input type="text" style="width: 80%"  name="age" id="age" value="${userVo.age}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">性别</font>
															<input type="text" style="width: 80%"  name="sex" id="sex" value="${userVo.sex}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">电话</font>
															<input type="text" style="width: 80%"  name="phone" id="phone" value="${userVo.phone}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">邮箱</font>
															<input type="text" style="width: 80%"  name="email" id="email" value="${userVo.email}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">个性签名</font>
														</label>
														<div class="ub_container">
															<div>
																<textarea rows="4" cols="30" id="sign" name="sign" style="margin: 0px 10px 10px; width:90%; height: 36px;" readonly="readonly">${userVo.sign}</textarea>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										<span class="span1"
											style="margin-top: 45px; margin-left: -1px;"> <!--   <i class="icon-arrow-right" style="width:100px;"></i>-->
											<img src="${ctx}/resources/third-party/img/message/u434_normal.png" />
										</span>
										
										<div class="span4  msg_div_white" style="padding: 0px; min-height: 300px; margin-left: -10px; height: auto;">
											   <p class="span11" id="copyTitle"	style="margin-top: 5%; min-height: 20px; margin-left: 5%;">
													<font class="msg_font_bold">${userVo.nickName}的头像</font>
											   </p>
											<div class="span11" align="center" style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<div id="preview_fake">
											          <img id="preview" src="${userVo.icon}" onload="onPreviewLoad(this)"/>
											     </div>
												 <div id="_preview_fake"> 
													<font style="" color="#aaaaaa" size="5">缩略图</font>
											   </div>
											</div>
											<br />
											<div class="span11"	style=" height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
														<font class="msg_font_normal">用户编号：${userVo.userId}</font>&nbsp;&nbsp;
												</p>
											</div>
										</div>
									</div>
									<div style="margin-top: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button type="button" class="btn btn-primary" onclick="history.go(-1);">返回</button>    
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>