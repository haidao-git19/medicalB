var doingAddConcern = false;
/**
 * 用户加关注,非档案页
 * modify	jianghua
 * date 2012-11-21
 */
function addConcern(obj,tar_uid,user_name,flag) {
    var isButton = false;
    if($(obj)[0].tagName.toUpperCase()=='INPUT') {
        isButton = true;
    }
    if(doingAddConcern) {
        return false;
    }
	var That = $(obj);
    var url = "http://www.wealink.com/user/concern/addConcernAjax/";
        doingAddConcern = true;
        var oldHtml = $(obj).html();
        if(isButton){
            $(obj).val('提交中...');
        } else {
            $(obj).text('提交中...');
        }
        $.ajax({
            dataType:'json',
            url:url,
            data:{
                tar_uid:tar_uid,
                isAjax:1,
                type:flag
            },
            type:'post',
            success:function(data){
                if(data.result == 1){
                    $(obj).replaceWith(data.html);
					ye_msg.open('关注成功!',3,1);
                }else {
					if( data.result == -4 || data.result == -5 ){
						var options = {
							url: 'http://www.wealink.com/user/bole/toSendMessage/?hrUid='+data.hr_uid
						}
						$(obj).cm_dialog(options);
					}else{
						ye_msg.open(data.msg,2,2);
					}
					$(obj).html(oldHtml);
                }
                if( typeof(data.redirect_url)!='undefined' && data.redirect_url!=''){
                    window.location = data.redirect_url;
                }
                doingAddConcern = false;
            },
            error:function(){
                $(obj).html(oldHtml);
                alert("连接服务器失败，请检查网络");
            }
        });
}


var doingCancelConcern = false;
/**
 *  移除关注
 *  modify	jianghua
 *	date 2012-11-21
 */
function removeConcern(obj,tar_uid,user_name,flag)
{
        var isButton = false;
        if($(obj)[0].tagName.toUpperCase()=='INPUT') {
            isButton = true;
        }
	if(doingCancelConcern) {
		return false;
	}
	
	var option = {'url':"http://www.wealink.com/user/bole/toCancelConcern/?tarUid="+tar_uid};
	$(obj).cm_dialog(option);
	
	// var url = "http://www.wealink.com/user/concern/removeConcernAjax/";
	// if(confirm('确定要取消对'+user_name+'的关注么？以后你将无法在首页看到'+user_name+'的动态')){
        // doingCancelConcern = true;
        // if(isButton){
            // $(obj).val('提交中...');
        // } else {
            // $(obj).text('提交中...');
        // }
        // $.ajax({
            // dataType:'json',
            // url:url,
            // data:{
                // tar_uid:tar_uid,
                // isAjax:1,
                // type:flag
            // },
            // type:'post',
            // success:function(data){
                // if(data.result == 1){
					// if( flag == 2 ){
						// $(obj).parent().parent().html(data.html);
					// }else{
						// $(obj).replaceWith(data.html);
					// }
                // } else {
                    // ye_msg.open(data.msg,2,2);
                // }
                // if(typeof(data.redirect_url)!='undefined' && data.redirect_url!=''){
                    // window.location = data.redirect_url;
                // }
                // doingCancelConcern = false;
            // },
            // error:function(){
                // alert("连接服务器失败，请检查网络");
            // }
        // });
    // }
}


/** 
 *  弹出添加分组层
 *  code by jianghua
 *	date 2012-11-20
 */
function addFirendGroup(obj, uid) {
    var bd_friend_url = 'http://www.wealink.com/user/';
	var option = {url: bd_friend_url+'friend/popAddGroup/?uid=' + uid};
	$(obj).cm_dialog(option);
	return false;
}
/**
 *  添加分组
 *  code by jianghua
 *  date 2012-11-20
 */
function ajaxAddGroup() {
	var $group_name = $('.group_name').val();
	if ($.trim($group_name) == '') {
		ye_msg.open('名称不能为空!',3,2);
		return false;
	}
	if ($.trim($group_name).length > 8) {
		ye_msg.open('字符超过限制大小!',3,2);
		return false;
	}
	var http_url = 'http://www.wealink.com/user/friend/ajaxAddGroup/'; 
	$.getJSON(http_url,{group_name:encodeURIComponent($group_name)},function(data){
		if(data.flag == '1') {
			/* 加入HTML列表 */
           var html = '';
		   html = '<label for=""><input type="checkbox" value="'+$group_name+'"/>'+$group_name+'</label>';
		   $('.group_list').append($(html));
           $('.group_name').val('');
		} else if (data.flag == '0'){
			ye_msg.open(data.msg);
		}
	});
}

