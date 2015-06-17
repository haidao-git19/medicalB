<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/medical-tags" prefix="m" %>

<input type="hidden" id="defCatId" value="${navis[0].childs[0].childs[0].id}" />

<c:forEach var="_firstLvl" items="${navis}">
	<h2 class="no_bd_b" onclick="tabFirst(${_firstLvl.id})"><span>${_firstLvl.name}</span></h2>
	<div class="show" id="tabFirst_${_firstLvl.id}">
	<c:forEach var="_secdLvl" items="${_firstLvl.childs}">
		<h3 class="no_bd_b" onclick="tabSecd(${_secdLvl.id})"><a class="icon_btn" href="javascript:void(0);"></a> <span>${_secdLvl.name}</span></h3>
		<c:if test="${not empty _secdLvl.childs}">
			<ul class="show" id="tabThrd_${_secdLvl.id}">
				<c:forEach var="_thrdLvl" items="${_secdLvl.childs}">
					<li><a href="javascript:void(0);" onclick="categoty_options(${_thrdLvl.id})" class="${_firstLvl.id}_${_secdLvl.id}_${_thrdLvl.id} ">${_thrdLvl.name}</a></li>
				</c:forEach>
			</ul>
		</c:if>
	</c:forEach>
	</div>
</c:forEach>