var isReplayed = true;
function appearAndShareExperience_pe() {
	if(!loginCheck(window.location.href)) return;
	
	if(!isReplayed){
		$("#isReplayed").show();
		return;
	}
	$('#addReview_pic').attr('href', '/product/' + item_iid + '.html');
	$('#addReview_pic').next().attr('href', '/product/' + item_iid + '.html');
	$('#addReview_pic > img').attr('src', 'http://p4.maiyaole.com/img/' + parseInt(item_iid/1000) + '/' + item_iid + '/100_100.jpg');
	$($(".p_r_leftimg img")).attr('src', 'http://p4.maiyaole.com/img/' + parseInt(item_iid/1000) + '/' + item_iid + '/100_100.jpg');
	$('#addReview_pic').next().html(item_name);
	
	if(!loginCheck(window.location.href.split('.html')[0] + '.html#buyer_experience'))return;
	$('#productExperienceContent').html('');
	$.getScript('http://s.maiyaole.com/js/201306/jquery.rate.js', function(){
		icResult = 1;
		if(icResult == 0) {
			var a = jQuery("#ex_prompt");
			return;
		}
		if (icResult == 1) {
			var comment_pop = document.getElementById("myComment");
			if (comment_pop != null) {
				initCommentDialog();
			}
		}
	});
}

//弹出框中 发表评伦
function deployShareExperience_pe(flag) {
	var goodsid = item_iid;
	
	if(!goodsid)return;
	
	var j = /<[^> ]+>/;
	var a = jQuery("#productExperienceContent").val();
	if (a && "" != jQuery.trim(a)) {
		a = a.replace(/(^\s*)|(\s*$)/g, "");
		if (a.length > 200) {
			jQuery("span[id='titleContent']").html(
					"<em class='r'>超出200个字的字数限制</em>");
			return;
		}
		if (a.length < 5) {
			jQuery("span[id='titleContent']").html(
					"<em class='r'>内容不少于5个字</em>");
			return;
		}
		if (j.exec(a)) {
			jQuery("span[id='titleContent']").html(
					"<em class='r'>请填写评论内容(5-200个字)</em>");
			return;
		}
	} else {
		jQuery("span[id='titleContent']").html(
				"<em class='r'>请填写评论内容(5-200个字)</em>");
		return;
	}

	var ratingCom = jQuery('#stars1-input');
	var rating = ratingCom.val();
	if (rating == "" || rating == null) {
		jQuery("span[id='stars1-tips']")
				.html("<em class='r'>请选择商品满意度</em>");
		return;
	}
	
	a = encodeURI(encodeURI(a));
	$.getJSON("http://my.111.com.cn/front/insertReview.action?review.reviewType=2&review.content=" + a
			+ "&review.grade=" + rating + "&flag=" + flag + "&goodsid=" + item_iid + "&jsoncallback=?"
		,function(json) {
			 if(json.code == 1){
				 loadReviewList(1);
				 $('#myComment').hide();
				 return;
			 }else if(json.code==2){
				 $('#titleContent').html('<font color="red">评论标题长度在4-20个字之间</font>');
			 }else if(json.code==3){
			 	 $('#titleContent').html('<font color="red">评论内容长度在5-300个字之间</font>');
			 }else if(json.code==6){
		     $('#titleContent').html('<font color="red">对不起，发表评论失败，请稍候再试！</font>');
			 }else if(json.code==7){
			 	 // 类型不对
			 	 $('#titleContent').html('<font color="red">服务器繁忙，请稍候再试！</font>');
			 }else if(json.code==8){
			 	 $('#titleContent').html('<font color="red">评分在1-5之间！</font>');
			 }else if(json.code==0){
			 	 $('#titleContent').html('<font color="red">您已经发表过评论！</font>');
			 }else if(json.code==10){
			 	 $('#titleContent').html('<font color="red">您没有购买过此商品或者您的订单未完成！</font>');
			 }else{
			 	 // 代码异常
				 $('#titleContent').html('<font color="red">服务器繁忙，请稍候再试！</font>');
			 }
			 $('#titleContent').fadeOut(function(){$(this).fadeIn()});
		});
}


