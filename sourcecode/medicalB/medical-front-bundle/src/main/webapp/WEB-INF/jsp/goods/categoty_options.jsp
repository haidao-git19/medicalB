<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/medical-tags" prefix="m" %>

<c:choose>
	<c:when test="${not empty optionMap}">
		<c:forEach var="_optionMap" items="${optionMap}">
			<dl class="clearfix ">
				<dt>${_optionMap.key.optionClsName}：</dt>
				<dd>
					<div class="selection">
						<ul class="filterAttrList filterMultiList clearfix">
							<c:forEach var="_option" items="${_optionMap.value}">
								<li> <a id="opt_${_option.scId}_${_option.id}" class="opt_${_option.scId}" href="javascript:void(0);" onclick="list(${_option.catId}, ${_option.scId}, ${_option.id})"><span>${_option.optionName}</span></a></li>
							</c:forEach>
						</ul>
					</div>
				</dd>
			</dl>
		</c:forEach>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>