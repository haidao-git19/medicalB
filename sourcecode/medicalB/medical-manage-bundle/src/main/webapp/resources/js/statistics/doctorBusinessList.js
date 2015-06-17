var table;
$(function(){
		queryBusinessList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryBusinessList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/statistics/queryDoctorBusinessPage',
			"paramSelector" : '#startTime,#endTime,#doctorId,#orderNumber,#payStatus',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "订单号"
							},
							{
							  "sTitle" : "医生"
							},
							{
							  "sTitle" : "服务类型",
							  "fnRender":function(obj){
								  var serviceType=obj.aData[3];
								  if(serviceType==1){
									  return "咨询";
								  }else if(serviceType==2){
									  return "预约";
								  }else if(serviceType==3){
									  return "加号";
								  }else{
									  return "异常";
								  }
							  }
							},
							{
							  "sTitle" : "支付金额"
							},
							{
							  "sTitle" : "支付状态",
							  "fnRender":function(obj){
								  var payStatus=obj.aData[5];
								  if(payStatus==0){
									  return "未支付";
								  }else if(payStatus==1){
									  return "已支付";
								  }
							  }
							},
							{
							  "sTitle" : "服务日期"
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}