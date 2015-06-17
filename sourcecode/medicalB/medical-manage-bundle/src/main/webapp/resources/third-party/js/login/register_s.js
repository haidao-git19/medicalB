	var flag=true;
	$(function(){
		var options={  
		        type:"post",  //提交方式    
		        dataType:"json", //数据类型    
		        url:ctx + "/anon/userSave", //请求url   
		        success:function(responseText, statusText){
		        	if(responseText.info){
		        		flag=false;
		        		$("input[id='userId']").attr("value",responseText.userId);
		        		$("#registerDoneBtn").unbind();
		        		$("input").attr("disabled","disabled");
			        	$("#finish").text("正在跳转...");
			        	window.location.href=ctx+"/anon/clientCenter?isOauth=1";
		        	}else{
		        		alert(responseText.error);
		        	}
		        	
		        },//提交成功的回调函数  
		       }  
		$("#registerSecondForm").ajaxForm(options);  
		$("#registerDoneBtn").on('click',function(){
			if(flag==false){
				return;
			}
			var nickName=$.trim($("#nickName").val());
			var pwd=$.trim($("#password").val());
			var rpwd=$.trim($("#rpassword").val());
			if(!nickName){
				alert("昵称不能为空");
				return;
			}else if(nickName.length>10){
				alert("昵称长度不能大于10");
				return;
			}
			if(!pwd||!rpwd){
				alert("密码或确认密码不能为空!");
				return;
			}else if(pwd!=rpwd){
				alert("两次密码输入不一致!");
				return;
			}else if(pwd==rpwd){
				if(pwd.length<6||rpwd.length<6){
					alert("密码长度不能小于6!");
					return;
				}else if(isValidate(pwd)==false){
					alert("密码不能包含特殊字符!");
					return;
				}
			}
			$("#registerSecondForm").ajaxSubmit(options);
			return false;
		});
	});
	
	function isValidate(str){
        var reg =/^[a-zA-Z0-9]+$/;
        var testFlag=reg.test(str);
        return testFlag;
    } 
	
    //图片上传预览    IE是用了滤镜。
    function previewImage(file)
    {
    	var fileSize;
      var div = document.getElementById('preview');
      if (file.files && file.files[0])
      {
    	  fileSize=file.files[0].size;
    	  if (file.files[0].type.indexOf("image") == 0) {
				if (fileSize > 100*1024) {
					alert("图片大小过大,应小于100k");
					return;
				}
			} else {
				alert('文件"' + file.files[0].name + '"不是图片。');	
				return;
			}
      	  
          div.innerHTML ='<img id="iconImg" id="iconImg" width="100" height="100">';
          var img = document.getElementById('iconImg');
          var reader = new FileReader();
          reader.onload = function(evt){img.src = evt.target.result;}
          reader.readAsDataURL(file.files[0]);
      }
      var options={  
		        type:"post",  //提交方式    
		        dataType:"json", //数据类型    
		        url:ctx + "/anon/uploadIcon", //请求url   
		        success:function(responseText, statusText){
		        	if(responseText.code){
		        		if(responseText.code!="0"){
		        			$("input[id='userId']").attr("value",responseText.userId);
		        		}
		        	}
		        },//提交成功的回调函数  
		       }  
		$("#iconForm").ajaxForm(options);
		$("#iconForm").ajaxSubmit(options);
    }
    
    function clacImgZoomParam( maxWidth, maxHeight, width, height ){
        var param = {top:0, left:0, width:width, height:height};
        if( width>maxWidth || height>maxHeight )
        {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;
             
            if( rateWidth > rateHeight )
            {
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
            }else
            {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
         
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }