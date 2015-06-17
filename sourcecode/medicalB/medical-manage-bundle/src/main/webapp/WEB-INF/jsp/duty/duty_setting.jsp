<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/medical-tags" prefix="m" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>专家管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
	<script type="text/javascript" src="${ctx }/resources/js/duty/duty.js"></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">专家管理<span class="divider">/</span></li>
				<li class="active">值班设置</li>
			</ul>
			
			<form id="postForm">
				<input type="hidden" name="id" value="${duty.id}">
				<input type="hidden" name="doctorID" value="${duty.doctorID}">
				<input type="hidden" name="weekFlag" value="${duty.weekFlag}">
				<input type="hidden" name="weekNum" value="${duty.weekNum}">
				<input type="hidden" name="dayFlag" value="${duty.dayFlag}">
			
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="mline-height: 300%; margin-right: 0px; height: auto; margin-left: 20%; width: 57.5%; margin-top: 20px;">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto;">
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">医生姓名：${duty.doctorName}</font>
													</label>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">值班标记：</font>
														<c:if test="${duty.weekFlag eq 0}">本周</c:if><c:if test="${duty.weekFlag eq 1}">下周</c:if> ， 
														星期<c:if test="${duty.weekNum eq 1}">一</c:if><c:if test="${duty.weekNum eq 2}">二</c:if><c:if test="${duty.weekNum eq 3}">三</c:if><c:if test="${duty.weekNum eq 4}">四</c:if><c:if test="${duty.weekNum eq 5}">五</c:if><c:if test="${duty.weekNum eq 6}">六</c:if><c:if test="${duty.weekNum eq 7}">日</c:if> ， 
														<c:if test="${duty.dayFlag eq 1}">上午</c:if><c:if test="${duty.dayFlag eq 2}">下午</c:if><c:if test="${duty.dayFlag eq 3}">晚上</c:if>
													</label>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">开始时间</font>
													</label>
													<div class="controls">
														<c:if test="${duty.dayFlag eq 1}"><input value="${duty.startTime}" style="width:36%;" name="startTime" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm', minDate:'06:00:00', maxDate:'12:00'})" /></c:if>
														<c:if test="${duty.dayFlag eq 2}"><input value="${duty.startTime}" style="width:36%;" name="startTime" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm', minDate:'12:00:00', maxDate:'18:00'})" /></c:if>
														<c:if test="${duty.dayFlag eq 3}"><input value="${duty.startTime}" style="width:36%;" name="startTime" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm', minDate:'18:30:00', maxDate:'22:00'})" /></c:if>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">结束时间</font>
													</label>
													<div class="controls">
														<c:if test="${duty.dayFlag eq 1}"><input value="${duty.endTime}" style="width:36%;" name="endTime" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm', minDate:'06:00:00', maxDate:'12:00'})" /></c:if>
														<c:if test="${duty.dayFlag eq 2}"><input value="${duty.endTime}" style="width:36%;" name="endTime" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm', minDate:'12:00:00', maxDate:'18:00'})" /></c:if>
														<c:if test="${duty.dayFlag eq 3}"><input value="${duty.endTime}" style="width:36%;" name="endTime" class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'HH:mm', minDate:'18:30:00', maxDate:'22:00'})" /></c:if>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">值班类型</font>
													</label>
													<div class="controls">
														<select style="width:88%" name="type">
															<option value="0" <c:if test="${duty.type eq 0}">selected="selected"</c:if>>门诊值班</option>
															<option value="1" <c:if test="${duty.type eq 1}">selected="selected"</c:if>>咨询值班</option>
														</select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label">
														<font class="msg_font_bold">是否允许超时</font>
													</label>
													<div class="controls">
														<select style="width:88%" name="isLater">
															<option value="0" <c:if test="${duty.isLater eq 0}">selected="selected"</c:if>>不允许</option>
															<option value="1" <c:if test="${duty.isLater eq 1}">selected="selected"</c:if>>允许</option>
														</select>
													</div>
												</div>
												
											</div>
										</div>
										
									</div>
									
									<div style="margin-top: 20px;padding-bottom: 20px;">
										<div align="center" style="margin-top: 10px;">
											<button type="button" class="btn btn-primary" onclick="formSumbit();" >保存</button>
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