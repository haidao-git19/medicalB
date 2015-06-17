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
	
		.operArea{
			display:block;
			list-style-type:none;
			border:2px solid #A9A9A9;
			width:50%;
			height:200px;
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
	<script type="text/javascript" src="${ctx }/resources/js/hospital/editHospital.js"></script>
	<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.artDialog.source.js?skin=idialog"></script>
	<script type="text/javascript" src="${ctx }/resources/third-party/js/artDialog.iframeTools.js"></script>
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
											<input type="hidden" id="hospitalID" name="hospitalID" value="${hospl.hospitalID }"/>
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 95%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">医院名称</font>
														<input type="text" id="hospitalName" name="hospitalName" value="${hospl.hospitalName }" />
														<span class="help-inline">*</span><font class="msg_font_bold">联系人&nbsp;&nbsp;</font>
														<input type="text" id="linkMan" name="linkMan" value="${hospl.linkMan }" />
														<span class="help-inline">*</span><font class="msg_font_bold">联系方式</font>
														<input type="text" name="linkPhone" id="linkPhone" value="${hospl.linkPhone }" />
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">医院等级</font>
														<select type="text" id="hospitalLevel" name="hospitalLevel">
															<option value="" selected="selected">--选择等级--</option>
															<option value="二级甲等" <c:if test="${hospl.hospitalLevel eq '二级甲等'}">selected="selected"</c:if>>二级甲等</option>
															<option value="二级" <c:if test="${hospl.hospitalLevel eq '二级'}">selected="selected"</c:if>>二级</option>
															<option value="三级甲等" <c:if test="${hospl.hospitalLevel eq '三级甲等'}">selected="selected"</c:if>>三级甲等</option>
															<option value="三级" <c:if test="${hospl.hospitalLevel eq '三级'}">selected="selected"</c:if>>三级</option>
															<option value="社区医院" <c:if test="${hospl.hospitalLevel eq '社区医院'}">selected="selected"</c:if>>社区医院</option>
														</select>
														<span class="help-inline">*</span><font class="msg_font_bold">本地网&nbsp;</font>
														<select type="text" id="areaID" name="areaID"></select>
														<input type="hidden" name="areaName" id="areaName" value="${hospl.areaName}">
														<span class="help-inline">*</span><font class="msg_font_bold">合作医院</font>
														<select id="isCooperation" name="isCooperation"><option value="1">是</option><option value="0">否</option></select>
													</label>
												</div>
												<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">医院地址</font>
														<input type="text" name="address" id="address" value="${hospl.address }"/>
														<span class="help-inline">*</span><font class="msg_font_bold">医院特色</font>
														<input type="text" name="skill" value="${hospl.skill }">
														<span class="help-inline">*</span><font class="msg_font_bold">医院类型</font>
														<select type="text" id="type" name="type">
															<option value="" selected="selected">--选择类型--</option>
															<option value="1" <c:if test="${hospl.type eq '1'}">selected="selected"</c:if>>专家医院</option>
															<option value="0" <c:if test="${hospl.type eq '0'}">selected="selected"</c:if>>社区医院</option>
														</select>
													</label>
												</div>
												<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">医院介绍</font>
														</label>
														<div class="ub_container">
															<div>
																<input type="hidden" name="summary" id="summary">
															    <script id="editor" type="text/plain" name="edit_summary" style="width:93%;height:150px">
																	${hospl.summary}
																</script>
															</div>
														</div>
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
						<li class="active"><a href="#profile_tab" data-toggle="tab">高级信息</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color:;">
									<div class="row-fluid">
										<table id="agent_develop_table" class="table table-hover table-bordered table-condensed table-striped">
											<div class="control-group">
													<label class="control-label">
														<span class="help-inline">*</span><font class="msg_font_bold">推&nbsp;&nbsp;荐&nbsp;&nbsp;医&nbsp;&nbsp;院&nbsp;</font>
														<select id="recomFlag" name="recomFlag" style="width: 20.5%;">
															<option value="0" <c:if test="${hospl.recomFlag eq 0}">selected="selected"</c:if>>不推荐</option>
															<option value="1" <c:if test="${hospl.recomFlag eq 1}">selected="selected"</c:if>>推荐</option>
														</select>
														
														<span class="help-inline">*</span><font class="msg_font_bold">咨询大夫数量</font>
														<input type="text" value="${hospl.ctNum }" style="width: 11.3%;" readonly="readonly"/>
														
														<span class="help-inline">*</span><font class="msg_font_bold">转诊大夫数量</font>
														<input type="text" value="${hospl.cgNum }" style="width: 11.3%;" readonly="readonly"/>
														
													</label>
												</div>
												<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">医院地图定位</font>
															<input type="text" style="width: 20%;" name="physicsAddress" id="physicsAddress" value="${hospl.physicsAddress}" />
															<button type="button" class="btn btn-primary" onclick="pitchBaiduMap()">地图</button>
															<span class="help-inline">*</span><font class="msg_font_bold">经度</font>
															<input type="text"  id="locationX" name="lng" value="${hospl.lng }">
																<span class="help-inline">*</span><font class="msg_font_bold">纬度</font>
															<input type="text"  id="locationY" name="lat" value="${hospl.lat }">
														</label>
												</div>
										</table>
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
						<li class="active"><a href="#profile_tab" data-toggle="tab">科室配置</a></li>
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
													<span id="parentSectionArea" style="display:none;">
														
													</span>
													<span class="help-inline">*</span><font class="msg_font_bold">一级科室</font>
													<select type="text" id="firstLevel" name="firstLevel" onchange="onchangeParentLevel(this);">
														<option value="" selected="selected">--选择科室--</option>
														
													</select>
													<span class="help-inline">*</span><font class="msg_font_bold">二级科室</font>
													<div id="sectionContainer" style="display:inline;margin-left: 5px;position:absolute;width: 30%">
														<ul id="operArea_" class="operArea">
															
														</ul>
													</div>
												</label>
											</div>
											<!-- <div class="control-group">
												<label class="control-label">
													<span class="help-inline">*</span><font class="msg_font_bold">二级科室</font>
												</label>
												<ul id="operArea" class="operArea">
													
												</ul>
											</div> -->
										</table>
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
			</form>
			<input type="hidden" id="latnId" value="${hospl.areaID}" />
			<input type="hidden" id="defaultCooperation" value="${hospl.isCooperation}" />
		</div>
	</div>
</body>
</html>