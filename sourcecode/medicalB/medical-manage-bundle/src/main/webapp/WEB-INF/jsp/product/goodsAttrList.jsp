<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/third-party/js/product/goodsAttrList.js"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/product/goodsAttrEdit.js"></script>
<script type="text/javascript">
	var goodsCode='${goodsCode}';
	var categoryCode='${categoryCode}';
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">商品属性</li>
			</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<input type="hidden" name="goodsCode" id="goodsCode" value="${goodsCode}">
				<input type="hidden" name="categoryCode" id="categoryCode" value="${categoryCode}">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="goodsCode">商品编码:</label> 
						<input id="goodsCode" name="goodsCode" type="text" class="input-text-medium" placeholder="商品编码">
					</div>
					<div class="control-group inline">
						<label class="inline" for="attrCode">属性名称:</label> 
						<select id="attrCode" name="attrCode" class="input-medium" placeholder="属性名称">
							<option value="">---选择属性---</option>
							<c:forEach items="${categoryAttrList}" var="categoryAttr">
								<option value="${categoryAttr.attrCode }">${categoryAttr.attrName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
				<div class="row-fluid toolbar">
	        		<button type="button" class="btn btn-primary" onclick="openAddOrModifyGoodsAttr('')">新增</button> 
	        		<button type="button" class="btn btn-primary" onclick="history.go(-1);">返回</button>   		
	        	</div>
	       </form>				
		</div>
	    <table id="_goods_attr_table" class="table table-hover table-bordered table-condensed table-striped">
			<tbody>
			</tbody>
		</table>
	</div>
	<jsp:include page="/WEB-INF/jsp/product/goodsAttrEdit.jsp"></jsp:include>
</body>
</html>