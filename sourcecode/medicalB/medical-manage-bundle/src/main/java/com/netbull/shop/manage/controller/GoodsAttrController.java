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

import com.netbull.shop.category.entity.CategoryAttr;
import com.netbull.shop.category.service.CategoryAttrService;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.ConvertUtils;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.vo.GoodsAttr;
import com.netbull.shop.common.vo.GoodsVo;
import com.netbull.shop.common.vo.JSONMessage;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.GoodsAttrDao;
import com.netbull.shop.manage.weixin.dao.GoodsDao;
import com.netbull.shop.manage.weixin.service.GoodsAttrService;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.util.DataTableUtils;

@Controller
public class GoodsAttrController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=GoodsAttrDao.class.getName();
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsAttrService goodsAttrService;
	@Autowired
	private CategoryAttrService categoryAttrService;
	
	/********************************后台管理***************************************/
	
	/**
	 * 商品属性管理
	 * @param model
	 * @param goodsCode
	 * @return
	 */
	@RequestMapping("/product/goodsAttrList")
	public String goodsAttrList(Integer goodsId,ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsAttrList method.");
		}
		Map<String,String> map=RequestUtils.parameterToMap(request);
		GoodsVo goodsVo=goodsService.get(GoodsDao.class.getName(), goodsId);
		map.put("categoryCode", goodsVo.getCategoryCode());
		map.put("goodsCode", goodsVo.getGoodsCode());
		List<CategoryAttr> categoryAttrList=categoryAttrService.queryAllCategoryAttrs(map);
		model.addAttribute("categoryAttrList", categoryAttrList);
		model.addAttribute("goodsCode", map.get("goodsCode"));
		model.addAttribute("categoryCode", map.get("categoryCode"));
		return "product/goodsAttrList";
	}
	
	/**
	 * 分页查询商品属性信息
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/product/queryGoodsAttrList")
	@ResponseBody
	public Map<String, Object> queryGoodsAttrList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryGoodsAttrList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = goodsAttrService.queryGoodsAttrPage(iDisplayStart, iDisplayLength, requestMap);	//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","goodsCode","attrCode","attrName","attrValue","createTime","updateTime","");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 商品属性编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/product/goodsAttrEdit")
	@ResponseBody
	public Map<String,Object> goodsAttrEdit(ModelMap model,Integer id,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsAttrEdit method.");
		}
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);;
		Map<String,Object> resultMap=new HashMap<String, Object>();
		GoodsAttr goodsAttr=null;
		List<CategoryAttr> categoryAttrList=categoryAttrService.queryAllCategoryAttrs(requestMap);
		if(id!=null&&id!=0){
			goodsAttr=goodsAttrService.get(MYBATIS_PREFIX, id);
		}
		resultMap.put("categoryAttrList", categoryAttrList);
		resultMap.put("goodsAttr", goodsAttr);
		return resultMap;
	}
	
	/**
	 * 商品属性保存
	 * @param id
	 * @param goodsAttr
	 */
	@RequestMapping("/product/goodsAttrSave")
	@ResponseBody
	public void goodsAttrSave(Integer id,GoodsAttr goodsAttr){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsAttrSave method.");
		}
		if(id!=null&&id!=0){
			goodsAttrService.update(MYBATIS_PREFIX, goodsAttr);
		}else{
			goodsAttrService.save(MYBATIS_PREFIX, goodsAttr);
		}
	}
	
	/**
	 * 商品属性删除
	 * @param id
	 */
	@RequestMapping("/product/goodsAttrDelete")
	@ResponseBody
	public String goodsAttrDelete(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsAttrDelete method.");
		}
		goodsAttrService.delete(MYBATIS_PREFIX, id);
		JSONMessage jsonM = new JSONMessage();
	    jsonM.setFlag(JSONMessage.Flag.SUCCESS);
		jsonM.setMsg("发送消息成功");
		return jsonM.toString();
	}
}
