// ����ӳ�ִ�з���
(function($){
    $.fn.hoverDelay = function(options){
        var defaults = {
            hoverDuring: 100,
            outDuring: 100,
            hoverEvent: function(){
                $.noop();
            },
            outEvent: function(){
                $.noop();
            }
        };
        var sets = $.extend(defaults,options || {});
        var hoverTimer, outTimer, that = this;
        return $(this).each(function(){
            $(this).hover(function(){
                clearTimeout(outTimer);
                hoverTimer = setTimeout(function(){sets.hoverEvent.apply(that)}, sets.hoverDuring);
            },function(){
                clearTimeout(hoverTimer);
                outTimer = setTimeout(function(){sets.outEvent.apply(that)}, sets.outDuring);
            });
        });
    }	
    	
})(jQuery);
	
function prompt(e,tag){
	//������У�鴦����������ַ��������С��0�������ʾ����δ���
	
	var tip = $(e).parent().parent().find('.amount-msg');
	var oldValue = tip.find('span').html();
	var limitCount = $(e).parent().parent().parent().find('.xg').attr('lc');
	var re = /^[0-9]+[0-9]*]*$/;
	var newValue = $(e).attr('value');
	var catalimit = $(e).parent().parent().attr('catal');
	var limitBuy=$(e).parent().parent().attr('limitbuy');
	if (newValue < 0 || isNaN(newValue) || !re.test(newValue) ) {
		alert("�����ʽ����");
		$(e).attr('value',oldValue);
		return;
	} else if (newValue == 0) {
		if(!confirm("ȷ�ϲ��������Ʒ��")){
			$(e).attr('value',oldValue);
			return;
		}
	}
	var itemid = $(e).attr('itemid');
	var itype = $(e).attr('itype');
	var refmainitemid = $(e).attr('refmainitemid');
	if (parseInt(newValue) < parseInt(limitBuy) && parseInt(limitBuy)>0 ) {
		//alert("�����������ܵ���"+limitBuy+"����");
		tip = $('#w7t' + itemid + "_" + itype + "_" + refmainitemid);
		//�����
		tip.empty();
        tip.html("<span style='color: red;'>"+limitBuy+"</span><em></em>����");
		tip.fadeIn("fast");
		window.setTimeout(hiden,3000);
		$(e).attr('value',limitBuy);
		$(e).parent().find('.minus').attr("class","no-minus");
		return;
	}
	else{
		$(e).parent().find('.minus').attr("class","minus");
	}
	if (parseInt(newValue) > parseInt(catalimit) && parseInt(catalimit)>0) {
		alert("�����������ܳ���"+catalimit+"�������ڹ�������ϵ�ͷ�400-007-0958");
		$(e).attr('value',oldValue);
		return;
	}
	//�ӺŰ�ť��ʾ��֤����ͨ��Ʒ��
	if (parseInt(newValue) > parseInt(limitCount)) {
		alert("�����������ܳ���"+limitCount+"��!");
		$(e).parent().find('.plus').attr("class","no-plus");
		$(e).attr('value',limitCount);
		return;
	} else {
		$(e).parent().find('.plus').attr("class","plus");
	}
	//ajax�ύ
	var urlPre = "";
	if (tag) {
		urlPre = "/cart/shoppingcart/update" + tag + "cartitem.action";
	} else {
		urlPre = "/cart/shoppingcart/updateshoppingcartitem.action";
	}
	var url = urlPre + "?ope=change&num=" + newValue + "&cartitemid=" + itemid + "&itype=" + itype + "&refmainitemid=" + refmainitemid + "&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				$(e).attr('value',oldValue);
				coo8alert(returnData.substring(6,returnData.length));
			} else {
				$('#cartbody').html(returnData);
				tip = $('#w7t' + itemid + "_" + itype + "_" + refmainitemid);
				//alert(tip.find('em').html());
				//ajax�ύ�ɹ�����ʾ
//				tip.find('span').html(newValue);
				tip.fadeIn("fast");
				//�����ִ�����ز���
				//setInterval
				window.setTimeout(hiden,3000);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
			}
		}
		//ajax�ύ���ɹ���ô����
	});
	function hiden(){
		tip.fadeOut("slow");
	}
}
function coo8confirm(text){
	return confirm(text);
	//var tag = openNewDiv(text,true);
	//alert('1' + tag);
	//return tag;
}
function coo8alert(text){
	alert(text);
	//var tag = openNewDiv(text,false);
	//alert('2' + tag);
}
function taocandetailshow(id_taocan){
	$('#taocandetailopenbutton' + id_taocan).hide();
	$('#taocandetailclosebutton' + id_taocan).show();
	$('.'+id_taocan).show();
}
function taocandetailhide(id_taocan){
	$('#taocandetailclosebutton' + id_taocan).hide();
	$('#taocandetailopenbutton' + id_taocan).show();
	$('.'+id_taocan).hide();
}
function minusitem_bak(itemid, itype, refmainitemid){
	var count = $('#w7c' + itemid+'_'+itype+'_'+refmainitemid).html();
	if (count == 1) {
		if(!coo8confirm("ȷ�����������Ʒ��")){
			return;
		}
	}
	var url = "/cart/shoppingcart/updateshoppingcartitem.action?ope=minus&cartitemid=" + itemid + "&itype=" + itype + "&refmainitemid=" + refmainitemid + "&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				coo8alert(returnData.substring(6,returnData.length));
			} else {
				$('#cartbody').html(returnData);
			}
		}
	});
}

