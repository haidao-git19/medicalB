//登录
var home_url = 'http://www.wealink.com/';
var _url_login_ajax = home_url+'secure/login/login_first_dialog_ajax/';
var _url_reg_ajax = home_url+'secure/reg/personRegAjax/';
//checkform2
function checkToSubmit2(){
    var result = checkAll2();	

    if(result){
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
                    $("#cm_closeMsg").click();
                    //刷新页面
                    window.location.reload();
                }else{
                    $("#loginEmailMsg").removeClass();
                    $("#loginEmailMsg").addClass("tips error");
                    $("#loginEmailMsg").show();
                    $("#loginEmailMsg").html(jdata.msg);
                    return false;
                }
            }
        );
    }
    return false;
}

function check_norefresh_login(){
    var result = checkAll2();	

    if(result){
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
					var href = " ' " + 'http://www.wealink.com/gongsi/dianpin/'+cid+'/#write_comment' + " ' ";
					var replace_html = '<span class="comment-2 comment-2H" onclick="javascript:window.location='+href+';document.getElementById(\'txt_content\').focus();">我要点评</span>';
					$('#norefresh_id').replaceWith(replace_html);
					//修改下面小的点评按钮
					$('#comment_norefresh_id').replaceWith("<span id='btn_addComment' class='comment commentH'>我要点评</span>");
                }else{
                    $("#loginEmailMsg").removeClass();
                    $("#loginEmailMsg").addClass("tips error");
                    $("#loginEmailMsg").show();
                    $("#loginEmailMsg").html(jdata.msg);
                    return false;
                }
            }
        );
    }
    return false;
}


function checkAll2(){
    if(!checkEmail2()){
        return false;
    }else if (!checkPassword2()) {
        return false;
    }else{
        return true;		
    }
}
function tipEmail2(){
    $("#loginEmailMsg").removeClass();
    $("#loginEmailMsg").addClass("tips prompt");
    $("#loginEmailMsg").show();
    $("#loginEmailMsg").html("请输入您常用的邮箱");

}
function checkEmail2(){
    var emailString = $("#mobileOrEmail").val();
    emailString = emailString.replace(/(^\s*)|(\s*$)/g, '');
    if(emailString == null || emailString == "" || emailString=='请输入您的账号'){
        $("#loginEmailMsg").removeClass();
        $("#loginEmailMsg").addClass("tips error");
        $("#loginEmailMsg").show();
        $("#loginEmailMsg").html("邮件地址不能为空");
        return false;
    }else if (emailString.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1){
        $("#loginEmailMsg").removeClass();
        $("#loginEmailMsg").addClass("tips error");
        $("#loginEmailMsg").show();
        $("#loginEmailMsg").html("无效的邮件地址格式");
        return false;
    }else{
        $("#loginEmailMsg").hide();
        return true;
    }
}
function tipPassword2(){
    $("#loginPassMsg").removeClass();
    $("#loginPassMsg").addClass("tips prompt");
    $("#loginPassMsg").show();
    $("#loginPassMsg").html("请输入您的密码");
}
function checkPassword2(){
    var password = $("#passwords").val();
    if(password == null || password == ''){
        $("#loginPassMsg").removeClass();
        $("#loginPassMsg").addClass("tips error");
        $("#loginPassMsg").show();
        $("#loginPassMsg").html("密码不能为空");
        return false;
    }else if(password.length < 6){
        $("#loginPassMsg").removeClass();
        $("#loginPassMsg").addClass("tips error");
        $("#loginPassMsg").show();
        $("#loginPassMsg").html("密码小于六位");
        return false;
    }else{
        $("#loginPassMsg").hide();
        return true;
    }
}

function checkAll4(){
    if(!checkEmail4()){
        return false;
    }else if (!checkPassword4()) {
        return false;
    }else{
        return true;        
    }
}
function checkEmail4(){
    var emailString = $("#mobileOrEmail").val();
    emailString = emailString.replace(/(^\s*)|(\s*$)/g, '');
    if(emailString == null || emailString == "" || emailString=='请输入您的账号'){
        ye_msg.open('邮件地址不能为空',3,2);
        return false;
    }else if (emailString.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1){
        ye_msg.open('无效的邮件地址格式',3,2);
        return false;
    }else{
        return true;
    }
}
function checkPassword4(){
    var password = $("#passwords").val();
    if(password == null || password == ''){
        ye_msg.open('密码不能为空',3,2);
        return false;
    }else if(password.length < 6){
        ye_msg.open('密码小于六位',3,2);
        return false;
    }else{
        return true;
    }
}

function check_show_msg(obj_id,val,flag){
    if(flag == 0){
        $('#'+obj_id+'_p').removeClass();
        if(obj_id == 'sex')
            $('#'+obj_id+'_p').addClass('ipRadio');
            
        $('#'+obj_id+'_msg').removeClass();
        $('#'+obj_id+'_msg').addClass('tips prompt');
        $('#'+obj_id+'_msg').html(val);
    }else if(flag == 1){
        $('#'+obj_id+'_p').removeClass();
        if(obj_id == 'sex')
            $('#'+obj_id+'_p').addClass('ipRadio correct');
        else
            $('#'+obj_id+'_p').addClass('correct');
            
        $('#'+obj_id+'_msg').removeClass();
        $('#'+obj_id+'_msg').addClass('tips');
        $('#'+obj_id+'_msg').html(val);
    }else if(flag == 2){
        $('#'+obj_id+'_msg').removeClass();
        $('#'+obj_id+'_msg').addClass('tips error');
        $('#'+obj_id+'_msg').html(val);
    }
}
function regSubmit(){
    if(!checkAgreement()){
        return false;
    }
    if(!check_email){
        check_show_msg('email',"请输入常用邮箱",2);
        return false;
    }
    if(!checkPassword()){
        return false;
    }
    var email = $('#email').val();
    var password = $('#password').val();
    var rfss = $('#rfss').val();
    $("#regbtn").attr("disabled",true);
    var URL = _url_reg_ajax;
    $.ajax({
        type: "POST",
        url: URL,
        data: "email="+email+"&password="+password+"&rfss="+rfss,
        async: false,
        dataType: "json",
        success: function(data){
            if(data.flag == 'success'){
                //ye_msg.open('注册成功',3,1);
                if(rfss=='rfss'){
                    //新页面弹出完善资料页面
                    window.open(home_url+'user/profile/index/');
                    //登录成功，关闭弹出框
                    $("#cm_closeMsg").click();
                    //刷新页面
                    window.location.reload();
                }else{
                    window.location.href = home_url+'user/home/';
                }
            }else{
            	$("#regbtn").attr("disabled",false);
                ye_msg.open(data.msg,3,2);
            }
        }
    });
}