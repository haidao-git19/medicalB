var table;
$(function(){
		queryQuestionnaireCaseClassList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryQuestionnaireCaseClassList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/questionnaire/queryQuestionnaireCaseClassPage',
			"paramSelector" : '#name,#qnId',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "问卷名称"
							},
							{
							  "sTitle" : "指标名称",
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  return "<a href='javascript:openAddOrModifyQuestionnaireCaseClass({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteQuestionnaireCaseClass({0});'>删除</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyQuestionnaireCaseClass(id){
	if(id!=null&&id!=''){
		$.ajax({
			url:ctx+"/questionnaire/queryQuestionnaireCaseClass",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data){
					$("#_id").val(data.id);
					$("#_name").val(data.name);
				}
			}
		});
	}else{
		$("#_id").val('');
		$("#_name").val('');
	}
	$("#modal_parameter").modal();
}

function deleteQuestionnaireCaseClass(id) {
	myConfirm('','确定要删除吗?',function(){
		$.ajax({
			url : ctx + '/questionnaire/deleteQuestionnaireCaseClass',
			data : {"id":id},
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryQuestionnaireCaseClassList();
			}
		});
	});
}

function submitQuestionnaireCaseClass(){
	var flag=validateMsg();
	if(flag==false){
		return;
	}
	
	$.ajax({
		url:ctx+"/questionnaire/addOrUpdateQuestionnaireCaseClass",
		type:"post",
		data:{"id":$("#_id").val(),"name":$("#_name").val(),"qnId":$("#qnId").val()},
		dataType:"json",
		async:false,
		success:function(data){
			closeModal();
			queryQuestionnaireCaseClassList();
		}
	});
}

function closeModal(){
	$("#modal_parameter").modal("hide");
}

function validateMsg(){
	if(!$.trim($("#_name").val())){
		alert( "请填写指标名称!");
		return false;
	}
}