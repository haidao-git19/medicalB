<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>宝贝详情</title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
    	
    	<script src="${ctx }/resources/third-party/js/jquery.event.drag-1.5.min.js" type="text/javascript"></script>
    	<script src="${ctx }/resources/third-party/js/jquery.touchSlider.js?ver=${version}" type="text/javascript"></script>
    	<script src="${ctx }/resources/third-party/js/jquery.lazyload.min.js?ver=${version}" type="text/javascript"></script>
    	
		<link href="${ctx }/resources/third-party/css/global.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/wnclient.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/touchSlider.css?ver=${version}" rel="stylesheet" type="text/css">

		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js?ver=${version}" type="text/javascript"></script>
		<script type="text/javascript">
        	var goodsCode = '${param.goodsCode}';
        	var goodsVersion = '${param.goodsVersion}';
        	var goodsStock = '${param.goodsStock}';
        	var type = '${param.type}';
        	var flag = false;
        	wx.config({
	    	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    	    appId: '${appId}', // 必填，公众号的唯一标识
	    	    timestamp: '${timestamp}', // 必填，生成签名的时间戳
	    	    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
	    	    signature: '${signature}',// 必填，签名，见附录1
	    	    jsApiList: ['checkJsApi',
	    	                'chooseImage',
	    	                'previewImage',
	    	                'uploadImage',
	    	                'downloadImage'
   	           ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	    	});
		</script>
        <script src="${ctx }/resources/third-party/js/buyshell/buyDetail.js?ver=${version}" type="text/javascript"></script>
	</head>
	
<body class="appbg pb60">
<div id="list">
	<div id="_buyDetail" >
		<div class="wtbg ct-pro">
			<div class="showpic" style="position:relative;">
				<div class="main_visual">
		            <div class="flicking_con">
		                <div class="flicking_inner picroll" id="flicking_inner_div" style="display:none;">
		                    <a style="margin-left:42%;">{0}</a>
		               	</div>
		            </div>
					<div class="main_image">
						<ul id="main_image_ul" style="display:none;">					
							<li><span>{0}</span></li>
						</ul>
						<a href="javascript:;" id="btn_prev"></a>
						<a href="javascript:;" id="btn_next"></a>
					</div>
				</div>
			</div>
			<div id="goods_detail_div" style="display:none;">
				<p class="pl10  pr10 pt10 clearfix"><span id="praise" class="fr tc orange f12 lh150"><img id="praiseImg" onclick="javascript:praise();" src="${ctx }/resources/third-party/images/icon-gd.png" width="20"><br/>{0}</span><span class="f16 titbox lh130">{1}</span><div class="cb"></div></p>
			    <p class=" pl10 orange f20">￥{2}&nbsp;&nbsp;<span class="f12 gray" style="text-decoration: line-through;">￥338</span></p>
			    <p class=" pl10 orange f14 pb10">手机壳+终身免费换+55元大礼包</p>
			    <p class="pl10 pb10 gray f14">{3}</p>
			    <p class="showpic pl10 pr10"><img src="${ctx }/resources/third-party/images/banner.png"></p>
			    <div style=" width:60px; margin:10px auto;" id="pullDiv"><img src="${ctx }/resources/third-party/images/jiantou.png" width="60" height="60" style=" position:fixed;"></div>
			</div>
		</div>
		<div class="mt10" id="detailDiv">
		</div>
	</div>
	<div class="btmbar p10 box-sizing">
	    <span class="fr"> <input name="submit" type="button" value="立即购买" class="btn-org" id="orderBut"></span>
	    <span onclick="toCommentsPage();" class="f14 blue" style=" position:absolute;top:1.2rem; line-height:2rem;"><img src="${ctx }/resources/third-party/images/kf.png" width="20" class="fl">&nbsp;&nbsp;查看评论</span>
	</div>
</div>
</body>
</html>
