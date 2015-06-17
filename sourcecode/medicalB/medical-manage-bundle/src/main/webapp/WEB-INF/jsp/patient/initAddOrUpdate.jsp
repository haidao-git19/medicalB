<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.blockUI.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.all.js"></script>
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/patient/editPatient.js"></script>

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
				<li class="active">病人管理</li>
			</ul>
			<form action="${ctx}/patient/addOrUpdate" id="postForm">
			
				<input type="hidden" name="patientID" value="${pat.patientID }"/>
				<input type="hidden" name="img" id="img"  value="${pat.img }"/>
				<input type="hidden" id="defaultPatientSex"  value="${pat.patientSex}" />
				
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">病人姓名</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="patientName" id="patientName" value="${pat.patientName }" />
													</div>
												</div>													
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">图片</font><font 	class="msg_font_normal" style="margin-left: 50%">建议尺寸：305像素* 160像素</font>
													</label>
													<div class="controls" style="border: 1px solid #ccc; width: 89%;">
														<span class="file-wrapper">
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="button" style="opacity:0;"  value="上传图片" onclick="uploadImg();" />
														</span>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">性别</font>
													</label>
													<div class="controls">
														<select  name="patientSex" id="patientSex" ><option value="1">男</option><option  value="0">女</option> </select>
													</div>
												</div>	
									
											
											
											<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">年龄</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="patientAge" id="patientAge" value="${pat.patientAge}" />
													</div>
												</div>	
											
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">证件号</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="patientCard" id="patientCard" value="${pat.patientCard}" />
													</div>
												</div>	
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">账户余额</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="balance" id="balance" value="${pat.balance}" />
													</div>
												</div>	
												
													<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">会员等级</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="patientLevel" id="patientLevel" value="${pat.patientLevel}" />
													</div>
												</div>	
												
												
													<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">联系方式</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="contactPhone" id="contactPhone" value="${pat.contactPhone}" />
													</div>
												</div>	
												
											</div>
										</div>
										
										<span class="span1"
											style="margin-top: 45px; margin-left: -1px;"> <!--   <i class="icon-arrow-right" style="width:100px;"></i>-->
											<img
											src="${ctx}/resources/third-party/img/message/u434_normal.png" />
										</span>
										
										<div class="span4  msg_div_white" style="padding: 0px; min-height: 300px; margin-left: -10px; height: auto;">
											   <p class="span11" id="copyTitle"	style="margin-top: 5%; min-height: 20px; margin-left: 5%;">
													<font class="msg_font_bold">${healthKnowlege.title}</font>
											   </p>
											<div class="span11" align="center" style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<c:if test="${not empty pat.img }">
													<div id="preview_fake">
												          <img id="showImage" src="${ctx }/showImg?fileName=${pat.img }" />
												     </div>
											     </c:if>
											     <c:if test="${empty pat.img }">
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
											<div style="margin-top: 20px;">
												<div align="center" style="margin-top: 10px;">
														<button type="button"  class="btn btn-primary" onclick="formSumbit();">保存</button>
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