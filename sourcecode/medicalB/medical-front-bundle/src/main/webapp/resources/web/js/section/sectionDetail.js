$(function(){
	initIsConsultDoctorList();
	initRecomDoctorList();
	initLatestConsultQuestionList();
	initDoctorDutyList();
});

function initIsConsultDoctorList(){
	$.ajax({
		url:ctx+"/anon/web/queryIsConsultDoctorList",
		type:"post",
		data:{"hospitalID":hospitalID,"sectionID":sectionID},
		dataType:"json",
		success:function(data){
			if(data){
				$.each(data,function(i,d){
					var isConsultTemp=$("#isConsultTemp").html();
					$(".wlr-box-bd").append(isConsultTemp.format(d.avatar,d.doctorName,d.doctorLevel,d.hospitalName,d.sectionName));
				});
			}else{
				$(".wlr-box-bd").append("暂无数据");
			}
		}
	});
}

function initLatestConsultQuestionList(){
	$.ajax({
		url:ctx+"/anon/web/queryLatestConsultQuestionList",
		type:"post",
		data:{"hospitalID":hospitalID,"sectionID":sectionID},
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

function initRecomDoctorList(){
	$.ajax({
		url:ctx+"/anon/web/queryRecomDoctorList",
		type:"post",
		data:{"hospitalID":hospitalID,"sectionID":sectionID},
		dataType:"json",
		success:function(data){
			if(data){
				$.each(data,function(i,D){
					var recomDoctorTemp='<tr><td><a href="/mianjijingluan" class="clr-org" target="_blank">{0}</a><span class="clr-ccc">：</span>'.format(D.diseaseMap.diseaseName);
					if(D.doctorList){
						$.each(D.doctorList,function(j,d){
							recomDoctorTemp+=' <a href="javascript:toDoctorMainPage({0})" target="_blank" class="blue" title="{1}">{1}</a>,'.format(d.doctorID,d.doctorName);
							if(j==8){
								return false;
							}
						});
						recomDoctorTemp+='<a href="/" target="_blank" class="blue">更多&gt;&gt;</a></td></tr>';
						$("#recomDoctorsContainer table tbody").append(recomDoctorTemp);
					}
				});
			}
		}
	});
}

function toDoctorMainPage(doctorID){
	window.location.href=ctx+"/anon/web/doctor?doctorID="+doctorID+"&hospitalID="+hospitalID+"&sectionID="+sectionID;
}

function initDoctorDutyList(){
	$.ajax({
		url:ctx+"/anon/web/queryDoctorDutyList",
		type:"post",
		data:{"hospitalID":hospitalID,"sectionID":sectionID},
		dataType:"json",
		success:function(data){
			if(data){
				$.each(data,function(i,d){
					var doctorDutyTemp=$("#doctorDutyContainer table tbody").html();
					
					var len=34;
					var skill='';
					if(d.skill){
						if(d.skill.length>len){
							skill=d.skill.substring(0,len)+"...";
						}else{
							skill=d.skill;
						}
					}
					$(".doctor-table table tbody").append(doctorDutyTemp.format(d.doctorID,d.doctorName,d.doctorLevel,skill))
				});
			}
		}
	})
}