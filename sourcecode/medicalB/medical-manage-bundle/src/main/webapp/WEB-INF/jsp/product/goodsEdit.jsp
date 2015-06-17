<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/third-party/js/product/goodsEdit.js"></script>
<style type="text/css">
	.operArea{
			display:block;
			list-style-type:none;
			border:2px solid #A9A9A9;
			width:50%;
			height:100px;
			overflow:auto;
		}
		.operArea li{
			margin-left:50px;
			margin-top: 10px;
		}
		.operArea li input{
			margin-right: 15px;
		}
</style>
<script type="text/javascript">
	var firstLevelNavId='${firstLevelNavId}';
	var secondLevelNavId='${secondLevelNavId}';
	var thirdLevelNavId='${thirdLevelNavId}';
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">商品编辑</li>
			</ul>
			<form id="goods_form" enctype="multipart/form-data">
				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<input type="hidden" id="id" name="id" value="${goodsVo.id}">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
												
													<%-- <div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">药店名称</font>
															<input type="hidden" id="pharmacy" value="${goodsVo.pharmacyCode}"  />
															<select id="pharmacyCode" name="pharmacyCode">
															<option >请选择</option>
																<c:forEach var="pharmacy" items="${pharmacyList }">
																	<option value="${pharmacy.code }">${pharmacy.name}</option>
																</c:forEach>
															</select>
														</label>
													</div>	 --%>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">类目名称</font>
															<select id="categoryCode" name="categoryCode">
																<option >--请选择类目--</option>
																<c:forEach items="${categoryList}" var="category">
																	<option value="${category.categoryCode }" <c:if test="${category.categoryCode eq goodsVo.categoryCode }">selected='selected'</c:if>>${category.name}</option>
																</c:forEach>
															</select>
														</label>
														<span id="categoryErrorSpan" style="color:red;"></span>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品编码</font>
															<input type="text" style="width: 80%"  name="goodsCode" id="goodsCode" value="${goodsVo.goodsCode}" />
														</label>
														<span id="goodsCodeErrorSpan" style="color:red;"></span>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">长标题</font>
															<input type="text" style="width: 80%"  name="longName" id="longName" value="${goodsVo.longName}" onkeyup="onchangeGraphicsName(this)" onblur="onchangeGraphicsName(this)"/>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">中标题</font>
															<input type="text" style="width: 80%"  name="mediumName" id="mediumName" value="${goodsVo.mediumName}" />
														</label>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">短标题</font>
															<input type="text" style="width: 80%"  name="shortName" id="shortName" value="${goodsVo.shortName}" />
														</label>
													</div>														
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">副名称</font>
															<input type="text" style="width: 80%"  name="secondName" id="secondName" value="${goodsVo.secondName}" />
														</label>
													</div>	
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">品牌</font>
															<input type="text" style="width: 80%"  name="brandId" id="brandId" value="${goodsVo.brandId}" />
														</label>
													</div>
													<div class="control-group edit_hotel_box">
														<div class="msg_font_bold edit_hotel_title"><span style="color: red;">*</span>主图</div>
														<div class="controls edit_hotel_controls" style="margin-right: 8px;">
															<span class="file-wrapper">
																<img src="${ctx}/resources/third-party/img/message/upload.png">
																<input type="file" name="upload" id="upload" value="上传图片" onchange="onUploadImgChange(this)" />
															</span>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品型号</font>
															<input type="text" style="width: 80%"  name="style" id="style" value="${goodsVo.style}" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品类别</font>
															<select name="model" id="model" style="width: 80%" >
																	<option value="1" <c:if test="${goodsVo.model eq 1}">selected='selected'</c:if>>单产品</option>
																	<option value="2" <c:if test="${goodsVo.model eq 2}">selected='selected'</c:if>>组合产品</option>
															</select>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">是否草稿</font>
															<select name="isDraft" id="isDraft" style="width: 80%" >
																	<option value="1" <c:if test="${goodsVo.isDraft eq 1}">selected='selected'</c:if>>否</option>
																	<option value="0" <c:if test="${goodsVo.isDraft eq 0}">selected='selected'</c:if>>是</option>
															</select>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">市场价</font>
															<input type="text" style="width: 80%"  name="marketPrice" id="marketPrice" value="${goodsVo.marketPrice}" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商城价</font>
															<input type="text" style="width: 80%"  name="shopPrice" id="shopPrice" value="${goodsVo.shopPrice}" />
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品类型</font>
															<select name="goodsType" id="goodsType" style="width: 80%" >
																	<option value="1" <c:if test="${goodsVo.goodsType eq 1}">selected='selected'</c:if>>实物</option>
																	<option value="2" <c:if test="${goodsVo.goodsType eq 2}">selected='selected'</c:if>>虚拟</option>
																	<option value="3" <c:if test="${goodsVo.goodsType eq 3}">selected='selected'</c:if>>配件</option>
																	<option value="4" <c:if test="${goodsVo.goodsType eq 4}">selected='selected'</c:if>>赠品</option>
															</select>
														</label>
													</div>
													<div class="control-group">
														<input type="hidden" id="sellCharacter" name="sellCharacter" value="${goodsVo.sellCharacter}">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">销售特征</font>
														</label>
														<input type="checkbox" id="checkbox1" name="type_one" value="1">价保&nbsp;&nbsp;
														<input type="checkbox" id="checkbox2" name="type_two" value="2">七天退货&nbsp;&nbsp;
														<input type="checkbox" id="checkbox3" name="type_three" value="4">正品行货
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">销售属性</font>
															<input type="hidden" name="sellAttr" id="sellAttr" value="${goodsVo.sellAttr}">
															<select name="sellAttrSelect" id="sellAttrSelect" style="width: 80%" onchange="javascript:document.getElementById('sellAttr').value=this.value">
																	<option value="1" selected="selected">预售</option>
															</select>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品标签</font>
															<select name="label" id="label" style="width: 80%">
																	<option value="1" <c:if test="${goodsVo.label eq 1}">selected='selected'</c:if>>新品</option>
																	<option value="2" <c:if test="${goodsVo.label eq 2}">selected='selected'</c:if>>促销</option>
																	<option value="3" <c:if test="${goodsVo.label eq 3}">selected='selected'</c:if>>推荐</option>
															</select>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品状态</font>
															<select name="status" id="status" style="width: 80%">
																	<option value="0" <c:if test="${goodsVo.status eq 0}">selected='selected'</c:if>>初始状态</option>
																	<option value="1" <c:if test="${goodsVo.status eq 1}">selected='selected'</c:if>>待审核</option>
																	<option value="2" <c:if test="${goodsVo.status eq 2}">selected='selected'</c:if>>在售</option>
																	<option value="3" <c:if test="${goodsVo.status eq 3}">selected='selected'</c:if>>下架</option>
																	<option value="4" <c:if test="${goodsVo.status eq 4}">selected='selected'</c:if>>禁用</option>
															</select>
														</label>
													</div>
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">上市时间</font>
															<input id="saleTime" name="saleTime" class="Wdate"  style="width:30%;" type="text" value="${goodsVo.saleTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
														</label>
													</div>
													<div class="control-group">
														<input type="hidden" name="sellingPoints" value="${goodsVo.sellingPoints}">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品买点</font>
														</label>
														<div class="ub_container">
															<div>
															    <script id="editor1" type="text/plain" name="_sellingPoints" style="width:97%;height:150px">
																	${clobs._sellingPoints}
																</script>
															</div>
														</div>
													</div>
													<div class="control-group">
														<input type="hidden" name="packageList" value="${goodsVo.packageList}">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">包装清单</font>
														</label>
														<div class="ub_container">
															<div>
															    <script id="editor2" type="text/plain" name="_packageList" style="width:97%;height:150px">
																	${clobs._packageList}
																</script>
															</div>
														</div>
													</div>
													<div class="control-group">
														<input type="hidden" name="buyNote" value="${goodsVo.buyNote}">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">购买须知</font>
														</label>
														<div class="ub_container">
															<div>
															    <script id="editor3" type="text/plain" name="_buyNote" style="width:97%;height:150px">
																	${clobs._buyNote}
																</script>
															</div>
														</div>
													</div>
													<div class="control-group">
														<input type="hidden" name="content" value="${goodsVo.content}">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">商品详情</font>
														</label>
														<div class="ub_container">
															<div>
															    <script id="editor4" type="text/plain" name="_content" style="width:97%;height:150px">
																	${clobs._content}
																</script>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.config.js"></script>
										<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.all.js"></script>
										<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
										<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/lang/zh-cn/zh-cn.js"></script>
										<script type="text/javascript">
											UE.getEditor('editor1');
											UE.getEditor('editor2');
											UE.getEditor('editor3');
											UE.getEditor('editor4');
										</script>
										<span class="span1"
											style="margin-top: 45px; margin-left: -1px;"> <!--   <i class="icon-arrow-right" style="width:100px;"></i>-->
											<img
											src="${ctx}/resources/third-party/img/message/u434_normal.png" />
										</span>
										
										<div class="span4  msg_div_white" style="padding: 0px; min-height: 300px; margin-left: -10px; height: auto;">
											   <p class="span11" id="copyTitle"	style="margin-top: 5%; min-height: 20px; margin-left: 5%;">
													<font class="msg_font_bold">${goodsVo.longName}</font>
											   </p>
											<div class="span11" align="center" style="margin-left: 5%; padding: 0px; height: auto; min-height: 160px; line-height: 160px; background-color: #eee; text-align: center;"
												id="showImg">
												<div id="preview_fake">
											          <img id="preview" src="${goodsVo.imgPath}" onload="onPreviewLoad(this)"/>
											     </div>
												 <div id="_preview_fake"> 
													<font style="" color="#aaaaaa" size="5">缩略图</font>
											   </div>
											</div>
											<br />
											<div class="span11"	style=" height: auto; margin-left: 5%; word-break: break-all;">
												<p id="copyContent" style="width: 100%">
														<font class="msg_font_normal">${goodsVo.goodsCode}</font>&nbsp;&nbsp;
												</p>
											</div>
										</div>
									</div>
										<div style="margin-top: 20px;">
											<div align="center" style="margin-top: 10px;">
												   
											</div>
										</div>
								</div>
							</div>
						</div>
					</div>
					<ul class="nav nav-tabs">
						<li class="active"><a href="#profile_tab" data-toggle="tab">导航设置</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color:;">
									<div class="row-fluid">
										<table id="agent_develop_table" class="table table-hover table-bordered table-condensed table-striped">
											<!-- <div >
												<ul id="operArea" class="operArea">
													
												</ul>
											</div> -->
											<div class="control-group" style="margin-bottom:10px;height:200px;">
												<label class="control-label">
													<span class="help-inline">*</span><font class="msg_font_bold">一级导航</font>
													<select type="text" id="firstLevel" name="firstLevelNavcatId" onchange="onchangeFirstNavLevel(this.value);">
														<option value="" selected="selected">--选择导航--</option>
														
													</select>
													
													<span class="help-inline">*</span><font class="msg_font_bold">二级导航</font>
													<select type="text" id="secondLevel" name="secondLevelNavcatId" onchange="onchangeSecondNavLevel(this.value);">
														<option value="" selected="selected">--选择导航--</option>
														
													</select>
													
													<span class="help-inline">*</span><font class="msg_font_bold">三级导航</font>
													<select type="text" id="thirdLevel" name="thirdLevelNavcatId" onchange="onchangeThirdNavLevel(this.value);">
														<option value="" selected="selected">--选择导航--</option>
														
													</select>
													
												</label>
												<label class="control-label">
													<span id="parentSectionArea" style="display:none;">
														
													</span>
													<span class="help-inline">*</span><font class="msg_font_bold">搜索条件</font>
													<select type="text" id="searchCondition" name="searchCondition" onchange="onchangeSearchCondition(this.value);">
														<option value="" selected="selected">--选择--</option>
														
													</select>
													<span class="help-inline">*</span><font class="msg_font_bold">具体条件</font>
													<div id="searchOptContainer" style="display:inline;margin-left: 5px;position:absolute;width: 30%">
														<ul id="operArea_" class="operArea">
															
														</ul>
													</div>
												</label>
											</div>
											
										</table>
									</div>
									<div style="margin-top: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button type="button" class="btn btn-primary" id="goodsSaveBtn">保存生效</button>
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