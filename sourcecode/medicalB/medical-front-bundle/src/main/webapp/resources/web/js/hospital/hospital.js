$(function() {
	seeConsult();
	seeDoctor();
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
					$("#hostjdoc").append("<div class=\"bole-img\">");
					$("#hostjdoc").append("<a href=\"#\" target=\"_blank\">");
					$("#hostjdoc").append("<img src=\"../img/nouimg.jpg\" title=\""+a.doctorName+"\" alt=\"\">");
					$("#hostjdoc").append("</a></div>");
					$("#hostjdoc").append("<ul class=\"bole-info\">");
					$("#hostjdoc").append("<li class=\"fnt-14 clr-08c\"><a class=\" clr-08c fnt-14 \" href=\"#\" target=\"_blank\" title=\""+a.doctorName+"\">"+a.doctorName+"</a><span  class=\"icon-id\">"+a.doctorLevel+"</span></li>");
					$("#hostjdoc").append("<li class=\"bole-info-zn\" title=\""+a.hospitalName+"."+a.sectionName+"\">"+a.hospitalName+"."+a.sectionName+"</li>");
					$("#hostjdoc").append("<li class=\"bole-info-zn\" title=\""+a.hospitalName+"."+a.sectionName+"\">"+a.hospitalName+"."+a.sectionName+"</li>");
					$("#hostjdoc").append("</ul></div>");
		            
				});
				//$("#zxtdnr").show();
			}
		}
	});
	
}