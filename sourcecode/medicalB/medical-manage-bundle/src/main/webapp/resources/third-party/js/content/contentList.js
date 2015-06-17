var table;
$(function(){
		queryContentList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryContentList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/content/queryContentList',
			"paramSelector" : '#title,#type',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "标题"
							},
							{
							  "sTitle" : "类型",
							  sWidth : 100,
							  "fnRender" : function(obj) {
								  var type=obj.aData[2];
								  if(type=='0'){
									  return "通用";
								  }else{
									  return "独立";
								  }
							  }
							},
							{
							  "sTitle" : "详情",
							  "fnRender" : function(obj) {
								  var url=obj.aData[3];
								  return "<a target='_blank' href='{0}'>详情</a>".format(url);
							  }
							},
							{
							  "sTitle" : "创建时间",
							  sWidth : 200
							},
							{
							  "sTitle" : "更新时间",
							  sWidth : 200
							},
							{
							  "sTitle" : "状态",
							  sWidth : 60
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  return "<a href='javascript:openAddOrModifyContent({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteContent({0});'>删除</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyContent(id){
	window.location.href = ctx + '/content/contentEdit?id='+id;
}

function deleteContent(id) {
	myConfirm('','确定要删除吗?',function(){
		$.ajax({
			url : ctx + '/content/contentDelete',
			data : [{
				name : 'id',
				value : id
			}],
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				if(data){
					queryContentList();
				}
			}
		});
	});
}
