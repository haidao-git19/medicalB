package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.JPushService;
import com.netbull.shop.manage.weixin.service.MessageService;
import com.netbull.shop.util.RequestUtils;

@Controller
public class MessageController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private MessageService messageService;
	@Autowired
	private JPushService jPushService;
	
	/**
	 * 患者留言
	 * @param request 参数:patientID-患者ID
	 * 					content-留言内容
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/leaveMessage", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map leaveMessage(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class leaveMessage method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("患者给平台留言，参数：" + requestMap.toString());
			}
			Map parameter=new HashMap();
			parameter.put("patientID", requestMap.get("patientID"));
			parameter.put("content", requestMap.get("content"));
			messageService.saveMessage(requestMap);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	} 
	
	/**
	 * 平台回复
	 * @param request 参数:messageID-留言记录ID
	 * 						patientID-患者ID
	 * 						adminID-管理员ID
	 * 						reply-回复
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/replyMessage", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map replyMessage(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class replyMessage method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("给患者回复，参数：" + requestMap.toString());
			}
			Map parameter=new HashMap();
			parameter.put("id", requestMap.get("messageID"));
			parameter.put("adminID", requestMap.get("adminID"));
			parameter.put("reply", requestMap.get("reply"));
			parameter.put("status", "1");
			int count=messageService.updateMessage(parameter);
			if(count==1){
				Map params=new HashMap();
				params.put("aliases", requestMap.get("patientID"));
				params.put("aliasesType", "1");
				params.put("alert", requestMap.get("reply"));
				params.put("sender", requestMap.get("adminID"));
				params.put("senderType", "2");
				jPushService.savePushLogInfo(requestMap);
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	}  
	
	/**
	 * 查看平台答复
	 * @param request 参数patientID-患者ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryReplyMessages", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map queryReplyMessages(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryReplyMessages method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("患者查看平台留言，参数：" + requestMap.toString());
			}
			Map parameter=new HashMap();
			parameter.put("patientID", requestMap.get("patientID"));
			List<Map> replyMessageList=messageService.queryReplyedMessageList(parameter);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("replyMessageList", replyMessageList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	} 
}
