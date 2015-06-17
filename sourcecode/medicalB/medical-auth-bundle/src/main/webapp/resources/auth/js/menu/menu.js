var menuTree, rMenu;
$(function() {
	initMenuTree();

	rendEdit();
	
	initUpload();

	initValidate();

	$("#btn_save").click(function() {
		submit();
	})
})

function initUpload(){
	$("#icon_upload").uploadify({
		swf : ctx + "/resources/auth/uploadify/uploadify.swf",
		uploader : ctx + "/menu/uploadIcon",
		width : 80,
		height : 80,
		multi : false,
		fileTypeExts : '*.jpg;*.jpeg;*.bmp;*.gif;*.png',
		fileTypeDesc : 'Image(*.jpg;*.bmp;*.gif;*.png)',
		fileObjName : "file",
		buttonText : '选择图片',
		removeTimeout : 0, // 完成时清除队列显示秒数
		onInit : function() {
			$(".uploadify-queue").hide();
			$("#icon_upload-button").remove();
			$("#icon_upload").css({
				position : 'absolute',
				width : '100%',
				height : '100%',
				left : 0,
				top : 0,
				margin : 0,
				cursor : 'pointer'
			}).attr("title", "点击上传");
		},
		removeCompleted : true,
		onUploadStart: function() {
			$(".uploadify-queue").show();
		},
		onUploadComplete: function() {
			$(".uploadify-queue").hide();
		},		
		onUploadSuccess : function(file, data, response) {
			renderIconUrl(data);
		},
		onUploadError : function(file, errorCode, errorMsg, errorString) {
			alert('文件 ' + file.name + ' 上传失败: ' + errorString);
		}
	});
	
	$(document).on("mouseover","#icon_upload_wrap",function(){
		if($("#icon_upload_wrap img").size() > 0) {
			$("#icon_upload_wrap .clear").show('fast');
		}
	})
	$(document).on("mouseleave","#icon_upload_wrap",function(){
		$("#icon_upload_wrap .clear").hide('fast');
	})
	$("#icon_upload_wrap .clear button").click(function(){
		$("#icon_upload_wrap img").remove();
		return false;
	})
}

function renderIconUrl(url) {
	var $img = $("#icon_upload_wrap img");
	if($img.size()==0) {
		$img = $("<img>");
		$("#icon_upload_wrap").append($img);
	}
	$img.attr("src",url);
}

function initMenuTree() {
	menuTree = $.fn.zTree.init($("#menu_tree"), {
		async : {
			enable : true,
			url : ctx + "/menu/subMenus",
			type : 'post',
			dataType : 'json',
			autoParam : [ "id=parentId" ],
			dataFilter : function(treeId, pNode, treeNodes) {
				for ( var i = 0; i < treeNodes.length; i++) {
					treeNodes[i] = menu2Node(treeNodes[i]);
				}
				return treeNodes;
			}
		},
		data : {
			keep : {
				leaf : true,
				parent : true
			}
		},
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false,
			drag : {
				isCopy : true,
				isMove : true
			}
		},
		view : {
			selectedMulti : false
		},
		callback : {
			onRightClick : function(evt, treeId, treeNode) {
				showMenu(treeNode, evt.pageX, evt.pageY);
			},
			beforeDrag : function(treeId, treeNodes) {
				return true;
			},
			beforeDrop : function(treeId, treeNodes, targetNode, moveType) {
				try {
					var containerType = 1;
					if (targetNode) {
						if (moveType === 'inner') {
							containerType = targetNode.menuType;
						} else if (targetNode.getParentNode()) {
							containerType = targetNode.getParentNode().menuType;
						}
					}
					var treeNode = treeNodes[0];
					switch (containerType) {
					case 1://目录接受页面和按键
						if (treeNode.menuType < 3) {
							return true;
						}
						break;
					case 2://页面只接受按键
						if (treeNode.menuType === 3) {
							return true;
						}
						break;
					case 3://按键不接受
						return false;
						break;
					}
				} catch (e) {
					console.log(e);
				}
				return false;
			},
			onDrop : function(evt, treeId, treeNodes, targetNode, moveType, isCopy) {
				if (targetNode && moveType) {
					var containerId = null;
					if (moveType === 'inner') {
						containerId = targetNode.id;
					} else if (targetNode.getParentNode()) {
						containerId = targetNode.getParentNode().id;
					}
					var container = menuTree.getNodeByParam("id",containerId);
					
					$.ajax({
						url : ctx + (isCopy ? '/menu/copy' : '/menu/move'),
						type : 'post',
						dataType : 'json',
						data : [ {
							name : 'toId',
							value : containerId
						}, {
							name : 'id',
							value : treeNodes[0].id
						}, {
							name : 'order',
							value : menuTree.getNodeIndex(treeNodes[0]) + 1
						}],
						complete : function() {
							menuTree.reAsyncChildNodes(container, "refresh", false);
							rendEdit();
						}
					})
				} else {
					rendEdit();
				}
			},
			onRemove : function(evt, treeId, treeNode) {
				rendEdit();
			},
			onClick : function(evt, treeId, treeNode, clickFlag) {
				rendEdit();
			}
		// callback end
		}
	});
	menuTree.expandAll(true);
}

function menu2Node(node) {
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
	node.parentId = node.parent ? node.parent.id : null;
	return node;
}

