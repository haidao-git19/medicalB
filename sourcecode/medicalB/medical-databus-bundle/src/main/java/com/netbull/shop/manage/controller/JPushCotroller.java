package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.manage.weixin.service.JPushService;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
public class JPushCotroller{

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private JPushService jPushService;
	
	/**
	 * 通过HTTPS POST向某单个设备或者某设备列表推送一条通知、或者消息。
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/push", produces="application/json;charset=utf-8")
	@ResponseBody
	public String push(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String aliases = !NullUtil.isNull(requestMap.get("aliases"))?requestMap.get("aliases"):null;
		String alert = !NullUtil.isNull(requestMap.get("alert"))?requestMap.get("alert"):null;
		
		HttpXmlUtil.sendNoticeMsg(aliases, alert);
		jPushService.savePushLogInfo(requestMap);
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryPushList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryPushList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("code", Constants.SUCCESS);
		resultMap.put("msg", Constants.SUCCESS_MSG);
		resultMap.put("pushList", jPushService.queryPushLogList(requestMap));
		
		return JsonUtils.obj2Json(resultMap);
	}
	
	public static PushPayload buildPushObject_all_alias_alert(String aliases,String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(aliases))
                .setNotification(Notification.alert(alert))
                .build();
    }
}
