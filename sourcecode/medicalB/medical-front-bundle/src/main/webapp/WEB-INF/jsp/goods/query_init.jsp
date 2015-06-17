<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/medical-tags" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>医保宝_网上药店领导者_网上购买药品的放心药房网</title>
	
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/product_detail_2014.css?ver=${version}" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/pagination.css?ver=${version}" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/head201406.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/list.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/search_zuhe.css" />
	
	<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.pagination.js?ver=${version}"></script>
	<script type="text/javascript" src="${ctx }/resources/web/js/goods/list.js?ver=${version}"></script>
</head>

<body>
<!--header-->
<%----%>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>

<div class="wrap clearfix">
	<div class="wrap clearfix"> 
    	<div class="searchColSub fl"> 
			<div class="itemChoose mt">
				<div class="itemChooseBox" id="categoty_list"></div>
      		</div>
        </div>

		<div id="bodyRight" class="searchColMain fr">
			<div class="searchCrumb clearfix">&nbsp;</div>
			<%--
			<div class="searchCrumb clearfix"> <span class="resultNum"> 搜索结果(<span id="num">1839</span>) </span>
				<ul>
					<li class="clearfix no_bd_b"><a class="linkOne catalogs"><span>中西药品</span></a><span>&gt;</span></li>
					<li class="one clearfix lastOne "><a class="linkOne catalogs"><span>风湿骨科</span></a></li>
				</ul>
			</div>
			--%>
   
			<div id="search_result"> 
				<div class="seachListBox">
					<div class="searchColMainItem searchResultOp mt" id="categoty_options"></div>
				</div>
        
        		<div id="plist">
					<div id="search_table" class="search_table">
						<div class="itemSearchResult clearfix fashionList"><ul class="itemSearchList" id="Searchresult"></ul></div>
						<div  id="Pagination"></div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<%-- --%>
<input type="hidden" id="catId" name="catId" value="${catId}" />
<input type="hidden" id="scId" name="scId" value="${scId}" />
<input type="hidden" id="catScOptId" name="catScOptId" value="${catScOptId}" />

<jsp:include page="/WEB-INF/jsp/common/paginationSettings.jsp"></jsp:include>

<!--footer-->
<%----%>
<jsp:include page="/WEB-INF/jsp/common/foot.jsp"></jsp:include>

</body>
</html>
