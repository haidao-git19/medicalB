/*
 *  weekFlag	周标记: 0本周   1下周
 *  weekNum   周几
 *  dayFlag	日标记: 1上午   2下午   3晚上
 */

function setting(weekFlag, weekNum, dayFlag, id){
	window.location.href = ctx+'/duty/setting?doctorID='+$("#doctorId").val()+'&weekFlag='+weekFlag+'&weekNum='+weekNum+'&dayFlag='+dayFlag+'&id='+id;
}

function cancel(weekFlag, weekNum, dayFlag, id){
	$.ajax({
		url : ctx + '/duty/cancel?doctorID='+$("#doctorId").val()+'&weekFlag='+weekFlag+'&weekNum='+weekNum+'&dayFlag='+dayFlag+'&id='+id,
		data : '',
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			if(data.flag == true) {
				$('#'+data.eid).empty().append(data.html);
			}
		}
	});
}

function formSumbit() {
	$.ajax({
		type : "post",
		url : ctx + '/duty/commit',
		data : $('#postForm').serialize(),
		dataType : "json",
		success : function(data) {
			if (data.flag == true) {
				window.location.href = ctx + '/duty/initSetting?doctorId='+data.doctorId;
			}
		}
	})
}

function toDoctorList() {
	window.location.href = ctx + '/doctor';
}