var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
});

function query() {
	if(table == undefined){
		table = $("#attr_class_table").myDataTable({
			sAjaxSource:ctx+"/categoryAttr/queryAttrClassList",
			paramSelector:"#attrClassName",
			"bSort": false,
			aoColumns:[
			           {sWidth : 50},{sWidth : 150}, {sWidth : 100}, {sWidth : 60}, {sWidth : 120}, {sWidth : 60}, {sWidth : 120}, 
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[0];
			        		   var categoryCode=obj.aData[1];
			        		   return "<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:del({0});'>删除</a>".format(id);
			        	   }
			           }
			           ]
		});
	}else{
		table.fnPageChange("first", true);
	}
}

function edit(id){
	$.ajax({
		url:ctx+"/categoryAttr/queryAttrClass",
		type:"post",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			if(data){
				$("#_attrClassId").val(data.id);
				$("#_categoryCode").val(data.categoryCode);
				$("#_attrClassName").val(data.attrClassName);
				
				$("#modal_parameter").modal(); 
			}else{
				myAlert("系统提示","没有找到相关信息!");
			}
		}
	});
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/categoryAttr/deleteAttrClass',
			data : 'id=' + id,
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload();
			}
		})
	}
}


