var table;
$(function(){
		queryHospitalBusinessList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryHospitalBusinessList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/statistics/queryHospitalBusinessPage',
			"paramSelector" : '#startTime,#endTime,#hospitalID',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							  "sTitle" : "医院"
							},
							{
							  "sTitle" : "医生"
							},
							{
							  "sTitle" : "服务次数"
							},
							{
							  "sTitle" : "收入(单位:元)"
							},
							{
							  "sTitle" : "操作",
							  "fnRender":function(obj){
								  var doctorID=obj.aData[4];
								  return "<a href='javascript:toDoctorBusinessList({0})'>详情</a>".format(doctorID);
							  }
							}
							
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function toDoctorBusinessList(doctorID){
	window.location.href=ctx+"/statistics/doctorBusinessList?doctorID="+doctorID;
}