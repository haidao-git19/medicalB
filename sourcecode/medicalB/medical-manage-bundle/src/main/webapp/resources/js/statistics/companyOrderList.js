var _table;
$(function(){
		queryShopOrderList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			_table.fnDraw();
		})
	});

function queryShopOrderList(){
	if(_table == undefined){
		_table = $("table").myDataTable({
			"sAjaxSource":ctx + '/statistics/queryShopOrderPage',
			"paramSelector" : '#startTime,#endTime,#orderNumber,#companyId',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "药店"
							},
							{
							  "sTitle" : "订单号"
							},
							{
							  "sTitle" : "商品"
							},
							{
							  "sTitle" : "价格"
							},
							{
							  "sTitle" : "数量"
							},
							{
							  "sTitle" : "合计"
							},
							{
							  "sTitle" : "下单时间"
							}
						]
		});
		
	}else{
		_table.fnPageChange("first",true);
	}
}
