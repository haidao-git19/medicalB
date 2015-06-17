$(function() {

	initTable();

	$("#query_btn").click(function() {
		table.fnDraw();
	});
})

function initTable() {
	table = $("#role_table").myDataTable(
			{
				"sAjaxSource" : ctx + '/role/query',
				"paramSelector" : '#name',
				"aoColumns" : [
						{
							// name 0
							"bSortable" : false,
							sWidth : 120
						},{
							//1 更新人
							"bSortable" : false,
							sWidth : 80
						},{
							//2 更新时间
							"bSortable" : true,
							sName : 'updateTime',
							fnRender : function(obj){
								var val = obj.aData[2];
								if(val) {
									return new Date(val).format('yyyy-MM-dd hh:mm:ss');
								}
								return '';
							},
							sWidth : 150
						},{
							//3 创建人
							"bSortable" : false,
							sWidth : 80
						},{
							//4 创建时间
							"bSortable" : true,
							sName : 'createTime',
							fnRender : function(obj) {
								var val = obj.aData[4];
								if(val) {
									return new Date(val).format('yyyy-MM-dd hh:mm:ss');
								}
								return '';
							},
							sWidth : 150
						},
						{
							// view 5
							"bSortable" : false,
							fnRender : function(obj) {
								var id = obj.aData[5];
								return "<a href='{1}/role/edit?id={0}&readOnly=true'>查看</a>"
										.format(id, ctx);
							},
							sWidth : 60
						},
						{
							// edit 6
							"bSortable" : false,
							fnRender : function(obj) {
								var id = obj.aData[6];
								return "<a href='{1}/role/edit?id={0}'>修改</a>".format(id, ctx);
							},
							sWidth : 60
						},
						{
							// del 7
							"bSortable" : false,
							fnRender : function(obj) {
								var id = obj.aData[7];
								return "<a href='javascript:deleteRole({0});'>删除</a>"
										.format(id);
							},
							sWidth : 60
						} ]
			});
}

function deleteRole(id) {
	myConfirm('', '确定删除?', function() {
		$.ajax({
			url : ctx + '/role/delete',
			type : 'post',
			dataType : 'json',
			data : [ {
				name : 'id',
				value : id
			} ],
			success : function(data) {
				if(data.error) {
					myAlert('','删除失败 : ' + data.error);
					return;
				}
				table.fnDraw(false);
			}
		})
	})
}
