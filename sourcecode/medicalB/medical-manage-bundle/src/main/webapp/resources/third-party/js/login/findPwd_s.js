	var loginName='${loginName}';
	$(function(){
		$("#confirmNewPwdBtn").on('click',function(){
			var pwd=$("#password").val().replace(/^\s+|\s+$/g,"");
			var rpwd=$("#rpassword").val().replace(/^\s+|\s+$/g,"");
			if(!pwd||!rpwd){
				alert("新密码或确认密码不能为空!");
			}else if(pwd!=rpwd){
				alert("两次输入不一致!");
			}else if(pwd==rpwd){
				if(pwd.length<6||rpwd.length<6){
					alert("密码长度不能小于6!");
				}else if(isValidate(pwd)==false){
					alert("新密码不能包含特殊字符!");
					return;
				}else{
					$.ajax({
						url:ctx+"/anon/confirmNewPwd",
						type:"post",
						data:{"loginName":loginName,"password":pwd},
						dataType:"json",
						success:function(data){
							if(data.info){
								alert(data.info);
								$("input").attr("disabled","disabled");
								$("#confirmNewPwdBtn").unbind().text("正在跳转...");
								window.location.href=ctx+"/anon/login?isOauth=1&randPref="+randPref;
							}else{
								alert(data.error);
							}
						}
					});
				}
			}
		});
	});
	
	function isValidate(str){
        var reg =/^[a-zA-Z0-9]+$/;
        var testFlag=reg.test(str);
        return testFlag;
    } 