$(function() {
	initValidate();
	
	$("input").keypress(function(event){
		if(event.which == 13){
			submit();
		}
	});

	$("#btn_save").click(function() {
		submit();
	});
});

function submit(){
	var ok = $("#pwd_form").valid();
	if (ok) {
		$("#btn_save").attr("disabled", "disabled");
		$.ajax({
			url : ctx + '/user/pwd/change',
			type : 'POST',
			dataType : 'json',
			data : $("#pwd_form").serializeArray(),
			complete : function() {
				$("#btn_save").removeAttr("disabled");
			},
			success : function() {
				myConfirm('', '修改完成,是否重新登录?', function() {
					top.location.href = ctx + '/logout';
				});
			}
		});
	}
}

function initValidate() {
	$("#pwd_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			oldPassword : {
				required : true
			},
			newPassword : {
				required : true,
				maxlength : 32
			},
			confirmPassword : {
				required : true,
				equalTo : '#newPassword'
			}
		},
		showErrors : function(errorMap, errorList) {
			$(this.currentForm).find("span.error").remove();
			if (errorList.length > 0) {
				var err = errorList[0];
				var el = $(err.element);
				var target = el.parentsUntil('form', '.controls');
				target.append("<span class='error'>{0}</span>".format(err.message));
			}
		}
	});
}
