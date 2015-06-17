package com.netbull.shop.auth.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.common.cache.Cache;
import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.Constant;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.HttpXmlUtil;
import com.netbull.shop.util.PropertiesUtils;
import com.netbull.shop.auth.service.AuthService;
import com.netbull.shop.auth.service.MenuService;
import com.netbull.shop.auth.service.UserService;
import com.netbull.shop.auth.util.GeneralModelDC;

@Controller
public class HomeController {

	@Resource
	private AuthService authService;
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	@Resource
	private Producer captchaProducer;

	@RequestMapping(value = "/")
	public String home(HttpServletResponse response) {
		String loginCode = queryCurrentShiroUser().getLoginName();
		CookiesUtils.setCookie(response, Constant.USERTRACK_LOGINCODE,loginCode, -1);
		return "auth/index";
	}

	@RequestMapping("/transit")
	public String transit(HttpServletResponse response) {
		return "auth/transit";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletResponse response) {
		return "auth/index";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(){
		return "auth/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletResponse response) {
		ShiroUser su = UserService.getCurrentShiroUser();
		if(su != null) {
			return home(response);
		}
		return "auth/_login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, HttpServletResponse response, String loginName, String password,
			String captchaCode) {
		request.setAttribute("loginName", loginName);

		if (StringUtils.isEmpty(loginName)) {
			request.setAttribute("l_message", "用户名不能为空");
			return "auth/_login";
		}
		if (StringUtils.isEmpty(password)) {
			request.setAttribute("p_message", "密码不能为空");
			return "auth/_login";
		}
		/** 以下注释代码为屏蔽验证码功能 **/
		/*if (StringUtils.isEmpty(captchaCode)) {
			request.setAttribute("message", "验证码不能为空");
			return "auth/login";
		}
		String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (StringUtils.isEmpty(captcha) || !captcha.equalsIgnoreCase(captchaCode)) {
			request.setAttribute("message", "验证码有误,请重试");
			return "auth/login";
		}*/

		String encodePwd = DigestUtils.md5DigestAsHex(password.getBytes());
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, encodePwd, true);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			request.setAttribute("p_message", "用户名或密码错误");
			return "auth/_login";
		}
		if (SecurityUtils.getSubject().isAuthenticated()) {
			try {
				WebUtils.redirectToSavedRequest(request, response, "/");
				return null;
			} catch (IOException e) {
			}
			return "auth/index";
		}
		return "auth/_login";
	}

	@RequestMapping(value = "/genTicket")
	public String genTicket(HttpServletRequest request, HttpServletResponse response, String service) {
		String ticket = UUID.randomUUID().toString();
		Cache<String, ShiroUser> cache = authService.getTicketCache();
		cache.put(ticket, UserService.getCurrentShiroUser());
		try {
			response.sendRedirect(service + "/anon/checkTicket?ticket=" + ticket);
			return null;
		} catch (IOException e) {
		}
		return "auth/index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/home/queryChartData")
	@ResponseBody
	public Map<String,List> queryChartData(HttpServletRequest request, HttpServletResponse response) {
		String trackUrl = PropertiesUtils.getProperty("track.url");
		
		Map<String,List> resultMap = new HashMap<String,List>();
		
		resultMap.put("PV1", invoken(trackUrl+"/anon/queryPV"));
		resultMap.put("PV2", invoken(trackUrl+"/anon/queryPVBefore"));
		resultMap.put("UV1", invoken(trackUrl+"/anon/queryUV"));
		resultMap.put("UV2", invoken(trackUrl+"/anon/queryUVBefore"));
		
		return resultMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/home/queryGeneralModel")
	@ResponseBody
	public Map<String,List> queryGeneralModel(HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = userService.getUserByLoginName(queryCurrentShiroUser().getLoginName());
		if (userInfo == null) {
			return null;
		}
		List<MenuInfo> menuInfos = menuService.getPermissionMenusOnceAll(userInfo.getId());
		String trackUrl = PropertiesUtils.getProperty("track.url");
		Map param = new HashMap();
		param.put("loginCode", queryCurrentShiroUser().getLoginName());
		
		Map<String,List> resultMap = new HashMap<String,List>();
		List<Map> resp = JSON.parseArray(HttpXmlUtil.doPost(trackUrl+"/anon/queryGeneralModel", param), Map.class);
		
		if(NullUtil.isNull(resp)){
			resp = new ArrayList();
		}

		if(resp.size() < 4){
			int size = resp.size();
			List<Map> generalModelList = GeneralModelDC.getInstance().getGeneralModel(queryCurrentShiroUser().getLoginName());
			removalDuplicate(generalModelList,resp);
			for (int i = 0; i < generalModelList.size() - size; i++) {
				Map temp = new HashMap();
				temp.put("title", generalModelList.get(i).get("generalName"));
				temp.put("url", generalModelList.get(i).get("generalUrl"));
				resp.add(temp);
			}
		}
		
		if(!NullUtil.isNull(resp)){
			for(int i=0;i<resp.size();i++){
				Map node = resp.get(i);
				String title = !NullUtil.isNull(node.get("title"))?StringUtil.getString(node.get("title")):null;
				String parentName = findParentNode(menuInfos,title);
				node.put("parentName", parentName);
			}
		}
		resultMap.put("generalModel",resp);
		return resultMap;
	}

	@SuppressWarnings("rawtypes")
	private void removalDuplicate(List<Map> generalModelList,List<Map> resp){
		if(generalModelList == null || resp == null){
			return;
		}
		for (Iterator iterator = resp.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String title = StringUtil.getString(map.get("title"));
			for (Iterator iterator2 = generalModelList.iterator(); iterator2.hasNext();) {
				Map _map = (Map) iterator2.next();
				String _title = StringUtil.getString(map.get("generalName"));
				if(_title.equals(title)){
					generalModelList.remove(_map);
				}
			}
		}
	}
	
	private String findParentNode(List<MenuInfo> menuInfos,String title){
		if(!NullUtil.isNull(menuInfos)){
			for(int i=0;i<menuInfos.size();i++){
				MenuInfo menu = menuInfos.get(i);
				if(menu!=null && title.equals(menu.getName())){
					return findTopParentNode(menu);
				}
			}
		}
		return null;
	}
	
	private String findTopParentNode(MenuInfo menu){
		if(menu != null){
			if(menu.getParent()!=null && !Constant.LEFT_MUNEU_VALUE.equals(menu.getParent().getName())){
				return findTopParentNode(menu.getParent());
			}else{
				return menu.getName();
			}
		}
		return null;
	}
	
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private List invoken(String url){
		List dataList = new ArrayList();
		Map param = new HashMap();
		param.put("loginCode", queryCurrentShiroUser().getLoginName());
		String pv = HttpXmlUtil.doPost(url, param);
		if(StringUtil.isEmpty(pv)){
			JSONArray jsonArray = JSONArray.fromObject(pv);     
			for(int i=0;i<jsonArray.size();i++){   
				JSONObject jsonObj = (JSONObject) JSONObject.parse(jsonArray.getString(i));
				dataList.add(jsonObj.get("count"));  
		      }
		}
		return dataList;
	}
	
	public static ShiroUser queryCurrentShiroUser() {
		try {
			WebSubject ws = (WebSubject) SecurityUtils.getSubject();
			return (ShiroUser) ws.getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/captcha-image")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		String capText = captchaProducer.createText();
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
	
	@RequestMapping(value="/home/queryStatisticsChartData")
	@ResponseBody
	public Map<String,List> queryStatisticsChartData(HttpServletRequest request, HttpServletResponse response) {
		String consoleUrl = PropertiesUtils.getProperty("console.url");
		
		Map<String,List> resultMap = new HashMap<String,List>();
		
		resultMap.put("DRV1", invoken(consoleUrl+"/anon/queryDayRV"));
		resultMap.put("DRV2", invoken(consoleUrl+"/anon/queryDayRVBefore"));
		
		resultMap.put("MRV", invoken(consoleUrl+"/anon/queryMonthRV"));
//		resultMap.put("MRV2", invoken(consoleUrl+"/anon/queryMonthRV"));
		
		return resultMap;
	}
}
