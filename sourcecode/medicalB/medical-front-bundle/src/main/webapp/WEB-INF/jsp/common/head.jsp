<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="y_head clearfix">
	<div class="site-nav">
		<div class="site-nav-bd">
			<ul class="site-nav-bd-l">
				<li class="menu">
					<div class="menu-hd province_box">
						<span>欢迎访问商城</span>
					</div>
				</li>
			</ul>
			<ul class="site-nav-bd-r">
				<li class="menu">
					<div id="logininfo" class="menu-hd">
						<a target="_self" class="red mr5" href="javascript: login();"
							rel="nofollow" title="登录网上药店后台">请登录</a><a title="注册会员，网上买药更简单"
							target="_self"
							href="http://passport.111.com.cn/sso/register.action"
							rel="nofollow">注册</a>
					</div>
				</li>
				<li class="menu">
					<div class="menu-hd">
						<a rel="nofollow"
							href="http://my.111.com.cn/ucenter/orderList.action"> 我的订单</a>
					</div>
				</li>
				<li class="menu"><div class="menu-hd">
						<a target="_blank"
							href="http://www.111.com.cn/cmsPage/2013073005/index.html"
							rel="nofollow">帮助中心</a>
					</div></li>
				<li class="menu li_none"><div class="menu-hd">
						<i class="iconfont icon-phone">&#xe658;</i>
						<div class="tellnumber">400-007-0958</div>
					</div></li>
				<li class="menu">
					<div class="menu-hd head_app">
						<a target="_blank" class="tell"
							href="http://www.111.com.cn/cmsPage/2013101101/" rel="nofollow"><em></em><span>手机APP</span></a>
						<div class="menu_bd tell_title">&emsp;</div>
						<div id="menu_bd_app">
							<ul>
								<li><span class="dimensional"></span></li>
								<li><a rel="nofollow"
									href="http://www.111.com.cn/cmsPage/2014061304/index.html">APP首单<span
										class="redBold">返10元</span></a></li>
								<li class="appmore"><a href="http://app.111.com.cn/"
									rel="nofollow">更多应用<span class="rightIcon"></span></a></li>
							</ul>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="wrap site_head yao_head">
		<div class="mod_logo">
			<a rel="nofollow" class="logo_jieri" target="_self" href="#"></a>
			<div class="logo">
				<a title="网上药店" href="http://www.111.com.cn">医保宝</a>
			</div>
		</div>
		<div class="mod_tell">
			<p>好医 · 好药 · 好健康</p>
		</div>
		<div class="mod_search">
			<div class="searchForm">
				<label class="combobox-placeholder" id="combobox-placeholder"
					for="word"></label> <input maxlength="100" value="" name="kw"
					id="word" type="text" autocomplete="off" />
				<button class="searchBtn" onclick="search();">搜索</button>
				<div style="display: none;" class="search_tips_result_new"
					id="searchSuggest">
					<ul>
					</ul>
				</div>
			</div>
			<div class="hotkeys">
				<div class="word_box" id="hotkeys"></div>
			</div>
		</div>
	</div>

</div>
<div class="head_wrap clearfix" id="headerNav">
	<div class="headerNavMain">

		<ul class="sitenav" id="sideNav">
			<li class="s_m home"><a href="http://www.111.com.cn/"
				class="nav_link" target="_self">首页</a></li>
			<li class="s_m"><a class="nav_link" data-rel="navHover1">常见病<i></i></a>
				<ul style="display: none;" id="navHover1">
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014030501/">感冒发烧</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014061302/">皮炎癣症</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014062301/">风湿骨病</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014030402/">失眠</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2015013001/">查看更多</a></li>
				</ul></li>
			<li class="s_m"><a class="nav_link" data-rel="navHover2">慢性病<i></i></a>
				<ul style="display: none;" id="navHover2">
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014030301/">肺癌</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014081101/">尖锐湿疣</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014040204/">抑郁症</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014040302/">帕金森</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014121701/">查看更多</a></li>
				</ul></li>
			<li class="s_m"><a class="nav_link" data-rel="navHover3">妈妈&宝宝<i></i></a>
				<ul style="display: none;" id="navHover3">
					<li><a rel="nofollow"
						href="http://www.111.com.cn/list/955412-0-0-0-0-0-0-1.html">面部护理</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2015021102/">减肥美颜</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014090502/">厨房助手</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014010302/">孕妈养成</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014072801/">查看更多</a></li>
				</ul></li>
			<li class="s_m"><a class="nav_link" data-rel="navHover4">女性&男性<i></i></a>
				<ul style="display: none;" id="navHover4">
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014111004/">补肾壮阳</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014051301/">早泄神药</a></li>
					<li><a
						href="http://www.111.com.cn/list/971522-0-0-0-0-0-0-1.html">运动健康</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/list/964106-0-1_93499_138826-0-0-0-0-1.html">解酒护肝</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2015031706/">查看更多</a></li>
				</ul></li>
			<li class="s_m"><a class="nav_link" data-rel="navHover6">保健&器械<i></i></a>
				<ul style="display: none;" id="navHover6">
					<li><a rel="nofollow"
						href="http://www.111.com.cn/list/971495-0-0-0-0-0-0-1.html/">婴儿奶粉</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014082801/">宝宝纸尿裤</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/list/965399-0-0-0-0-0-0-1.html">宝宝喂养</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014050402/">孕育总动员</a></li>
					<li><a rel="nofollow"
						href="http://www.111.com.cn/cmsPage/2014050801/">查看更多</a></li>
				</ul></li>
		</ul>
		</li>

		</ul>
		</li>

		</ul>

		<div class="searchForm" style="display: none">
			<label class="combobox-placeholder" for="fix_keyword"></label> <input
				maxlength="100" value="" name="kw" id="fix_keyword" type="text"
				autocomplete="off" />
			<button class="searchBtn" onclick="javascript:searchTips();;">搜索</button>
			<div style="display: none;" class="search_tips_result_new"
				id="fix_searchSuggest">
				<ul></ul>
			</div>
		</div>
		<div class="mod_minicart">
			<a rel="nofollow" target="_self"
				href="http://buy.111.com.cn/cart/shoppingcart/queryshoppingcart.action"
				class="mini_cart_btn"> <em class="cart_num">0</em> <span>购物车</span>
			</a>
			<div id="minicart_list" style="display: none" class="minicart_list">
				<div class="list_detail">
					<!--购物车有商品时begin-->
					<ul style="display: block;"></ul>
					<div class="checkout_box" style="display: blcok">
						<p>
							<span class="fl"> 共<em class="fstrong">0</em>件商品
							</span> 合计：<em class="fstrong">0.0</em>
						</p>
						<p id="miniCart_p2" style="display: none;">
							<span style="color: gray;"> 满百免运费 </span>
						</p>
						<a rel="nofollow" class="checkout_btn"
							href="http://buy.111.com.cn/cart/shoppingcart/queryshoppingcart.action"
							target="_self"> 去结算 </a>
					</div>
					<div style="display: none;" class="none_tips">
						<i> </i>
						<p>
							您的购物车里还没有商品，如已添加商品，请 <a rel="nofollow"
								href="javascript: login();" target="_self">登录 </a> 。
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--head end-->