function initCommentDialog() {
	var attributeSettingDetailsDescriptions = $("#attributeSettingDetailsDescriptions").val();
	var stars = new EStars("stars1");
	if (currSiteId == 1 && currSiteType == 2) {
		var star_desc = new EStars("stars_desc", desc_tips);
		var star_atti = new EStars("stars_atti", atti_tips);
		var star_logi = new EStars("stars_logi", logi_tips);
	}
	var ids = null;
	if (attributeSettingDetailsDescriptions
			&& attributeSettingDetailsDescriptions != '') {
		ids = new Array();
		var ids = attributeSettingDetailsDescriptions.split("_");
		for (i = 0; i < ids.length; i++) {
			var str = ids[i];
			var keyVal = str.split("-");
			var id = keyVal[0];
			ids[i] = id;
			var val = keyVal[1];
			var descriptions = val.split(",");
			var o = new Object();
			o.tipsTxt = [ "1分-" + descriptions[0], "2分-" + descriptions[1],
					"3分-" + descriptions[2], "4分-" + descriptions[3],
					"5分-" + descriptions[4] ];
		}
	}
	showDivToCenter($('#myComment'));
}

function showDivToCenter(div){
		div.css("position","absolute");   
		div.css("top", ($(window).height() - div.height()) / 2+$(window).scrollTop() + "px");   
		div.css("left", '50%');
		div.css("margin-left", -div.width()/2);
		div.show();	
}

function showQuestionConfirm(){
	var q_confirm = $('#pop_submit_confirm');
	
	showDivToCenter(q_confirm);
	window.setTimeout(function(){q_confirm.hide()}, 3000);
}

function submit_qution(){
	var goodsid = item_id;
	var content = $('#question_content').val();
	if(!content || content == '请输入10～200个有效字符'){
		remind('question_content', '<font color="red">请输入10～200个有效字符</font>');
		return false;
	}
	if(content.length < 10 || content.length > 200){
		$('#question_content').prev().html('<font color="red">请输入10～200个有效字符</font>');
		remind('question_content', '<font color="red">请输入10～200个有效字符</font>');
		return false;
	}
	var qtype = $('input[name="productQuestion.questiontype"]:checked').val();
	
	if(!qtype){alert('请选择发问的类型!!');}
	
	content = encodeURI(encodeURI(content));
	
	$.getJSON("http://my.111.com.cn/front/insertReview.action?review.reviewType=1&review.subject=" + content
		+ "&review.consultationType=" + qtype + "&flag=1" + "&goodsid=" + item_iid + "&jsoncallback=?"
		,function(json) {
			 if(json.code <= 1){
					loadQuestion(1);
					$('#p_question').hide();
					showQuestionConfirm();
			 }else if(json.code==2){
				 remind('question_info', '<font color="red">问题长度在10-200个字之间</font>');
			 }else if(json.code==6){
					remind('question_info', '<font color="red">对不起，发表提问失败，请稍候再试！</font>');
			 }else if(json.code==7){
					remind('question_info', '<font color="red">服务器繁忙，请稍候再试！</font>');
			 }
		})
}

