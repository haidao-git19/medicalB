var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
});

function query() {
	if(table == undefined){
		table = $("#recovery_table").myDataTable({
			sAjaxSource:ctx+"/recovery/query",
			paramSelector:"#title",
			"bSort": false,
			aoColumns:[
			           {sWidth : 60}, {sWidth : 200}, {sWidth : 80},{sWidth : 80}, {sWidth :80}, {sWidth :150}, {sWidth : 150}, 
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[0];
			        		   var url= obj.aData[7];
			        		   return "<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='{0}' target='_blank'>详情</a>".format(url)
			        		   +"&nbsp;|&nbsp<a href='javascript:del({0});'>删除</a>".format(id);
			        	   }
			           }
			           ]
		});
	}else{
		table.fnPageChange("first", true);
	}
}

function edit(id){
	window.location.href=ctx+'/recovery/initAddOrUpdate?id='+id; 
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/recovery/del',
			data : {"id":id},
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload();
			}
		})
	}
}