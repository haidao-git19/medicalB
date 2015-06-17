package com.netbull.shop.manage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.ConvertUtils;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.vo.UserAcceptInfoVo;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.UserAcceptInfoDao;
import com.netbull.shop.manage.weixin.service.UserAcceptInfoService;
import com.netbull.shop.util.DataTableUtils;

@Controller
public class UserAcceptInfoController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=UserAcceptInfoDao.class.getName();
	
	@Autowired
	private UserAcceptInfoService userAcceptInfoService;
	
	/********************************后台管理***************************************/
	/**
	 * 收货人信息管理
	 * @return
	 */
	@RequestMapping("/user/userAcceptInfoList")
	public String userAcceptInfoList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userAcceptInfoList method.");
		}
		return "user/userAcceptInfoList";
	}
	
	/**
	 * 分页查询收货人信息
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/user/queryUserAcceptInfoList")
	@ResponseBody
	public  Map<String, Object> queryUserAcceptInfoList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryUserAcceptInfoList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = userAcceptInfoService.queryUserAcceptInfoPage(iDisplayStart, iDisplayLength, requestMap);	//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","userId","acceptName","phone","province","city","enable","status","createTime","updateTime","");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 收货人详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/userAcceptInfoDetail")
	public String userAcceptInfoDetail(ModelMap model,Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userAcceptInfoDetail method.");
		}
		UserAcceptInfoVo userAcceptInfoVo=new UserAcceptInfoVo();
		if(id!=null&&id!=0){
			userAcceptInfoVo=userAcceptInfoService.get(MYBATIS_PREFIX, id);
		}
		model.addAttribute("userAcceptInfoVo", userAcceptInfoVo);
		return "user/userAcceptInfoDetail";
	}
	
	
	/**
	 * 查询用户所有收货信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("/anon/queryUserAcceptInfoList")
	@ResponseBody
	public List<UserAcceptInfoVo> queryUserAcceptInfoList(Integer userId){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryUserAcceptInfoList method.");
		}
		List<UserAcceptInfoVo> list=new ArrayList<UserAcceptInfoVo>();
		if(userId!=null&&userId!=0){
			list=userAcceptInfoService.queryUserAcceptInfoListByUserId(userId);
		}
		return list;
	}
	
	/**
	 * 查询用户收货信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/anon/queryUserAcceptInfo")
	@ResponseBody
	public UserAcceptInfoVo queryUserAcceptInfo(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryUserAcceptInfo method.");
		}
		UserAcceptInfoVo userAcceptInfoVo=new UserAcceptInfoVo();
		if(id!=null&&id!=0){
			Map<Object,Object> map=new HashMap<Object,Object>();
			map.put("id", id);
			userAcceptInfoVo=userAcceptInfoService.queryUserAcceptInfo(map);
		}
		return userAcceptInfoVo;
	}
	
	/**
	 * 保存用户收货信息
	 * @param userAcceptInfoVo
	 */
	@RequestMapping("/anon/userAcceptInfoSave")
	public void userAcceptInfoSave(Integer id,UserAcceptInfoVo userAcceptInfoVo){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userAcceptInfoSave method.");
		}
		if(id!=null&&id!=0){
			userAcceptInfoService.update(MYBATIS_PREFIX, userAcceptInfoVo);
		}else{
			userAcceptInfoService.save(MYBATIS_PREFIX, userAcceptInfoVo);
		}
	}
	
	/**
	 * 删除用户收货信息
	 * @param id
	 */
	@RequestMapping("/anon/userAcceptInfoDelete")
	public void userAcceptInfoDelete(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userAcceptInfoDelete method.");
		}
		userAcceptInfoService.delete(MYBATIS_PREFIX, id);
	}
	
	/**
	 * 启用用户某个收货地址
	 * @param userId
	 * @param id
	 */
	@RequestMapping("/anon/userAcceptInfoEnabledSet")
	public void userAcceptInfoEnabledSet(Integer userId,Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userAcceptInfoEnabledSet method.");
		}
		List<UserAcceptInfoVo> list=userAcceptInfoService.queryUserAcceptInfoListByUserId(userId);
		if(!NullUtil.isNull(list)){
			for(UserAcceptInfoVo userAcceptInfoVo:list){
				userAcceptInfoVo.setEnable("-1");
			}
		}
		UserAcceptInfoVo userAcceptInfoVo=userAcceptInfoService.get(MYBATIS_PREFIX, id);
		userAcceptInfoVo.setEnable("0");
		userAcceptInfoService.update(MYBATIS_PREFIX, userAcceptInfoVo);
	}
}
