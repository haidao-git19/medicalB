var smailImgPageSize;
var smailImgIndex = 1, smailImgIndex_prev = 0;

$(document).ready(
	function(){
		smailImgPageSize = Math.ceil($("#thumblist > li").length - 4);
		
		$(".jqzoom").jqzoom({zoomType:"standard",lens:true,preloadImages:false,alwaysOn:false,zoomWidth:380,zoomHeight:380});
		
		if($("#thumblist > li").length > 5){
			$("#nextBtn").click(function(){
				if(smailImgIndex >= smailImgPageSize) return;
				smailImgIndex++;
				
				smailImgIndex_prev = smailImgIndex_prev + 50;
				$("#thumblist").animate({'margin-left': '-' + smailImgIndex_prev + 'px'}, "slow");
				
				if(smailImgIndex >= smailImgPageSize) {
					setOpacity("nextBtn", 3);
				}
			});
			
			$("#prevBtn").click(function(){
				if(smailImgIndex <= 1) return;
				smailImgIndex--;
				
				smailImgIndex_prev = smailImgIndex_prev - 50;
				$("#thumblist").animate({'margin-left': '-' + smailImgIndex_prev + 'px'}, "slow");
				
				if(smailImgIndex <= 1) {
					setOpacity("prevBtn", 3);
				}
			});
		} else {
			setOpacity("prevBtn", 0);
			setOpacity("nextBtn", 0);
		}
	}
);

function setOpacity(id, value) {
	document.getElementById(id).style.opacity = value / 10;
	document.getElementById(id).style.filter = 'alpha(opacity=' + value*10 + ')';
}

function updatedProducts(amount) {
	var curAmt = parseInt($("#product_amount").val());
	var amt = parseInt(amount);
	curAmt += amt;
	
	if(curAmt <= 1) {
		$("#product_amount").val(1);	
	}else{
		$("#product_amount").val(curAmt);		
	}
}

function addItem() {
	var cartInfo = $("#shoppingCartForm").serialize();
	
	var curAmt = parseInt($("#product_amount").val());
	var singlePrice = parseFloat($("#goodsSingePrice").val());
	
	$("#add_amt").empty().text(curAmt)
	$("#add_price").empty().text((curAmt * singlePrice).toFixed(2))
	
	$.ajax({
		url : ctx + '/anon/shoppingcart/addItem',
		data : cartInfo,
		type : 'POST',
		success : function(data) {
			$('#addCartInfo').show();
		}
	});
}