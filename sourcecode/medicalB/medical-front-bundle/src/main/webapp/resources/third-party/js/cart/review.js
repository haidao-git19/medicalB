var g_senstives = [];
var g_suspicious = [];
var g_contents=[];
var auditing=1;
var  recontent="";
function check(){
	if($.trim($("#recontent").val())=='请填写回复，文字字数在5-300字之间'){
		$("#recontent").val("");
	}
	$("#recontent").removeClass("ntc");
}
function checkspecial(){
	var con=$("#recontent").val();
	if(con.indexOf('<')>-1||con.indexOf('>')>-1){
		$("#recontentdiv").html("<span class='warning'>不能有特殊字符</span>");
		return false;
	}
}
function reClose(){
	var t = $().popBx().close();

}
function save(e,backurl,status){
	var e =  window.event;
	if(!createUser()){
		testMessageBox(e,backurl);
		return;
	}
   var domain=$("#domain").val();
   recontent=$("#recontent").val();
   var flag=true;
	if (toVerify($("#recontent"))) {
		if(recontent.indexOf('<')>-1||recontent.indexOf('>')>-1){
			$("#recontentdiv").html("<span class='warning'>不能有特殊字符</span>");
			return false;
		}
//		var checkCode=$("#checkCode").val();
//		var code=getCookieCode('actCode');
//		if(checkCode==''){
//			openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请输入验证码！');
//			return false;
//		}else if(checkCode.toUpperCase()!=code.toUpperCase()){
//			openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','验证码输入错误！');
//			return false;
//		}
		jQuery.ajax({
			type: "post",
			url: "/front/review/getWordContents.action",
			data:"",
			dataType: "text",
			cache: false,
			success: function(returndata){
			  if(returndata!=''){
				  g_contents=returndata.split(";")
				  if(g_contents[0]!=''){
					g_senstives = g_contents[0].split(',');
					var recon=checkSensitive(g_contents[0].split(','));
					if(recon==true){
					  if(g_contents[1]!=''){
						  g_suspicious=g_contents[1].split(",");
						  var flagSuspicious=checkSuspicious();
						  if(flagSuspicious==true){
							  onlySave();
						  }
					  }
					}
				  }else{
					  if(g_contents[1]!=''){
						  g_suspicious=g_contents[1].split(",");
						  var flagSuspicious=checkSuspicious();
						  if(flagSuspicious==true){
							  onlySave();
						  }
					  }
					  
				  }
			  }else{
				  onlySave();
			  }
	           }
	      	});
	}else
	{
		return false;
	}
}
function confirmSensitive(str){
		var r=new RegExp(str,"i");
		var rr=new RegExp(str.replace("，",","),"i");
		recontent=recontent.replace(r,"****");
		recontent=recontent.replace(rr,"****");
		var flagSensitive = checkSensitive();
		if(flagSensitive) {
			g_suspicious=g_contents[1].split(",");
			var flagsus=checkSuspicious();
			if(flagsus){
				onlySave();
			}
		}
}
function checkSensitive(){
	for(i=0;i<g_senstives.length;i++){
		if(recontent.replace(",","，").toLowerCase().indexOf(g_senstives[i].toLowerCase())>-1){
			var html1='<div class="L_Msg"><b class="miconB"></b>您的评论内容包含敏感词“<span style="color:#F00;">'+g_senstives[i]+'</span>”，确认发表，<br />该敏感词会以****代替。</div>'+
  			'<p style="text-align:right"><a href="#" class="FQFKbtn2 pop-close" onclick="confirmSensitive(\''+g_senstives[i]+'\')">确认发表</a>'+
  			'<a href="#" class="FQFKbtn2 pop-close">返回修改</a></p>';
			$().popBx({html:html1, title: '提示',width:'425px'}).open();
		  	return false;
		}
	}
	return true;
}
/**
 * 检查可疑词
 * @return
 */
