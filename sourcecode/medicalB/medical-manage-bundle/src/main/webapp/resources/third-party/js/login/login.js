$(function(){
		$("#loginBtn").on({
			click:function(){
				$("#loginBtn").css("background-color","#ff4301");
			},
			mouseup:function(){
				var loginName=$("#loginName").val();
				var password=$("#password").val();
				$.ajax({
					url:ctx+"/anon/loginValidate",
					type:"post",
					data:{"loginName":loginName,"password":password,"randPref":randPref},
					dataType:"json",
					success:function(data){
						$("#loginBtn").css("background-color","#ff6100");
						if(data.success!=null&&data.success!=''){
							$("#loginName").attr("readonly","readonly");
							$("#password").attr("readonly","readonly");
							if(returnUrl){
								window.location.href=returnUrl;
							}else{
								alert("登录成功。");
								window.location.href=ctx+"/anon/buyList";
							}
						}else if(data.fail){
							alert(data.fail);
						}else{
							alert(data.error);
						}
					}
				});
			}
		});
});