$(function(){
	ajaxGetFeeCart();
	ajaxGetHuan();
	bfd();
})


function initShowGroupPage() {
   var b = parseInt($("#showGroupNmu").val());
   var c = $("#groupTabs a").length;
   if (c <= (b + 1)) {
       $("#groupPrev").addClass("left_default");
       $("#groupNext").addClass("right_default")
   }
   var d = getCurrGroupPageNum();
   $("#groupTabs").data("currGroupPageNum", d);
   var a = $("#redemptionPromotionList");
   a.data("currBuzyType", parseInt($("#currBuzyType").val()));
   a.data("currGroupId", parseInt($("#currGroupId").val()));
   a.data("startIndexNum", parseInt($("#startIndexNum").val()));
   showGroupPage()
}

function showGroupPage() {
	var e = $("#groupTabs").data("currGroupPageNum");
	var a = parseInt($("#showGroupNmu").val());
	var c = $("#groupTabs a").length;
	if (c < (a + 1)) {
		$("#groupTabs").css("width", "auto")
	}
	var d = a * e;
	var b = d + a;
	if (b > c) {
		b = c
	}
	if (e == 0) {
		$("#groupPrev").addClass("left_default");
		if ((a + 1) >= c) {
			$("#groupNext").addClass("right_default")
		} else {
			$("#groupNext").removeClass("right_default")
		}
	} else {
		if (b == c) {
			$("#groupPrev").removeClass("left_default");
			$("#groupNext").addClass("right_default")
		}
		if(b < c){
			$("#groupPrev").removeClass("left_default");
		}
	}
	if (c < 2) {
		$("#redemGroupsMenu").hide();
		return
	}
	$("#groupTabs a").each(function(f) {
		if (f == 0) {
			$(this).show()
		} else {
			if (f > d && f <= b) {
				$(this).show()
			} else {
				$(this).hide()
			}
		}
	})
}
function onGroupClick(b) {
	var a = parseInt($("#showGroupNmu").val());
	var c = $("#groupTabs a").length;
	if (c <= (a + 1)) {
		$("#groupPrev").addClass("left_default");
		$("#groupNext").addClass("right_default");
		return
	}
	if ($("#groupPrev").hasClass("left_default") && b == -1) {
		return
	}
	if ($("#groupNext").hasClass("right_default") && b == 1) {
		return
	}
	var d = $("#groupTabs").data("currGroupPageNum") + b;
   if (d < 0) {
       return
   }
   $("#groupTabs").data("currGroupPageNum", d);
	showGroupPage()
}
function getCurrGroupPageNum() {
	var a = parseInt($("#showGroupNmu").val());
	var c = 0;
	var b = 0;
	$("#groupTabs a").each(function(d) {
		if ($(this).hasClass("current")) {
			b = d
		}
	});
	c = Math.ceil((b / a)) - 1;
	if (c < 0) {
		c = 0
	}
	return c
}

function ajaxGetHuan(){
	
	var count=getItemCount();
	
	if(count== undefined || count<=0){
		$("#redemptionPromotionList").hide();
		return;
	}
	
	var url = "/cart/shoppingcart/getCartRedemption.action?A=" + Math.random();
	
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				$("#redemptionPromotionList").hide();
			} else {
				$("#redemptionPromotionList").html(returnData);
                $("#redemptionPromotionList").show();
				initRedemptionHoverEvent();
				initShowGroupPage();
			}
		}
	});
}

function ajaxGetFeeCart(){
	var fei = $("#cartThefei").val();
	if(parseFloat(fei)<=0){
		$("#mianyoucart").hide();
		return;
	} 
	var url = "/cart/shoppingcart/queryFeeCart.action?cartTheFei="+fei+"&A=" + Math.random();
	
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				$("#mianyoucart").hide();
                $("#coudanDiv").hide();
			} else {
				$("#mianyoucart").html(returnData);
                $("#mianyoucart").show();
				initTabsHoverEvent();
                $("#coudanDiv").show();
			}
		}
	});
}

