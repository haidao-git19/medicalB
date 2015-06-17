<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=C7c10f81a443d30812fccb1203518bd1"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/jquery-ui-bs/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/jquery.artDialog.source.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx }/resources/third-party/js/artDialog.iframeTools.js"></script>

	<style type="text/css">
		.nav-tabs>.active>a,.nav-tabs>.active>a:hover,.nav-tabs>.active>a:focus
			{
			background-color: #0088cc;
			color: #fff;
		}
		.msg_div_white {
			border: 1px solid #bbb;
			background-color: #fff;
			border-radius: 4px;
			-moz-border-radius: 4px;
			-webkit-border-radius: 4px;
			-o-border-radius: 4px;
		}
	   .file-wrapper{ cursor: pointer; display: inline-block; overflow: hidden; position: relative; *display:inline; zoom:1; }
	   .file-wrapper input{ cursor: pointer; position: absolute; right: 0; top: 0; height:26px; }
	   .file-wrapper .button{ cursor: pointer; display:inline-block; width:60px; height:26px; *display:inline; zoom:1; }
	   body, html,#allmap {width: 630px;height: 470px;overflow: hidden;margin:0;}
	   #allmap {width: 627px;height: 400px;border-radius:3px;}
	</style>
<body>
	<div id="modal_parameter">
		<div class="modal-header">
			<h3>请标记地图位置</h3>
		</div>
		<div class="control-group inline" style="margin:10px 0;">
				<label class="inline" for="hostLocation">查询位置:</label> &nbsp;&nbsp;
				<input id="hostLocation" name="hostLocation" type="text" class="input-text-medium" placeholder="素材标题" onkeyup="searchLocation()" onblur="searchLocation()">
		</div>

			<div class="span6 msg_div_white" >
				<div>
					<div>
						<div id="allmap"></div>
					</div>
				</div>
			</div>
		</div>

</body>

<script type="text/javascript">
//百度地图API功能
var map = null;
$(function() {
	map = new BMap.Map("allmap");            // 创建Map实例
	map.centerAndZoom(new BMap.Point(117.205329, 31.820654), 11);
	setTimeout(function(){
		map.setZoom(11);   
	}, 2000);  //2秒后放大到14级
	map.enableScrollWheelZoom(true);
	
	var gc = new BMap.Geocoder();    
	map.addEventListener("click", function(e){        
	    var pt = e.point;
	    gc.getLocation(pt, function(rs){
	        var addComp = rs.addressComponents;
	        var hotelAddress = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
	        var hotelLocationX = rs.point.lng;//经度
	        var hotelLocationY = rs.point.lat;//
	        parent.setLocation(hotelAddress,hotelLocationX,hotelLocationY);
	    });        
	}); 
});

function showMsg(){
	art.dialog({
		title:'温馨提示',
		content: '操作成功！',
		width: 160,
		height: 80,
		fixed: true,
		lock: true,
		yesText:'确定',
	    noText: '关闭'
	});
}

/**
 * 根据关键字查询位置；
 */
function searchLocation(){
	var liveLocation = $("#hostLocation").val();
	var local = new BMap.LocalSearch(map, {
		  renderOptions:{map: map}
	});
	local.search(liveLocation);
}



</script>
