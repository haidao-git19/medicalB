var table;
$(function(){
		queryHealthKnowlegeList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryHealthKnowlegeList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/health/queryHealthKnowlegeList',
			"paramSelector" : '#title',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "标题",
							},
							{
							  "sTitle" : "创建时间",
							},
							{
							  "sTitle" : "更新时间",
							},
							{
							   "sTitle" : "状态",
							   "fnRender" : function(obj) {
								   var status = obj.aData[4];
									if(status=="0"){
										return "正常";
									}else if(status=="1"){
										return "过期";
									}
								}
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  var detailUrl=obj.aData[5];
								  return "<a href='javascript:openAddOrModifyKnowlege({0});'>编辑</a>".format(id)
								  +"&nbsp;|&nbsp;<a href='javascript:deleteKnowlege({0});'>删除</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyKnowlege(id){
	window.location.href=ctx+'/health/healthKnowlegeEdit?id='+id;
}

function deleteKnowlege(id){
	myConfirm("系统提示","确定要删除吗？",function(){
		$.ajax({
			url:ctx+"/health/healthKnowlegeDelete",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data){
					window.location.href=ctx+"/health/healthKnowlegeList?id="+id;
				}
			}
		});
	});
}
