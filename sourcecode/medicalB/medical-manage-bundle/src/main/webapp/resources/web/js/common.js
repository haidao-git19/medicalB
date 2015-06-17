/**
 * Require JQuery 1.2.5+
 */
function multipleLevelSelectSimple (main_sel_id, sub_name, data, init_selected, config) {
	var $main_sel = $('#' + main_sel_id), dataMap = {};
	$.each(data, function (i, item) {
		$main_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
		dataMap[item[0]] = i;
	});
	var _change_handle = function () {
		var id = $(this).val();
		if (id == -1) {
			if ($main_sel.next().length) 
				$main_sel.next().remove();
			return;
		}
		if (!$main_sel.next().length) {
			$main_sel.after('<select name="' + sub_name + '"></select>');
		} 
		$sub_sel = $main_sel.next();
		$sub_sel.empty();
		var topItem = data[dataMap[id]];
		//$sub_sel.append('<option value="' + topItem[0] + '">' + topItem[1] + '</option>');   
		//消除第二级城市的第一个默认和第一级相同
		$.each(topItem[2], function (i, item) {
			$sub_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
		});
		
	};
	$main_sel.change(_change_handle);
	if (init_selected) {
		setTimeout(function() {
			for (var i = 0, m = data.length; i < m ; i ++) {
				if (data[i][0] == init_selected) {
					$main_sel.val(data[i][0]);
					_change_handle.apply($main_sel[0]);
					$main_sel.next().val(data[i][0]);
					break;	
				}
				for (var j = 0, n = data[i][2].length; j < n; j ++) {
					if (data[i][2][j][0] == init_selected) {
						$main_sel.val(data[i][0]);
						_change_handle.apply($main_sel[0]);
						$main_sel.next().val(data[i][2][j][0]);
						break;
					}	
				}
				if (j != n) break;
			}
		}, 1);
	}
}

function multipleLevelSelect_triple(main_id, main2_id, sub_id, data, init_selected){
	var $main_sel = $('#' + main_id), $main2_sel=$('#' + main2_id), $sub_sel = $('#' + sub_id),
	dataMap = {};	
	$.each(data, function (i, item) {
		$main_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
		dataMap[item[0]] = i;
	});	
	var _change_handle = function () {
		var id = $(this).val();
        $main2_sel.empty();
		$sub_sel.empty();
		if (id > 0) {
			$.each(data[dataMap[id]][2], function (i, item) {
                if(typeof dataMap[item[0]] == 'undefined')
                {
                    dataMap[item[0]] = i;
                }
				$main2_sel.append('<option value="' + item[0] + '" rel="' + id + '">' + item[1] + '</option>');
			});
            $.each(data[dataMap[id]][2][0][2], function (i, item) {
                $sub_sel.append('<option value="' + item[0] + '" rel="' + id + '">' + item[1] + '</option>');
            });
		}
	}; 
	$main_sel.change(_change_handle);  
	   
	var _change_handle2 = function () {
		var id = $(this).val();
		$sub_sel.empty();
		if(id > 0)
		{
	        $.each(data[dataMap[$main_sel.val()]][2][dataMap[id]][2], function (i, item) {
	            $sub_sel.append('<option value="' + item[0] + '" rel="' + id + '">' + item[1] + '</option>');
	        });
		}
	};
	$main2_sel.change(_change_handle2);
	if (init_selected) {
		setTimeout(function() {
			for (var i = 0, m = data.length; i < m ; i ++) 
			{
				if (data[i][0] == init_selected) 
				{
					alert('k');
					$main_sel.val(data[i][0]);
					_change_handle.apply($main_sel[0]);
					$sub_sel.val(data[i][0]);
					break;	
				}
				for (var j = 0, n = data[i][2].length; j < n; j ++) 
				{
					for(var k = 0, o = data[i][2][j][2].length; k < o; k ++)
					{
						if (data[i][2][j][2][k][0] == init_selected) 
						{
							$main_sel.val(data[i][0]);
							_change_handle.apply($main_sel[0]);
							$main2_sel.val(data[i][2][j][0]);
							_change_handle2.apply($main2_sel[0]);
							$sub_sel.val(data[i][2][j][2][k][0]);
							break;
						}							
					}
				}
				if (k != o) break;
			}
		}, 1);
		//为了ie6下兼容
		setTimeout(function() {$sub_sel.val(init_selected);},1);
	}
}

