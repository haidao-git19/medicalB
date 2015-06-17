$(document).ready(function(){ 
	var as = AreaSelector({
		selProvinceId: 'selProvince',
		selCityId : 'selCity',
	    selAreaId : 'selArea',
		onProvinceChange : function() {
			if(this.options[this.selectedIndex].value=='-1'){
				$('#_province').val('');
				$('#_city').val('');
				$('#_area').val('');
			}else{
				$('#_province').val(this.options[this.selectedIndex].text);
			}
		},
		onCityChange : function() {
			if(this.options[this.selectedIndex].value=='-1'){
				$('#_city').val('');
				$('#_area').val('');
			}else{
				$('#_city').val(this.options[this.selectedIndex].text);
			}
		},
		onAreaChange : function() {
			if(this.options[this.selectedIndex].value=='-1'){
				$('#_area').val('');
			}else{
				$('#_area').val(this.options[this.selectedIndex].text);
			}
		}
	});
	as.initProvinceByText('安徽');
	$('#_province').val($("#selProvince").find("option:selected").text());
});

function submitOrder() {
    $('#orderForm').attr("action", ctx + '/anon/buy/saveOrder');
    $('#orderForm').attr("method", "POST")
    $('#orderForm').submit();
}