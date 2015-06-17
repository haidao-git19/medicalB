var table;
$(function(){
		queryGoodsAttrList();
		//绑定查询按钮
		$("#query_btn").on('click',function(){
			   table.fnDraw();
		})
});

function queryGoodsAttrList(){
	if(table == undefined){
		table = $("#_goods_attr_table").myDataTable({
			"sAjaxSource":ctx + '/product/queryGoodsAttrList',
			"paramSelector" : '#attrCode,#goodsCode',
			"iDisplayLength":10,
			"bSort": false,	
			"aoColumns" : [
							{
							 "sTitle" : "序列",
							 "bSortable" : true,
							  sWidth : 60
							},
							{
							  "sTitle" : "商品编号"
							},
							{
							  "sTitle" : "属性编号",
							  sWidth : 60
							},
							{
							  "sTitle" : "属性名称"
							},
							{
							  "sTitle" : "属性值"
							},
							{
							  "sTitle" : "创建时间",
							  sWidth : 120
							},
							{
							  "sTitle" : "更新时间",
							  sWidth : 120
							},
							{ "sTitle" : "操作",
							  "fnRender" : function(obj) {
								  var id = obj.aData[0];
								  var goodsCode = obj.aData[1];
								  return "<a href='javascript:openAddOrModifyGoodsAttr({0});'>编辑</a>".format(id)
								        +"&nbsp;|&nbsp;<a href='javascript:deleteGoodsAttr({0});'>删除</a>".format(id);
							   }
							}
						]
		});
	}else{
		table.fnPageChange("first",true);
	}
}

function openAddOrModifyGoodsAttr(id){
	$.ajax({
		url:ctx+"/product/goodsAttrEdit",
		type:'post',
		data:{"id":id,"categoryCode":categoryCode},
		dataType:"json",
		success:function(data){
			if(data!=null&&data!=''){
				if(data.categoryAttrList){
					$("#_attrCode").empty();
					var optionTemplate="<option value='{0}'>{1}</option>";
					$.each(data.categoryAttrList,function(i,ct){
						var _ot=optionTemplate.format(ct.attrCode,ct.attrName);
						$("#_attrCode").append(_ot);
					});
				}
				if(data.goodsAttr){
					$("#_id").val(data.goodsAttr.id);
					$("#_attrCode").find("option[value='"+data.goodsAttr.attrCode+"']").attr("selected","selected");
					$("#attrValue").val(data.goodsAttr.attrValue);
					$("#_attrCode").attr("disabled","disabled");
				}else{
					$("#_attrCode").removeAttr("disabled");
					$("#attrValue").val("");
				}
			}else{
				$("#_attrCode").removeAttr("disabled");
				$("#attrValue").val("");
			}
		}
	});
	$("#goodsAttr_modal").modal("show");
}

function deleteGoodsAttr(id) {
	myConfirm('','确定要删除吗?',function(){
		$.ajax({
			url : ctx + '/product/goodsAttrDelete',
			data : [ {
				name : 'id',
				value : id
			}],
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				queryGoodsAttrList();
			}
		});
	});
}
