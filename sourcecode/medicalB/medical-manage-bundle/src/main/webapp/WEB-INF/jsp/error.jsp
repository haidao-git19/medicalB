<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${childMenu}</title>
		<meta id="viewport" content="target-densitydpi=device-dpi,width=device-width,user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0,initial-scale=1.0 " name="viewport"/>
		<script type="text/javascript">
		 window.onload = function () {
			 var w = window.screen.width;
			 var h  = window.screen.height;
			 initWidth(w+"*"+h);		  
	     };
		
		</script>
	</head>

	<body>
	 <div  id="top">错误页面</div>
	            <div class="bot1" >
		                <p  id="p1">出错了！</p>
		            	<p  id="p2">错误原因：${errorMsg}</p>
		        </div>
		        <div id="code">
	            	<img style="width:92%" src="${ctx }/resources/img/error.png" />	            
		        </div>
		       

        
	</body>
</html>
