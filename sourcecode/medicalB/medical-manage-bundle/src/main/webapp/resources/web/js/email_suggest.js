/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * kingstar 2012-03-02
 * 邮箱提示
 */
var openIndex = -1;
function init_reg(name){
    $('#'+name).focus(function(){
    	if($(this).val().indexOf("@") != -1){
            showEmailTips();
        }
    });
    $('#'+name).keyup(function(){
        var inputVal = $("#"+name).val();
        inputVal = inputVal.split("@")[0];
        $('li',$('#emailTips')).each(function(){
            $(this).html(inputVal + "@" + $(this).attr('title'));
        });
    });
	$('#'+name).keyup(function(event){
		var email = $('#'+name).val();
		if( email.indexOf('@') == -1 ){
			hideEmailTips();
		}
	});
    $('#'+name).keydown(function(event){
    	openTab = false;
        //代表输入 @

        if(event.keyCode == 50 && event.shiftKey){
            showEmailTips();
            openTab = true;
            openIndex = -1;
        }else if(event.keyCode == 13){//代表摁enter
            var maxEmailLength = 33;
            var inputVal = $("#"+name).val();
            inputVal = inputVal.split("@")[0];
            if(openIndex != -1){
                var email = inputVal + "@" + ($("#emailTips li").eq(openIndex).attr('title'));
                if(email.length >maxEmailLength){
                    email = inputVal.substring(0,(maxEmailLength-($("#emailTips li").eq(openIndex).attr('title')).length)-1) + "@" + ($("#emailTips li").eq(openIndex).attr('title'));
                }
                $('#'+name).val(email);
            }
            $('#'+name).blur();
            hideEmailTips()
        }else if(event.keyCode == 190)//离开
        {
            hideEmailTips()
        }else if( event.keyCode == 40 && $('#emailTips').css('display') != "none" ){

			openIndex++;
			if( openIndex > $("#emailTips li").length - 1 ){
				openIndex = 0;
			}
			
			$("#emailTips li").removeClass("hover");
			$("#emailTips li:eq("+openIndex+")").addClass("hover");
			
		}else if( event.keyCode == 38 && $('#emailTips').css('display') != "none" ){
			openIndex--;
			if( openIndex < 0 ){
				openIndex = $("#emailTips li").length - 1;
			}
			
			$("#emailTips li").removeClass("hover");
			$("#emailTips li:eq("+openIndex+")").addClass("hover");
		}
    });
    $("#emailTips li").each(function(){
        $(this).click(function(){
            var maxEmailLength = 33;
            var inputVal = $("#"+name).val();
            inputVal = inputVal.split("@")[0];
            var email = inputVal + "@" + $(this).attr("title");
            if(email.length >maxEmailLength) {
                email = inputVal.substring(0,(maxEmailLength-($(this).attr("title")).length)-1) + "@" 
                + $(this).attr("title");
            };
            $("#"+name).val(email);
            checkRegEmail();
            hideEmailTips();
        });
    })
    $("body").click(function(event){
        var ele = event.srcElement || event.target;
        if(hideProvinceCityTips(ele, $("#"+name)[0]) && hideProvinceCityTips(ele, $("#emailTips")[0]))
        {
            hideEmailTips();
        }
    });
}
function hideProvinceCityTips(srcObj, obj)
{
    if(srcObj == obj)
        return false;
    srcObj = srcObj.parentNode;
    while(srcObj !=null && srcObj.tagName != 'body'){
        if(srcObj == obj)
            return false;
        srcObj = srcObj.parentNode;
    }
    return true;		
}
function showEmailTips(){
	$("#emailTips li").removeClass("hover");
    $('#emailTips').show();
	openIndex = -1;
	
}
	
function hideEmailTips(){
	$("#emailTips li").removeClass("hover");
    $('#emailTips').hide();
	openIndex = -1;
}
$(function(){
	init_reg("email");
})