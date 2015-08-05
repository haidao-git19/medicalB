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
			"sAjaxSource":ctx + '/statistics/companyPatientPage',
			"paramSelector" : '#patientName,#companyId',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "患者姓名"
							},
							{
							  "sTitle" : "患者昵称"
							},
							{
							  "sTitle" : "联系电话"
							},
							{"sTitle" : "性别",
								"fnRender" : function(obj) {
			                   		 var isM=obj.aData[4];
			               		  var show='男';
			               		  if(isM==0){
			               			  show='女';
			               		  }
									 return show;
	                      	}
							},
							{"sTitle" : "年龄"},
							{"sTitle" : "证件号"},
							{"sTitle" : "创建日期"}
						]
		});
		
	}else{
		_table.fnPageChange("first",true);
	}
}
