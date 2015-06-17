var isBusy = false;
var threadCount = 0;
var imgCountPerTime = 1000;
var lazyLoadDelay = 50; (function(b) {
	b.YHD = {
		imgLoad: {
			objArray: [],
			loadImg: function(e) {
				if (e && e.length > 0) {
					for (var f = 0, a = e.length; f < a; f++) {
						if (b.inArray(e[f], b.YHD.imgLoad.objArray) == -1) {
							b.YHD.imgLoad.objArray.push(e[f])
						}
					}
				}
			},
			pageTop: function() {
				return document.documentElement.clientHeight + Math.max(document.documentElement.scrollTop, document.body.scrollTop);
			},
			load: function() {
				if (window.shutdownImgLoad) {
					return false
				}
				isBusy = true;
				threadCount = 0;
				var a = b.YHD.imgLoad.pageTop();
				for (var p = 0, q = b.YHD.imgLoad.objArray.length; p < q; p++) {
					var m = b("#" + b.YHD.imgLoad.objArray[p]);
					if (m) {
						var n = m.find("img");
						for (var i = 0, l = n.size(); i < l; i++) {
							var o = b(n[i]);
							if (o.offset().top <= a + 100) {
								var r = o.attr("original");
								if (r) {
									o.attr("src", r).removeAttr("original");
									threadCount++;
									if (threadCount >= imgCountPerTime) {
										break
									}
								}
							}
						}
					}
					if (threadCount >= imgCountPerTime) {
						break
					}
				}
				if (threadCount >= imgCountPerTime) {
					setTimeout("jQuery.YHD.imgLoad.load()", lazyLoadDelay)
				} else {
					isBusy = false
				}
			}
		}
	}
})(jQuery);


// 分类筛选
function nav_search_page() {
	/*var url=$($(".itemChoose").find(".current").parent().parent().prev().children('a')[1]).attr("href");
	 var h3url=$($(".itemChoose").find(".current").parents("ul").prev("h3").children('a')[1]).attr("href");*/
	var b = $(".itemChooseBox").children("ul:visible");



	//改变了默认展开第一个的逻辑
	var crumbChoose=$("a[fatherstatus='1']");
	var crumbChooseId = crumbChoose.attr("showId");
	var hLink = $("a[cid="+crumbChooseId+"]");
	var itch=$(hLink).parent();


	var a = itch.next("ul");
	itch.addClass("no_bd_b");
	//itch.siblings("h3").find(".open").removeClass("open");
	var btn=itch.children(".icon_btn");
	btn.addClass("open");
	//b.hide();
	//b.find("ul").hide();
	a.find(".show").show();
	a.show();

	/*$($(".itemChoose").find(".current").parent().parent().prev().children('a')[0]).attr("class","icon_back").attr("href",url);
	 $($(".itemChoose").find(".current").parents("ul").prev("h3").children('a')[0]).attr("class","icon_back").attr("href",h3url);*/
	$(".itemChoose > h3:gt(4)").hide();
	$(".itemChoose > .showOther").toggle(function() {
			$(this).addClass("open");
			$(".itemChoose > h3:gt(4)").show();
			$(this).siblings("h3").find(".icon_btn").removeClass("open");
			$(this).parent(".itemChoose").find("ul").hide();
			$(this).text("收缩");
		},
		function() {
			$(this).removeClass("open");
			$(".itemChoose > h3:gt(4)").hide();
			$(this).siblings("h3").find(".icon_btn").removeClass("open");
			$(this).parent(".itemChoose").find("ul").hide();
			$(this).text("展开");
		});
	$(".itemChoose").find(".current").parents("ul").each(function() {
		//var a = $(this).addClass("show").siblings(":eq(1)").filter("a").attr("href");
		//$(this).addClass("show").siblings(":first").filter("a").attr("class", "icon_btn open");
		var openul=$(this).addClass("show").prev();
		//$("openul>a:first-child").attr("class", "icon_btn open");
		openul.find("a:first-child").attr("class", "icon_btn open");
	});
	$(".itemChoose h3 .icon_btn").click(function() {
		if ($(this).hasClass("back")) {
			return
		}
		var a = $(this).parent("h3").next("ul");
		if (a.length <= 0) {
			return false;
		}
		var b = $(".itemChooseBox").children("ul:visible");
		$(".itemChoose h3").removeClass("no_bd_b");
		if (a.is(":visible")) {
			$(this).parent("h3").removeClass("no_bd_b");
			$(this).removeClass("open");
			a.hide();
			a.find("ul").hide();
		} else {
			$(this).parent("h3").addClass("no_bd_b");
			$(this).parent("h3").siblings("h3").find(".open").removeClass("open");
			$(this).addClass("open");
			b.hide();
			b.find("ul").hide();
			a.find(".show").show();
			a.show();
		}
	});
	$(".itemChoose li.child .icon_btn").click(function(a) {
		if ($(this).hasClass("back")) {
			return
		}
		var b = $(this).siblings("ul");
		if (b.is(":hidden")) {
			b.find(".show").show();
			b.find(".show").siblings(".icon_btn").addClass("open");
			b.show();
			$(this).addClass("open");
			$(this).parent("li").siblings("li").find(".icon_btn").removeClass("open");
			$(this).parent("li").siblings("li").find("ul").hide();
			a.stopPropagation();
		} else {
			$(this).removeClass("open");
			b.hide();
			b.find("ul").hide();
			b.find(".icon_btn").removeClass("open");
			a.stopPropagation();
		}
	});
	$(".itemChoose ul a.current").parents("ul").show();
}

// 鼠标移到商品时效果
function mouseover_add_class(a) {
	$(a).hover(function() {
			$(this).addClass("cur");
		},
		function() {
			$(this).removeClass("cur");
		});
}
//加载时设置排序条件的href
function sort_add_href() {
	var sorturl=$("#sortPatternId").val();
	var lis=$(".rank").find("li").each(function(){
			var obj = $(this);
			if (!obj.hasClass("down price_range")) {
				var a=obj.find("a");
				if(typeof(a)=="object"){
					var sortId=a.attr("sortid");
					if(sortId!=undefined){
						var l=sorturl.StringFormat(sortId);
						a.attr("href",l);
					}
				}
			}
		}
	)
}
//加载时设置页码的href
function page_add_href() {
	var pagepattern=$("#pagePatternId").attr("value");
	var lis=$(".turnPageBottom a").each(function(){
			var obj = $(this);
			var pageno=obj.attr("pageno");
			var sendurl=pagepattern.StringFormat(pageno);
			var l=sendurl.StringFormat(pageno);
			obj.attr("href",l);
		}
	)
}
//加载时设置上下頁的href
function prev_next_add_href() {
	var pagepattern=$("#pagePatternId").attr("value");
	$(".page a").each(function(){
			var obj = $(this);
			var clazz=obj.attr("class");
			if(clazz=="prev" || clazz=="next"){
				var pagenum=obj.attr("pagenum");
				var l=pagepattern.StringFormat(pagenum);
				obj.attr("href",l);
			}
		}
	)
}
function redirecttopage() {
	var pagepattern=$("#pagePatternId").attr("value");
	var topage=$("#jumpto").attr("value");
	var totalPage=$("#totalPageId").attr("value");

	if (!isDigit(topage)) {
		alert("输入页码非数字字符,请重新输入");
		return;
	}
	if (topage == 0) {
		alert("输入页码不能为0,请重新输入");
		return;
	}
	if (eval(topage) > eval(totalPage)) {
		alert("输入页码不能大于最大页码数,请重新输入");
		return;
	}
	var sendurl=pagepattern.StringFormat(topage);
	window.location =sendurl;
}
function more2Attribute() {
	// if (cateType != 5) {
	var c = $("#group_attr .attrMore:eq(0) dt").text();
	var b = $("#group_attr .attrMore:eq(1) dt").text();
	var a = $("#group_attr .attrMore:eq(2) dt").text();
	var d = "";
	if (c != "") {
		d += c.replace(/[\:|\：]/, "");
	}
	if (b != "") {
		d += "、" + b.replace(/[\:|\：]/, "");
	}
	if (a != "") {
		d += "...";
	}
	if (d != "") {
		$("#more_attribute .unfold:eq(0) span").text("（" + d + "）");
	}
	// }
}

