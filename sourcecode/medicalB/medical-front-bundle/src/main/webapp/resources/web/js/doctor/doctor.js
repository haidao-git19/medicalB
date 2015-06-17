$(function(){
	initLatestConsultList();
});

function initLatestConsultList(){
	$.ajax({
		url:ctx+"/anon/web/queryLatestConsultQuestionList",
		type:"post",
		data:{"hospitalID":hospitalID,"sectionID":sectionID,"doctorID":doctorID},
		dataType:"json",
		success:function(data){
			if(data){
				var limitLen=30;
				$.each(data,function(i,c){
					var question='';
					if(c.question){
						if(c.question.length>limitLen){
							question=c.question.substring(0,limitLen)+'...';
						}else{
							question=c.question;
						}
					}
					$("#latestConsultContainer table tbody").append('<tr><td width="60%"><a href="superfawn_g_2375108068" target="_blank" class="blue" title="'+c.question+'">'+question+'</a></td><td class="clr-999" width="40%" style="text-align:right;"><a href="DE4rO-XCoLUmy75Bfmw7E-sSlj" target="_blank" class="clr-666" title="'+c.doctorName+'">'+c.sectionName+'&nbsp;'+c.doctorName+'</a>&nbsp;回复 </td></tr>');
				});
			}else{
				$("#latestConsultContainer table tbody").append('<tr><td width="60%">暂无数据</td></tr>');
			}
		}
	});
}