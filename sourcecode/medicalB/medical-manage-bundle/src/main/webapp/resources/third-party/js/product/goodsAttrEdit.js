$(function() {
	$("#goodsAttrSaveBtn").on("click",function(){
		var $attrValue=$.trim($("#attrValue").val());
		if(!$attrValue){
			return;
		}
		$("#goods_attr_form").submit();
	})
	initValidate();
});

function initValidate() {
	$("#goods_attr_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			attrCode : {
				required : true,
				maxlength : 32
			},
			attrValue : {
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
   				beforeSubmit: validate,  //提交前的回调函数  
   				complete : function() {
   					$.unblockUI();	
				},					  
   				success: function(responseText, statusText) {
   					showMsg();
   					$("#goodsAttr_modal").modal("hide");
   					queryGoodsAttrList();
				},      				                  
			   type: 'post', 
			   resetForm:true,               
			   url: ctx + "/product/goodsAttrSave"
			});
		}
	});
	
}

function validate(formData, jqForm, options){
	$.blockUI({ 
		message: '<h4>正在提交，请等待...................</h4>'
	})	
}

function showMsg(){
	myAlert('','操作成功!');
}