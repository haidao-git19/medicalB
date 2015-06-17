//实例化编辑器

$(function() {
	initSellCharacter();
	
	var sellAttr=$("#sellAttr").val();
	if(sellAttr){
		$("#sellAttrSelect").find("option[value='"+sellAttr+"']").attr("selected","selected").siblings().removeAttr("selected");
	}
	
	$("#goodsSaveBtn").on('click',function(){
		$("#goods_form").submit();
	})
	initGoodsValidate();
	
//	//默认加载药店 
//	if($('#pharmacy').val()!=''){
//		$('#pharmacyCode').val($('#pharmacy').val());
//	}
	
	initFirstLevelNavList();
	initAllGroupConditionOpts();
	
	if(thirdLevelNavId){
		$("#firstLevel").val(firstLevelNavId);
		onchangeFirstNavLevel(firstLevelNavId);
		$("#secondLevel").val(secondLevelNavId);
		onchangeSecondNavLevel(secondLevelNavId);
		$("#thirdLevel").val(thirdLevelNavId);
		onchangeThirdNavLevel(thirdLevelNavId);
	}else if(secondLevelNavId){
		$("#firstLevel").val(firstLevelNavId);
		onchangeFirstNavLevel(firstLevelNavId);
		$("#secondLevel").val(secondLevelNavId);
		onchangeSecondNavLevel(secondLevelNavId);
	}else if(firstLevelNavId){
		$("#firstLevel").val(firstLevelNavId);
		onchangeFirstNavLevel(firstLevelNavId);
	}	
	
	initScMappingList();
});


function initScMappingList(){
	if($("#goodsCode").val()){
		$.ajax({
			url:ctx+"/navicate/queryScMappingByParam",
			type:"post",
			data:{"goodsCode":$("#goodsCode").val()},
			dataType:"json",
			async:false,
			success:function(data){
				if(data){
					$.each(data,function(i,scM){
						$("input:checkbox[name='conditionOptList'][value='"+scM.catScOptId+"']").attr("checked",'true');
					});
				}
			}
		});
	}
	
	
}
function initFirstLevelNavList(){
	$.ajax({
		url:ctx+"/navicate/queryNavicateList",
		type:"post",
		data:{"pid":"0"},
		dataType:"json",
		async:false,
		success:function(data){
			$(data).each(function(i){
				$("#firstLevel").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			});
		}
	});
}

function initAllGroupConditionOpts(){
	$.ajax({
		url:ctx+"/navicate/queryAllGroupConditionOptList",
		type:"post",
		data:{"catId":null},
		dataType:"json",
		async:false,
		success:function(data){
			if(data){
				$.each(data,function(i,d){
					var temp="<ul id='operArea_"+d.scId+"' class='operArea' style='display:none;'>";
					if(d.conditionOptList){
						for(var j in d.conditionOptList){
							temp+="<li><input type='checkbox' name='conditionOptList' value='"+d.conditionOptList[j].id+"'>"+d.conditionOptList[j].name+"</li>"
						}
					}
					temp+="</ul>";
					$("#searchOptContainer").append(temp);
				});
			}
		}
	});
}

function onchangeFirstNavLevel(value){
	clearConditionAndOpt();
	
	if(value!=''){
		$.ajax({
			url:ctx+"/navicate/queryNavicateList",
			type:"post",
			data:{"pid":value},
			dataType:"json",
			async:false,
			success:function(data){
				$("#secondLevel").empty().append("<option value=''>--选择导航--</option>");
				$("#thirdLevel").empty().append("<option value=''>--选择导航--</option>");
				if(data){
					$(data).each(function(i){
						$("#secondLevel").append("<option value="+data[i].id+">"+data[i].name+"</option>");
					});
				}
			}
		});
	}else{
		querySearchConditions(0);
		$("#secondLevel").empty().append("<option value=''>--选择导航--</option>");
		$("#thirdLevel").empty().append("<option value=''>--选择导航--</option>");
	}
	
}

