package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.CatalogDC;
import com.netbull.shop.manage.common.util.GoodsDC;
import com.netbull.shop.manage.weixin.service.PosterService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@Controller
public class PosterCotroller {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Resource
	private PosterService posterService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/poster", produces="application/json;charset=utf-8")
	@ResponseBody
	public String poster(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查询列表信息，参数：" + requestMap.toString());
			}
			List<Map> poster = posterService.myPoster(null);
			for (Iterator iterator = poster.iterator(); iterator.hasNext();) {
				Map posterMap = (Map) iterator.next();
				String type = StringUtil.getString(posterMap.get("type"));
				posterMap.put("image", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+posterMap.get("image"));
			}
			
			resultMap.put("posterList", poster);
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
