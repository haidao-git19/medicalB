//实例化编辑器


var ue = UE.getEditor('editor');
$(function() {	
	
	var type=$("#type").val();
	if(type){
		$("#typeSelect").find("option[value='"+type+"']").attr("selected","selected").siblings().removeAttr("selected");
	}
	
	$("#contentSaveBtn").on('click',function(){
		$("#content_form").submit();
	});
	initContentValidate();
});

function initContentValidate() {
	$("#content_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			title : {
				required : true
			},
			type : {
				required : true
			},
			text : {
				required : true
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
   					getTemplate(responseText.id);
   					//showMsg();
				},      				                  
			   type: 'post', 
			   resetForm:false,               
			   url: ctx + "/content/contentSave"
			});
		}
	});
}

function getTemplate(id) {

	$.ajax({
		url : ctx + "/content/template",
		type : "POST",
		dataType : 'html',
		data : null,
		async:false,
		success : function(data) {
			if(data != null && data != ''){
				createDetailHtmlFile(id,data);
			}
		},
		error: function(msg) {
			alert("系统故障，操作失败！");
		}
	});
}

function createDetailHtmlFile(id,data) {
	var text = ue.getContent();

	text = text.replace(/(^\s+)|(\s+$)/g,"");//去掉前后空格；
	/*var rex = new RegExp("</?[p|P][^>]*>");
	content = content.replace(rex,'')//去掉P标签；
*/	 
	data = data.format(text);
	
	$.ajax({
		url : ctx + "/content/detail",
		type : "POST",
		dataType : 'json',
		data : {"id":id,"detail":data},
		success : function(data) {
			myAlert('系统提示', '内容添加成功！');
			ue.setContent('');
			//window.location.href = ctx + "/content/contentList";
		}
	});
	
}

function onchangeTitle(obj) {
	$("#copyTitle font").text(obj.value);
}

function onchangeSummary(obj) {
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

function validate(formData, jqForm, options){
	$.blockUI({ 
		message: '<h4>正在提交，请等待...................</h4>'
	})	
}

function showMsg(){
	myAlert('系统提示',"操作成功!");
	window.location.href=ctx+"/content/contentList";
}