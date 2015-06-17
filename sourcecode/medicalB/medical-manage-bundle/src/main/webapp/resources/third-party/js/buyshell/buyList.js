var screenHeight = $(window).height(); 
var screenWidth = $(window).width(); 
var images = {
	    url: []
   };

function scrollPagination(obj,type,param1,param2) {
	var _itemTemp = $("#_itemTemp").html();
	obj.scrollPagination({
		'contentPage' : ctx + '/anon/buyShellList',
		'contentData' : {
			'pageId' : 0,
			'showCount' : 6,
			'type':type,
			'attrKey11':'机型',
			'attrKey12':param1,
			'attrKey21':'风格',
			'attrKey22':param2
		},
		'scrollTarget' : $(window),
		'heightOffset' : 50,
		'beforeLoad' : function() {
			$('#loading').fadeIn();
		},
		'afterLoad' : function(elementsLoaded, data) {
			$('#loading').fadeOut();
			if (data.goodsList == null || data.goodsList == '' || data.code !='0') {// data为空则停止滚动事件
				if(type=='3'){
					++x;
				}
				if(x==1){
					$("#emptyBox").show();
					$("#noGoodsWords").show();
				}
				$("#moreGoods").hide();
				$('#nomoreresults').fadeIn();
				obj.stopScrollPagination();
			} else {
				++x;
				$("#p").val(parseInt($("#p").val()) + 1)// 否则把page加1设置到页面input
				$.each(data.goodsList, function(i, a) {
					var imageUrl = '<img class=\"lazy\" data-original="'+ getString(a.imageUrl+"?ver="+a.goodsVersion) +'">';
					var goodsName = getString(a.goodsName);
					if(goodsName != null && goodsName.length >= 21 ){
						goodsName = goodsName.substring(0,21)+'...';
					}
					obj.append(_itemTemp.format(imageUrl,getString(goodsName),getNumber(a.buyCount),getNumber(a.praiseCount),getString(a.goodsCode),getString(a.goodsVersion),getNumber(a.goodsStock)));
				});
				$("img.lazy").lazyload({effect: "fadeIn",placeholder:ctx+"/resources/third-party/images/product_default_img.png"});
			}
		}
	});
}


$(function() {
	$("img[id='closeImage']").on('click',function(){
		$("#picScroll").css("display","none");
	})
});

function queryDetail(goodsCode,goodsVersion,goodsStock,goodsName){
	if(goodsCode == null || goodsCode ==''){
		alert('亲，您还没选择您想要的宝贝哦。');
		return ;
	}
	window.location.href = ctx + '/anon/toBuyDetail?type='+type+'&goodsCode='+goodsCode+'&goodsVersion='+goodsVersion+'&goodsStock='+goodsStock;
}

function setSliderImage(height){
	if(screenWidth < height){
		height = screenWidth;
	}
	$(".main_image").css('height',height);
	$(".main_image ul").css('height',height);
	$(".main_image li").css('height',height);
	$(".main_image li span").css('height',height);
	$(".main_image li a").css('height',height);
	$("div.flicking_con .flicking_inner").css('top',height-20);
}

/**
 * 动态获取图片的宽度和高度的像素值
 * @param sUrl
 *            图片的url
 * @param fCallback
 *            回调函数，fCallback至少有一个类型为object类型的参数用来接收图片的宽、高、url
 * usage: var url;
 * FGetImg(url,function(img){alert('width:'+img.width+";height:"+img.height+";url:"+img.url);});
 */
var FGetImg = function(sUrl, fCallback) {
	var img = new Image();
	img.src = sUrl + '?t=' + Math.random(); // IE下，ajax会缓存，导致onreadystatechange函数没有被触发，所以需要加一个随机数
	if (FBrowser.isIE) {
		img.onreadystatechange = function() {
			if (this.readyState == "loaded" || this.readyState == "complete") {
				fCallback({
					width : img.width,
					height : img.height,
					url : sUrl
				});
			}
		};
	} else if (FBrowser.isFirefox || FBrowser.isSafari || FBrowser.isOpera
			|| FBrowser.isChrome) {
		img.onload = function() {
			fCallback({
				width : img.width,
				height : img.height,
				url : sUrl
			});
		};
	} else {
		fCallback({
			width : img.width,
			height : img.height,
			url : sUrl
		});
	}
};

//浏览器类型检测
var FBrowser=(function(){
    var ua = navigator.userAgent;
    var isOpera = Object.prototype.toString.call(window.opera) == '[object Opera]';
    return {
      isIE:             !!window.attachEvent && window.ActiveXObject && !isOpera,
      isOpera:          isOpera,
      isSafari:         ua.indexOf('AppleWebKit/') > -1,
      isFirefox:        ua.indexOf('Gecko') > -1 && ua.indexOf('KHTML') === -1,
      MobileSafari:     /Apple.*Mobile.*Safari/.test(ua),
      isChrome:         !!window.chrome,
    };
})();