/**
 *  添加用户到指定的分组
 *  code by jianghua
 *  date 2012-11-20
 */
function addUserToGroup() {
	var $groups = '';
	var obj_group_name = $(':checkbox[checked=checked]');
	if (obj_group_name.length == '0') {
		ye_msg.open('请选择分组!',3,2);
		return false;
	}
    obj_group_name.each(function(index, obj){
		$groups += '|'+obj.value;
	});
	var http_url = 'http://www.wealink.com/user/friend/addUserDesGroup/'; 
	var $uid = $(':input[name=des_uid]').val();
	$.getJSON(http_url,{groups:$groups,uid:$uid},function(data){
		if(data.flag == '1') {
			ye_msg.open(data.msg,3,1);
			$('#cm_closeMsg').trigger('click');
		} else if (data.flag == '0') {
			ye_msg.open(data.msg,3,2);
		}
	});
	return false;

}
/**
 *  未登录情况，弹出层
 *  code by jianghua
 *  date 2012-11-20
 */
function userLoin(obj) {
	var bd_root_url = "http://www.wealink.com/";
	var option = {'url':bd_root_url+'secure/login/login_by_dialog/?done='+encodeURI(location.href)};
	$(obj).cm_dialog(option);

}
/**
 * 点击创建组和取消的效果
 * code by jianghua
 * date 2012-11-21
 */
(function(){
	$('.addLink').click(function(){
		$(this).hide();
		$('.addInput').show();
	});
	$('#cancel_add').click(function(){
		$('.addInput').hide();
		$('.addLink').show();
		$('.group_name').val('');
	});
	

})();

/**
 * 用户弹出层
 * code by jianghua
 * date 2012-11-21
 */
function report(obj, uid) {
	if ($.trim(uid) == '') {
		ye_msg.open('非法uid',3,2);
		return false;
	}
	var bd_friend_url = 'http://www.wealink.com/user/';
	var option = {url: bd_friend_url+'friend/popReport/?uid=' + uid};
	$(obj).cm_dialog(option);
	return false;
}

/**
 *  用户举报操作
 *  code by jianghua
 *  date 2012-11-21
 */
function doReport() {
	var selected = $(':radio[name=reportReason][checked=checked]');
	var $reason = selected.val();
	var $content = $('#report_content').val();
	if (parseInt($reason) == 3) { /* 如果选择其它 */
		if ($.trim($content) == '') {
			ye_msg.open('内容不能为空!',3,2,null,false);
			return false;
		}
	}
	var http_url = 'http://www.wealink.com/user/friend/doReport/';
    $('#popReport').hide();
	$.getJSON(http_url,{reason:$reason,uid:$('.report_uid').val(),content:$content},function(data){
		if (data.flag == '1') {
			ye_msg.open(data.msg,3,1,function(){$('#popReport .close').click();});
			$('#report_content').val('');
		} else {
			ye_msg.open(data.msg,3,2);
            $('#popReport').show();
		}
	});

}

var doingAddConcern = false;
function addconcern(obj,tar_uid,user_name,more) {
    var isButton = false;
    if($(obj)[0].tagName.toUpperCase()=='INPUT') {
        isButton = true;
    }

    if(doingAddConcern) {
        return false;
    }
    var fun=more?"addConcernsAjax":"addConcernAjax";
    var url = "http://www.wealink.com/user/concern/"+fun+"/";
    if(confirm('确定要关注'+user_name+'么？以后你可以在首页看到'+user_name+'的动态')){
        doingAddConcern = true;
        var oldHtml = $(obj).html();
        if(isButton){
            $(obj).val('');
        } else {
            $(obj).text('提交中...');
        }
        $.ajax({
            dataType:'json',
            url:url,
            data:{
                tar_uid:tar_uid,
                isAjax:1,
                flg:isButton
            },
            type:'post',
            success:function(data){
                if(data.result == 1){
                    if(data.concernEach){
                        $('#followEach_' + tar_uid).show();
                    }
                    $(obj).parent().html(' <input type="button" title="取消关注" class="introInput_3" onclick=\'removeconcern(this,'+tar_uid+',"'+user_name+'")\'/>');
                    ye_msg.open(data.msg,2,1);
                }else {
                    ye_msg.open(data.msg,2,2);
                }
                if(typeof(data.redirect_url)!='undefined' && data.redirect_url!=''){
                    window.location = data.redirect_url;
                }
                doingAddConcern = false;
                $(obj).html(oldHtml);
            },
            error:function(){
                $(obj).html(oldHtml);
                alert("连接服务器失败，请检查网络");
            }
        });
    }
}


