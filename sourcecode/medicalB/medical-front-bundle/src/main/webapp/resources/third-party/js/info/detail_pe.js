//我要回答的框为空的效果
function normal(id,times)
{

        var obj=$("#"+id);
        obj.css("background-color","#FFF");
        if(times<0)
        {
                return;
        }
        times=times-1;
        setTimeout("error('"+id+"',"+times+")",200);
}
function error(id,times){
        var obj=$("#"+id);
        obj.css("background-color","#fee");
        times=times-1;
        setTimeout("normal('"+id+"',"+times+")",250);
}
//我要回答的框为空的效果 end

$(function () {
	var qFlag=0;
	$("#experienceSubmit").click(function() {
		if(!loginCheck(window.location.href+"#detialMenu")) return;
		
		var goodsid = item_iid;
		var content = $('#question_content').val();
		var defaultVal = "";
		if(!content || content == defaultVal){
			normal('question_content','2');
			qFlag=0;
			return false;
		}
		if(content.length < 10 || content.length > 200){
			qFlag=0;
			//alert("您好，提问内容字数为10-200字");
			$('#popQuestion .questionPopText').html("您好，提问内容字数为10-200字");
			$('#popQuestion').show();
			return false;
		}
		
		content = encodeURI(encodeURI(content));
		if(qFlag <= 0){
			$.ajax({
				type:"get",
				dataType: "json",
				url: "http://www.111.com.cn/front/advisory/saveQuestion.action",
				data:{
					"review.reviewType" : 1,
					"review.goodsId" : goodsid ,
					"review.subject" : content
				},
				success: function(data) {
					if(data.code <= 1){
						qFlag = 1;
						//alert("问题提交成功");
						$('#popQuestion .questionPopText').html("问题提交成功");
						$('#popQuestion').show();
					}else if(data.code==2){
						//alert("问题长度在10-200个字之间");
						$('#popQuestion .questionPopText').html("问题长度在10-200个字之间");
			            $('#popQuestion').show();
						qFlag=0;
						return false;
					}else if(data.code==6){
						//alert("对不起，发表提问失败，请稍候再试！");
						$('#popQuestion .questionPopText').html("对不起，发表提问失败，请稍候再试！");
						$('#popQuestion').show();
						qFlag=0;
						return false;
					}else if(data.code==7){
						//alert("服务器繁忙，请稍候再试！");
						$('#popQuestion .questionPopText').html("服务器繁忙，请稍候再试！");
						$('#popQuestion').show();
						qFlag=0;
						return false;
					}
					$("#question_content").val("");
				}
			});
		}
	
	});
});


//初始化问题类型
function load_popwin_question_tips(a) {
	//$("#configed_question_tips").hide();
	//$("#question_tips_1").hide();
	//$("#question_tips_2").hide();
	//$("#question_tips_3").hide();
	//$("#question_tips_4").hide();
	//$("#question_tips_5").hide();
	var b = $("#productId").val();
	
	//获取问题类型
	$.getJSON(detailPath.ctxDomain + "/item/ajax/ajaxViewQATips.do?callback=?", {
		"questionPamsMode.questiontype": a,
		"questionPamsMode.productid": b
	},
	function(c) {
		if (c.data != null) {
			$("#configed_question_tips_text").html(c.data);
			$("#configed_question_tips").show()
		} else {
			var d = "#question_tips_" + a;
			$(d).show()
		}
	})
	
}

var questions_type_temp = "";
//返回提交的问答
var questions_type_temp = "";
function submit_qution_afert(c) {
	switch (c.code) {
	case 1:
		alert("请您根据提示信息准确填写验证信息。");
		break;
	case 2:
		$(".popwinClose").click();//关闭弹出框

		//loadProductQA(1);更新商品
		var a = $(window).scrollTop();
		var b = $(window).height();
		$("#pop_submit_confirm").css({
			top: (b - 120) / 2 + a
		});
		$("#pop_submit_confirm").show();
		setTimeout("confirmSubmit()", 3000);
		if (questions_type_temp != "") {
			mouseover(questions_type_temp)
		}
		break;
	case 3:
		alert("请选择提问类型");
		break;
	case 4:
		alert("提问内容过长");
		break;
	case 5:
		alert("提问过程中出错，再次提交");
		break
	}
	$("#question_content").val("请输入10～200个有效字符")
}

function loadProductQA(b) {
	var c = /^[0-9]+$/;
	if (!c.test(b)) {
		b = 1
	}
	var a = $("#productQATabNav li").index($("#productQATabNav li.select"));
	var d = {
		"product.id": $("#mainProductId").val(),
		"questionPamsMode.questiontype": a + 1,
		"pagenationVO.currentPage": b
	};
	$.getJSON(detailPath.ctxDomain + "/item/ajax/ajaxViewQA.do?callback=?", d,
	function(e) {
		$("#productQA").html(e.value)
	})
}

