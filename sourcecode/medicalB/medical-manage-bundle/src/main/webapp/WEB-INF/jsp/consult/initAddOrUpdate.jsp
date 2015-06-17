<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>咨询管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/consult/editConsult.js"></script>
		<style type="text/css">
		#user_table {
			min-width : 800px;
		}
		</style>
			<style type="text/css">
		.nav-tabs>.active>a,.nav-tabs>.active>a:hover,.nav-tabs>.active>a:focus
			{
			background-color: #0088cc;
			color: #fff;
		}
		
		.msg_div_white {
			border: 1px solid #bbb;
			background-color: #fff;
			border-radius: 4px;
			-moz-border-radius: 4px;
			-webkit-border-radius: 4px;
			-o-border-radius: 4px;
		}
	   .file-wrapper{ cursor: pointer; display: inline-block; overflow: hidden; position: relative; *display:inline; zoom:1; }
	   .file-wrapper input{ cursor: pointer; position: absolute; right: 0; top: 0; height:26px; }
	   .file-wrapper .button{ cursor: pointer; display:inline-block; width:60px; height:26px; *display:inline; zoom:1; }
	</style>
	</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">咨询编辑</li>
			</ul>
			<input type="hidden"  id="defaultIsFree" value="${consult1.isFree }"/>
			
			<form id="postForm" enctype="multipart/form-data"  >
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
						<input type="hidden" name="consultationID" value="${consult1.consultationID }"/>
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 20%; width: 57.5%; ">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">客服</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="userID" value="${consult1.userID }" />
													</div>
												</div>													
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">咨询问题</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="question" value="${consult1.question }"/>
													</div>
												</div>			
												
												
													
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">客服回答</font>
													</label>
													<div class="controls">
													<input type="text" style="width: 88%" name="answer" value="${consult1.answer }"/>
													</div>
												</div>		
												
												
													
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">问题级别</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="level" value="${consult1.level }" />
													</div>
												</div>		
												
													
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">患者评价</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="evaluation" value="${consult1.evaluation }" />
													</div>
												</div>		
												
																						
												
									
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">是否追问</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="isRepeat" value="${consult1.isRepeat }" /> 
													</div>
												</div>	
												
													<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">原始问题ID</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="sourceID" value="${consult1.sourceID }"/> 
													</div>
												</div>	
												
													<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">是否免费</font>
													</label>
													<div class="controls">
														<select name="isFree" id="isFree" style="width: 88%"><option value="1">是</option><option value="0">否</option></select>
													</div>
												
											</div>
										</div>
										<div style="margin-top: 20px;">
												<div align="center" style="margin-top: 10px;">
														<input type="button" value="提交" class="btn btn-primary" onclick="formSumbit();"/>
												 			<input type="button" value="取消" class="btn btn-primary" onclick="window.history.go(-1);">
												</div>
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