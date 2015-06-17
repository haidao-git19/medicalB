<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/medical-tags" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>医保宝_网上药店领导者_网上购买药品的放心药房网</title>
	
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/css/product_detail_2014.css" />
	
	<script type="text/javascript" src="${ctx }/resources/web/js/goods/goods.js"></script>
</head>

<body>
<!--header-->
<%----%>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>

<!--面包屑-->
<div class="wrap clearfix">
	<div class="detailnav">
		<span class="linkhome"><a href="#">首页</a></span>
		<span class="arrow">&gt;</span>
		<span>${goodsVo.goods.goodsName}</span>
	</div>
</div>
<!--面包屑-->

<div class="wrap_detail">
	<div class="detail_top clearfix">
		<!--大图-->
		<div class="sideleft fl" id="gallery_view">
			<div class="sideleft fl" id="gallery_view">
				<div style="position: relative;" class="pic mb5" id="mainPic">
					<a id="productImgA" href="${goodsVo.images[0].bigImg}" rel="gal1" class="jqzoom" style="outline-style: none; cursor: crosshair; position: relative; height: 298px; width: 298px;" title="${goodsVo.goods.goodsName}"> 
						<img id="productImg" title="${goodsVo.goods.goodsName}" src="${goodsVo.images[0].bigImg}" alt="${goodsVo.goods.goodsName}" >
					</a>
				</div>
				
				<div class="picbox" id="slider">
					<div class="imgpreview" id="prevBtn"></div>
					<div class="imgnext" id="nextBtn"></div>
					<div class="dt_scrollable">
						<div id="sliderImgs" class="clearfix">
							<ul id="thumblist" class="clearfix">
								<c:forEach var="goodsImg" items="${goodsVo.images}" varStatus="stat">
									<li>
										<a <c:choose><c:when test="${stat.index eq 0}">class="zoomThumbActive"</c:when><c:otherwise>class="className"</c:otherwise></c:choose>
											href='javascript:void(0);' rel="{gallery: 'gal1', smallimage:'${goodsImg.smallImg}', largeimage:'${goodsImg.bigImg}'}">
											<img src="${goodsImg.smallImg}">
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			
				<!-- 收藏分享 -->
			</div>
		</div>
		<!--大图-->

		<!--产品属性-->
		<div class="middle_property">
			<h1>
				${goodsVo.goods.goodsName}<span class="red giftRed">${goodsVo.goods.sellingPoints}</span>
			</h1>

			<div class="shangpin_info">
				<dl class="clearfix">
					<dt class="price_dt">药网价：</dt>
					<dd>
						<span class="good_price">&yen;${goodsVo.goods.shopPrice}</span>
						<span class="old_price">市场价：<del>&yen;${goodsVo.goods.marketPrice}</del></span>
					</dd>
				</dl>

				<div class="xiliping" a="1">
					<dl class="clearfix">
						<dt>数 量：</dt>
						<dd>
							<div class="num_section clearfix">
								<div class="num_box">
									
									<form id="shoppingCartForm">
										<input type="hidden" name="goodsCode" value="${goodsVo.goods.goodsCode}" />
										<input id="goodsSingePrice" type="hidden" name="goodsPrice" value="${goodsVo.goods.shopPrice}" />
										<input id="product_amount" type="text" name="goodsAmount" value="1" class="num" limit="0" moq="0" readonly="readonly" />
									</form>
									
									<input type="button" onclick="updatedProducts(1);" class="num_pre">
									<input type="button" onclick="updatedProducts(-1);" class="num_next">
								</div>
								<p name="showLimit"></p>
							</div>
						</dd>
					</dl>
				</div>
				
				<dl class="clearfix btnCartDl">
					<dd>
						<input id="seriesCartButton" type="button" class="btn_shopping_cart" onclick="addItem();" value="加入购物车">
					</dd>
					<dd>
						<p class="proTips">
							本商品由医保宝自营提供<br>
							现货，18:00前下单当天出库，部分城市支持次日达。<a target="_blank" href="javascript:;" rel="nofollow">运费详情 &gt;&gt;</a>
						</p>
					</dd>
				</dl>

			</div>

			<div class="promise">
				<span class="fl m0">支持</span>
				<c:if test="${m:bitCalc(goodsVo.goods.sellCharacter,1) eq 1}">
					<span class="zhengping_100">
						<a rel="nofollow" target="_blank" href="#"><i></i>100%正品</a>
					</span>
				</c:if>
				
				<c:if test="${m:bitCalc(goodsVo.goods.sellCharacter,2) eq 2}">
					<span class="yaojian">
						<a rel="nofollow" target="_blank" href="#"><i></i>药监认证</a>
					</span>
				</c:if>
			</div>
		</div>
		<!--产品属性-->
	</div>
