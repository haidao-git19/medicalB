var table_company;

$(function(){	
	
	table_company = $("#patient_table").myDataTable({
		sAjaxSource:ctx+"/patientList.json",
		paramSelector:"#patientCard",
		aoColumns : [   
                     {},
                     {
                      	"fnRender" : function(obj) {
		                   		 var isM=obj.aData[1];
		               		  var show='男';
		               		  if(isM==0){
		               			  show='女';
		               		  }
								 return show;
                      	}
                     },
                     {sWidth:60},
                     {},
                     {},
                     {sWidth:180},
                     {
                     
                     "fnRender" : function(obj) {
	  				    	var id = obj.aData[6];
	  			    		return "<a href='javascript:toupdatePatient({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
	  			    		+"<a href='javascript:deletePatient({0});'>删除</a>".format(id);
	  		    		}
                     
                     }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});