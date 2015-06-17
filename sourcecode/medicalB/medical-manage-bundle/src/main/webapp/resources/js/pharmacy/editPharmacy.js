
$(document).ready(function(){
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
			shopName : {
				required : true,
				maxlength : 200
			},
			address : {
				required : true,
				maxlength : 200
			},
			physicsAddress : {
				required : true,
				maxlength : 200
			},
			lng : {
				required : true,
				maxlength : 200
			},
			lat : {
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
   						createTemplate(responseText["pharmacyID"]);
   					}else{
   						alert(responseText["msg"]);
   					}
				}, 
				dataType: "json",
			   type: 'post', 
			   resetForm:false,               
			   url: ctx + "/pharmacy/addOrUpdate"
			});
		}
	});
}

function createTemplate(id){
	$.ajax({
		url:ctx+"/pharmacy/createTemplate",
		type:"post",
		data:{"id":id},
		dataType:"html",
		success:function(data){
			if(data){
				createDetailHtmlFile(id,data);
			}else{
				alert("生成药店详情失败!");
			}
		}
	});
}

function createDetailHtmlFile(id,data){
	$.ajax({
		url:ctx+"/pharmacy/createDetail",
		type:"post",
		data:{"id":id,"detail":data},
		dataType:"json",
		success:function(data){
			if(data.success){
				myAlert('系统提示',data.success);
				window.location.href=ctx+"/pharmacy.do";
			}else if(data.error){
				myAlert('系统提示',data.error);
			}
		},
		error: function() {
			myAlert("系统提示","系统故障，操作失败！");
		}
	});
}

function toupdatePharmacy(id){
	   window.location.href=ctx+'/pharmacy/initAddOrUpdate?shopID='+id; 
}

function showPharmacy(id){
	   window.location.href=ctx+'/pharmacy/showPharmacy?shopID='+id; 
}


function deletePharmacy(id){

	 var url=ctx+'/pharmacy/del';
	 var param='shopID='+id;
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

function onchangeShopName(obj) {
	$("#copyTitle font").text(obj.value);
}

function onchangeDescription(obj) {
	$("#copyContent font").text(obj.value);
}

function onUploadImgChange(sender) {
	$("#_preview_fake").css('display', 'none');
	if (!sender.value.match(/.jpg|.gif|.png|.bmp/i)) {
		alert('图片格式无效！');
		return false;
	}

	var objPreview = document.getElementById('preview');
	var objPreviewFake = document.getElementById('preview_fake');
	var objPreviewSizeFake = document.getElementById('preview_size_fake');

	if (sender.files && sender.files[0]) {
		objPreview.style.display = 'block';
		objPreview.style.width = 'auto';
		objPreview.style.height = 'auto';

		// Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径
		// objPreview.src = sender.files[0].getAsDataURL();
		objPreview.src = window.URL.createObjectURL(sender.files[0]);
	} else if (objPreviewFake.filters) {
		// IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果
		// （相同环境有时能显示，有时不显示），因此只能用滤镜来解决

		// IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径
		sender.select();
		var imgSrc = document.selection.createRange().text;

		objPreviewFake.filters
				.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
		objPreviewSizeFake.filters
				.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;

		autoSizePreview(objPreviewFake, objPreviewSizeFake.offsetWidth,
				objPreviewSizeFake.offsetHeight);
		objPreview.style.display = 'none';
	}
}

function onPreviewLoad(sender) {
	$("#_preview_fake").css('display','none');
	autoSizePreview(sender, sender.offsetWidth, sender.offsetHeight);
}

function autoSizePreview(objPre, originalWidth, originalHeight) {
	var zoomParam = clacImgZoomParam(305, 160, originalWidth, originalHeight);
	if(objPre == null || objPre == undefined){
		return ;
	}
	objPre.style.width = zoomParam.width + 'px';
	objPre.style.height = zoomParam.height + 'px';
//	objPre.style.marginTop = zoomParam.top + 'px';
//	objPre.style.marginLeft = zoomParam.left + 'px';
}

function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		width : width,
		height : height,
		top : 0,
		left : 0
	};

	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = height / rateWidth;
		} else {
			param.width = width / rateHeight;
			param.height = maxHeight;
		}
	}
	param.left = (maxWidth - param.width) / 2;
	param.top = (maxHeight - param.height) / 2;
	return param;
}

