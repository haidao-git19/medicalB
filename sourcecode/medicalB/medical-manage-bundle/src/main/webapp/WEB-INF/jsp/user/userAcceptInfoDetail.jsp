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
				<li class="active">收货人详情</li>
			</ul>
			<form id="userAcceptInfo_form" enctype="multipart/form-data">
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
															<input type="text" style="width: 77%"  name="userId" id="userId" value="${userAcceptInfoVo.userId}" readonly="readonly"/>
														</label>
													</div>													
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">收货人姓名</font>
															<input type="text" style="width: 75%"  name="acceptName" id="acceptName" value="${userAcceptInfoVo.acceptName}" readonly="readonly"/>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">收货人手机</font>
															<input type="text" style="width: 75%"  name="phone" id="phone" value="${userAcceptInfoVo.phone}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">收货人电话</font>
															<input type="text" style="width: 75%"  name="telephone" id="telephone" value="${userAcceptInfoVo.telephone}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">省份</font>
															<input type="text" style="width: 80%"  name="province" id="province" value="${userAcceptInfoVo.province}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">城市</font>
															<input type="text" style="width: 80%"  name="city" id="city" value="${userAcceptInfoVo.city}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">邮编</font>
															<input type="text" style="width: 80%"  name="zipcode" id="zipcode" value="${userAcceptInfoVo.zipcode}" readonly="readonly" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">地址</font>
														</label>
														<div class="ub_container">
															<div>
																<textarea rows="4" cols="30" id="address" name="address" style="margin: 0px 10px 10px; width:90%; height: 36px;" readonly="readonly">${userAcceptInfoVo.address}</textarea>
															</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">是否启用</font>
															<input type="text" style="width: 77%"  name="enable" id="enable" value="${userAcceptInfoVo.enable=='0'?'是':'否'}" readonly="readonly" />
														</label>
													</div>
												</div>
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