$(function(){
	queryGoodsFilter();
});

function queryGoodsFilter(){
	$.ajax({
		url:ctx+"/anon/queryGoodsFilter",
		type:"post",
		data:null,
		dataType:"json",
		success:function(data){
			if(data!=null && data.code=='0'&&data.filter!=null&&data.filter!=''){
					$.each(data.filter,function(i,a){
						var b=a["list"];
						$.each(b,function(j,c){
							var d=c["list"];
							if(d!=null){
								$("#mobile_"+a.id).find("h4").attr("id",a.id+"_Btn");
								$("#mobile_"+a.id+" li ul").css("display","none");
								$("#mobile_"+a.id+" #"+a.id+"_Btn").on('click',function(){
									$(this).parent().find("ul").show("50");
									$(this).parent().siblings().find("ul").hide("50");
								});
								
								$("#mobile_"+a.id+" li ul").find("li").each(function(){
									$(this).click(function(){
										$(this).addClass("fli-selt blue").siblings().removeClass("fli-selt blue");
										$(this).parent().parent().siblings().find("ul").find("li").removeClass("fli-selt blue");
										var text=$(this).clone().children().remove().end().text();
										$(this).empty().append('<span class="fr"><img src="'+ctx+'/resources/third-party/images/icon-ok.png" width="16"></span>'+text+'');
										$("#"+a.id+"_Attr").val(text.trim());
										$("#"+a.id+"_Attr").text(text.trim());
										$(this).siblings().children().remove();
										$(this).parent().parent().siblings().find("ul").find("li").children().remove();
									});
								});
							}else{
								$("#mobile_"+a.id).find("li").each(function(){
									$(this).click(function(){
										$(this).find("h4").attr("class","f18  fli-selt blue").parent().siblings().find("h4").attr("class","f18 gray");
										var text=$(this).find("h4").clone().children().remove().end().text();
										$(this).find("h4").empty().append('<span class="fr"><img src="'+ctx+'/resources/third-party/images/icon-ok.png" width="16"></span>'+text);
										$("#"+a.id+"_Attr").val(text.trim());
										$("#"+a.id+"_Attr").text(text.trim());
										$(this).siblings().find("h4").children().remove();
									});
								});
								
							}
						});
				});
			}
		}
	});
}

//	$("#mobileModel li ul").css("display","none");
//	$("#mobileModel #modelBtn").on('click',function(){
//		$(this).parent().find("ul").show("200");
//		$(this).parent().siblings().find("ul").hide("200");
//	});
//	
//	$("#mobileModel li ul").find("li").each(function(){
//		$(this).click(function(){
//			$(this).addClass("fli-selt blue").siblings().removeClass("fli-selt blue");
//			$(this).parent().parent().siblings().find("ul").find("li").removeClass("fli-selt blue");
//			var text=$(this).clone().children().remove().end().text();
//			$(this).empty().append('<span class="fr"><img src="'+ctx+'/resources/third-party/images/icon-ok.png" width="16"></span>'+text+'');
//			$("#modelAttr").val(text.trim());
//			$("#modelAttr").text(text.trim());
//			$(this).siblings().children().remove();
//			$(this).parent().parent().siblings().find("ul").find("li").children().remove();
//		});
//	});
//	
//	$("#mobileColor").find("li").each(function(){
//		$(this).click(function(){
//			$(this).find("h4").attr("class","f18  fli-selt blue").parent().siblings().find("h4").attr("class","f18 gray");
//			var text=$(this).find("h4").clone().children().remove().end().text();
//			$(this).find("h4").empty().append('<span class="fr"><img src="'+ctx+'/resources/third-party/images/icon-ok.png" width="16"></span>'+text);
//			$("#colorAttr").val(text.trim());
//			$("#colorAttr").text(text.trim());
//			$(this).siblings().find("h4").children().remove();
//		});
//	});
//	
//});

