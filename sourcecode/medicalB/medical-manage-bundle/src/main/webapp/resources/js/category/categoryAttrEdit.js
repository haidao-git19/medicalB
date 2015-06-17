$(function(){
	initAttrType();
	$("#submitBtn").on('click',function(){
		submitCategoryAttr();
	});
});

function initAttrType(){
	var attrType=$("#attrType").val();
	if(attrType==null||attrType==''){
		return;
	}
	var atVal=parseInt(attrType);
	var cbArr=$("input[type='checkbox']");
	var sum=0;
	$.each(cbArr,function(i,cb){
		sum+=parseInt($(cb).val());
	});
	if(atVal==sum){
		$.each(cbArr,function(i,cb){
			$(cb).attr("checked",true);
		});
	}else{
		for(var i=0;i<cbArr.length;i++){
			var aaArr=new Array();
			aaArr[0]=parseInt($(cbArr[i]).val());
			for(var j=i+1;j<cbArr.length;j++){
				if(atVal==aaArr[0]){
					$(cbArr[i]).attr("checked",true);
					break;
				}else{
					if(atVal==(aaArr[0]+parseInt($(cbArr[j]).val()))){
						$(cbArr[i]).attr("checked",true);
						$(cbArr[j]).attr("checked",true);
						break;
					}
				}
			}
		}
	}
	
	
}

function submitCategoryAttr(){
	var attrType=0;
	$.each($("input[type='checkbox']:checked"),function(i,c){
		attrType+=parseInt($(c).val());
	});
	$("#attrType").val(attrType);
	
	$.ajax({
		url:ctx+"/categoryAttr/addOrUpdate",
		type:"post",
		data:$("#categoryAttr_edit_form").serialize(),
		dataType:"json",
		async:false,
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