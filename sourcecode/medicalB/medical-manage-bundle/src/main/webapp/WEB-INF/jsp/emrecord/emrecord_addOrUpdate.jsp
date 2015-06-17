<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>电子病历</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
	<script type="text/javascript" src="${ctx }/resources/js/emrecord/emrecord.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">电子病历<span class="divider">/</span></li>
				<li class="active">${emrecord.id gt 0 ? '编辑' : '新增'}</li>
			</ul>
			
			<form id="postForm" >
				<input type="hidden" name="id" value="${emrecord.id}"/>
				<input type="hidden" id="patientID" name="patientID" value="${emrecord.patientID}"/>
			
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
														<span class="help-inline">*</span><font class="msg_font_bold">患者身份证</font>
													</label>
													<div class="controls">
														<input id="patientCard" name="patientCard" value="${emrecord.patientCard}" maxlength="18" onblur="loadPatient();" type="text" style="width: 26%" />
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">患者姓名</font>
													</label>
													<div class="controls">
														<input id="patientName" name="patientName" value="${emrecord.patientName}" type="text" style="width: 26%" readonly="readonly"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">就诊医院</font>
													</label>
													<div class="controls">
														<input id="hospitalName" name="hospitalName" value="${emrecord.hospitalName}" type="text" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">就诊科室</font>
													</label>
													<div class="controls">
														<input id="sectionName" name="sectionName" value="${emrecord.sectionName}" type="text" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">病种名称</font>
													</label>
													<div class="controls">
														<input id="diseaseName" name="diseaseName" value="${emrecord.diseaseName}" type="text" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">诊断记录</font>
													</label>
													<div class="controls">
														<textarea id="note" name="note" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)">${emrecord.note}</textarea>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">用药记录</font>
													</label>
													<div class="controls">
														<textarea id="drug" name="drug" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)">${emrecord.drug}</textarea>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">病历来源</font>
													</label>
													<select style="width:26%" name="source">
														<option value="1" <c:if test="${emrecord.source eq 1}">selected="selected"</c:if>>医院对接</option>
														<option value="2" <c:if test="${emrecord.source eq 2}">selected="selected"</c:if>>医生提交</option>
														<option value="3" <c:if test="${emrecord.source eq 3}">selected="selected"</c:if>>患者提交</option>
													</select>
												</div>
												
												<c:if test="${emrecord.id gt 0}">
												<div class="control-group">
													<label class="control-label"><font class="msg_font_bold">创建时间: <fmt:formatDate value="${emrecord.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></font></label>
													<label class="control-label"><font class="msg_font_bold">更新时间: <fmt:formatDate value="${emrecord.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></font></label>
												</div>
												</c:if>
												
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

</body>
</html>