function onchangeSecondNavLevel(value){
	clearConditionAndOpt();
	
	if(value!=''){
		querySearchConditions(value);
		
		$.ajax({
			url:ctx+"/navicate/queryNavicateList",
			type:"post",
			data:{"pid":value},
			dataType:"json",
			async:false,
			success:function(data){
				$("#thirdLevel").empty().append("<option value=''>--选择导航--</option>");
				if(data){
					$(data).each(function(i){
						$("#thirdLevel").append("<option value="+data[i].id+">"+data[i].name+"</option>");
					});
				}
			}
		});
	}else{
		querySearchConditions(0);
		$("#thirdLevel").empty().append("<option value=''>--选择导航--</option>");
	}
	
}

function onchangeThirdNavLevel(value){
	clearConditionAndOpt();
	
	if(value!=''){
		querySearchConditions(value);
	}else{
		querySearchConditions($("#secondLevel").val());
	}
	
}

function clearConditionAndOpt(){
	$("#searchCondition").empty().append("<option value=''>--选择--</option>");
	$("input:checkbox[name='conditionOptList']").attr("checked",false);
	$("#operArea_").show().siblings().hide();
}

function querySearchConditions(catId){
	$.ajax({
		url:ctx+"/navicate/querySearchConditionList",
		type:"post",
		data:{"catId":catId},
		dataType:"json",
		async:false,
		success:function(data){
			if(data){
				$("#searchCondition").empty().append("<option value=''>--选择--</option>");
				$(data).each(function(i){
					$("#searchCondition").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
				})
			}
		}
	});
}

function onchangeSearchCondition(value){
	var operAreaObj=$("#operArea_"+value);
	if(operAreaObj){
		$(operAreaObj).siblings().hide();
		$(operAreaObj).show();
	}
}

function queryConditionOpts(scId){
	$.ajax({
		url:ctx+"/navicate/queryConditionOptList",
		type:"post",
		data:{"scId":scId},
		dataType:"json",
		success:function(data){
			if(data){
				
			}
		}
	});
}

function initSellCharacter(){
	var sellCharacter=$("#sellCharacter").val();
	if(sellCharacter==null||sellCharacter==''){
		return;
	}
	var atVal=parseInt(sellCharacter);
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

function initGoodsValidate() {
	$("#goods_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			longName : {
				required : true,
				maxlength : 200
			},
			mediumName : {
				required : true,
				maxlength : 200
			},
			shortName : {
				required : true,
				maxlength : 200
			},
			shopPrice : {
				required : true,
				maxlength : 200
			},
			coverImage : {
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
   					window.location.href=ctx+"/product/goodsList";
				},      				                  
			   type: 'post', 
			   resetForm:true,               
			   url: ctx + "/product/goodsSave"
			});
		}
	});
}

function onchangeGraphicsName(obj) {
	$("#copyTitle font").text(obj.value);
}

function onchangeGraphicsAbstract(obj) {
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
	var categoryCode=$("#categoryCode").val();
	if(categoryCode==null||categoryCode==''){
		$("#categoryErrorSpan").text("必选项");
		return false;
	}
	var goodsCode=$("#goodsCode").val();
	if(goodsCode==null||goodsCode==''){
		$("#goodsCodeErrorSpan").text("必选项");
		return false;
	}
	$("#goodsCodeErrorSpan").empty();
	$("#categoryErrorSpan").empty();
	
	var firstLevelNavId=$("#firstLevel").val();
	if(firstLevelNavId==''){
		alert("请设置商品所属导航!");
		return false;
	}
	
	var sellCharacter=0;
	$.each($("input[type='checkbox']:checked"),function(i,c){
		sellCharacter+=parseInt($(c).val());
	});
	$("#sellCharacter").val(sellCharacter);
	
	$.blockUI({ 
		message: '<h4>正在提交，请等待...................</h4>'
	})	
}

function showMsg(){
	myAlert('系统提示',"操作成功!");
}