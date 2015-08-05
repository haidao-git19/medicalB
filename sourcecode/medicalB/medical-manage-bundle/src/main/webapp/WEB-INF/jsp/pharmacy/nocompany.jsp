<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctx }/resources/third-party/js/jquery.artDialog.source.js?skin=idialog"></script>
<script type="text/javascript"
	src="${ctx }/resources/third-party/js/artDialog.iframeTools.js"></script>
<link href="${ctx }/resources/third-party/css/multimaterial.css"
	rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctx }/resources/js/pharmacy/editPharmacy.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
				<li class="active">${parentMenu}<span class="divider">/</span></li>
				<li class="active">药店管理</li>
			</ul>
			<div class="tabbable">
				<div class="tab-content">
					<div class="tab-pane active" id="profile_tab">
						<div class="container-fluid myminwidth">
							<div id="home2" class=" " style="min-width: 900px; background-color: #EEEEEE">
									您不是医药公司账号不能进行药店管理 
							</div>
							<div style="margin-top: 20px;">
								<div align="center" style="margin-top: 10px;">
									<button type="button" class="btn btn-primary"
										onclick="history.go(-1);">返回</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>