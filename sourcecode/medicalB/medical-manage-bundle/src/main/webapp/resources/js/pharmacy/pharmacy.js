var table_company;

$(function(){	
	
	table_company = $("#pharmacy_table").myDataTable({
		sAjaxSource:ctx+"/pharmacyList.json",
		paramSelector:"#shopName",
		aoColumns : [   
                     {sWidth : 80},
                     {sWidth : 150},
                     {sWidth : 150},
                     {sWidth : 120,
                    	 "fnRender":function(obj){
                    		 var type=obj.aData[3];
                    		 if(type==1){
                    			 return "普通药房";
                    		 }else if(type==2){
                    			 return "医院药房";
                    		 }else{
                    			 return "异常";
                    		 }
                    	 }
                     },
                     {sWidth : 400},
                     { 
     			    	"fnRender" : function(obj) {
     				    	var id = obj.aData[0];
     				    	var url= obj.aData[5];
     			    		return "<a href='javascript:toupdatePharmacy({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
     			    		+"<a href='{0}' target='_blank'>详情</a>".format(url)+"&nbsp;|&nbsp"
     			    		+"<a href='javascript:deletePharmacy({0});'>删除</a>".format(id);
     		    		}
     			    }
				]
	});
	
	$("#query_btn").on("click",function(){
		table_company.fnPageChange("first",true);
	});
});