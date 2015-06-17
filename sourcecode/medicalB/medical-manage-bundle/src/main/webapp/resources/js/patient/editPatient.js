$(document).ready(function(){
	$('#patientSex').val($('#defaultPatientSex').val());
});

function toupdatePatient(id){
	   window.location.href=ctx+'/patient/initAddOrUpdate?patientID='+id; 
}

function showConsult(id){
	   window.location.href=ctx+'/patient/showPatient?patientID='+id; 
}


function deletePatient(id){

	 var url=ctx+'/patient/del';
	 var param='patientID='+id;
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

	 var url=ctx+'/patient/addOrUpdate';
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
            		 window.location.href=ctx+'/patient.do';  
            	}
               }
            })

}


function uploadImg(){
	
	$('#imgFile').click();
}
/**
 * 上传图片
 */	
function uploadFile(){
	 var options = {  
		      dataType : "json",  
		      async: false,
		      success :showResponse,
		      error:showError,
		      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
		  };  
	 jQuery("#imgForm").ajaxSubmit(options);
	
}


//上传成功回调
var showResponse = function(data){
	if(data.flag==true){
		$('#img').val(data.name);
		 $("#showImage").attr("src",ctx+'/showImg?fileName='+data.name);
		 $('#_preview_fake').hide();
	}else{
		alert('上传失败请重试!')
	}
	
}
//上传失败
 var showError = function(data){
    alert('上传失败,请稍后重试!');
 }
 
 function validate(){
		var msg='';
		if($('#patientName').val()==''){
			msg="请填写病人姓名!"
		}else if($('#patientSex').val()==''){
			msg="请选择性别!"
		}else if($('#patientAge').val()==''){
			msg="请填写病人年龄!"
		}else if($('#patientCard').val()==''){
			msg="请填写证件号!"
		}else if($('#contactPhone').val()==''){
			msg="请填写联系方式!"
		}
		return msg;
	}

