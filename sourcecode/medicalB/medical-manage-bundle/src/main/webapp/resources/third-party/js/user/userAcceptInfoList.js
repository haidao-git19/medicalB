var table;
$(function(){
		queryUserAcceptInfoList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryUserAcceptInfoList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/user/queryUserAcceptInfoList',
			"paramSelector" : '#userId,#acceptName,#phone',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "用户编号"
							},
							{
							  "sTitle" : "收货人姓名"
							},
							{
							  "sTitle" : "收货人手机"
							},
							{
							  "sTitle" : "省份"
							},
							{
							  "sTitle" : "城市"
							},
							{
							  "sTitle" : "是否启用",
							  "fnRender" : function(obj) {
								  var enable = obj.aData[6];
								  if(enable=='0'){
									  return "是";
								  }else{
									  return "否";
								  }
							   }
							},
							{
								"sTitle" : "状态",
								"fnRender" : function(obj) {
									  var enable = obj.aData[7];
									  if(enable=='0'){
										  return "正常";
									  }else{
										  return "异常";
									  }
								   }
							},
							{
							  "sTitle" : "创建时间",
							},
							{
							  "sTitle" : "更新时间",
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  return "<a href='javascript:openUserAcceptInfoDetail({0});'>详情</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openUserAcceptInfoDetail(id){
	window.location.href = ctx + '/user/userAcceptInfoDetail?id='+id;
}
