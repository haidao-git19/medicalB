package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.DoctorDC;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.service.GroupService;
import com.netbull.shop.manage.weixin.service.JPushService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class GroupController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private GroupService groupService;
	@Autowired
	private JPushService jPushService;
	@Resource
	private DoctorService doctorService;
	
	/**
	 * 添加好友
	 * @param request 参数initiatorID-好友请求发送人ID
	 * 	 					recipientID-好友请求接收人ID
	 * 		                  alert-附带消息
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/addRelation", produces="application/json;charset=utf-8")
	@ResponseBody
	public String addRelation(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class addRelation method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("医生发起添加好友，参数：" + requestMap.toString());
			}
			Map relation=groupService.queryRelationBetw(requestMap);
			if(!NullUtil.isNull(relation)&&StringTools.equals("1", 
					StringUtil.getString(relation.get("state")))){
				resultMap.put("backMessage", 1);//已经是好友了
			}else{
				
				if(!NullUtil.isNull(relation)&&StringTools.equals("-1", 
						StringUtil.getString(relation.get("state")))){
					
					requestMap.put("state", "0");
					groupService.modifyRelation(requestMap);
				
				}else if(NullUtil.isNull(relation)){
					groupService.addRelation(requestMap);
				}
				String aliases = !NullUtil.isNull(requestMap.get("recipientID"))?requestMap.get("recipientID"):null;
				String alert = !NullUtil.isNull(requestMap.get("alert"))?requestMap.get("alert"):"请求添加好友。";
				requestMap.put("aliasesType", "0");
				requestMap.put("senderType", "0");
				requestMap.put("aliases", aliases);
				requestMap.put("sender", !NullUtil.isNull(requestMap.get("initiatorID"))?requestMap.get("initiatorID"):null);
				
				HttpXmlUtil.sendNoticeMsg(aliases, alert);
				jPushService.savePushLogInfo(requestMap);
				
				resultMap.put("backMessage", 0);//好友请求已经发送
			}
			
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 接受好友请求或删除好友
	 * @param request 参数initiatorID-好友请求发送人ID
	 * 	 					recipientID-好友请求接收人ID
	 * 		                  alert-附带消息
	 * 							state-请求状态(1表示接受请求；-1表示拒绝请求或删除)
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/modifyRelation", produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyRelation(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class modifyRelation method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("拒绝添加或删除好友，参数：" + requestMap.toString());
			}
			Map relation=groupService.queryRelationBetw(requestMap);
			
			if(StringTools.equals("-1", requestMap.get("state"))){
				//拒绝好友请求
				if(!NullUtil.isNull(relation)&&
						StringTools.equals("0", StringUtil.getString(relation.get("state")))){
					groupService.modifyRelation(requestMap);
					
					String aliases = !NullUtil.isNull(requestMap.get("initiatorID"))?requestMap.get("initiatorID"):null;
					String alert = !NullUtil.isNull(requestMap.get("alert"))?requestMap.get("alert"):"对方拒绝了您的添加好友请求。";
					requestMap.put("aliasesType", "0");
					requestMap.put("senderType", "0");
					requestMap.put("aliases", aliases);
					requestMap.put("sender", !NullUtil.isNull(requestMap.get("recipientID"))?requestMap.get("recipientID"):null);
					
					HttpXmlUtil.sendNoticeMsg(aliases, alert);
					jPushService.savePushLogInfo(requestMap);
				//删除好友
				}else if(!NullUtil.isNull(relation)&&
						StringTools.equals("1", StringUtil.getString(relation.get("state")))){
					groupService.modifyRelation(requestMap);
				}
			//接受好友请求
			}else if(StringTools.equals("1", requestMap.get("state"))){
				groupService.modifyRelation(requestMap);
				
				String aliases = !NullUtil.isNull(requestMap.get("initiatorID"))?requestMap.get("initiatorID"):null;
				String alert = !NullUtil.isNull(requestMap.get("alert"))?requestMap.get("alert"):"对方接受了您的添加好友请求。";
				requestMap.put("aliasesType", "0");
				requestMap.put("senderType", "0");
				requestMap.put("aliases", aliases);
				requestMap.put("sender", !NullUtil.isNull(requestMap.get("recipientID"))?requestMap.get("recipientID"):null);
				
				HttpXmlUtil.sendNoticeMsg(aliases, alert);
				jPushService.savePushLogInfo(requestMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 医生检索好友
	 * @param request参数operatorID-检索人ID
	 * 					realName-被检索人姓名
	 * 					phone-被检索人手机
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/queryFriends", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryFriends(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map> addendfriendList = new ArrayList<Map>();
		
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryFriends method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("医生查找好友，参数：" + requestMap.toString());
			}
		
			List<Map> allDoctorList = doctorService.queryDoctorAllList(requestMap);
			List<Map> friendList=groupService.queryFriends(requestMap);
			
			if(NullUtil.isNull(allDoctorList)&&NullUtil.isNull(friendList)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			for (Iterator iterator = friendList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if(!NullUtil.isNull(map.get("avatar"))){
					map.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") + map.get("avatar"));
				}
				
				if(NullUtil.isNull(allDoctorList)){
					continue;
				}
				
				for (Iterator iterator2 = allDoctorList.iterator(); iterator2.hasNext();) {
					Map map2 = (Map) iterator2.next();
					if(map2.get("doctorID").equals(map.get("doctorID"))){
						addendfriendList.add(map2);
					}else{
						map2.put("state", "2");
					}
				}
			}
			
			allDoctorList.removeAll(addendfriendList);
			
			resultMap.put("friendList", friendList);
			resultMap.put("noFriendList", allDoctorList);
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
