$(document).ready(function(){
	
	//默认显示合肥本地网医院
	$('#hospitalLatn_551').show();
	$('#hospitalLatn_551').siblings().hide();
	//控制医生选中
	$('#hospitalLatn a').hover( //鼠标滑过下拉列表是改变当前栏目样式 
			function(){ 
				$(this).siblings().addClass('normal').removeClass('menu-active')
				$(this).addClass('menu-active'); 
				var latnId=$(this).attr('latn');
				if($('#hospitalLatn_'+latnId).length<1){
					//暂无数据
					$('#hospitalLatn_none').show();
					$('#hospitalLatn_none').siblings().hide();
				}else{
					$('#hospitalLatn_'+latnId).show();
					$('#hospitalLatn_'+latnId).siblings().hide();
				}
			}, 
			function(){ 
			} 
		); 
	
	
	//控制电话咨询选中
	$('#tel_zx a').hover( //鼠标滑过下拉列表是改变当前栏目样式 
			function(){ 
				$(this).siblings().addClass('normal').removeClass('menu-active')
				$(this).addClass('menu-active'); 
				var latnId=$(this).attr('latn');
				if($('#hospitalLatn_'+latnId).length<1){
					//暂无数据
					$('#hospitalLatn_none').show();
					$('#hospitalLatn_none').siblings().hide();
				}else{
					$('#hospitalLatn_'+latnId).show();
					$('#hospitalLatn_'+latnId).siblings().hide();
				}
			}, 
			function(){ 
			} 
		); 
	
})


//查找医院科室
function section(){
	
	var url=ctx+'/web/index/section'
	$("#querySection").load(url);
}