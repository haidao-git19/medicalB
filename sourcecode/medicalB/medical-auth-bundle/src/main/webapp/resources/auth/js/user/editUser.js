$(function() {
	
	initEdit();
	initRoles();
	initProvince();
	initProducts();
	initPlatforms();
	initValidate();

	$("#btn_save").click(function() {
		submit();
	});

	if (readOnly) {
		$("input").attr("disabled", "disabled");
		$("select").attr("disabled", "disabled");
		$("#btn_save").hide();
	}
});

function initProducts(){
	var p = $('#productIds').multiselect({
		checkAllText : "全选",
		uncheckAllText : "全部取消",
		noneSelectedText : "请选择产品",
		selectedText : "已选择#项",
		minWidth: 170,
		selectedList : 4
	}).empty();
	p.multiselect("refresh");
	$.ajax({
		url : ctx + "/anon/getPermitProducts",
		type : 'POST',
		dataType : 'json',
		async : false,
		data : [{
			name : 'loginName',
			value : curUserLoginName
		}],
		success : function(data) {
			var userProductIds = $.map(userProducts,function(a){
				return a.id;
			})
			$.each(data,function(i,product){
				if($.inArray(product.id,userProductIds)<0){
					p.append("<option value='{1}'>{0}</option>".format(product.cnName,product.id));
				}else {
					p.append("<option value='{1}' selected='selected'>{0}</option>".format(product.cnName,product.id));
				}
			});
			p.multiselect("refresh");
		}
	})
	$("#productIds").change(function(){
		initPlatforms();
	});
}

function initPlatforms(){
	var p = $('#platformIds').multiselect({
		checkAllText : "全选",
		uncheckAllText : "全部取消",
		noneSelectedText : "请选择平台",
		selectedText : "已选择#项",
		minWidth: 170,
		selectedList : 4
	}).empty();
	p.multiselect("refresh");
	var productIds = $("#productIds").val();
	if(!productIds) return;
	$.ajax({
		url : ctx + "/anon/getPermitPlatforms",
		type : 'POST',
		dataType : 'json',
		async : false,
		data : [{
			name : 'loginName',
			value : curUserLoginName
		},{
			name : 'productIds',
			value : productIds
		}],
		success : function(data) {
			var userPlatformIds = $.map(userPlatforms,function(a){
				return a.id;
			})
			$.each(data,function(i,platform){
				if($.inArray(platform.id,userPlatformIds)<0){
					p.append("<option value='{1}'>{0}</option>".format(platform.name,platform.id));
				}else {
					p.append("<option value='{1}' selected='selected'>{0}</option>".format(platform.name,platform.id));
				}
			});
			p.multiselect("refresh");
		}
	})
}

function initProvince() {
	var p = $("#province").empty();
	$.ajax({
		url : ctx + "/area/permitProvinces",
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			if (data.length == 1) {
				p.empty();
			}
			var ids = [];
			$.each(data, function(i, a) {
				ids.push(a.id);
				p.append("<option value='{0}'>{1}</option>".format(a.aiid, a.name));
			});
			if ($.inArray(userProvinceId, ids) >= 0) {
				p.val(userProvinceId);
			}
			initCity();
		}
	});
	p.change(function() {
		initCity();
	});
}

function initCity() {
	var c = $("#city").empty();
	c.append("<option value=''>选择城市</option>");
	$.ajax({
		url : ctx + "/area/permitCitys",
		type : 'POST',
		dataType : 'json',
		data : [ {
			name : 'provinceId',
			value : $("#province").val()
		} ],
		success : function(data) {
			if (data.length == 1) {
				c.empty();
			}
			var ids = [];
			$.each(data, function(i, a) {
				ids.push(a.id);
				c.append("<option value='{0}'>{1}</option>".format(a.aiid, a.name));
			});
			if ($.inArray(userCityId, ids) >= 0) {
				c.val(userCityId);
			}
		}
	});
}