//flag=true那么第二选项中的第一个含有第一个选项的值 比如地区location
function multipleLevelSelect (main_sel_id, sub_id, data, init_selected, flag) {
	var $main_sel = $('#' + main_sel_id), $sub_sel = $('#' + sub_id), dataMap = {};
	$.each(data, function (i, item) {
		$main_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
		dataMap[item[0]] = i;
	});
	var _change_handle = function () {
		var id = $(this).val();
		$sub_sel.empty();
		if(id > 0)
		{
			var topItem = data[dataMap[id]];
			if(flag == true)
			{
				$sub_sel.append('<option value="' + topItem[0] + '">' + topItem[1] + '</option>');
			}
			$.each(topItem[2], function (i, item) {
				$sub_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
			});
		}
	};
	$main_sel.change(_change_handle);
	if (init_selected) {
		setTimeout(function() {
			for (var i = 0, m = data.length; i < m ; i ++) 
			{
				if (data[i][0] == init_selected) {
					$main_sel.val(data[i][0]);					
					_change_handle.apply($main_sel[0]);
					$sub_sel.val(data[i][0]);
					break;	
				}			
				for (var j = 0, n = data[i][2].length; j < n; j ++) 
				{
					if (data[i][2][j][0] == init_selected) {
						$main_sel.val(data[i][0]);
						_change_handle.apply($main_sel[0]);
						$sub_sel.val(data[i][2][j][0]);
						break;
					}	
				}
				if (j != n) break;
			}
		}, 1);
		//为了ie6下兼容
		setTimeout(function() {$sub_sel.val(init_selected);},1);
	}
}

/**
 *  职位，从原来的3级，改变为现在的2级输出
 *  author	jianghua
 *  2012 - 12 -30
 */
function multipPosition (main_sel_id, sub_id, data, init_selected, flag) {
	   var $main_sel = $('#' + main_sel_id), $sub_sel = $('#' + sub_id);
	   var parent_key = {};
	   var sub_key = {};
	   //生成第2级数据
		$.each(data, function (i, item) {
			$.each(item[2], function(index, itemObj){
				$main_sel.append('<option value="' + itemObj[0] + '">' + itemObj[1] + '</option>');
				sub_key[itemObj[0]] = index;
				parent_key[itemObj[0]] = i;
				
			});
		});
	   var _change_handle = function () {
		var id = $(this).val();
		$sub_sel.empty();
		if(id > 0)
		{
			var topItem = data[parent_key[id]];  //外围数组
			$.each(topItem[2][sub_key[id]][2], function (i, item) {
				$sub_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
			});
		}
	};
	$main_sel.change(_change_handle);
	
}

function loactionReg (main_sel_id, sub_name, data, init_selected) {
	var $main_sel = $('#' + main_sel_id), dataMap = {};
	$.each(data, function (i, item) {
		$main_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
		dataMap[item[0]] = i;
	});
	var _change_handle = function () {
		var id = $(this).val();
        $sub_sel = $('#' + sub_name);
        if (id == -1) {
			$sub_sel.html('<option value="-1">--请选择--</option>');
			return;
		}
		$sub_sel.empty();
		var topItem = data[dataMap[id]];
		$.each(topItem[2], function (i, item) {
			$sub_sel.append('<option value="' + item[0] + '">' + item[1] + '</option>');
		});
	};
	$main_sel.change(_change_handle);
	if (init_selected) {
		setTimeout(function() {
			for (var i = 0, m = data.length; i < m ; i ++) {
				if (data[i][0] == init_selected) {
					$main_sel.val(data[i][0]);
					_change_handle.apply($main_sel[0]);
					$main_sel.next().val(data[i][0]);
					break;	
				}
				for (var j = 0, n = data[i][2].length; j < n; j ++) {
					if (data[i][2][j][0] == init_selected) {
						$main_sel.val(data[i][0]);
						_change_handle.apply($main_sel[0]);
						$main_sel.next().val(data[i][2][j][0]);
						break;
					}	
				}
				if (j != n) break;
			}
		}, 1);
	}
}

