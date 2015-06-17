/**
 * 动态tab样式；
 */
function setTabcss(num,length){
	for(i=0;i<length;i++)
	{
		if(i==num)
		{
			$("#jhcon_"+i).show();
	    	$("#jh_"+i).addClass("menu-active");
		}
		else
		{
			$("#jhcon_"+i).hide();
	    	$("#jh_"+i).removeClass("menu-active");
		}
	}
    	
    }
$(function() {
	poster();
	fristlevelsection();
	countShop();
	seeNotices();
	countNurse();
	seeConsult();
	seeArcticle();
	section();
});

/**
 * 查询滚动海报；
 */
function poster() {
	var flexslider = $(".pics").html();
	$(".pics").empty();
	
	$.ajax({
		url : ctx + "/anon/web/posterList",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					$(".pics").append("<li ><a href='"+a.url+"' target='_blank'><img width='710px' src='"+a.image+"' /></a></li>");
				});
			}
			
			$(".pics").show();
			/*鼠标移过，左右按钮显示*/
			$(".pic_banner").hover(function(){
				$(this).find(".prev,.next").fadeTo("show",0.1);
			},function(){
				$(this).find(".prev,.next").hide();
			})
			/*鼠标移过某个按钮 高亮显示*/
			$(".prev,.next").hover(function(){
				$(this).fadeTo("show",0.7);
			},function(){
				$(this).fadeTo("show",0.1);
			})
			$(".pic_banner").slide({ titCell:".num ul" , mainCell:".pics",effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });
			
		}
	});
	
	
}
/**
 * 查询一级科室；
 */
function fristlevelsection() {
	var fistdata="";
	var silegnth;
	var strksstinrg="";
	$("#zxzxks").html("");
	$.ajax({
		url : ctx + "/anon/web/section",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					if(i==0)
					{
						fistdata=a.id;
						silegnth=data.length;
						strksstinrg=strksstinrg+"<a id=\"jh_"+i+"\" class=\"menu-active\" onmouseover=\"seeDoc("+a.id+","+data.length+",0);\" target=\"_blank\" href=\"#\">"+a.attrname+"</a>";
					}
					else
						strksstinrg=strksstinrg+"<a id=\"jh_"+i+"\"  class=\"normal\" onmouseover=\"seeDoc("+a.id+","+data.length+","+i+");\" target=\"_blank\" href=\"#\">"+a.attrname+"</a>";
				});
				$("#zxzxks").html(strksstinrg);
				seeDoc(fistdata,silegnth,0);
			}
			webfristlevelsection(data);
		}
	});
	
}

/**
 * 查询一级科室下面推荐医生；
 */
function seeDoc(secid,length,i)
{
	var basesrc=$("#srcloadhide").val();
	var strdoc="";
	if($("#jhcon_"+i).length>0)
	{
		setTabcss(i,length);
	}
	else
	{
		strdoc="<div id=\"jhcon_"+i+"\" style=\"display: none;\" class=\"menu_con2\">";
		strdoc=strdoc+"<ul class=\"homeTelUl\">";
		$.ajax({
			url : ctx + "/anon/web/secdoc",
			data:"secid="+secid,
			type : "POST",
			dataType : "json",
			success : function(data) {
				if(data == null || data == ''){
					strdoc=strdoc+"暂无数据";
					strdoc=strdoc+"</ul><div class=\"clear\"></div></div>";
					$("#docli").append(strdoc);
				}
				else if(data != undefined && data != null){
					$.each(data,function(i, a) {
						strdoc=strdoc+"<li class=\"homeTelLi\"> ";
						strdoc=strdoc+"<span class=\"homeTelLi_span0\">";
						strdoc=strdoc+"<table><tbody><tr>";
						strdoc=strdoc+"<td><a href=\"http://400.haodf.com/haodf/mdonglai\"><img src=\""+basesrc+a.avatar+"\"></a></td>";
						strdoc=strdoc+"</tr></tbody></table></span>";
						strdoc=strdoc+"<span class=\"homeTelLi_span1\">";
						strdoc=strdoc+"<p> <a class=\"blue2\" href=\"http://400.haodf.com/haodf/mdonglai\">"+a.doctorName+"</a> "+a.doctorLevel+" </p>";
						strdoc=strdoc+"<p>"+a.hospitalName+"</p>";
						strdoc=strdoc+"<p>"+a.sectionName+"</p></span>";
						strdoc=strdoc+"<span class=\"homeTelLi_span1\">";
						strdoc=strdoc+"<p> <a href=\"http://www.haodf.com/wenda/mdonglai_g_1818634760.htm\"> 白癜风 </a>&nbsp; </p>";
						strdoc=strdoc+"<p>最近通话： 04-01 19:51</p>";
						strdoc=strdoc+"<p><a href=\"http://400.haodf.com/haodf/mdonglai#successAnswer\" target=\"_blank\" class=\"blue\">查看最新用户分享 &gt;&gt;</a></p>";
						strdoc=strdoc+"</span> ";
						strdoc=strdoc+" <span class=\"homeTelLi_span4\"> <a class=\"orange3\" href=\"http://mdonglai.haodf.com/payment/servicelist\">立刻预约</a> </span> ";
						strdoc=strdoc+"</li>";
					});
				}
				strdoc=strdoc+"</ul><div class=\"clear\"></div></div>";
				$("#docli").append(strdoc);
				setTabcss(i,length);
			}
		});
		
	}
	

}


