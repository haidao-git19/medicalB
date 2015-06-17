var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
});

function query() {
	if(table == undefined){
		table = $("#category_table").myDataTable({
			sAjaxSource:ctx+"/category/query",
			paramSelector:"#name",
			"bSort": false,
			aoColumns:[
			           {sWidth : 50},{sWidth : 150}, {sWidth : 100}, {sWidth : 100}, {sWidth : 80}, {sWidth : 60}, {sWidth : 120}, {sWidth : 60}, {sWidth : 120}, 
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[0];
			        		   var categoryCode=obj.aData[1];
			        		   return "<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:del({0});'>删除</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:openCategoryAttrs({0});'>类目属性</a>".format(id);
			        	   }
			           }
			           ]
		});
	}else{
		table.fnPageChange("first", true);
	}
}

function edit(id){
	window.location.href=ctx+'/category/initAddOrUpdate?id='+id; 
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/category/del',
			data : 'id=' + id,
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload()
			}
		})
	}
}

function openCategoryAttrs(id){
	window.location.href=ctx+'/categoryAttr?categoryId='+id; 
}

