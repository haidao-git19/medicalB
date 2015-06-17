var home_url = 'http://www.wealink.com/';
var bd_reg_url = home_url+'secure/reg/'; 
var _url_login_ajax = home_url+'secure/login/login_first_dialog_ajax/';
var _url_reg_ajax = home_url+'secure/reg/personRegAjax/';
function checkIdentity()
{
	var identity = $(':radio[name=identity][checked]').val();
	if(identity == undefined || identity == null || identity == '')
	{
        $("#id_tips_identity").show();
        $("#id_tips_identity").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">请选择您的身份类型</p>');
		return false;
	}
	$("#id_tips_identity").hide();
	return true;
}
var check_email = false;
function checkRegEmail(){
    var emailString = $.trim($('#email').val());
    if (emailString == null || emailString == "" || emailString=="将作为简历中的联系邮箱") {
    	$('#email_correct_i').hide();
        $("#id_tips_reg_email").show();
        $("#id_tips_reg_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">邮箱不能为空</p>');
        return false;
    }else if(emailString.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1){
    	$('#email_correct_i').hide();
        $("#id_tips_reg_email").show();
        $("#id_tips_reg_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">邮箱格式有误</p>');
        return false;
    }else{
        var URL = bd_reg_url+"checkEmailAjax";
		$.ajax({
		type:"POST",
		async:false,
		url:URL,
		data:"email="+emailString,
		success:function(result){
		    if(result == 1){
		    	$('#email_correct_i').hide();
		        $("#id_tips_reg_email").show();
		        $("#id_tips_reg_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">该邮箱已经注册过，<a href=\'http://www.wealink.com/denglu/?mobileOrEmail='+emailString+'\'>直接登录</a></p>');
		    }else{
		        $("#id_tips_reg_email").hide();
		        $('#email_correct_i').show();
		        check_email = true;
		    }
		    return check_email;
		}
		});
    }
}
function checkRegPassword(){
	var password = $('#password').val();
    if(password == null || password == ""){
    	$("#pwd_correct_i").hide();
	    $("#id_tips_reg_pwd").show();
	    $("#id_tips_reg_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不能为空</p>'); 
        return false;
    }else if(password.length < 6){
    	$("#pwd_correct_i").hide();
	    $("#id_tips_reg_pwd").show();
	    $("#id_tips_reg_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不能少于6位</p>'); 
        return false;
    }else if(password.indexOf(" ")>=0){
		$("#pwd_correct_i").hide();
	    $("#id_tips_reg_pwd").show();
	    $("#id_tips_reg_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不能有空格</p>'); 
        return false;
	}else if(password.length >16){
		$("#pwd_correct_i").hide();
	    $("#id_tips_reg_pwd").show();
	    $("#id_tips_reg_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不能超过16位</p>'); 
        return false;
    }else{
    	$("#id_tips_reg_pwd").hide();
        $("#pwd_correct_i").show();
        return true;
    }
}
function checkAgreement()
{
    if($('#AgreementAndPrivacyPolicy').attr('checked')=='checked')
    {
    	$('.notic-tip-box').html('');
        return true;
    }
    else 
    {
        $('.notic-tip-box').show();
        $('.notic-tip-box').html('<i class="notic-tip-icon png"></i><p class="notic-tip">请接受《用户协议》</p>');
        return false;
    }
}


function checkRegAgreement()
{
    if($('#AgreementAndPrivacyPolicy').attr('checked')=='checked')
    {
        $('#id_tips_reg_agree').html('');
        return true;
    }
    else
    {
        $('#id_tips_reg_agree').show();
        $('#id_tips_reg_agree').html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">请接受《用户协议》</p>');
        return false;
    }
}
function check_login(){
    if(!checkLoginEmail()){
        return false;
    }
    if (!checkLoginPassword()) {
        return false;
    }
    var _email = $("#mobileOrEmail").val();
    var _password = $("#passwords").val();
    var _md5pwd = hex_md5(_password);
    var _timeout = ($("#timeout").attr("checked")=='checked') ? 1 : 0;
    var _ajax_data = {'email': _email,'passwords': _password,'md5pwd': _md5pwd,'timeout': _timeout};
    var _now = new Date().getTime();
    $.post(
        _url_login_ajax+'?now='+_now,
        _ajax_data,
        function(data){
            var jdata = $.parseJSON(data);
            if(jdata.flag==0){
                ye_msg.open(jdata.msg,3,1);
                //登录成功，关闭弹出框
                //$("#cm_closeMsg").click();
                //刷新页面
                window.location.reload();
            }else{
		        $("#id_tips_login_email").show();
		        $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">'+jdata.msg+'</p>');
                return false;
            }
        }
    );
    return false;
}

function check_norefresh_login(){
    if(!checkLoginEmail()){
        return false;
    }
    if (!checkLoginPassword()) {
        return false;
    }

    var _email = $("#mobileOrEmail").val();
    var _password = $("#passwords").val();
    var _md5pwd = hex_md5(_password);
	var cid = $('#norefresh_cid').val();
    var _timeout = ($("#timeout").attr("checked")=='checked') ? 1 : 0;
    var _ajax_data = {'email': _email,'passwords': _password,'md5pwd': _md5pwd,'timeout': _timeout, login_style : 'norefresh'};
    var _now = new Date().getTime();
    $.post(
        _url_login_ajax+'?now='+_now,
        _ajax_data,
        function(data){
            var jdata = $.parseJSON(data);
            if(jdata.flag==0){
                ye_msg.open(jdata.msg,3,1);
                //登录成功，关闭弹出框
                $("#cm_closeMsg").click();
                //修改头部状态
                $('.top').html(jdata.content);
				//修改页头点评按钮
				var href = " ' " + home_url +'gongsi/dianpin/'+cid+'/#write_comment' + " ' ";
				var replace_html = '<span class="comment-2 comment-2H" onclick="javascript:window.location='+href+';document.getElementById(\'txt_content\').focus();">我要点评</span>';
				$('#norefresh_id').replaceWith(replace_html);
				//修改下面小的点评按钮
				$('#comment_norefresh_id').replaceWith("<span id='btn_addComment' class='comment commentH'>我要点评</span>");
            }else{
        		$("#id_tips_login_email").show();
		        $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">'+jdata.msg+'</p>');
                return false;
            }
        }
    );
    return false;
}

function check_reg(){
    if(!check_email){
        return false;
    }
    if(!checkRegPassword()){
        return false;
    }
    if(!checkAgreement()){
        return false;
    }
    var email = $('#email').val();
    var password = $('#password').val();
    $("#regbtn").attr("disabled",true);
    var URL = _url_reg_ajax;
    $.ajax({
        type: "POST",
        url: URL,
        data: "email="+email+"&password="+password,
        async: false,
        dataType: "json",
        success: function(data){
            if(data.flag == 'success'){
                //刷新页面
                window.location.reload();
            }else{
            	$("#regbtn").attr("disabled",false);
                ye_msg.open(data.msg,3,2);
            }
        }
    });
}
function checkToLoginSubmit(){
    if(!checkLoginEmail()){
        return false;
    }
    if(!checkLoginPassword()) {
        return false;
    }	
    document.form.md5pwd.value = hex_md5(document.form.passwords.value);
    return true;
}

function checkLoginEmail(){
    var emailString = $("#mobileOrEmail").val();
    emailString = emailString.replace(/(^\s*)|(\s*$)/g, '');
    if(emailString == null || emailString == "" || emailString=="用于登录和申请职位" || emailString=="请输入您的邮箱"){
        $("#id_tips_login_email").show();
        $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">邮件地址不能为空</p>');
        return false;
    }else if (emailString.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1){
        $("#id_tips_login_email").show();
        $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">无效的邮件地址格式</p>');
        return false;
    }else{
        $("#id_tips_login_email").hide();
        return true;
    }
}

function checkLoginPassword(){
    var password = $("#passwords").val();
    if(password == null || password == ''){
        $("#pwd_correct_i").hide();
	    $("#id_tips_login_pwd").show();
	    $("#id_tips_login_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不能为空</p>'); 
        return false;
    }else if(password.length < 6){
        $("#pwd_correct_i").hide();
	    $("#id_tips_login_pwd").show();
	    $("#id_tips_login_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不足6位</p>'); 
        return false;
    }else if(password.length > 16){
        $("#pwd_correct_i").hide();
	    $("#id_tips_login_pwd").show();
	    $("#id_tips_login_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不能大于16位</p>'); 
        return false;
    }else if(password.indexOf(" ")>=0){
        $("#pwd_correct_i").hide();
        $("#id_tips_login_pwd").show();
        $("#id_tips_login_pwd").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">密码不能有空格</p>');
        return false;
    }else{
	    $("#id_tips_login_pwd").hide();
        $("#pwd_correct_i").show();
        return true;
    }
}

function login_error_tips(val){
    if(val == -1){
	    $("#id_tips_login_email").show();
	    $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">账号已被删除，请重新注册或联系客服</p>'); 
    }else if(val == -2){
	    $("#id_tips_login_email").show();
	    $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">账号和密码不匹配</p>'); 
    }else if(val == -3){
	    $("#id_tips_login_email").show();
	    $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">该帐号不存在</p>'); 
    }else if(val == -5){
	    $("#id_tips_login_email").show();
	    $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">邮件地址或密码不能为空</p>'); 
    }else if(val == -6){
	    $("#id_tips_login_email").show();
	    $("#id_tips_login_email").html('<i class="wrong-tip-icon png"></i><p class="wrong-tip">无效的邮件地址格式</p>'); 
    }
}