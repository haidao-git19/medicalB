var table;
$(function(){
		queryGoodsList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
	});

function queryGoodsList(){
	if(table == undefined){
		table = $("table").myDataTable({
			"sAjaxSource":ctx + '/product/queryGoodsList',
			"paramSelector" : '#longName,#goodsCode',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 50
							},
							{
							  "sTitle" : "类目编号",
							  sWidth : 150
							},
							{
							  "sTitle" : "商品编号",
							  sWidth : 180
							},
							{
							  "sTitle" : "短标题",
							  sWidth : 120
							},
							{
							  "sTitle" : "状态",
							  "fnRender":function(obj){
								  var status=obj.aData[4];
								  if(status=='0'){
									  return "初始";
								  }else if(status=='1'){
									  return "审核";
								  }else if(status=='2'){
									  return "在售";
								  }else if(status=='3'){
									  return "下架";
								  }else if(status=='4'){
									  return "禁用";
								  }else{
									  return "异常";
								  }
							  },
							  sWidth : 60
							},
							{
							  "sTitle" : "创建人",
							  sWidth : 60
							},
							{
							  "sTitle" : "创建时间",
							  sWidth : 130
							},
							{
							  "sTitle" : "更新人",
							  sWidth : 60
							},
							{
							  "sTitle" : "更新时间",
							  sWidth : 130
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  var categoryCode=obj.aData[1];
								  var goodsCode = obj.aData[2];
								  return "<a href='javascript:openAddOrModifyGoods({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteGoods({0});'>删除</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:openGoodsAttrList({0});'>属性</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:openImageList({0});'>图片</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyGoods(id){
	window.location.href = ctx + '/product/goodsEdit?id='+id;
}

function openGoodsAttrList(id){
	window.location.href=ctx+'/product/goodsAttrList?goodsId='+id;
}

function openImageList(id){
	window.location.href=ctx+'/product/goodsImageList?goodsId='+id;
}

function deleteGoods(id,goodsCode) {
	myConfirm('','确定要删除吗?',function(){
		$.ajax({
			url : ctx + '/product/goodsDelete',
			data : [ {
				name : 'id',
				value : id
			} ],
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryGoodsList();
			}
		});
	});
}
