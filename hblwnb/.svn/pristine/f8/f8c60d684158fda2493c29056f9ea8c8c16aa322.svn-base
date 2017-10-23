var ls = {
	ctxPath : function(path) {
		return globalCtxPath + path;
	},
	loadingDiv : jQuery('<div title="操作提示" id="loadingDiv">正在加载中……</div>'),
	showInfo : function(str, closeCallBack) {
		jQuery('<div class="info-div" title="信息提示" style="margin:20px;">' + str + '</div>').dialog({
			modal : true,
			buttons : {
				"确认" : function() {
					$(this).dialog("destroy");
					if (closeCallBack) {
						closeCallBack();
					}
				}
			}
		});
	},
	showInfoAndGoto : function(str, url) {
		jQuery('<div class="info-div" title="信息提示" style="margin:20px;">' + str + '</div>').dialog({
			modal : true,
			buttons : {
				"确认" : function() {
					$(this).dialog("destroy");
					if (url) {
						window.location.href = url;
					} else {
						window.location.reload();
					}
				}
			}
		});
	},
	showAlert : function(str, closeCallBack) {
		jQuery('<div class="info-div" title="警告提示" style="margin:20px; color:red;">' + str + '</div>').dialog({
			modal : true,
			buttons : {
				"确认" : function() {
					$(this).dialog("destroy");
					if (closeCallBack) {
						closeCallBack();
					}
				}
			}
		});
	},
	showConfirm : function(str, yesCallBack, noCallBack) {
		jQuery('<div class="info-div" title="确认操作" style="margin:20px;">' + str + '</div>').dialog({
			modal : true,
			buttons : {
				"确定" : function() {
					$(this).dialog("destroy");
					if (yesCallBack) {
						yesCallBack();
					}
				},
				"取消" : function(){
					$(this).dialog("destroy");
					if (noCallBack) {
						noCallBack();
					}
				}
			}
		});
	},
	showProgress : function(str) {
		if (str) {
			ls.loadingDiv.html(str);
		} else {
			ls.loadingDiv.html('正在加载中……');
		}
		ls.loadingDiv.dialog({modal:true});
	},
	closeProgress : function() {
		ls.loadingDiv.dialog("destroy");
	},
	ajaxCall : function(url, onSuccess, noProgress) {
		if (!noProgress){ls.showProgress("正在加载中……");}
		$.ajax({
			url : url,
			cache : false,
			dataType: "json", 
			success : function(ret) {
				if (!noProgress){ls.closeProgress();}
				if (onSuccess) {
					onSuccess(ret);
				}
			},
			error : function(data) {
				if (!noProgress){ls.closeProgress();}
				console.log(data.statusText+"\n"+data.readyState+"\n"+data.status);
				ls.showAlert("服务器错误，请稍候访问！");
			}
		});
	},
	ajaxCallAsync : function(url, onSuccess, noProgress) {
		if (!noProgress){ls.showProgress("正在加载中……");}
		$.ajax({
			url : url,
			cache : false,
			async:false,
			dataType: "json", 
			success : function(ret) {
				if (!noProgress){ls.closeProgress();}
				if (onSuccess) {
					onSuccess(ret);
				}
			},
			error : function(data) {
				if (!noProgress){ls.closeProgress();}
				ls.showAlert("服务器错误，请稍候访问！");
			}
		});
	},
	ajaxValidForm : function(id, onSuccess, beforeSubmitCheck) {
		var formId = "#" + id;
		jQuery(formId).validationEngine({
			promptPosition : "topRight",
			autoHidePrompt : true,
			autoHideDelay : 3000,
			onValidationComplete : function(form, status) {
				if (status) {
					if (beforeSubmitCheck && !beforeSubmitCheck()) {
						return;
					}
					var options = {
						dataType:'json',
						beforeSubmit: function(){
							
							},
						success : function(ret){
								
								if (onSuccess) {
									onSuccess(ret);
								}
							},
						error : function(data) {
								
							}
					};
					$(form).ajaxSubmit(options);
				}
			}
		});
	},
	ajaxForm : function(id, onSuccess, beforeSubmitCheck) {
		var formId = "#" + id;
		var options = {
				dataType:'json',
				beforeSubmit: function(){
					ls.showProgress("正在加载中……");
					},
				success : function(ret){
						ls.closeProgress();
						if (onSuccess) {
							onSuccess(ret);
						}
					},
				error : function(data) {
						ls.closeProgress();
						ls.showAlert("服务器错误，请稍候访问！");
					}
			};
		$(formId).ajaxForm(options);
	},
	validForm : function(id) {
		jQuery("#" + id).validationEngine({
			promptPosition : "topRight",
			autoHidePrompt : true,
			autoHideDelay : 3000
		});
	},
	fillForm : function(obj, preffix){
		for (var p in obj) {
			var eId = '#' + preffix + p;
			if (typeof (obj[p]) != "function" && $(eId)) {
				$(eId).val("" + obj[p]);
			}
		}
	},
	fillHtml : function(obj, preffix){
		for (var p in obj) {
			var eId = '#' + preffix + p;
			if (typeof (obj[p]) != "function" && $(eId)) {
				$(eId).html("" + obj[p]);
			}
		}
	}
}


