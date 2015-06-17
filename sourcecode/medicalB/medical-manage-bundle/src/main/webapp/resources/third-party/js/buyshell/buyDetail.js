var screenHeight = $(window).height(); 
var screenWidth = $(window).width(); 
var images = {
	    url: []
   };
var detailUrl = null;
/*window.onload = function(){
	$(".main_image").css('height',screenHeight-220);
	$(".main_image ul").css('height',screenHeight-220);
	$(".main_image li").css('height',screenHeight-220);
	$(".main_image li span").css('height',screenHeight-220);
	$(".main_image li a").css('height',screenHeight-220);
	$("div.flicking_con .flicking_inner").css('top',screenHeight-240);
}*/

$(function() {
	if(type == '1'){
		$("#orderBut").val('立即更换');
	}else if(type == '4'){
		$("#orderBut").val('立即兑换');
	}else{
		if( goodsStock  == '0'){
			$("#tips").html("<img src=\""+ctx+"/resources/third-party/images/kf.png\">&nbsp;&nbsp;&nbsp;商品已售完");
			$("#tips").show();
			$("#orderBut").val('立即预约');
		}else{
			$("#orderBut").val('立即购买');
		}
	}
	
	$("#orderBut").on('click',function(){
		toOrderPage();
	})
	queryGoodsDetail();
	queryCommentList();
	scrollLoadData();
	$(window).bind("load", function() {    
		var timeout = setTimeout(function() {$("img").trigger("sporty")}, 5000);    
	});     
});

function init() {
	$(".main_visual").hover(function() {
		$("#btn_prev,#btn_next").fadeIn()
	}, function() {
		$("#btn_prev,#btn_next").fadeOut()
	})
	$dragBln = false;
	$(".main_image").touchSlider(
			{
				flexible : true,
				speed : 200,
				btn_prev : $("#btn_prev"),
				btn_next : $("#btn_next"),
				paging : $(".flicking_con a"),
				counter : function(e) {
					$(".flicking_con a").removeClass("on").eq(e.current - 1)
							.addClass("on");
				}
			});
	$(".main_image").bind("mousedown", function() {
		$dragBln = false;
	})
	$(".main_image").bind("dragstart", function() {
		$dragBln = true;
	})
	$(".main_image a").click(function() {
		if ($dragBln) {
			return false;
		}
	})
	timer = setInterval(function() {
		$("#btn_next").click();
	}, 5000);
	$(".main_visual").hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval(function() {
			$("#btn_next").click();
		}, 5000);
	})
	$(".main_image").bind("touchstart", function() {
		clearInterval(timer);
	}).bind("touchend", function() {
		timer = setInterval(function() {
			$("#btn_next").click();
		}, 5000);
	})
}

function queryGoodsDetail() {
	var flicking_inner_div = $("#flicking_inner_div").html();
	var main_image_ul = $("#main_image_ul").html();
	var goods_detail_div = $("#goods_detail_div").html();
	$("#flicking_inner_div").empty();
	$("#main_image_ul").empty();
	$("#goods_detail_div").empty();
	
	$.ajax({
		url : ctx + "/anon/buyDetail",
		data : [ {
			name : 'goodsCode',
			value : goodsCode
		}, {
			name : 'goodsVersion',
			value : goodsVersion
		} ],
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			var d = data.goodsDetail;
			var attrs = "";
			if(d.attrList != undefined && d.attrList != null){
				$.each(d.attrList,function(i, a) {
					attrs = attrs + a.attrKey + ":" + a.attrValue +"&nbsp;&nbsp;";
				});
			}
			goodsStock = getNumber(d.goodsStock);
			detailUrl = getString(d.goodsdetailUrl);
			$("#goods_detail_div").append(goods_detail_div.format(getNumber(d.praiseNum),getString(d.goodsName),getNumber(d.shopPrice),getString(attrs)));
			
			if(d.imageList != undefined && d.imageList != null){
				$.each(d.imageList,function(i, b) {
					images.url[i] = getString(b.imageUrl)+"?ver="+goodsVersion;
					var imageUrl = '<img class=\"main_diy_image lazy\" data-original="'+ getString(b.imageUrl)+"?ver="+goodsVersion +'" width=\"100%\">';
					//var imageUrl = '<img class=\"main_diy_image\" src="'+ getString(b.imageUrl)+"?ver="+goodsVersion +'" width=\"100%\" height=\"'+(screenHeight-240)+'\">';
					$("#main_image_ul").append(main_image_ul.format(imageUrl));
					if(i == 0){
						$("#flicking_inner_div").append(flicking_inner_div.format(i+1));
					}else{
						$("#flicking_inner_div").append("<a>"+(i+1)+"</a>");
					}
				});
				if(d.imageList.length >= 1){
					FGetImg(images.url[0],function(img){
						setSliderImage(img.height);
						$("#flicking_inner_div").show();
						$("#main_image_ul").show();
						$("#goods_detail_div").show();
						$("img.lazy").lazyload({effect: "fadeIn",event : "sporty" ,placeholder:ctx+"/resources/third-party/images/product_default_img.png"});
					});
				}
				if(d.imageList.length > 1){
					init();
				}
				openDetail();
			}
		}
	});
}

