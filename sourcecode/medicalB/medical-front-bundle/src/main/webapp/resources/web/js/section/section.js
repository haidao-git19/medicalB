$(function(){
	initSectionList();
})

function initSectionList(){
	$.ajax({
		url:ctx+"/anon/web/querySectionList",
		type:"post",
		data:null,
		dataType:"json",
		success:function(data){
			if(data){
				$.each(data,function(i,ps){
					
					$(".com-right-num").append(' <li><a rel="nofollow" target="_blank" href="javascript:toLocatePart('+ps.id
							+');" class="clr-08c"><span class="clr-08c">'+ps.attrname+' </span></a></li>');
					
					var csContentTemp='<div class="ctg-box cf" style="display:none" id="p_section_'+ps.id
						+'"><div class="ctg-title"><span class="ctg-titleL">'+ps.attrname+'</span></div><div class="find-departments"><ul><ul>';
					
					if(ps.list){
						$.each(ps.list,function(j,cs){
							csContentTemp+='<li><a href="#" title="1469家推荐医院,2600位大夫提供在线咨询" class="black_link">'+cs.attrname+'</a></li>';
						});
					}
					
					csContentTemp+='</ul></ul></div></div>';
					$(".comp-detail-left").append(csContentTemp);
				
				});
				$("#p_section_"+data[0].id).show();
			}
		}
	});
}

function toLocatePart(pid){
	$("#p_section_"+pid).show().siblings().hide();
}