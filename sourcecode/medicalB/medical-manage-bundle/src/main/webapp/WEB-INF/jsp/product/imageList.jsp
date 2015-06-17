<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css" rel="stylesheet">
<style type="text/css">
.divX
	{
   		z-index:100;
    	height:20px;
    	width:20px;
    	cursor:pointer;
    	display: block;
	}
.imageDiv{
	width:900px;
	position:relative;
	left:10px;
	top:10px;
}
.imgSpan{
	position:relative;
	float:left;
	margin:10px 10px;
}
</style>
	<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/ueditor.all.js"></script>
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="${ctx}/resources/third-party/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
		<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">商品图片</li>
			</ul>
			<form id="queryForm" action="javascript:imageTable.fnDraw();">
				<input type="hidden" name="goodsCode" id="goodsCode" value="${goodsCode}">
				<div class="row-fluid toolbar">
	        		<button id="imageUpload" type="button" class="btn btn-primary" onclick="OpenUpload()">选择图片上传</button> 
	        		<button type="button" class="btn btn-primary" onclick="history.go(-1);">返回</button>   		
	        	</div>
	        	<hr style="margin:5px 2px; color: #fbfbfb;"/>
	        	<div id="imageDiv" class="imageDiv">
	        		
	        	</div>
	       </form>				
		</div>
	    
	</div>
	 <!-- 加载编辑器的容器 -->
    <%-- <script id="editor" name="content" type="text/plain">
        
    </script> --%>
<script type="text/javascript">
var goodsCode=$("#goodsCode").val();
$(function(){
	queryImageList(goodsCode);
})

//实例化编辑器
//var ue = UE.getEditor('editor');

var imageUpload = new UE.ui.Editor();
imageUpload.render('imageUpload');  //渲染控件，控件名必须相同否则图片上操作功能无效
imageUpload.ready(function () {
	imageUpload.setDisabled(); //设置编辑器为禁用
	imageUpload.hide(); //隐藏UE框体
	imageUpload.addListener('beforeInsertImage', function (t, arg) {   //注册拦截插入事件，这一步是最关健的，其它的所有组件只要找到此方法就能单独调用。
	queryImageList(goodsCode);  //查找商品图片列表 
});
});

function OpenUpload() {  //弹出对话框
	var goodsCode=$("#goodsCode").val();
	var d = imageUpload.getDialog("insertimage");
	setUploadImageData(goodsCode);
	d.render();
	d.open();
}
function setUploadImageData(param1){
	$.ajax({
		url : ctx+"/product/setUploadImageData", // 调用方法
		type : 'post', // 提交类型
		dataType : 'json', // 数据格式
		ifModified : true, // 浏览器不缓存
		cache : false, // 不缓存
		data : "goodsCode="+param1 // 传递的参数
		//error : errorHandler
	});
}
function queryImageList(param){
	$.ajax({
		url :ctx+ "/product/queryImageList", // 调用方法
		type : 'post', // 提交类型
		dataType : 'json', // 数据格式
		ifModified : true, // 浏览器不缓存
		cache : false, // 不缓存
		//error : errorHandler,
		data : "goodsCode="+param,
		success : function(data) {
		if(data == null || data == ''){
			$("#imageDiv").empty();
			$("#imageDiv").html("<p style='font-family:Microsoft Yahei;font-size:20px;margin-top:50px;text-align:center'>暂无数据，您可以点击【选择图片上传】按钮上传图片。</p>");
			return;
		}
			$("#imageDiv").empty();
			$.each(data,function(i,item){
				$("#imageDiv").append("<span id='imgSpan"+item.id+"' class='imgSpan'><img style='width:200px;height:200px;' id='image"+item.id+"' alt='"+item.id+"' src='"+item.bigImgPath+"'></span>");
			})
			$("img[id*='image']").each(function(i){
		        var divObj=$("<div onclick=deleteImage('"+$(this).attr('alt')+"');><img style='width:20px;height:20px;' src='${ctx}/resources/third-party/img/rubbishIcon.png'></div>");
		        divObj.addClass("divX");
		        divObj.attr("id","DIV"+$(this).attr("alt"));
		        divObj.attr("title","删除图片"+$(this).attr('alt'));
		        divObj.css({position:"absolute",left: $(this).position().left+183, top:$(this).position().top-10});
		        $(this).parent().append(divObj);
		    });
		}
	}); 
}

function deleteImage(id){
	myConfirm('','确定删除吗?',function(){
		$.ajax({
			url : ctx + '/product/imageDelete',
			data : [ {
				name : 'id',
				value : id
			} ],
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryImageList(goodsCode);
			}
		});
	});
} 
</script>
</body>
</html>