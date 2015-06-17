// 给Date类型增加格式化原型方法
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"H+" : this.getHours(), // hour
		"h+" : this.getHours(),
		//"h+" : this.getHours()%12, //12 hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(m, i) {
		return args[i];
	});
}

String.prototype.replaceAll = function stringReplaceAll(AFindText, ARepText) {
	raRegExp = new RegExp(AFindText, "g");
	return this.replace(raRegExp, ARepText);
};

function myAlert(title, content) {
	if (!title) {
		title = "系统提示";
	}
	if (!content) {
		content = "";
	}
	var $div = $("#_my_alert");
	if($div.size()==0) {
		$div = $("<div id='_my_alert'><i class='ui-icon ui-icon-alert' style='float:left;margin-right:10px;'></i> <p></p></div>");
		$div.appendTo($("body"));
		$div.dialog({
			autoOpen : false,
			modal : true,
			buttons : {
				确定 : function() {
					$(this).dialog("close");
				}
			}
		});
	}
	$div.find("p").empty().html(content);
	$div.dialog("option","title",title);
	$div.dialog("open");
}

var myalert = myAlert;

function myConfirm(title,content,callback) {
	var divId = "_my_confirm";
	if (!title) {
		title = "系统提示";
	}
	if (!content) {
		content = "";
	}
	var $div = $("<div id='{0}' title='{1}' class='row-fluid' ></div>".format(
			divId, title));
	$div.append("<p><i class='icon-question-sign'></i> {0}<p>".format(content));
	$div.appendTo($("body"));
	$("#" + divId).dialog({
		autoOpen : true,
		modal : true,
		buttons : {
			确定 : function() {
				$(this).dialog("close");
				callback();
			},
			取消 : function() {
				$(this).dialog("close");
			}
		},
		close : function(evt,ui) {
			$(this).remove();
		}
	});
}

$(function() {
	$(document).ajaxError(function(evt, req, opts, ex) {
		if (req.status === 0 || req.readyState === 0) {
			return;
		}
		if (req.status === 404) {
			myAlert('','没有找到');
			return;
		}
		try{
			var result = $.parseJSON(req.responseText);
			myAlert('',result.msg);
		}catch(e){
			myAlert('', '系统内部错误');
		}
	}).ajaxSend(function(evt, request, settings) {
		var target = settings.maskTarget;
		if (settings.maskTarget) {
			$(settings.maskTarget).unmask();
			$(settings.maskTarget).mask("加载中...", 500);
		}
	}).ajaxComplete(function(event, request, settings) {
		var target = settings.maskTarget;
		if (settings.maskTarget) {
			$(settings.maskTarget).unmask();
		}
	});
});

(function($) {
	$.fn.ellipsis = function() {

		this.css({
			'overflow' : 'hidden',
			'text-overflow' : 'ellipsis',
			'white-space' : 'nowrap'
		});

		this.bind('mouseenter', function() {
			var $this = $(this);
			if (this.offsetWidth < this.scrollWidth) {
				$this.attr('title', $this.text());
			}
		});
	};
})(jQuery);

/**
 * 扩展dataTables -> myDataTables 1.提供默认配置：布局、分页、汉化、服务端数据设置、表格长字符串截短
 * 2.扩展配置参数paramSelector 用于获取查询参数 3.使用示例(需要先引入jquery.dataTables.js):
 * 
 * var table = $("#_rule_table").myDataTable({ "sAjaxSource" : './rules',
 * //服务端数据请求url "paramSelector" : '#_business,#_policy,#_status', //查询参数
 * "aoColumns" : [...] //列配置不变 });
 */
(function($) {

	var methods = {
		"init" : function(cfg) {
			var default_settings = {
				"bInfo" : true,
				"bServerSide" : true,
				"bPaginate" : true,
				"bFilter" : false,
				"aaSorting" : [ ],
				"bProcessing" : false,
				"bLengthChange" : true,
				"aLengthMenu" : [ [ 10, 20, 30 ], [ 10, 20, 30 ] ],
				"sPaginationType" : "full_numbers",
				"bStateSave" : true,
				"bAutoWidth" : false,
				"sDom" : '<"top"f>r<"datatable-scroll"t><"datatable-bottom"lip>',
				"oLanguage" : {
					"sLengthMenu" : "<div style='float:left;margin-left:0px;'>_MENU_</div>",
					"sZeroRecords" : "抱歉， 没有找到",
					"sInfo" : "共_TOTAL_条 / _PAGENUM_页",
					"sInfoEmpty" : "共 0 条数据/共 0 页！",
					"sInfoFiltered" : "(filtered from _MAX_ total records)",
					"sProcessing" : "加载中...",

					oPaginate : {
						sPrevious : "上一页",
						sNext : "下一页",
						sFirst : "首页",
						sLast : "尾页"
					}
				},
				"fnDrawCallback" : function(oSettings) {
					var table = this;
					$(this.context).find('tr td,tr th').each(function() {
						$(this).ellipsis();
					});
					$(this.context).find('.sorting,.sorting_desc,.sorting_asc').dblclick(function(e){
				    table.fnSort([]);
				    return false;
					});
				}
			};
			var settings = $.extend(default_settings, cfg);
			if (settings['bServerSide'] && !settings.fnServerData
					&& settings.paramSelector) {
				settings.fnServerData = function(sSource, aoData, fnCallback) {
					$(settings.paramSelector).each(function() {
						var name = $(this).attr("name");
						var value = $(this).val();
						name = name ? name : '';
						if($(this).attr("type")=="radio"){
							value = $("[name='"+name+"']:checked").val();
						}else if($(this).attr("type")=="checkbox"){
							//value = $("[name='"+name+"']:checked").val();
							value = $(this).prop("checked");
						}else{
							value = value ? value : '';
						}
						aoData.push({
							name : name,
							value : value
						});
					});
					$
							.ajax({
								"type" : "post",
								"url" : sSource,
								"data" : aoData,
								maskTarget : $(this).parents('.dataTables_wrapper')[0],
								"success" : function(json) {
									fnCallback(json);
								},
								"dataType" : "json",
								"cache" : false,
								"error" : function(xhr, error, thrown) {
									if (error == "parsererror") {
										alert("DataTables warning: JSON data from server could not be parsed. "
												+ "This is caused by a JSON formatting error.");
									}
								}
							});
				};
			}
			if (!settings['bServerSide']) {
				settings.fnServerData = function(sSource, aoData, fnCallback) {
				};
			}
			if (settings['aaData']) {
				$.each(settings['aaData'], function(i, row) {
					if (row) {
						$.each(row, function(j, data) {
							if (data == undefined || data == null) {
								settings['aaData'][i][j] = '';
							}
						});
					}
				});
			}
			return this.dataTable(settings);
		}
	};

	$.fn.myDataTable = function(method) {
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(arguments,
					1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.myDataTable');
		}
	};
})(jQuery);

rnd.today = new Date();
rnd.seed = rnd.today.getTime();
function rnd() {
	rnd.seed = (rnd.seed * 9301 + 49297) % 233280;
	return rnd.seed / (233280.0);
};
function rand(number) {
	return Math.ceil(rnd() * number);
}; 

function getString(data){
	return data;
	//return data==null||data==undefined?'':data.trim();
}
