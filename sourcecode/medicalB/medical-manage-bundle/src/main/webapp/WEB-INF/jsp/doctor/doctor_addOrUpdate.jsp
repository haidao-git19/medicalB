<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专家管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
	<script type="text/javascript" src="${ctx }/resources/js/doctor/doctor.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">专家管理<span class="divider">/</span></li>
				<li class="active">专家编辑1</li>
			</ul>
			
			<form id="postForm">
				<input type="hidden" name="doctorID" value="${doctor.doctorID}">
				<input type="hidden" name="loginID" value="${doctor.loginID}">
				<input type="hidden" id="avatar" name="avatar" value="${doctor.avatar}">
			
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="margin-top: 10px; line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 75%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">医生姓名</font>
															<input style="width: 130px;" type="text" name="realName" value="${doctor.realName}">
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">医生电话</font>
															<input style="width: 130px;" type="text" name="phone" value="${doctor.phone}">
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">登录账号</font>
															<input style="width: 130px;" type="text" name="loginAccount" value="${doctor.loginAccount}" <c:if test="${doctor.doctorID gt 0}">readonly="readonly"</c:if> />

															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">登录密码</font>
															<input style="width: 130px;" type="password" name="loginPwd" value="${doctor.loginPwd}" <c:if test="${doctor.doctorID gt 0}">readonly="readonly"</c:if> />
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">医生职称</font>
															<input style="width: 130px;" type="text" name="doctorLevel" value="${doctor.doctorLevel}">
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">是否推荐</font>
															<select style="width:50px;" name="isRecommend">
																<option value="1" <c:if test="${doctor.isRecommend eq 1}">selected="selected"</c:if>>是</option>
																<option value="0" <c:if test="${doctor.isRecommend eq 0}">selected="selected"</c:if>>否</option>
															</select>
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">账户余额</font>
															<input style="width: 100px;" type="text" name="balance" value="${doctor.balance}">
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">上传头像</font>
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="button" style="opacity:0;"  value="上传图片" onclick="uploadImg();" />
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">所属城市</font>
															<select style="width:140px;" onchange="javascript:loadHospital(this.value);">
																<option value="0">请选择</option>
																<c:forEach var="city" items="${areaList}">
																	<option value="${city.areaID}" <c:if test="${city.areaID eq hospitalAreaId}">selected=selected</c:if> >${city.areaName}</option>
																</c:forEach>
															</select>
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">所属医院</font>
															<c:if test="${doctor.doctorID eq 0}">
																<select style="width:360px;" id="select_hospital" name="hospitalID" onchange="onchangeHospital(this);">
																	<option value="0">--请选择医院--</option>
																</select>
															</c:if>
															<c:if test="${doctor.doctorID gt 0}">
																<select style="width:360px;" id="select_hospital" name="hospitalID" onchange="onchangeHospital(this);">
																	<option value="0">--请选择医院--</option>
																	<c:forEach var="hospital" items="${hospitals}">
																		<option value="${hospital.hospitalID}" <c:if test="${hospital.hospitalID eq doctor.hospitalID}">selected=selected</c:if>>${hospital.hospitalName}</option>
																	</c:forEach>
																</select>
															</c:if>
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">科室大类</font>
															<select id="firstLevelSection" style="width:140px;" onchange="javascript:secondLevelSection(this.value);">
																<option value="0">--请选择--</option>
																<c:forEach var="section" items="${firstLevelSections}">
																	<option value="${section.id}" <c:if test="${section.id eq firstLevelSectionId}">selected=selected</c:if> >${section.attrname}</option>
																</c:forEach>
															</select>
														
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">所属科室</font>
															<c:if test="${doctor.doctorID eq 0}">
																<select style="width:360px;" id="select_section" name="sectionID">
																	<option value="0">--请选择科室--</option>
																</select>
															</c:if>
															<c:if test="${doctor.doctorID gt 0}">
																<select style="width:360px;" id="select_section" name="sectionID">
																	<option value="0">--请选择科室--</option>
																	<c:forEach var="section" items="${sections}">
																		<option value="${section.id}" <c:if test="${section.id eq doctor.sectionID}">selected=selected</c:if>>${section.attrname}</option>
																	</c:forEach>
																</select>
															</c:if>
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">网络咨询</font>
															<select style="width:140px;" name="isNetCT">
																<option value="0" <c:if test="${doctor.isNetCT eq 0}">selected="selected"</c:if>>不支持</option>
																<option value="1" <c:if test="${doctor.isNetCT eq 1}">selected="selected"</c:if>>支持</option>
															</select>
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">语音咨询</font>
															<select style="width:140px;" name="isAudioCT">
																<option value="0" <c:if test="${doctor.isAudioCT eq 0}">selected="selected"</c:if>>不支持</option>
																<option value="1" <c:if test="${doctor.isAudioCT eq 1}">selected="selected"</c:if>>支持</option>
															</select>
														
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">视频咨询</font>
															<select style="width:140px;" name="isVideoCT">
																<option value="0" <c:if test="${doctor.isVideoCT eq 0}">selected="selected"</c:if>>不支持</option>
																<option value="1" <c:if test="${doctor.isVideoCT eq 1}">selected="selected"</c:if>>支持</option>
															</select>
														</label>	
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">转&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;诊</font>
															<select style="width:140px;" name="isZZ">
																<option value="0" <c:if test="${doctor.isZZ eq 0}">selected="selected"</c:if>>不支持</option>
																<option value="1" <c:if test="${doctor.isZZ eq 1}">selected="selected"</c:if>>支持</option>
															</select>
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">上门服务</font>
															<select style="width:140px;" name="isVisit" id="isVisit">
																<option value="" <c:if test="${empty doctor.isVisit}">selected="selected"</c:if>>--选择--</option>	
																<option value="0" <c:if test="${doctor.isVisit eq 0}">selected="selected"</c:if>>不支持</option>
																<option value="1" <c:if test="${doctor.isVisit eq 1}">selected="selected"</c:if>>支持</option>
															</select>
															
															<span class="help-inline">&nbsp;&nbsp;</span><font class="msg_font_bold">顶级专家</font>
															<select style="width:140px;" name="specialist" id="specialist">
																<option value="0" <c:if test="${doctor.specialist eq 0}">selected="selected"</c:if>>否</option>
																<option value="1" <c:if test="${doctor.specialist eq 1}">selected="selected"</c:if>>是</option>
															</select>
														</label>	
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">特长擅长</font>
														</label>
														<div class="controls">
															<textarea id="skill" name="skill" rows="3" cols="30" style="width: 88%">${doctor.skill}</textarea>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">就医经验</font>
														</label>
														<div class="controls">
															<textarea id="experience" name="experience" rows="3" cols="30" style="width: 88%" >${doctor.experience}</textarea>											
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">咨询范围</font>
														</label>
														<div class="controls">
															<textarea id="ctArea" name="ctArea" rows="3" cols="30" style="width: 88%" >${doctor.ctArea}</textarea>													
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">网络咨询范围</font>
														</label>
														<div class="controls">
															<textarea id="netctArea" name="netctArea" rows="3" cols="30" style="width: 88%" >${doctor.netctArea}</textarea>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">执业经历</font>
														</label>
														<div class="controls">
															<textarea id="practice" name="practice" rows="3" cols="30" style="width: 70%" >${doctor.practice}</textarea>
														</div>
													</div>
													
												</div>
											</div>
										</div>
										
										<span class="span1" style="margin-top: 85px; margin-left: -1px;">
											<img src="${ctx}/resources/third-party/img/message/u434_normal.png" />
										</span>
										
										<div class="span4  msg_div_white" style="margin-top: 45px; padding: 0px; min-height: 100px; margin-left: -30px; height: 100px; width: 100px;">
											<div class="span11" align="center" style="margin-left: 5%; margin-top: 3px; height: 90px; min-height: 90px; line-height: 90px; background-color: #eee; text-align: center;" id="showImg">
												<c:if test="${not empty doctor.avatar}">
													<div id="preview_fake">
												          <img id="showImage" src="${ctx }/showImg?fileName=${doctor.avatar}" />
												     </div>
											     </c:if>
											     <c:if test="${empty doctor.avatar}">
											   		  <div id="preview_fake">
												          <img id="showImage"  />
												     </div>
													 <div id="_preview_fake"> 
														<font style="" color="#aaaaaa" size="3">头像预览</font>
												     </div>
											     </c:if>
											</div>
											<br />
											<div class="span11"	style="margin-top: 10px; height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
													<a href="javascript:void(0);" id="" target="_blank"><font color="#0088cc"></font></a>
												</p>
											</div>
										</div>
									</div>
									
									<div style="padding-bottom: 10px; margin-top: 10px;">
										<div align="center">
											<button type="button" class="btn btn-primary" onclick="formSumbit();">提交</button>
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