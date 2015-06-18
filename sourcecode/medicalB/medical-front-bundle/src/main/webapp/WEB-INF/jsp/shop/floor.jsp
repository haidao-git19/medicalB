<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/medical-tags" prefix="m" %>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>

<c:forEach var="floor" items="${floors}">
	<div class="wrap floor clearfix">
		<div class="floorNav">
			<h3 class="floorName"><span>${floor.floor}F<a class="ml13">${floor.title}</a></span></h3>
		</div>
	
		<div id="fl1" class="floor_l">
			<c:forEach var="floorItem" items="${floor.items}">
				<c:if test="${floorItem.type eq 1}">
					<a target="_blank" href="${ctx}/anon/goods/query?catId=${floorItem.catId}">${floorItem.catName}</a>
				</c:if>
			</c:forEach>
		</div>
		
		<div id="fr1" class="floor_r">
			<ul class="diseaseType">
				<c:forEach var="floorItem" items="${floor.items}">
					<c:if test="${floorItem.type eq 2}">
						<li>
							<h3><a target="_blank" href="${ctx}/anon/goods/query?catId=${floorItem.catId}">${floorItem.catName}</a></h3>
							<c:if test="${not empty floorItem.link}">
							<a class="f_img" target="_blank" href="${floorItem.link}">
							</c:if>
								<c:if test="${not empty floorItem.coverImg}"><img width="215" height="195" src="${floorItem.coverImg}"></c:if>
								<c:if test="${empty floorItem.coverImg}"><img width="205" height="188" src="${ctx }/resources/third-party/images/no_200_200_200.jpg" /></c:if>
							<c:if test="${not empty floorItem.link}">
							</a>
							</c:if>
						</li>
					</c:if>
				</c:forEach>
			</ul>
			
			<div class="showPro">
				<c:forEach var="floorItem" items="${floor.items}">
					<c:if test="${floorItem.type eq 2}">
						<div class="f_prod">
							<div class="pic">
								<a target="_blank" href="${ctx}/anon/goods/detail?_gc=${floorItem.goodsCode}">
									<c:if test="${not empty floorItem.imgPath}"><img src="${floorItem.imgPath}"></c:if>
									<c:if test="${empty floorItem.imgPath}"><img width="205" height="188" src="${ctx }/resources/third-party/images/no_200_200_200.jpg" /></c:if>
								</a>
							</div>
							<div class="title">
								<a target="_blank" href="${ctx}/anon/goods/detail?_gc=${floorItem.goodsCode}">${floorItem.brandName}&nbsp;${floorItem.goodsName}${floorItem.spec}</a>
							</div>
							<span class="price_l"><i>¥</i>${floorItem.shopPrice}</span>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</c:forEach>