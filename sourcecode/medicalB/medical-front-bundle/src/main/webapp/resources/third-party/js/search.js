
	function searchBrandFilter(baseUrl, brandid) {

		var a = $("#filterbrandList");
		var path = "&brandFilter=";
		var url = "";
		//url=bassUrl+"&brandFilter=";
		var brandIds = "";
		a.find(".cur").each(function() {
			var earchId = $(this).attr("brandId");
			if (earchId != brandid) {
				brandIds = brandIds + "," + earchId;
			}
		});
		if (brandIds != "") {
			var l = brandIds.substr(1);
			url = baseUrl + path + l;
		} else {
			url = baseUrl;
		}
		var sendUrl=encodeURI(encodeURI(url));
		window.location.href = sendUrl;
	}
	function searchMutiFilter(baseUrl, excludeId, operation) {
		var b = $("#group_attr");
		var path = "&attrFilter=";
		var url = "";
		var brandIds = "";
		var g = $(this);
		b.find(".cur").each(function() {
			var earchId = $(this).attr("attrid");
			if (operation = "exclude") {
				if (earchId != undefined) {
					if (earchId != excludeId) {
						brandIds = brandIds + "," + earchId;
					}
				}
			} else {
				if (earchId != undefined) {
					brandIds = brandIds + "," + earchId;
				}
			}
		});

		if (brandIds != "") {
			var l = brandIds.substr(1);
			url = baseUrl + path + l;
		} else {
			url = baseUrl;
		}
		var sendUrl=encodeURI(encodeURI(url));
		window.location.href = sendUrl;
	}

    function searchlocationPageUrl(url,totalpage)
    {
        var pages=$('#jumpto').val();
        if (!isDigit(pages)) {
            alert("输入页码非数字字符,请重新输入");
            return;
        }
        if (pages == 0) {
            alert("输入页码不能为0,请重新输入");
            return;
        }
        if (eval(pages) > eval(totalpage)) {
            alert("输入页码不能大于最大页码数,请重新输入");
            return;
        }
        if(pages!="") {
            var pageUrl=url + "&gotoPage=" + pages;
            pageUrl = encodeURI(encodeURI(pageUrl));
            location.href =pageUrl;
        }
    }
    function listLocationPageUrl(url,totalpage)
    {
        var pages=$('#jumpto').val();
        if (!isDigit(pages)) {
            alert("输入页码非数字字符,请重新输入");
            return;
        }
        if (pages == 0) {
            alert("输入页码不能为0,请重新输入");
            return;
        }
        if (eval(pages) > eval(totalpage)) {
            alert("输入页码不能大于最大页码数,请重新输入");
            return;
        }
        if(pages!="")
        location.href=url+"-"+pages+".html";
    }

	function searchEnterJumpto(e,url,totalpage)
	{
		var e = e || window.event;
		if(e.keyCode == 13){
			searchlocationPageUrl(url,totalpage);
		}

	}
	function listEnterJumpto(e,url,totalpage)
	{
		var e = e || window.event;
		if(e.keyCode == 13){
			listLocationPageUrl(url,totalpage);
		}

	}

	function listKeyDownJump(e,url,totalpage) {
        var currKey=0,e=e||event;
		currKey=e.keyCode||e.which||e.charCode;
		var keyName = String.fromCharCode(currKey);
		if(e.keyCode == 13){
			listLocationPageUrl(url,totalpage);
		}
			}

	function searchKeyDownJump(e,url,totalpage) {
		var currKey=0,e=e||event;
		currKey=e.keyCode||e.which||e.charCode;
		var keyName = String.fromCharCode(currKey);
		if(e.keyCode == 13){
			searchlocationPageUrl(url,totalpage);
		}
	}

	function isDigit(s) {
		var patrn = /^[0-9]{1,20}$/;
		if (!patrn.exec(s))
			return false;
		return true;
	}
	function clickFilterBox(url) {
		if (document.getElementById("isPromotions").checked) {
			url = url + "&promotions=1";
		}
		var sendUrl=encodeURI(encodeURI(url));
		window.location.href = sendUrl;
	}

	function jumptopage(url, totalpage) {
		var pages = document.getElementById("jumpto").value;
		if (!isDigit(pages)) {
			alert("输入页码非数字字符,请重新输入");
			return;
		}
		if (pages == 0) {
			alert("输入页码不能为0,请重新输入");
			return;
		}
		if (eval(pages) > eval(totalpage)) {
			alert("输入页码不能大于最大页码数,请重新输入");
			return;
		}
		var str = url + "&gotoPage=" + pages;
		searchAjax(str);
	}

