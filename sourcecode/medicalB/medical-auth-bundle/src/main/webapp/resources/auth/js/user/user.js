$(function() {
	initProvince();
	initTable();

	$("#query_btn").click(function() {
		table.fnDraw();
	});
	$("#name,#roleName").keyup(function(e){
		if(e.keyCode == 13) {
			table.fnDraw();
		}
	});

});

function initTable() {
	table = $("#user_table").myDataTable({
		"sAjaxSource" : ctx + '/user/users',
		"paramSelector" : '#province,#city,#status,#name,#roleName',
		"aoColumns" : [ {
			// loginName 0
			"bSortable" : false,
			sWidth : 60
		}, {
			// trueName 1
			"bSortable" : false,
			sWidth : 80
		}, {
			// area 2
			"bSortable" : false,
			sWidth : 60,
			"fnRender" : function(obj) {
				var ai = obj.aData[2];
				if (ai) {
					return ai.name;
				}
				return "";
			}
		}, {
			// role 3
			"bSortable" : false,
			sWidth : 100,
			fnRender : function(obj) {
				var roles = obj.aData[3];
				if (roles) {
					var names = $.map(roles, function(a) {
						return a.name;
					});
					return names.join(', ')
				}
				return "";
			}
		}, {
			// status 4
			"bSortable" : false,
			sWidth : 50,
			"fnRender" : function(obj) {
				var val = obj.aData[4];
				var text = "";
				$("#status option").each(function() {
					if (val && val == $(this).val()) {
						text = $(this).text();
						return false;
					}
				});
				return text;
			}
		}, {
			// createTime 5
			"bSortable" : true,
			"sName" : 'createTime',
			"sWidth" : 120,
			"fnRender" : function(obj) {
				var val = obj.aData[5];
				try{
					return new Date(val).format("yyyy-MM-dd hh:mm:ss");
				}catch(e){
					return '';
				}
			}
		}, {
			// password 6
			"bSortable" : false,
			sWidth : 60,
			fnRender : function(obj) {
				var id = obj.aData[6];
				return "<a href='javascript:resetPassword({0});'>初始化</a>".format(id);
			}
		}, {
			// view 7
			"bSortable" : false,
			sWidth : 50,
			fnRender : function(obj) {
				var id = obj.aData[7];
				return "<a href='{1}/user/edit?id={0}&readOnly=true'>查看</a>".format(id, ctx);
			}
		}, {
			// edit 8
			"bSortable" : false,
			sWidth : 50,
			fnRender : function(obj) {
				var id = obj.aData[8];
				return "<a href='{1}/user/edit?id={0}'>修改</a>".format(id, ctx);
			}
		}, {
			// del 9
			"bSortable" : false,
			sWidth : 50,
			fnRender : function(obj) {
				var id = obj.aData[9];
				return "<a href='javascript:deleteUser({0});'>删除</a>".format(id);
			}
		} ]
	});
}

function initProvince() {
	var p = $("#province").empty();
	p.append("<option value=''>选择省份</option>");
	$.ajax({
		url : ctx + "/area/permitProvinces",
		type : 'POST',
		dataType : 'json',
		async : false,
		success : function(data) {
			if(data.length == 1) {
				p.empty();
			}
			$.each(data, function(i, a) {
				p.append("<option value='{0}'>{1}</option>".format(a.aiid, a.name));
			});
			initCity();
		}
	});
	p.change(function() {
		initCity();
	});
}

function initCity() {
	var c = $("#city").empty();
	c.append("<option value=''>选择城市</option>");
	$.ajax({
		url : ctx + "/area/permitCitys",
		type : 'POST',
		dataType : 'json',
		async : false,
		data : [ {
			name : 'provinceId',
			value : $("#province").val()
		} ],
		success : function(data) {
			if(data.length == 1) {
				c.empty();
			}
			$.each(data, function(i, a) {
				c.append("<option value='{0}'>{1}</option>".format(a.aiid, a.name));
			});
		}
	});
}

function deleteUser(id) {
	myConfirm('', '确定要删除吗?', function() {
		$.ajax({
			url : ctx + '/user/delete',
			data : [ {
				name : 'id',
				value : id
			} ],
			type : 'POST',
			dataType : 'json',
			success : function() {
				table.fnDraw(false);
			}
		});
	});
}

function resetPassword(id) {
	myConfirm('', '确定要初始化密码吗?', function() {
		$.ajax({
			url : ctx + '/user/resetPassword',
			data : [ {
				name : 'id',
				value : id
			} ],
			type : 'POST',
			dataType : 'json',
			success : function() {
				myAlert('','密码初始化完成');
			}
		});
	});
}