function searchFilter() {
	var a = $("#filterbrandList"),
		d = $("#filterBrandAll"),
		b = $("#filterBrandMore"),
		c = $("#filterBrandListAll");

	//品牌重载页面的时候只显示10个，其它隐藏
	/*$("#filterbrandList").find("li").each(function(i){
	 if(i+1>10){
	 $(this).attr("style","display:none;");
	 }
	 }
	 );*/

	filterMultiList = d.find(".filterMultiList");
	b.click(function() {
		var f = $(this);
		e = d.find(".cur").find("span");
		if (f.hasClass("packup")) {
			f.removeClass("packup").text("更多");
			a.show();
			d.hide()
		} else {
			f.addClass("packup").text("收起");
			a.hide();
			d.show();
			a.find(".cur").each(function() {
				var g = filterMultiList.find("[brandId=" + $(this).attr("brandId") + "]");
				if (!g.hasClass("cur")) {
					g.addClass("cur")
				}
			});

			c.find("a").each(function() {
				var h = $(this);
				var g = $(this).attr("brandid");
				if (!h.hasClass("cur")) {
					var urlStr=$(this).attr("url");
					$(this).attr("href", encodeURI(encodeURI(urlStr)));
				}

			});

			if ($.browser.msie && $.browser.version <= 6 && c.height() > 106) {
				c.height(106)
			}
		}
	});
	$("#brandSwitch").delegate("li", "click",
		function() {
			c.scrollTop(c.children("ul").children("li").eq($(this).index()).position().top)
		});
	$(".filterMulti").click(function() {
		var i = $(this),
			g = $(this).parent().prev().find(".filterMultiList"),
			h = i.parent(),
			f;
		h.addClass("searchOpOperateMulti");
		g.find("a").each(function() {
			$(this).attr("href", "javascript:void(0);")
		});
		if (i.hasClass("filterMultiBrand")) {
			var e = i.parent().prev().find(".selection");
			a.hide();
			d.show().addClass("selectionMulti");
			g.find("a").each(function() {
				var j = $(this);
				if (j.attr("select") == 1) {
					j.addClass("cur");
					j.find("span").removeAttr("style")
				}
			});
			f = g.parent().next().find(".confirm");
			if ($.browser.msie && $.browser.version <= 6 && c.height() > 106) {
				c.height(106)
			}
		} else {
			h.prev().find(".selection").addClass("selectionMulti");
			g.find("a").each(function() {
				var j = $(this);
				if (j.parent().hasClass("none")) {
					j.parent().removeClass("none")
				}
				if (j.attr("select") == 1) {
					j.addClass("cur");
					j.find("span").removeAttr("style")
				}
			});
			f = g.next().find(".confirm")
		}
		if (g.find(".cur").length > 0) {
			if (!f.hasClass("clickable")) {
				f.addClass("clickable")
			}
		} else {
			if (f.hasClass("clickable")) {
				f.removeClass("clickable")
			}
		}
	});
	$(".confirm").each(function() {
		var e = $(this);
		if (!e.hasClass("clickable") && e.parent().prev().find(".cur").length > 0) {
			e.addClass("clickable")
		}
	});
	$(".searchResultOp").delegate(".selectionMulti a", "click",
		function() {
			var g = $(this),
				f = g.parents(".filterMultiList"),
				e = g.parents(".selectionMulti").find(".confirm");
			if (!g.hasClass("cur") && f.find(".cur").length >= 20) {
				alert("最多选择20个");
				return
			}
			if (g.hasClass("cur")) {
				g.removeClass("cur")
			} else {
				g.addClass("cur")
			}
			if (f.find(".cur").length > 0 && !e.hasClass("clickable")) {
				e.addClass("clickable")
			} else {
				if (f.find(".cur").length <= 0 && e.hasClass("clickable")) {
					e.removeClass("clickable")
				}
			}
		});
	$(".selection").delegate(".confirm", "click",
		function() {
			var k = $(this);
			if (!k.hasClass("clickable")) {
				return
			}
			var l = k.attr("url");
			var f = k.parent().prev();
			var j = k.parents("dd").next().find(".filterMulti");
			var h = "";
			if (j.hasClass("filterMultiBrand")) {
				f.find(".cur").each(function() {
					h = h + "," + $(this).attr("brandId")
				});
				h = h.substr(1);
				//品牌点击确定后提交到此处
				l=brand_format(l,h);
				//结束
			}
			else if(j.hasClass("listFilterCatch"))
			{
				$("#group_attr").find(".cur").each(function() {
					var attr=$(this).attr("attrId");
					if(attr!=undefined){
						h = h + "," + $(this).attr("attrId");
					}
				});
				$("#listSearchFilter").find(".cur").each(function() {
					var attr=$(this).attr("attrId");
					if(attr!=undefined){
						h = h + "," + $(this).attr("attrId");
					}
				});
				h = h.substr(1);
				//筛选条件点击确定后提交到此处
				l=filter_format(l,h);

			}
			else {
				$("#group_attr").find(".cur").each(function() {
					var attr=$(this).attr("attrId");
					if(attr!=undefined){
						h = h + "," + $(this).attr("attrId");
					}
				});
				$("#listSearchFilter").find(".cur").each(function() {
					var attr=$(this).attr("attrId");
					if(attr!=undefined){
						h = h + "," + $(this).attr("attrId");
					}
				});
				h = h.substr(1);
				//筛选条件点击确定后提交到此处
				l=filter_format(l,h);
			}
			var sendUrl=encodeURI(encodeURI(l));
			window.location =sendUrl;
		});
	$(".selection").delegate(".cancel", "click",
		function() {
			var f = $(this);
			var e = $(this).parents("dd").next().find(".filterMulti");
			f.parents(".selectionMulti").removeClass("selectionMulti").parents("dl").find(".searchOpOperate").removeClass("searchOpOperateMulti");
			if (f.hasClass("cancelBrnad")) {
				d.hide();
				a.show();
			}
			if (e.hasClass("filterMultiBrand")) {
				if (b.hasClass("packup")) {
					b.removeClass("packup").text("更多");
				}
				c.find("a").each(function() {
					if ($(this).attr("select") == 0) {
						$(this).removeClass("cur");
					}
				});
			} else {
				f.parent().prev().find("a").each(function() {
					var g = $(this);
					g.attr("href", g.attr("url"));
					if (g.attr("select") == 0) {
						g.removeClass("cur");
						if (f.attr("ismultichoose") == 1) {
							g.parent().addClass("none");
						}
					} else {
						if (!g.hasClass("cur")) {
							g.addClass("cur");
						}
					}
				});
			}
		});
	$(".filterAttrList").find("i").hover(function() {
			$(this).addClass("hover");
		},
		function() {
			$(this).removeClass("hover");
		});
}
String.prototype.StringFormat = function() {
	if (arguments.length == 0) {
		return this;
	}
	for (var StringFormat_s = this, StringFormat_i = 0; StringFormat_i < arguments.length; StringFormat_i++) {
		StringFormat_s = StringFormat_s.replace(new RegExp("\\{" + StringFormat_i + "\\}", "g"), arguments[StringFormat_i]);
	}
	return StringFormat_s;
};

