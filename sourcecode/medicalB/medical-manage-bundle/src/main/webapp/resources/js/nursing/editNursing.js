

function toupdateNursing(id){
	   window.location.href=ctx+'/nursing/initAddOrUpdate?adviceID='+id; 
}

function showNursing(id){
	   window.location.href=ctx+'/nursing/showNursing?adviceID='+id; 
}


function deleteNursing(id){

	 var url=ctx+'/nursing/del';
	 var param='adviceID='+id;
	 if(confirm("确定要删除?")){
		 //ajax 删除事件
			$.ajax({
					type: "post",
		            url: url,
		            data: param,
		            dataType: "json",
		            success: function(data){
		            	alert('删除成功');
		            	location.reload()
		               }
		            })
	 }

}

function formSumbit(){

	 var url=ctx+'/nursing/addOrUpdate';
	 //ajax form 提交
	$.ajax({
			type: "post",
            url: url,
            data: $('#postForm').serialize(),
            dataType: "json",
            success: function(data){
            	if(data.flag==false){
            	    alert(data.msg);
            	}else{
            		//转向列表
            		 window.location.href=ctx+'/nursing.do';  
            	}
               }
            })

}


function uploadImg(){
	
	$('#imgFile').click();
}

 
