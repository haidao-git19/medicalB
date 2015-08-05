<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专家管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
	<script type="text/javascript" src="${ctx }/resources/js/doctor/doctorFee.js"></script>
</head>

<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">资费设置<span class="divider">/</span></li>
				<li class="active">${doctorFee.id gt 0 ? '编辑' : '新增'}</li>
			</ul>
			
			<form id="postForm" >
				<input type="hidden" id="doctorID" name="doctorID" value="${param.doctorID}"/>
				<input type="hidden" id="id" name="id" value="${doctorFee.id}"/>
			
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="mline-height: 300%; margin-right: 0px; height: auto; margin-left: 20%; width: 57.5%; margin-top: 20px;">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">资费类型</font>
													</label>
													<div class="controls">
														<select name="type" <c:if test="${doctorFee.id gt 0}">disabled="disabled"</c:if>>
															<option value="1" <c:if test="${doctorFee.type eq 1}">selected="selected"</c:if>>普通咨询</option>
															<option value="2" <c:if test="${doctorFee.type eq 2}">selected="selected"</c:if>>网络咨询</option>
															<option value="3" <c:if test="${doctorFee.type eq 3}">selected="selected"</c:if>>语音咨询</option>
															<option value="4" <c:if test="${doctorFee.type eq 4}">selected="selected"</c:if>>视频咨询</option>
															<option value="5" <c:if test="${doctorFee.type eq 5}">selected="selected"</c:if>>加号</option>
															<option value="7" <c:if test="${doctorFee.type eq 7}">selected="selected"</c:if>>包月</option>
															<option value="8" <c:if test="${doctorFee.type eq 8}">selected="selected"</c:if>>上门服务</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">资费等级</font>
													</label>
													<div class="controls">
														<select name="level" <c:if test="${doctorFee.id gt 0}">disabled="disabled"</c:if>>
															<option value="1" <c:if test="${doctorFee.level eq 1}">selected="selected"</c:if>>一级</option>
															<option value="2" <c:if test="${doctorFee.level eq 2}">selected="selected"</c:if>>二级</option>
															<option value="3" <c:if test="${doctorFee.level eq 3}">selected="selected"</c:if>>三级</option>
															<option value="4" <c:if test="${doctorFee.level eq 4}">selected="selected"</c:if>>四级</option>
															<option value="5" <c:if test="${doctorFee.level eq 5}">selected="selected"</c:if>>五级</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">资费描述</font>
													</label>
													<div class="controls">
														<input id="levelTitle" name="levelTitle" value="${doctorFee.levelTitle}" type="text" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">资费(单位：元)</font>
													</label>
													<div class="controls">
														<input id="fee" name="fee" value="${doctorFee.fee}" type="text" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)"/>
													</div>
												</div>
												
											</div>
										</div>
										
									</div>
									
									<div style="margin-top: 20px;padding-bottom: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button type="button" class="btn btn-primary" onclick="formSumbit();" >保存</button>
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
	
	<div style="display: none">
		<form action="${ctx }/fileUpload" enctype="multipart/form-data" target="upload-target" id="imgForm" method="post">
			<input type="file" id="imgFile" name="imgFile" onchange="uploadFile();"/>
		</form>
	</div>

</body>
</html>