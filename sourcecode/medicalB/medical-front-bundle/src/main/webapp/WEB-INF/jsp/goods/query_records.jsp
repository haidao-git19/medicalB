<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/medical-tags" prefix="m" %>

<c:choose>
	<c:when test="${not empty records}">
		<c:forEach var="goods" items="${records}">
			<li id="producteg_806224">
				<div class="itemSearchResultCon">
					<a class="product_pic pro_img" target="_blank" href="${ctx}/shop/anon/goods/detail?_gc=${goods.goodsCode}">
						<c:if test="${not empty goods.imgPath}"><img width="205" height="188" src="${goods.imgPath}" /></c:if>
						<c:if test="${empty goods.imgPath}"><img width="205" height="188" src="${ctx }/shop/resources/third-party/images/no_200_200_200.jpg" /></c:if>
					</a>
                  	<p class="price">
                  		<span style="color: red; font-weight: bold;">${goods.marketPrice}</span> 
                  		<del id="listprice0_783339">¥${goods.shopPrice}</del>
                  	</p>
					<a href="${ctx}/shop/anon/goods/detail?_gc=${goods.goodsCode}" target="_blank">${goods.brandName} ${goods.goodsName}</a> 
                 	<div class="buyInfo"> 
						<button class="buy" onclick="window.open('${ctx}/shop/anon/goods/detail?_gc=${goods.goodsCode}')">购买</button>
					</div>
                </div>
			</li>
		</c:forEach>
	</c:when>
	<c:otherwise>
		未发现结果
	</c:otherwise>
</c:choose>

<%--
pageNum: ${pageNum}<br>
pageSize: ${pageSize}<br>
PageCount: ${pageCount}
--%>
