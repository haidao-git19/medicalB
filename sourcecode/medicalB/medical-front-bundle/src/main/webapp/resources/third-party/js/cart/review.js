var g_senstives = [];
var g_suspicious = [];
var g_contents=[];
var auditing=1;
var  recontent="";
function check(){
	if($.trim($("#recontent").val())=='����д�ظ�������������5-300��֮��'){
		$("#recontent").val("");
	}
	$("#recontent").removeClass("ntc");
}
function checkspecial(){
	var con=$("#recontent").val();
	if(con.indexOf('<')>-1||con.indexOf('>')>-1){
		$("#recontentdiv").html("<span class='warning'>�����������ַ�</span>");
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
			$("#recontentdiv").html("<span class='warning'>�����������ַ�</span>");
			return false;
		}
//		var checkCode=$("#checkCode").val();
//		var code=getCookieCode('actCode');
//		if(checkCode==''){
//			openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','��������֤�룡');
//			return false;
//		}else if(checkCode.toUpperCase()!=code.toUpperCase()){
//			openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','��֤���������');
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
		var rr=new RegExp(str.replace("��",","),"i");
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
		if(recontent.replace(",","��").toLowerCase().indexOf(g_senstives[i].toLowerCase())>-1){
			var html1='<div class="L_Msg"><b class="miconB"></b>�����������ݰ������дʡ�<span style="color:#F00;">'+g_senstives[i]+'</span>����ȷ�Ϸ���<br />�����дʻ���****���档</div>'+
  			'<p style="text-align:right"><a href="#" class="FQFKbtn2 pop-close" onclick="confirmSensitive(\''+g_senstives[i]+'\')">ȷ�Ϸ���</a>'+
  			'<a href="#" class="FQFKbtn2 pop-close">�����޸�</a></p>';
			$().popBx({html:html1, title: '��ʾ',width:'425px'}).open();
		  	return false;
		}
	}
	return true;
}
/**
 * �����ɴ�
 * @return
 */
function checkSuspicious(){
	  for(j=0;j<g_suspicious.length;j++){
		  if(recontent.replace(",","��").toLowerCase().indexOf(g_suspicious[j].toLowerCase())>-1){
			  var html2='<div class="L_Msg"><b class="miconB"></b>�����������ݰ������ɴʡ�<span style="color:#F00;">'+g_suspicious[j]+'</span>����ȷ�Ϸ������������ɹ���Ա��ˣ���Ӱ����ǰ̨��ʾ��ʱ�䡣</div>'+
	  			'<p style="text-align:right"><a href="#" class="FQFKbtn2 pop-close" onclick="confirmSuspicious()">ȷ�Ϸ���</a>'+
	  			'<a href="#" class="FQFKbtn2 pop-close">�����޸�</a></p>';
			  	$().popBx({html:html2, title: '��ʾ',width:'425px'}).open();
			  	return false;
		  }
	  }
	  return true;
}
/**
 * ȷ�ϴ��п��ɴʵ�����ȥ�������̨��ˣ�
 * @return
 */