function mouseover(d) {
	var c = $("#productQATabNav li");
	if (c && c.size() > 1) {
		var a = $("#productQATabNav li.select");
		var b = $(this);
		a.removeClass("select");
		if (d == 1) {
			c.eq(0).addClass("select")
		}
		c.eq(d - 1).addClass("select");
		loadProductQA(1)
	}
}

// 收藏
function itemFavorite(){
	if(loginCheck(window.location.href)){
		$.getScript('http://s.maiyaole.com/js/zDialog/popDialog.js', function(){
			$.getScript('http://s.maiyaole.com/js/zDialog/zDrag.js', function(){
				$.getScript('http://s.maiyaole.com/js/zDialog/zDialog.js', function(){
					addFavorite(item_iid);
				});
			});
		});
	}
}


function loadQuestion(page, istop){
	if(istop && !istop) $("#detialMenu").attr("top", "false");
	if($("#detialMenu").attr("top") != "true") {
		if($("#detialMenu").hasClass("detialMenu_fix"))
			$.top($("#detialMenu").offset().top - 10);
	} else {
		$.topObj($("#question_content"));
	}
	if(!page){page=1};
	var time = new Date().format("yyMMddhh");
	$.ajaxSetup({cache: true});
	
	var url = 'http://www.111.com.cn/interfaces/review/questionlist/html.action?goodsId='
				+ item_iid + '&pageIndex=' + page + '&type=-1&_'+time;
				
	$.ajax({
		url: url,
		dataType: 'text',
		timeout: 5000,
		success: function(data){
			$('.question_txt').html(data);
		}
	});
}


var reviewScore = "";
function loadReviewList(page, istop){
	if(istop && !istop) $("#detialMenu").attr("top", "false");
	if($("#detialMenu").attr("top") != "true") {
		if($("#detialMenu").hasClass("detialMenu_fix"))
			$.top($("#detialMenu").offset().top - 10);
	} else {
		$.topObj($("#review_head_count"));
	}
	
	if(!page){page=1};
	$.ajaxSetup({cache: true});
	var time = new Date().format("yyMMddhh");
	var url = 'http://www.111.com.cn/interfaces/review/list/html.action?goodsId='
	    + item_iid + '&pageIndex=' + page + "&score=" + reviewScore + '&_'+time;
      
	$.ajax({
		url: url,
		dataType: 'text',
		timeout: 5000,
		success: function(data){
			$('#review_divlist').html(data);
		}
	});
}

function loadReviewList1(){
	$("#detialMenu").attr("top", "true");
	$.ajax({
		url: "http://www.111.com.cn/interfaces/review/list/html.action?goodsId="
			+ item_iid + "&pageIndex=1&score=&isShowAll=0",
		dataType: 'text',
		timeout: 5000,
		success: function(data){
			$('#review_divlist').html(data);
			initReviewCount($(".otherCompanyReview"));
		}
	});
	
	$.ajax({
		url: "http://www.111.com.cn/interfaces/review/questionlist/html.action?goodsId="
			+ item_iid + "&pageIndex=1&type=-1",
		dataType: 'text',
		timeout: 5000,
		success: function(data){
			$('.question_txt').html(data);
		}
	});
}

function loadItemDesc(){
	$($('#productQATabNav > li')).click(function(){
		loadQuestion(1);
	})
}

function showBuyTc(arr1){
	if(!sdata_)return;
	var taocan1Store = true;
	if(arr1){
		for(var k = 0; k < arr1.length; k++){
			var tcItemid = arr1[k].productno;
			if(sdata_[tcItemid] && sdata_[tcItemid] > 0){
				continue;
			}else{
				taocan1Store = false;
				break;
			}
		}
	}else{return}
	
	// 套餐有缺货的buy_pre_btn
	$('#buyTc').unbind('click');
	if(!taocan1Store){
		$('#buyTc').attr('class', 'sold_out_btn');
	}else{
		$('#buyTc').attr('class', 'buy_pre_btn');
		$('#buyTc').bind('click', function(){
			addItemToCart(3, item_iid, -1);
		})
	}
}

