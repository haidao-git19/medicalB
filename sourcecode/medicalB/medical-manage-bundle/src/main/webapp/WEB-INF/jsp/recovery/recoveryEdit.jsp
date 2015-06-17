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
	<script type="text/javascript" src="${ctx }/resources/js/recovery/recoveryEdit.js"></script>
	<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.artDialog.source.js?skin=idialog"></script>
	<script type="text/javascript" src="${ctx }/resources/third-party/js/artDialog.iframeTools.js"></script>
	<script type="text/javascript">
		var parentSectionID='${recovery.parentSectionID}';
		var childSectionID='${recovery.childSectionID}';
		var diseaseID='${recovery.diseaseID}';
	</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<form id="postForm" method="post">
				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<input type="hidden" id="id" name="id" value="${recovery.id }"/>
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 95%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题&nbsp;</font>
														<input type="text" id="title" name="title" value="${recovery.title }" />
														
														<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;发&nbsp;布&nbsp;人&nbsp;&nbsp;</font>
														<input type="text" id="publisher" name="publisher" value="${recovery.publisher }" />
														
														<span class="help-inline">*</span><font class="msg_font_bold">资讯类型</font>
														<select id="type" name="type" onchange="onchangeType(this);">
															<option value="0" <c:if test="${recovery.type eq 0}">selected="selected"</c:if>>公共</option>
															<option value="1" <c:if test="${recovery.type eq 1}">selected="selected"</c:if>>科室</option>
														</select>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">一级科室</font>
														<select id="parentSection" name="parentSectionID" onchange="onchangeParentSection(this);">
															
														</select>
														<span class="help-inline">*</span><font class="msg_font_bold">二级科室</font>
														<select id="childSection" name="childSectionID" onchange="onchangeChildSection(this);">
															
														</select>
														<span class="help-inline">*</span><font class="msg_font_bold">病&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;种&nbsp;</font>
														<select id="disease" name="diseaseID">
															
														</select>
													</label>
												</div>
												
												<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">内容</font>
														</label>
														<div class="ub_container">
															<div>
															    <script id="editor" type="text/plain" name="content" style="width:85%;height:300px">
																	${recovery.content}
																</script>
															</div>
														</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span>
														<font class="msg_font_bold">资讯链接：
															<a href="${!empty recovery.url?recovery.url:'javascript:void(0);'}" target="_blank">
																${!empty recovery.url?recovery.url:'暂无'}
															</a>
														</font>
													</label>
												</div>
												<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.config.js"></script>
												<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.all.js"></script>
												<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
												<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/lang/zh-cn/zh-cn.js"></script>
												<script type="text/javascript">
													UE.getEditor('editor');
												</script>
											</div>
										</div>
										<div style="margin-top: 20px;">
											<div align="center" style="margin-top: 10px;">
												<input type="button" class="btn btn-primary" value="提交" onclick="formSumbit();">
												<input type="button" class="btn btn-primary" value="取消" onclick="window.history.go(-1);">
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