/**
 * @use submit时check用到的判断
 * @tony.lu 2012-02-10
 */
function trim(ss){
	return ss.replace(/(^\s*)|(\s*$)/g, "");
}

function isAllDigit(str) {
	var ch, index;
	for(index = 0; index<str.length; index++) {
		ch = str.charAt(index);
		if (!(ch >= '0' && ch <= '9')) {
			return false;
		}
	}
	return true;
}

/*
function checkMobile(ctrl){
	if (typeof(ctrl) != 'object')
	{
		return false;
	}
	else
	{
		var datastr = trim(ctrl.val());
		if (datastr.length == 0)
		{
			return false;
		}
		else if (datastr.search(/^([1][0-9]{10})$/gi) == -1) 
		{
			return false;
		}
		return true;
	}
}
*/

/*
function checkEmail(ctrl){
	if (typeof(ctrl) != 'object')
	{
		return false;
	}
	else
	{
		var datastr = trim(ctrl.val());
		if (datastr.length == 0)
		{
			return false;
		}
		else
		{
			var myRegExp = /[a-z0-9](([a-z0-9]|[_\-\.][a-z0-9])*)@([a-z0-9]([a-z0-9]|[_\-][a-z0-9])*)((\.[a-z0-9]([a-z0-9]|[_\-][a-z0-9])*)*)/gi;
			var answerind = datastr.search(myRegExp);
			var answerarr = datastr.match(myRegExp);
		
			if (answerind != 0 || answerarr[0].length != datastr.length)
			{
				return false;
			}
		}
		return true;
	}
}
*/

 /*判断输入是否为合法的电话号码，匹配固定电话或小灵通*/
 function checkPhone(inpurStr)
 {
	var partten = /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/;
	if(partten.test(inpurStr))
	{
		//alert('是电话号码');
		return true;
	 }
	else
	{
		//alert('不是电话号码');
		return false;
	}
}


/**
* 截字，中文算2个字符
*/
String.prototype.mb_substr = function( len, hasDot )
{
	var newLength = 0;
	var newStr = "";
	var chineseRegex = /[^\x00-\xff]/g;
	var singleChar = "";
	var strLength = this.replace(chineseRegex,"**").length;

	if( strLength < len ) return this;

	for(var i = 0;i < strLength;i++)
	{
		singleChar = this.charAt(i).toString();
		if(singleChar.match(chineseRegex) != null)
		{
			newLength += 2;
		}
		else
		{
			newLength++;
		}
		if(newLength > len)
		{
			break;
		}
		newStr += singleChar;
	}

	newStr += hasDot ? ".." : "";

	return newStr;
}

String.prototype.mb_strlen = function()
{
	var chineseRegex = /[^\x00-\xff]/g;
	return this.replace(chineseRegex,"**").length;
}

/**
 * 字符串长度，中文按两个算；
 * @param text
 * @return {Number}
 */
function getTextLength(text){
    var len = 0;
    for (i = 0; i < text.length; i++) {
        if (text.charCodeAt(i) > 256) {
            len += 2;
        }
        else {
            len++;
        }
    }
    return len;
}

