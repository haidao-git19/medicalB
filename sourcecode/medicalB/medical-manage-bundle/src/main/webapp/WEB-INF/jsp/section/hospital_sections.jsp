<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<option value="0">请选择科室</option>
<c:forEach var="section" items="${sections}">
	<option value="${section.id}">${section.attrname}</option>
</c:forEach>