function brand_format(source,value){
	return custom_format(source,"BRAND_PLACE_HOLDER",value);
}

function filter_format(source,value){
	return custom_format(source,"FILTER_PLACE_HOLDER",value);
}

function promotion_format(source,value){
	return custom_format(source,"PROMOTION_PLACE_HOLDER",value);
}

function price_format(source,value){
	return custom_format(source,"PRICE_PLACE_HOLDER",value);
}

function custom_format(source,rep,value){
	return source.replace(new RegExp(rep,"g"),value);
}

function brandListAll(urlStr) {
	var sendUrlPre= urlStr.StringFormat("0");
	var sendUrl=encodeURI(encodeURI(sendUrlPre));
	window.location.href = sendUrl;
}

function locationPageUrl(url)
{
	var pageId=$('#jumpto').text();
	searchAjax(url+"&gotoPage="+pageId);
}

function showOrHideMoreAttr(a, b) {
	if (a == "1") {
		$(".attrMore").each(function() {
			$(this).show()
		});
		$(b).hide().next().show()
	} else {
		$(".attrMore").each(function() {
			$(this).hide()
		});
		$(b).hide().prev().show()
	}
}

var searchCompareSelect = new searchCompareProductSelect();
function searchCompareProductSelect() {
	var c = this;
	var e, k, j, b, g, i, m, f, l, d;
	l = '<p class="tips_1">请至少选择2件商品才能对比。</p>';
	d = '<p class="tips_2">很抱歉，最多只能对比3个商品哦，请删除其他商品后再试。</p>';

	this.loadCompare = function() {
		c.initHTMLParams();
		e.delegate(".search_list_compare", "mouseenter",
			function() {
				var n = $(this);
				if (n.hasClass("search_compare_active")) {
					return false
				}
				n.addClass("search_compare_hover")
			});
		e.delegate(".search_list_compare", "mouseleave",
			function() {
				$(this).removeClass("search_compare_hover")
			});
		e.delegate(".compare_select_Btn", "click",
			function() {
				var q = $(this).parent();
				var p = q.attr("compareproductid");
				var n = q.attr("compareproductimg");
				var o = q.attr("compareproducttitle");
				if (k.is(":hidden")) {
					k.show();

					$(window).scroll()

					j.addClass("show_compare");
					if ($.browser.msie && $.browser.version <= 6) {
						j.css("top", $(window).scrollTop() + 200)
					}
				}
				if (q.hasClass("search_compare_active")) {// 立即对比 状态时
					if (c.CookieContains(p)) {
						q.removeClass("search_compare_active");
						c.updateCookie(p, n, o, 1)
					} else {
						c.updateCookie(p, n, o, 0)
					}
				} else {// 对比 状态时
					f = c.getCompareProductNum();
					if (f < 3) {
						if (c.CookieContains(p)) {
							c.updateCookie(p, n, o, 0)
						} else {
							q.addClass("search_compare_active");
							c.updateCookie(p, n, o, 0)
						}
						if (k.is(":hidden")) {
							k.show();

							$(window).scroll()

						}
					} else {
						a(d)
					}
				}


				c.refreshCompareBox();
				f = c.getCompareProductNum();
				if (f <= 1) {
					g.addClass("disabled").attr("title", "请至少选择2件商品才能对比。")
				} else {
					g.removeClass("disabled").removeAttr("title")
				}
			});
		$("#compareBox").delegate(".bth_compare", "click",
			function() {
				var p = $(this);
				f = c.getCompareProductNum();
				c.refreshCompareSelected();
				if (f <= 1) {
					a(l);
					g.addClass("disabled").attr("title", "请至少选择2件商品才能对比。");
					return false
				} else {
					g.removeClass("disabled").removeAttr("title");
					var o = c.getCompareProductHref();
					var n = document.location.href;
					jQuery.cookie("search_compare_products_prevUrl", n, {
						domain: '.111.com.cn',
						path: '/',
						expires: 1
					});
					p.attr("href", o);
					p.attr("target", "_blank")
				}
			});
		e.delegate(".search_list_compare a", "click",
			function(p) {
				var q = $(this);
				var o = c.getCompareProductHref();
				f = c.getCompareProductNum();
				if (f <= 1) {
					a(l);
					c.refreshCompareSelected();
				} else {
					var n = document.location.href;
					jQuery.cookie("search_compare_products_prevUrl", n, {
						domain: no3wUrl,
						path: "/",
						expires: 1
					});
					q.attr("href", o);
					q.attr("target", "_blank")
				}
			});
		$("body").delegate(".compare_tips .close", "click",
			function() {
				$("#searchListMask").hide();
				$(this).parent(".compare_tips").hide()
			});
		k.delegate(".close", "click",
			function() {
				h();
				k.hide();
				j.removeClass("show_compare");
				if ($.browser.msie && $.browser.version <= 6) {
					j.css("top", $(window).scrollTop() + $(window).height() / 2)
				}
				var n = $("#online_service_show");
				var o = $("#online_service_hide");
				if ($.browser.msie && $.browser.version <= 6) {
					n.css({
						position: "absolute",
						top: 300 + $(window).scrollTop()
					});
					o.css({
						position: "absolute",
						top: 300 + $(window).scrollTop()
					})

				}
			});
		k.delegate(".del", "click",
			function() {
				c.updateCookie(this.id, "", "", 1);
				c.refreshCompareSelected();
				f = c.getCompareProductNum();
				if (f <= 1) {
					g.addClass("disabled");
				}
			});
		k.delegate(".empty", "click",
			function() {
				h();
				g.addClass("disabled");
			});
		$(".itemSearchResult").delegate(".itemSearchResultCon", "mouseenter",
			function() {
				var o = $(this);
				o.addClass("cur");
				var n = o.find(".search_list_compare");
				if (!n.hasClass("none")) {
					n.show();
				}
			});
		$(".itemSearchResult").delegate(".itemSearchResultCon", "mouseleave",
			function() {
				var o = $(this),
					n = o.find(".search_list_compare");
				o.removeClass("cur");
				if (n.hasClass("search_compare_active")) {
					return false;
				}
				n.hide();
			});
		c.refreshCompareSelected();
	};
	this.initHTMLParams = function() {
		e = $("#search_table");
		k = $("#compareBox");
		j = k.parent();
		b = k.find("ul");
		g = k.find(".bth_compare");
		i = $(".search_list_compare a")
	};
	function h() {
		jQuery.cookie("search_compare_products", null, {
			domain: no3wUrl,
			path: "/"
		});
		c.refreshCompareSelected();
	}
	function a(p) {
		var o, r, q, n;
		q = '<div id="searchListMask" class="search_list_mask"></div>',
			n = '<div class="compare_tips" id="compareTips"><div class="compare_tips_bg"></div><div class="compare_tips_con">' + p + '</div><a href="javascript:void(0);" class="close"></a></div>';
		if ($("#searchListMask").length <= 0) {
			o = $(q).insertAfter(e);
			r = $(n).appendTo("body")
		} else {
			o = $("#searchListMask").show();
			r = $("#compareTips").show()
		}
		if ($.browser.msie && $.browser.version <= 6) {
			r.css("top", $(window).scrollTop() + $(window).height() / 2)
		}
		if (m) {
			clearTimeout(m)
		}
		r.find(".compare_tips_con").html(p);
		m = setTimeout(function() {
				o.hide();
				r.hide()
			},
			3000);
		$(window).scroll(function() {
			if ($.browser.msie && $.browser.version <= 6) {
				r.css("top", $(window).scrollTop() + $(window).height() / 2)
			}
		})
	}
	this.refreshCompareSelected = function() {
		var o = c.getProductsFromCookie();
		b = k.find("ul");
		var n = [];
		$.each(o,
			function(q, r) {
				n.push(r.id)
			});
		$(".search_list_compare").each(function() {
			var r = $(this);
			var p = r.find("a");
			var q = r.attr("compareproductid");
			if (jQuery.inArray(q, n) != -1) {
				if (!r.hasClass("search_compare_active")) {
					r.addClass("search_compare_active")
				}
				if (k.is(":hidden")) {
					k.show();

					$(window).scroll()

				}
			} else {
				if (r.hasClass("search_compare_active")) {
					r.removeClass("search_compare_active");
					r.hide()
				}
			}
		});
		c.refreshCompareBox()
	};
	this.refreshCompareBox = function() {
		var o = c.getProductsFromCookie();
		if (o.length == 0) {
			b.empty();
			return false
		}
		b = k.find("ul");
		var n = [];
		var q = [];
		var p = [];
		$.each(o,
			function(r, s) {
				n.push(s.id);
				q.push(s.img);
				p.push(s.title)
			});
		f = n.length;
		if (f <= 1) {
			g.addClass("disabled").attr("title", "请至少选择2件商品才能对比")
		} else {
			g.removeClass("disabled").removeAttr("title")
		}
		b.empty();
		$.each(n,
			function(r, s) {
				b.append('<li><a href="javascript:void(0);"><img src="' + q[r] + '" title="' + p[r] + '"/></a><a class="del" id="' + s + '" href="javascript:void(0);"></a></li>')
			});
		if (n.length > 0 && k.is(":hidden")) {
			k.show()
		}
	};
	this.updateCookie = function(s, q, o, v) {
		var n = [];
		var r = [];
		var t = [];
		var p = "[";
		var u = c.getProductsFromCookie();
		if (u && s) {
			$.each(u,
				function(w, x) {
					if (v == 0 || s != x.id) {
						n.push(x.id);
						r.push(x.img);
						t.push(x.title)
					}
				})
		}
		if (v == 0) {
			n.push(s);
			r.push(q);
			t.push(o)
		}
		$.each(n,
			function(x, z) {
				if (x > 0) {
					p += ","
				}
				p += "{";
				var w = r[x];
				var y = t[x];
				if (!w) {
					w = ""
				}
				if (!y) {
					y = ""
				}
				p = p + '"id":"' + z + '","img":"' + w + '","title":"' + y + '"';
				p += "}"
			});
		p += "]";
		jQuery.cookie("search_compare_products", p, {
			domain: no3wUrl,
			path: "/",
			expires: 1
		})
	};
	this.CookieContains = function(p) {
		var n = false;
		var o = [];
		o = c.getProductsFromCookie();
		$.each(o,
			function(q, r) {
				if (r.id == p) {
					n = true;
					return false
				}
			});
		return n
	};
	this.getCompareProductNum = function() {
		var n = c.getProductsFromCookie();
		return n.length
	};
	this.getProductsFromCookie = function() {
		var o = [];
		var n = jQuery.cookie("search_compare_products");
		if (n) {
			o = $.parseJSON(n)
		}
		return o
	};
	this.getCompareProductHref = function() {
		var o = c.getProductsFromCookie();
		var p = "";
		$.each(o,
			function(r, s) {
				if (r > 0) {
					p = p + "-"
				}
				var q = r + 1;
				p = p + s.id
			});
		//拼接请求地址
		var n = "http://search.111.com.cn/compareProduct/compareProduct.action?compares=" + p;
		return n
	}
};