function doStock(sdata, productnos, allproductnos, data){
	var index = index2 = 0;
	var html = '';
	myStock = sdata[item_pno] > 0;
	if (myStock) {
		$('#seriesCartButton').attr('class', 'add_shopping_cart');
	} else {
		$('#seriesCartButton').attr('class', 'input_sellout');
		$('#seriesCartButton').attr('onclick', null);
		$('#seriesCartButton').val('商品已售完');
	}
	$('#buyButtonsDistrict').show();

	// 特殊属性
	if (!productnos) {productnos_ = 'null'; return};
	for (var k in data) {
		if (k == 'selected') break;

		// 判断是否显示图片
		var isPic = data[k][0].split('^')[3] != 'null';
		var name = (k.length == 2 ? (k.split('')[0] + '　　' + k.split('')[1]) : k);

		if (isPic) {
			html += '<dl class="clearfix"><dt class="color_lineheight">' + name + '：</dt><dd><ul id="color_list" class="color_list">'
		} else {
			html += '<dl class="clearfix"><dt class="size_lineheight">' + name + '：</dt><dd><ul id="size_list" class="size_list">'
		}

		for (v = 0; v < x[k].length; v++) {
			var value_att = x[k][v];
			var isSelect = false;
			var no_ = allproductnos.split(',')[index2];
			var hasStock = sdata[no_] > 0;
			// 已选择
			var slet_ = x['selected'][index].split('^')[1];
			if (value_att.split('^')[0] == slet_) {
				isSelect = true;
			}
			html += getLi(value_att, isSelect, isPic, hasStock);
			index2++;
		}
		index++;
		html += '</ul></dd></dl>';
	}
	$('#product_attri').prepend(html);
	html__ = html;

	if (x) {
		var sel = x['selected'];
		for (var i = 0; i < sel.length; i++) {
			sltVal += sel[i].split('^')[1] + "  ";
		}
		$('#seriesSelectColor').html(sltVal);
		$('#seriesShow').show();
	}
	checkYao();
}

$.ajaxSetup({cache: false});

function getLi(li, isSelect, isPic, hasStock){
	var rel = '';
	var vals = li.split('^');
	var pic = vals[3];
	var itemid = vals[1];
	var pname = vals[0];
	var slt = ' class="';
	var url = 'http://www.111.com.cn/product/' + itemid + '.html';
	slt += isSelect?'selected':'';
	slt += hasStock?'':' no_model';
	slt += '"';
	
	if(isPic){
		rel += '<a href="' + url + '"><li' + slt + '><img width="43" height="43" title="'
			+ pname + '" id="color_' + pname + '" src="' + pic + '"></li></a>';
	} else {
		rel += '<a href="' + url + '"><li' + slt + '><span id="size_' 
			+ pname + '" title="' + pname + '">' + pname +'</span></li></a>';
	}
	return rel;
}
//20141103

function updatedProducts(n,obj){
	var $obj = $(obj);
	var numObj = $obj.parent().find(".num");
	var nv = numObj.val() == "" ? 0 : numObj.val();	
	var num = parseInt(nv) + n;
	var limit = parseInt(numObj.attr("limit"));
	var moq = parseInt(numObj.attr("moq"));
	var itemid = numObj.attr("itemid");
	var stock = getCurrentStockByIid(itemid);
	//stone
	if(num<moq){
		numObj.addClass('redInput');
	}else{
		numObj.removeClass('redInput');	
	}
	if(num==0){
		$obj.closest('.num_section').find('.selectBox').removeClass('errorRed');
	}
	//stone end
	if(numObj.attr("stock") == "false") return;
	
	num = num < 0 ? 0 : num;
	num = num > 999 ? 999 : num;
	
	var numPre = numObj.parent().find(".num_pre");
	var numNext = numObj.parent().find(".num_next");
	
	// 大于库存
	if(num > stock) {
		var num_ = limit <= 0 ? stock : (stock > limit ? limit : stock);
		numObj.val(num_);
		showLimitDiv($obj, num_, "stock");
		numNext.removeClass("num_next_disabled");
		numPre.addClass("num_pre_disabled");
		return;
	}
	
	// 大于限购
	if(limit > 0 && num > limit) {
		numObj.val(limit);
		showLimitDiv($obj, limit, "limit");
		numNext.removeClass("num_next_disabled");
		numPre.addClass("num_pre_disabled");
		return;
	}
	
	// 小于起售
	if(moq && num > 0 && num < moq) {
		showLimitDiv($obj, moq, "moq");
		numObj.val(moq);
		numPre.removeClass("num_pre_disabled");
		numNext.addClass("num_next_disabled");
		return;
	}
	
	// 起购
	if(moq && num > 0 && num == moq){
		numNext.addClass("num_next_disabled");
	} else {
		numNext.removeClass("num_next_disabled");
	}
	
	// 限购
	if(limit > 0 && (num >= limit || num == stock)){
		numPre.addClass("num_pre_disabled");
	} else {
		numPre.removeClass("num_pre_disabled");
	}
	
	if($('[name="product_amount"]').length == 1)
		num = num < 1 ? 1 : num;
	
	numObj.val(num);
}

function getCurrentStockByIid(itemid){
	if(!itemid) return myStock;
	
	var pno = $($("li[itemid='" + itemid + "']")[0]).attr("pno");
	if(!pno) pno = item_pno;
	var stock = getCurrentStock(pno, stocks);
	
	if(!stock) stock = myStock;
	
	return parseInt(stock);
}

