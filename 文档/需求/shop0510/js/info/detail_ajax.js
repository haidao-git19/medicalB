function good(value, reviewId){
	var exp=new Date();
	var exp2=new Date();
	exp2.setTime(exp2.getTime()+1800000);
	var flag=false;
	var cookies = document.cookie;
	var hasreviewTime = cookies.indexOf(reviewId+'=');
	if(hasreviewTime==-1){
		document.cookie = reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
		flag=true;
	} else {
		var start = cookies.indexOf(reviewId+'=')+reviewId.length+1;
		var end = cookies.indexOf(';', start);
		if(end==-1){
			end= cookies.length;
		}
		var idStr = cookies.substring(start, end);
		if((Date.parse(exp.toGMTString())-idStr)>1800000){
			document.cookie = reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
			flag=true;
		}else{
			 alert("请您稍后进行评价");
		}
	
	}
	if(flag){
		var url = "http://www.111.com.cn/front/advisory/" + (value==1?'addYes':'addNo') +".action?reviewId="+reviewId;
		jQuery.ajax({
			type: "GET",
			url: url,
			dataType: "text",
			cache: false,
			success: function(returndata){
				if(returndata!=null){
					var _data = eval("["+returndata+"]");
					if(value==1){
						jQuery(("#upNum_"+_data[0].reviewId)).html(_data[0].yes);
					}else{
						jQuery(("#downNum_"+_data[0].reviewId)).html(_data[0].no);
					}
				}
			}
		});
	}
}