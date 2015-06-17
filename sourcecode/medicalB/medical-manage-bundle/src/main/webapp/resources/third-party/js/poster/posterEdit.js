$(function() {
	
	queryDoctorList();
	
	var type=$("#type").val();
	if(type){
		$("#typeSelect").find("option[value='"+type+"']").attr("selected","selected").siblings().removeAttr("selected");
		if(type=="0"){
			$("#goodsInfoSelect").attr("disabled","disabled");
			$("#url").removeAttr("readonly");
		}else if(type=="1"){
			$("#goodsInfoSelect").removeAttr("disabled");
			$("#url").val("").attr("readonly","readonly");
		}
	}else{
		$("#goodsInfoSelect").attr("disabled","disabled");
		$("#url").val("").attr("readonly","readonly");
	}
	
	$("#btnSave").on("click",function(){
		var type=$("#type").val();
		var url=$("#url").val();
		var goodsInfo=$("#goodsInfoSelect").val();
		if(!type){
			myAlert("系统提示","请选择打开类型!");
			return;
		}else if(type=='0'&&!url){
			myAlert("系统提示","请填写链接地址!");
			return;
		}else if(type=='1'&&!goodsInfo){
			myAlert("系统提示","请选择商品!");
			return;
		}
		
		var startdate=new Date(($("#startTime").val()).replace(/-/g,"/"));
		var enddate=new Date(($("#endTime").val()).replace(/-/g,"/"));             
		var date = new Date(); 
		if(!$("#startTime").val()||!$("#endTime").val()){
			myAlert("系统提示","请填写完整开始时间和结束时间!");
			return;
		}else if(enddate<date){
			myAlert("系统提示","海报结束时间不能小于当前时间!");
			return;
		}else if(startdate>enddate){
			myAlert("系统提示","海报结束时间不能大于开始时间!");
			return;
		}
		$("#poster_form").submit();
	})
	initValidate();
});

function queryDoctorList(){
	$.ajax({
		url:ctx+"/anon/queryRecommendDoctorList",
		type:"post",
		data:null,
		dataType:"json",
		success:function(data){
			var temp=$("#goodsInfoSelect").html();
			$("#goodsInfoSelect").empty();
			$("#goodsInfoSelect").append("<option value='' selected='selected'>---请选择推荐医生---</option>")
			if(data){
				$.each(data,function(i,g){
					var _temp=temp.format(g.doctorID,getString(g.hospitalName)+"->"+ getString(g.sectionName)+"->"+ getString(g.true_name));
					$("#goodsInfoSelect").append(_temp);
				});
			}
			var goodsInfo=$("#goodsInfo").val();
			if(goodsInfo){
				$("#goodsInfoSelect").find("option[value='"+goodsInfo+"']").attr("selected","selected").siblings().removeAttr("selected");
			}
		}
	})
}

function initValidate() {
	$("#poster_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			posterName : {
				required : true,
				maxlength : 200
			},
			type : {
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
   				success: function(data, statusText) {
   					if(data){
   						window.location.href=ctx+"/poster/posterList";
   					}
				},      				                  
			   type: 'post', 
			   resetForm:false,               
			   url: ctx + "/poster/posterSave"
			});
		}
	});
}

function onchangeGoodsInfo(obj) {
	var value=obj.value;
	var arr=value.split("_");
	$("#copyTitle font").text("医生id："+arr[0]);
}

function onchangeControlFunc(){
	var selectVal=$("#typeSelect").val();
	if(selectVal=='0'){
		$("#goodsInfoSelect").attr("disabled","disabled");
		$("#url").removeAttr("readonly");
	}else if(selectVal=='1'){
		$("#goodsInfoSelect").removeAttr("disabled");
		$("#url").val("").attr("readonly","readonly");
	}else{
		$("#goodsInfoSelect").attr("disabled","disabled");
		$("#url").val("").attr("readonly","readonly");
	}
}

function onchangePosterName(obj) {
	$("#copyTitle font").text(obj.value);
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
