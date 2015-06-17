var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
});

function query() {
	if(table == undefined){
		table = $("#emrecordAtta_table").myDataTable({
			sAjaxSource:ctx+"/emrecord_atta/query",
			paramSelector:"#recordID",
			"bSort": false,
			aoColumns:[
			           {sWidth : 100}, {sWidth : 200}, {sWidth : 500}, {sWidth : 100},
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[0];
			        		   return "<a href='javascript:details({0});'>查看</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:del({0});'>删除</a>".format(id);
			        	   }
			           }
			           ]
		});
	}else{
		table.fnPageChange("first", true);
	}
}

function details(id){
	window.location.href=ctx+'/emrecord_atta/details?attaID='+id; 
}

function edit(id){
	window.location.href=ctx+'/emrecord_atta/initAddOrUpdate?attaID='+id+"&recordID="+$("#recordID").val();
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/emrecord_atta/del',
			data : 'attaID=' + id,
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload()
			}
		})
	}
}

function formSumbit() {
	$.ajax({
		type : "post",
		url : ctx + '/emrecord_atta/addOrUpdate',
		data : $('#postForm').serialize(),
		dataType : "json",
		success : function(data) {
			if (data.flag == false) {
				alert(data.msg);
			} else {
				window.location.href = ctx + '/emrecord_atta?recordID='+$("#recordID").val();
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
		$('#attaURL').val(data.name);
		$("#showImage").attr("src",ctx+'/showImg?fileName='+data.name);
		$('#_preview_fake').hide();
	}else{
		alert('上传失败请重试!')
	}
}

//上传失败
var showError = function(data){
    alert('上传失败, 请稍后重试!');
}