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
								<img width="215" height="195" src="${floorItem.coverImg}">
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
									<img src="${floorItem.imgPath}">
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

<%--
<div class="wrap floor clearfix">
	<div class="floorNav">
		<h3 class="floorName">
			<span>1F<a class="ml13">维生素、钙片</a></span>
		</h3>
	</div>

	<div id="fl1" class="floor_l">
		<a href="http://180.153.55.124:8089/shop/anon/list?id=654326509">螯合钙</a>
		<a href="http://180.153.55.124:8089/shop/anon/list?id=654326510">液体钙</a>
	</div>
	
	<div id="fr1" class="floor_r">
		<ul class="diseaseType">
			<li>
				<h3>
					<a href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">螯合钙</a>
				</h3>
				<a class="f_img" href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">
					<img width="215" height="195" src="http://180.153.55.124:8089/shop/resources/third-party/images/no_200_200_200.jpg">
				</a>
			</li>
			<li>
				<h3>
					<a href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">螯合钙</a>
				</h3>
				<a class="f_img" href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">
					<img width="215" height="195" src="http://180.153.55.124:8089/shop/resources/third-party/images/no_200_200_200.jpg">
				</a>
			</li>
		</ul>
		
		<div class="showPro">
			<div class="f_prod">
				<div class="pic">
					<a target="_blank" href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">
						<img src="http://180.153.55.124:8089/shop/resources/third-party/images/no_pic_80_80.jpg">
					</a>
				</div>
				<div class="title">
					<a target="_blank" href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">痤康王</a>
				</div>
				<span class="price_l"><i>¥</i>9.8</span>
			</div>
			<div class="f_prod">
				<div class="pic">
					<a target="_blank" href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">
						<img src="http://180.153.55.124:8089/shop/resources/third-party/images/no_pic_80_80.jpg">
					</a>
				</div>
				<div class="title">
					<a target="_blank" href="http://180.153.55.124:8089/shop/anon/goods/detail?_gc=CATEGORY_DRUG_CLKS1">痤康王</a>
				</div>
				<span class="price_l"><i>¥</i>9.8</span>
			</div>
		</div>
	</div>
</div>
--%>