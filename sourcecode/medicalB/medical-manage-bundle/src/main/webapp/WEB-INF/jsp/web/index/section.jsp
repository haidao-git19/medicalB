<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="isJbLShow" value="0"></c:set>
<c:set var="isJbRShow" value="0"></c:set>
<c:forEach items="${sectionFilter.filter}" var="filter" varStatus="index">
<c:choose>
<c:when test="${isJbLShow==0&&index.count<5}">
<div class="find_jb_l">
<c:set var="isJbLShow" value="1"></c:set>
</c:when>
<c:when test="${isJbRShow==0&&index.count==5}">
</div>
<div class="find_jb_r">
<c:set var="isJbRShow" value="1"></c:set>
</c:when>
</c:choose>
<strong>${filter.attrname}</strong>
<ul>
	<c:forEach items="${filter.list}" var="section">
		<li><a target="_blank" class="black_link" href="/jibing/yigan/daifu.htm">${section.attrname}</a></li>
	</c:forEach>
	<li><a class="blue2" target="_blank" href="/jibing/neike/list.htm">更多&gt;&gt;</a></li>
</ul>
<div class="clear"></div>
</c:forEach>

</div>
