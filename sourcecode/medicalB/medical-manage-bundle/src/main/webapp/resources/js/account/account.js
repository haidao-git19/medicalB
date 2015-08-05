var table_company;

$(function(){	
	
	table_company = $("#patient_table").myDataTable({
		sAjaxSource:ctx+"/getcashList.json",
		paramSelector:"#patientCard,#startTime,#endTime",
		aoColumns : [  
		             {},
                     {},
                     {},
                     {},
                     {},
                     {},
                     {},
                     {},
                     {},
                     {
                      	"fnRender" : function(obj) {
		                   		 var isM=obj.aData[9];
		               		  var show='已完成';
		               		  if(isM==0){
		               			  show='未完成';
		               		  }
								 return show;
                      	}
                     }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});