function showMenu(treeNode, x, y) {
	$("#rMenu").css({
		"top" : y + "px",
		"left" : x + "px",
		"visibility" : "visible"
	});
	var pMenuType = null;
	if (treeNode) {
		pMenuType = treeNode.menuType;
	}
	switch (pMenuType) {
	case 1:
		$("#m_refresh").show();
		$("#m_add_folder").show();
		$("#m_add_page").show();
		$("#m_add_action").hide();
		$("#m_del").show();
		break;
	case 2:
		$("#m_refresh").show();
		$("#m_add_folder").hide();
		$("#m_add_page").hide();
		$("#m_add_action").show();
		$("#m_del").show();
		break;
	case 3:
		$("#m_refresh").hide();
		$("#m_add_folder").hide();
		$("#m_add_page").hide();
		$("#m_add_action").hide();
		$("#m_del").show();
		break;
	default:
		$("#m_refresh").show();
		$("#m_add_folder").show();
		$("#m_add_page").show();
		$("#m_add_action").hide();
		$("#m_del").hide();
		break;
	}

	if (!treeNode) {
		$("#rMenu").removeData('tId');
	} else {
		$("#rMenu").data("tId", treeNode.tId);
		menuTree.selectNode(treeNode);
		rendEdit();
	}
	$(document).bind("mousedown", onBodyMouseDown);
}

function hideMenu() {
	$("#rMenu").css({
		"visibility" : "hidden"
	});
	$(document).unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
		hideMenu();
	}
}

function rendEdit() {
	if (menuTree && menuTree.getSelectedNodes()[0]) {
		var node = menuTree.getSelectedNodes()[0];
		var _id = $("#id").val();
		$("#id").val(node.id);
		if(node.parentId) {
			$("#parentId").val(node.parentId);
			$("#description").removeAttr('placeholder');
			$(".control-group:has(#description)").hide();
		}else {//当前菜单是子系统节点
			$("#parentId").val('');
			$(".control-group:has(#description)").show();
			$("#description").attr('placeholder',"此处内容将作为子系统logo展示");
		}
		$("#menuOrder").val(menuTree.getNodeIndex(node) + 1);
		$("#name").val(node.name);
		$("#link").val(node.link);
		$("#description").val(node.description);
		$("#menuType").val(node.menuType);
		$("#visible").prop({
			checked : node.visible
		})
		if(node.iconUrl){
			renderIconUrl(node.iconUrl);
		}else {
			$("#icon_upload_wrap img").remove();
		}
		
		//当前选中的和之前选中的不是同一个节点
		if(_id != node.id) {
			$("#menu_info_block").hide();
			$("#menu_info_block").show('fast',function(){
				$("#name").focus().select();
			});
		}
	}else {
		$("#id").val('');
		$("#menu_info_block").hide();
	}
}

function menu_refreshTreeNode() {
	hideMenu();
	var tId = $("#rMenu").data("tId");
	var node = menuTree.getNodeByTId(tId);
	menuTree.reAsyncChildNodes(node, "refresh", false);
	rendEdit();
}

function menu_addTreeNode(menuType) {
	hideMenu();
	var tId = $("#rMenu").data("tId");
	var node = menuTree.getNodeByTId(tId);
	if (node && !node.isParent) {
		return;
	}
	var isParent = true;
	var iconSkin = "page";
	switch (menuType) {
	case 1:
		isParent = true;
		iconSkin = "folder";
		break;
	case 2:
		isParent = true;
		iconSkin = "page";
		break;
	case 3:
		isParent = false;
		iconSkin = "action";
		break;
	}
	var newNodes = menuTree.addNodes(node, [ {
		id : '',
		name : '新建菜单',
		menuType : menuType,
		isParent : isParent,
		iconSkin : iconSkin
	} ], false);
	var newNode = newNodes[0];
	var pId = node ? node.id : '';
	var data = [ {
		name : 'id',
		value : ''
	}, {
		name : 'name',
		value : newNode.name
	}, {
		name : 'menuOrder',
		value : menuTree.getNodeIndex(newNode) + 1
	}, {
		name : 'menuType',
		value : newNode.menuType
	} ];
	if (pId) {
		data.push({
			name : 'parent.id',
			value : pId
		});
	}
	$.ajax({
		url : ctx + '/menu/save',
		type : 'post',
		dataType : 'json',
		data : data,
		success : function(menu) {
			newNode.id = menu.id;
			newNode.parentId = menu.parent.id;
			menuTree.updateNode(newNode);
			if(newNode.getParentNode) {
				menuTree.selectNode(newNode);
			}
			rendEdit();
		},
		error : function() {
			menuTree.removeNode(newNode, false);
		}
	})
}

function menu_removeTreeNode() {
	hideMenu();
	var tId = $("#rMenu").data("tId");
	var node = menuTree.getNodeByTId(tId);
	myConfirm('', '确定删除?', function() {
		$.ajax({
			url : ctx + '/menu/delete',
			type : 'post',
			dataType : 'json',
			data : [ {
				name : 'id',
				value : node.id
			} ],
			complete : function(data) {
				menuTree.reAsyncChildNodes(node.getParentNode(), "refresh", false);
				rendEdit();
			}
		})
	});
}

function initValidate() {
	$("#menu_form").validate({
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
	if (!$("#menu_form").valid()) {
		return;
	}
	$("#btn_save").attr("disabled","disabled");
	var data = [];
	$.each($("#menu_form").serializeArray(),function(i,a){
		if(typeof(a.value) == 'string' && !a.value){
			return;
		}
		data.push(a);
	});
	var iconUrl = $("#icon_upload_wrap img").attr("src");
	if(iconUrl) {
		data.push({
			name : 'iconUrl',
			value : iconUrl
		})
	}
	$.ajax({
		url : ctx + '/menu/save',
		type : 'post',
		dataType : 'json',
		data : data,
		success : function(menu) {
			var node = menuTree.getNodeByParam("id", menu.id);
			$.extend(node,menu);
			menuTree.updateNode(node);
			$("#id").val('');
			rendEdit();
		},
		complete : function(){
			$("#btn_save").removeAttr("disabled");
		}
	})
}