</div>

<div class="wrap_detail clearfix">
	<div id="top" class="detail_left">
		<div id="detialMenu">
			<div class="detail_menu clearfix">
				<ul class="navList clearfix">
					<li class="on">
						<a href="javascript:;">商品详情</a>
					</li>
				</ul>
			</div>
		</div>
		
		<div id="tabCon">
			<div class="detail_tab" style="display: block;">
				<div class="goods_intro">
					<table>
						<col width="10%">
						<col width="31%">
						<col width="10%">
						<tr>
							<th>商品名称：</th>
							<td colspan="3">${goodsVo.goods.goodsName}</td>
						</tr>
						<tr>
							<th>品 牌：</th>
							<td>${goodsVo.goods.brandName}</td>
							<th>规 格：</th>
							<td>${goodsVo.goods.spec}</td>
						</tr>
						<tr>
							<th>重 量：</th>
							<td>${goodsVo.goods.weight}</td>
							<th>生产厂商:</th>
							<td>${goodsVo.goods.producer}</td>
						</tr>
						<tr>
							<th>批准文号：</th>
							<td colspan="3">${goodsVo.goods.license}</td>
						</tr>
						<tr>
							<th>温馨提示：</th>
							<td colspan="3">部分商品包装更换频繁，如货品与图片 不完全一致，请以收到的商品实物为准</td>
						</tr>
					</table>
				</div>

				<div id="prodDetailCotentDiv" style="display: block" class="detail_wrapper self_goods clearfix">
					${goodsVo.goods.details}
				</div>
			</div>
		</div>
	</div>

</div>

<div class="float_box" id="rightFloat">
	<a href="javascript:;" class="f_back" target="_blank" title="用户反馈" name="yao_docuor" rel="nofollow">用户反馈</a>
	<a href="javascript:;" onclick="jQuery.top();" class="f_top" target="_self" title="返回顶部" rel="nofollow">top</a>
</div>

<div style="display:none; position: absolute; width: 0px; height: auto; left: 711.5px; top: 600px; z-index: 10000;" id="addCartInfo">
	<input type="hidden" value="success" id="addProductResult">
	
	<div class="spop" id="addCartWin">
		<div class="spoptop">
			<a class="popwinClose" onclick="$('#addCartInfo').hide();" href="javascript:void(0);">关闭</a>
			<font>购物车</font>
		</div>
		<div class="spopro">
			<div class="spopimg">
				<img src="${goodsVo.images[0].bigImg}" width="100" height="100" >
			</div>
			<div class="spopbox">
				<p class="spopstitle"></p>
				<span>加入数量：<span id="add_amt"></span></span>
				<span>总计金额：<span><i class="qian">&yen;<span id="add_price"></span></i></span></span>
				<div class="spopbtn">
					<a class="red_btn" href="javascript:void(0)" onclick="$('#addCartInfo').hide();">继续购物</a>
					<a class="red_border_btn" href="${ctx}/anon/buy/cart">去结算</a>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>

<!--footer-->
<%----%>
<jsp:include page="/WEB-INF/jsp/common/foot.jsp"></jsp:include>

</body>
</html>