/**
 * 查询网上咨询一级科室；
 */
function webfristlevelsection(data) {
	var wsfistdata="";
	var wssilegnth;
	var wsstrksstinrg="";
	$("#wszxks").html("");
	
	if(data == null || data == ''){
		return ;
	}
	if(data != undefined && data != null){
		$.each(data,function(i, a) {
			if(i==0)
			{
				wsfistdata=a.id;
				wssilegnth=data.length;
				wsstrksstinrg=wsstrksstinrg+"<a id=\"wsjh_"+i+"\" class=\"menu-active\" onmouseover=\"seeZx("+a.id+","+data.length+",0);\" target=\"_blank\" href=\"#\">"+a.attrname+"</a>";
			}
			else
				wsstrksstinrg=wsstrksstinrg+"<a id=\"wsjh_"+i+"\"  class=\"normal\" onmouseover=\"seeZx("+a.id+","+data.length+","+i+");\" target=\"_blank\" href=\"#\">"+a.attrname+"</a>";
		});
		$("#wszxks").html(wsstrksstinrg);
		seeZx(wsfistdata,wssilegnth,0);
	}
	
}

/**
 * 动态tab样式；
 */
function setTabwscss(num,length){
	for(i=0;i<length;i++)
	{
		if(i==num)
		{
			$("#zxcon_"+i).show();
	    	$("#wsjh_"+i).addClass("menu-active");
		}
		else
		{
			$("#zxcon_"+i).hide();
	    	$("#wsjh_"+i).removeClass("menu-active");
		}
	}
    	
    }

/**
 * 查询一级科室下面咨询信息；
 */
function seeZx(secid,length,i)
{
	var wsstrdoc="";
	if($("#zxcon_"+i).length>0)
	{
		setTabwscss(i,length);
	}
	else
	{
		wsstrdoc="<div id=\"zxcon_"+i+"\" style=\"display: none;\" >";
		wsstrdoc=wsstrdoc+"<ul >";
		$.ajax({
			url : ctx + "/anon/web/ksconsult",
			data:"secid="+secid,
			type : "POST",
			dataType : "json",
			success : function(data) {
				if(data == null || data == ''){
					wsstrdoc=wsstrdoc+"暂无数据";
					wsstrdoc=wsstrdoc+"</ul></div>";
					$("#zxdoc").append(wsstrdoc);
				}
				else if(data != undefined && data != null){
					$.each(data,function(i, a) {
						wsstrdoc=wsstrdoc+"<li class=\"zx_bg1\">";
						wsstrdoc=wsstrdoc+"<span class=\"gray f_rt\">"+a.hospitalName+a.attrname+"&nbsp;&nbsp;"+a.doctorName+"&nbsp;回复</span>";
						wsstrdoc=wsstrdoc+"<a href=\"http://www.haodf.com/wenda/chensongzyy_g_1836022995.htm\" target=\"_blank\">"+a.question+"</a> ";
						wsstrdoc=wsstrdoc+"<a class=\"gray\" style=\"text-decoration:none;\">"+a.datediff+"</a>";
						wsstrdoc=wsstrdoc+"</li>";
					});
				}
				wsstrdoc=wsstrdoc+"</ul></div>";
				$("#zxdoc").append(wsstrdoc);
				setTabwscss(i,length);
			}
		});
		
	}
	

}
/**
 * 查询入驻药房数量；
 */
function countShop() {
	$.ajax({
		url : ctx + "/anon/web/countshop",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				$("#rzyf").html("0");
			}
			else
			{
				$("#rzyf").html(data);
			}
			
		}
	});
	
}

/**
 * 查询体验服务数量；
 */
function countNurse() {
	$.ajax({
		url : ctx + "/anon/web/countNurse",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				$("#fwsl").html("0");
			}
			else
			{
				$("#fwsl").html(data);
			}
			
		}
	});
	
}

/**
 * 查询网上咨询数量；
 */
function countShop() {
	$.ajax({
		url : ctx + "/anon/web/countWszx",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				$("#zxcounttj").html("0");
			}
			else
			{
				$("#zxcounttj").html(data);
			}
			
		}
	});
	
}
/**
 * 查询首页显示公告；
 */
