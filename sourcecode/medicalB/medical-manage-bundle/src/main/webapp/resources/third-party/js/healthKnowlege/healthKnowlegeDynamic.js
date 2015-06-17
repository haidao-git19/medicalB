$(function(){
	queryDynamicList();
	var container=$("#dynamicContainer");
	var type='0';
	scrollPagination(container,type);
});

function scrollPagination(obj,type) {
	var _itemTemp = $("#dynamicContainer").html();
	$("#dynamicContainer").empty();
	obj.scrollPagination({
		'contentPage' : ctx + '/anon/queryHealthKnowlegeDynamics',
		'contentData' : {
			'sortKey':'desc',
			'pageId' : 0,
			'showCount' : 10,
			'type':type
		},
		'scrollTarget' : $(window),
		'heightOffset' : 10,
		'beforeLoad' : function() {
			$('#loading').fadeIn();
		},
		'afterLoad' : function(elementsLoaded, data) {
			$('#loading').fadeOut();
			if (data == null || data == '' || data.code !='0') {// data为空则停止滚动事件
				$('#nomoreresults').fadeIn();
				obj.stopScrollPagination();
			} else {
				$("#p").val(parseInt($("#p").val()) + 1)// 否则把page加1设置到页面input
				$.each(data.detailList, function(i, d) {
					var contentData=$.trim($("<p>"+d.content+"</p>").text());
					var content=contentData.substr(0,30)+"...";
					$("#dynamicContainer").append(_itemTemp.format(getString(d.title),content,getString(d.detailUrl),d.coverImage));
				});
				$("#dynamicContainer").show();
			}
		}
	});
}

function queryDynamicList(){
	var _itemTemp = $("#dynamicContainer").html();
	$("#dynamicContainer").empty();
	$.ajax({
		url : ctx + "/anon/queryHealthKnowlegeDynamics",
		data : [{
			name : 'pageId',
			value : 0
		},{
			name : 'sortKey',
			value : 'desc'
		}],
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data == null || data == ''){
				return ;
			}
			if(data.detailList != undefined && data.detailList != null){
				$.each(data.detailList, function(i, d) {
					var contentData=$.trim($("<p>"+d.content+"</p>").text());
					var content=contentData.substr(0,30)+"...";
					$("#dynamicContainer").append(_itemTemp.format(getString(d.title),content,getString(d.detailUrl),d.coverImage));
				});
			}
			$("#dynamicContainer").show();
		}
	});
}