package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.cache.CacheService;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.task.Const;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.common.util.DesEncrypt;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.util.TokenDC;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.service.UserService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.SpringContextUtil;

@Controller
public class BuyShellController {
	private CacheService memCacheService = (CacheService) SpringContextUtil.getBean("memCacheService");
	private static final Logger logger = Logger.getLogger("controlerLog");
	 private static String WEIXIN_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/anon/buyList")
	public String buyList(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class buyList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryGoodsFilter"); 
		/*String resp = (String) memCacheService.getCache("COM.NETBULL.SHOP", "GOODS_FILTERS");
		if(NullUtil.isNull(resp)){
			resp = HttpXmlUtil.doPost(url, requestMap);
			memCacheService.putCache("COM.NETBULL.SHOP", "GOODS_FILTERS", resp);
		}*/
		String resp = HttpXmlUtil.doPost(url, requestMap);
		Map<String,Object> resultMap=JsonUtils.json2Map(resp);
		String code=(String) resultMap.get("code");
		List<Map<String,Object>> filterList=(List<Map<String,Object>>) resultMap.get("filter");
		if(StringTools.equals(code, "0")&&!NullUtil.isNull(filterList)){
			model.addAttribute("filterList", filterList);
		}
		
		String posterUrl = ConfigLoadUtil.loadConfig().getPropertie("wxPosterListUrl");
		String posterResp = HttpXmlUtil.doPost(posterUrl, requestMap);
		Map<String,Object> posterResultMap=JsonUtils.json2Map(posterResp);
		String posterCode=(String) posterResultMap.get("code");
		List<Map> posterList=null;
		if(StringTools.equals(posterCode, "0")){
			posterList=(List<Map>) posterResultMap.get("posterList");
			model.addAttribute("posterList", posterList);
		}
		
		
		model.addAttribute("param", requestMap);
		return "buyshell/buyList";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/anon/queryPosterList")
	@ResponseBody
	public List<Map> queryPosterList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryPosterList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String posterUrl = ConfigLoadUtil.loadConfig().getPropertie("wxPosterListUrl");
		String posterResp = HttpXmlUtil.doPost(posterUrl, requestMap);
		Map<String,Object> posterResultMap=JsonUtils.json2Map(posterResp);
		String posterCode=(String) posterResultMap.get("code");
		List<Map> posterList=null;
		if(StringTools.equals(posterCode, "0")){
			posterList=(List<Map>) posterResultMap.get("posterList");
		}
		return posterList;
	}
	
	@RequestMapping("/anon/buyShellList")
	@ResponseBody
	public  String buyShellList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class buyShellList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryGoodsListUrl"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	@RequestMapping("/anon/toBuyDetail")
	public String toBuyDetail(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toBuyDetail method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		
		Map paramterMap = TokenDC.getInstance().getWechatVo(Const.NETBULL_LOGIN_CODE);
		String appid = StringUtil.getString(paramterMap.get("appId"));
		String jsapi_ticket =  StringUtil.getString(paramterMap.get("jsapi_ticket"));
		String queryStr = request.getQueryString();
		String currentUrl = null;
		if(!NullUtil.isNull(queryStr)){
			currentUrl = request.getRequestURL()+"?"+request.getQueryString();
		}else{
			currentUrl = request.getRequestURL().toString();
		}
		
		Map<String, String> ret = StringTools.sign(jsapi_ticket, currentUrl);
		model.addAttribute("appId",appid);
		model.addAttribute("timestamp",ret.get("timestamp"));
		model.addAttribute("nonceStr",ret.get("nonceStr"));
		model.addAttribute("signature",ret.get("signature"));
		
		return "buyshell/buyDetail";
	}
	
