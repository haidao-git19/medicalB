<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/resources/third-party/js/order/sellRecordDetail.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">订单详情</li>
			</ul>
			<form id="order_form" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
													<ul class="nav nav-tabs">
															<li class="active"><a href="#permission_tab" data-toggle="tab">订单基本信息</a></li>
													</ul>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;&nbsp;订单号&nbsp;</font>
															<input type="text" style="width: 35%"  name="orderCode" id="orderCode" value="${sellRecordVo.orderCode}"/>
															<span class="help-inline">*</span><font class="msg_font_bold">商品编号</font>
															<input type="text" style="width: 35%"  name="goodsCode" id="goodsCode" value="${sellRecordVo.goodsCode}" />
														</label>
													</div>													
													
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;区</font>
															<input type="text" style="width: 35%"  name="localCode" id="localCode" value="${userAcceptInfoVo.province}${userAcceptInfoVo.city}${userAcceptInfoVo.area}"/>
															<span class="help-inline">*</span><font class="msg_font_bold">订单状态</font>
															<input type="text" style="width: 35%"  name="status" id="status" value="${sellRecordVo.status==0?'成功':'失败'}" />
														</label>
													</div>	
													
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">购买时间</font>
															<input type="text" style="width: 35%"  name="sellTime" id="sellTime" value="${sellRecordVo.sellTime}" />
															<span class="help-inline">*</span><font class="msg_font_bold">购买数量</font>
															<input type="text" style="width: 35%"  name="productNum" id="productNum" value="${sellRecordVo.productNum}" />
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">订单金额</font>
															<input type="text" style="width: 35%"  name="totalFee" id="totalFee" value="${sellRecordVo.totalFee}" />
															<span class="help-inline">*</span><font class="msg_font_bold">支付状态</font>
															<input type="text" style="width: 35%"  name="ispay" id="ispay" value="${sellRecordVo.ispay==0?'已支付':'未支付'}" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">购买记录类型</font>
															<input type="text" style="width: 35%"  name="style" id="style" value="${sellRecordVo.style==1?'正常购买':'定时任务配置'}" />
														</label>
													</div>			
												</div>
												
												<div class="tabbable">
													<ul class="nav nav-tabs">
														<li class="active"><a href="#permission_tab" data-toggle="tab">商品信息</a></li>
													</ul>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品名称</font>
															<input type="text" style="width: 35%"  name="goodsName" id="goodsName" value="${goodsVo.goodsName}" />
															<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;&nbsp;市场价&nbsp;</font>
															<input type="text" style="width: 35%"  name="marketPrice" id="marketPrice" value="${goodsVo.marketPrice}" />
														</label>
													</div>
													
													<div class="control-group">
														<c:forEach items="${goodsAttrs}" var="goodsAttr">
															<label class="control-label">
																<span class="help-inline">*</span><font class="msg_font_bold">${goodsAttr.attrName}</font>
																<input type="text" style="width: 40%"  name="attrValue" id="attrValue" value="${goodsAttr.attrValue}" />
															</label>
														</c:forEach>
													</div>
												</div>
												
												<div class="tabbable">
													<ul class="nav nav-tabs">
														<li class="active"><a href="#permission_tab" data-toggle="tab">收货人信息</a></li>
													</ul>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;&nbsp;&nbsp;&nbsp;姓名</font>
															<input type="text" style="width: 30%"  name="acceptName" id="acceptName" value="${userAcceptInfoVo.acceptName}" />
															<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;&nbsp;&nbsp;&nbsp;电话</font>
															<input type="text" style="width: 30%"  name="phone" id="phone" value="${userAcceptInfoVo.phone}" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;&nbsp;&nbsp;&nbsp;地址</font>
															<input type="text" style="width: 70%"  name="address" id="address" value="${userAcceptInfoVo.address}" />
														</label>
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
													<font class="msg_font_bold">${goodsVo.goodsCode}</font>
											   </p>
											<div class="span11" align="center" style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<div id="preview_fake">
											          <img id="preview" src="${goodsVo.coverImage}" onload="onPreviewLoad(this)"/>
											     </div>
												 <div id="_preview_fake"> 
													<font style="" color="#aaaaaa" size="5">缩略图</font>
											     </div>
											</div>
											<br />
											<div class="span11"	style=" height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
														<font class="msg_font_normal">${goodsVo.goodsName}</font>&nbsp;&nbsp;
												</p>
											</div>
										</div>
									</div>
										<div style="margin-top: 20px;">
											<div align="center" style="margin-top: 10px;">
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
</body>
</html>