function bfd(){
	var items = "";
	var catalogNames = "";
	var userId = $.cookie("JUD");
	if(userId ==""||userId == null){
		userId = 0;
	}
	var catalogarray = new Array();
	var itemArrays = new Array();
	var bfds = $(".bfd");
	if(bfds.length>=0){
		$(bfds).each(function(){
			//<input type="hidden" class=".bfd" itemid="${itemId}" price="${itemoriginalprice}" itemCount="${itemcount}" catalogId="${catalogId}" catalogName="${catalogName}" >
			var itemid = $(this).attr("itemid");
			var price = $(this).attr("price");
			var itemCount = $(this).attr("itemCount");
			var catalogName = $(this).attr("catalogName");
			var itemarray = new Array();
			itemarray.push(itemid);
			itemarray.push(price);
			itemarray.push(itemCount);
			itemArrays.push(itemarray);
			catalogNames = "[\""+catalogName+"\"]";
			catalogarray.push(catalogNames);
		});
	}
	window["_BFD"] = window["_BFD"] || {};
 	_BFD.BFD_INFO = {
          "cart_items" : itemArrays,   //2ά���飬�����ֱ���["��Ʒid��","����Ʒ�ĵ���","���ﳵ�и���Ʒ������"];��Ʒid����͵�Ʒҳ�ṩ��ID��һ��
          "category" : catalogarray,   //���ﳵ�ڵ���Ʒ����С���
          "user_id" : userId, //��վ��ǰ�û�id�����δ��¼��Ϊ0����ַ���
          "page_type" : "shopcart" //��ǰҳ��ȫ�ƣ������޸�
    };
}


function ajaxUpdateRedemption(catId){
	
	var currIndex=$("#startIndexNum").val();
	var promotionId=$("#currBuzyType").val();
	var url = "/cart/shoppingcart/getRedeByCatIdAndIndex.action?promotionId="+promotionId+"&currCat="+catId+"&currIndex="+currIndex+"&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				
			} else {
				$("#redemptionItemsInfo").html(returnData);
				initRedemptionHoverEvent();
				$("#currGroupId").val(catId);
				initCatClickEvent(catId);
			}
		}
	});
}


function initTabsHoverEvent() {
	jQuery(".ap-prolist ").find("li").hover(function() {
	jQuery(".ap-prolist ").find("li").removeClass("graybg");
	jQuery(this).addClass("graybg")
	},
	function() {
	jQuery(this).removeClass("graybg")
	})
} 

function onRedemPrevClick(promotionId,catId,indexId){
	
	var url = "/cart/shoppingcart/getRedeByCatIdAndIndex.action?promotionId="+promotionId+"&currCat="+catId+"&currIndex="+indexId+"&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				
			} else {
				$("#redemptionItemsInfo").html(returnData);
				initRedemptionHoverEvent();
				$("#currGroupId").val(catId);
				$(".content").hide();	
				$("p.tabs_tit a:first").addClass("current").show(); 
				$(".content:first").show(); 
				initCatClickEvent(catId);
			}
		}
	});
}

function initCatClickEvent(catId){
	$("#groupTabs").find("a").removeClass("current");
	$("#"+catId).addClass("current");
}

function initRedemptionHoverEvent() {
	$(".content").hide();	
	$("p.tabs_tit a:last").addClass("current").show(); 
	$(".content:last").show(); 
	$("p.tabs_tit a").mouseenter(function() {
		$("p.tabs_tit a").removeClass("current"); //Remove any "active" class
		$(this).addClass("current"); //Add "active" class to selected tab
		$(".content").hide(); //Hide all tab content
		var activeA = $(this).index(); //Find the rel attribute value to identify the active tab + content
		
		$(".content").eq(activeA).show(); //Fade in the active content
	});
	
	$(".redemListUl").find("li").hover(function() {
		$(".info .btn_buy", this).show();
	},
	function() {
		$(".info .btn_buy", this).hide();
	});
}


//function ajaxChooseGift(promotionId){
//
//	if(promotionId==undefined ||promotionId == null ||promotionId=='' || promotionId == 0)return;
//	var url = "/cart/shoppingcart/getitemgift.action?promotionId=" + promotionId + "&A=" + Math.random();
//	$.get(url, {}, function(returnData) {
//		if (returnData) {
//			var reg=/^error_/;
//			if (returnData.match(reg) == "error_") {
//				coo8alert(returnData.substring(6,returnData.length));
//			} else {
//				$("#yhd_pop_win").html(returnData);
//				addmaskbox();
//				var objWH=getObjWh("yhd_pop_win");
//				var tbT=objWH.split("|")[0]+"px";
//				//var tbL=objWH.split("|")[1]+"px";
//				$("#yhd_pop_win").css({top:tbT,display:"block"});
//				$(window).scroll(function(){resetBg("yhd_pop_win")});
//				$(window).resize(function(){resetBg("yhd_pop_win")});
//				//$("#yhd_pop_win").show();
//			}
//		}
//	});
//}

function chooseCartGift(itemid,promotionid){
	var totalCount=$("#giftALlLimit").val();
	
	var cartGiftCount=$("#giftcount").val();
	
	var itemCount=$("#giftItemCount"+itemid).html();
	var quantity=$("#gift_"+itemid).attr("ic");
	
	var tCount=parseInt(itemCount)+parseInt(1);
	
	if(parseInt(quantity) < parseInt(tCount)){
		alert('��治�㣬���ѡȡ'+quantity+"����");
		return;
	}
	
	if(parseInt(cartGiftCount) < parseInt(totalCount)){
		addToCart(6,itemid,1,promotionid);
		cartGiftCount++;
		$("#giftcount").val(cartGiftCount);
	}
		
	if(parseInt(cartGiftCount) == parseInt(totalCount)){
		$("#yhd_pop_win").hide();
		closeGiftDiv();
	}
	
}

