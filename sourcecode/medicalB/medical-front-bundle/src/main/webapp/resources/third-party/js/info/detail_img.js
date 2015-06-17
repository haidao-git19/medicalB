var smailImgPageSize = Math.ceil($("#thumblist > li").length/5);
var smailImgIndex = 1;
$(document).ready(
	function(){
		$(".jqzoom").jqzoom({zoomType:"standard",lens:true,preloadImages:false,alwaysOn:false,zoomWidth:380,zoomHeight:380});
		
		if($("#thumblist > li").length > 5){
			$("#nextBtn").click(function(){
				if(smailImgIndex >= smailImgPageSize) return;
				smailImgIndex++;
				
				$("#thumblist").animate({'margin-left': '-' + (smailImgIndex-1)*250 + 'px'}, "slow");
				
				if(smailImgIndex >= smailImgPageSize) {
					setOpacity("nextBtn", 5);
				}
			});
			
			$("#prevBtn").click(function(){
				if(smailImgIndex <= 1) return;
				
				smailImgIndex--;
				
				$("#thumblist").animate({'margin-left': (smailImgIndex-1)*250 + 'px'}, "slow");
				
				setOpacity("nextBtn", 10);
			});
		} else {
			$("#prevBtn").hide();
			$("#nextBtn").hide();
		}
	}
);

// value: 0-10
function setOpacity(id, value) {
	document.getElementById(id).style.opacity = value / 10;
	document.getElementById(id).style.filter = 'alpha(opacity=' + value*10 + ')';
}