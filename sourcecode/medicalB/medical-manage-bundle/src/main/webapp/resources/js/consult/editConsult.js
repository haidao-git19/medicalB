$(document).ready(function(){
	$('#isFree').val($('#defaultIsFree').val());
});

function toupdateConsult(id){
	   window.location.href=ctx+'/consult/initAddOrUpdate?consultationID='+id; 
}

function showConsult(id){
	   window.location.href=ctx+'/consult/showConsult?consultationID='+id; 
}


function deleteConsult(id){

	 var url=ctx+'/consult/del';
	 var param='consultationID='+id;
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

	var msg=validate();
	
	if(msg!=''){
		alert(msg);
		return;
	}
	
	 var url=ctx+'/consult/addOrUpdate';
	 //ajax form 提交
	$.ajax({
			type: "post",
            url: url,
            data: $('#postForm').serialize(),
            dataType: "json",
            success: function(data){
            	if(data.flag==false){
            	    alert(data.msg);
            	}else{
            		//转向列表
            		 window.location.href=ctx+'/consult.do';  
            	}
               }
            })

}

function validate(){
	var msg='';
	if($('#question').val()==''){
		msg="请填写咨询问题!"
	}
	return msg;
}
