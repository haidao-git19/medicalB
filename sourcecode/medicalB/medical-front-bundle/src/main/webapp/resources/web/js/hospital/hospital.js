$(function() {
	seeConsult();
	seeDoctor();
	initAllSections();
});

/**
 * 查询医院页咨询动态；
 */
function seeConsult() {
	var hospitalID=$("#hosid").val();
	$("#zxtdnr").html("");
	$.ajax({
		url : ctx + "/anon/web/hospitalconsult",
		data:"hospitalID="+hospitalID,
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				$("#zxtdnr").append("暂无记录");
				return ;
			}
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					$("#zxtdnr").append("<tr>");
					$("#zxtdnr").append("<td width=\"60%\"><a href=\"superfawn_g_2375108068\" target=\"_blank\" class=\"blue\" title=\""+a.question+"\">"+a.question+"</a></td>");
					$("#zxtdnr").append("<td class=\"clr-999\" width=\"40%\" style=\"text-align:right;\"><a href=\"DE4rO-XCoLUmy75Bfmw7E-sSlj\" target=\"_blank\" class=\"clr-666\" title=\""+a.doctorName+"\">"+a.attrname+"&nbsp;"+a.doctorName+"</a>&nbsp;回复 </td>");
					$("#zxtdnr").append(" </tr>");
				});
				//$("#zxtdnr").show();
			}
		}
	});
	
}


/**
 * 查询医院页推荐医生；
 */
function seeDoctor() {
	var hospitalID=$("#hosid").val();
	$("#hostjdoc").html("");
	$.ajax({
		url : ctx + "/anon/web/hospitaltjdoctor",
		data:"hospitalID="+hospitalID,
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				$("#hostjdoc").append("暂无记录");
				return ;
			}
			if(data != undefined && data != null){
				$.each(data,function(i, a) {
					$("#hostjdoc").append("<div class=\"comp-right-box cf\">");
					$("#hostjdoc").append("<div class=\"bole-img\"><a href=\"javascript:toDoctorMainPage("+a.doctorID+","+a.hospitalID+","+a.sectionID+");\" target=\"_blank\"><img src=\"../img/nouimg.jpg\" title=\""+a.realName+"\" alt=\"\"></a></div>");
					$("#hostjdoc").append("<ul class=\"bole-info\">");
					$("#hostjdoc").append("<li class=\"fnt-14 clr-08c\"><a class=\" clr-08c fnt-14 \" href=\"javascript:toDoctorMainPage("+a.doctorID+","+a.hospitalID+","+a.sectionID+");\" target=\"_blank\" title=\""+a.realName+"\">"+a.realName+"</a><span  class=\"icon-id\">&nbsp;"+a.doctorLevel+"</span></li>");
					$("#hostjdoc").append("<li class=\"bole-info-zn\" title=\""+a.hospitalName+"."+a.sectionName+"\">"+a.hospitalName+"."+a.sectionName+"</li>");
					$("#hostjdoc").append("</ul></div>");
		            
				});
				//$("#zxtdnr").show();
			}
		}
	});
	
}

function toDoctorMainPage(doctorID,hospitalID,sectionID){
	window.location.href=ctx+"/anon/web/doctor?doctorID="+doctorID+"&hospitalID="+hospitalID+"&sectionID="+sectionID;
}

function initAllSections(){
	var hospitalID=$("#hosid").val();
	$.ajax({
		url : ctx + "/anon/web/queryhosSections",
		data:"hospitalID="+hospitalID,
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''||data.allselist.length==0){
				$("#hos_sections_init_area").empty().append('<table><tbody><tr><td width="50%">暂无记录</td></tr></tbody></table>');
			}else{
				$("#hos_sections_init_area").empty();
				var p_temp="<table><tbody>";
				$.each(data.firstselist,function(i,f){
					var f_temp='<tr><td class="position-title" width="12%">'+f.sectionName+'</td><td><table id="hosbra" border="0" cellpadding="0" cellspacing="0" width="100%"><tbody>';
					
					var f_arr=new Array();
					$.each(data.allselist,function(j,s){
						if(s.sectionParentid==f.sectionParentid){
							f_arr.push(s);
						}
					});
					
					f_temp+="<tr>";
					if(f_arr.length>0){
						var count=0;
						for(var i in f_arr){
							f_temp+='<td width="50%"><a href="${ctx }/anon/web/sectionDetail?hospitalID='+hospitalID+'&sectionID='+f_arr[i].sectionID+'" target="_blank" class="blue">'+f_arr[i].sectionName+'</a> </td>';
							count++;
							if(count==2||i==(f_arr.length-1)){
								f_temp+="</tr><tr>";
								count=0;
							}
						}
					}
					f_temp+="</tr></tbody></table></td></tr>";
					p_temp+=f_temp;
				});
				p_temp+="</tbody></table>";
				$("#hos_sections_init_area").append(p_temp);
			}
		}
	});
}