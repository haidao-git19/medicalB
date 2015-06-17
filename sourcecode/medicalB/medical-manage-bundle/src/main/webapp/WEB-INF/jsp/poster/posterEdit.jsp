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
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.all.js"></script>
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript" src="${ctx }/resources/third-party/js/poster/posterEdit.js"></script>
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
				<li class="active">海报编辑</li>
			</ul>
			<form id="poster_form" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
						<input type="hidden" id="id" name="id" value="${poster.id }"/>
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">海报名称</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%"  name="posterName" id="posterName" value="${poster.posterName}" onkeyup="onchangePosterName(this)" onblur="onchangePosterName(this)"/>
													</div>
												</div>													
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">海报图片</font><font 	class="msg_font_normal" style="margin-left: 50%">建议尺寸：305像素* 160像素</font>
													</label>
													<div class="controls" style="border: 1px solid #ccc; width: 89%;">
														<span class="file-wrapper">
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="file" name="upload" id="upload" value="上传图片" onchange="onUploadImgChange(this)" />
														</span>
													</div>
												</div>
												<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">展示时间</font>
														</label>
														<div class="controls" id="sd">
															<input id="startTime" name="startTime" class="Wdate"  style="width:135px;" type="text" value="${poster.startTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" /> -
															<input id="endTime" name="endTime" class="Wdate"  style="width:135px;" type="text" value="${poster.endTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
														</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">打开类型</font>
													</label>	
													<input type="hidden" name="type" id="type" value="${poster.type}">
													<select name="typeSelect" id="typeSelect" style="width: 89%" onchange="javascript:document.getElementById('type').value=this.value;onchangeControlFunc();">
														<option value="" selected="selected">---请选择类型---</option>
														<option value="1">推荐医生</option>
														<option value="0">链接地址</option>	
													</select>			
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">医生信息</font>
													</label>
													<input type="hidden" name="goodsInfo" id="goodsInfo" value="${goodsInfo}">
													<select name="goodsInfoSelect" id="goodsInfoSelect" style="width: 89%" onchange="javascript:onchangeGoodsInfo(this);document.getElementById('goodsInfo').value=this.value">
														<option value="{0}">{1}</option>
													</select>
												</div>
												
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<font class="msg_font_bold">链接文字</font>
													</label>
													<div class="controls">
														<input type="text" class="form-control"
															onKeyup="$('#copyContent a').html(this.value+'>>')"
															onblur="$('#copyContent a').html(this.value+'>>')"
															id="url" name="url" placeholder="打开链接"
															style="width: 88%"
															value="${poster.url}" />
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
													<font class="msg_font_bold">${poster.posterName}</font>
											   </p>
											<div class="span11" align="center" style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<div id="preview_fake">
											          <img id="preview" src="${poster.image}" onload="onPreviewLoad(this)"/>
											     </div>
												 <div id="_preview_fake"> 
													<font style="" color="#aaaaaa" size="5">海报图片</font>
											     </div>
											</div>
											<br />
											<div class="span11"	style="margin-top: 10px; height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
													<a href="${poster.url}" id="" target="_blank"><font color="#0088cc">查看详情&gt;&gt;</font></a>
												</p>
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