function updatedItem(goodsCode, amount) {
	var curAmt = parseInt($('#product_amount_'+goodsCode).val());
	var amt = parseInt(amount);
	curAmt += amt;
	if(curAmt <= 1) {
		curAmt = 1; 
	}
	$('#product_amount_'+goodsCode).val(curAmt);		
	
	$.ajax({
		url : ctx + '/anon/shoppingcart/addItem',
		data : 'goodsCode='+goodsCode+"&goodsAmount="+curAmt,
		type : 'POST',
		success : function(data) {
			window.location.href=ctx + '/anon/buy/cart';
		}
	});
}

function delItem(goodsCode) {
	if (confirm("确定删除？")) {
		$.ajax({
			url : ctx + '/anon/shoppingcart/delItem',
			data : 'goodsCode='+goodsCode,
			type : 'POST',
			success : function(data) {
				window.location.href=ctx + '/anon/buy/cart';
			}
		});
	}
}

function delItems() {
	var chk_value = '';
	$('input[name="cart_item"]:checked').each(function(){    
		chk_value += $(this).val()+",";
	});
	
	if(chk_value.length <= 0) {
		return;		
	}
	
	if (confirm("确定删除？")) {
		$.ajax({
			url : ctx + '/anon/shoppingcart/delItems',
			data : 'goodsCodes='+chk_value,
			type : 'POST',
			success : function(data) {
				window.location.href=ctx + '/anon/buy/cart';
			}
		});
	}
}

function checkAllItem(eid) {
	if($('#'+eid).is(':checked')) {
		$('input[type="checkbox"]').attr("checked", "checked");
	}else{
		$('input[type="checkbox"]').removeAttr("checked");
	}
}

function clearCart(goodsCode) {
	if (confirm("确定清空购物车？")) {
		$.ajax({
			url : ctx + '/anon/shoppingcart/clearCart',
			data : '',
			type : 'POST',
			success : function(data) {
				window.location.href=ctx + '/anon/buy/cart';
			}
		});
	}
}

function fillOrder() {
	window.location.href=ctx + '/anon/buy/fillOrder';
}

