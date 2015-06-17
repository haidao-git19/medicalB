$(document).ready(function(){
	initFirstLevelSection();
	initAllGroupSection();
	queryCheckedSection();
//加载本地网
	$.ajax({
		url:ctx+"/area/handleAllArea",
		async:false,
		dataType:"json",
		success:function(data){
			$("#areaID").html("<option value=''>--选择城市--</option>");
			if(data){
				for(var i in data){
					$("#areaID").append("<option value='"+data[i].areaID+"'>"+data[i].areaName+"</option>");
				}
			}
		}
	});
	
	$('#areaID').val($('#latnId').val());
	$('#isCooperation').val($('#defaultCooperation').val());
	
	$("#areaID").change(function(){
		var areaName=$("#areaID").children("option:selected").text();
		$("#areaName").val(areaName);
	});
});

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
					$("#parentSectionArea").append("<input type='hidden' id='parentSection_"+s.id+"'>")
				});
			}
		}
	});
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
}

function toupdateHospital(id){
	   window.location.href=ctx+'/hospital/initAddOrUpdate?hospitalID='+id; 
}

function showHospital(id){
	   window.location.href=ctx+'/hospital/showHospital?hospitalID='+id; 
}


function deleteHospital(id){

	 var url=ctx+'/hospital/del';
	 var param='hospitalID='+id;
	 if(confirm("确定要删除?")){
		 //ajax 删除事件
			$.ajax({
					type: "post",
		            url: url,
		            data: param,
		            dataType: "json",
		            success: function(data){
		            	alert('删除成功');
		            	location.reload()
		               }
		            })
	 }

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
