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
<script type="text/javascript" src="${ctx }/resources/js/category/attrDimEdit.js"></script>

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
				<li class="active">类目属性管理<span class="divider">/</span></li>
				<li class="active">属性维度管理</li>
				<!-- 左边导航菜单 end -->
			</ul>
			<form id="attr_dim_form" name="attr_dim_form" enctype="multipart/form-data">
				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#profile_tab" data-toggle="tab">基本信息</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<input type="hidden" name="id" id="id" value="${categoryAttrDim.id}"/>
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">类目编号</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="categoryCode" id="categoryCode" value="${categoryCode}" readonly="readonly"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性编号</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="attrCode" id="attrCode" value="${attrCode}" readonly="readonly"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性维度值</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="attrShowValue" id="attrShowValue" value="${categoryAttrDim.attrShowValue}"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">属性值</font>
													</label>
													<div class="controls">
														<input type="text" style="width: 88%" name="attrValue" id="attrValue" value="${categoryAttrDim.attrValue}"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">是否默认值</font>
														<select name="isDefault" id="isDefault" style="width: 80%">
															<option value="0" <c:if test="${categoryAttrDim.isDefault eq 0}">selected="selected"</c:if>>否</option>
															<option value="1" <c:if test="${categoryAttrDim.isDefault eq 1}">selected="selected"</c:if>>是</option>
														</select>
													</label>	
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">排序</font>
														<select name="sortNum" id="sortNum" style="width: 80%">
															<option value=" " selected="selected">--选择排序--</option>
															<option value="0" <c:if test="${categoryAttrDim.sortNum eq 0}">selected="selected"</c:if>>1</option>
															<option value="1" <c:if test="${categoryAttrDim.sortNum eq 1}">selected="selected"</c:if>>2</option>
															<option value="2" <c:if test="${categoryAttrDim.sortNum eq 2}">selected="selected"</c:if>>3</option>
															<option value="3" <c:if test="${categoryAttrDim.sortNum eq 3}">selected="selected"</c:if>>4</option>
															<option value="4" <c:if test="${categoryAttrDim.sortNum eq 4}">selected="selected"</c:if>>5</option>
														</select>
													</label>	
												</div>
											</div>
										</div>
									</div>
									<div style="margin-top: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button id="submitBtn" type="button" class="btn btn-primary">提交</button>
											<button onclick="javascript:history.go(-1);" type="button" class="btn btn-primary">返回</button>
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