function closeGiftDiv(){
	removemaskbox();
	var url = "/cart/shoppingcart/flashshoppingcart.action?A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				coo8alert(returnData.substring(6,returnData.length));
			} else {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
			}
		}
	});
	
}

function addFeeCartItem(itemid){
	var url = "/cart/shoppingcart/addfeeitem2shoppingcart.action?itype=1&itemnum=1&itemid="+itemid+"&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				coo8alert(returnData.substring(6,returnData.length));
			} else {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
                window.location= "http://buy.111.com.cn/cart/shoppingcart/queryshoppingcart.action"
			}
		}
	});	
}

function addToCart(iy, ri, ic, ii){
	var cart = readCart();
	var item = {'iy':parseInt(iy),'ri':parseInt(ri),'ic':parseInt(ic),'ii':parseInt(ri),'pr':0,'ps':parseInt(ii),'pc':0,'ss':0,'sh':0};
	var itemCount=$("#giftItemCount"+ri).html();
	$("#giftItemCount"+ri).html(parseInt(itemCount)+parseInt(ic));
	if(cart){
		for(var i in cart){
			if(cart[i].ii == item.ii && cart[i].iy == item.iy){
				cart[i].ic = parseInt(cart[i].ic) + parseInt(item.ic);
				writeCart(cart);
				return;
			}
		}
		cart[cart.length] = item;
		writeCart(cart);
	}else{
		writeCart([item]);
	}
	
}
function writeCart(cart){
	$.cookie('cartItem'
		, JSONstringify(cart).replace(/\"/gm, '$')
		, {path: '/', domain: '.111.com.cn', expires: 30});
}


function JSONstringify(Json){
	if($.browser.msie){
		if($.browser.version=="7.0"||$.browser.version=="6.0"){
		 var result = jQuery.parseJSON(Json);
		}else{
		 	var result=JSON.stringify(Json);  
		}       
		}else{
			var result=JSON.stringify(Json);           
		}
	return result;
}


function readCart(){
	var c = $.cookie('cartItem');
	if(c){
		c = c.replace('"','').replace('"','');
		return eval(c.replace(/\$/gm, '"'));
	}
	return null;
}

function minusitem(e,tag){
	var inputObject = $(e).parent().find('.text-amount');
	var oldValue = inputObject.val();
	inputObject.val(parseInt(oldValue) - 1);
	prompt(inputObject,tag);
}

function checkItemStatus(e){
	var checkStatus=$(e).attr("checked");
	var itemId=$(e).attr("value");
	var itype=$(e).attr("itype");
	var status=0;
	if(checkStatus!=true){
		status=-1;
	}else {
		status=0;
	}
	//alert(status);return;
	var url = "/cart/shoppingcart/updateshoppingstatus.action?cartitemid=" + itemId + "&status=" + status +"&itype="+itype+ "&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				coo8alert(returnData.substring(6,returnData.length));
			} else {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
			}
		}
	});
	//edit by Dancy 20140306
	//������Ʒ�ĵ�һ���Żݻ����ʽ
	//$(e).parents(".item-body").find(".itme_huodong:first").addClass("bt_org").find(".hd_bgicon").show();
}

function checkAll(e){
	var checkStatus=e.checked;
	var itemId=0;
	if(checkStatus!=true){
		itemId = 0;
	}else {
		itemId = 1;
	}
	var url = "/cart/shoppingcart/updateshoppingstatus.action?cartitemid=" + itemId +"&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				coo8alert(returnData.substring(6,returnData.length));
			} else {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);

			}
		}
	});	
	//edit by Dancy 20140306
	//������Ʒ�ĵ�һ���Żݻ����ʽ
	//$(e).parents(".cart-main").find(".item-body .itme_huodong:first").addClass("bt_org").find(".hd_bgicon").show();
}

function checkVenderAll(e,venderId){
    if(venderId == undefined || venderId == '' || venderId == null){
        alert("ȫѡ���̣���������");return;
    }
    var checkStatus=e.checked;
    var itemId=0;
    if(checkStatus!=true){
        itemId = 0;
    }else {
        itemId = 1;
    }
    var url = "/cart/shoppingcart/updateshoppingstatus.action?packageVenderId=" + venderId + "&status=" + itemId +"&A=" + Math.random();
    $.get(url, {}, function(returnData) {
        if (returnData) {
            var reg=/^error_/;
            if (returnData.match(reg) == "error_") {
                coo8alert(returnData.substring(6,returnData.length));
            } else {
                $('#cartbody').html(returnData);
                ajaxGetFeeCart();
                ajaxGetHuan();
                bfd();
                var divobj = $("#bfd_screc");
                _BFD.shopCart(divobj);
            }
        }
    });
    //edit by Dancy 20140306
    //������Ʒ�ĵ�һ���Żݻ����ʽ
    //$(e).parents(".cart-main").find(".item-body .itme_huodong:first").addClass("bt_org").find(".hd_bgicon").show();
}

