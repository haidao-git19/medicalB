<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/medical-tags" prefix="m" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>专家管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx }/resources/js/duty/duty.js"></script>
		
		<style type="text/css">
		#user_table {
			min-width : 800px;
		}
		</style>
	</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			
			<ul class="breadcrumb">
				<li class="active">专家管理 <span class="divider">/</span></li>
				<li class="active">值班设置 <span class="divider">/</span></li>
				<li class="active">${doctorName}</li>
			</ul>
			
			<div class="tabbable">
				<div class="tab-content">
					<div class="tab-pane active" id="profile_tab">
						<div class="container-fluid myminwidth">
							<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
								<div class="row-fluid">
									<div class="span6 msg_div_white" style="margin-left: 20%;margin-top: 20px; padding-top: 3px;">
										<div style="margin-left: 10%; height: auto; padding-bottom: 12px;">
											
											<input type="hidden" id="doctorId" value="${doctorId}">
											
											本周值班：
											<table border="1px;">
												<tr>
													<td style="width:100px;text-align: center;"></td>
													<td style="width:100px;text-align: center;">上午</td>
													<td style="width:100px;text-align: center;">下午</td>
													<td style="width:100px;text-align: center;">晚上</td>
												</tr>
												<c:forEach var="weekNum" begin="1" end="7" step="1">
													<tr>
														<td style="width:100px;text-align: center;">
															星期<c:if test="${weekNum eq 1}">一</c:if><c:if test="${weekNum eq 2}">二</c:if><c:if test="${weekNum eq 3}">三</c:if><c:if test="${weekNum eq 4}">四</c:if><c:if test="${weekNum eq 5}">五</c:if><c:if test="${weekNum eq 6}">六</c:if><c:if test="${weekNum eq 7}">日</c:if>
														</td>
														<td id="_0_${weekNum}_1" style="width:100px;text-align: center;">
															${m:dutyLink(thisWeekDutys, doctorId, 0, weekNum, 1)}
														</td>
														<td id="_0_${weekNum}_2" style="width:100px;text-align: center;">
															${m:dutyLink(thisWeekDutys, doctorId, 0, weekNum, 2)}
														</td>
														<td id="_0_${weekNum}_3" style="width:100px;text-align: center;">
															${m:dutyLink(thisWeekDutys, doctorId, 0, weekNum, 3)}
														</td>
													</tr>
												</c:forEach>
											</table>
											
											<br>
											
											下周值班：
											<table border="1px;">
												<tr>
													<td style="width:100px;text-align: center;"></td>
													<td style="width:100px;text-align: center;">上午</td>
													<td style="width:100px;text-align: center;">下午</td>
													<td style="width:100px;text-align: center;">晚上</td>
												</tr>
												<c:forEach var="weekNum" begin="1" end="7" step="1">
													<tr>
														<td style="width:100px;text-align: center;">
															星期<c:if test="${weekNum eq 1}">一</c:if><c:if test="${weekNum eq 2}">二</c:if><c:if test="${weekNum eq 3}">三</c:if><c:if test="${weekNum eq 4}">四</c:if><c:if test="${weekNum eq 5}">五</c:if><c:if test="${weekNum eq 6}">六</c:if><c:if test="${weekNum eq 7}">日</c:if>
														</td>
														<td id="_1_${weekNum}_1" style="width:100px;text-align: center;">
															${m:dutyLink(nextWeekDutys, doctorId, 1, weekNum, 1)}
														</td>
														<td id="_1_${weekNum}_2" style="width:100px;text-align: center;">
															${m:dutyLink(nextWeekDutys, doctorId, 1, weekNum, 2)}
														</td>
														<td id="_1_${weekNum}_3" style="width:100px;text-align: center;">
															${m:dutyLink(nextWeekDutys, doctorId, 1, weekNum, 3)}
														</td>
													</tr>
												</c:forEach>
											</table>
											<br>
											<div class="control-group">
														<label class="control-label">
															<font class="msg_font_bold">门诊提示 : </font>
														</label>
														<textarea id="remind" name="remind" style="width: 58%;height: 100px;">${remind}</textarea>
												</div>
										</div>
									</div>
								</div>
								
								<div style="margin-top: 20px;padding-bottom: 20px;">
									<div align="center" style="margin-top: 10px;">
										<button type="button" class="btn btn-primary"  onclick="commitDutyRemind();">保存</button>
								 		<button type="button" class="btn btn-primary" onclick="toDoctorList();">返回</button>
									</div>
								</div>
									
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>