var table_company;

$(function(){	
	
	table_company = $("#company_table").myDataTable({
		sAjaxSource:ctx+"/companyList.json",
		paramSelector:"#companyName,#latnId",
		aoColumns : [   
                     {sWidth : 80},
                     {sWidth : 100},
                     {sWidth : 100},
                     {sWidth : 200},
                     {sWidth : 200},
                     { sWidth : 400,
     			    	"fnRender" : function(obj) {
     				    	var id = obj.aData[0];
     			    		return "<a href='javascript:toupdateCompany({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
     			    		+"<a href='javascript:deleteCompany({0});'>删除</a>".format(id);
     		    		}
     			    }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});