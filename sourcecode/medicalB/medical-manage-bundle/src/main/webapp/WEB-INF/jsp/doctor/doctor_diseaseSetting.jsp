<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专家管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
	<script type="text/javascript" src="${ctx }/resources/js/doctor/doctor.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">专家管理<span class="divider">/</span></li>
				<li class="active">病种设置</li>
			</ul>
			
			<form id="postForm">
				<input type="hidden" name="doctorID" value="${doctor.doctorID}">
			
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="margin-top: 10px; line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 95%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">医生姓名：${doctor.realName}</font>
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">所属科室：${doctor.sectionName}</font>
														</label>
													</div>
													
													<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">擅长疾病：</font><hr>
														</label>
														<div class="controls">
															<c:forEach var="disease" items="${diseaseList}" varStatus="stat">
																<c:set var="_diseaseID">,${disease.diseaseID},</c:set>
																<input type="checkbox" name="diseaseID" value="${disease.diseaseID}" <c:if test="${fn:contains(doctorDiseases, _diseaseID)}">checked="checked"</c:if> />${disease.diseaseName}&nbsp;&nbsp;
																<c:if test="${stat.count % 6 eq 0}"><br></c:if>
															</c:forEach>
														</div>
													</div>
													
												</div>
											</div>
										</div>
									</div>
									
									<div style="padding-bottom: 10px; margin-top: 10px;">
										<div align="center">
											<button type="button" class="btn btn-primary" onclick="commitDiseaseSetting();">提交</button>
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