function DrawImage(ImgD,FitWidth,FitHeight){
	var image=new Image();
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		if(image.width/image.height>= FitWidth/FitHeight){
			if(image.width>FitWidth){
				ImgD.width=FitWidth;
				ImgD.height=(image.height*FitWidth)/image.width;
			}else{
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		} else{
			if(image.height>FitHeight){
				ImgD.height=FitHeight;
				ImgD.width=(image.width*FitHeight)/image.height;
			}else{
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		}
	}
}

//自己不能给自己发私信
function can_not_send_msg_to_self(){
	ye_msg.open("自己不能给自己发私信",3,3);
}

//自己不能标记自己为备选人才
function can_not_do_reserve_to_self(){
	ye_msg.open("自己不能标记自己为备选人才",3,3);
}

//自己不能给自己委托档案
function can_not_entrust_resume_to_self(){
	ye_msg.open("自己不能给自己委托档案",3,3);
}

//自己不能推荐自己
function can_not_recommend_to_self(){
	ye_msg.open("自己不能推荐自己",3,3);
}

function checkBlockWord(content){
    var blockWord = '';
    $.ajax({
        type:'POST',
        dataType:'json',
        url:'http://www.wealink.com/ajax/blockwords.php',
        data:{'keywords':content},
        async:false,
        cache:false,
        success:function(resp){
            if(resp.flag=='no'){
                blockWord = resp.msg;
            }
        }
    });
    return $.trim(blockWord);
}

function wl_check_blockwords(keywords){
	if(!keywords || keywords==''){
		return true;
	}

	var result = true;
	$.ajax({
	   type: "POST",
	   dataType: "json",
	   url: 'http://www.wealink.com/ajax/blockwords.php',
	   data: 'keywords='+keywords,
	   async: false,
       cache: false,
	   success: function(data){
			if(data.flag=='no'){
				result = false;
			}
	   }
	});	

	return result;
}

//201311改版新增我的若邻左侧菜单缓存设置
function homeleft_set_current_tab_in(flag0or1){
	$.ajax({
	   type: "POST",
	   dataType: "json",
	   url: 'http://www.wealink.com/user/home/homeleft_set_current_tab_in/',
	   data: {'flag0or1':flag0or1},
	   async: false,
       cache: false,
	   success: function(data){return true;}
	});	
	return true;
}

// 数组里删除指定下标的元素；
Array.prototype.remove = function(from, to) {
    var rest = this.slice((to || from) + 1 || this.length);
    this.length = from < 0 ? this.length + from : from;
    return this.push.apply(this, rest);
};

// cookie操作；
if (!window.getCookie) {
    function setCookie (cookieName, cookieValue, nDays) {
        var today = new Date;
        var expire = new Date;
        if (nDays) {
            expire.setTime(today.getTime() + 3600000 * 24 * nDays);
        }
        document.cookie = cookieName + '=' + escape(cookieValue) +
            (nDays ? ('; expires=' + expire.toGMTString()) : '') + '; path=/; domain=wealink.com';
    }

    function getCookie (name) {
        var nameEQ = name + '=';
        var ca = document.cookie.replace(/\s/g, '').split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            if (c.indexOf(nameEQ) == 0)
                return unescape(c.substring(nameEQ.length, c.length).replace(/\+/g, ' '));
        }
        return null;
    }
}

//2014-12-09
var checkSensitiveWordsType = "" ; //检测的敏感词类别，如“教育经历”等

//校验关键字提示
/*
 * 2014-12-09
 * 校验关键字窗口提示
 * checkContents = 校验内容，例“教育经历"
 * block_word = 所校验的关键字
 */
function showCheckSensitiveWords(checkSensitiveWordsType,block_word){
    if ( checkSensitiveWords == 1){
        var showMessage = '你的'+checkSensitiveWordsType+'含有敏感词“'+block_word+'”，我们将尽快审核，审核不通过会删除本'+checkSensitiveWordsType+'，确定保存吗？';
    }else{
        var showMessage = '你的'+checkSensitiveWordsType+'含有敏感词“'+block_word+'”，请重新填写！';
    }
    return showMessage;
}
