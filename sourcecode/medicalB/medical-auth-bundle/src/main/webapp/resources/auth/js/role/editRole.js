var menuTree;
var roleMenuIds = [];
$(function() {

	initEdit();

	renderMenuTree();

	initValidate();

	if (readOnly) {
		$("input").attr("readonly", "readonly");
		$("input").attr("disabled", "disabled");
		$("select").attr("disabled", "disabled");
		$("#btn_save").hide();
	}

	$("#btn_save").click(function() {
		submit();
	})

})

function initValidate() {
	$("#role_form").validate({
		onfocusout : false,
		onkeyup : false,
		onclick : false,
		focusInvalid : true,
		rules : {
			name : {
				required : true,
				maxlength : 32
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
	if (!$("#role_form").valid()) {
		return;
	}
	var data = $("#role_form").serializeArray();
	var nodes = menuTree.getCheckedNodes();
	var menuIds = [];
	$.each(nodes,function(i,node){
		menuIds.push(node.id);
	});
	data.push({
		name : 'menuIds',
		value : menuIds
	});
	//console.log(data);
	$("#btn_save").attr("disabled", "disabled");
	$.ajax({
		url : ctx + "/role/save",
		type : "POST",
		dataType : 'json',
		data : data,
		complete : function() {
			$("#btn_save").removeAttr("disabled");
		},
		success : function(role) {
			$("#id").val(role.id);
			history.go(-1);
		}
	});
}

function initEdit() {
	var id = $("#id").val();
	if (id) {
		$.ajax({
			url : ctx + "/role/findById",
			type : "POST",
			dataType : 'json',
			async : false,
			data : [ {
				name : 'id',
				value : id
			} ],
			success : function(role) {
				if (role != null) {
					renderRole(role);
				}
			}
		});
	}
}

function renderRole(role) {
	window.role = role;
	$.ajax({
		url : ctx + '/role/roleMenus',
		type : 'post',
		dataType : 'json',
		async : false,
		data : [ {
			name : 'roleId',
			value : role.id
		} ],
		success : function(menus) {
			$.each(menus, function(i, menu) {
				roleMenuIds.push(menu.id);
			})
		}
	})
	$("#name").val(role.name);
	$("#createTime").val(new Date(role.createTime).format('yyyy-MM-dd HH:mm:ss'));
}

function renderMenuTree() {
	menuTree = $.fn.zTree.init($("#menu_tree"), {
		async : {
			enable : true,
			url : ctx + "/menu/permitSubMenus",
			type : 'post',
			dataType : 'json',
			autoParam : [ "id=parentId" ],
			dataFilter : function(treeId, pNode, treeNodes) {
				for ( var i = 0; i < treeNodes.length; i++) {
					var node = treeNodes[i];
					if($.inArray(node.id,roleMenuIds)>=0){
						node.checked = true;
					}
					if(readOnly) {
						node.chkDisabled = true;
					}
					node.isParent = !(node.menuType === 3);
					switch (node.menuType) {
					case 1:
						node.iconSkin = "folder";
						break;
					case 2:
						node.iconSkin = "page";
						break;
					case 3:
						node.iconSkin = "action";
						break;
					default:
						break;
					}
				}
				return treeNodes;
			}
		},
		check : {
			enable : true
		},
		data : {
			keep : {
				leaf : true,
				parent : true
			}
		},
		view : {
			selectedMulti : false
		},
		callback : {
			onAsyncSuccess : function(evt, treeId, treeNode, msg) {
				var children = [];
				if (treeNode) {
					children = treeNode.children;
				} else {
					children = menuTree.getNodes();
				}
				for ( var i = 0; i < children.length; i++) {
					var child = children[i];
					menuTree.expandNode(child, true, false, false);
				}
			}
		// callback end
		}
	});
	menuTree.expandAll(true);
}