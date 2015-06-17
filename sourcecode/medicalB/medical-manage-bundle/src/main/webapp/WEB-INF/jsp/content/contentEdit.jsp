<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.all.js"></script>
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/content/contentEdit.js"></script>

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
	<!-- <script type="text/javascript">
		var type='${content.type}';
	</script> -->
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<!-- 左边导航菜单 begin -->
				<jsp:include page="/WEB-INF/jsp/navLeft.jsp"></jsp:include>
				<!-- 左边导航菜单 end -->
			</ul>
			<form id="content_form" name="content_form" enctype="multipart/form-data">
				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<input type="hidden" id="id" name="id" value="${content.id }"/>
							<input type="hidden" id="type" name="type" value="0"/>
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">标题</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" onkeyup="onchangeTitle(this)" onblur="onchangeTitle(this)" name="title" id="title" value="${content.title}" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
													<font class="msg_font_bold">图片</font><font 	class="msg_font_normal" style="margin-left: 50%">建议尺寸：305像素* 160像素</font></label>
													<div class="controls" style="border: 1px solid #ccc; width: 89%;">
														<span class="file-wrapper">
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="file" name="upload" id="upload" value="上传图片" onchange="onUploadImgChange(this)" />
														</span>
													</div>
												</div>
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">简介</font>
													</label>
													<div class="ub_container">
														<div>
															<textarea rows="2" cols="30" id="summary" name="summary" style="margin: 0px 0px 10px; width:88%; height: 36px;" onkeyup="onchangeSummary(this)" onblur="onchangeSummary(this)">${content.summary}</textarea>
														</div>
													</div>
												</div>
												<div class="control-group" style="margin-top: 10px;">
													<label class="control-label">
														<font class="msg_font_bold">正文</font>
													</label>
													<div class="ub_container">
														<div>
														    <script id="editor" type="text/plain" name="text" style="width:90%;height:150px">
																${content.text}
															</script>
														</div>
													</div>
												</div>
												<div class="control-group" style="margin-top: 20px;">
														<label class="control-label">
															<font class="msg_font_bold">内容类型</font>
														</label>
															<input type="hidden" name="type" id="type" value="${content.type}" style="width: 88%">
															<select name="typeSelect" id="typeSelect" style="width: 80%" onchange="javascript:document.getElementById('type').value=this.value">
																	<option value="0" selected="selected">通用</option>
																	<option value="1">独立</option>
															</select>
													</div>
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">链接文字</font>
													</label>
													<div class="controls">
														<input type="text" class="form-control"
															onKeyup="$('#copyContent a').html(this.value+'>')"
															onblur="$('#copyContent a').html(this.value+'>')"
															id="url" name="url" placeholder="打开链接"
															style="width: 88%"
															value="${content.url}" />
													</div>
												</div>
											</div>
										</div>
										
										<span class="span1"
											style="margin-top: 45px; margin-left: -1px;"> <!--   <i class="icon-arrow-right" style="width:100px;"></i>-->
											<img
											src="${ctx}/resources/third-party/img/message/u434_normal.png" />
										</span>
										
										<div class="span4  msg_div_white"
											style="padding: 0px; min-height: 300px; margin-left: -10px; height: auto;">
											   <p class="span11" id="copyTitle"
													style="margin-top: 5%; min-height: 20px; margin-left: 5%;">
													<font class="msg_font_bold">${content.title}</font>
											   </p>
											<div class="span11" align="center"
												style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<div id="preview_fake">
											         <%--  <img id="preview" src="${content.thumbnails}" onload="onPreviewLoad(this)"/> --%>
											     </div>
												 <div id="_preview_fake"> 
													<font style="" color="#aaaaaa" size="5">活动图片</font>
											     </div>
											</div>
											<br />
											<div class="span11"
												style="margin-top: 10px; height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
													<font class="msg_font_normal">${content.summary}</font>&nbsp;&nbsp;
													<a href="${content.url}" id="" target="_blank"><font color="#0088cc">查看详情》</font></a>
												</p>
											</div>
										</div>
									</div>
									<div style="margin-top: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button id="contentSaveBtn" type="button" class="btn btn-primary">保存</button>
											<button onclick="javascript:history.go(-1);" id="btnCancel" type="button" class="btn btn-primary">返回</button>
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