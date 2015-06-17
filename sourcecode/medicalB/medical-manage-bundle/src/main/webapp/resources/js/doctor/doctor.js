var table;

$(function(){
	query();
	
	$("#query_btn").on("click", function(){
		table.fnDraw();
	});
	
	if(!$("#isVisit").val()){
		$("#isVisit").attr("disabled","disabled");
	}
});

function query() {
	if(table == undefined){
		table = $("#doctor_table").myDataTable({
			sAjaxSource:ctx+"/doctor/query",
			paramSelector:"#realName",
			"bSort": false,
			aoColumns:[
			           {sWidth : 80}, {sWidth : 200}, {sWidth : 100}, {sWidth : 80}, {sWidth : 60}, {sWidth : 60}, {sWidth : 60}, {sWidth : 60}, 
			           {
			        	   "fnRender" : function(obj) {
			        		   var id = obj.aData[8];
			        		   return "<a href='javascript:edit({0});'>编辑</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:del({0});'>删除</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:diseaseSetting({0});'>病种设置</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:feeSetting({0});'>资费设置</a>".format(id)+"&nbsp;|&nbsp"
			        		   +"<a href='javascript:dutySetting({0});'>值班设置</a>".format(id);
			        	   }
			           }
			           ]
		});
	}else{
		table.fnPageChange("first", true);
	}
}

function loadHospital(areaId) {
	if(areaId == 0){
		$("#select_hospital").empty();
	}
	
	$.ajax({
		url : ctx + '/hospital/area_hospitals',
		data : [ {
			name : 'areaId',
			value : areaId
		}],
		type : 'POST',
		dataType : 'html',
		success : function(data) {
			$("#select_hospital").empty().append(data);
		}
	});
}

function secondLevelSection(sectionId) {
	if(sectionId == 0){
		$("#select_section").empty();
	}
	
	$.ajax({
		url : ctx + '/section/second_level_sections',
		data : [ {
			name : 'parentid',
			value : sectionId
		},{
			name : 'hospitalID',
			value : $("#select_hospital").val()
		},{
			name : 'level',
			value : 2
		}],
		type : 'POST',
		dataType : 'html',
		success : function(data) {
			$("#select_section").empty().append(data);
		}
	});
}

function edit(id){
	window.location.href=ctx+'/doctor/initAddOrUpdate?doctorID='+id; 
}

function del(id){
	if (confirm("确定要删除?")) {
		$.ajax({
			type : "post",
			url : ctx + '/doctor/del',
			data : 'doctorID=' + id,
			dataType : "json",
			success : function(data) {
				alert('删除成功');
				location.reload()
			}
		})
	}
}

function formSumbit() {
	var isVabl=$("#isVisit").attr("disabled");
	if(typeof(isVabl)=='undefined'||isVabl==false){
		if($("#isVisit").val()==''){
			myAlert("系统提示","请选择上门服务选项");
			return;
		}
	}
	$.ajax({
		type : "post",
		url : ctx + '/doctor/addOrUpdate',
		data : $('#postForm').serialize(),
		dataType : "json",
		async:false,
		success : function(data) {
			if (data.flag == false) {
				alert(data.msg);
			} else {
				window.location.href = ctx + '/doctor';
			}
		}
	})
}

//病种设置
function diseaseSetting(id) {
	window.location.href = ctx + '/doctor/diseaseSetting?doctorID='+id;
}

function commitDiseaseSetting() {
	$.ajax({
		type : "post",
		url : ctx + '/doctor/commitDiseaseSetting',
		data : $('#postForm').serialize(),
		dataType : "json",
		success : function(data) {
			if (data.flag == false) {
				alert(data.msg);
			} else {
				window.location.href = ctx + '/doctor';
			}
		}
	})
}

//值班设置
function dutySetting(id) {
	window.location.href = ctx + '/duty/initSetting?doctorId='+id;
}

//资费设置
function feeSetting(id) {
	window.location.href = ctx + '/doctorFee?doctorID='+id;
}

function uploadImg(){
	$('#imgFile').click();
}

/**
 * 上传图片
 */	
function uploadFile(){
	 var options = {  
		      dataType : "json",  
		      async: false,
		      success :showResponse,
		      error:showError,
		      timeout: 30000      //限制请求的时间，当请求大于3秒后，跳出请求
		  };
	 jQuery("#imgForm").ajaxSubmit(options);
}

//上传成功回调
var showResponse = function(data){
	if(data.flag==true){
		$('#avatar').val(data.name);
		$("#showImage").attr("src",ctx+'/showImg?fileName='+data.name);
		$('#_preview_fake').hide();
	}else{
		alert('上传失败请重试!')
	}
}

//上传失败
var showError = function(data){
    alert('上传失败, 请稍后重试!');
}

function onchangeHospital(obj){
	var hospitalID=obj.value;
	
	$("#firstLevelSection").empty().append("<option value='0'>--请选择--</option>");
	$("#select_section").empty().append("<option value='0'>--请选择科室--</option>");
	
	if(hospitalID!=''&&hospitalID!='0'){
		$.ajax({
			url:ctx+"/section/queryAimLevelSections",
			type:"post",
			data:{"hospitalID":hospitalID,"level":1},
			dataType:"json",
			success:function(data){
				if(data){
					$.each(data,function(i,s){
						$("#firstLevelSection").append("<option value='"+s.id+"'>"+s.attrname+"</option>")
					});
				}
			}
		});
	}
	
	$.ajax({
		url:ctx+"/hospital/queryHospital",
		type:"post",
		data:{"hospitalID":hospitalID},
		dataType:"json",
		success:function(data){
			if(data&&data.type=='0'){
				$("#isVisit").removeAttr("disabled");
			}else{
				$("#isVisit").children("option[value='']").attr("selected","selected");
				$("#isVisit").attr("disabled","disabled");
			}
		}
	});
}