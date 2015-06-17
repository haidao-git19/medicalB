var table;
$(function(){
		queryQuestionnaireCaseOptionList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryQuestionnaireCaseOptionList(){
	var type=$("#type").val();
	if(type=="1"){
		initFillTable();
	}else if(type=="2"||type=="3"){
		initOptionTable();
	}
}

function initFillTable(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/questionnaire/queryQuestionnaireCaseOptionPage',
			"paramSelector" : '#option,#qId',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "题目"
							},
							{
							  "sTitle" : "异常值",
							  "fnRender":function(obj){
								  var exFlag=obj.aData[3];
								  if(exFlag==1){
									  return "是";
								  }else if(exFlag==0){
									  return "否";
								  }
							  }
							},
							{
								"sTitle" : "级别",
								"fnRender":function(obj){
									var level=obj.aData[4];
									if(level==0){
										return "正常";
									}else if(level==1){
										return "轻度";
									}else if(level==2){
										return "中度";
									}else if(level==3){
										return "严重";
									}else if(level==4){
										return "高危";
									}
								}
							},
							{
								"sTitle" : "排序"
							},
							{
								"sTitle" : "最小范围"
							},
							{
								"sTitle" : "最大范围"
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  return "<a href='javascript:openAddOrModifyQuestionnaireCaseOption({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteQuestionnaireCaseOption({0});'>删除</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function initOptionTable(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/questionnaire/queryQuestionnaireCaseOptionPage',
			"paramSelector" : '#option,#qId',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "题目"
							},
							{
							  "sTitle" : "选项",
							},
							{
							  "sTitle" : "异常值",
							  "fnRender":function(obj){
								  var exFlag=obj.aData[3];
								  if(exFlag==1){
									  return "是";
								  }else if(exFlag==0){
									  return "否";
								  }
							  }
							},
							{
								"sTitle" : "级别",
								"fnRender":function(obj){
									var level=obj.aData[4];
									if(level==0){
										return "正常";
									}else if(level==1){
										return "轻度";
									}else if(level==2){
										return "中度";
									}else if(level==3){
										return "严重";
									}else if(level==4){
										return "高危";
									}
								}
							},
							{
								"sTitle" : "排序"
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  return "<a href='javascript:openAddOrModifyQuestionnaireCaseOption({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteQuestionnaireCaseOption({0});'>删除</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyQuestionnaireCaseOption(id){
	if(id!=null&&id!=''){
		$.ajax({
			url:ctx+"/questionnaire/queryQuestionnaireCaseOption",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data){
					$("#_id").val(data.id);
					$("#_exceptionFlag").val(data.exceptionFlag);
					$("#_level").val(data.level);
					$("#_index").val(data.index);
					$("#_note").val(data.note);
					
					var type=$("#type").val();
					if(type=="1"){
						$("#_rangeFrom").val(data.rangeFrom);
						$("#_rangeTo").val(data.rangeTo);
					}else if(type=="2"||type=="3"){
						$("#_option").val(data.option);
					}
				}
			}
		});
	}else{
		$("#_id").val('');
		$("#_option").val('');
		$("#_exceptionFlag").val('');
		$("#_level").val('');
		$("#_index").val('');
		$("#_rangeFrom").val('');
		$("#_rangeTo").val('');
		$("#_note").val('');
	}
	$("#modal_parameter").modal();
}

function deleteQuestionnaireCaseOption(id) {
	myConfirm('','确定要删除吗?',function(){
		$.ajax({
			url : ctx + '/questionnaire/deleteQuestionnaireCaseOption',
			data : {"id":id},
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryQuestionnaireCaseOptionList();
			}
		});
	});
}

function submitQuestionnaireCaseOption(){
	var flag=validateMsg();
	if(flag==false){
		return;
	}
	
	$.ajax({
		url:ctx+"/questionnaire/addOrUpdateQuestionnaireCaseOption",
		type:"post",
		data:$("#edit_questionnaire_option_form").serialize(),
		dataType:"json",
		async:false,
		success:function(data){
			closeModal();
			queryQuestionnaireCaseOptionList();
		}
	});
}

function closeModal(){
	$("#modal_parameter").modal("hide");
}

function validateMsg(){
	if($("#type").val()=="2"||$("#type").val()=="3"){
		if(!$.trim($("#_option").val())){
			alert( "请填写选项!");
			return false;
		}
	}
	if(!$.trim($("#_index").val())){
		alert( "请选择排序!");
		return false;
	}
}