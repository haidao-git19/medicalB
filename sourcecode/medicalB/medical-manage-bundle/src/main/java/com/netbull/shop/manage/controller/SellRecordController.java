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

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.ConvertUtils;
import com.netbull.shop.common.vo.GoodsAttr;
import com.netbull.shop.common.vo.GoodsVo;
import com.netbull.shop.common.vo.SellRecordVo;
import com.netbull.shop.common.vo.UserAcceptInfoVo;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.SellRecordDao;
import com.netbull.shop.manage.weixin.service.GoodsAttrService;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.manage.weixin.service.SellRecordService;
import com.netbull.shop.manage.weixin.service.UserAcceptInfoService;
import com.netbull.shop.util.DataTableUtils;

@Controller
public class SellRecordController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=SellRecordDao.class.getName();

	@Autowired
	private SellRecordService sellRecordService;
	@Autowired
	private UserAcceptInfoService userAcceptInfoService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsAttrService goodsAttrService;
	
	/********************************后台管理***************************************/
	
	/**
	 * 订单管理页面
	 * @return
	 */
	@RequestMapping("/order/sellRecordList")
	public String sellRecordList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class querySellRecordList method.");
		}
		return "order/sellRecordList";
	}
	
	/**
	 * 查询订单记录
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/order/querySellRecordList")
	@ResponseBody
	public  Map<String, Object> querySellRecordList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class querySellRecordList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = sellRecordService.querySellRecordsPage(iDisplayStart, iDisplayLength, requestMap);	//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","acceptName","phone","telephone","orderCode","goodsCode","totalFee","sellTime","ispay","status","userId");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 订单详情
	 * @param model
	 * @param id
	 * @param userId
	 * @return
	 */
	@RequestMapping("/order/sellRecordDetail")
	public String sellRecordDetail(ModelMap model,Integer id,String userId){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class sellRecordDetail method.");
		}
		String realPath = ConfigLoadUtil.loadConfig().getPropertie("accessAddress");
		
		/*订单信息*/
		SellRecordVo sellRecordVo=sellRecordService.get(MYBATIS_PREFIX, id);
		model.addAttribute("sellRecordVo", sellRecordVo);
		/*收货人信息*/
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("userId", userId);
		map.put("enable", "0");
		UserAcceptInfoVo userAcceptInfoVo=userAcceptInfoService.queryUserAcceptInfo(map);
		model.addAttribute("userAcceptInfoVo", userAcceptInfoVo);
		/*商品信息*/
		map=new HashMap<Object,Object>();
		map.put("goodsCode", sellRecordVo.getGoodsCode());
		GoodsVo goodsVo=goodsService.queryGoodsVoByParams(map);
		goodsVo.setImgPath(realPath+goodsVo.getImgPath());
		model.addAttribute("goodsVo", goodsVo);
		/*商品属性信息*/
		List<GoodsAttr> goodsAttrs=goodsAttrService.queryGoodsAttrList(map);
		model.addAttribute("goodsAttrs", goodsAttrs);
		return "order/sellRecordDetail";
	}
	
	/********************************微信***************************************/
	/**
	 * 保存订单
	 * @param sellRecordVo
	 */
	@RequestMapping("/anon/sellRecordSave")
	public void sellRecordSave(SellRecordVo sellRecordVo){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class sellRecordSave method.");
		}
		sellRecordService.save(MYBATIS_PREFIX, sellRecordVo);
	}
}
