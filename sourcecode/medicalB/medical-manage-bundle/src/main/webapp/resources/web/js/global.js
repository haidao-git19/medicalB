function actionClass(target,enterClass,clickClass){
	$(target).bind({
		mouseenter:function(){$(this).addClass(enterClass)},
		mouseleave:function(){$(this).removeClass(enterClass)},
		click:function(){
			if(clickClass) $(this).addClass(clickClass).unbind();
		}
	});
}

$(function(){
	//加关注按钮
	actionClass(".follow-3","followH-3");
	actionClass(".entrust","entrustH");

	actionClass(".follow-1","followH-1");
	actionClass(".follow-2","followH-2");

	// 申请职位
	actionClass(".apply","applyH");
	
});



//全局模块函数
$(function(){
	//整个条目可点
	actionClass(".js_linkItem-2","hightLight");
	$(".js_linkItem,.js_linkItem-2").find("a,button,input").click(function(event){
		event.stopPropagation();
	});


	//整个条目可点
    $(".js-clk-li > li,.js-clk-cnt").hover(function () {
        $(this).addClass('js-li-hover');
        $(this).find(".list-title").addClass("js-li-hover");
    }, function () {
        $(this).removeClass('js-li-hover');
        $(this).find(".list-title").removeClass("js-li-hover");
    })
    .delegate("a,button,input", 'click', {}, function (event) {
        event.stopPropagation();
    });
    $("li").find("a,button,input").click(function(event){
        event.stopPropagation();
    });
	//列表页-排序最后一项竖线去掉
	$(".wllft-ctrl2 a:last").addClass("last");
	//列表页-顶部筛选
    $(".filterTag .infoExtra span").toggle(function(){
        $(this).addClass("showLess")
            .parents(".info").find(".infoTxt:not('.sub')").height("auto");
        var tar = $(this).parents(".info").find(".infoTxt:not('.sub') a:eq(1)");
        var _index = tar.attr('id');
        if(_index != "1"){
            // alert(_index-1);
            tar.insertAfter($(this).parents(".info").find(".infoTxt:not('.sub') a").eq(_index));
        }
        var id = $(this).attr('sname');
        if( id != 'undefined' ){
            $("#"+id+"Span").show();
            $("#"+id+"Show").hide();
        }

    },function(){
        $(this).removeClass("showLess")
            .parents(".info").find(".infoTxt:not('.sub')").height("23px");
        var id = $(this).attr('sname');
        if( id != 'undefined' ){
            $("#"+id+"Span").hide();
            $("#"+id+"Show").show();
        }
    });

	//tab选项卡
	$(".js_tabPanel").not(":eq(0)").hide();
	actionClass(".js_tabSwitcher li","hover");
	$(".js_tab").each(function(){
		var switcher = $(".js_tabSwitcher li");
		var panel = $(".js_tabPanel")
		switcher.click(function(){
			$(this).addClass("current").siblings(".current").removeClass("current");
			var index = $(this).index();
			panel.eq(index).show().siblings(".js_tabPanel").hide();
		});
	});

	//selectList下拉框
	((function(){
		var selectList = $('.selectList');
		var title = selectList.find('span');
		var optionList = selectList.find('.optionList');

		title.live('mouseenter',function () {
			$(this).parents('.selectList')
			.css('z-index','9')
			.find('.optionList').show()
			.find('li').click(function () {
				$(this).parents('.optionList').hide();
			});
			$(this).siblings("i").addClass('on');
			//ie6,7下回复区域的下拉菜单层级显示问题
			if($.browser.msie){
				$(this).parents('.selectlist-li').css("z-index","10");
				$(this).parents('.selectlist-li').parent('ul').parent().css("z-index","10");
				//$(this).parents('.workEvaluate').parent('.section').css("z-index","1");
			}
		});

		selectList.live('mouseleave',function () {
			$(this).css('z-index','0').find('.optionList').hide();
			$(this).find("i").removeClass('on');
			// ie6,7下回复区域的下拉菜单层级显示问题
			if($.browser.msie){
				$(this).parent('.selectlist-li').css("z-index","0");
				$(this).parent('.selectlist-li').parent('ul').parent().css("z-index","0");
		 		//$(this).parents('.workEvaluate').parent('.section').css("z-index","0");
		 	}
		});

		optionList.each(function () {
			var opItem = $(this).find('li').length;
			if(opItem > 8){
				$(this).find('ul').addClass('ulLimit');
			}
		});
		// old不要删除
		// $(".selectList").hover(function(){
		// 	$(this).css("z-index","9").find(".optionList").show();
		// 	//ie6,7下回复区域的下拉菜单层级显示问题
		// 	if($.browser.msie){
		//  		$(this).parents('.workEvaluate').parent('.section').css("z-index","1");
		//  	}
		// },function(){
		// 	$(this).css("z-index","0").find(".optionList").hide();
		// 	//ie6,7下回复区域的下拉菜单层级显示问题
		// 	if($.browser.msie){
		//  		$(this).parents('.workEvaluate').parent('.section').css("z-index","0");
		//  	}
		// });

        //顶部用户消息下拉
        $(".ucenter-message").hover(
            function(){$(".ucenter-message-list").show();},
            function(){$(".ucenter-message-list").hide();}
        );
        $(".ucenter-message-list").hover(
            function () {
                $(this).show();
                $('.ucenter-message-new').hide();
            },
            function () {
                $(this).hide();
                if (($('.ucenter-message-new ul li').length > 0) && ($('.ucenter-message-list').is(':hidden'))) {
                    $('.ucenter-message-new').fadeIn(500);
                }
            }
        );
        $('.ucenter-message-new a.ucenter-message-new-close').click(function(){$(this).parent().hide();});

        //顶部搜索选择
        $('.search-mod ul.dropdown-menu li').click(function(){
            $('#topSearchForm').attr('action', $(this).attr('url'));

            var placeHolderText = $(this).attr('placeholder-text');
            var inputObj = $('#search_position_key');
            var currentKeyword = $.trim(inputObj.val());
            var currentPlaceHolder = $(this).siblings().filter('.current').attr('placeholder-text');

            inputObj.attr('placeholder-text', placeHolderText)
                .val((currentKeyword==currentPlaceHolder || currentKeyword=='')?'':currentKeyword)
                .change()
                .blur();
        });
    })());
	
	

	//排序筛选
	$(".itemRank dd").click(function(){
		$(this).addClass("current").siblings(".current").removeClass("current");
	});

	//返回顶部
	((function(){
		var wh = $(window).height();
		var toTop = $("<div id='toTop'></div>").appendTo(".doc")
		.click(function(){
			//chrome下用html无效，只能是body
			$("html,body").animate({
				scrollTop : 0
			},200)
		});
		$(window).scroll(function(){
			var st = $(window).scrollTop();
			if(st > 200){
				$("#toTop").fadeIn(500);
			}else{
				$("#toTop").fadeOut(500);
			}
			toTop.css({top:wh - 163 + st +"px"});
		});
		//页面添加app下载
		function onlineQQ(){
			var ww = $(window).width();
			var wh = $(window).height();
			var st = $(window).scrollTop();	
			var downloadHeight = download.height();	
			if(ww < 1286 && ww > 982){		
				download.removeAttr("style");	
				download.css({top:parseInt((wh - downloadHeight)/2) + st +"px","margin-left":"0","right":"10px","left":"none"});
			}
			else{
				download.removeAttr("style");
				download.css({top:parseInt((wh - downloadHeight)/2) + st +"px","margin-left":"511px","right":"none","left":"50%"});
			}
			if(ww <= 982){		
				download.removeAttr("style");	
				download.css({top:parseInt((wh - downloadHeight)/2) + st +"px","margin-left":"366px","right":"none","left":"50%"});
			}
		}	
		if(!$('#app-profile-download').val()){
			var download = $("<div class='online-qq-pop' id='onlineGlobal'><div class='online-app-download' title='点击或扫描下载' onclick=\"window.open('http://www.wealink.com/mobile.php');\"></div></div>").appendTo("body");
			$("html,body").attr('scrollTop',0);
			onlineQQ();			
			$(window).scroll(function(){
				onlineQQ();
			});			
			$(window).resize(function(){
				onlineQQ();
			});
		}
	})());
	
	// 招聘方注册角色提示
	((function(){
		$('.ltipsTo').bind({
			mouseenter:function(event){
				$('.ltipsC').html($(this).attr("rel"));
				var th = parseInt($(this).height());
				var ty = $(this).offset().top;
				var tx = $(this).offset().left;
				var lw = parseInt($('.ltips').width())/2-32;
				$('.ltips').show().css({
					top:ty+th,
					left:tx-lw
				});
				
			},
			mouseleave:function(){
				$('.ltips').hide();
			}
		});
	})());

	// 顶部下拉框，鼠标hover切换消息提醒显示与隐藏
	((function(){
		var st;
		// alert(0);
		var target = $('.topUserFun .user, .topUserFun .message');
		target.live('mouseover',function(){
			// alert(1);
			$(".topSysAlert").hide();
			clearTimeout(st);
		});
		target.live('mouseleve',function(){
			// alert(2);
			function tashow() {$('.topSysAlert').show();}
			st = setTimeout(tashow,500);
		});
/* 		$('.topUserFun .user, .topUserFun .message').hover(function(){
			$(".topSysAlert").hide();
			clearTimeout(st);
		},function(){
			function tashow() {$('.topSysAlert').show();}
			st = setTimeout(tashow,500);
		}); */
	})());

	//input radio和checkbox类型去掉边框
	$('input[type="radio"]').css('border','0');
	$('input[type="checkbox"]').css('border','0');

/*-------
|
|dropdown
|
-------*/

	(function(){
		var dropdown = $('.dropdown');
		//遍历dropdown
		dropdown.each(function(){
			var dropdown_toggle = $(this).find('.dropdown-toggle'),
				dropdown_default = $(this).find('.dropdown-default'),
				dropdown_menu = $(this).find('.dropdown-menu'),
				default_txt = dropdown_default.text();

			if(dropdown_menu.height()>300){
				dropdown_menu.css({
					height:'300px',
					overflowY: 'scroll',
                    overflowX: 'hidden'
				});
			}
			
			//如果默认文字是“请选择”，那么设置颜色为“#ccc”
			if(/^(请选择|全部|不限|推荐状态)/.test($.trim(default_txt))){
				dropdown_default.css('color','#ccc');
			}

			//通过是否有类名“mouseenter-pattern”判断dropdown的触发方式
			var toggle_pattern = $(this).is('.mouseenter-pattern') ? 'mouseenter':'click';
			//dropdown_toggle绑定触发事件
			dropdown_toggle.bind(toggle_pattern,function(){
				$(this).parents('.dropdown').css('z-index','9')
				.find('.dropdown-menu').show();
				$(this).parents('.selectlist-li').css('z-index','10');

			});
			//menu下li的交互效果

            $('.hoveronoff').hover(function(){
                $(this).addClass('active');
            },function(){
                $(this).removeClass('active');
            });

			$(this).find('li').not('.extra').hover(function(){
				$(this).addClass('active');
			},function(){
				$(this).removeClass('active');
			}).click(function(){
                    //将选中的li的值传递给drown-default,并高亮当前项，隐藏menu列表
                    var select_txt = $(this).html();
                    $(this).addClass('current').siblings('.current').removeClass('current').end()
                        .parents('.dropdown').find('.dropdown-default').html(select_txt).css('color','#333');
                    $(this).parent('.dropdown-menu').hide();
			});
		});
		dropdown.on('mouseleave',function(){
			$(this).css('z-index','0').find('.dropdown-menu').hide();
			$(this).parents('.selectlist-li').css('z-index','0');
		});
		
	}());

/*-------
|
|form placeholder
|
-------*/

	(function(){
		var form_placeholder_wrap = $('.form-placeholder-wrap');
		//遍历form_placeholder_wrap
		form_placeholder_wrap.each(function(){
			var placeholder_input = $(this).find('input, textarea'),
				form_placeholder = $(this).find('.form-placeholder');
			//当placeholder_input有值的时候，form_placeholder隐藏
			if(placeholder_input.val()){
				form_placeholder.hide();
			}
			//当placeholder_input得焦时，form_placeholder隐藏
			placeholder_input.focus(function(){
				form_placeholder.hide();
			}).blur(function(){
			//当placeholder_input失焦时，如果内容为空，form_placeholder显示
				if(this.value == ''){
					form_placeholder.show();
				}
			});
			//点击form_placeholder，触发placeholder_input的得焦事件
			form_placeholder.click(function(){
				placeholder_input.trigger('focus');
			});
		});
	}());

	/*-------
	头部搜索按钮hover事件
	-------*/
	$('.search-btn').hover(function(){$('.searchFillIn').toggleClass('searchFillOn');});
})