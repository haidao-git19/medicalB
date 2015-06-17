var table;
$(function(){
		queryUserList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryUserList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/user/queryUserList',
			"paramSelector" : '#loginName,#userId',
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
							  "sTitle" : "登录名"
							},
							{
							  "sTitle" : "昵称"
							},
							{
							  "sTitle" : "年龄"
							},
							{
							  "sTitle" : "性别",
							},
							{
							  "sTitle" : "手机",
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
								  return "<a href='javascript:openUserDetail({0});'>详情</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openUserDetail(id){
	window.location.href = ctx + '/user/userDetail?id='+id;
}
