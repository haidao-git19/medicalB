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
		<script type="text/javascript" src="${ctx }/resources/third-party/js/healthKnowlege/healthKnowlegeEdit.js"></script>
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
			<form id="healthKnowlege_form" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
						<input type="hidden" id="id" name="id" value="${healthKnowlege.id }"/>
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 20%; width: 57.5%; ">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">标题</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="title" id="title" value="${healthKnowlege.title}" onkeyup="onchangeKnowlegeTitle(this)" onblur="onchangeKnowlegeTitle(this)"/>
													</div>
												</div>													
												
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">内容</font>
													</label>
													 <input type="hidden" id="textContent" name="textContent"> 
													<div class="ub_container">
														<div>
														    <script id="editor" type="text/plain" name="content" style="width:90%;height:150px">
																${healthKnowlege.content}
															</script>
														</div>
													</div>
												</div>
									
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">作者</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="author" id="author" value="${healthKnowlege.author}" /> 
													</div>
											</div>	
											</div>
										</div>
										
									</div>
											<div style="margin-top: 20px;">
												<div align="center" style="margin-top: 10px;">
														<button type="button" id="btnSave" class="btn btn-primary">保存</button>
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