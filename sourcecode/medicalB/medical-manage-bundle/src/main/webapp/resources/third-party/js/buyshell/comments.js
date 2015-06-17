$(function(){
	var container=$("#commentsContainer");
	var type='0';
	scrollPagination(container,type);
	
	$("#sendBtn").on('click',function(){
		var contents=$.trim($("#comment").val());
		if(contents==''||contents=='请输入评论内容...'){
			alert("评论不能为空!");
			return;
		}
		$.ajax({
			url:ctx+"/anon/addComments",
			type:"post",
			data:[{
				'name':'goodsCode',
				'value':goodsCode
			},{
				'name':'contents',
				'value':contents
			}],
			dataType:"json",
			success:function(data){
				if(data.code=='0'){
					alert("评论成功,正在审核");
					$("#comment").val("");
				}else if(data=="noLogin"){
					alert("亲，您还没登录，请先登录。");
					window.location.href = ctx + '/anon/login?isOauth=1&returnUrl='+callbackUrl;
					return ;	
				}else{
					alert("评论失败!");
				}
			}
		});
	});
});

var x=0;
function scrollPagination(obj,type) {
	var _itemTemp = $("#commentsContainer").html();
	$("#commentsContainer").empty();
	obj.scrollPagination({
		'contentPage' : ctx + '/anon/queryCommentList',
		'contentData' : {
			'goodsCode':goodsCode,
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
			if (data.commentList == null || data.commentList == ''||data.code!='0') {// data为空则停止滚动事件
				$('#nomoreresults').fadeIn();
				obj.stopScrollPagination();
				++x;
				if(x==1){
					$("#noComments").css("display","block");
				}
			} else {
				++x;
				$("#noComments").css("display","none");
				$("#p").val(parseInt($("#p").val()) + 1)// 否则把page加1设置到页面input
				$.each(data.commentList, function(i, b) {
					var _icon = null;
					if(b.icon == null || b.icon == '' || b.icon == undefined ){
						_icon = ctx + '/resources/third-party/images/man.png';
					}else{
						_icon = b.icon;
					}
					var icon = '<img src="'+ getString(_icon) +'" width=\"90\" heigth=\"90\">';
					$("#commentsContainer").append(_itemTemp.format(getString(b.createTime),icon,getString(b.nickName),getString(b.contents)));
				});
				$("#commentsContainer").show();
			}
		}
	});
}

function queryCommentsList(){
	var commentsContainer = $("#commentsContainer").html();
	$("#commentsContainer").empty();
	$.ajax({
		url : ctx + "/anon/queryCommentList",
		data : [ {
			name : 'goodsCode',
			value : goodsCode
		}, {
			name : 'pageId',
			value : 0
		},{
			name : 'sortKey',
			value : 'desc'
		}],
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data.commentList == null || data.commentList == ''||data.code!='0'){
				$("#noComments").css("display","block");
				return ;
			}
			if(data.commentList != undefined && data.commentList != null){
				$("#noComments").css("display","none");
				$.each(data.commentList,function(i, b) {
					var _icon = null;
					if(b.icon == null || b.icon == '' || b.icon == undefined ){
						_icon = ctx + '/resources/third-party/images/user0.png';
					}else{
						_icon = b.icon;
					}
					var icon = '<img src="'+ getString(_icon) +'" width=\"48\" heigth=\"48\">';
					$("#commentsContainer").append(commentsContainer.format(getString(b.createTime),icon,getString(b.nickName),getString(b.contents)));
				});
			}
			$("#commentsContainer").show();
		}
	});
}