function checkSuspicious(){
	  for(j=0;j<g_suspicious.length;j++){
		  if(recontent.replace(",","，").toLowerCase().indexOf(g_suspicious[j].toLowerCase())>-1){
			  var html2='<div class="L_Msg"><b class="miconB"></b>您的评论内容包含可疑词“<span style="color:#F00;">'+g_suspicious[j]+'</span>”，确认发表，该评论需由管理员审核，会影响在前台显示的时间。</div>'+
	  			'<p style="text-align:right"><a href="#" class="FQFKbtn2 pop-close" onclick="confirmSuspicious()">确认发表</a>'+
	  			'<a href="#" class="FQFKbtn2 pop-close">返回修改</a></p>';
			  	$().popBx({html:html2, title: '提示',width:'425px'}).open();
			  	return false;
		  }
	  }
	  return true;
}
/**
 * 确认带有可疑词的评论去发表（需后台审核）
 * @return
 */
function confirmSuspicious(){
	auditing=2;//需要审核
	onlySave();
}
/**
 * 保存回复
 * @return
 */
function onlySave(){
	  $("#replySave").removeClass("submit").addClass("tip");
		var reviewid=$("#replyReviewId").val();
		var goodsId=$("#replyGoodsId").val();
		var productId=$("#productId").val();
		var identifyingCode=$("#checkCode").val();
		jQuery.ajax({
			type: "post",
			url: "/front/review/saveReply.action",
			data:"recontent="+encodeURIComponent(encodeURIComponent(recontent))+"&reviewId="+reviewid+"&auditing="+auditing+"&identifyingCode="+encodeURIComponent(encodeURIComponent(identifyingCode)),
			dataType: "text",
			cache: false,
			success: function(returndata){
			       if(returndata==1){
			    	   openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','添加评论回复成功，谢谢参与！',"/front/review/reviewList.action?productId="+productId+"&goodsId="+goodsId+"&flag=all");
			       }else if(returndata==2){//您没有发表回复的权限
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','您没有发表回复的权限！');
						return false;
			       }else if(returndata==3){
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','您提交的回复与网站规则不符，回复未能发布！');
						return false;
			       }else if(returndata==4){//回复的内容为空
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','回复的内容不能为空！');
						return false;
			       }else if(returndata==5){//您填写的回复内容重复
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','您填写的回复内容重复！');
						return false;
			       }else if(returndata==6){//验证码为空
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请输入验证码！');
						return false;
			       }else if(returndata==7){//验证码不正确
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','验证码输入错误！');
						return false;
			       }else if(returndata==8){//redis不正确
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','系统繁忙！');
						return false;
			       }
			       else{
			    	   window.location.href=domain+"/errorpage.html";
			       }
	           }
	      	});
}
function searchAdvisory(goodsId,consultationType,productId){
	window.location.href="/front/advisory/advisoryList.action?productId="+productId+"&goodsId="+goodsId+"&consultationType="+consultationType+"&pageIndex=1";
}
/**
 * 点击评论部分有用
 * @param pId
 * @param consultationType
 * @return
 */