var tmid = 0;
function showLimitDiv(obj, num, type){
	if(num <= 0) return;
	
	window.clearTimeout(tmid);
	
	var div;
	if(type == "moq")
		div = $('<div class="amount-msg"><span style="color: red;">' + num + '</span>件起购<em></em></div>');
	else if(type == "stock")
		div = $('<div class="amount-msg">最多购买<span style="color: red;">' + num + '</span><em></em></div>');
	else
		div = $('<div class="amount-msg">每人限购<span style="color: red;">' + num + '</span>件<em></em></div>');
	$(".amount-msg").remove();
	obj.parent().append(div);
	
	tmid = window.setTimeout(function(){
		$(".amount-msg").fadeOut("slow", function(){
			$(".amount-msg").remove();
		});
		window.clearTimeout(tmid);
	},2000);
}

//20141103 end

function youTellFocus(o){
	$(o).addClass('focusIput');
    if($(o).val() == "请输入联系电话"){
        $(o).val("");
    }else{
    	o.select();
    }
}
function youTellBlur(o){
	$(o).removeClass('focusIput');
    if($(o).val() == ""){
        $(o).val("请输入联系电话");
    }
}

var regex = {
    //phone: /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/,
    phone : /[0-9]{3,4}[—|-]\d{7,8}$/,
    mobile : /^0?(13[0-9]|15[0-9]|18[0-9]|14[57]|170|17[0-9])[0-9]{8}$/
}

function getUserIdName(){
    var uReg = new RegExp(".*UserId=(.*?)&UserName=(.*?)&.*");
    return uReg.exec($.cookie("UserInfo"));
}

var userInfo = "";
function loadUserInfo(){
    if(!createUser()) return "";
    var uInfo = getUserIdName();

    $.ajax({
        url : "http://passport.111.com.cn/sso/customer/getUserInfoJsonP.action",
        type : "GET",
        dataType : "JSONP",
        async : false,
        data : "customer.ecUserId=" + uInfo[1] + "&customer.id=" + uInfo[2] + "&jsoncallback=?",
        success:function(user){
        	userInfo = $.stringifyJSON(user);
        	if(userInfo){
        		var val = "";
        		if(user.UserFax != null && user.UserFax != '' && user.UserFax != 'null'){
        			val = user.UserFax;
        		}else if(user.UserMobile != null && user.UserMobile != '' && user.UserMobile != 'null'){
        			val = user.UserMobile;
        		}else if(user.UserPhone != null && user.UserPhone != '' && user.UserPhone != 'null'){
        			val = user.UserPhone;
        		}
			    	$('.your_tell').val(val);
			    }
        }
    })
}


//20141103
function callMe(){
	var textVal="";
	var htmlPop='<div class="detailPop"><div class="popBg" onClick="closePop(this)"></div><div class="pop_div pop_div02 popConts"><div class="title_div"><a href="javaScript:;"  onClick="closePop(this)" class="closeBtn">&nbsp;</a>壹药网温馨提示</div><div class="popText"></div></div></div>';
  var phone = $('#phonecall').val();
  
	if(phone == "" || phone == "请输入联系电话"){
	textVal='<p class="noticeTips">请输入电话号码</p><div class="okBtn"><a class="bule_btn" href="javaScript:;"  onClick="closePop(this)">确 定</a></div>';
			$('body').append(htmlPop);
			$('.popText').html(textVal);
      return;
  }
  
  if(!regex.phone.test(phone) && !regex.mobile.test(phone)){
		textVal='<p class="errTips">联系电话格式不正确。</p><p>手机号为11位数字，座机格式如：021-88888888</p><div class="okBtn"><a href="javaScript:;" class="bule_btn"  onClick="closePop(this)">确 定</a></div>';
		$('body').append(htmlPop);
		$('.popText').html(textVal);
    return;
  }

  var islogin = createUser() ? 1 : 0;
  $.ajax({
	    url : "/front/user/userCallbackPhone.action",
	    type : "POST",
	    dataType : "text",
	    data : "itemid=" + item_iid + "&phone=" + phone + "&islogin=" + islogin + "&userInfo=" + userInfo,
	    success : function(data){
			    if(data == "SUCCESS"){
			      textVal='<p class="sucTips">您的号码已成功提交!</p><p>稍后会有壹药网药师与您联系，请您保持电话畅通。<br>如紧急，请拨打400-007-0958加1咨询。</p><p class="yellow">当日21点后提交电话，将在次日早晨9点后回拨。</p><div class="okBtn"><a  href="javaScript:;" class="bule_btn" onClick="closePop(this)">确 定</a></div>';
			    } else if(data == "NULL"){
						textVal='<p>您的号码已经登记，请不要重复提交！</p><div class="okBtn"><a href="javaScript:;" class="bule_btn"  onClick="closePop(this)">确 定</a></div>';
			    } else if(data == "FORMATERROR"){
						textVal='<p>联系电话格式不正确，请重新输入</p><div class="okBtn"><a href="javaScript:;" class="bule_btn" onClick="closePop(this)">确 定</a></div>';       
			    } else {
						textVal='<p>您好，服务器维护中，您可以使用"在线咨询"功能向药师咨询。</p><div class="okBtn" ><a href="javaScript:;" class="bule_btn" onClick="closePop(this)">确 定</a></div>';  
			    };
					$('body').append(htmlPop);
					$('.popText').html(textVal);
			}
	})
} 