function modifyBuyNum(d, a) {
	var b;
	var c;
	if (a == -1) {
		c = $(d).next();
		b = parseInt(c.val()) || 1;
		if (b == 1) {
			return
		} else {
			if (b == 2) {
				$(d).attr("class", "search_list_reduce_gray")
			} else {
				$(d).next().next().attr("class", "search_list_plus")
			}
			c.val(b + a)
		}
	} else {
		c = $(d).prev();
		b = parseInt(c.val()) || 1;
		if (b == 99) {
			return
		} else {
			if (b == 98) {
				$(d).attr("class", "search_list_plus_gray")
			} else {
				$(d).prev().prev().attr("class", "search_list_reduce")
			}
			c.val(b + a)
		}
	}
}
function checkBuyNum(c, a) {
	var b = $(c);
	if (a != 8 && a != 46) {
		if (!/^(([1-9])|([1-9][0-9]))$/.test(b.val())) {
			b.val(1);
			alert("请输入1-99的整数")
		}
		if (b.val() == 1) {
			b.prev().attr("class", "search_list_reduce_gray");
			b.next().attr("class", "search_list_plus")
		} else {
			if (b.val() == 99) {
				b.prev().attr("class", "search_list_reduce");
				b.next().attr("class", "search_list_plus_gray")
			} else {
				b.prev().attr("class", "search_list_reduce");
				b.next().attr("class", "search_list_plus")
			}
		}
	} else {
		b.prev().attr("class", "search_list_reduce_gray");
		b.next().attr("class", "search_list_plus")
	}
}

