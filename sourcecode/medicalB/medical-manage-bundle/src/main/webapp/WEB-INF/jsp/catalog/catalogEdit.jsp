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
<script type="text/javascript" src="${ctx }/resources/third-party/js/catalog/catalogEdit.js"></script>

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
				<!-- 左边导航菜单 begin -->
				<jsp:include page="/WEB-INF/jsp/navLeft.jsp"></jsp:include>
				<!-- 左边导航菜单 end -->
			</ul>
			<form id="catalog_form" name="content_form" enctype="multipart/form-data">
				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">类目名称</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="catalogName" id="catalogName" value="${resultMap.catalogName}" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">类目编号</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="catalogCode" id="catalogCode" value="${resultMap.catalogCode}" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">类目排序</font>
														<input type="hidden" name="seq" id="seq" value="${resultMap.seq}">
														<select name="seqSelect" id="seqSelect" style="width: 80%" onchange="javascript:document.getElementById('seq').value=this.value">
															<option value="" selected="selected">---请选择排序---</option>
															<option value="0">0</option>
															<option value="1">1</option>
															<option value="2">2</option>
														</select>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">商品风格</font>
													</label>
													<input type="hidden" name="attrValue" id="attrValue" value="${resultMap.attrValue}">
													<select name="attrValueSelect" id="attrValueSelect" style="width: 88%" onchange="javascript:document.getElementById('attrValue').value=this.value">
														<option value="{0}">{1}</option>
													</select>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">封面图片一<a id="viewPic_0" onclick="javascript:$('#preview').attr('src',document.getElementById('coverImage_0').value);">(点我看已设置封面图)</a></font>
														<font class="msg_font_normal" style="margin-left: 40%">建议尺寸：305像素* 160像素</font>
													</label>
													<div class="controls" style="border: 1px solid #ccc; width: 89%;">
														<span class="file-wrapper">
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="hidden" id="coverImage_0" value="${resultMap.coverImage_0}">
															<input type="file" name="upload_0" id="upload_0" value="上传图片" onchange="onUploadImgChange(this)" />
														</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">商品一名称</font>
													</label>
													<input type="hidden" name="goods_0" id="goods_0" value="${resultMap.goods_0}">
													<select name="goods_0Select" id="goods_0Select" style="width: 88%" onchange="javascript:onchangeCatalogGoods(this);document.getElementById('goods_0').value=this.value">
														<option value="{0}">{1}</option>
													</select>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">封面图片二<a id="viewPic_1" onclick="javascript:$('#preview').attr('src',document.getElementById('coverImage_1').value);">(点我看已设置封面图)</a></font>
														<font 	class="msg_font_normal" style="margin-left: 40%">建议尺寸：305像素* 160像素</font>
													</label>
													<div class="controls" style="border: 1px solid #ccc; width: 89%;">
														<span class="file-wrapper">
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="hidden" id="coverImage_1" value="${resultMap.coverImage_1}">
															<input type="file" name="upload_1" id="upload_1" value="上传图片" onchange="onUploadImgChange(this)" />
														</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">商品二名称</font>
													</label>
													<input type="hidden" name="goods_1" id="goods_1" value="${resultMap.goods_1}">
													<select name="goods_1Select" id="goods_1Select" style="width: 88%" onchange="javascript:onchangeCatalogGoods(this);document.getElementById('goods_1').value=this.value">
														
													</select>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">封面图片三<a id="viewPic_2" onclick="javascript:$('#preview').attr('src',document.getElementById('coverImage_2').value);">(点我看已设置封面图)</a></font>
														<font 	class="msg_font_normal" style="margin-left: 40%">建议尺寸：305像素* 160像素</font>
													</label>
													<div class="controls" style="border: 1px solid #ccc; width: 89%;">
														<span class="file-wrapper">
															<img src="${ctx}/resources/third-party/img/message/upload.png">
															<input type="hidden" id="coverImage_2" value="${resultMap.coverImage_2}">
															<input type="file" name="upload_2" id="upload_2" value="上传图片" onchange="onUploadImgChange(this)" />
														</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">商品三名称</font>
													</label>
													<input type="hidden" name="goods_2" id="goods_2" value="${resultMap.goods_2}">
													<select name="goods_2Select" id="goods_2Select" style="width: 88%" onchange="javascript:onchangeCatalogGoods(this);document.getElementById('goods_2').value=this.value">
														
													</select>
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
													<font class="msg_font_bold">类目商品信息</font>
											   </p>
											<div class="span11" align="center"
												style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<div id="preview_fake">
											        <img id="preview" src="${resultMap.coverImage_0}" onload="onPreviewLoad(this)"/>
											     </div>
												 <div id="_preview_fake"> 
													<font style="" color="#aaaaaa" size="5">图片预览</font>
											     </div>
												
											</div>
											<br />
											<div class="span11"
												style="margin-top: 10px; height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
													<font class="msg_font_normal">${resultMap.goodsCode_0}</font>&nbsp;&nbsp;
												</p>
											</div>
										</div>
									</div>
									<div style="margin-top: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button id="catalogSaveBtn" type="button" class="btn btn-primary">保存</button>
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