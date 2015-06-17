$(function(){
	if(parentSectionID&&childSectionID&&diseaseID){
		initParentSectionList();
		$("#parentSection").find("option[value="+parentSectionID+"]").attr("selected","selected");
		queryChildSectionList(parentSectionID);
		$("#childSection").find("option[value="+childSectionID+"]").attr("selected","selected");
		queryDiseaseList(childSectionID);
		$("#disease").find("option[value="+diseaseID+"]").attr("selected","selected");
	}else{
		initParentSectionList();
		queryChildSectionList('');
		queryDiseaseList('');
	}
	
	var type=$("#type").val();
	if(type=="0"){
		$("#parentSection,#childSection,#disease").each(function(i,s){
			$(s).attr("disabled","disabled");
		});
	}
});

function initParentSectionList(){
	$.ajax({
		url:ctx+"/section/querySectionList",
		type:"post",
		data:{"level":"1","parentid":"0"},
		dataType:"json",
		async:false,
		success:function(data){
			$("#parentSection").empty().append("<option value=''>--选择科室--</option>");
			if(data.sectionList){
				$.each(data.sectionList,function(i,s){
					$("#parentSection").append("<option value='"+s.id+"'>"+s.attrname+"</option>");
				});
			}
		}
	});
}

function queryChildSectionList(parentid){
	$.ajax({
		url:ctx+"/section/querySectionList",
		type:"post",
		data:{"level":"2","parentid":parentid},
		dataType:"json",
		async:false,
		success:function(data){
			$("#childSection").empty().append("<option value=''>--选择科室--</option>");
			if(data.sectionList){
				$.each(data.sectionList,function(i,s){
					$("#childSection").append("<option value='"+s.id+"'>"+s.attrname+"</option>");
				});
			}
		}
	});
}

function queryDiseaseList(sectionID){
	$.ajax({
		url:ctx+"/disease/queryListBySectionID",
		type:"post",
		data:{"sectionID":sectionID},
		dataType:"json",
		async:false,
		success:function(data){
			$("#disease").empty().append("<option value=''>--选择病种--</option>");
			if(data){
				$.each(data,function(i,d){
					$("#disease").append("<option value='"+d.diseaseID+"'>"+d.diseaseName+"</option>");
				});
			}
		}
	});
}

function onchangeType(obj){
	if(obj.value=='0'){
		$("#parentSection").find("option[value='']").attr("selected","selected");
		$("#childSection").empty().append("<option value=''>--选择科室--</option>");
		$("#disease").empty().append("<option value=''>--选择病种--</option>");
		
		$("#parentSection,#childSection,#disease").each(function(i,s){
			$(s).attr("disabled","disabled");
		});
	}else if(obj.value=='1'){
		$("#parentSection,#childSection,#disease").each(function(i,s){
			$(s).removeAttr("disabled");
		});
	}
}

function onchangeParentSection(obj){
	if(obj.value!=''){
		queryChildSectionList(obj.value);
		$("#disease").empty().append("<option value=''>--选择病种--</option>");
	}else{
		$("#childSection").empty().append("<option value=''>--选择科室--</option>");
		$("#disease").empty().append("<option value=''>--选择病种--</option>");
	}
}

function onchangeChildSection(obj){
	if(obj.value!=''){
		queryDiseaseList(obj.value);
	}else{
		$("#disease").empty().append("<option value=''>--选择病种--</option>");
	}
}

function formSumbit(){
	
	var title=$.trim($("#title").val());
	var publisher=$.trim($("#publisher").val());
	var diseaseID=$.trim($("#disease").val());
	if(!title){
		alert("标题必填");
		return;
	}
	if(!publisher){
		alert("发布人必填");
		return;
	}
	
	$.ajax({
		url:ctx+"/recovery/saveOrUpdate",
		type:"post",
		data:$("#postForm").serialize(),
		dataType:"json",
		async:false,
		success:function(data){
			if(data.flag==true){
				createTemplate(data.id);
			}else{
				myAlert("系统提示","操作失败!");
			}
		}
	});
}

function createTemplate(id){
	$.ajax({
		url:ctx+"/recovery/createTemplate",
		type:"post",
		data:{"id":id},
		dataType:"html",
		success:function(data){
			if(data){
				createDetailHtmlFile(id,data);
			}
		}
	});
}

function createDetailHtmlFile(id,data){
	$.ajax({
		url:ctx+"/recovery/createDetail",
		type:"post",
		data:{"id":id,"detail":data},
		dataType:"json",
		success:function(data){
			if(data.success){
				myAlert('系统提示',data.success);
				UE.getEditor('editor').setContent('');
				window.location.href=ctx+"/recovery";
			}else if(data.error){
				myAlert('系统提示',data.error);
			}
		},
		error: function() {
			myAlert("系统提示","系统故障，操作失败！");
		}
	});
}
