<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/resources/third-party/js/questionnaire/questionnaireCase.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="breadcrumb">
					<!-- 左边导航菜单 begin -->
					<li class="active">${parentMenu}<span class="divider">/</span></li>
					<li class="active">题目管理</li>
					<!-- 左边导航菜单 end -->
				</ul>
			<form id="queryForm" action="javascript:table.fnDraw();">
				<div class="form-inline">
					<div class="control-group inline">
						<label class="inline" for="title">题目名称:</label> 
						<input id="title" name="title" type="text" class="input-text-medium" placeholder="题目名称">
					</div>
					<div class="control-group inline pull-right">
						<button type="button" id="query_btn" class="btn btn-primary">查询</button>
					</div>
				</div>
				<div class="row-fluid toolbar">
	        		<button type="button" class="btn btn-primary" onclick="openAddOrModifyQuestionnaireCase('')">新增</button> 
	        		<button type="button" class="btn btn-primary" onclick="window.location.href='${ctx}/questionnaire/questionnaireList'">返回</button>   		
	        		<div class="inline pull-right">
						<button type="button" onclick="previewQuestionnaire();" class="btn btn-primary">预览问卷</button>
					</div>
	        	</div>
	       </form>				
		</div>
	    <table id="_questionnaire_case_table" class="table table-hover table-bordered table-condensed table-striped">
			<tbody>
			</tbody>
		</table>
	</div>
	<jsp:include page="/WEB-INF/jsp/questionnaire/questionnairePreview.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/questionnaire/initAddOrUpdateQuestionnaireCase.jsp"></jsp:include>
</body>
</html>