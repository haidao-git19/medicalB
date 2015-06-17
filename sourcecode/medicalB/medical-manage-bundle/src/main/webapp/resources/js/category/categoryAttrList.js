var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
	$("#class_add_btn").on("click",function(){
		$("#modal_parameter").modal();
	});
});

function query() {
	if(table == undefined){
		table = $("#category_attr_table").myDataTable({
			sAjaxSource:ctx+"/categoryAttr/query",
			paramSelector:"#attrName,#categoryCode,#attrClassId",
			"bSort": false,
			aoColumns:[
			           {sWidth : 50},{sWidth : 130},{sWidth : 80}, {sWidth : 80}, {sWidth : 80}, {sWidth : 30},{sWidth : 60}, {sWidth : 60}, {sWidth : 120},{sWidth : 60},{sWidth : 120}, 
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[0];
			        		   var attrEnterType=obj.aData[6];
			        		   if(attrEnterType=="单值输入"){
			        			   return "<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
				        		   +"<a href='javascript:del({0});'>删除</a>".format(id);
			        		   }else if(attrEnterType="多值输入"){
			        			   return "<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
			        			   +"<a href='javascript:del({0});'>删除</a>".format(id)+"&nbsp;|&nbsp"
			        			   +"<a href='javascript:openAttrDim({0});'>属性维度</a>".format(id);
			        		   }
			        	   }
			           }
			           ]
		});
	}else{
		table.fnPageChange("first", true);
	}
}

function openAttrDim(id){
	window.location.href=ctx+"/categoryAttr/attrDim?attrId="+id+"&categoryCode="+categoryCode;
}

function edit(id){
	window.location.href=ctx+'/categoryAttr/edit?id='+id+"&categoryCode="+categoryCode; 
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/categoryAttr/del',
			data : 'id=' + id,
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload()
			}
		})
	}
}
