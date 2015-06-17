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
	<div id="modal_preview" class="modalMaterialChoose hide fade" tabindex="-1" 
		 role="dialog" aria-labelledby="provinceModalLabel" aria-hidden="true" style="display:none">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<div style="text-align: center"><h3>就诊回访问卷预览</h3></div>
		</div>
		<div class="row-fluid">
			<form id="preview_questionnaire_form" name="preview_questionnaire_form" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 96%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto" >
												
												<div id="item_fill" style="text-align: left;margin-left:10px;position: relative;display: none;">
													<div style="margin-bottom: 10px;margin-top: 20px;"><h4>填空题</h4></div>
													
													<div id="fill_content">
														
													</div>	
												</div>
												
												<div id="item_single_option" style="text-align: left;margin-left:10px;position: relative;display: none;">
													<div style="margin-bottom: 10px;margin-top: 20px;"><h4>单选题</h4></div>
													
													<div id="singleSelectContent">
													
													</div>
												</div>
												
												<div id="item_multi_option" style="text-align: left;margin-left:10px;position: relative;display: none;">
													<div style="margin-bottom: 10px;margin-top: 20px;"><h4>多选题</h4></div>
													
													<div id="multiSelectContent">
														
													</div>
												</div>
											</div>
										</div>
									</div>
									<div style="margin-top: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button type="button" class="btn btn-primary" onclick="$('#modal_preview').modal('hide');">关闭</button>    
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="fillHtml" style="display: none;">
					<div class="control-group">
						<label class="control-label">
							<font class="msg_font_bold">{0}：</font>
							_______________________________________{1}
						</label>
					</div>
				</div>
				
				<div id="singleSelectHtml" style="display: none;">
					<div class="control-group">
						<label class="control-label">
							<font class="msg_font_bold">{0}</font>
						</label>
						<div class="controls">
							
						</div>
					</div>	
				</div>
				
				<div id="multiSelectHtml" style="display: none;">
					<div class="control-group">
						<label class="control-label">
							<font class="msg_font_bold">{0}</font>
						</label>
						<div class="controls">
						</div>
					</div>	
				</div>
			</form>
		</div>
	</div>
</body>
</html>