var table_company;

$(function(){	
	
	initHospitalTable();
	
	//加载本地网
	$.ajax({
		url:ctx+"/area/handleAllArea",
		async:false,
		dataType:"json",
		success:function(data){
			$("#areaID").html("<option value=''>--选择城市--</option>");
			if(data){
				for(var i in data){
					$("#areaID").append("<option value='"+data[i].areaID+"'>"+data[i].areaName+"</option>");
				}
			}
		}
	});
	
	$('#areaID').val($('#latnId').val());
	$('#isCooperation').val($('#defaultCooperation').val());
	
	$("#areaID").change(function(){
		var areaName=$("#areaID").children("option:selected").text();
		$("#areaName").val(areaName);
	});
});

function initHospitalTable(){
	table_company = $("#hospital_table").myDataTable({
		sAjaxSource:ctx+"/hospitalList.json",
		paramSelector:"#hospitalLevel,#hospitalName,#areaID,#type",
		aoColumns : [   
                     {},
                     {},
                     {},
                     {},
                     {},
                     {},
                     {
	  			    	"fnRender" : function(obj) {
	  				    	var id = obj.aData[6];
	  			    		return "<a href='javascript:toupdateHospital({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
	  			    		+"<a href='javascript:deleteHospital({0});'>删除</a>".format(id);
	  		    		}
                     }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
}

function toupdateHospital(id){
	   window.location.href=ctx+'/hospital/initAddOrUpdate?hospitalID='+id; 
}

function deleteHospital(id){

	 var url=ctx+'/hospital/del';
	 var param='hospitalID='+id;
	 if(confirm("确定要删除?")){
		 //ajax 删除事件
			$.ajax({
					type: "post",
		            url: url,
		            data: param,
		            dataType: "json",
		            success: function(data){
		            	alert('删除成功');
		            	location.reload()
		               }
		            })
	 }

}