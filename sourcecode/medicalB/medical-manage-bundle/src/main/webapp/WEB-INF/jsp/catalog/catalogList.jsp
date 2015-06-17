<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/third-party/js/catalog/catalogList.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
					<!-- 左边导航菜单 begin -->
					<jsp:include page="/WEB-INF/jsp/navLeft.jsp"></jsp:include>
					<!-- 左边导航菜单 end -->
				</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="catalogName">类目名称:</label> 
						<input id="catalogName" name="catalogName" type="text" class="input-text-medium" placeholder="类目名称">
					</div>
					<div class="control-group inline">
						<label class="inline" for="goodsName">商品名称:</label> 
						<input id="goodsName" name="goodsName" type="text" class="input-text-medium" placeholder="商品名称">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
				<div class="row-fluid toolbar">
	        		<button type="button" class="btn btn-primary" onclick="openAddOrModifyCatalogGoods('')">新增</button>   		
	        	</div>
	       </form>				
		</div>
	    <table id="_catalog_table" class="table table-hover table-bordered table-condensed table-striped">
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>