function seeNotices() {
	$("#ulgonggao").html("");
	$.ajax({
		url : ctx + "/anon/web/notice",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					$("#ulgonggao").append("<li><a title=\""+a.title+"\" href=\"/helpcenter/2015/0121/0000000800.html\">"+a.title+"</a></li>");
				});
				$("#ulgonggao").show();
			}
		}
	});
	
}


/**
 * 查询首页咨询动态；
 */
function seeConsult() {
	//$("#zxtdnr").html("");
	$.ajax({
		url : ctx + "/anon/web/consult",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					$("#zxtdnr").append("<div class=\"r_bar_doc3\">");
					$("#zxtdnr").append("<div class=\"r_bar_doc3_l\">");
					$("#zxtdnr").append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr>");
					$("#zxtdnr").append("<td style=\"padding: 0px; border: 1px solid #DFDFDF; height: 31px; vertical-align: middle; width: 31px;\" align=\"center\" valign=\"middle\"><a target=\"_blank\" href=\"http://chjh76.haodf.com\"><img style=\"border:none;\" src=\"http://n1.hdfimg.com/g3/M00/43/FE/p4YBAFIwhECAAEfZAAAH4eTfEf0542_31_31_1.jpg?02f0\"></a></td>");
					$("#zxtdnr").append("</tr></tbody> </table></div>");
					$("#zxtdnr").append("<div class=\"r_bar_doc3_c\"><span style=\"float:right;\">"+a.datediff+"</span><a href=\"http://chjh76.haodf.com\" class=\"blue2\" target=\"_blank\">"+a.doctorName+"</a> "+a.doctorLevel);
					$("#zxtdnr").append("<br/>"+a.hospitalName+" ");
					$("#zxtdnr").append("回复了患者 <br>");
					$("#zxtdnr").append("</div><div class=\"clear\"></div>");
					$("#zxtdnr").append("</div>");
					  
					
				});
				//$("#zxtdnr").show();
			}
		}
	});
	
}
$(document).ready(function(){
	
	//默认显示合肥本地网医院
	$('#hospitalLatn_551').show();
	$('#hospitalLatn_551').siblings().hide();
	//控制医生选中
	$('#hospitalLatn a').hover( //鼠标滑过下拉列表是改变当前栏目样式 
			function(){ 
				$(this).siblings().addClass('normal').removeClass('menu-active')
				$(this).addClass('menu-active'); 
				var latnId=$(this).attr('latn');
				if($('#hospitalLatn_'+latnId).length<1){
					//暂无数据
					$('#hospitalLatn_none').show();
					$('#hospitalLatn_none').siblings().hide();
				}else{
					$('#hospitalLatn_'+latnId).show();
					$('#hospitalLatn_'+latnId).siblings().hide();
				}
			}, 
			function(){ 
			} 
		); 
	
	
	//控制电话咨询选中
	$('#tel_zx a').hover( //鼠标滑过下拉列表是改变当前栏目样式 
			function(){ 
				$(this).siblings().addClass('normal').removeClass('menu-active')
				$(this).addClass('menu-active'); 
				var latnId=$(this).attr('latn');
				if($('#hospitalLatn_'+latnId).length<1){
					//暂无数据
					$('#hospitalLatn_none').show();
					$('#hospitalLatn_none').siblings().hide();
				}else{
					$('#hospitalLatn_'+latnId).show();
					$('#hospitalLatn_'+latnId).siblings().hide();
				}
			}, 
			function(){ 
			} 
		); 
	
})


//查找医院科室
function section(){
	$("#querySection").html("暂无数据");
	var s="<div class='find_jb_l'>";
	var q;
	$.ajax({
		url : ctx + "/anon/web/sectionandse",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				$("#querySection").html("暂无数据");
				return;
			}
			else
			{
					$.each(data,function(i, a) {
						q=data.length;
						if((i+1)==(Math.round(q/2)+1))
						s=s+"</div><div class='find_jb_r'><strong><strong>"+a.attrname+"</strong><ul>";
						else
							s=s+"<strong>"+a.attrname+"</strong><ul>";
						$.each(a.childlist,function(j, b) {
							s=s+"<li><a target='_blank' class='black_link' href='#' onclick='gotoks("+b.id+")'>"+b.attrname+"</a></li>";
						});
						    s=s+"</ul>";	
						
					});
					$("#querySection").html(s+"</div>");
			}
			
		}
	});
}
//首页科室找大夫点击某个科室传入科室id进入对应页面
function gotoks(id)///输入科室id，进入对应科室页面
{
	
}

/**
 * 查询首页专家文章；
 */
function seeArcticle() {
	$("#zjwz").html("");
	$.ajax({
		url : ctx + "/anon/web/zjarcticle",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					$("#zjwz").append("<li><a href=\"/xulingmin_2371689198.htm\" target=\"_blank\">"+a.title+"</a></li>");
				});
			}
		}
	});
	
}