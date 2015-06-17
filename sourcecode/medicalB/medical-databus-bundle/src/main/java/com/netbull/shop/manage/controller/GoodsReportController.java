package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.GoodsReport;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.GoodsContentDC;
import com.netbull.shop.manage.weixin.service.GoodsReportService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class GoodsReportController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private GoodsReportService goodsReportService;
	
	/**
	 * 点赞
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/anon/addPraise",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String addPraise(HttpServletRequest request) throws IOException{
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("点赞；参数：" + requestMap.toString());
			}
			GoodsReport goodsReport=goodsReportService.queryGoodsReportByParams(requestMap);
			String praiseNum=(String) (goodsReport.getPraiseNum()==null?"0":goodsReport.getPraiseNum());
			requestMap.put("praiseNum", StringUtil.getString((Integer.parseInt(praiseNum)+1)));
			goodsReportService.updateGoodsReport(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	} 
}
