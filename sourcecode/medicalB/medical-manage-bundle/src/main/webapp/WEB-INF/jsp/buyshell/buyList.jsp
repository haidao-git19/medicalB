<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>选宝贝</title>
		<meta content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0; user-scalable=no" id="viewport" name="viewport">
		<jsp:include page="/WEB-INF/jsp/mobileLib.jsp"></jsp:include>
		<script src="${ctx }/resources/third-party/js/touchslide.js?ver=${version}" type="text/javascript"></script>
		<script src="${ctx }/resources/third-party/js/scrollpagination.js" type="text/javascript" ></script>
		<script src="${ctx }/resources/third-party/js/jquery.lazyload.min.js?ver=${version}" type="text/javascript"></script>
    	<script src="${ctx }/resources/third-party/js/buyshell/buyList.js?ver=${version}" type="text/javascript"></script>
    	<script src="${ctx }/resources/third-party/js/buyshell/screenTips.js" type="text/javascript" ></script>
		<link href="${ctx }/resources/third-party/css/global.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/wnclient.css?ver=${version}" rel="stylesheet" type="text/css">
		<link href="${ctx }/resources/third-party/css/touchSlider.css?ver=${version}" rel="stylesheet" type="text/css">
		
		<script type="text/javascript">
        	var type = '${param.type}';
        </script>
	</head>
	
<body class="appbg">

	<input type="hidden" id="p" value="0"/>
		<div id="picScroll" class="picScroll">
			
			<div class="bd"  id="posterDiv">
				 <c:forEach items="${posterList}" var="poster">
					<ul>
						<li>
							<div style="position:relative;">
								<a href="${poster.url}">
									<img src="${poster.image}" style="width:100%;">
								</a>
								<img id="closeImage" src="${ctx }/resources/third-party/images/chacha_03.png" width="26" height="26" style="position:absolute; z-index:9999px;top:5px; right:5px;">
							 </div> 
						</li>
					</ul>
				</c:forEach> 
			</div>
			
			<div class="hd">
				<ul></ul>
			</div>
			
		</div>

<div id="hxTabBox"  class="tabbox">
  <div class="tab hd">
    <ul class="w33" style="background-color:#FFF">
      <li class="on">人气</li>
      <li>销量</li>
      <li>筛选</li>
    </ul>
  </div>
  
  <div id="tipsDiv" class="tree" style="display:none;">
  	<c:forEach items="${filterList}" var="filter">
  		<div class="tree_box">
	        <h3  style="background:#e4e4e4;" class="gray f16">
	           	 ${filter.attrname}
	        </h3>
	        <input type="hidden" id="paramId" value="${filter.id}">
	        <input type="hidden" id="${filter.id}_Attr" value="">
	        <ul class="tree_one" id="mobile_${filter.id}">
        	<c:forEach items="${filter.list}" var="list1">
	            <li>
	                <h4 id="${filter.id}_Btn" style="background:#fcfcfc;" class="f18 gray">
	                 	  ${list1.attrname}    
	                </h4>
	                <ul class="tree_two f14" >
	                	<c:forEach items="${list1.list}" var="list2">
		                    <li style="background:#f6f6f6;">
		                        ${list2.attrname}
		                    </li>
	                	</c:forEach>
	                </ul>
	            </li>
        		
        	</c:forEach>
        </ul>
    	</div>
  	</c:forEach>
	<div class="tc mt20" id="screenConfirmBtn"><input name="" type="button" class="btn-bigorg" value="确定"></div>

</div>
	<div class="ct-list bd" id="scrollDiv">
	   	<div class="hunit">
	      <ul class="clearfix wrapper" id="humanUl">
	      </ul>
	      <div class="cb"></div>
	    </div>
      	<div class="hunit">
	      <ul class="clearfix wrapper" id="salesUl">
	      </ul>
	      <div class="cb"></div>
	    </div>
	    <div class="hunit">
	      <ul class="clearfix wrapper"  id="screenUl">
	      </ul>
	      <div class="cb"></div>
	    </div>
    </div>
</div>

<a href="#top" class="u-backtop" id="toTop"><img src="${ctx }/resources/third-party/images/back_top_bg.png" width="40" height="40" style="position:fixed; bottom:10px; right:10px;"></a>

<div id="noGoodsWords" style="display:none; margin:10px; text-align:center; font-size:18px; color:gray; ">
	<img id="emptyBox" src="${ctx }/resources/third-party/images/kongxiangzi.png" width="140" height="140"  style=" display:none;margin-left:auto; margin-right:auto; margin-top:50px; margin-bottom:10px;">
	<p>暂无商品信息</p>
</div>

<ul class="clearfix" id="_itemTemp" style="display:none;">
 	 <li class="box-sizing" onclick="queryDetail('{4}','{5}','{6}','{1}')">
 	 	<div class="box">
 	 		<p class="showpic">
 	 			{0}
 	 		</p>
  		<p class="f14 lh130 pt8" style="height:3.3rem; overflow:hidden;">{1}</p>
      	<p class="f13 gray mt5">
      		<span class="fr">{2}人付款</span>
      		<span>
      			<img src="${ctx }/resources/third-party/images/icon-gd.png" align="absmiddle" width="14" class="fl">{3}
      		</span>
      	</p>
      </div>
   </li>
</ul>

<script type="text/javascript">

TouchSlide({ 
	slideCell:"#picScroll",
	titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
	autoPage:true, //自动分页
	pnLoop:true, // 前后按钮循环
	autoPlay:true,//自动运行
	interTime:5000//自动运行间隔
	//switchLoad:"_src" //切换加载，真实图片路径为"_src" 
});

  var x=0;
  TouchSlide({ 
	  slideCell:"#hxTabBox",
	  startFun:function(i,c){ 
		  $("#p").val(0);
		  $("#humanUl").html('');
		  $("#salesUl").html('');
		  $("#screenUl").html('');
		  if(i==0){
			  $("#tipsDiv").hide();
			  $("#noGoodsWords").hide();
			  $("#scrollDiv").show();
			  $("#toTop").show();
			  $("#emptyBox").css("display","none");
			  $("#noGoodsWords").css("display","none");
			  scrollPagination($("#humanUl"),'0',null,null);
		  }else if(i==1){
			  $("#tipsDiv").hide();
			  $("#noGoodsWords").hide();
			  $("#scrollDiv").show();
			  $("#toTop").show();
			  $("#emptyBox").css("display","none");
			  $("#noGoodsWords").css("display","none");
			  scrollPagination($("#salesUl"),'1',null,null);
		  }else if(i==2){
			  $("#moreGoods").hide();
			  $("#toTop").hide();
			  $('#nomoreresults').fadeIn();
			  $('#humanUl').stopScrollPagination();
			  $('#salesUl').stopScrollPagination();
			  $('#screenUl').stopScrollPagination();
			 
			  $("#emptyBox").css("display","none");
			  $("#noGoodsWords").css("display","none");
			  $("#tipsDiv").css("display","block");
			  $("#scrollDiv").css("display","none");
			  $("#screenConfirmBtn").on('click',function(){
				  	x=0;
				    $("#tipsDiv").css("display","none");
					$("#scrollDiv").css("display","block");
					var $Array=$("input[id='paramId']");
					var id1=$Array[0].value;
					var id2=$Array[1].value;
					scrollPagination($("#screenUl"),"3",$("#"+id1+"_Attr").val(),$("#"+id2+"_Attr").val());
				});
		  }
  	  } 
  });
</script>
</body>
</html>
