$(document).ready(function(){
	initFirstLevelSection();
	initAllGroupSection();
	queryCheckedSection();
	//加载省份直辖市
	loadProvinceAreaList(0);

//	//加载本地网
//	$.ajax({
//		url:ctx+"/area/handleAllArea",
//		async:false,
//		dataType:"json",
//		success:function(data){
//			$("#areaID").html("<option value=''>--选择城市--</option>");
//			if(data){
//				for(var i in data){
//					$("#areaID").append("<option value='"+data[i].areaID+"'>"+data[i].areaName+"</option>");
//				}
//			}
//		}
//	});
	if($("#p_latnId").val()){
		$("#p_areaID").val($("#p_latnId").val());
		loadCityAreaList($("#p_latnId").val());
		$('#areaID').val($('#latnId').val());
	}
	
	$('#isCooperation').val($('#defaultCooperation').val());
	
	$("#areaID").change(function(){
		var areaName=$("#areaID").children("option:selected").text();
		$("#areaName").val(areaName);
	});
});

function loadProvinceAreaList(parentID){
	$.ajax({
		url:ctx+"/area/queryAreasByParams",
		type:"post",
		data:{"parentID":parentID},
		async:false,
		dataType:"json",
		success:function(data){
			$("#p_areaID").html("<option value=''>--选择省--</option>");
			$("#areaID").empty().append("<option value=''>--选择市--</option>");
			if(data){
				for(var i in data){
					$("#p_areaID").append("<option value='"+data[i].areaID+"'>"+data[i].areaName+"</option>");
				}
			}
		}
	});
}

function loadCityAreaList(value){
	if(value!=null&&value!=''){
		//加载本地网
		$.ajax({
			url:ctx+"/area/queryAreasByParams",
			type:"post",
			data:{"parentID":value},
			async:false,
			dataType:"json",
			success:function(data){
				$("#areaID").html("<option value=''>--选择市--</option>");
				if(data){
					for(var i in data){
						$("#areaID").append("<option value='"+data[i].areaID+"'>"+data[i].areaName+"</option>");
					}
				}
			}
		});
	}else{
		$("#areaID").empty().append("<option value=''>--选择市--</option>");
	}
}

function initFirstLevelSection(){
	$.ajax({
		url:ctx+"/section/querySectionList",
		type:"post",
		data:{"level":"1","parentid":"0"},
		dataType:"json",
		async:false,
		success:function(data){
			if(data.sectionList){
				$.each(data.sectionList,function(i,s){
					$("#firstLevel").append("<option value='"+s.id+"'>"+s.attrname+"</option>");
					$("#parentSectionArea").append("<input type='hidden' id='parentSection_"+s.id+"'>");
					
					var introTemp=$("#introductionTemplate").html();
					var _introTemp=introTemp.format(s.id,s.attrname);
					$("#introductionContainer").append(_introTemp);
					initSectionIntroduction(s.id);
				});
			}
		}
	});
}

function initSectionIntroduction(sectionid){
	if($("#hospitalID").val()){
		$.ajax({
			url:ctx+"/section/querySectionIntroduction",
			type:"post",
			data:{"hospitalID":$("#hospitalID").val(),"sectionID":sectionid},
			dataType:"json",
			async:false,
			success:function(data){
				if(data&&data.section){
					$("#introduction_"+sectionid).val(data.section.introduction);
				}
			}
		});
	}
}

function initAllGroupSection(){
	$.ajax({
		url:ctx+"/section/queryGroupSectionList",
		type:"post",
		data:null,
		dataType:"json",
		async:false,
		success:function(data){
			if(data){
				$.each(data,function(i,d){
					var temp="<ul id='operArea_"+d.parentid+"' class='operArea' style='display:none;'>";
					if(d.childList){
						for(var j in d.childList){
							temp+="<li><input type='checkbox' name='sectionList' value='"+d.childList[j].id+"'>"+d.childList[j].attrname+"</li>"
						}
					}
					temp+="</ul>";
					$("#sectionContainer").append(temp);
				});
			}
		}
	});
}

function queryCheckedSection(){
	$.ajax({
		url:ctx+"/hospital/queryCheckedSection",
		type:"post",
		data:{"hospitalID":$("#hospitalID").val()},
		dataType:"json",
		async:false,
		success:function(data){
			if(data.relatedSectionList){
				$.each(data.relatedSectionList,function(i,R){
					$("input:checkbox[value='"+R.sectionID+"']").attr("checked","true");
				});
			}
		}
	});
}

function onchangeParentLevel(obj){
	var operAreaObj=$("#operArea_"+obj.value);
	if(operAreaObj){
		$(operAreaObj).siblings().hide();
		$(operAreaObj).show();
	}
	
	if(obj.value!=''&&typeof(obj.value)!='undefined'&&obj.value!=null){
		$("#intro_"+obj.value).show().siblings().hide();
	}else{
		$("div[id^='intro_']").hide();
	}
}

function showHospital(id){
	   window.location.href=ctx+'/hospital/showHospital?hospitalID='+id; 
}


function formSumbit(){
	
	//表单验证
	var msg=validate();
	if(msg!=''){
		alert(msg);
		return ;
	}
	
	var summary=$.trim(UE.getEditor('editor').getContentTxt());
	$("#summary").val(summary);
	
	var CBArray=$("input[type='checkbox']:checked");
	$.each(CBArray,function(i,CB){
		var parentidStr=$(CB).parent().parent().attr("id");
		var parentid=parentidStr.substring(9);
		$("#parentSection_"+parentid).attr("name","sectionList").val(parentid);
	});
	
	 var url=ctx+'/hospital/addOrUpdate';
	 //ajax form 提交
	$.ajax({
			type: "post",
            url: url,
            data: $('#postForm').serialize(),
            dataType: "json",
            async:false,
            success: function(data){
            	if(data.flag==false){
            	    alert(data.msg);
            	}else{
            		//转向列表
            		 window.location.href=ctx+'/hospital.do';  
            	}
               }
            })

}

function validate(){
	var msg='';
	if($('#hospitalName').val()==''){
		msg="请填写医院名称!"
	}else if($('#linkMan').val()==''){
		msg="请填写联系人!"
	}else if($('#linkPhone').val()==''){
		msg="请填写联系方式!"
	}else if($('#hospitalLevel').val()==''){
		msg='请选择医院等级!';
	}else if($('#areaID').val()==''){
		msg='请选择本地网!';
	}else if($('#address').val()==''){
		msg='请填写医院地址!';
	}else if($("#type").val()==''){
		msg='请选择医院类型!';
	}
	return msg;
}


//地图

var baiduMapDialog = null;
function pitchBaiduMap(){
	$.ajax( {
		url : ctx + '/hotel/invokenBaiduMap',
		type : 'post',
		async:false,
		dataType : 'html',
		success : function(data) {
			baiduMapDialog = art.dialog({
				title:'地图选择',
				width:640,
				height:480,
				content: data,
				fixed: true,
				lock: true
			});
		}
	});
}
