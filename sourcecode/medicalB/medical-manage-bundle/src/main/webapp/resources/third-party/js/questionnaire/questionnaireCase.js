var table;
$(function(){
		queryQuestionnaireCaseList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryQuestionnaireCaseList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/questionnaire/queryQuestionnaireCasePage',
			"paramSelector" : '#title,#qnId',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "问卷名"
							},
							{
							  "sTitle" : "指标",
							},
							{
								"sTitle" : "题目类型",
								"fnRender" : function(obj) {
									var type= obj.aData[3];
									if(type==1){
										return "填空题";
									}else if(type==2){
										return "单选题";
									}else if(type==3){
										return "多选题";
									}
								}
							},
							{
								"sTitle" : "题目名称",
							},
							{
								"sTitle" : "单位",
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  var _type= obj.aData[6];
								  return "<a href='javascript:openAddOrModifyQuestionnaireCase({0});'>编辑</a>".format(id)
							        +"&nbsp;|&nbsp;<a href='javascript:deleteQuestionnaireCase({0});'>删除</a>".format(id)
							        +"&nbsp;|&nbsp;<a href='javascript:toCaseOptionList({0},{1})'>详细设置</a>".format(id,_type);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function toCaseOptionList(id,_type){
	window.location.href=ctx+"/questionnaire/questionnaireCaseOptionList?qId="+id+"&qnId="+$("#qnId").val()+"&type="+_type;
}

function previewQuestionnaire(){
	var qnId=$("#qnId").val();
	$.ajax({
		url:ctx+"/questionnaire/queryCaseAndOptionInfo",
		type:"post",
		data:{"qnId":qnId},
		dataType:"json",
		success:function(data){
			if(data){
				
				$("#fill_content").empty();
				$("#singleSelectContent").empty();
				$("#multiSelectContent").empty();
				
				$.each(data.caseList,function(i,c){
					if(c.type==1){
						var fillHtml=$("#fillHtml").html();
						var _fillHtml=fillHtml.format(c.title,c.unit);
						$("#fill_content").append(_fillHtml);
					
					}else if(c.type==2){
						
						var optionList=data["optionList_"+c.id];
						if(optionList){
							$("#singleSelectHtml").find("div[class='controls']").empty();
							$.each(optionList,function(j,o){
								$("#singleSelectHtml").find("div[class='controls']").append("<input type='radio'/>"+o.option+"&nbsp;&nbsp;&nbsp;&nbsp;");
							});
						}
						var singleSelectHtml=$("#singleSelectHtml").html();
						var _singleSelectHtml=singleSelectHtml.format(c.title);
						$("#singleSelectContent").append(_singleSelectHtml);
					
					}else if(c.type==3){
						
						var optionList=data["optionList_"+c.id];
						if(optionList){
							$("#multiSelectHtml").find("div[class='controls']").empty();
							$.each(optionList,function(j,o){
								$("#multiSelectHtml").find("div[class='controls']").append("<input type='checkbox'/>"+o.option+"&nbsp;&nbsp;&nbsp;&nbsp;");
							});
						}
						var multiSelectHtml=$("#multiSelectHtml").html();
						var _multiSelectHtml=multiSelectHtml.format(c.title);
						$("#multiSelectContent").append(_multiSelectHtml);
					}
					
					$("#singleSelectHtml").find("div[class='controls']").empty();
					$("#multiSelectHtml").find("div[class='controls']").empty();
				});
				
				if($.trim($("#fill_content").html())){
					$("#item_fill").show();
				}
				if($.trim($("#singleSelectContent").html())){
					$("#item_single_option").show();
				}
				if($.trim($("#multiSelectContent").html())){
					$("#item_multi_option").show();
				}
			}else{
				alert("没有数据!");
			}
		}
	});
	$("#modal_preview").modal();
}

function openAddOrModifyQuestionnaireCase(id){
	if(id!=null&&id!=''){
		$.ajax({
			url:ctx+"/questionnaire/queryQuestionnaireCase",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data){
					$("#_id").val(data.id);
					$("#_classId").val(data.classId);
					$("#_type").val(data.type);
					$("#_title").val(data.title);
					$("#_unit").val(data.unit);
				}
			}
		});
	}else{
		$("#_id").val('');
		$("#_classId").val('');
		$("#_type").val('');
		$("#_title").val('');
		$("#_unit").val('');
	}
	$("#modal_parameter").modal();
}

function deleteQuestionnaireCase(id) {
	myConfirm('','确定要删除该题目及相关信息吗?',function(){
		$.ajax({
			url : ctx + '/questionnaire/deleteQuestionnaireCase',
			data : {"id":id},
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryQuestionnaireCaseList();
			}
		});
	});
}

function submitQuestionnaireCase(){
	var flag=validateMsg();
	if(flag==false){
		return;
	}
	
	$.ajax({
		url:ctx+"/questionnaire/addOrUpdateQuestionnaireCase",
		type:"post",
		data:$("#edit_questionnaire_case_form").serialize(),
		dataType:"json",
		async:false,
		success:function(data){
			closeModal();
			queryQuestionnaireCaseList();
		}
	});
}

function closeModal(){
	$("#modal_parameter").modal("hide");
}

function validateMsg(){
	if(!$.trim($("#_classId").val())){
		alert( "请选择指标!");
		return false;
	}
	if(!$.trim($("#_type").val())){
		alert( "请选择类型!");
		return false;
	}
	if(!$.trim($("#_title").val())){
		alert("请填写名称");
		return false;
	}
}