jQuery(document).ready(function() {

	runfunctions([], [showSearchOpGraph, priceRange, opBrandMaxWidth], this);
	// lazyLoad_AdHtmlData(0, 1);//默认装入第一页
	$(".search_op_tips", "li").each(function(){
		$(this).mouseenter(function(){			//该鼠标放在上面时 事件
			//alert($(this).parent().parent().parent().parent().parent().parent().html());

			$(this).parent().parent().addClass('cur');

			$(this).parent().parent().children('.selectionTips').show();

			$(this).parent().parent().parent().parent().parent().parent().attr('style','z-index:103');

		}).mouseleave(function(){				//该鼠标离开时 事件
			$(this).parent().parent().removeClass('cur');
			$(this).parent().parent().children('.selectionTips').hide();

			$(this).parent().parent().parent().parent().parent().parent().attr('style','');

		});

	});
	mouseover_add_class(".itemSearchResultCon");
	more2Attribute();

	runfunctions([], [nav_search_page, search_Sort_Margin, attribute_Selected, scrollToTop, priceRange, opBrandMaxWidth, picChange, on_result_load], this);
	bindSeriesBuy();
	/*sort_add_href();*/
	/*page_add_href();*/
	/*prev_next_add_href();*/
	checkPlist(true);
	selectionTips();
	bigPromotion();
	groupBuy();
	searchFilter();
	setProvince();
	relative();
	search_roll_revel();
	searchCompareSelect.loadCompare();
	headerNav_channel();
	//setSupInit();
	iniCombineProduct();
});


//获取省份ID

function setProvince(){
	if(!$.cookie('provinceId')){
		$.cookie('provinceId', '110000', {path: '/', domain: '.111.com.cn', expires: 15})
		$('#allProvinces').show();
	}
}

// 浏览相关的
function relative() {
	$("#relative > li").each(function(){// 循环每个浏览相关的

		$(this).mouseenter(function(){			// 该鼠标放在上面时 事件
			$(this).addClass('cur').siblings().removeClass('cur');
		}).mouseleave(function(){				// 该鼠标离开时 事件
			$(this).removeClass('cur');
		});

		$(this).find('.not_Interested').click(function() {
			$('.not_Interested').addClass('active');
		});
	});


}

// 浏览相关分页
function search_roll_revel() {
	c = screen.width >= 1200 ? 5 : 4;

	$('.prev').live('click', function() {// 上一页
		var j = $(this),
			l = j.parent(".correlationProductTurn"),
			m = l.attr("curPage"),
			g = l.find("li"),
			e = l.find(".next"),
			f = l.find(".turn").find("span"),
			k = g.length,
			d = Math.ceil(k / c);
		if (m > 0) {
			f.eq(--m).addClass("cur").siblings().removeClass("cur");
			g.hide();
			for (var h = c * m; h < c * (m + 1); h++) {
				if (g[h]) {
					g[h].style.display = "block"
				} else {
					break
				}
			}
			l.attr("curPage", m);
			if (m == 0) {
				j.hide()
			}
			e.show()
		}
	});

	$('.next').live('click', function() {// 下一页
		var j = $(this),
			l = j.parent(".correlationProductTurn"),
			m = l.attr("curPage"),
			g = l.find("li"),
			e = l.find(".prev"),
			f = l.find(".turn").find("span"),
			k = g.length,
			d = Math.ceil(k / c);
		if (m < d - 1) {
			f.eq(++m).addClass("cur").siblings().removeClass("cur");
			g.hide();
			for (var h = c * m; h < c * (m + 1); h++) {
				if (g[h]) {
					g[h].style.display = "block"
				} else {
					break
				}
			}
			l.attr("curPage", m);
			if (m == d - 1) {
				j.hide()
			}
			e.show()
		}
	});

	// 与浏览记录相关商品
	$('.turn span').live('click', function() {// 当哪一页
		var m = $(this),
			h = m.index(),
			n = m.parents(".correlationProductTurn"),
			o = n.attr("curPage"),
			j = n.find("li"),
			f = n.find(".prev"),
			e = n.find(".next"),
			g = n.find("span"),
			l = j.length,
			d = Math.ceil(l / c);
		if (h != o) {
			m.addClass("cur").siblings().removeClass("cur");
			j.hide();
			o = h;
			for (var k = c * o; k < c * (o + 1); k++) {
				if (j[k]) {
					j[k].style.display = "block"
				} else {
					break
				}
			}
			n.attr("curPage", o);
			if (h == 0) {
				f.hide();
				e.show()
			} else {
				if (h == d - 1) {
					f.show();
					e.hide()
				} else {
					f.show();
					e.show()
				}
			}
		}
	})
}

// 清除 价格文本框
function clearPriceRange() {
	jQuery("#searchPriceRangeMin").val("");
	jQuery("#searchPriceRangeMax").val("")
}

// 搜索价格区间
function searchPriceRange(b) {
	var f = jQuery("#searchPriceRangeMin")[0];
	var c = jQuery("#searchPriceRangeMax")[0];
	var a;
	var e = /^[0-9]*$/;
	if ((f.value == "" || f.value == "0") && (c.value == "0")) {
		c.value = "";
		f.value = "0"
	}
	if ((!e.test(f.value)) || (!e.test(c.value))) {
		return
	}
	if ((f.value != "") && (c.value != "")) {
		if (Number(c.value) < Number(f.value)) {
			a = c.value;
			c.value = f.value;
			f.value = a
		}
	}

	var sendUrl;
	var min;
	var max;

	if(f.value!=""){
		min=f.value;
	}else{
		min="0";
	}

	if(c.value!=""){
		max=c.value;
	}else{
		max="n";
	}

	if(c.value=="" && f.value==""){
		b=price_format(b,"0");
		sendUrl=b;
	}else{
		b=price_format(b,min+","+max);
		sendUrl=b;
	}
	window.location.href = sendUrl
}


function showMoreCategory(a, d) {
	if (jQuery("#categoryPromotionMore")[0] && jQuery("#categoryPromotionPack")[0]) {
		if (d && d == "more") {
			jQuery("#categoryPromotionMore")[0].style.display = "none";
			jQuery("#categoryPromotionPack")[0].style.display = "block"
		} else {
			jQuery("#categoryPromotionMore")[0].style.display = "block";
			jQuery("#categoryPromotionPack")[0].style.display = "none"
		}
	}
	var c;
	if (isWidescreen == 1) {
		c = 4
	} else {
		c = 3
	}
	var b = $("#" + a).children();
	if (d && d == "more") {
		b.each(function(e) {
			$(this).show()
		})
	} else {
		b.each(function(e) {
			if (e >= c) {
				$(this).hide()
			}
		})
	}
}


function setPageBottomDisplayStatus(c) {
	var b = jQuery(".turnPageBottom")[0];
	if (b != null) {
		jQuery(".turnPageBottom")[0].style.display = c
	}
	var a = jQuery(".searchBottom ")[0];
	if (a != null) {
		jQuery(".searchBottom ")[0].style.display = c
	}
}

