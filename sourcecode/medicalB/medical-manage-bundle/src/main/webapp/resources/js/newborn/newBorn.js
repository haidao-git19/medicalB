var table_company;

$(function(){	
	
	table_company = $("#patient_table").myDataTable({
		sAjaxSource:ctx+"/newBornList.json",
		paramSelector:"#mamu,#father",
		aoColumns : [   
                     {},
                     {},
                     {},
                     {},
                     {},
                     {
	                     "fnRender" : function(obj){
		  				    	var id = obj.aData[5];
		  			    		return "<a href='javascript:toupdateNewBorn({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
		  			    		+"<a href='javascript:deleteNewBorn({0});'>删除</a>".format(id);
		  		    		}
                     
                     }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});