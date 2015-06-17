<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="modal_parameter" class="modalMaterialChoose hide fade" tabindex="-1" 
		 role="dialog" aria-labelledby="provinceModalLabel" aria-hidden="true" style="display:none">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3>编辑题目信息</h3>
		</div>
		<div class="row-fluid">
			<form id="edit_questionnaire_case_form" name="edit_questionnaire_case_form" enctype="multipart/form-data">
				<input type="hidden" id="_id" name="id" value="${questionnaireCase.id}">
				<input type="hidden" id="qnId" name="qnId" value="${qnId}">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 80%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">指标：</font>
														<select id="_classId" name="classId" style="width: 60%">
															<option value="">--选择指标--</option>
															<c:forEach items="${caseClassList}" var="caseClass">
																<option value="${caseClass.id}">${caseClass.name}</option>
															</c:forEach>
														</select>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">类型：</font>
														<select id="_type" name="type" style="width: 60%">
															<option value="">--题目类型--</option>
															<option value="1">填空题</option>
															<option value="2">单选题</option>
															<option value="3">多选题</option>
														</select>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">名称：</font>
														<input type="text" style="width: 60%" id="_title" name="title" value="${questionnaireCase.title}"/>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">单位：</font>
														<input type="text" style="width: 60%" id="_unit" name="unit" value="${questionnaireCase.unit}"/>
													</label>
												</div>
											</div>
									</div>
								</div>
								<div style="margin-top: 20px;">
									<div align="center" style="margin-top: 10px;">
										<button type="button" class="btn btn-primary" onclick="submitQuestionnaireCase();">提交</button>
										<button type="button" class="btn btn-primary" onclick="closeModal();">关闭</button>    
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