function additem(e,tag){
	var inputObject = $(e).parent().find('.text-amount');
	var oldValue = inputObject.val();
	inputObject.val(parseInt(oldValue) + 1);
	prompt(inputObject,tag);
}
function additem_bak(itemid, itype, refmainitemid){
	var url = "/cart/shoppingcard/updateshoppingcartitem.action?ope=add&cartitemid=" + itemid + "&itype=" + itype + "&refmainitemid=" + refmainitemid + "&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			$('#cartbody').html(returnData);
			//$('#cartcount').html();
		}
	});
}

function deleteCheckedItem(){
	
	if(coo8confirm("ȷ��������ѡ����Ʒ��")){
		var itemids='';
		$($("input[name='cart2Checkbox']")).each(
			function(){
				if(this.checked==true){
					var itemid=this.value;
					itemids+=this.value+":"+$("#cart_itype_"+itemid).val()+":"+$("#cart_ref_"+itemid).val()+",";
				}
			}
		);
		var url = "/cart/shoppingcart/deletecheckcartitem.action?itemids=" +itemids+ "&A=" + Math.random();
		$.get(url, {}, function(returnData) {
			if (returnData) {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
				//$('#cartcount').html();
			}
		});
	}
}

function deletecellitem(itemid,itype,refmainitemid,cellitemid){
	
	//alert(itemid+":"+itype+":"+refmainitemid+":"+cellitemid);

	if(coo8confirm("ȷ�����������Ʒ��")){
		var url = "/cart/shoppingcart/deleteshoppingcartcellitem.action?cartitemid=" + itemid + "&itype=" + itype + "&refmainitemid=" + refmainitemid + "&cellitemid=" + cellitemid + "&A=" + Math.random();
		$.get(url, {}, function(returnData) {
			if (returnData) {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
				//$('#cartcount').html();
			}
		});
	}
}
function deleteitem(itemid,itype,refmainitemid){
	if(coo8confirm("ȷ�ϲ�����ѡ����Ʒ��")){
		var url = "/cart/shoppingcart/deleteshoppingcartitem.action?cartitemid=" + itemid + "&itype=" + itype + "&refmainitemid=" + refmainitemid + "&A=" + Math.random();
		$.get(url, {}, function(returnData) {
			if (returnData) {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
				//$('#cartcount').html();
			}
		});
	}
}
function refresh(){
	var url = "/cart/shoppingcart/refreshshoppingcart.action?" + "&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			$('#cartbody').html(returnData);
		}
	});
}
function deleteall(){	
	if(coo8confirm("ȷ��Ҫ��չ��ﳵ?")){
		var url = "/cart/shoppingcart/deleteallshoppingcartitem.action?" + "A=" + Math.random();
		$.get(url, {}, function(returnData) {
			if (returnData) {
				$('#cartbody').html(returnData);
				ajaxGetFeeCart();
				ajaxGetHuan();
				bfd();
				var divobj = $("#bfd_screc");
				_BFD.shopCart(divobj);
			}
		});
	}
}
function addYanbao(codeidid, itemid){
	var url = '/cart/shoppingcart/additemyanbao2shoppingcart.action?itype=2&itemnum=1&itemid=' + codeidid + '&refmainitemid=' + itemid + "&A=" + Math.random();;
	//$.getJSON(url + "&callback=?", writesmallcart);
	$.get(url,function(data){
		var reg=/^ok_/;
		if (data.match(reg) == "ok_") {
			//ˢ��ҳ��
			var urll = "/cart/shoppingcart/queryshoppingcart.action" + "?A=" + Math.random();;
			window.location.href=urll;
		} else {
			//����������ʾ
			coo8alert(data.substring(6,data.length));
		}
	});
}

function checkCartNum()
{
	var re = /^[1-9]+[0-9]*]*$/;
	var numArray=$(".numTxt").val();
	$(numArray).each(function(){
		if(!re.test(this))
		{
			alert("�������Ʒ����������Ʒ����ֻ��Ϊ��������");
			return false;
		}
	});
	return true;
}