var doingAddConcern = false;
function addConcern_v4_new(obj,tar_uid,user_name,more) {
    var isButton = false;
    if($(obj)[0].tagName.toUpperCase()=='INPUT') {
        isButton = true;
    }

    if(doingAddConcern) {
        return false;
    }
    var fun=more?"addConcernsAjax":"addConcernAjax";
    var url = "http://www.wealink.com/user/concern/"+fun;
    if(confirm('确定要关注'+user_name+'么？以后你可以在首页看到'+user_name+'的动态')){
        doingAddConcern = true;
        var oldHtml = $(obj).html();
        if(isButton){
            $(obj).val('');
        } else {
            $(obj).text('提交中...');
        }
        $.ajax({
            dataType:'json',
            url:url,
            data:{
                tar_uid:tar_uid,
                isAjax:1,
                flg:isButton
            },
            type:'post',
            success:function(data){
                if(data.result == 1){
                    if(data.concernEach){
                        $('#followEach_' + tar_uid).show();
                    }
					$(obj).replaceWith(data.html);
                    ye_msg.open(data.msg,2,1);
                }else {
                    ye_msg.open(data.msg,2,2);
                }
                if(typeof(data.redirect_url)!='undefined' && data.redirect_url!=''){
                    window.location = data.redirect_url;
                }
                doingAddConcern = false;
                $(obj).html(oldHtml);
            },
            error:function(){
                $(obj).html(oldHtml);
                alert("连接服务器失败，请检查网络");
            }
        });
    }
}



var doingCancelConcern = false;
function removeconcern(obj,tar_uid,user_name)
{
        var isButton = false;
        if($(obj)[0].tagName.toUpperCase()=='INPUT') {
            isButton = true;
        }
	if(doingCancelConcern) {
		return false;
	}
	var url = "http://www.wealink.com/user/concern/removeConcernAjax/";
	if(confirm('确定要取消对'+user_name+'的关注么？以后你将无法在首页看到'+user_name+'的动态')){
        doingCancelConcern = true;
        if(isButton){
            $(obj).val('');
        } else {
            $(obj).text('提交中...');
        }
        $.ajax({
            dataType:'json',
            url:url,
            data:{
                tar_uid:tar_uid,
                isAjax:1,
                flg:isButton
            },
            type:'post',
            success:function(data){
                if(data.result == 1){
                    $('#followEach_' + tar_uid).hide();
					$(obj).parent().html(' <input type="button" title="加关注" class="introInput_2" onclick=\'addconcern(this,'+tar_uid+',"'+user_name+'")\'/>');
                } else {
                    ye_msg.open(data.msg,2,2);
                }
                if(typeof(data.redirect_url)!='undefined' && data.redirect_url!=''){
                    window.location = data.redirect_url;
                }
                doingCancelConcern = false;
            },
            error:function(){
                alert("连接服务器失败，请检查网络");
            }
        });
    }
}


