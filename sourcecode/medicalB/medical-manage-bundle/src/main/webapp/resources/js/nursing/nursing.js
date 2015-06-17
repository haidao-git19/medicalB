var table_company;

$(function(){	
	
	table_company = $("#data_table").myDataTable({
		sAjaxSource:ctx+"/nursingList.json",
		paramSelector:"#objectName,#sectionName",
		aoColumns : [   
                     {},
                     {},
                     {},
                     {
                     	"fnRender" : function(obj) {
	  				    	var id = obj.aData[3];
	  			    		return "<a href='javascript:toupdateNursing({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
	  			    		+"<a href='javascript:deleteNursing({0});'>删除</a>".format(id);
	  		    		}
                     }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});