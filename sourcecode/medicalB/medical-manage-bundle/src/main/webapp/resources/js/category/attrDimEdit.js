$(function(){
	$("#submitBtn").on("click",function(){
		submitAttrDim();
	});
});

function submitAttrDim(){
	$.ajax({
		url:ctx+"/categoryAttr/addOrUpdateAttrDim",
		type:"post",
		data:$("#attr_dim_form").serialize(),
		dataType:"json",
		success:function(data){
			if(data.flag==true){
				myAlert("系统提示","操作成功!");
				$("#submitBtn").attr("disabled",true);
			}else{
				myAlert("系统提示","操作失败!");
			}
		}
	});
}