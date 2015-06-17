var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
});

function query() {
	if(table == undefined){
		table = $("#doctorFee_table").myDataTable({
			sAjaxSource:ctx+"/doctorFee/query",
			paramSelector:"#doctorID, #type",
			"bSort": false,
			aoColumns:[
			           {sWidth : 100}, {sWidth : 200}, {sWidth : 120}, {sWidth : 300}, {sWidth : 160}, 
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
	window.location.href=ctx+'/doctorFee/initAddOrUpdate?id='+id+"&doctorID="+$("#doctorID").val();
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/doctorFee/del',
			data : 'id=' + id,
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
		url : ctx + '/doctorFee/addOrUpdate',
		data : $('#postForm').serialize(),
		dataType : "json",
		success : function(data) {
			if (data.flag == false) {
				alert(data.msg);
			} else {
				window.location.href = ctx + '/doctorFee?doctorID='+$("#doctorID").val();
			}
		}
	})
}