function closePop(obj){
	$(obj).closest('.detailPop').remove();
	
}

//20141103 end
function getCompanyTotalNum(){
	var totalNum=0;
	$(".pro-list-dl").each(function(){
		var proListDlObj = $(this);
		 if($(this).attr("currentAreaItemId")!=$(this).attr("areaItemId")){
             if($(this).attr("site")==1||$(this).attr("site")==2||$(this).attr("site")==3){
                 if($(this).attr("site")==getSite()){
                	 totalNum+=1;
                  }
             }else{
                 totalNum+=1;
              }
        }else{
           totalNum+=1;
       }
	})
	return totalNum;
}
$(document).ready(function(){
	$('.rankUl > li').bind('click', function(){
		$.exeScroll();
	});
	var date = new Date().format("yyMMddhh");
	$.ajaxSetup({cache: true});
	$.ajax({
		url: "/interfaces/review/questionlist/count.action?productId=" + item_iid + "&pno=" + item_pno + "&_=" + date,
		dataType: 'json',
		timeout: 5000,
		success: function(d){
			var reviewSatisfaction = 0;
			if(d.count > 0)
				reviewSatisfaction = Math.ceil((d.hao*5+d.zhong*2.5)/(d.count*5)*100)
			
			reviewSatisfaction =
				d.count == 0 ? 100 : reviewSatisfaction
			
			$("[name='reviewCount']").text(d.count);
			$("[name='reviewSatisfaction']").text(reviewSatisfaction);
			$("[name='reviewHao']").text(d.hao);
			$("[name='reviewZhong']").text(d.zhong);
			$("[name='reviewCha']").text(d.cha);
			$("[name='questionCount']").text(d.questioncount);
			
			isReplayed = d.replay;
			
			if(d.count != 0){
				var cha = parseInt(d.cha/d.count*100);
				var zhong = parseInt(d.zhong/d.count*100);
				var hao = 100 - zhong - cha;
			} else {
				var cha = 0;
				var zhong = 0;
				var hao = 100;
			}
			$("#review_B_hao i").width(hao + "%");
			$("#review_B_hao .percentage").text(hao + "%");
			$("#review_B_zhong i").width(zhong + "%");
			$("#review_B_zhong .percentage").text(zhong + "%");
			$("#review_B_cha i").width(cha + "%");
			$("#review_B_cha .percentage").text(cha + "%");
		}
	});
	loadUserInfo();
	
});


/****TR的hover效果****/
$(function() {
   $(".other_price tbody tr").hover(function() {
   $(this).addClass("backOn");
   },function(){
  $(this).removeClass("backOn");
   });
});


//$(document).ready(function(){
//	//商家比价按钮，当是下架商品的时候不展示
//	if($("#1_item_companyCompare")==null||$("#1_item_companyCompare").length==0){
//		$("#desc_othercompany").hide();
//	}
//})


//商家比价js  add by yaobin

$(document).ready(function(){
	 if(status==8&&materialtype!='TCP'){
		 initMultiCompany(item_iid,item_id,skuId,8); 
	 }else{
		 $("#dimensional_box_id").show();
	 }
})