var doingCancelConcern = false;
function removeConcern_v4_new(obj,tar_uid,user_name)
{
        var isButton = false;
        if($(obj)[0].tagName.toUpperCase()=='INPUT') {
            isButton = true;
        }
	if(doingCancelConcern) {
		return false;
	}
	var url = "http://www.wealink.com/user/concern/removeConcernAjax/";
	if(confirm('确定要取消对'+user_name+'的关注么？以后你将无法在首页看到'+user_name+'的动态')){
        doingCancelConcern = true;
        if(isButton){
            $(obj).val('');
        } else {
            $(obj).text('提交中...');
        }
        $.ajax({
            dataType:'json',
            url:url,
            data:{
                tar_uid:tar_uid,
                isAjax:1,
                flg:isButton
            },
            type:'post',
            success:function(data){
                if(data.result == 1){
                    $('#followEach_' + tar_uid).hide();
					//$(obj).parent().html(' <input type="button" title="加关注" class="introInput_2" onclick=\'addconcern(this,'+tar_uid+',"'+user_name+'")\'/>');
					$(obj).parent().html(' <a class="follow-1" title="加关注" onclick=\'addConcern_v4_new(this,'+tar_uid+',"'+user_name+'" )\'/> ');
                } else {
                    ye_msg.open(data.msg,2,2);
                }
                if(typeof(data.redirect_url)!='undefined' && data.redirect_url!=''){
                    window.location = data.redirect_url;
                }
                doingCancelConcern = false;
            },
            error:function(){
                alert("连接服务器失败，请检查网络");
            }
        });
    }
}



var applyfans_txt_did = false;
function applyfansTxt(obj,cid) {

	if(applyfans_txt_did) {
		return false;
	}

	var url = "http://www.wealink.com/company/visit/applyfans/";
	applyfans_txt_did = true;
	$.ajax({
		dataType:'json',
		url:url,
		data:{
			cid:cid,
			type:'text'
		},
		type:'post',
		success:function(data){
			if(data.code == 1){
				$(obj).replaceWith(data.html);
			} else if(data.code == 3){
				ye_msg.open(data.msg,2,3);
				$(obj).replaceWith(data.html);
			}else {
				ye_msg.open(data.msg,2,2);
			}
			applyfans_txt_did = false;
		},
		error:function(){
			alert("连接服务器失败，请检查网络");
		}
	});
}

var cancelfans_txt_did = false;
function cancelfansTxt(obj,cid)
{
	if(cancelfans_txt_did) {
		return false;
	}

	var url = "http://www.wealink.com/company/visit/cancelfans/";
	if(confirm('确定取消关注该公司吗?')){
		cancelfans_txt_did = true;
		$.ajax({
			dataType:'json',
			url:url,
			data:{
				cid:cid,
				type:'text'
			},
			type:'post',
			success:function(data){
				if(data.code == 1){
					$(obj).replaceWith(data.html);
				}else if(data.code == 3) {
					ye_msg.open(data.msg,2,3);
					$(obj).replaceWith(data.html);
				} else {
					ye_msg.open(data.msg,2,2);
				}
				cancelfans_txt_did = false;
			},
			error:function(){
				alert("连接服务器失败，请检查网络");
			}
		});
	}
}

function addConcerns(obj,tar_uid,user_name) {
	if(doingAddConcern) {
		return false;
	}
        var fun="addConcernsAjax";
	var url = "http://www.wealink.com/user/concern/"+fun+"/";
	if(confirm('确定要关注'+user_name+'么？以后你可以在首页看到'+user_name+'的动态')){
		doingAddConcern = true;
		$.ajax({
			dataType:'json',
			url:url,
			data:{tar_uid:tar_uid,isAjax:1},
			type:'post',
			success:function(data){
				if(data.result == 1){
                 ye_msg.open(data.msg,2,1);
				}else {
					ye_msg.open(data.msg,2,2);
				}
				if(typeof(data.redirect_url)!='undefined' && data.redirect_url!=''){
					window.location = data.redirect_url;
				}
				doingAddConcern = false;
			},
			error:function(){
				alert("连接服务器失败，请检查网络");
			}
		});
	}
}

var doingReserve = false;
var host = "http://www.wealink.com/";
function doReserve(obj,uid,source,pid) {
	if(doingReserve){
		return false;
	}
	doingReserve = true;

	var _url = host + "user/candidate/doreserve/";
	$.ajax({
		type:'post',
		url:_url,
		data:{uid:uid,source:source,pid:pid},
		dataType:'json',
		success:function(data){
			if(data.flag == 'success'){
				ye_msg.open(data.msg,3,1);
				setTimeout(function(){
					location.reload();
				}, 2 * 1000);
				
			} else {
				ye_msg.open(data.msg,3,2);
			}
			doingReserve = false;
		},
		error:function(){
			alert("连接服务器失败，请检查网络");
		}
	});
}