function initPasswordValidate() {
	$("#password_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			oldPassword : {
				required : true,
				maxlength : 32,
				minlength : 6
			},
			newPassword : {
				required : true,
				maxlength : 32,
				minlength : 6
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

function initValidate() {
	$("#user_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			loginName : {
				required : true,
				maxlength : 32,
				remote : {
					url : ctx + '/user/checkLoginName',
					type : 'POST',
					dataType : 'json',
					async : false,
					data : [ {
						name : 'id',
						value : function() {
							return $("#id").val();
						}
					}, {
						name : 'loginName',
						value : function() {
							return $("#loginName").val();
						}
					} ]
				}
			},
			trueName : {
				required : true,
				maxlength : 32
			},
			email : {
				email : true,
				remote : {
					url : ctx + '/user/checkEmail',
					type : 'POST',
					dataType : 'json',
					async : false,
					data : [ {
						name : 'id',
						value : function() {
							return $("#id").val();
						}
					}, {
						name : 'email',
						value : function() {
							return $("#email").val();
						}
					} ]
				}
			},
			phone : {
				digits : true,
				maxlength : 11,
				minlength : 11
			},
			roleIds : {
				required : true
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

function submit() {
	if(!$("#user_form").valid()){
		return;
	}
	$("#btn_save").attr("disabled", "disabled");
	$.ajax({
		url : ctx + "/user/save",
		type : "POST",
		dataType : 'json',
		data : $("#user_form").serializeArray(),
		complete : function() {
			$("#btn_save").removeAttr("disabled");
		},
		success : function(user) {
			$("#id").val(user.id);
			history.go(-1);
		}
	});

}

function initEdit() {
	var id = $("#id").val();
	if (id) {
		$.ajax({
			url : ctx + "/user/findById",
			type : "POST",
			dataType : 'json',
			async : false,
			data : [ {
				name : 'id',
				value : id
			} ],
			success : function(user) {
				if(user!=null) {
					renderUser(user);
				}
			}
		});
	}

}

function renderUser(user) {
	window.user = user;
	var level = user.area.level;
	if (level == 1 || level == 2) {
		window.userProvinceId = user.area.id;
	}
	if (level == 3) {
		window.userProvinceId = user.area.parentId;
		window.userCityId = user.area.id;
	}
	$.ajax({
		url : ctx + "/user/findRolesByUser",
		type : "POST",
		dataType : 'json',
		async : false,
		data : [ {
			name : 'userId',
			value : user.id
		} ],
		success : function(roles) {
			window.userRoles = roles;
			$.each(roles, function(i, role) {
				window.userRoleIds.push(role.id);
			});
		}
	});
	$.ajax({
		url : ctx + "/anon/getPermitProducts",
		type : "POST",
		dataType : "json",
		async : false,
		data : [{
			name : 'loginName',
			value : user.loginName
		}],
		success : function(products) {
			userProducts = products;
		}
	})
	$.ajax({
		url : ctx + "/anon/getPermitPlatforms",
		type : "POST",
		dataType : "json",
		async : false,
		data : [{
			name : 'loginName',
			value : user.loginName
		}],
		success : function(platforms) {
			userPlatforms = platforms;
		}
	})
	$("#loginName").val(user.loginName);
	$("#trueName").val(user.trueName);
	$("#status").val(user.status);
	$("input[name='sex'][value='{0}']".format(user.sex)).prop({
		checked : true
	});
	$("#phone").val(user.phone);
	$("#email").val(user.email);

}

function initRoles() {
	var html = $("#roles").html();
	$("#roles").empty();
	$.ajax({
		url : ctx + "/user/permitRoles",
		type : "POST",
		dataType : 'json',
		async : false,
		maskTarget : '#permission_tab',
		success : function(roles) {
			var roleIds = $.map(roles,function(role){
				return role.id;
			})
			$.each(userRoles,function(i,a){
				if($.inArray(a.id,roleIds)<0){
					var roleEl = $("#roles").append(html.format(a.id,a.name));
					roleEl.find("input").prop({checked : true}).attr("disabled","disabled");
				}
			})
			
			$.each(roles, function(i, a) {
				$("#roles").append(html.format(a.id,a.name));
				if ($.inArray(a.id, userRoleIds)<0) {
				}else {
					$("#roles #role_{0}".format(a.id)).prop({
						checked : true
					});
				}
			});
		}
	});
}
