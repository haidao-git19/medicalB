<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>地址列表</title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
		<link href="${ctx }/resources/third-party/css/address.css?ver=${version}" rel="stylesheet" type="text/css">
		<script type="text/javascript">
        	var goodsCode = '${_param.goodsCode}';
        	var goodsVersion = '${_param.goodsVersion}';
        	var goodsStock = '${_param.goodsStock}';
        	var acceptId = '${_param.acceptId}';
        	var orderCode = '${_param.orderCode}';
        	var type = '${_param.type}';
        	var topage = '${_param.topage}';
        	var flag = '${_param.flag}';
        </script>
        <script src="${ctx }/resources/third-party/js/address/addressList.js?ver=${version}" type="text/javascript"></script>
	</head>
<body>
	<div class="shadow"></div>
	<div id="_addressDiv">

	</div>
	
	<div id="_addressDivTemp" style="display: none;">
		<div class="dzbox" id="dzbox_{3}">
			<div onclick="toChoose('{3}','{0}','{1}','{2}')">
				<div class="dzleft" >
					<div>
						<div class="dztop-left">{0}</div>
						<div class="dztop-right">{1}</div>
					</div>
					<div class="dztop-bottom">{2}</div>
				</div>
			</div>
			<p class="xiugai"><a onclick="toModify('{3}')">[修改]</a> 
				<img src="${ctx }/resources/third-party/images/huishou_03.png" width="24" height="27" onclick="deleteAddress('{3}')" id="deleteBut" style="display: none;">
			</p>
		</div>
		<div class="block"></div>
	</div>
	
	<div class="footerbox">
		<div class="add">新增收货地址</div>
	</div>
</body>
</html>
