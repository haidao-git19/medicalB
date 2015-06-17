<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>常规管理</title>
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/nursing/editNursing.js"></script>
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
				<li class="active">常识编辑</li>
			</ul>
			<form id="postForm" enctype="multipart/form-data"  >
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
						<input type="hidden" name="adviceID" value="${nurs.adviceID }"/>
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 20%; width: 57.5%; ">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">发布者ID</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="objectID" value="${nurs.objectID }" />
													</div>
												</div>													
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">发布者姓名</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="objectName" value="${nurs.objectName}" />
													</div>
												</div>			
												
												
													
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">标题</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="adviceTitle" value="${nurs.adviceTitle }" />
													</div>
												</div>		
												
												
													
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">内容</font>
													</label>
													<div class="controls">
														<textarea rows="10"  style="width: 88%" cols="10" name="note">${nurs.note }</textarea>
													</div>
												</div>		
												
													
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">建议类型</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="adviceType" value="${nurs.adviceType }" />
													</div>
												</div>		
												
																						
												
									
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">科室ID</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="sectionID" value="${nurs.sectionID }" /> 
													</div>
												</div>	
												
													<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">科室名称</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="sectionName" value="${nurs.sectionName}" /> 
													</div>
												</div>	
												
												
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
			</form>
		</div>
	</div>
</body>
</html>