function remind(id, info){
	$('#' + id).prev().fadeOut("flow",function(){
		$('#question_content').prev().html(info);
		$('#' + id).prev().fadeIn("flow");
		$("#question_content").focus();
 });
}
// 促销
var html_inner = '';
var html="";
var provinceId = $.cookie('provinceId')||'1';
if(typeof item_prescription == "undefined" || item_prescription != 16){
$.getJSON('http://www.111.com.cn/front/promotion/product/list.action?productNo=' + item_id + '&province=' + provinceId, function(data){
	data.sort(function(a,b){
		return a.promotionType > b.promotionType;
	});
	var div = '';
	for(var i = 0; i < data.length; i++){
		var d = data[i];
		
		var mzjf;
		
		if((d.promotionType+'').match(/^3/g)){
			
			mzjf = d.proMzsets;
			div += "<p class='fristTit'></p><div class='giftBox'>"
			/*stone*/
			for(var k = 0; k < mzjf.length; k++){
				var zjf = mzjf[k];
				var items = d.proGifts;
				items.sort(function(a,b){return a.quantity < b.quantity});
				div += '<p class="zenTit"><span class="icon_meng">赠品</span><em class="zenping" >'
					+ ((d.promotionType==32)?'买<b>'+zjf.quantity+'</b>件':'买满<em class="qian">&yen;</em>'+zjf.quantity)
					+ '，即可免费获赠以下任意<b>' + zjf.price + '</b>件商品，抢完为止</em><span class="moreList">&nbsp;</span></p>';
				
				div += '<div class="zenpingList"><ul>';
				for(var y = 0; y < items.length; y++){
					var it_ = items[y],n,stock = ' 先抢先得 ',iid = it_.itemId;
					var zzpImg6 = it_.mainimg6;
					if(d.promotionType == 42){
						n = it_.productName;
					} else {
						if(it_.schemeId != zjf.id){continue}
						if(it_.quantity<1){
							stock = '<font color="red"> 已抢完 </font>';
						}
						n = it_.giftName;
					}
					div += '<li><a class="imgBox" title="' + n + '" href="http://www.111.com.cn/product/' + iid + '.html" target="_blank"><img src="' + zzpImg6 + '" title="' + n + '"></a><div class="text"><a  title="' + n + '" href="http://www.111.com.cn/product/' + iid + '.html" target="_blank">'+ n + '</a><span>' + stock + '</span></div></li>';
				}
				div += '</ul></div>';
			}
			div += '<p><a href="http://www.111.com.cn/list/promotion.action?promoid=' + d.promotionId + '" target="_blank" type="gift_info">查看全部活动商品 &gt;&gt;</a><br>赠品可在购物车中领取</p></div>';
			
		}else if((d.promotionType+'').match(/^2/g)){
			mzjf = d.proMfsets;
			
			
			for(var k = 0; k < mzjf.length; k++){
			var zjf = mzjf[k];
				div += '<p><span class="icon_meng">满减</span><a class="zenping" href="http://www.111.com.cn/list/promotion.action?promoid='+ d.promotionId + '" target="_blank" type="gift_info">'
					+ ((d.promotionType==22)?'买<b>'+zjf.quantity+'</b>件':'买满<em class="qian">&yen;</em>'+zjf.quantity)
					+ '，返<b><em class="qian">&yen;</em>' + zjf.price + '</b>元，返完为止</a></p>';
			}
			
		}else if((d.promotionType+'').match(/^1/g)){
			mzjf = d.proMjsets;
			for(var k = 0; k < mzjf.length; k++){
			var zjf = mzjf[k];
			div += '<p><span class="icon_meng">满减</span><a class="zenping"  href="http://www.111.com.cn/list/promotion.action?promoid=' + d.promotionId + '" target="_blank" type="gift_info">'
					+ ((d.promotionType==12)?'买<b>'+zjf.quantity+'</b>件':'买满<em class="qian">&yen;</em>'+zjf.quantity)
					+ '，减<em class="qian">&yen;</em><b>' + zjf.price + '</b>元，减完为止</a></p>';
			}
			
		}else if(d.promotionType == 42){
			mzjf = d.redemptionItems;
			for(var k = 0; k < mzjf.length; k++){
			var zjf = mzjf[k];
			items = d.redemptionItems;
				
				var url = 'http://www.111.com.cn/list/promotion.action?promoid=' + d.promotionId;
				div += '<p><span class="icon_meng">满减</span><a class="zenping" href="' + url + '" target="_blank" type="gift_info">'+ d.giftName + '</a><a class="blue m0" href="' + url + '" target="_blank" type="gift_info">点击参加</a></p>';
			}
			
		}
	}
	html_inner += div;
	html = '<dl class="clearfix"><dt>优　惠：</dt><dd>'+ html_inner + '</dd></dl>';	
	if(html_inner&&html_inner!=""){
		$('#newpromotion2').html(html);
	}
	hideZZP();
});
};

function hideZZP(){
	$('.fristTit').html($('.giftBox .zenTit').eq(0).html());
	$('.giftBox').hide();
	$(".fristTit .moreList").show();
	$('.giftBox .zenTit').eq(0).hide();
	
	$(".fristTit .moreList").toggle(
  	function () {
			$(this).addClass('moreListDown');
			$(this).closest('.fristTit').next('.giftBox').slideDown(350);
		},
		function () {
			$(this).removeClass('moreListDown');
			$('.giftBox').slideUp(350);
		}
	);
	$(".fristTit .zenping").click(function(){
			$(".fristTit .moreList").trigger("click");	
	});
}