function useful(reviewId){
	var exp=new Date();
	var exp2=new Date();
	exp2.setTime(exp2.getTime()+1800000);
	var flag=false;
	var cookies = document.cookie;
	 var hasreviewTime = cookies.indexOf(reviewId+'=');
	 if(hasreviewTime==-1){
			document.cookie =reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
			flag=true;
	 }
	 else{
		 var start = cookies.indexOf(reviewId+"=")+reviewId.length+1;
		    var end = cookies.indexOf(';', start);
			if(end==-1){
				end= cookies.length;
			}
			var idStr = cookies.substring(start, end);
			if((Date.parse(exp.toGMTString())-idStr)>1800000){
				document.cookie =reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
				flag=true;
			}
			else{
				openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请您稍后进行评价！');
				return false;
			}
	 }
	 if(flag){
		 jQuery.ajax({
				type: "POST",
				url: "/front/advisory/addYes.action",
				data:"reviewId="+reviewId,
				dataType: "text",
				cache: false,
				success: function(returndata){
			 
				     if(returndata!=null){
				    	 var _data = eval('[' + returndata + ']');
				    	 $(("#"+_data[0].reviewId+'useful')).html("<span>有用("+_data[0].yes+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','感谢您对1号药网的支持！');
				    	
				     }
		           }
		      	});
	 }
}
/**
 * 点击评论部分没有用
 * @param pId
 * @param consultationType
 * @return
 */
function unuseful(reviewId){
	var exp=new Date();
	var exp2=new Date();
	exp2.setTime(exp2.getTime()+1800000);
	var flag=false;
	var cookies = document.cookie;
	 var hasreviewTime = cookies.indexOf(reviewId+'=');
	 if(hasreviewTime==-1){
			document.cookie =reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
			flag=true;
	 }
	 else{
		 var start = cookies.indexOf(reviewId+"=")+reviewId.length+1;
		    var end = cookies.indexOf(';', start);
			if(end==-1){
				end= cookies.length;
			}
			var idStr = cookies.substring(start, end);
			if((Date.parse(exp.toGMTString())-idStr)>1800000){
				document.cookie =reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
				flag=true;
			}
			else{
				openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请您稍后进行评价！');
				return false;
			}

	 }
	 if(flag){
		 jQuery.ajax({
				type: "POST",
				url: "/front/advisory/addNo.action",
				data:"reviewId="+reviewId,
				dataType: "text",
				cache: false,
				success: function(returndata){
				     if(returndata!=null){
				    	 var _data = eval("["+returndata+"]");
				    	 $(("#"+_data[0].reviewId+'unuseful')).html("<span>没用("+_data[0].no+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','感谢您对1号药网的支持！');
				    	
				     }
		           }
		      	});
	 }
}

//------------一下是咨询
function checkadvisoryContent(){
	$("#advisoryContent").removeClass("ntc");
	if($("#advisoryContent").val()=="请填写咨询内容，文字长度在3-250字之间"){
		$("#advisoryContent").val("");
	}
	
}
function saveAdvisory(e,backurl,status){
	 //e =  window.event;
	if(!createUser()){
		backurl+="&"+Math.random()+"#r";
		testMessageBox(e,backurl);	
		return;
	}
	var flag=false;
	var exp=new Date();
	var exp2=new Date();
	exp2.setTime(exp2.getTime()+1800000);
	var cookies = document.cookie;
	var goodsId=$("#advisoryGoodsId").val();
	var domain=$("#domain").val();
	 var hasaddadvisoryTime = cookies.indexOf("addadvisory"+goodsId+'=');
	 if(hasaddadvisoryTime==-1){
		 flag=true;
	 }else{
		 var start = cookies.indexOf("addadvisory"+goodsId+'=')+goodsId.length+12;
		    var end = cookies.indexOf(';', start);
			if(end==-1){
				end= cookies.length;
			}
			var idStr = cookies.substring(start, end);
			if((Date.parse(exp.toGMTString())-idStr)>1800000){
				
				flag=true;
			}else{
				 openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请您稍后进行咨询！');
				return false;
			}
	 }
	 			if(flag){
             	   var advisoryType=$('#consultationTypec').val();
  		    		var advisoryContent=$.trim($("#advisoryContent").val());
  		    		var myObj=document.getElementsByName("consultationType2");
  					for( var i=0;i<myObj.length;i++){
  					if(myObj[i].checked)break;
  					};
  					if(i>=myObj.length){
  					openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请选择咨询类型！');
  		  		    			return false;
  					}	
  		    			if (toVerify($("#advisoryContent"))) {
  		    				$("#advisorySave").removeClass("submit").addClass("tip");
  		    				var productId=$("#productId").val();
  		    			jQuery.ajax({
  			    			type: "get",
  			    			url: "/front/advisory/saveAdvisory.action",
  			    			data:"goodsId="+goodsId+"&advisoryContent="+encodeURIComponent(encodeURIComponent(advisoryContent))+"&advisoryType="+advisoryType,
  			    			dataType: "text",
  			    			cache: false,
  			    			success: function(returndata){
  			    			       if(returndata==1){
  			    			    	   document.cookie = "addadvisory"+goodsId+'='+Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
  			    			    	   openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','咨询提交成功，请等待管理员处理！',domain+'/product/'+productId+'.html');
  			    			       }else{
		    				    	   window.location.href=domain+"/errorpage.html";
		    				       }
  			    	           }
  			    	      	});
  		    		}else
  		    		{
  		    			return false;
  		    		}
	 			}
}
function good(reviewId){
	var exp=new Date();
	var exp2=new Date();
	exp2.setTime(exp2.getTime()+1800000);
	var flag=false;
	var cookies = document.cookie;
	 var hasreviewTime = cookies.indexOf(reviewId+'=');
	 if(hasreviewTime==-1){
			document.cookie = reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
			flag=true;
	 }
	 else{
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
				 openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请您稍后进行评价！');
				return false;
			}

	 }
	 if(flag){
		 jQuery.ajax({
				type: "POST",
				url: "/front/advisory/addYes.action",
				data:"reviewId="+reviewId,
				dataType: "text",
				cache: false,
				success: function(returndata){
				     if(returndata!=null){
				    	
				    	 var _data = eval("["+returndata+"]");
				    	
				    	 $(("#"+_data[0].reviewId+'yes')).html("<span>满意("+_data[0].yes+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','感谢您对1号药网的支持！');
				    	
				     }
		           }
		      	});
	 }
	
}

function no(reviewId){
	var exp=new Date();
	var exp2=new Date();
	exp2.setTime(exp2.getTime()+1800000);
	var flag=false;
	var cookies = document.cookie;
	 var hasreviewTime = cookies.indexOf(reviewId+'=');
	 if(hasreviewTime==-1){
			document.cookie = reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
			flag=true;
	 }
	 else{
		 var start = cookies.indexOf(reviewId+"=")+reviewId.length+1;
		    var end = cookies.indexOf(';', start);
			if(end==-1){
				end= cookies.length;
			}
			var idStr = cookies.substring(start, end);
			if((Date.parse(exp.toGMTString())-idStr)>1800000){
				document.cookie = reviewId+'=' + Date.parse(exp.toGMTString())+';expires='+exp2.toGMTString()+';path=/';
				flag=true;
			}else{
				openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','请您稍后进行评价！');
				return false;
			}

	 }
	 if(flag){
			jQuery.ajax({
				type: "POST",
				url: "/front/advisory/addNo.action",
				data:"reviewId="+reviewId,
				dataType: "text",
				cache: false,
				success: function(returndata){
				     if(returndata!=null){
				    	 var _data = eval("["+returndata+"]");
				    	 $(("#"+_data[0].reviewId+'no')).html("<span>不满意("+_data[0].no+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','感谢您对1号药网的支持！');
				    	
				     }
		           }
		      	});
	 }

}
var docEle = function()

{
    return document.getElementById(arguments[0]) || false;
}

function openNewDiv(imgurl,content,url)
{
    var m = "mask";
    var _id='popup';
    if (docEle(_id)) document.body.removeChild(docEle(_id));
    if (docEle(m)) document.body.removeChild(docEle(m));
   
    //mask遮罩层

    var newMask = document.createElement("div");
    newMask.id = m;
    newMask.style.position = "absolute";
    newMask.style.zIndex = "9900";
    _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
    _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
    newMask.style.width = _scrollWidth + "px";
    newMask.style.height = _scrollHeight + "px";
    newMask.style.top = "0px";
    newMask.style.left = "0px";
    newMask.style.background = "#33393C";
    newMask.style.filter = "alpha(opacity=40)";
    newMask.style.opacity = "0.40";
    newMask.innerHTML='<iframe style="position: absolute; top: 0pt; left: 0pt; width: 98%; height: 100%;"></iframe>';
    document.body.appendChild(newMask);
   
    //新弹出层

    var newDiv = document.createElement("div");
    newDiv.id = _id;
    newDiv.style.position = "absolute";
    newDiv.style.zIndex = "9999";
    newDivWidth = 485;
    newDivHeight = 165;
    newDiv.style.width = newDivWidth + "px";
    newDiv.style.height = newDivHeight + "px";
    newDiv.className='DialogueBG';
    newDiv.style.top = (document.body.scrollTop + document.body.clientHeight/2 - newDivHeight/2) + "px";
    newDiv.style.left = (document.body.scrollLeft + document.body.clientWidth/2 - newDivWidth/2) + "px";
    newDiv.style.background = "#EFEFEF";
    newDiv.style.border = "1px solid #860001";
    newDiv.style.padding = "5px";
    newDiv.innerHTML ='<div class="DialogueBox"><div class="top"><span>温馨提示</span><span class="Close"><img src="http://p4.51mdq.com/images/DialogueBoxClose.jpg" id="closepopup"></span></div>'
    	+'<div class="ConBox"><div class="Con"><img src="'+imgurl+'"><span>'+content+'</span></div><p id="timer" style="text-align:center;">5秒后自动关闭</p>'
    	+'</div></div>';
    
    document.body.appendChild(newDiv);

	 //newDiv.style.top = (myHeight ) / 3 + document.documentElement.scrollTop+"px";
    // newDiv.style.left = (myWidth ) / 3+"px";
     newDiv.style.left=($(window).width()-452)/2+$(window).scrollLeft()+ "px";
     newDiv.style.top=($(window).height()-200)/2+$(window).scrollTop()+ "px";
     
   
    //弹出层滚动居中

    function newDivCenter()
    {
    	newDiv.style.left=($(window).width()-452)/2+$(window).scrollLeft()+ "px";
	     newDiv.style.top=($(window).height()-200)/2+$(window).scrollTop()+ "px";
    }
    if(document.all)
    {
    	
        window.attachEvent("onscroll",newDivCenter);
    }
    else
    {
        window.addEventListener('scroll',newDivCenter,false);
    }
   
    //关闭新图层和mask遮罩层

    var newA = document.getElementById("closepopup");
    
    newA.onclick = function()
    {
        if(document.all)
        {
            window.detachEvent("onscroll",newDivCenter);
        }
        else
        {
            window.removeEventListener('scroll',newDivCenter,false);
        }
        document.body.removeChild(docEle(_id));
        document.body.removeChild(docEle(m));
        if(url!=undefined){
        	window.location.href=url;
        }
        return false;
    }
    if(url!=undefined){
    	count(5,url);
    }else{
    	count(5);
    }
}
function count(countFlag,url){
	if(document.getElementById("timer")!=null){
		document.getElementById("timer").innerHTML =countFlag+"秒自动关闭！";
	}
	if(countFlag==0){
		   function newDivCenter()
		    {
			   newDiv.style.left=($(window).width()-452)/2+$(window).scrollLeft()+ "px";
			     newDiv.style.top=($(window).height()-200)/2+$(window).scrollTop()+ "px";
		    }
		if(document.all)
        {
            window.detachEvent("onscroll",newDivCenter);
        }
        else
        {
            window.removeEventListener('scroll',newDivCenter,false);
        }
		if(document.getElementById("popup")!=null){
			
			document.body.removeChild(docEle("popup"));
		}
		if(document.getElementById("mask")){
			
			document.body.removeChild(docEle("mask"));
		}
		if(url!=undefined){
        	window.location.href=url;
        }
        return false;
		
	}else{
		countFlag--;
		if(document.getElementById("timer")!=null){
			if(url!=undefined){
				setTimeout("count("+countFlag+",'"+url+"')",1000);
			}
			else{
				setTimeout("count("+countFlag+")",1000);
			}
		}
		
		
	}
	;
}
function  FilterWords(obj){
	var patten = "我靠|吃屎|我日|你妈逼|你大爷|垃圾|鸡巴|傻逼|煞笔|傻比|龌龊|你妈的|他妈的|狗屁|日你|靠你|奶奶的|MB|CNM|TMD|FUCK|SHIT|2B|SB|JB|NND|MLGB|MNGB|操蛋|操你|操1号药网|操!|操！";
	var na=obj.val();
	na=na.replace(/\s+/g,"");
	if(na.match(patten)) {
		var imgstr = "<img src='http://p1.51mdq.com/images/Wro.gif'/>";
		$("#recontentdiv").html(imgstr+"<span class='cRed'>您输入的内容包含敏感词汇，请您重新输入！</span>");
		return false;
		}
	return true;
}
function getCookieCode(name){
	   var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') c = c.substring(1, c.length);
			if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
		}
		return null;
}