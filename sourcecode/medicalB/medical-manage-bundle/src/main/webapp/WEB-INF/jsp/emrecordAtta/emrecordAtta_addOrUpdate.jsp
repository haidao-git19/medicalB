<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>电子病历附件</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
	<script type="text/javascript" src="${ctx }/resources/js/emrecord/emrecordAtta.js"></script>
</head>

<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">电子病历<span class="divider">/</span></li>
				<li class="active">附件</li>
			</ul>
			
			<form id="postForm" >
				<input type="hidden" id="recordID" name="recordID" value="${recordID}"/>
				<input type="hidden" id="attaID" name="attaID" value="${emrecordAtta.attaID}"/>
			
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="mline-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%; margin-top: 20px;">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">附件名称</font>
													</label>
													<div class="controls">
														<input id="attaName" name="attaName" value="${emrecordAtta.attaName}" type="text" style="width: 88%" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">上传附件</font>
													</label>
													<div class="controls" style="border: 1px solid #ccc; width: 89%;">
														<span class="file-wrapper">
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="button" style="opacity:0;"  value="上传图片" onclick="uploadImg();" />
														</span>
													</div>
												</div>
												
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">附件路径</font>
													</label>
													<div class="controls">
														<input id="attaURL" name="attaURL" value="${emrecordAtta.attaURL}" type="text" style="width: 88%" readonly="readonly" /> 
													</div>
												</div>
																		
											</div>
										</div>
										
										<span class="span1" style="margin-top: 45px; margin-left: -1px;">
											<img src="${ctx}/resources/third-party/img/message/u434_normal.png" />
										</span>
										
										<div class="span4  msg_div_white" style="padding: 0px; min-height: 300px; margin-left: -10px; height: auto;">
											<p class="span11" id="copyTitle"	style="margin-top: 5%; min-height: 20px; margin-left: 5%;">
												<font class="msg_font_bold">&nbsp;</font>
											</p>
											<div class="span11" align="center" style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;" id="showImg">
												<c:if test="${not empty emrecordAtta.attaURL}">
													<div id="preview_fake">
												          <img id="showImage" src="${ctx }/showImg?fileName=${emrecordAtta.attaURL}" />
												     </div>
											     </c:if>
											     <c:if test="${empty emrecordAtta.attaURL}">
											   		  <div id="preview_fake">
												          <img id="showImage"  />
												     </div>
													 <div id="_preview_fake"> 
														<font style="" color="#aaaaaa" size="5">预览图</font>
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