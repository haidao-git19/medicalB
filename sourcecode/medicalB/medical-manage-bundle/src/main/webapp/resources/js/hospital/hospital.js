var table_company;

$(function(){	
	
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
});