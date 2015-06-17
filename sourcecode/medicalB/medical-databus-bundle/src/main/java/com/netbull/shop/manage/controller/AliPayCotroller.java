package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.OrderVo;
import com.netbull.shop.manage.common.alipay.AlipayConfig;
import com.netbull.shop.manage.common.alipay.AlipayNotify;
import com.netbull.shop.manage.common.alipay.AlipaySubmit;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.service.AlipayTransService;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.manage.weixin.service.OrderInfoService;
import com.netbull.shop.manage.weixin.service.UserBaseInfoService;

@Controller("AliPayCotroller")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AliPayCotroller {
	private static final Logger logger = Logger.getLogger("alipayLog");
	
	String req_id;
	//支付宝网关地址
	String ALIPAY_GATEWAY_NEW = ConfigLoadUtil.loadConfig().getPropertie("alipay_gatway_new");
	//服务器异步通知页面路径
	String notify_url = ConfigLoadUtil.loadConfig().getPropertie("alipay_notify_url");
	//页面跳转同步通知页面路径
	String call_back_url = ConfigLoadUtil.loadConfig().getPropertie("alipay_call_back_url");
	//操作中断返回地址
	String merchant_url = ConfigLoadUtil.loadConfig().getPropertie("alipay_merchant_url");
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private AlipayTransService alipayTransService;
	@Resource
	private OrderInfoService orderInfoService;
	@Resource
	private GoodsService goodsService;
	/**
	 * 方法简要描述: 跳转到支付宝支付页面；
	 * 修改说明：
	 */
	@RequestMapping(value = "/anon/toaliPay" , produces="text/plain;charset=utf-8")
	public String topay(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "alipay";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/anon/alipayapi" , produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryWXPayPrepayid(HttpServletRequest request,HttpServletResponse response) {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			req_id = StringTools.getBillnoFor27();
			//卖家支付宝帐户
			String seller_email = ConfigLoadUtil.loadConfig().getPropertie("WIDseller_email");
			//商户编号
			String goodsCode = requestMap.get("goodsCode");
			//商户订单号
			String out_trade_no = requestMap.get("orderCode");
			//商户网站订单系统中唯一订单号，必填
			String subject = requestMap.get("goodsName");
			//付款金额
			String total_fee = requestMap.get("totalFee");
			//请求业务参数详细
			String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + seller_email + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
			
			if(logger.isDebugEnabled()){
				logger.info("卖家支付宝帐户,seller_email="+seller_email);
				logger.info("付款金额,total_fee="+total_fee);
				logger.info("请求业务参数详细,req_dataToken="+req_dataToken);
			}
			
			//把请求参数打包成数组
			Map<String, String> sParaTempToken = new HashMap<String, String>();
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", AlipayConfig.partner);
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.sign_type);
			sParaTempToken.put("format", Constants.ALIPAY_FORMAT);
			sParaTempToken.put("v", Constants.ALIPAY_VERSION);
			sParaTempToken.put("req_id", req_id);
			sParaTempToken.put("req_data", req_dataToken);
			
			if(logger.isDebugEnabled()){
				logger.info("支付宝授权接口请求参数:respMap="+sParaTempToken.toString());
			}
			
			/***保存授权请求数据到数据库**/
			//this.alipayAuthService.saveAliPayAuthReq(sParaTempToken,seller_email,out_trade_no,subject,total_fee);
			/***调用支付宝授权接口**/
			String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);//建立请求
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
			Map<String, String> respMap = AlipaySubmit.getRequestToken(sHtmlTextToken);	//获取token
			if(logger.isDebugEnabled()){
				logger.info("支付宝授权接口html同步返回参数:respMap="+respMap.toString());
			}
			/***保存授权响应数据到数据库**/
			//this.alipayAuthService.saveAliPayAuthResp(respMap);
			////////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////
			String request_token = respMap.get("request_token");
			if(NullUtil.isNull(request_token)){
				logger.info("调用支付宝授权接口返回token为空:request_token="+request_token);
			}
			//业务详细
			String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.sign_type);
			sParaTemp.put("format", Constants.ALIPAY_FORMAT);
			sParaTemp.put("v", Constants.ALIPAY_VERSION);
			sParaTemp.put("req_data", req_data);
			
			/***保存交易请求数据到数据库**/
			//this.alipayTransService.saveAliPayTransReq(sParaTemp,request_token,out_trade_no);
			//建立请求
			String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
			if(logger.isDebugEnabled()){
				logger.info("调用支付宝交易接口请求参数，组装的请求html表单对象:sHtmlText="+sHtmlText);
			}
			this.sendMessages(response, sHtmlText);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
		}
		this.sendMessages(response, Constants.ALIPAY_FAIL);
		return null;
	}
	
	
	/**
	 * 方法简要描述: 用户微信付款完成后回调  
	 * 修改说明：
	 */
	@RequestMapping(value = "/anon/alipayCallback" , produces="text/plain;charset=utf-8")
	public String alipayCallback(HttpServletRequest request,HttpServletResponse response){
		try {
			//获取支付宝GET过来反馈信息
			Map<String,String> requestMap = RequestUtils.parameterToMap(request);
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			if(logger.isDebugEnabled()){
				logger.info("支付宝交易接口页面跳转同步通知参数返回值,requestMap="+requestMap.toString());
			}
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyReturn(params);
			if(verify_result){//验证成功
				return "payOk";
			}else{
				return "payFail";
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
		}
		return "payFail";
	}
	
	/**
	 * 方法简要描述: 用户微信付款完成后回调  
	 * 修改说明：
	 */
	@RequestMapping(value = "/anon/alipayNotice" , produces="text/plain;charset=utf-8")
	public void alipayNotice(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			if(logger.isDebugEnabled()){
				logger.info("进入支付宝交易接口服务器异步通知处理入口...");
			}
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			
			if(logger.isDebugEnabled()){
				logger.info("支付宝交易接口服务器异步通知参数返回值,params="+params.toString());
			}
			
			//RSA签名解密
		   	if(AlipayConfig.sign_type.equals(Constants.ALIPAY_SIGN_TYPE)) {
		   		params = AlipayNotify.decrypt(params);
		   	}
			
		   	Map reslutMap = AlipaySubmit.getNotifyData(params);
		   	if(logger.isDebugEnabled()){
				logger.info("支付宝交易接口服务器异步通知参数返回值,reslutMap="+reslutMap.toString());
			}
			if(AlipayNotify.verifyNotify(params)){//验证成功
				Integer count = this.alipayTransService.modifyAliTransPayResp(reslutMap);
				if(NullUtil.isNull(count) || count <= 0){
					this.alipayTransService.saveAliPayTransResp(reslutMap);
			        OrderVo order = orderInfoService.queryOrderDetail(reslutMap);
			        if(NullUtil.isNull(order)){
			        	logger.info("支付宝交易完成接口服务器异步通知，根据订单号【"+reslutMap.get("out_trade_no")+"】查询订单记录为空，不进行下一步操作。");
						this.sendMessages(response, Constants.ALIPAY_FAIL);
						return;
			        }
			        String orderCode = order.getOrderCode();
			        String userId = order.getUserId();
			        String goodsCode = order.getGoodsCode();
			        String productNum = StringUtil.getString(order.getProductNum());
			        
			        if(logger.isDebugEnabled()){
						logger.info("支付宝交易完成接口服务器异步通知参数返回值,orderCode="+orderCode);
						logger.info("支付宝交易完成接口服务器异步通知参数返回值,userId="+userId);
					}
					/*******支付成功，赠送特权；*******/
					this.presentPrivilege(userId,orderCode,productNum);
					/*******支付成功，修改订单状态；；*******/
					this.modifyOrder(orderCode);
					/**修改商品库存数量**/
					reslutMap.put("offset", productNum);
					reslutMap.put("goodsCode", goodsCode);
					goodsService.modifyGoodReport(reslutMap);
			     }
				this.sendMessages(response, Constants.ALIPAY_SUCCESS);
				return;
			}else{//验证失败
				this.sendMessages(response, Constants.ALIPAY_FAIL);
				return;
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			e.printStackTrace();
		}
		this.sendMessages(response, Constants.ALIPAY_FAIL);
		return;
	}
	
	
	/**
	 * 方法简要描述: 用户微信付款完成后回调  
	 * 修改说明：
	 */
	@RequestMapping(value = "/anon/alipayNoticeForAndroid" , produces="text/plain;charset=utf-8")
	public void alipayNoticeForAndroid(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			if(logger.isDebugEnabled()){
				logger.info("进入支付宝交易接口服务器异步通知android处理入口...");
			}
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			if(logger.isDebugEnabled()){
				logger.info("支付宝交易接口服务器异步通知参数返回值,params="+params.toString());
			}
			if(com.netbull.shop.manage.common.alipay.android.alipay.util.AlipayNotify.verify(params)){
				//验证成功
				params.put("partner", StringUtil.getString(params.get("seller_id")));
				Integer count = this.alipayTransService.modifyAliTransPayResp(params);
				if(NullUtil.isNull(count) || count <= 0){
					this.alipayTransService.saveAliPayTransResp(params);
			        OrderVo order = orderInfoService.queryOrderDetail(params);
			        if(NullUtil.isNull(order)){
			        	logger.info("支付宝交易完成接口服务器异步通知，根据订单号【"+params.get("out_trade_no")+"】查询订单记录为空，不进行下一步操作。");
						this.sendMessages(response, Constants.ALIPAY_FAIL);
						return;
			        }
			        String orderCode = order.getOrderCode();
			        String userId = order.getUserId();
			        String goodsCode = order.getGoodsCode();
			        String productNum = StringUtil.getString(order.getProductNum());
			        
			        if(logger.isDebugEnabled()){
						logger.info("支付宝交易完成接口服务器异步通知参数返回值,orderCode="+orderCode);
						logger.info("支付宝交易完成接口服务器异步通知参数返回值,userId="+userId);
					}
					/*******支付成功，赠送特权；*******/
					this.presentPrivilege(userId,orderCode,productNum);
					/*******支付成功，修改订单状态；；*******/
					this.modifyOrder(orderCode);
					/**修改商品库存数量**/
					params.put("offset", productNum);
					params.put("goodsCode", goodsCode);
					goodsService.modifyGoodReport(params);
			     }
				this.sendMessages(response, Constants.ALIPAY_SUCCESS);
				return;
			}else{//验证失败
				this.sendMessages(response, Constants.ALIPAY_FAIL);
				return;
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			e.printStackTrace();
		}
		this.sendMessages(response, Constants.ALIPAY_FAIL);
		return;
	}
	
	@RequestMapping("/anon/merchant")
	public String merchant(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class merchant method.");
		}
		return "payFail";
	}
	
	private void presentPrivilege(String userId,String orderCode,String privilegeNum)throws Exception{
		if(NullUtil.isNull(userId)){
			return ;
		}
		Map requestParams = new HashMap();
		requestParams.put("userId", userId);
		requestParams.put("vip", "1");
		requestParams.put("businessCode", orderCode);
		requestParams.put("offset", privilegeNum);
		
	}
	
	private void modifyOrder(String orderCode){
		if(NullUtil.isNull(orderCode)){
			return ;
		}
		Map requestParams = new HashMap();
		requestParams.put("ispay", "0");
		requestParams.put("payType", "1");
		requestParams.put("orderCode", orderCode);
		
		this.orderInfoService.modifyOrderInfo(requestParams);
	}
	
	public String sendMessages(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().print(json);
		} catch (IOException e) {
			logger.error("操作失败，原因："+e.getMessage());
		}
        return null;
    }
}
