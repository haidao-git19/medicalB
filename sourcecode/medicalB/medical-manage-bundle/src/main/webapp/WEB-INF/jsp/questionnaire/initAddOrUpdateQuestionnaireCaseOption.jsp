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
			<h3>编辑详细</h3>
		</div>
		<div class="row-fluid">
			<form id="edit_questionnaire_option_form" name="edit_questionnaire_option_form" enctype="multipart/form-data">
				<input type="hidden" id="_id" name="id">
				<input type="hidden" id="qId" name="qId" value="${qId}">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 80%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<c:if test="${type eq 2 or type eq 3}">
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">选项：</font>
															<input type="text" style="width: 60%" id="_option" name="option"/>
														</label>
													</div>
												</c:if>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">异常：</font>
														<select id="_exceptionFlag" name="exceptionFlag" style="width: 60%">
															<option value="">--选择异常值--</option>
															<option value="0">否</option>
															<option value="1">是</option>
														</select>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">级别：</font>
														<select id="_level" name="level" style="width: 60%">
															<option value="">--选择级别--</option>
															<option value="0">正常</option>
															<option value="1">轻度</option>
															<option value="2">中度</option>
															<option value="3">严重</option>
															<option value="4">高危</option>
														</select>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">排序：</font>
														<select id="_index" name="index" style="width: 60%">
															<option value="">--选择排序--</option>
															<option value="1">1</option>
															<option value="2">2</option>
															<option value="3">3</option>
															<option value="4">4</option>
															<option value="5">5</option>
															<option value="6">6</option>
														</select>
													</label>
												</div>
												<c:if test="${type eq 1 }">
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">范围：</font>
															<input type="text" style="width: 28%" id="_rangeFrom" name="rangeFrom"/>—
															<input type="text" style="width: 28%" id="_rangeTo" name="rangeTo"/>
														</label>
													</div>
												</c:if>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">备注：</font>
														<input type="text" style="width: 60%" id="_note" name="note"/>
													</label>
												</div>
											</div>
									</div>
								</div>
								<div style="margin-top: 20px;">
									<div align="center" style="margin-top: 10px;">
										<button type="button" class="btn btn-primary" onclick="submitQuestionnaireCaseOption();">提交</button>
										<button type="button" class="btn btn-primary" onclick="closeModal();">关闭</button>    
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