function initMultiCompany(item_iid,areaItemId,skuId,countEachPage){
    var count = getMultiCompanyNum(skuId);
    if(count==null||count<=1){
          $("#dimensional_box_id").show();
          return;
    }
    
   var data = getMultiComanyDataJson(skuId,1,countEachPage);
   initMoreBuySelect(item_iid,areaItemId,count,data);
   initCompanyCompare(count,countEachPage,data,skuId);
}
function initCompanyCompare(count,countEachPage,data,skuId){  
	$("#desc_othercompany").show();
   initCompanyList(data,-1,-1,0,countEachPage);
   initPageIndex(countEachPage,count,skuId);
}
function initCompanyList(data,skuId,pageIndex,index,countEachPage){
	initList(data,skuId,pageIndex,index,countEachPage);
	$("#desc_othercompany").click(function(){
		initReviewCount($(".otherCompanyReview"));
	})
	
	dealBlock($(".buyButtonsOtherCompany"));
}
function initReviewCount(obj){
   if(obj==null||obj==""||obj.length==0){
      return;
  }
  obj.each(function(){
      dealCompanyReviewCount($(this));
  })
}
function initList(data,skuId,pageIndex,index,countEachPage){
   var tempData = data;
   if(index==1){
       tempData = getMultiComanyDataJson(skuId,pageIndex,countEachPage);
   }
   if(tempData ==null||tempData ==""||tempData.length==0){
       return;
   }
   var html ='<colgroup><col width="28%"><col width="20%"><col width="12%"><col width="12%"><col width="13%"></colgroup>';
   html+='<thead><tr><th>商家</th>';
   html+='<th>'+(tempData[0].prescription!= 16?'价格':' 药房价格')+'</th>';
   html+='<th>是否包邮</th><th>评价</th><th>发货地/中转站</th><th>购买</th></tr></thead>';
   html+='<tbody>';
   for(var i = 0;i<tempData.length;i++){
          var temp = tempData[i];
          html+='<tr class="companyClass">';
          html+='<td><a href="http://www.111.com.cn/product/'+temp.itemId+'.shtml" target="_blank">'+temp.nickName+'</a></td>';
          html+='<td><p> <i class  = "priceStyle" >&yen;</i><b class  = "priceStyle">'+temp.originalPrice+'</b></p></td>';
          html+='<td>';
          if(temp.prescription!=16){
               if(temp.saleType==1||temp.sellType==8){
                      html+='包邮';
                }else{
                      html+='满百包邮';
                }
           }else{
                html+='--';
          }
          html+='</td>';
          html+='<td>';
          if(temp.prescription!=16){
                html+='<span class = "otherCompanyReview" productId= "'+temp.itemId+'"><img src="http://p1.maiyaole.com/images/201306/images/zoomloader.gif" width="10" height="10"></span>'
          }else{
                html+='--';
          }
          html+='</td>';
          html+='<td>';
               if(temp.saleArea==1){
                      html+='北京';
               }else if(temp.saleArea==2){
                      html+='上海';
               }else if(temp.saleArea==3){
                      html+='广州';
               }else{
                      html+=temp.deliveryPlace;
               }
          html+='</td>';
          html+='<td>';
           if((temp.materialtype  == 'ZSP' || temp.materialtype == 'TCP')){
              if(temp.prescription!=16){
                     html+='<div class="buyButtonsOtherCompany"  product_no= "'+temp.productNo+'" style="display:none;" >';
                     if(temp.materialtype == 'TCP'){
                           if(temp.sellType ==3){
                                   html+='<a href="javascript:void(0)" onclick="addItemToCart2(3, '+temp.itemId+', '+temp.itemId+');" class="addpro">我要订购</a>';
                           }else{
                                    html+= '<a href="javascript:void(0)" onclick="addItemToCart2(3, '+temp.itemId+', '+temp.itemId+');" class="addpro">加入购物车</a>';
                           }
                     }else{
                           if(temp.sellType ==3){
                                   html+='<a href="javascript:void(0)" onclick="addItemToCart2(1, '+temp.itemId+', '+temp.itemId+');" class="addpro">我要订购</a>';
                           }else{
                                    html+= '<a href="javascript:void(0)" onclick="addItemToCart2(1, '+temp.itemId+', '+temp.itemId+');" class="addpro">加入购物车</a>';
                           }                           
                     }
              }else{
                     html+='<a href="javascript:;" onclick="Detector.floatclick(4)"  class="consult_btn"  sellType="'+temp.sellType+'">在线咨询</a>';
             }
          }
          html+='</td>';
          html+='</tr>';
   }
   html+='</tbody>';
   $("#other_price_id").html(html);
}

//20141105
function initPageIndex(countEachPage,count,skuId){
   var totalPageNum = count%countEachPage ==0?parseInt(count/countEachPage):parseInt(count/countEachPage)+1;
   var html = "<li style='display:none;'><a onclick='javascript:loadCompanyPage(1,-1,"+skuId+","+totalPageNum+","+countEachPage+");' href='#desc_othercompany' class='page-prev'>上一页</a></li>";
    for(var i=0;i<totalPageNum;i++){
         if(i==0){
                       html+='<li><span class="page-cur">'+(i+1)+"</span></li>";
          }else if(i<7){
                    html+="<li><a  onclick='javascript:loadCompanyPage(2,"+(i+1)+","+skuId+","+totalPageNum+","+countEachPage+");' href='#desc_othercompany'>"+(i+1)+"</a></li>";
          }else{
                    html+="<li style= 'display:none'><a  onclick='javascript:loadCompanyPage(2,"+(i+1)+","+skuId+","+totalPageNum+","+countEachPage+");' href='#desc_othercompany'>"+(i+1)+"</a></li>";
          }
        
     }
     if(totalPageNum>1){
           html+="<li><a onclick='javascript:loadCompanyPage(3,-1,"+skuId+","+totalPageNum+","+countEachPage+");' href='#desc_othercompany' class='page-next'>下一页</a></li>";
     }
     html+="<li>共"+totalPageNum+"页</li>";
     $("#companyRollUl").append(html);
}

