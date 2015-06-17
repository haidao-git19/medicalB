var table;
$(function(){
		queryPosterList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryPosterList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/poster/queryPosterList',
			"paramSelector" : '#posterName',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "海报名称"
							},
							{
							  "sTitle" : "商品编码"
							},
							{
							  "sTitle" : "商品版本",
							},
							{
							  "sTitle" : "开始时间",
							},
							{
							  "sTitle" : "结束时间",
							},
							{
							  "sTitle" : "状态",
							  "fnRender" : function(obj) {
								  var status=obj.aData[6];
								  if(status=='0'){
									  return "正常";
								  }else{
									  return "过期";
								  }
							  }
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  return "<a href='javascript:openAddOrModifyPoster({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deletePoster({0});'>删除</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyPoster(id){
	window.location.href = ctx + '/poster/posterEdit?id='+id;
}

function deletePoster(id) {
	myConfirm('','确定要删除吗?',function(){
		$.ajax({
			url : ctx + '/poster/posterDelete',
			data : {"id":id},
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				if(data){
					queryPosterList();
				}
			}
		});
	});
}
