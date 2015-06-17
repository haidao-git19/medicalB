	var c=60;
	var t;
	var openId='${openId}';
	var regexPattern="^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
	var flag=false;
	$(function(){
		getValiCode();
		$("#findPwdNextBtn").on('click',function(){
			var loginName=$("#loginName").val();
			var valiCode=$("#valiCode").val();
			if(!loginName){
				alert("请输入手机号码!");
				return;
			}else if(!valiCode){
				alert("请输入验证码!");
				return;
			}
			$.ajax({
				url:ctx+"/anon/checkValiCode",
				type:"post",
				data:{"loginName":loginName,"valiCode":valiCode},
				dataType:"json",
				success:function(data){
					if(data.info){
						$("input").attr("disabled","disabled");
						window.location.href=ctx+"/anon/findPwdNext?loginName="+loginName+"&randPref="+randPref;
					}else{
						alert("验证码错误!");
					}
				}
			});
		});
	});
	
	function getValiCode(){
		$("#getValiCode").bind('click',function(){
			var loginName=$("#loginName").val();
			if(!loginName){
				alert("请填写手机号!");
				return;
			}else if(!loginName.match(regexPattern)){
				alert("请填写正确的手机号!");
				return;
			}else{
				$.ajax({
					url:ctx+"/anon/checkAccount2",
					type:"post",
					data:{"loginName":loginName},
					dataType:"json",
					success:function(data){
						if(data.notExistInfo){
							alert(data.notExistInfo);
							$("#valiCode").attr("disabled","disabled");
						}else if(!data.notExistInfo){
							sendValiCode(loginName);
						}
					}
				});
			}
		});
	}
	
	//发送验证码
	function sendValiCode(loginName){
		timeCount();
		$.ajax({
			url:ctx+"/anon/sendValiCode",
			type:"post",
			data:{"loginName":loginName},
			dataType:"json",
			success:function(data){
				if(data.info){
					alert("已发送!");
				}else{
					alert("发送失败!")
				}
			}
		});
		$("#valiCode").removeAttr("disabled");
	}
	
	function timeCount(){
		$("#getValiCode").unbind().html("<a>"+c+"秒后重新获取</a>").css("background-color","#cccccc");
		c=c-1;
		if(c==-2){
			stopCount();
			getValiCode();
			return;
		}
		t=setTimeout("timeCount()",1000);
	}
	
	function stopCount(){
		clearTimeout(t);
		c=60;
		$("#getValiCode").html("<a>获取验证码</a>").css("background-color","#ff6100");
	}