/**
* ȥ���㣬������ǰ�ļ�飬������¼
* @author JiangChi
* @since 2011-11-7
* @param event
* @param cartDomain
* @param appDomain
* @param cartItemCount
* @param type ��ͨ(0) or ����(1) or �Ź�(2)
*/
function cartnullalert(event,cartDomain,appDomain,cartItemCount,type){
	if (cartItemCount > 0) {
		if(!checkCartNum())
		{
			return;
		}
		var result = hasPersentNotLingqu();
		if(result > 0){
			alert("������Ʒ��δ��ȡ�ɹ�������\"��ȡ\"��ť��");
			var href = location.href;
			var index = href.indexOf("#");
			if(index>0){
				href = href.substring(0, index);
			}
			location.href = href +"#lingqu_"+result;
			return;
		}

        result = hasErrorRush();

        if(result>0){
            alert("���Ĺ��ﲻ���ϴ�����������޸Ļ�ɾ����");
            return;
        }
		var cartHasPrescription = $('#cartHasPrescription').val();
		if (cartHasPrescription == 1) {
			var userInfo = getCookieCode('UserInfo');
			if(userInfo!=undefined&&userInfo!=''){
				userInfo = userInfo.replace("\"","").replace("\"","");
				var infos = userInfo.split('&');
				//alert(infos);
				var userId = '';
				for (var i = 0; i < infos.length; i++) {
					if (infos[i].indexOf('UserId=') >= 0) {
						userId = infos[i].substring('UserId='.length);
					}
				}
				if(userId!=''){
					var moniPharmacistId = '';
					for (var i = 0; i < infos.length; i++) {
						if (infos[i].indexOf('moniPharmacistId=') >= 0) {
							moniPharmacistId = infos[i].substring('moniPharmacistId='.length);
						}
					}
					if (moniPharmacistId == '') {
						//alert('������Ʒ�а�������ҩ������ϵҩʦ��');
						$("#kfDialogDiv").show();
						return;
					}
				}	
			}	
		}

		var backurl;
		backurl = 'http://buy.111.com.cn/cart/shoppingcart/queryshoppingcart.action';
		//��תҳ��
		loginCheck(event, backurl, 0, null);
	} else {
		coo8alert('���Ĺ��ﳵ��û����Ʒ��ϵͳ���Զ�ת���Է���������������Ʒ��');
		window.location.href=appDomain;
	}
}
function hasPersentNotLingqu(){
	var result = 0;
	$($("a[name='lingqu']")).each(
			function(){
				if($(this).css("display").toLowerCase()!="none"){
					result = $(this).attr("promotionid");
					return;
				}
			}
	);
	return result;
}
function hasErrorRush(){


    return $("div[name='rushError']").size();
}

/**
* ��������̻�ID
* @author JiangChi
* @since 2011-11-7
* @returns �����̻���ID
*/
function getSH (cartStr) {
	var cartString = "cartItem";
	if (cartStr) {
		cartString = cartStr;
	}
	//��cookie����Ĺ��ﳵcartItem
	var cartItemString = $.cookie(cartString);
	var cartItemString1 = replaceall(cartItemString,"\"", "");
	var cartItemString2 = replaceall(cartItemString1,"$", "\"");
	var cartItems = eval(cartItemString2);
	var tag = null ;
	var shh = "";
	$.each(cartItems,function(index,domEle){
		if (shh) {
			if (shh != domEle.sh) {
				tag = false;
			}
		} else {
			shh = domEle.sh;
			tag = domEle.sh;
		}
	});
	return tag;
}
function replaceall(str, str1, str2){
	var index = str.indexOf(str1);
	if (index <= -1) {
		return str;
	} else {
		var a = str.slice(0, index);
		var b = str.slice(index + str1.length);
		var returnStr = a + str2 + b;
		return replaceall(returnStr, str1, str2);
	}
}
var docEle1 = function () {
   return document.getElementById(arguments[0]) || false;
};
function openNewDiv(content,confirm) {
   var m = "mask";
   var _id='popup';
   if (docEle1(_id)) document.body.removeChild(docEle1(_id));
   if (docEle1(m)) document.body.removeChild(docEle1(m));
   //mask���ֲ�
   var newMask = document.createElement("div");
   newMask.id = m;
   newMask.style.position = "absolute";
   newMask.style.zIndex = "9900";
   _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
   _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
   newMask.style.width = _scrollWidth + "px";
   newMask.style.height = _scrollHeight + "px";
   newMask.style.top = "0px";
   newMask.style.left = "0px";
   newMask.style.background = "#33393C";
   newMask.style.filter = "alpha(opacity=40)";
   newMask.style.opacity = "0.40";
   document.body.appendChild(newMask);
   //�µ�����
   var newDiv = document.createElement("div");
   newDiv.id = _id;
   newDiv.style.position = "absolute";
   newDiv.style.zIndex = "9999";
   newDivWidth = 485;
   newDivHeight = 150;
   newDiv.className='DialogueBG';
   newDiv.style.top = (document.body.scrollTop + document.body.clientHeight/2 - newDivHeight/2) + "px";
   newDiv.style.left = (document.body.scrollLeft + document.body.clientWidth/2 - newDivWidth/2) + "px";
   var str = "";
   if (confirm) {
   	str = '<p style="text-align:center;padding:1em 0 0;margin:0;"><input value="ȷ��" type="button" id="okpopup" style="margin: 0 10px;width: 80px;" /><input value="ȡ��" type="button" id="cancelpopup"  style="margin: 0 10px;width:80px;" /></p>';
   } else {
   	str = '<p style="text-align:center;padding:1em 0 0;margin:0;"><input value="ȷ��" type="button" id="cancelpopup" style="margin: 0 10px;width: 80px;" />';
   }
   newDiv.innerHTML ='<div class="DialogueBox"><div class="top"><span>��ܰ��ʾ</span><span class="Close"><img src="http://p4.51mdq.com/images/DialogueBoxClose.jpg" id="closepopup"></span></div>'
   	+'<div class="ConBox"><p style="text-align:center;padding:1em 0;margin:0;">'+content+'</p>'
   	+ str
   	+'</div></div>';
   document.body.appendChild(newDiv);
	newDiv.style.left=($(window).width()-452)/2+$(window).scrollLeft()+ "px";
	newDiv.style.top=($(window).height()-200)/2+$(window).scrollTop()+ "px";
   //�������������
   function newDivCenter() {
   	newDiv.style.left=($(window).width()-452)/2+$(window).scrollLeft()+ "px";
	     newDiv.style.top=($(window).height()-200)/2+$(window).scrollTop()+ "px";
	     //alert("newDiv.style.left======"+newDiv.style.left);
	     //alert("newDiv.style.top======="+newDiv.style.top);
   }
   if(document.all) {
       window.attachEvent("onscroll",newDivCenter);
   } else {
       window.addEventListener('scroll',newDivCenter,false);
   }
   //�ر���ͼ���mask���ֲ�
   function closeLayer () {
       if(document.all){
           window.detachEvent("onscroll",newDivCenter);
       } else {
           window.removeEventListener('scroll',newDivCenter,false);
       }
       document.body.removeChild(docEle1(_id));
       document.body.removeChild(docEle1(m));
       return false;
   }
   function okLayer () {
       if(document.all){
           window.detachEvent("onscroll",newDivCenter);
       } else {
           window.removeEventListener('scroll',newDivCenter,false);
       }
       document.body.removeChild(docEle1(_id));
       document.body.removeChild(docEle1(m));
       //ִ�в���
       return true;
   }
   document.getElementById("closepopup").onclick = closeLayer;
   document.getElementById("cancelpopup").onclick = closeLayer;
   document.getElementById("okpopup").onclick = okLayer;
}


/**
* ҹ�ܻݻ��ʱ����ÿ����Ʒ���޹�
* @author JiangChi
* @since 2011-11-7
* @returns {Boolean}
*/
function checkcart(){
	if($('.myclassforcartitem').length == 0)
		return true;

	//����״̬
	var result = false;
	//����һ�����飬�����������������Ʒ
	var faileProducts = new Array();
	//��ŷ����޹�����
	var faileNums = new Array();
	//�õ����й��ﳵ����Ʒ������ID��������������ô�ã���
	var cartProducts = new Array();
	$('.myclassforcartitem').each(function(index, domEle){
		var tmp = {"pid":$(domEle).attr("pid"),"pnum":$(domEle).attr("pnum"),"limitcount":$(domEle).find('.tipBx').attr('lc'),"name":$(domEle).find('.name a').html(),"catalimit":$(domEle).find('.count').attr('catal')};
		cartProducts.push(tmp);
	});
	
	//ajax�õ����Ʒ�б�
	var activeProducts = new Array();
	var len = yezonghuiids.split(",");
	for(var i=0;i<len.length;i++){
		activeProducts.push(len[i]);
	}
	var cartProducts1 = new Array();
	$.each(cartProducts, function(i, domEle){//�������ﳵ��ÿ����ƷID������
		var b = containsProduct1(cartProducts1,domEle.pid);
		if (b != -1) {
			domEle.pnum = parseInt(domEle.pnum)+parseInt(cartProducts1[b].pnum);
			cartProducts1[b] = domEle;
		} else {
			cartProducts1.push(domEle);
		}
	});
	var limitTag = true;//�޹���ʶ
	var activeTag = true;//���ʶ
	var numTag = true;//����С�����ʶ
	$.each(cartProducts1, function(i, domEle){//�������ﳵ��ÿ����ƷID������
		if (faileProducts.length == 0){
			var b = containsProduct(activeProducts,domEle.pid);
			if (parseInt(domEle.pnum) > parseInt(domEle.catalimit)) {
				numTag = false;
				faileProducts.push(domEle.name);
				faileNums.push(domEle.catalimit+"̨");
			}else if (domEle.pnum > parseInt(domEle.limitcount)) {
				limitTag = false;
				faileProducts.push(domEle.name);
			}else if (b && domEle.pnum > 1) {//�жϻ��Ʒ����û�У�����У�������������1���ӵ���һ����������û�м���
				activeTag = false;
				faileProducts.push(domEle.name);
			}
		}
	});

	//�ж�������û������
	if (faileProducts.length == 0) {
		result = true;
	}else{
		if(!numTag){
			alert("��ܰ��ʾ��" + faileProducts + "������������Ʒ�����޹�����"+faileNums+"�޷����㣬������ڹ�������ϵ�ͷ�4008880909");
		}else if(!limitTag){
			alert("��ܰ��ʾ��" + faileProducts + "���������ܳ����޹�������");
		}else if(!activeTag){
			alert("��ܰ��ʾ: " + faileProducts + "Ϊ���Ʒ��һ��ֻ����һ��Ŷ");
		}
	}
	return result;
}
function containsProduct(list,id){
	var result = false;
	$.each(list, function(index, domEle){
		if (domEle == id) {
			result = true;
		}
	});
	return result;
}
function containsProduct1(list,id){
	var result = -1;
	$.each(list, function(index, domEle){
		if (domEle.pid == id) {
			result = index;
		}
	});
	return result;
}

