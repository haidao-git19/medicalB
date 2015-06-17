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
<script type="text/javascript" src="${ctx }/resources/js/category/categoryAttrEdit.js"></script>

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
				<li class="active">类目管理<span class="divider">/</span></li>
				<li class="active">类目属性编辑</li>
				<!-- 左边导航菜单 end -->
			</ul>
			<form id="categoryAttr_edit_form" name="categoryAttr_edit_form" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											
											<input type="hidden" id="id" name="id" value="${categoryAttr.id}">
											
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">类目编号</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="categoryCode" id="categoryCode" value="${categoryCode}" readonly="readonly"/>
													</div>
												</div>
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">分类标识</font>
													</label>
													<select name="attrClassId" id="attrClassId" style="width: 88%">
														<option value="" selected="selected">--选择分类标识--</option>
														<c:forEach items="${attrClassList}" var="attrClass">
															<option value="${attrClass.id}" <c:if test="${categoryAttr.attrClassId eq attrClass.id}">selected='selected'</c:if>>${attrClass.attrClassName}</option>
														</c:forEach>
													</select>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性编号</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="attrCode" id="attrCode" value="${categoryAttr.attrCode}" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性名称</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="attrName" id="attrName" value="${categoryAttr.attrName}" />
													</div>
												</div>
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">录入方式</font>
													</label>
													<select name="attrEnterType" id="attrEnterType" style="width: 88%">
														<option value="" selected="selected">--选择录入方式--</option>
														<option value="1" <c:if test="${categoryAttr.attrEnterType eq 1}">selected='selected'</c:if>>单值输入</option>
														<option value="2" <c:if test="${categoryAttr.attrEnterType eq 2}">selected='selected'</c:if>>多值输入</option>
													</select>
												</div>
												<div class="control-group" style="margin-top: 20px;">
													<input type="hidden" id="attrType" name="attrType" value="${categoryAttr.attrType}">
													
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性类型：</font>
													</label>
													<input type="checkbox" id="checkbox1" name="type_one" value="1">查询属性&nbsp;&nbsp;
													<input type="checkbox" id="checkbox2" name="type_two" value="2">销售属性&nbsp;&nbsp;
													<input type="checkbox" id="checkbox3" name="type_three" value="4">SKU属性
												</div>
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性排序</font>
													</label>
													<select name="sortNum" id="sortNum" style="width: 88%">
														<option value="" selected="selected">--选择排序--</option>
														<option value="0" <c:if test="${categoryAttr.sortNum eq 0}">selected='selected'</c:if>>1</option>
														<option value="1" <c:if test="${categoryAttr.sortNum eq 1}">selected='selected'</c:if>>2</option>
														<option value="2" <c:if test="${categoryAttr.sortNum eq 2}">selected='selected'</c:if>>3</option>
														<option value="3" <c:if test="${categoryAttr.sortNum eq 3}">selected='selected'</c:if>>4</option>
														<option value="4" <c:if test="${categoryAttr.sortNum eq 4}">selected='selected'</c:if>>5</option>
														<option value="5" <c:if test="${categoryAttr.sortNum eq 5}">selected='selected'</c:if>>6</option>
														<option value="6" <c:if test="${categoryAttr.sortNum eq 6}">selected='selected'</c:if>>7</option>
														<option value="7" <c:if test="${categoryAttr.sortNum eq 7}">selected='selected'</c:if>>8</option>
														<option value="8" <c:if test="${categoryAttr.sortNum eq 8}">selected='selected'</c:if>>9</option>
														<option value="9" <c:if test="${categoryAttr.sortNum eq 9}">selected='selected'</c:if>>10</option>
														<option value="10" <c:if test="${categoryAttr.sortNum eq 10}">selected='selected'</c:if>>11</option>
														<option value="11" <c:if test="${categoryAttr.sortNum eq 11}">selected='selected'</c:if>>12</option>
														<option value="12" <c:if test="${categoryAttr.sortNum eq 12}">selected='selected'</c:if>>13</option>
														<option value="13" <c:if test="${categoryAttr.sortNum eq 13}">selected='selected'</c:if>>14</option>
														<option value="14" <c:if test="${categoryAttr.sortNum eq 14}">selected='selected'</c:if>>15</option>
													</select>
												</div>
												<div class="control-group" style="margin-top: 20px;">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性描述</font>
													</label>
													<textarea id="description" name="description" style="width:88%; height: 36px;">${categoryAttr.description}</textarea>
												</div>
											</div>
										</div>
									</div>
									<div style="margin-top: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button id="submitBtn" type="button" class="btn btn-primary">提交</button>
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