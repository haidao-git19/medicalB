<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:forEach var="floor" items="${floors}" varStatus="stat">
	<li class="s_m">
		<a class="nav_link" onmouseover='_hover("navHover${stat.count}")'>${floor.title}<i></i></a>
		<ul style="display: none;" id="navHover${stat.count}">
			<c:forEach var="floorItem" items="${floor.items}">
				<c:if test="${floorItem.type eq 1}">
					<li><a rel="nofollow" href="${ctx}/shop/anon/goods/query?catId=${floorItem.catId}">${floorItem.catName}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</li>
</c:forEach>