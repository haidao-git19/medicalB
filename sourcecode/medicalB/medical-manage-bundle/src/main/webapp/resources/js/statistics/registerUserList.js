var table;
$(function(){
		queryRegisterUserList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryRegisterUserList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/statistics/queryRegisterUserPage',
			"paramSelector" : '#startTime,#endTime',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "姓名"
							},
							{
							  "sTitle" : "昵称"
							},
							{
							  "sTitle" : "年龄"
							},
							{
								"sTitle" : "性别"
							},
							{
								"sTitle" : "电话"
							},
							{
							  "sTitle" : "注册时间",
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}