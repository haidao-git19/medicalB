var table;
$(function(){
		queryCatalogGoodsList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryCatalogGoodsList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/catalog/queryCatalogGoodsList',
			"paramSelector" : '#catalogName,#goodsName',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "类目编号",
							   sWidth:120  
							},
							{
							  "sTitle" : "类目名称"
							},
							{
							  "sTitle" : "属性",
							  sWidth:120
							},
							{
							  "sTitle" : "商品编号",
							  sWidth : 200
							},
							{
							  "sTitle" : "商品版本",
							  sWidth : 120
							},
							{
							  "sTitle" : "商品名称",
							  sWidth : 200
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  var catalogCode=obj.aData[1];
								  return "<a href='javascript:openAddOrModifyCatalogGoods({0});'>编辑</a>".format(catalogCode)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteCatalogGoods({0},{1});'>删除</a>".format(id,catalogCode);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyCatalogGoods(catalogCode){
	window.location.href = ctx + '/catalog/catalogGoodsEdit?catalogCode='+catalogCode;
}

function deleteCatalogGoods(id,catalogCode) {
	myConfirm('','确定要删除吗?',function(){
		$.ajax({
			url : ctx + '/catalog/catalogGoodsDelete',
			data : [ {
				name : 'id',
				value : id
			},{
				name : 'catalogCode',
				value : catalogCode
			}],
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryCatalogGoodsList();
			}
		});
	});
}