	@RequestMapping("/anon/buyDetail")
	@ResponseBody
	public String buyDetail(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class buyDetail method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryGoodsDetailUrl"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	@RequestMapping("/anon/queryCommentList")
	@ResponseBody
	public String queryCommentList(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryCommentList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryCommentList"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	@RequestMapping("/anon/toOrderPage")
	public String toOrderPage(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toOrderPage method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String type = StringUtil.getString(requestMap.get("type"));
		//如果是从DIY页面跳转过来进行支付的，直接到订单支付页面；
		if("3".equals(type)){
			return "buyshell/orderComfirm";
		}
		Integer goodsStock = !NullUtil.isNull(requestMap.get("goodsStock"))?Integer.parseInt(requestMap.get("goodsStock")):0;
		model.addAttribute("param", requestMap);
		
		/*Map paramterMap = TokenDC.getInstance().getWechatVo(Const.NETBULL_LOGIN_CODE);
		String appid = StringUtil.getString(paramterMap.get("appId"));
		String secret = StringUtil.getString(paramterMap.get("appSecret"));
		String json="";
		String code = request.getParameter("code");
		String bodyContent="appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		logger.info("调用微信公众平台Auth2.0接口获取OpenId请求参数："+bodyContent);
		try {
			json=HttpClient.httpSendMsg(WEIXIN_OPENID_URL,bodyContent);
			logger.info("获取json信息："+json);
		} catch (IOException e) {
			logger.error("微信平台报错",e);
		}
		if(NullUtil.isNull(json)){
			logger.info("是否执行了这个地方"+json);
		}
		JSONObject accessTokenVo = JSONObject.parseObject(json);
		String accessToken = accessTokenVo.getString("access_token");
		
		String queryStr = request.getQueryString();
		String currentUrl = null;
		if(!NullUtil.isNull(queryStr)){
			currentUrl = request.getRequestURL()+"?"+request.getQueryString()+"&code="+code+"&state=123";
		}else{
			currentUrl = request.getRequestURL().toString()+"?code="+code+"&state=123";
		}
		
		logger.info("accessToken="+accessToken);
		logger.info("currentUrl="+currentUrl);
		
		Map<String, String> ret = StringTools.signForEditAddress(accessToken,appid, currentUrl);
		model.addAttribute("appId",appid);
		model.addAttribute("timeStamp",ret.get("timestamp"));
		model.addAttribute("nonceStr",ret.get("nonceStr"));
		model.addAttribute("addrSign",ret.get("signature"));*/
		
		if(goodsStock > 0){
			return "buyshell/orderComfirm";
		}else{
			return "subscribe/subscribe";
		}
	}
	
	@RequestMapping("/anon/submitOrder")
	@ResponseBody
	public String submitOrder(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class submitOrder method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Cookie  cookie = CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return JsonUtils.obj2Json("noLogin");
		}
		/***
		 * 开始价格签名校验
		 */
		boolean result = StringTools.verifiProductPrice(requestMap);
		if(!result){
			return JsonUtils.obj2Json("dataSignFail");
		}
		
		String shopPrice = StringUtil.getString(requestMap.get("shopPrice"));
		String productNum = StringUtil.getString(requestMap.get("productNum"));
		requestMap.put("totalFee", Float.parseFloat(shopPrice)*Integer.parseInt(productNum)+"");
		requestMap.put("userId", DesEncrypt.getDecryptString(userId));
		String url = ConfigLoadUtil.loadConfig().getPropertie("saveOrModifyOrder"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	@RequestMapping("/anon/toalipay")
	public String toalipay(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toalipay method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "buyshell/toalipay";
	}
	
	@RequestMapping("/anon/saveSubscribe")
	@ResponseBody
	public String saveSubscribe(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class saveSubscribe method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Cookie  cookie = CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return JsonUtils.obj2Json("noLogin");
		}
		requestMap.put("userId", DesEncrypt.getDecryptString(userId));
		String url = ConfigLoadUtil.loadConfig().getPropertie("saveOrModifySubscribe"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	@RequestMapping("/anon/toSuccess")
	public String toSuccess(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toSuccess method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "subscribe/subscribeOk";
	}
	
	@RequestMapping("/anon/toChangeSuccess")
	public String toChangeSuccess(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toChangeSuccess method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "oldchange/changeOk";
	}
	
	@RequestMapping("/anon/toPaySuccess")
	public String toPaySuccess(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toPaySuccess method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "buyshell/payOk";
	}
	
	@RequestMapping("/anon/toPayFail")
	public String toPayFail(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toPayFail method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "buyshell/payFail";
	}
	
	@RequestMapping("/anon/toCommentsPage")
	public String toCommentsPage(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toCommentsPage method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "buyshell/comments";
	}
	
	@RequestMapping("/anon/addComments")
	@ResponseBody
	public String addComments(HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class addComments method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Cookie cookie=CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return JsonUtils.obj2Json("noLogin");
		}
		requestMap.put("userId", DesEncrypt.getDecryptString(cookie.getValue()));
		String url = ConfigLoadUtil.loadConfig().getPropertie("addCommentsUrl"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	@RequestMapping("/anon/clickPraise")
	@ResponseBody
	public Map<String,Object> clickPraise(HttpServletRequest request,HttpServletResponse response){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class clickPraise method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String userId=requestMap.get("userId");
		String goodsCode=requestMap.get("goodsCode");
		String praiseNumStr=requestMap.get("praiseNumStr");
		
		Integer praiseNum=0;
		if(StringUtil.isEmpty(praiseNumStr)){
			praiseNum=Integer.parseInt(praiseNumStr);
		}
		String url = ConfigLoadUtil.loadConfig().getPropertie("addPraiseUrl"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		Map<String,Object> resultMap=JsonUtils.json2Map(resp);
		String code=(String) resultMap.get("code");
		if(StringTools.equals(code, "0")){
			praiseNum=praiseNum+1;
			Cookie cookie=CookiesUtils.getCookie(request, userId+"_praiseRecord_"+goodsCode);
			if(NullUtil.isNull(cookie)){
				CookiesUtils.setCookie(response, userId+"_praiseRecord_"+goodsCode, "1", 3600*24);
			}
			resultMap.put("praiseNum", praiseNum);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/anon/toMoreDetail",produces="text/plain;charset=utf-8")
	public String toMoreDetail(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toMoreDetail method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "buyshell/toMoreDetail";
	}
	
	@RequestMapping(value="/anon/getScreenTips",produces="text/plain;charset=utf-8")
	public String getScreenTips(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class getScreenTips method.");
		}
		return "buyshell/screenTips";
	}
	
	@RequestMapping(value="/anon/queryGoodsFilter",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryGoodsFilter(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryGoodsFilter method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryGoodsFilter"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
}