//��ʾ��ɫJS���ֲ�
function showBg(ct,content){
    var bH=$("body").height();
    var bW=$("body").width()+16;
    var objWH=getObjWh(ct);
    $("#maskBoxPop").css({width:bW,height:bH,display:"block"});
    var tbT=objWH.split("|")[0]+"px";
    var tbL=objWH.split("|")[1]+"px";
    $("#"+ct).css({top:tbT,left:tbL,display:"block"});
    $("#"+content).html("<div style='text-align:center'>���ڼ��أ����Ժ�...</div>");
    $(window).scroll(function(){resetBg()});
    $(window).resize(function(){resetBg()});
}
function getObjWh(obj){document.documentElement.scrollTop+document.body.scrollTop
    var st=document.documentElement.scrollTop+document.body.scrollTop;//�������ඥ���ľ���
    var sl=document.documentElement.scrollLeft+document.body.scrollLeft;//����������ߵľ���
    var ch=document.documentElement.clientHeight;//��Ļ�ĸ߶�
    var cw=document.documentElement.clientWidth;//��Ļ�Ŀ��
    var objH=$("#"+obj).height();//��������ĸ߶�
    var objW=$("#"+obj).width();//��������Ŀ��
    var objT=Number(st)+(Number(ch)-Number(objH))/2;
    var objL=Number(sl)+(Number(cw)-Number(objW))/2;
    return objT+"|"+objL;
}
function resetBg(){

    var maskBoxPop=$("#maskBoxPop").css("display");

    if(maskBoxPop=="block"){
        var bH2=$("body").height();
        var bW2=$("body").width()+16;
        $("#maskBoxPop").css({width:bW2,height:bH2});
        var objV=getObjWh("dialog");
//alert(objV);
        var tbT=objV.split("|")[0]+"px";
        var tbL=objV.split("|")[1]+"px";
        $("#dialog").css({top:tbT,left:tbL});
    }
}
//�رջ�ɫJS���ֲ�Ͳ�������
function closeBg(){
    $("#maskBoxPop").css("display","none");
    $("#dialog").css("display","none");
}
function showTabs(e,name){
	$(e).parent().find("li").each(function(){
		$(this).removeClass("on");
	});
	$(e).attr("class","on");
	$("#"+name).parent().find("ul[name='list']").each(function(){
		$(this).hide();
	});
	$("#"+name).show();
}


//ѡ��л�
function ntabs(e,index) {
    $(e).parent().find("li").each(function(){
    	$(this).removeClass("on");
    });
    $(e).attr("class","on");
    $("#recommedList_"+index).parent().find("ul[name='recommedList']").each(function(){
    	$(this).hide();
    });
    $("#recommedList_"+index).show();
}
function selectProvince(num,name){
	setAddressCity(num);
	$("#selectedProvince").html(name);
}
function checkPresent(promotionId,status){
	var itemids = "";
	$($("input[name='promotion_"+promotionId+"']")).each(
			function(){
				if(this.checked==true){
					var itemid=this.value;
					itemids+=this.value+",";
				}
			}
		);
	if(itemids == ""){
		alert("��ѡ��Ҫ��ȡ����Ʒ");
		return;
	}
	var url = "/cart/shoppingcart/updateshoppingPresentstatus.action?itemids=" +itemids+"&status="+status+"&promotionId="+promotionId+"&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			$('#cartbody').html(returnData);
			ajaxGetFeeCart();
			ajaxGetHuan();
			bfd();
			var divobj = $("#bfd_screc");
			_BFD.shopCart(divobj);
		}
	});
	
}

function checkPersentItem(promotionId,giftCount,itemId){
	var itemids = "";
	var count = 0;
	$($("input[name='promotion_"+promotionId+"']")).each(
		function(){
			if(this.checked==true){
				if(!isNaN( $(this).attr("count"))){
					count = count + parseInt($(this).attr("count"));
				}
			}
		}
	);
	if(count >= giftCount){
		$($("input[name='promotion_"+promotionId+"']")).each(
				function(){
					if(this.checked==false){
						$(this).attr('disabled',true);
					}
				}
			);
	}else{
		$($("input[name='promotion_"+promotionId+"']")).each(
				function(){
					if(parseInt($(this).attr("storeCount"))>0)
						$(this).attr('disabled',false);
					else
						$(this).attr('disabled',true);
				}
			);
	}
}

function modifyPersent(promotionId,e){
	$(e).hide();
	$("#promotion_choice_"+promotionId).show();
	$("#button_"+promotionId).show();
	$("#promotion_choiced_"+promotionId).hide();
	$("#delete_promotion_"+promotionId).hide();
}
function deletePersent(promotionId,status){
	var itemids = "";
	$($("input[name='promotion_"+promotionId+"']")).each(
			function(){
				itemids+=this.value+",";
			}
		);
	var url = "/cart/shoppingcart/updateshoppingPresentstatus.action?itemids=" +itemids+"&status="+status+"&promotionId="+promotionId+"&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			$('#cartbody').html(returnData);
			ajaxGetFeeCart();
			ajaxGetHuan();
			bfd();
			var divobj = $("#bfd_screc");
			_BFD.shopCart(divobj);
		}
	});
}
function addFavoriteCart(itemId){
	if(!createUser()){
		popLogin("http://buy.111.com.cn/cart/shoppingcart/queryshoppingcart.action");
	}else{
		addFavorite(itemId);
	}
}
function gotomianyoucart(){
	$("#mianyoucart").focus();
}

