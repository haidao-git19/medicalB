function addHistory(itemid, name){
	var ck = $.cookie('history');
	var addck = itemid+', '+name+'; ';
	if(ck!=null && ck.indexOf(itemid)!=-1){
		return;
	}
	
	$.cookie('history', addck + (ck==null?'':ck), {path: '/', domain: '.111.com.cn', expires: 7});
	checkHistory(5);
}

function checkHistory(sizi){
	var ck = $.cookie('history');
	if(!ck)return;
	ck = ck.split('; ');
	if(ck.length <= sizi) return;
	var newCk = '';
	for(var i = 0; i < sizi; i++){
		newCk += (ck[i] + '; ');
	}
	$.cookie('history', newCk, {path: '/', domain: '.111.com.cn', expires: 7});
}

function showHistory(divid){
	var cks = $.cookie('history');
	if(!cks)return;
	cks = cks.split('; ');

	var cIds = "";
	for(var x = 0; x < cks.length; x++){
	    if(cIds != "")cIds+=",";
	    cIds += cks[x].split(',')[0]
	}
	$.ajaxSetup({cache: true});
	var date = new Date().format("yyMMddhhmm");
	
	$.ajax({
		type: "GET",
		url: "http://www.111.com.cn/interfaces/item/itemPrice.action?itemids=" + cIds + "&_=" + date.substring(0,date.length - 1),
		dataType:'jsonp',
		jsonp:'jsoncallback',
		jsonpCallback:'cbprice',
		success: function(data){
		  for(var i = 0 ; i < cks.length; i++){
				if(cks[i] != '' && cks[i] != null){
					$('#' + divid).append(getDl(cks[i], data));
				}
			}
			$.ajaxSetup({cache: false});
		}
	})
}

function getDl(ck, data){
	var cks = ck.split(', ');
	var id = cks[0];
	var name = cks[1];
	var href = 'http://www.111.com.cn/product/' + id + '.html'
	var price = 0.0;
	var pic5 = "";
	var h_style = "";
	for(var i = 0; i < data.length; i++){
		if(id == data[i].id){
			price = data[i].price;
			pic5 = data[i].img5;
			if(i == 4) h_style = "last";
		}
	}
		
	return	'<li class="' + h_style +'"><div class="photo"><a href="' + href + '"><img height="50" width="50" src="' + pic5 + '"></a></div><p><a title="' + name + '" href="' + href + '">' + name +'</a><span>&yen;' + price + '</span></p></li>'				
}