function setRelatedBrowseDisplayStatus(c) {
	var a = jQuery(".correlationProduct")[0];
	if (a != null) {
		jQuery(".correlationProduct")[0].style.display = c
	}
	var b = jQuery("#hotProductsWrap")[0];
	if (b != null) {
		jQuery("#hotProductsWrap")[0].style.display = c
	}
};
function attribute_Selected() {
	$(".attrMore .all a").each(function() {
		if ($(this).attr("class") != "cur") {
			$(".searchResultOpMore .unfold").hide().next().show()
		}
	})
}

function picChange() {
	$(".pic_list span:not(.arrow)").click(function() {
		var c = $(this).attr("id");
		var f = $(this).attr("pmId");
		var locId=$(this).attr("localId");  //10-11获取区域商品id
		var d = $(this).attr("oId");
		var b = $(this).attr("hasVideo");
		var parmentid=$(this).attr("parentid");
		$(this).addClass("current").siblings("span").removeClass("current");
		var h = $(this).find("img").attr("src");
		var i = h;
		var j = $(this).parents(".pic_list").prev(".pro_img").find("img");
		j.attr("src", i);
		var pricePng =  $(this).parents(".pic_list").next(".price").find("img");  //遍历后面的元素找到价格图片
		//http://price.maiyaole.com/iprice/38/38474,1.png
		var localPrefix=parseInt(locId/1000);  //取前缀
		var loalPrice="http://price.maiyaole.com/iprice/"+localPrefix+"/"+locId+",1.png";
		pricePng.attr("src",loalPrice);
		if (b == 1) {
			if (!j.next().hasClass(".none")) {
				j.next().addClass(".none")
			}
		} else {
			if (j.next().hasClass(".none")) {
				j.next().removeClass(".none")
			}
		}
		// getProductPrice(c, d);
		var k = $(this).attr("originalTitle");
		var e = $(this).attr("compareId");
		picChang4compare(d, e, h, k);
		//$("#pdlink1_" + d + " img").attr("title", k);
		var g = $(this).attr("subTitle");
		var o = $(this).attr("originaltitle");
		$("#pdlink2_" + parmentid).attr("title", k).html(g + " <em>" + k + "</em>");
		// gotracker("2", "serial_product_change", c);
		var a = $(this).parents(".pic_list").prev(".pro_img").attr("href");
		a =  a.substring(0, a.lastIndexOf("/"))+"/"+c+".html";
		$(this).parents(".pic_list").prev(".pro_img").attr("href", a);
		$(this).parents(".pic_list").prev(".pro_img").attr("title", g);
		$(this).parents(".pic_list").siblings("a.title").attr("href", a)
		$(this).parents(".pic_list").siblings("a.title").attr("title", g);
		$(this).parents(".pic_list").siblings("a.title").html(g+'&nbsp;'+o);
	})
}


function picChang4compare(d, c, a, e) {
	//a = a.substring(0, a.lastIndexOf("_")) + "_60x60.jpg";
	var b = $('.itemSearchResultCon[comproid="' + d + '"] > .search_list_compare');
	if (b.hasClass("none")) {
		b.removeClass("none")
	}
	b.attr("compareproductid", c);
	b.attr("compareproductimg", a);
	b.attr("compareproducttitle", e);
	searchCompareSelect.refreshCompareSelected();
	if (b.is(":hidden")) {
		b.show()
	}
}

function on_result_load() {
	searchGraphSlide(4)
}

function searchGraphSlide(b) {
	var c = $(".fashionList .pic_list"),
		d = c.find("div"),
		a = c.find("a.arrow");
	c.each(function() {
		var h = $(this),
			g = h.attr("totleNum"),
			f = h.find("span");
		if (g <= b) {
			h.children(".arrow").attr("style", "visibility:hidden");
			f.show()
		} else {
			for (var e = 0; e < b; e++) {
				f[e].style.display = "block";
				h.find("a.nextDisable").addClass("next")
			}
		}
	});
	c.delegate(".next", "click",
		function() {
			var l = $(this),
				n = l.parent(),
				k = n.attr("currentPage"),
				g = n.find("span"),
				m = g.length,
				j = Math.ceil(m / b),
				f = n.find(".prevDisable");
			if (k < j - 1) {
				g.hide();
				var e = b * (++k);
				for (var h = e; h < e + b; h++) {
					if (g[h]) {
						g[h].style.display = "block"
					} else {
						break
					}
				}
				f.addClass("prev");
				n.attr("currentPage", "" + k)
			}
			if (k == j - 1) {
				l.removeClass("next")
			}
		});
	c.delegate(".prev", "click",
		function() {
			var m = $(this),
				l = m.parent(),
				k = l.attr("currentPage"),
				j = l.attr("totleNum"),
				h = l.find("span"),
				e = l.find(".nextDisable");
			if (k > 0) {
				h.hide();
				var f = b * (--k);
				for (var g = f; g < f + b; g++) {
					if (h[g]) {
						h[g].style.display = "block"
					} else {
						break
					}
				}
				e.addClass("next");
				l.attr("currentPage", "" + k)
			}
			if (k == 0) {
				m.removeClass("prev")
			}
		})
};

function priceRange() {
	var d = $(".rankOp li.price_range"),
		c = d.find("input"),
		b = d.find("cite"),
		a;
	c.focus(function() {
		clearTimeout(a);
		c.removeClass("cur");
		$(this).addClass("cur");
		d.addClass("cur");
		if (c.eq(0).val() == "￥" && c.eq(1).val() == "￥") {
			c.val("").css("color", "#333")
		}
	});
	c.blur(function() {
		a = setTimeout(function() {
				c.removeClass("cur");
				d.removeClass("cur");
				if (!c.eq(0).val() && !c.eq(1).val()) {
					c.css("color", "#999").val("￥")
				}
			},
			400)
	});
	b.hover(function() {
			$(this).css("color", "#f60")
		},
		function() {
			$(this).css("color", "#06c")
		});
	b.click(function() {
		c.val("");
		c.eq(0).focus()
	})
}

function selectionTips() {
	$(".selection").find("li").hover(function() {
			var c = $(this);
			if (c.find(".selectionTips").length > 0) {
				gotracker("2", "search_daogoushuxing", 0);
				c.addClass("cur");
				c.parents("dl").css("zIndex", "103");
				var a = c.find(".selectionTips").find("img");
				var b = a.attr("original");
				a.attr("src", b);
				if ($.browser.msie && $.browser.version == 6 && c.find("iframe").length == 0) {
					c.find(".selectionTipsBg").append("<iframe style='width:100%;height:100%;filter:Alpha(opacity=0);' border='0' frameborder='0'></iframe>")
				}
			}
		},
		function() {
			$(this).removeClass("cur").parents("dl").css("zIndex", "0");
			$(this).find(".selectionTips").find("img").attr("src", "")
		})
}

function bigPromotion() {
	$(".celebrate").hover(function(b) {
			var a = $(this).parent();
			$tips = $(".select_tips", a);
			a.closest(".itemSearchResult").css({
				zIndex: "1"
			});
			a.closest(".tips").css({
				zIndex: "11"
			});
			$tips.toggleClass("none")
		},
		function(b) {
			var a = $(this).parent();
			$tips = $(".select_tips", a);
			a.closest(".itemSearchResult").css({
				zIndex: "0"
			});
			a.closest(".tips").css({
				zIndex: "0"
			});
			$tips.toggleClass("none")
		})
};