function buyToCart(iy,pid) {
		if(!iy) iy = 1;
		var buyNumId="buyNum_"+pid;
		var buyNum=document.getElementById(buyNumId).value;
		addToCart(iy,pid,buyNum,pid,1);
		drawBottomCart(1);
		setTimeout("jQuery('.minicart_list').fadeOut()",3000);
}
function searchAjax(urlStr) {
		var rqstmode = "asynchronous";
		var sendUrl = urlStr + "&rqstMode=" + rqstmode + "&t=" + Math.random();
		$.ajax({
			url : encodeURI(encodeURI(sendUrl)),
			type : "GET",
			cache : false,
			success : function(data) {
				$('#plist').html(data);
				mouseover_add_class(".itemSearchResultCon");
				priceRange();
				iniCombineProduct();
				var searchCompareSelectAjax = new searchCompareProductSelect();
				searchCompareSelectAjax.loadCompare();
				picChange();
				on_result_load();
				window.scrollTo(0,166);
				jQuery('[data-original]').lazyload({threshold : 200, effect : "fadeIn", skip_invisible : true});
			},
			error : function() {
				alert("搜索失败！ ");
			}
		})
	};
function catagorySearch(urlStr) {
	 var sendUrl=encodeURI(encodeURI(urlStr));
	window.location.href = sendUrl;
}
function locationUrl(urlStr){
	var sendUrl=encodeURI(encodeURI(urlStr));
	window.location.href = sendUrl;
}
function listCatagoryClick(id) {
	 var baseUrl="http://www.111.com.cn/list/list.action";
	 var sendUrl= baseUrl+"?category="+id;
	 window.location.href =sendUrl;
}
function searchCatagoryClick(keyword,id) {
	 var baseUrl="http://www.111.com.cn/search/search.action";
	 var sendUrl= baseUrl+"?keyWord="+keyword+"&category="+id;
	 window.location.href =encodeURI(encodeURI(sendUrl));
}
jQuery(document).ready(function() {
    //stone 2014 0917  wangxudong
    $('.searchCrumb .one').hover(function(){
        $(this).find('.linkOne').addClass('cur');
        $(this).find('ul').css('display','block');
    },function(){
        $(this).find('.linkOne').removeClass('cur');
        $(this).find('ul').css('display','none');
    });
    $('.secondList li a').click(function(){
        $('.secondList li a').removeClass('red');
        $(this).addClass('red');
        var tex=$(this).text();
        $(this).closest('.one').find('.linkOne span').text(tex);
    });
    $('.filtroDl .listZonghe').hover(function(){
	$(this).find('.linkOne').addClass('cur');
	var pl=$(this).closest('ul').offset().left;
	var pw2=$(this).closest('ul').width()/2;
	var lef=$(this).find('.linkOne').offset().left;
	var ll=$(this).find('.linkOne').width()-$(this).find('.listShow').width();
	if(lef-pl>pw2){
		$(this).find('.listShow ').css('left',ll);
	};
	$(this).find('.listShow ').css('display','block');
    },function(){
        $(this).find('.linkOne').removeClass('cur');

        $(this).find('.listShow').css('display','none');
    });

});

