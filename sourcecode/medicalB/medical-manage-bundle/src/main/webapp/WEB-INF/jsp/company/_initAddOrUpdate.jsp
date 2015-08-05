<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.artDialog.source.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/artDialog.iframeTools.js"></script>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/js/company/editCompany.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">医药公司管理</li>
			</ul>
			<form id="postForm">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							
							<input type="hidden" name="companyId" value="${ph.companyId }"/>
							<input type="hidden" name="loginId" value="${ph.loginId}">
							<input type="hidden" id="latnId2"  value="${ph.latnId}"/>
							
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">公司名称</font>
														</label>
														<div class="controls">
															<input type="text" style="width: 88%"  name="companyName" id="companyName" value="${ph.companyName}" onchange="onchangeCompanyName(this);"/>
														</div>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">登录账号</font>
															<input style="width: 28.5%;" type="text" id="loginAccount" name="loginAccount" value="${ph.loginAccount}" <c:if test="${ph.companyId gt 0}">readonly="readonly"</c:if> />

															<span class="help-inline">*</span><font class="msg_font_bold">登录密码</font>
															<input style="width: 28.5%;" type="password" id="loginPwd" name="loginPwd" value="${ph.loginPwd}" <c:if test="${ph.companyId gt 0}">readonly="readonly"</c:if> />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">本地网</font>
															<select id="latnId"  name="latnId" style="width: 34%">
																
															</select>
														</label>
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">公司电话</font>
														</label>
														<div class="controls">
															<input type="text" style="width: 88%"  name="linkPhone" id="linkPhone" value="${ph.linkPhone }" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">详细地址</font>
														</label>
														<div class="controls">
															<input type="text" style="width: 88%"  name="companyaddress" id="companyaddress" value="${ph.companyaddress}" />
														</div>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">公司描述</font>
														</label>
														<div class="controls" style="margin-left: 15px">
															<textarea rows="" cols="" style="width: 90%"  name="companyDesc" id="companyDesc" onchange="onchangeDescription(this);">${ph.companyDesc}</textarea>
														</div>
													</div>	
												</div>
											</div>
										</div>
									</div>
										<div style="margin-top: 20px;">
											<div align="center" style="margin-top: 10px;">
												<button type="button"  class="btn btn-primary" id="submitBtn">保存</button>
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