function groupBuy() {
	$(".pro_group_box").find("a").click(function() {
		if ($(this).hasClass("cur")) {
			return
		}
		var c = $(this).attr("comproid");
		var a = $(this).parents(".pro_group");
		var b = $(a).attr("p_index");
		$(this).parents(".itemSearchResultCon").siblings("[comproid='" + c + "']").find(".pro_group").remove();
		// if (requestType != 70 && requestType != 75) {
		$(this).parents(".itemSearchResultCon").siblings("[comproid='" + c + "']").find(".search_list_compare").show()
		// }
		$(this).parents(".itemSearchResultCon").addClass("none").siblings("[comproid='" + c + "']").removeClass("none").append(a);
		$(this).addClass("cur").siblings().removeClass("cur");
		// gotracker("2", "combine_product_preview_" + b, c)
	})
}

function searchListInfoShow(a) {
	$(a).live("mouseover",
		function() {
			recomProducts($(this), $(this).attr("sellProductId"), $(this).attr("mcsiteId"), $(this).attr("merchantId"), $(this).attr("pminfo"))
		}).live("mouseout",
		function() {
			$(this).children("div").hide()
		})
}

function promotionInfoShow(b) {
	var a;
	$(b).live("mouseenter",
		function() {
			var c = $(this);
			a = setTimeout(function() {
					showGift(c, c.attr("giftProductId"), c.attr("merchantId"), c.attr("pmId"), c.attr("mcsiteId"), c.attr("siteflag"), c.attr("sitetype"));
					// gotracker("2", "search_show_gift", c.attr("giftProductId"))
				},
				500)
		}).live("mouseleave",
		function() {
			if (a) {
				clearTimeout(a)
			}
			$(this).children("div").hide()
		})
}

function runfunctions(d, c, e) {
	if (! (c && c.length)) {
		return
	}
	e = e || window;
	var b = c.shift();
	var a = d.shift() || [];
	for (;; b = c.pop(), a = d.pop()) {
		if (typeof b == "function") {
			setTimeout(function() {
					try {
						b.apply(e, a)
					} catch(f) {}
					runfunctions(d, c, e)
				},
				100);
			return false
		}
	}
}

function showSearchOpGraph() {
	if (screen.width >= 1200) {
		searchOpGraphSlide(10)
	} else {
		searchOpGraphSlide(7)
	}
}
function searchOpGraphSlide(c) {
	var g = 0,
		l = $(".searchOpGraph"),
		d = l.find(".con"),
		f = l.find(".prevDisable"),
		h = l.find(".nextDisable"),
		k = d.find("a"),
		b = k.length,
		j = Math.ceil(b / c);
	if (j > 1) {
		h.addClass("next")
	}
	for (var e = 0; e < j * c - b; e++) {
		d.append("<a class='blank'></a>")
	}
	var a = d.find("a");
	for (var e = 0; e < c; e++) {
		if (a[e]) {
			a[e].style.display = "block"
		}
	}
	l.delegate(".next", "click",
		function() {
			if (g < j - 1) {
				a.hide();
				for (var m = c * ++g; m < c * (g + 1); m++) {
					if (a[m]) {
						a[m].style.display = "block"
					} else {
						break
					}
				}
				f.addClass("prev")
			}
			if (g == j - 1) {
				$(this).removeClass("next")
			}
		});
	l.delegate(".prev", "click",
		function() {
			if (g > 0) {
				a.hide();
				for (var m = c * --g; m < c * (g + 1); m++) {
					if (a[m]) {
						a[m].style.display = "block"
					} else {
						break
					}
				}
				h.addClass("next")
			}
			if (g == 0) {
				$(this).removeClass("prev")
			}
		})
}

function opBrandMaxWidth() {
	if ($.browser.msie && $.browser.version <= 6) {
		$(".searchOpBrand li a").each(function() {
			var b = $(this),
				a = b.children("span");
			if (!b.hasClass("cur")) {
				if (a.hasClass("search_op_tips") && a.width() > 101) {
					a.width(101)
				} else {
					if (a.hasClass("search_op_tips") && a.width() > 108) {
						a.width(108)
					}
				}
			}
		})
	}
}

function search_Sort_Margin() {
	var a = $(".searchResultSort ul li").length;
	if (a == 5) {
		$(".searchResultSort ul").addClass("five")
	} else {
		if (a == 4) {
			$(".searchResultSort ul").addClass("four")
		} else {
			if (a == 3) {
				$(".searchResultSort ul").addClass("three")
			}
		}
	}
}

function scrollToTop() {

	//var d = $(".fixedRight"),
	a = $("#go-top"),
		e = $(window),
		c = $("#compareBox"),
		b = e.height() / 2;
	e.scroll(function() {
		if ($(window).scrollTop() > 0) {
			a.fadeIn()
		} else {
			a.fadeOut()
		}
		if (c.is(":visible")) {
			var f = $("#online_service_show");
			var g = $("#online_service_hide");
			if (e.scrollTop() > 432 - b) {
				if ($.browser.msie && $.browser.version <= 6) {
					c.css({
						position: "absolute",
						top: b - 232 + e.scrollTop()
					});
					f.css("top", b + 36 + e.scrollTop());
					g.css("top", b + 36 + e.scrollTop())
				} else {
					c.css({
						position: "fixed",
						top: b - 210
					});
					f.css({
						position: "fixed",
						top: b + 60
					});
					g.css({
						position: "fixed",
						top: b + 60
					})
				}
			} else {
				c.css({
					position: "absolute",
					top: 165
				});
				f.css({
					position: "absolute",
					top: 435
				});
				g.css({
					position: "absolute",
					top: 435
				})
			}
			c.css("right", 0)
		}
		return false
	});
	a.click(function() {
		$("body,html").scrollTop(0);
		return false
	})
}

function bindSeriesBuy() {
	$(".series_pro_buy dd li").live("click",
		function() {
			var h = $(this),
				c = h.parents(".series_pro_buy").find("button");
			var f = h.parents(".series_pro_buy").find("span").attr("id");
			if (!h.hasClass("disable")) {
				if (h.hasClass("cur")) {
					h.removeClass("cur");
					if (h.parents("dl").siblings(".series_para").find(".disable").length > 0) {
						h.parents("dl").siblings(".series_para").find(".disable").removeClass("disable")
					}
					c.removeClass("able").attr("title", "购买前请先选择商品信息")
				} else {
					h.addClass("cur").siblings().removeClass("cur");
					var e = h.find("a").attr("detailTitle");
					var a = h.parents("dl").hasClass("series_color");
					var b = h.parents("dl").siblings(".series_para").find("li");
					if (b.size() > 0) {
						b.each(function() {
							var l = $(this);
							var j = l.find("a").attr("detailTitle");
							var k = checkProductExist(f, a, e, j);
							if (k == "") {
								l.addClass("disable")
							} else {
								var i = $("#stockSubPIds_" + f).val();
								if (i == null || i == "") {
									return
								}
								if (i.indexOf(k) != -1) {
									if (l.hasClass("disable")) {
										l.removeClass("disable")
									}
								} else {
									l.addClass("disable")
								}
							}
							if (h.parents("dl").siblings(".series_para").find(".cur").length == 1 && l.hasClass("cur")) {
								c.addClass("able").attr("title", "");
								$("#seriesPid_" + f).val(k)
							}
						});
						var g = h.parents("dl").siblings(".series_para").find(".disable");
						var d = h.parents("dl").siblings(".series_para").find(".cur");
						if (d.length == 0 && g.length == b.length - 1) {
							b.each(function() {
								if (!$(this).hasClass("disable") && !$(this).hasClass("cur")) {
									var i = $(this).find("a").attr("detailTitle");
									var j = checkProductExist(f, a, e, i);
									if (j != "") {
										$(this).addClass("cur");
										c.addClass("able").attr("title", "");
										$("#seriesPid_" + f).val(j)
									}
								}
							})
						}
					}
				}
			}
		})
}

