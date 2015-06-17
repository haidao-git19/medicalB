var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
});

function query() {
	if(table == undefined){
		table = $("#attr_dim_table").myDataTable({
			sAjaxSource:ctx+"/categoryAttr/queryAttrDimList",
			paramSelector:"#attrShowName,#categoryCode,#attrCode",
			"bSort": false,
			aoColumns:[
			           {sWidth : 50},{sWidth : 150}, {sWidth : 100},{sWidth : 100},{sWidth : 100}, {sWidth : 60}, {sWidth : 120}, {sWidth : 60}, {sWidth : 120}, 
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[0];
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
	window.location.href=ctx+"/categoryAttr/editAttrDim?id="+id+"&categoryCode="+categoryCode+"&attrCode="+attrCode;
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/categoryAttr/deleteAttrDim',
			data : 'id=' + id,
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload();
			}
		})
	}
}