/** PageList util */
var PageList = {
	setMyForm : function(f) {
		this.myForm = f;
	},
	setCurrentPage : function(p) {
		this.currentPage = p;
	},
	nextPage: function() {
		this.myForm["page"].value = this.currentPage + 1;
		this.myForm.submit();
	},
	prePage: function(){
		if (this.currentPage > 1){
			this.myForm["page"].value = this.currentPage - 1;
		}
		this.myForm.submit();	
	},
	changePage: function() {
		this.myForm.submit();
	},
	gotoPage:function(p) {
		this.myForm["page"].value = p;
		this.myForm.submit();
	}
}

/** 配合宏FormPageList使用，用于翻页显示 **/
function flipOver(page,formId){
	if(page > 0)
		$("#page").val(page);
	$("#"+formId).submit();
}

var globalDateFormat = {
		closeText : '关闭',
		prevText : '<向前',
		nextText : '向后>',
		currentText : '今天',
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
				'十一月', '十二月' ],
		monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
				'十月', '十一月', '十二月' ],
		dayNames : [ '星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		weekHeader : '星期',
		hourText : '时',
		minuteText : '分',
		secondText : '秒',
		timeText : '选定时间',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : false,
		showTime : false,
		yearSuffix : '',
		dateFormat : 'yy-mm-dd',
		timeFormat : 'hh:mm:ss',
		alwaysSetTime : false,
		showHour : false,
		showMinute : false,
		showSecond : false,
		showButtonPanel : false
	};
	
var globalDateFormat2 = {
		closeText : '确定',
		prevText : '<向前',
		nextText : '向后>',
		currentText : '',
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
				'十一月', '十二月' ],
		monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
				'十月', '十一月', '十二月' ],
		dayNames : [ '星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		weekHeader : '星期',
		hourText : '时',
		minuteText : '分',
		secondText : '秒',
		timeText : '选定时间',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : true,
		showTime : false,
		yearSuffix : '',
		dateFormat : 'yy-mm-dd',
		timeFormat : 'hh:mm:ss',
		alwaysSetTime : false,
		showHour : false,
		showMinute : false,
		showSecond : false,
		changeMonth: true,
		changeYear: true,
		yearRange:'1960:2020',
		showButtonPanel : false
	};
	
var globalDateFormat3 = {
		closeText : '确定',
		prevText : '<向前',
		nextText : '向后>',
		currentText : '本月',
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
				'十一月', '十二月' ],
		monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
				'十月', '十一月', '十二月' ],
		timeText : '选定时间',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : true,
		showTime : false,
		yearSuffix : '',
		dateFormat : 'yy-mm',
		alwaysSetTime : false,
		changeMonth: true,
		changeYear: true,
		yearRange:'1980:2020',
		showButtonPanel : true,
		onClose: function(dateText, inst) { 
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		//	$(this).datepicker('setDate', new Date(year, month, 1));
			$(this).val($.datepicker.formatDate('yy-mm', new Date(year, month, 1)));
        },
		beforeShow : function(input, inst) {
			var datestr;
			if ((datestr = $(this).val()).length > 0) {
				var actDate = datestr.split('-');
                year = actDate[0];
                month = actDate[1]-1;
				$(this).datepicker('option', 'defaultDate', new Date(year, month, 1));
				$(this).datepicker('setDate', new Date(year, month, 1));
			}
        }
	};

