var right_floor = "";
var right_top_floor = "";
var right_bottom_floor = "";

$(function() {
	poster();
	shopList();
	recomGoods();
	discountGoods();
	queryFilter();
});

/**
 * 查询滚动海报；
 */
function poster() {
	var flexslider = $(".flexslider").html();
	$(".flexslider").empty();
	
	$.ajax({
		url : ctx + "/anon/posterList",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					$(".flexslider").append(flexslider.format(getString(a.url),getString(a.image)));
				});
			}

			$(".flexslider").show();
			$('.flexslider').flexslider({
		        animation: "slide",
		        start: function(slider){
		          $('body').removeClass('loading');
		        }
		     });
		}
	});
}

/**
 * 查询合作伙伴；
 */
function shopList() {
	var doctor_detail = $("#doctorSlide0").html();
	var to_detail_url = url + "/anon/goods/detail?_gc=";
	$("#doctorSlide0").empty();
	
	$.ajax({
		url : ctx + "/anon/queryShopList",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					if(i >= 8){
						return;
					}
					var _detailUrl = to_detail_url + a.shopID;
					$("#doctorSlide0").append(doctor_detail.format(getString(a.logo)));
				});
			}
			$("#doctorSlide0").show();
		}
	});
}

/**
 * 查询推荐商品；
 */
function recomGoods() {
	var main_ul = $(".main_ul").html();
	var image = '<img src=\"{0}\" onerror=\"imgERROR(this,\'no_pic_80_80.jpg\');\">';
	var to_detail_url = url + "/anon/goods/detail?_gc=";
	$(".main_ul").empty();
	
	$.ajax({
		url : ctx + "/anon/queryRecomGoodsList",
		type : "POST",
		dataType : "json",
		data : {"label":"3"},
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					var _detailUrl = to_detail_url + a.goodsCode;
					var _image = image.format(getString(a.imgPath));
					$(".main_ul").append(main_ul.format(getString(a.mediumName),_image,getString(a.longName),getString(a.shopPrice),getString(a.marketPrice),_detailUrl));
				});
			}
			$(".main_ul").show();
		}
	});
}

/**
 * 查询促销商品；
 */
function discountGoods() {
	var product_list = $(".product_list").html();
	var image = '<img alt=\"{0}\" src=\"{1}\" onerror=\"imgERROR(this,\'no_200_200_200.jpg\');\">';
	var to_detail_url = url + "/anon/goods/detail?_gc=";
	$(".product_list").empty();
	
	$.ajax({
		url : ctx + "/anon/queryRecomGoodsList",
		type : "POST",
		dataType : "json",
		data : {"label":"2"},
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					if(i + 1 > 5){
						return;
					}
					var _detailUrl = to_detail_url + a.goodsCode;
					var _image = image.format(getString(a.longName),getString(a.imgPath));
					$(".product_list").append(product_list.format(_detailUrl,_image,getString(a.longName),getString(a.shopPrice),getString(a.marketPrice)));
				});
			}
			$(".product_list").show();
		}
	});
}

/**
 * 查询药品功效筛选条件；
 */
function queryFilter() {
	var floor = $("#floorDiv").html();
	var image = '<img alt=\"{0}\" src=\"{1}\" onerror=\"imgERROR(this,\'no_200_200_200.jpg\');\">';
	var to_detail_url = url + "/anon/goods/detail?_gc=";
	$("#floorDiv").empty();
	$.ajax({
		url : ctx + "/anon/queryFilter",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			var len = data.length;
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					right_floor = '';
					
					var floorName = a.attrvalue;
					var floorNodes = childNodes(a.list);
					if(floorNodes == null || floorNodes == '' || floorNodes == 'null'){
						return ;
					}
					drugsForDisease(floor,(len--),floorName,floorNodes)
				});
			}
		}
	});
}

function childNodes(data){
	if(data == null || data.size == 0){
		return null;
	}
	
	var nohotnode = "<a href=\"{0}\">{1}</a> ";
	var nodes = '';
	$.each(data,function(i, a) {
		var floorName = a.level;
		if(a != null && a.level == '2'){
			nodes = nodes +  childNodes(a.list);
		}else if(a != null && a.level == '3'){
			if(right_floor == ''){
				right_floor = a.attrvalue;
			}else{
				right_floor = right_floor + "|" + a.attrvalue;
			}
			var to_list_url = url + "/anon/list?id="+a.id;
			nodes = nodes +nohotnode.format(to_list_url,getString(a.attrvalue));
		}
	});
	return nodes;
} 

function drugsForDisease(floor,index,floorName,floorNodes){
	var diseaseType = $("#diseaseType").html();
	var showPro = $("#showPro").html();
	var top_image = '<img src=\"{0}\" alt=\"{1}\" height=\"195\" width=\"215\" onerror=\"imgERROR(this,\'no_200_200_200.jpg\');\">';
	var bottom_image = '<img src=\"{0}\" alt=\"{1}\"  onerror=\"imgERROR(this,\'no_pic_80_80.jpg\');\">';
	var to_detail_url = url + "/anon/goods/detail?_gc=";
	
	$.ajax({
		url : ctx + "/anon/queryGoodsListByKey",
		type : "POST",
		dataType : "json",
		data : {"keys":right_floor},
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			
			right_top_floor = "";
			right_bottom_floor = "";
			
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					if(i >= 5){
						return;
					}
					
					var _detailUrl = to_detail_url + a.goodsCode;
					var _top_image = top_image.format(getString(a.coverImgPath),getString(a.longName));
					var _bottom_image = bottom_image.format(getString(a.imgPath),getString(a.longName));
					
					right_top_floor = right_top_floor + diseaseType.format(_detailUrl,_top_image,getString(a.diseaseName));
					right_bottom_floor = right_bottom_floor + showPro.format(_detailUrl,_bottom_image,getString(a.longName),getString(a.shopPrice));
				});
				
				$("#floorDiv").append(floor.format(index,floorName,floorNodes,right_top_floor,right_bottom_floor));
				$("#floorDiv").show();
			}
		}
	});
}