//20141105 end
function loadCompanyPage(index,pageIndex,skuId,totalPageNum,countEachPage){
       if(index==1){
              var preIndex = findCurrentIndex()-1;
              showCompanyPageList(skuId,preIndex,totalPageNum,countEachPage);
              return;
        }
        if(index==2){
              showCompanyPageList(skuId,pageIndex,totalPageNum,countEachPage);
              return;
        }
        if(index==3){
             var nextIndex = findCurrentIndex()+1;
             showCompanyPageList(skuId,nextIndex,totalPageNum,countEachPage);
             return;
        }
}
function findCurrentIndex(){
        var index;
        $("#companyRollUl").find("li").each(function(){ 
                 if($(this).attr("class")=="latestnewcurrentpage"){
                        index =  parseInt($(this).html());
                        return;
                 }
       })
       return index;
}
function showCompanyPageList(skuId,pageIndex,totalPageNum,countEachPage){
          initCompanyList(null,skuId,pageIndex,1,countEachPage);
          $("#companyRollUl").find("li").each(function(){   
                 if($(this).attr("class")=="latestnewcurrentpage"){
                          $(this).html("<a href='#desc_othercompany'>"+$(this).html()+"</a>");
                 }
          })
          $("#companyRollUl").find("li").each(function(){                     
                        var aObj = $(this).find("a");
                        if(aObj.html()=='上一页'){
                                if(pageIndex==1){
                                     $(this).hide();
                                }else{
                                     $(this).show();
                                }
                        }else if(aObj.html()=='下一页'){
                                if(pageIndex==totalPageNum){
                                     $(this).hide();
                                }else{
                                     $(this).show();
                                }  
                        }else{
                                if(aObj.html()==(pageIndex)){
                                     aObj.attr("onclick",null);
                                      $(this).attr("class","latestnewcurrentpage");
                               }else{
                                      aObj.attr("onclick","javascript:loadCompanyPage(2,"+(aObj.html())+","+skuId+","+totalPageNum+","+countEachPage+");" );
                                      $(this).attr("class",null);
                              }
                              if(aObj.html()!=null){
                                    displayIndex($(this),aObj.html()-1,pageIndex,totalPageNum);
                               }
                              
                         }
                        
          })
          $("#companyRollUl").find("li").each(function(){   
                 if($(this).attr("class")=="latestnewcurrentpage"){
                          $(this).html($(this).find("a").html());
                 }
          })
}
function displayIndex(obj,index,pageIndex,total){
          if(total<=7){
               return;
          }
          if(pageIndex<5){
               if(index<7){
                      obj.show();
                }else{
                     obj.hide();
               }
          }else if(pageIndex>=total-3){
                if(index>=total-7){
                     obj.show();
               }else{
                      obj.hide();
               }
          }else{
               if(index>=pageIndex-4&&index<=pageIndex+2){
                       obj.show();
               }else{
                       obj.hide();
              }
          }
}
function initMoreBuySelect(item_iid,areaItemId,count,data){
   var html ='<div class="fixed_property">';
   html+='<p class="tit_h3">更多购买选择</p>';
   var showNum = 0;
   for(var i = 0;i<data.length;i++){
         var d = data[i];
         if(d.areaItemId==areaItemId){
              continue;
         }
         if(showNum>=2){
               break;
         }   
         showNum++;      
         html+='<dl class="pro-list-dl">';
         html+='<dt><a href="http://www.111.com.cn/product/'+d.itemId+'.shtml" target="_blank">'+d.nickName+'</a></dt>';
         html+='<dd class="proPrice">';
         html+='<i>&yen;'+d.originalPrice+'</i> ';
         if(d.prescription!=16){
               if(d.saleType==1||d.sellType==8){
                     html+='<span class="baoyou01">包邮</span>';
               }
         }
         html+='</dd>';
         html+='<dd>'; 
         if((d.materialtype  == 'ZSP' || d.materialtype == 'TCP')){
              if(d.prescription!=16){
                     html+='<div class="buyButtonsOtherCompany"  product_no= "'+d.productNo+'" style="display:none;" >';
                     if(d.materialtype == 'TCP'){
                           if(d.sellType ==3){
                                   html+='<a href="javascript:void(0)" onclick="addItemToCart2(3, '+d.itemId+', '+d.itemId+');" class="addpro">我要订购</a>';
                           }else{
                                    html+= '<a href="javascript:void(0)" onclick="addItemToCart2(3, '+d.itemId+', '+d.itemId+');" class="addpro">加入购物车</a>';
                           }
                     }else{
                           if(d.sellType ==3){
                                   html+='<a href="javascript:void(0)" onclick="addItemToCart2(1, '+d.itemId+', '+d.itemId+');" class="addpro">我要订购</a>';
                           }else{
                                    html+= '<a href="javascript:void(0)" onclick="addItemToCart2(1, '+d.itemId+', '+d.itemId+');" class="addpro">加入购物车</a>';
                           }                           
                     }
              }else{
                     html+='<a href="javascript:;" onclick="Detector.floatclick(4)"  class="consult_btn"  sellType="'+d.sellType+'">在线咨询</a>';
             }
         }
         html+='</dd>';
         html+='</dl>';
   }
   html+='<p class="pmore"><a onclick=$("#desc_othercompany").click(); href="#desc_othercompany" id = "totalCompanyNum">全部商家 ('+count+')</a></p>';
   html+='<a href="http://www.111.com.cn/cmsPage/2014061304/zhengshi.html" title="点击查看详细" target="_blank">';
   html+='<div class="erweima">';
   html+='<div class="erwei_img">';
   html+='<img id = "tdcodeimg" alt="" src="';
   html+='http://www.111.com.cn/tdcode/'+parseInt(item_iid/1000)+'/'+item_iid+'.png';
   html+='"/></div>';
   html+='<p>手机端首次下单<span>满百返10元</span></p>';
   html+='</div>';
   html+='</a>' ;  
   html+= '</div>';
 
   var obj = $("#right_company_box");
   obj.append(html);
   obj.show();
}
function doStock2(obj,products,sdata){
	obj.each(function(){
      var thisObj = $(this);
      var pno = thisObj.attr("product_no");
      if(products.indexOf(thisObj.attr("product_no"))>=0){    
				if(sdata[thisObj.attr("product_no")]<=0){
					thisObj.html("<div class='sold_out_style'>商品已售完</div>");
					$("#cartRight_" + pno).attr("href", "javascript:;");
					$("#cartRight_" + pno).addClass('shouwan').text("商品已售完");
				}
				thisObj.show();
			}
	});
}
function getMultiComanyDataJson(skuId,pageIndex,countEachPage){
    var result;
    $.ajax({
        type:"get",
	dataType: "json",
        async: false,
        url: "http://www.111.com.cn/interfaces/item/queryMultiCompanyItemBySkuId.action?skuId="+skuId+"&pageIndex="+pageIndex+"&displayPageSize="+countEachPage,
        success: function(data){
            result = eval(data); 
       }
  })
 return result;
}   
function getMultiCompanyNum(skuId){
   var result = 0;
    $.ajax({
        type:"get",
	dataType: "json",
        async: false,
        url: "http://www.111.com.cn/interfaces/item/queryMultiCompanyCountBySkuId.action?skuId="+skuId,
        success: function(data){
            result = data; 
       }
  })
 return result;
}
function dealCompanyReviewCount(obj){
	var date = new Date().format("yyMMddhh");
	$.ajax({
		url: "/interfaces/review/questionlist/count.action?productId=" + obj.attr("productid")+ "&_=" + date,
		dataType: 'JSON',
		timeout: 5000,
		success: function(d){
			obj.html(d.count);
		}
	});
}
function dealBlock(obj){
 if(obj==null||obj==""||obj.length==0){
       return;
   }
   var products = "";
   obj.each(function(){
        if($(this).css("display")=='none'){
             if(products.indexOf($(this).attr("product_no"))<0){
                 products += $(this).attr("product_no")+',' ;
              }
        }    
   })
   if(products==''){
          return;
  }
  products = products.substring(0,products.length-1);
  var date = new Date().format("yyMMddhh");
  var prvid = $.cookie('provinceId') || '1';
   if (!checkProvinceId(prvid)) {
            prvid = '1'
   }
  var url = "http://www.111.com.cn/gss/interfaces/stock/queryStockToJson.action?province="
				+ prvid + "&productNos=" + products + "&_=" + date + parseInt(new Date().format("mm"))%3;
  $.ajaxSetup({cache: true});
  $.ajax({
		type: "GET",
		url: url,
		dataType:'jsonp',
		jsonp:'callback',
		jsonpCallback:'cbprice',
		complete: function(rep, err){
			// 有的时候请求成功了，也会报错，不会走success.在这再次判断处理一次
			
			if("success" == err) return;
			
			var repText = rep.responseText;
		
			eval(repText);
			function cbprice(stocks){						
				doStock2(obj,products,stocks);
			}
		},
		success: function(sdata){
				doStock2(obj,products,sdata);
		}
	});
}