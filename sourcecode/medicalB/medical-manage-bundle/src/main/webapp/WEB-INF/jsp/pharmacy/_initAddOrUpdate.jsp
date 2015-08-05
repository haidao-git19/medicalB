<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.artDialog.source.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/artDialog.iframeTools.js"></script>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/js/pharmacy/editPharmacy.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">药店管理</li>
			</ul>
			<form id="postForm" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							
							<input type="hidden" name="shopID" value="${ph.shopID }"/>
							<input type="hidden" name="loginID" value="${ph.loginID}">
							<input type="hidden" name="companyId" value="${ph.companyId}">
							<input type="hidden" id="latnId"  value="${ph.latnId }"/>
							<input type="hidden" name="img" id="img"  value="${ph.logo }"/>
							
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">药店名称</font>
														</label>
														<div class="controls">
															<input type="text" style="width: 88%"  name="shopName" id="shopName" value="${ph.shopName }" onchange="onchangeShopName(this);"/>
														</div>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">药店编码</font>
														</label>
														<div class="controls">
															<input type="text" style="width: 88%"  name="code" id="code" value="${ph.code }" />
														</div>
													</div>
													<!-- 
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">登录账号</font>
															<input style="width: 28.5%;" type="text" id="loginAccount" name="loginAccount" value="${ph.loginAccount}" <c:if test="${ph.shopID gt 0}">readonly="readonly"</c:if> />

															<span class="help-inline">*</span><font class="msg_font_bold">登录密码</font>
															<input style="width: 28.5%;" type="password" id="loginPwd" name="loginPwd" value="${ph.loginPwd}" <c:if test="${ph.shopID gt 0}">readonly="readonly"</c:if> />
														</label>
													</div>
													 -->
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">药店电话</font>
														</label>
														<div class="controls">
															<input type="text" style="width: 88%"  name="phone" id="phone" value="${ph.phone }" />
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">店铺照片</font><font 	class="msg_font_normal" style="margin-left: 50%">建议尺寸：305像素* 160像素</font>
														</label>
														<div class="controls" style="border: 1px solid #ccc; width: 89%; margin-left: 15px">
															<span class="file-wrapper">
																<img src="${ctx}/resources/third-party/img/message/upload.png">
																<input type="file" style="opacity:0;" name="upload" id="upload" value="上传图片" onchange="onUploadImgChange(this)" />
															</span>
														</div>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">药店类型</font>
															<select id="type"  name="type" style="width: 34%">
																<option value="1" <c:if test="${ph.type eq 1}">selected="selected"</c:if>>普通药房</option>
																<option value="2" <c:if test="${ph.type eq 2}">selected="selected"</c:if>>医院药房</option>
															</select>
															<span class="help-inline">*</span><font class="msg_font_bold">本地网</font>
															<select id="areaID"  name="latnId" style="width: 34%">
																
															</select>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">地址</font>
														</label>
														<div class="controls" >
															<input type="text" style="width: 80%"  name="physicsAddress" id="physicsAddress" value="${ph.physicsAddress}" />
															<button type="button" class="btn btn-primary" onclick="pitchBaiduMap()">地图</button>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">经度</font>
															<input type="text" style="width: 34%"  name="lng" id="locationX" value="${ph.lng}" />
															
															<span class="help-inline">*</span><font class="msg_font_bold">纬度</font>
															<input type="text" style="width: 34%"  name="lat" id="locationY" value="${ph.lat}" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">详细地址</font>
														</label>
														<div class="controls">
															<input type="text" style="width: 88%"  name="address" id="address" value="${ph.address}" />
														</div>
													</div>	
													
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">店铺描述</font>
														</label>
														<div class="controls" style="margin-left: 15px">
															<textarea rows="" cols="" style="width: 90%"  name="description" id="description" onchange="onchangeDescription(this);">${ph.description}</textarea>
														</div>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span>
															<font class="msg_font_bold">详情介绍：
																<a href="${!empty ph.url?ph.url:'javascript:void(0);'}" target="_blank">
																	${!empty ph.url?ph.url:'暂无'}
																</a>
															</font>
														</label>
													</div>	
												</div>
											</div>
										</div>
										<span class="span1" style="margin-top: 45px; margin-left: -1px;"> <!--   <i class="icon-arrow-right" style="width:100px;"></i>-->
											<img src="${ctx}/resources/third-party/img/message/u434_normal.png" />
										</span>
										
										<div class="span4  msg_div_white" style="padding: 0px; min-height: 300px; margin-left: -10px; height: auto;">
											   <p class="span11" id="copyTitle"	style="margin-top: 5%; min-height: 20px; margin-left: 5%;">
													<font class="msg_font_bold">${ph.shopName}</font>
											   </p>
											<div class="span11" align="center" style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<div id="preview_fake">
											          <img id="preview" src="${ph.logo}" onload="onPreviewLoad(this)"/>
											     </div>
												 <div id="_preview_fake"> 
													<font style="" color="#aaaaaa" size="5">缩略图</font>
											   </div>
											</div>
											<br />
											<div class="span11"	style=" height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
														<font class="msg_font_normal">${ph.description}</font>&nbsp;&nbsp;
												</p>
											</div>
										</div>
									</div>
										<div style="margin-top: 20px;">
											<div align="center" style="margin-top: 10px;">
												<button type="button"  class="btn btn-primary" id="submitBtn">保存</button>
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