$(document).ready(function(){
	categoty_list();
});

function categoty_list() {
	var params = '';
	$.ajax({
		type: "GET",
		url: ctx+"/anon/goods/categoty/list?rnd="+Math.random(),
		data: params,
		success : function(data) {
			$('#categoty_list').empty().append(data);
			
			var catId = $("#catId").val();
			var scId = $("#scId").val();
			var catScOptId = $("#catScOptId").val();
			if(catId==0) {
				catId = $("#defCatId").val();
			}
			categoty_options(catId, scId, catScOptId);
		},
		error : function() {alert('error');}
	});
}

function categoty_options(catId, scId, catScOptId) {
	$("#Searchresult").empty();
	$("#Pagination").empty();
	
	var params = 'catId='+catId;
	$.ajax({
		type: "GET",
		url: ctx+"/anon/goods/categoty/options?rnd="+Math.random(),
		data: params,
		success : function(data) {
			$('#categoty_options').empty().append(data);
			list(catId, scId, catScOptId);
		},
		error : function() {alert('error');}
	});
}

function list(catId, scId, catScOptId) {
	//选择切换样式控制
	$(".opt_"+scId).removeClass("cur");
	$("#opt_"+scId+"_"+catScOptId).addClass("cur");
	//选择切换样式控制
	
	$("#catId").val(catId); $("#scId").val(scId); $("#catScOptId").val(catScOptId);
	
	var params = 'catId='+catId+'&scId='+scId+"&catScOptId="+catScOptId;
	var optInit = getOptionsFromForm(paginationCallback);
	
	$.ajax({
		type: "GET",
		url: ctx+"/anon/goods/query/totalRecords?rnd="+Math.random(),
		data: params,
		dataType: "json",
		success : function(r) {
			$("#Pagination").pagination(r.recordSize, optInit);
		},
		error : function() {alert('error');}
	});
}

function getOptionsFromForm(_callback){
	var opt = {callback: _callback}; //翻页回调函数
	//opt.link_to = "javascript:toPage(__id__);";
	opt.link_to = "javascript:void(0);";
	
	$("#paginationOptionsForm input").each(function(){
		opt[this.name] = this.className.match(/numeric/) ? parseInt(this.value) : this.value;
	});
	// Avoid html injections in this demo
	var htmlspecialchars ={ "&":"&amp;", "<":"&lt;", ">":"&gt;", '"':"&quot;"}
	$.each(htmlspecialchars, function(k,v){
		opt.prev_text = opt.prev_text.replace(k,v);
		opt.next_text = opt.next_text.replace(k,v);
	})
	return opt;
}

function paginationCallback(page_index, jq){
	var pageNum = parseInt(page_index) + 1;
	var pageSize = $('#items_per_page').val();	
	var params = "pageSize=" + pageSize + "&pageNum=" + pageNum + '&catId='+$('#catId').val()+'&scId='+$('#scId').val()+"&catScOptId="+$('#catScOptId').val();
	
	$.ajax({
		type: "GET",
		url: ctx+"/anon/goods/query/records?rnd="+Math.random(),
		data: params,
		success : function(data) {
			$('#Searchresult').html(data);
		},
		error : function() {
			$('#Searchresult').html("occur system exception!");
		}
	});
}

function tabFirst(firstLvlId) {
	if($("#tabFirst_"+firstLvlId).attr("class") == "show"){
		$("#tabFirst_"+firstLvlId).removeClass("show");
		$("#tabFirst_"+firstLvlId).addClass("hide");
	}else{
		$("#tabFirst_"+firstLvlId).removeClass("hide");
		$("#tabFirst_"+firstLvlId).addClass("show");
	}
}

function tabSecd(secdLvlId) {
	if($("#tabThrd_"+secdLvlId).attr("class") == "show"){
		$("#tabThrd_"+secdLvlId).removeClass("show");
	}else{
		$("#tabThrd_"+secdLvlId).addClass("show");
	}
}