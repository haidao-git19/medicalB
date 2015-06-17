var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
});

function query() {
	if(table == undefined){
		table = $("#emrecord_table").myDataTable({
			sAjaxSource:ctx+"/emrecord/query",
			paramSelector:"#patientName,#patientCard",
			"bSort": false,
			aoColumns:[
			           {sWidth : 70}, {sWidth : 150}, {sWidth : 100}, {sWidth : 120}, {sWidth : 70}, {sWidth : 80}, {sWidth : 140}, {sWidth : 140}, 
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[8];
			        		   return "<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:del({0});'>删除</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:atta({0});'>附件</a>".format(id);
			        	   }
			           }
			           ]
		});
	}else{
		table.fnPageChange("first", true);
	}
}

function edit(id){
	window.location.href=ctx+'/emrecord/initAddOrUpdate?id='+id; 
}

function loadPatient() {
	$.ajax({
		type : "post",
		url : ctx + '/patient/get/idcard',
		data : 'patientCard=' + $("#patientCard").val(),
		dataType : "json",
		success : function(data) {
			if(data == '' || data == 'null') {
				alert("该患者不存在！");
				$("#patientID").val("");
				$("#patientName").val("");
			}else{
				$("#patientID").val(data.patientID);
				$("#patientName").val(data.patientName);
			}
		}
	})
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/emrecord/del',
			data : 'id=' + id,
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload()
			}
		})
	}
}

function atta(id){
	window.location.href=ctx+'/emrecord_atta?recordID='+id; 
}

function formSumbit() {
	$.ajax({
		type : "post",
		url : ctx + '/emrecord/addOrUpdate',
		data : $('#postForm').serialize(),
		dataType : "json",
		success : function(data) {
			if (data.flag == false) {
				alert(data.msg);
			} else {
				window.location.href = ctx + '/emrecord';
			}
		}
	})
}