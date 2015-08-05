
$(document).ready(function(){
//加载本地网
	$.ajax({
		url:ctx+"/area/handleAllArea",
		async:false,
		dataType:"json",
		success:function(data){
			$("#latnId").html("<option value=''>--选择城市--</option>");
			if(data){
				for(var i in data){
					$("#latnId").append("<option value='"+data[i].areaID+"'>"+data[i].areaName+"</option>");
				}
			}
		}
	});
	
	$('#latnId').val($('#latnId2').val());
	
	$("#submitBtn").on('click',function(){
		$("#postForm").submit();
	});
	initPharmacyValidate();
});

function initPharmacyValidate() {
	$("#postForm").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			companyName : {
				required : true,
				maxlength : 200
			},
			address : {
				required : true,
				maxlength : 200
			},
			loginAccount : {
				required : true,
				maxlength : 200
			},
			loginPwd : {
				required : true,
				maxlength : 200
			}
		},
		showErrors : function(errorMap, errorList) {
			$(this.currentForm).find("span.error").remove();
			if (errorList.length > 0) {
				var err = errorList[0];
				var el = $(err.element);
				var target = el.parentsUntil('form', '.controls');
				target.append("<span class='error'>{0}</span>".format(err.message));
			}
		},	submitHandler: function(form) {
			$(form).ajaxSubmit({
   				beforeSubmit: beforeSumbit,  //提交前的回调函数  
   				complete : function() {
   					$.unblockUI();	
				},					  
   				success: function(responseText, statusText) {
   					if(responseText["flag"]&&responseText["flag"]==true){
   						showMsg();
   						window.location.href=ctx+'/company.do'; 
   					}else{
   						alert(responseText["msg"]);
   					}
				}, 
				dataType: "json",
			   type: 'post', 
			   resetForm:false,               
			   url: ctx + "/company/addOrUpdate"
			});
		}
	});
}


function toupdateCompany(id){
	   window.location.href=ctx+'/company/initAddOrUpdate?companyId='+id; 
}

function showCompany(id){
	   window.location.href=ctx+'/company/showCompany?companyId='+id; 
}


function deleteCompany(id){

	 var url=ctx+'/company/del';
	 var param='companyId='+id;
	 if(confirm("确定要删除?")){
		 //ajax 删除事件
			$.ajax({
					type: "post",
		            url: url,
		            data: param,
		            dataType: "json",
		            success: function(data){
		            	alert('删除成功');
		            	location.reload();
		               }
		   });
	 }

}

function beforeSumbit(){

	var msg=validate();
	if(msg!=''){
		alert(msg);
		return false;
	}
	
}

 /**
  * 表单提交验证
  */
 function validate(){
	 var msg="";
	 
	 if($('#shopName').val()==''){
		 msg="请填写药店名称!";
	 }else if($('#loginAccount').val()==''){
		 msg="请填写登录名称!";
	 }else if($('#loginPwd').val()==''){
		 msg="请填写登录密码!";
	 }
//	 else if($('#code').val()==''){
//		 msg="请填写药店编码!";
//	 }
	 else if($('#areaID').val()==''){
		 msg="请选择地市!";
	 }else if($('#address').val()==''){
		 msg="请填写地址!";
	 }else if($('#physicsaddress').val()==''){
		 msg="请填写地址!";
	 }
	 return msg;
 }
 
 function _validate(formData, jqForm, options){
		$.blockUI({ 
			message: '<h4>正在提交，请等待...................</h4>'
		})	
	}

function showMsg(){
	alert("操作成功!");
}

function onchangeShopName(obj) {
	$("#copyTitle font").text(obj.value);
}

function onchangeDescription(obj) {
	$("#copyContent font").text(obj.value);
}

