function addAttrClass(){
	var attrClassName=$.trim($("#_attrClassName").val());
	if(!attrClassName){
		$("#errorSpan").empty().text("必填字段!");
		return;
	}
	var _categoryCode;
	if(typeof categoryCode=="undefined"||!categoryCode){
		 _categoryCode=$("#_categoryCode").val();
	}else{
		_categoryCode=categoryCode;
	}
	var _attrClassId=$("#_attrClassId").val();
	$("#errorSpan").empty();
	$.ajax({
		url:ctx+"/categoryAttr/addOrUpdateAttrClass",
		type:"post",
		data:{"categoryCode":_categoryCode,"attrClassName":attrClassName,"id":_attrClassId},
		dataType:"json",
		success:function(data){
			if(data.flag==true){
				closeModal();
				location.reload();
			}else{
				myAlert("系统提示","操作失败!");
			}
		}
	});
}

function closeModal(){
	$("#modal_parameter").modal("hide");
}
