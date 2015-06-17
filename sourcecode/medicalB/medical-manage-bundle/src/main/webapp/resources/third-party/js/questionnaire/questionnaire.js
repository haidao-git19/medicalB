var table;
$(function(){
		queryQuestionnaireList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryQuestionnaireList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/questionnaire/queryQuestionnairePage',
			"paramSelector" : '#name',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "医生"
							},
							{
							  "sTitle" : "问卷名称"
							},
							{
							  "sTitle" : "创建时间",
							},
							{
							  "sTitle" : "备注",
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  return "<a href='javascript:openAddOrModifyQuestionnaire({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteQuestionnaire({0});'>删除</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:toQuestionnaireCaseClassList({0});'>指标</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:toQuestionnaireCaseList({0});'>题目</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyQuestionnaire(id){
	if(id!=null&&id!=''){
		$.ajax({
			url:ctx+"/questionnaire/queryQuestionnaire",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data){
					$("#_id").val(data.id);
					$("#_name").val(data.name);
					$("#_note").val(data.note);
				}
			}
		});
	}else{
		$("#_id").val('');
		$("#_name").val('');
		$("#_note").val('');
	}
	$("#modal_parameter").modal();
}

function deleteQuestionnaire(id) {
	myConfirm('','确定要删除该问卷及相关指标、题目等信息吗?',function(){
		$.ajax({
			url : ctx + '/questionnaire/deleteQuestionnaire',
			data : {"id":id},
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryQuestionnaireList();
			}
		});
	});
}

function toQuestionnaireCaseClassList(id){
	window.location.href = ctx + '/questionnaire/questionnaireCaseClassList?qnId='+id;
}

function toQuestionnaireCaseList(id){
	window.location.href = ctx + '/questionnaire/questionnaireCaseList?qnId='+id;
}

function submitQuestionnaire(){
	var flag=validateMsg();
	if(flag==false){
		return;
	}
	
	$.ajax({
		url:ctx+"/questionnaire/addOrUpdateQuestionnaire",
		type:"post",
		data:{"id":$("#_id").val(),"name":$("#_name").val(),"note":$("#_note").val()},
		dataType:"json",
		async:false,
		success:function(data){
			closeModal();
			queryQuestionnaireList();
		}
	});
}

function closeModal(){
	$("#modal_parameter").modal("hide");
}

function validateMsg(){
	if(!$.trim($("#_name").val())){
		alert( "请填写问卷名称!");
		return false;
	}
}