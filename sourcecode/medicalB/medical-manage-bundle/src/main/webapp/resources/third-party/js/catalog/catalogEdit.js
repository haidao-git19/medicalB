$(function() {	
	
	queryAttrValues();
	queryGoodsList();
	
	var seq=$("#seq").val();
	if(seq){
		$("#seqSelect").find("option[value='"+seq+"']").attr("selected","selected").siblings().removeAttr("selected");
	}
	
	var catalogCode=$("#catalogCode").val();
	if(catalogCode){
		$("#catalogCode").attr("readonly","readonly");
	}else{
		$("a[id^='viewPic']").css("display","none");
	}
	
	$("#catalogSaveBtn").on('click',function(){
		$("#catalog_form").submit();
	});
	initCatalogValidate();
});

function initCatalogValidate() {
	$("#catalog_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			catalogName : {
				required : true
			},
			catalogCode : {
				required : true
			},
			attrValue : {
				required : true
			},
			upload0:{
				required : true
			},
			goodsCode_0:{
				required : true
			},
			seq:{
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
   					showMsg();
				},      				                  
			   type: 'post', 
			   resetForm:false,               
			   url: ctx + "/catalog/catalogGoodsSave"
			});
		}
	});
}

function queryAttrValues(){
	$.ajax({
		url:ctx+"/catalog/queryAttrValues",
		type:"post",
		data:null,
		dataType:"json",
		success:function(data){
			var temp=$("#attrValueSelect").html();
			$("#attrValueSelect").empty();
			$("#attrValueSelect").append("<option value=''>---请选择风格---</option>");
			if(data){
				$.each(data,function(i,filter){
					var _temp=temp.format(filter.attrvalue,filter.attrvalue);
					$("#attrValueSelect").append(_temp);
				});
			}
			$("#attrValueSelect").show();
			var attrValue=$("#attrValue").val();
			if(attrValue){
				$("#attrValueSelect").find("option[value='"+attrValue+"']").attr("selected","selected").siblings().removeAttr("selected");
			}
		}
	})
}

function queryGoodsList(){
	$.ajax({
		url:ctx+"/catalog/queryGoodsList",
		type:"post",
		data:null,
		dataType:"json",
		success:function(data){
			var temp=$("#goods_0Select").html();
			$("select[name^='goods_']").empty();
			$("select[name^='goods_']").append("<option value=''>---请选择商品---</option>");
			if(data){
				$.each(data,function(i,goods){
					var _temp=temp.format(goods.goodsCode+"_"+goods.goodsVersion,goods.goodsName);
					$("select[name^='goods_']").append(_temp);
				});
			}
			var goodsJQArr=$("input[name^='goods_']");
			$.each(goodsJQArr,function(i,g){
				var val=g.value;
				if(val){
					$("#goods_"+i+"Select").find("option[value='"+val+"']").attr("selected","selected").siblings().removeAttr("selected");
				}
			});
		}
	})
}

function onchangeCatalogGoods(obj) {
	var value=obj.value;
	var arr=value.split("_");
	$("#copyTitle font").text("商品编号："+arr[0]);
	$("#copyContent font").text("商品版本:"+arr[1]);
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
	window.location.href=ctx+"/catalog/catalogList";
}