wx.ready(function () {
	   $(".main_diy_image").click(function() {
		   var url = $(this).attr("src");
		   wx.previewImage({
			   current: url,
			      urls: images.url
		   });
		});
});

function queryCommentList() {
	var pltxt_ul = $("#pltxt_ul").html();
	$("#pltxt_ul").empty();
	$.ajax({
		url : ctx + "/anon/queryCommentList",
		data : [ {
			name : 'goodsCode',
			value : goodsCode
		}, {
			name : 'pageId',
			value : 0
		},{
			name : 'sortKey',
			value : 'desc'
		}],
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			var totalCount = getNumber(data.totalCount);
			$("#totalcount_span").text("("+totalCount+"人评论)");
			if(data.commentList != undefined && data.commentList != null){
				$.each(data.commentList,function(i, b) {
					var _icon = null;
					if(b.icon == null || b.icon == '' || b.icon == undefined ){
						_icon = ctx + '/resources/third-party/images/user0.png';
					}else{
						_icon = b.icon;
					}
					var icon = '<img src="'+ getString(_icon) +'" width=\"48\" heigth=\"48\">';
					$("#pltxt_ul").append(pltxt_ul.format(getString(b.createTime),icon,getString(b.nickName),getString(b.contents)));
				});
			}
			$("#pltxt_ul").show();
		}
	});
}

function toOrderPage() {
	var userId = queryUserId();
	if(userId == null || userId == ''){
		alert("亲，您还没登录，请先登录。");
		window.location.href = ctx + '/anon/login?isOauth=1&returnUrl='+callbackUrl;
		return ;
	}
	window.location.href = ctx + '/anon/toOrderPage?ver='+version+'&goodsStock='+goodsStock+'&type='+type+'&goodsCode='+goodsCode+'&goodsVersion='+goodsVersion;
}

function toCommentsPage(){
	var userId = queryUserId();
	if(userId == null || userId == ''){
		alert("亲，您还没登录，请先登录。");
		window.location.href = ctx + '/anon/login?isOauth=1&returnUrl='+callbackUrl;
		return ;
	}
	window.location.href = ctx + '/anon/toCommentsPage?goodsCode='+goodsCode;
}
function praise(){
	$("#praiseImg").unbind();
	var userId = queryUserId();
	if(userId == null || userId == ''){
		alert("亲，您还没登录，请先登录。");
		window.location.href = ctx + '/anon/login?isOauth=1&returnUrl='+callbackUrl;
		return ;
	}
	
	var cookie=$.cookie(userId+"_praiseRecord_"+goodsCode);
	if(cookie&&cookie=="1"){
		alert("您已经赞过啦亲!");
		return;
	}
	
	var praiseNumStr=$("#praise").clone().children().remove().end().text();
	$.ajax({
		url:ctx+"/anon/clickPraise",
		type:"post",
		data:{"userId":userId,"goodsCode":goodsCode,"praiseNumStr":praiseNumStr},
		dataType:"json",
		success:function(data){
			if(data.code=="0"){
				$("#praise").html('<img id="praiseImg" onclick="javascript:praise();" src="'+ctx+'/resources/third-party/images/icon-gd.png" width="20"><br/>'+data.praiseNum+'');
			}else{
				alert("赞不了啦,歇一会吧亲~");
			}
		}
	});
}

function scrollLoadData(){
	var range = 50;             //距下边界长度/单位px  
    var maxnum = 20;            //设置加载最多次数  
    var num = 1;  
    var totalheight = 0;   
    var main = $("#list");                     //主体元素  
    var isload = false;
    $(window).scroll(function(){  
        var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)  
        totalheight = parseFloat($(window).height()) + parseFloat(srollPos);  
        if(($(document).height()-range) <= totalheight  && num != maxnum) { 
        	if(!isload){
        		$("#pullDiv").hide();
        		isload = true;
        	}
            num++;  
        }  
    });  
}

function openDetail(){
	$.ajax({
		url:ctx+"/anon/toMoreDetail",
		type:"post",
		data:{"url":detailUrl},
		dataType:"html",
		success:function(data){
			$("#detailDiv").html(data);
		}
	});
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