function checkPlist(b) {
	var a = $("#pflag").val();
	if (a == 1) {
		$("#group_attr").hide();
		$("#more_attribute").hide();
		$("#phoneAttribute").show();
		if (b) {
			checkBox_Default();
			$(".searchOpMulti input:checked").parent("span").addClass("checked");
			$(".searchOpMulti input:checkbox").click(function() {
				$(this).parent("span").toggleClass("checked")
			})
		}
	}
}

function checkBox_Default() {
	$("input[name='checkbox_0']").each(function() {
		$(this).attr("checked", false);
		$(this).parent("span").removeClass("checked")
	});
	$("input[name='checkbox_1']").each(function() {
		$(this).attr("checked", true);
		$(this).parent("span").addClass("checked")
	});
	$("#searchPriceRangeMinIP").attr("value", "");
	$("#searchPriceRangeMaxIP").attr("value", "")
}
//产品显示的右上角小图标显示
function setSupInit(){
	var picBoxItems = {
		'1':{'css':'cuxiao', 'name':'促销'},
		'2':{'css':'dujia', 'name':'独家'},
		'3':{'css':'jingbaojia', 'name':'惊爆价'},
		'4':{'css':'jingkou', 'name':'进口'},
		'5':{'css':'yushou', 'name':'预售'},
		'6':{'css':'qianggou', 'name':'抢购'},
		'7':{'css':'remai', 'name':'热卖'},
		'8':{'css':'qingcang', 'name':'清仓'},
		'9':{'css':'renqi', 'name':'人气'},
		'10':{'css':'youzengpin', 'name':'有赠品'},
		'11':{'css':'xinpin', 'name':'新品'},
		'12':{'css':'tejia', 'name':'特价'},
		'13':{'css':'shoufa', 'name':'首发'},
		'14':{'css':'tuangou', 'name':'团购'},
		'15':{'css':'chaozhi', 'name':'超值'}
	};
	$("sup").each(function(){
			var picId=$(this).attr("picId");
			if(picId!=0){
				var obj=picBoxItems[picId];
				$(this).addClass(obj.css);
			}
		}
	)
}
function clickPromotionBox(f) {
	var val=$("#isPromotions").attr("value");
	f=promotion_format(f,val);
	window.location.href = f
}
//悬浮框
function headerNav_channel() {

	var a = $("#headerNav").offset().top;
	$(window).scroll(function() {
		var b = $(window).scrollTop();
		if (b > a) {
			$("#headerNav").addClass('hd_nav_fixed');
			$(".headerNavWrap").hide();
			$(".hd_fix_center").show();
			$("#headerNav .wrap").removeClass('wline');
			if ($.browser.msie == 1 && $.browser.version == 6) {
				$("#headerNav").css("top", $(window).scrollTop())
			}

		} else {
			$("#headerNav").removeClass('hd_nav_fixed');
			$(".headerNavWrap").show();
			$(".hd_fix_center").hide();
			$("#headerNav .wrap").addClass('wline');
		}


	})
}


//增加组合品显示
function iniCombineProduct() {
	$("li.taocan").each(function(){
			var topid=$(this).attr("topid");
			/*                var d=$($(this).find("[comproid='" + topid + "']"))[1];
			 var def=$(d).attr("comproid");*/
			var kit=$(this).find("#item_kit");
			var kita= $(kit).find("[comproid='" + topid + "']");
			if(kita.length==1){
				initProductConvert(kita);
			}
			else{
				var firsta= $($(kit).find("a"))[0];
				initProductConvert($(firsta));

			}

		}
	)
}
function combineProductBuy() {
	$(".item_kit_n a").live("click",
		function() {
			var kit = $(this);
			if (kit.hasClass("k_cur")) {
				return
			}
			combineProductConvert(kit)
		})
}
function combineProductConvert(kit) {
	var kit_left = kit.parents(".item_kit_n").offset().left,
		w = kit.offset().left,
		q = kit.outerWidth(true),
		s = $(".item_kit i").outerWidth(true),
		B = w - kit_left + q / 2 - s / 2;
	kit.addClass("k_cur").siblings().removeClass("k_cur");
	kit.parents(".item_kit").find("i").stop(true).animate({
			left: B
		},
		150);
	var v = kit.attr("comproid");
	var t = kit.parents("#item_kit");
	var D = kit.parents(".itemSearchResultCon").siblings("[comproid='" + v + "']");
	$(D).find("#item_kit").remove();
	if($(D).hasClass("itemSearchResultCon")){$(D).addClass("cur");}
	if(D.length!=0){
		kit.parents(".itemSearchResultCon").addClass("none").siblings("[comproid='" + v + "']").removeClass("none").append(t);
	}
	kit.addClass("k_cur").siblings().removeClass("k_cur");
	kit.parents(".item_kit").find("i").show();

}
function initProductConvert(kit) {
	var kit_left = kit.parents(".item_kit_n").offset().left,
		w = kit.offset().left,
		q = kit.outerWidth(true),
		s = $(".item_kit i").outerWidth(true),
		B = w - kit_left + q / 2 - s / 2;
	kit.addClass("k_cur").siblings().removeClass("k_cur");
	kit.parents(".item_kit").find("i").stop(true).animate({
			left: B
		},
		150);
	var v = kit.attr("comproid");
	var t = kit.parents("#item_kit");
	var D = kit.parents(".itemSearchResultCon").siblings("[comproid='" + v + "']");
	$(D).find("#item_kit").remove();
	if(D.length!=0){
		kit.parents(".itemSearchResultCon").addClass("none").siblings("[comproid='" + v + "']").removeClass("none").append(t);
	}
	kit.addClass("k_cur").siblings().removeClass("k_cur");
	kit.parents(".item_kit").find("i").show();

}
//增加组合品显示
$(function() {

	//初始化悬浮层 购物车
	//悬浮层 购物车显示
	$(".shoppingCart","#scrollminiCart").mousemove(function(){
		$(this).find('.minicart_list').show();
	});
	$(".shoppingCart","#scrollminiCart").mouseleave(function(){
		$(this).find('.minicart_list').hide();
	});

});