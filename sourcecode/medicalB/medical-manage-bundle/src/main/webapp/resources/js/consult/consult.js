var table_company;

$(function(){	
	
	table_company = $("#consult_table").myDataTable({
		sAjaxSource:ctx+"/consult.json",
		paramSelector:"#isFree,#level",
		aoColumns : [   
                     {},
                     {},
                     {},
                     {
                    	 "fnRender" : function(obj) {
                    		 var isFree=obj.aData[3];
                    		  var show='是';
                    		  if(isFree==0){
                    			  show='否';
                    		  }
							 return show;
                      } 
                    	 
                     },
                     {},
                     {
	                     "fnRender" : function(obj) {
		  				    	var id = obj.aData[5];
		  			    		return "<a href='javascript:toupdateConsult({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
		  			    		+"<a href='javascript:deleteConsult({0});'>删除</a>".format(id);
		  		    		}
                     }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});