function confirmSuspicious(){
	auditing=2;//��Ҫ���
	onlySave();
}
/**
 * ����ظ�
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
			    	   openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','������ۻظ��ɹ���лл���룡',"/front/review/reviewList.action?productId="+productId+"&goodsId="+goodsId+"&flag=all");
			       }else if(returndata==2){//��û�з���ظ���Ȩ��
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','��û�з���ظ���Ȩ�ޣ�');
						return false;
			       }else if(returndata==3){
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','���ύ�Ļظ�����վ���򲻷����ظ�δ�ܷ�����');
						return false;
			       }else if(returndata==4){//�ظ�������Ϊ��
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','�ظ������ݲ���Ϊ�գ�');
						return false;
			       }else if(returndata==5){//����д�Ļظ������ظ�
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','����д�Ļظ������ظ���');
						return false;
			       }else if(returndata==6){//��֤��Ϊ��
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','��������֤�룡');
						return false;
			       }else if(returndata==7){//��֤�벻��ȷ
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','��֤���������');
						return false;
			       }else if(returndata==8){//redis����ȷ
			    	   $("#replyVcode").attr("src","http://www.111.com.cn/front/identify/getSecurityCode.action?t="+new Date().getTime());
			    	   openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','ϵͳ��æ��');
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
 * ������۲�������
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
				openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','�����Ժ�������ۣ�');
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
				    	 $(("#"+_data[0].reviewId+'useful')).html("<span>����("+_data[0].yes+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','��л����1��ҩ����֧�֣�');
				    	
				     }
		           }
		      	});
	 }
}
/**
 * ������۲���û����
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
				openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','�����Ժ�������ۣ�');
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
				    	 $(("#"+_data[0].reviewId+'unuseful')).html("<span>û��("+_data[0].no+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','��л����1��ҩ����֧�֣�');
				    	
				     }
		           }
		      	});
	 }
}

//------------һ������ѯ
function checkadvisoryContent(){
	$("#advisoryContent").removeClass("ntc");
	if($("#advisoryContent").val()=="����д��ѯ���ݣ����ֳ�����3-250��֮��"){
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
				 openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','�����Ժ������ѯ��');
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
  					openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','��ѡ����ѯ���ͣ�');
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
  			    			    	   openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','��ѯ�ύ�ɹ�����ȴ�����Ա����',domain+'/product/'+productId+'.html');
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
				 openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','�����Ժ�������ۣ�');
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
				    	
				    	 $(("#"+_data[0].reviewId+'yes')).html("<span>����("+_data[0].yes+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','��л����1��ҩ����֧�֣�');
				    	
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
				openNewDiv('http://p4.51mdq.com/images/Boderh2Bg.gif','�����Ժ�������ۣ�');
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
				    	 $(("#"+_data[0].reviewId+'no')).html("<span>������("+_data[0].no+")</span>");
				    	 openNewDiv('http://p4.51mdq.com/images/ConSc.jpg','��л����1��ҩ����֧�֣�');
				    	
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
   
    //mask���ֲ�

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
   
    //�µ�����

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
    newDiv.innerHTML ='<div class="DialogueBox"><div class="top"><span>��ܰ��ʾ</span><span class="Close"><img src="http://p4.51mdq.com/images/DialogueBoxClose.jpg" id="closepopup"></span></div>'
    	+'<div class="ConBox"><div class="Con"><img src="'+imgurl+'"><span>'+content+'</span></div><p id="timer" style="text-align:center;">5����Զ��ر�</p>'
    	+'</div></div>';
    
    document.body.appendChild(newDiv);

	 //newDiv.style.top = (myHeight ) / 3 + document.documentElement.scrollTop+"px";
    // newDiv.style.left = (myWidth ) / 3+"px";
     newDiv.style.left=($(window).width()-452)/2+$(window).scrollLeft()+ "px";
     newDiv.style.top=($(window).height()-200)/2+$(window).scrollTop()+ "px";
     
   
    //�������������

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
   
    //�ر���ͼ���mask���ֲ�

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
		document.getElementById("timer").innerHTML =countFlag+"���Զ��رգ�";
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
	var patten = "�ҿ�|��ʺ|����|�����|���ү|����|����|ɵ��|ɷ��|ɵ��|����|�����|�����|��ƨ|����|����|���̵�|MB|CNM|TMD|FUCK|SHIT|2B|SB|JB|NND|MLGB|MNGB|�ٵ�|����|��1��ҩ��|��!|�٣�";
	var na=obj.val();
	na=na.replace(/\s+/g,"");
	if(na.match(patten)) {
		var imgstr = "<img src='http://p1.51mdq.com/images/Wro.gif'/>";
		$("#recontentdiv").html(imgstr+"<span class='cRed'>����������ݰ������дʻ㣬�����������룡</span>");
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