var bd_profile_url = 'http://www.wealink.com/dangan/';

/**
 * 委托档案--伯乐详情页委托档案功能
 * @param  {[html object]} 	obj  [this]
 * @param  {[int]} 			hr_uid  [hr_uid：要把自己的简历委托给的目标人]
 * @param  {[int]} 			type [type：4]
 * @param  {[string]} 		op   [operation：do|undo]
 * 弹出委托档案框
 * 说明：使用此方法的页面需要引用jquery.cm_dialog.js文件
 */
function entrust_resume(obj,hr_uid,type,op,dom_id) {
	var option = {		
		'url':bd_profile_url+'entrust_resume/?hr_uid='+hr_uid+'&type='+type+'&op='+op+'&dom_id='+dom_id
	};
	$(obj).cm_dialog(option);	
}

/**
 * 关闭弹出界面，改写界面DOM
 * @return {[type]} [description]
 */
function entrust_resume_close_window(dom_id,hr_uid,flag,not_show_dialog){
	//改写界面DOM
	if(flag && dom_id){
		if($('#'+dom_id+'_span')){
		   $('#'+dom_id+'_span').attr('title','已委托档案');
		   $('#'+dom_id+'_span').attr('onclick','');
		   $('#'+dom_id+'_span').addClass('entrusted');
		}
		if($('#'+dom_id+'_a')){
			//$('#'+dom_id+'_a').attr('title','已委托档案');
			//$('#'+dom_id+'_a').attr('onclick','');
			//$('#'+dom_id+'_a').text('已委托档案');
			//$('#'+dom_id+'_a').css('color','#888');
			$('#'+dom_id+'_a').replaceWith('<span class="grayC">已委托档案</span>');
		}

		//弹出推荐框
		if(not_show_dialog==0){
			entrust_resume_recommend(hr_uid);
		}
	}
}

/**
 * 推荐的委托人
 */
function entrust_resume_recommend(hr_uid) {
	var option = {		
		'url':bd_profile_url+'entrust_resume_recommend/?hr_uid='+hr_uid+'&now='+new Date().getTime()
	};
	$("doc").cm_dialog(option);	
}

function toCancelEntrust(btnObj, hrUid){
	var options = {
		url: 'http://www.wealink.com/user/bole/toCancelEntrust/?hrUid='+hrUid
	}
	$(btnObj).cm_dialog(options);
}

function toEntrustResume(btnObj, hrUid){
	var options = {
		url: 'http://www.wealink.com/user/bole/toEntrustResume/?hrUid='+hrUid
	}
	$(btnObj).cm_dialog(options);
}
