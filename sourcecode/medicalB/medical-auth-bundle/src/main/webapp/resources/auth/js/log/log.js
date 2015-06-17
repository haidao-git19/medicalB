function initTable() {
	table = $("#log_table").myDataTable({
		"sAjaxSource" : ctx + '/log/pageQuery',
		"paramSelector" : '#text,#startDate,#endDate',
		"aoColumns" : [ {
			//0 optName
			"bSortable" : false,
			sWidth : 150
		}, {
			//1 loginName
			"bSortable" : false,
			sWidth : 60
		}, {
			// 2 optTime
			bSortable : true,
			sName : 'optTime',
			sWidth : 80,
			fnRender : function(obj) {
				var val = obj.aData[2];
				try {
					return new Date(val).format("yyyy-MM-dd hh:mm:ss");
				} catch (e) {
					return '';
				}
			}
		} ]
	});
}

function initQuery() {
	$("#btn-query").click(function() {
		table.fnDraw();
	});
	$("#text").keyup(function(e) {
		if (e.keyCode == 13) {
			table.fnDraw();
		}
	});
}
$(document).ready(function() {
	initTable();
	initQuery();
});