/** 显示人名币 **/
function rmb(i){
	if (i){
		var yuan = parseInt(i / 100);
		var fen = i % 100;
		if (fen > 0){
			return yuan + "." + (fen < 10 ? "0":"") + fen + "元";	
		} else {
			return yuan + "元";
		}
	} else {
		return  "0元";
	}
}

function fix2(i){
	if (i){
		i = parseInt(i);
		var yuan = parseInt(i / 100);
		var fen = i % 100;
		if (fen > 0){
			return yuan + "." + (fen < 10 ? "0":"") + fen;	
		} else {
			return yuan;
		}
	} else {
		return 0;
	}
}

//时间
function showDate(time){
	var date=new Date(time);
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    var day=date.getDate();
    var hour=date.getHours();
    var minute=date.getMinutes();
    var second=date.getSeconds();
    return year+"-"+month+"-"+day;
}
//显示通知信息
/**
 * layout:
 *  	'top':页面顶部;
    	'topCenter：页面顶部中央';
    	'topLeft'：页面顶部靠左;
    	'topRight'：页面顶部靠右;
    	'center'：页面中央
    	'centerLeft'：页面中央靠左;
    	'centerRight'：页面中央靠右;
    	'bottom'：页面底部;
    	'bottomCenter'：页面底部中央;
    	'bottomLeft'：页面左下角;
    	'bottomRight'：页面右下角;
    type:
    	'alert';
        'information';
        'error';
        'warning';
        'notification';
        'success';
    timeout:
    	false:不自动关闭
    	时间：如3000代表3000ms，即3秒后关闭
 * 
 * */
function showNotification(layout,type,text,timeout){
	var n = noty({
		layout: layout,
		theme: 'noty',
		type: type,
		text: text,
		dismissQueue: true,
		animation: {
        	open: {height: 'toggle'},
        	close: {height: 'toggle'},
        	easing: 'swing',
        	speed: 500
   		},
		timeout: timeout,
		closeWith: ['button']
	});
}

/**
* dialog中使用的分页
*
**/
function dialogPageList(func, tp, cp, str1, str2, str3){
	var tmp = 5, nextStart = cp + 1, nextDistance = tp - cp;

	var dhtm = '<div class="dialog-page-list">共'  + tp + '页，请选择:&nbsp;';
	var param = '\''+ str1 +'\'';
	if(str2 != undefined){
		param += ',\''+str2+'\'';
	}
	if(str3 != undefined){
		param += ',\''+str3+'\'';
	}
	if(cp > tmp){
		dhtm += '<span onclick="'+ func +'(1,' + param + ')" > [<b>1</b>] </span>';
		dhtm += '<span onclick="'+ func +'(2,' + param + ')" > [<b>2</b>] </span>';
		dhtm += '<span class="dot" > <b> ... </b> </span>';
		for (var i=1; i <= cp; i++) {
			var distance =  cp - i;
			if(distance < 5){
				if (cp == i)
					dhtm += '<span class="on"> [<b>'+ i +'</b>] </span>';
				else
					dhtm += '<span onclick="'+ func +'('+ i +',' + param + ')" > [<b>'+ i +'</b>] </span>';
			}
		}
	}else{
		for (var i=1; i <= cp; i++) {
			if (cp == i)
				dhtm += '<span class="on"> [<b>'+ i +'</b>] </span>';
			else
				dhtm += '<span onclick="'+ func +'('+ i +',' + param + ')" > [<b>'+ i +'</b>] </span>';
		}
	}
	
	if(nextDistance > tmp){
		for (var i=1; i <= tp; i++) {
			var distance =  i-cp;
			if(distance>0 && distance < 5){
				dhtm += '<span onclick="'+ func +'('+ i +',' + param + ')" > [<b>'+ i +'</b>] </span>';
			}
		}
		var tp2= tp-1;
		dhtm += '<span class="dot" > <b> ... </b> </span>';
		dhtm += '<span onclick="'+ func +'('+tp2+',' + param + ')" > [<b>'+tp2+'</b>] </span>';
		dhtm += '<span onclick="'+ func +'('+tp+',' + param + ')" > [<b>'+tp+'</b>] </span>';
	}else{
		for (var i=1; i <= tp; i++) {
			if(i > cp){
				dhtm += '<span onclick="'+ func +'('+ i +',' + param + ')" > [<b>'+ i +'</b>] </span>';
			}
		}
	}
	dhtm += '</div><div class="clear"></div>';
	return dhtm;
}