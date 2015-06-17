<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${childMenu}</title>
<link href="${ctx }/resources/third-party/css/multimaterial.css" rel="stylesheet">
<link href="${ctx }/resources/third-party/css/editfile.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid modalMaterialChoose hide fade" id="goodsAttr_modal" style="width: 80%;margin-left:100px;" tabindex="-1" 
		 role="dialog" aria-labelledby="provinceModalLabel" aria-hidden="true">
		<div class="row-fluid">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>商品属性编辑</h3>
			</div>
			<form id="goods_attr_form" enctype="multipart/form-data">
				<div class="tabbable">
					<div class="tab-content">
						<div class="tab-pane active" id="profile_tab">
							<div class="container-fluid myminwidth">
								<div id="home2" class=" " style="min-width: 80%; background-color: #EEEEEE">
									<input type="hidden" name="id" id="_id">
									<input type="hidden" name="goodsCode" value="${goodsCode}">
									<div class="row-fluid">
										<div class="span6 msg_div_white" style="line-height: 300%; margin-right: 0px; height: auto; margin-left: 2%; width: 57.5%">
											<div style="margin-left: 5%; margin-top: 20px; margin-bottom: 50px; height: auto">
												<div class="tabbable">
													<div class="control-group inline">
														<label class="inline" for="attrCode">
															<span class="help-inline">*</span><font class="msg_font_bold">属性名称:</font></label> 
															<select name="attrCode" id="_attrCode" class="input-medium" placeholder="属性名称">
															
															</select>
													</div>												
													<div class="control-group">
														<label class="control-label">
															<span class="help-inline">*</span><font class="msg_font_bold">&nbsp;&nbsp;属性值&nbsp;&nbsp;</font>
															<input type="text" style="width: 80%"  name="attrValue" id="attrValue" placeholder="属性值" />
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer" style="text-align: center;padding:10px 10px 10px;">
										<a id="goodsAttrSaveBtn" class="btn btn-primary">完成</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>