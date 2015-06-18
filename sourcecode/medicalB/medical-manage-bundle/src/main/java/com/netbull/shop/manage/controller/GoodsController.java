package com.netbull.shop.manage.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.category.service.CategoryService;
import com.netbull.shop.common.vo.ContentVo;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.ConvertUtils;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.GoodsAttr;
import com.netbull.shop.common.vo.GoodsClob;
import com.netbull.shop.common.vo.GoodsReport;
import com.netbull.shop.common.vo.GoodsVo;
import com.netbull.shop.common.vo.ImageVo;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.GoodsAttrDao;
import com.netbull.shop.manage.weixin.dao.GoodsDao;
import com.netbull.shop.manage.weixin.dao.ImageDao;
import com.netbull.shop.manage.weixin.service.ContentService;
import com.netbull.shop.manage.weixin.service.GoodsAttrService;
import com.netbull.shop.manage.weixin.service.GoodsReportService;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.manage.weixin.service.ImageService;
import com.netbull.shop.navicate.service.NavicateService;
import com.netbull.shop.pharmacy.entity.Pharmacy;
import com.netbull.shop.pharmacy.service.PharmacyService;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.DataTableUtils;

@Controller
public class GoodsController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=GoodsDao.class.getName();
	private static String[] columnsArray={"sellingPoints","packageList","buyNote","content"};
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private NavicateService navicateService;
	/********************************后台管理***************************************/
	
	/**
	 * 商品管理
	 * @return
	 */
	@RequestMapping("/product/goodsList")
	public String goodsList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsList method.");
		}
		return "product/goodsList";
	}
	
	/**
	 * 分页查询商品信息
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/product/queryGoodsList")
	@ResponseBody
	public  Map<String, Object> queryGoodsList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryGoodsList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		
		Page page = goodsService.queryGoodsPage(iDisplayStart, iDisplayLength, requestMap);	//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","categoryCode","goodsCode","shortName","status","createPerson","createTime","updatePerson","updateTime","");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 商品信息编辑
	 * @param model
	 * @param id
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/product/goodsEdit")
	public String goodsEdit(ModelMap model,Integer id,HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsEdit method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		GoodsVo goodsVo=new GoodsVo();
		Map<String,String> clobsMap=new HashMap<String,String>();
		if(id!=null&&id!=0){
			goodsVo=goodsService.get(MYBATIS_PREFIX, id);
			if(!NullUtil.isNull(goodsVo)){
				requestMap.put("goodsCode", goodsVo.getGoodsCode());
				
				for(String column:columnsArray){
					String columnVal=StringUtil.getString(PropertyUtils.getProperty(goodsVo, column));
					Integer columnId=Integer.parseInt(StringUtil.isEmpty(columnVal)?columnVal:"0");
							
					if(columnId!=null&&columnId>0){
						GoodsClob goodsClob=goodsService.queryGoodsClob(columnId);
						if(!NullUtil.isNull(goodsClob)){
							clobsMap.put("_"+column, goodsClob.getText());
						}
					}
				}
				
				Integer navicatId=goodsVo.getNavicatId();
				if(navicatId!=null&&navicatId>0){
					Map nav1=navicateService.queryNavicateById(navicatId);
					if(!NullUtil.isNull(nav1)){
						Map nav2=navicateService.queryNavicateById(Integer.parseInt(StringUtil.getString(nav1.get("pid"))));
						if(!NullUtil.isNull(nav2)){
							Map nav3=navicateService.queryNavicateById(Integer.parseInt(StringUtil.getString(nav2.get("pid"))));
							if(!NullUtil.isNull(nav3)){
								model.addAttribute("firstLevelNavId", nav3.get("id"));
								model.addAttribute("secondLevelNavId", nav2.get("id"));
								model.addAttribute("thirdLevelNavId", nav1.get("id"));
							}else{
								model.addAttribute("firstLevelNavId", nav2.get("id"));
								model.addAttribute("secondLevelNavId", nav1.get("id"));
							}
						}else{
							model.addAttribute("firstLevelNavId", nav1.get("id"));
						}
					}
				}
				
			}
		}
		model.addAttribute("goodsVo", goodsVo);
		model.addAttribute("clobs", clobsMap);
		//查询所有类目
		List<Map> categoryList= categoryService.queryAllCategorys(null);
		model.addAttribute("categoryList", categoryList);
		
//		//查询药店信息
//		List<Pharmacy>  pharmacyList =pharmacyService.queryPharmacy(new Pharmacy());
//		request.setAttribute("pharmacyList", pharmacyList);
		return "product/goodsEdit";
	}
	
	/**
	 * 商品信息保存
	 * @param id
	 * @param goodsVo
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/product/goodsSave")
	@ResponseBody
	public void goodsSave(Integer id,Integer contentId,GoodsVo goodsVo,HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsSave method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		ShiroUser currUser = BaseDao.queryCurrentShiroUser();
		
		Integer catId=null;
		String goodsCode=goodsVo.getGoodsCode();
		
		if(!NullUtil.isNull(requestMap.get("thirdLevelNavcatId"))){
			catId=Integer.parseInt(StringUtil.getString(requestMap.get("thirdLevelNavcatId")));
			goodsVo.setNavicatId(Integer.parseInt(StringUtil.getString(requestMap.get("thirdLevelNavcatId"))));
		}else if(!NullUtil.isNull(requestMap.get("secondLevelNavcatId"))){
			catId=Integer.parseInt(StringUtil.getString(requestMap.get("secondLevelNavcatId")));
			goodsVo.setNavicatId(Integer.parseInt(StringUtil.getString(requestMap.get("secondLevelNavcatId"))));
		}else if(!NullUtil.isNull(requestMap.get("firstLevelNavcatId"))){
			catId=Integer.parseInt(StringUtil.getString(requestMap.get("firstLevelNavcatId")));
			goodsVo.setNavicatId(Integer.parseInt(StringUtil.getString(requestMap.get("firstLevelNavcatId"))));
		}
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upload");
		
		if(id!=null&&id!=0){
			for(String column:columnsArray){
				String columnVal=StringUtil.getString(PropertyUtils.getProperty(goodsVo, column));
				Integer columnId=Integer.parseInt(StringUtil.isEmpty(columnVal)?columnVal:"0");
				GoodsClob goodsClob=null;
				if(columnId!=null&&columnId>0){
					goodsClob=goodsService.queryGoodsClob(columnId);
					if(!NullUtil.isNull(goodsClob)){
						goodsClob.setTitle(column);
						goodsClob.setText(requestMap.get("_"+column));
						goodsService.updateGoodsClob(goodsClob);
					}
				}else{
					goodsClob=new GoodsClob();
					goodsClob.setTitle(column);
					goodsClob.setText(requestMap.get("_"+column));
					int flag=goodsService.saveGoodsClob(goodsClob);
					if(flag==1){
						PropertyUtils.setProperty(goodsVo, column, goodsClob.getId());
					}
				}
			}
			
			goodsService.goodsUpdate(MYBATIS_PREFIX, goodsVo, file);
			requestMap.put("goodsCode", goodsVo.getGoodsCode());
		}else{
			for(String column:columnsArray){
				int flag=0;
				GoodsClob goodsClob=new GoodsClob();
				goodsClob.setTitle(column);
				goodsClob.setText(requestMap.get("_"+column));
				flag=goodsService.saveGoodsClob(goodsClob);
				if(flag==1){
					PropertyUtils.setProperty(goodsVo, column, goodsClob.getId());
				}
			}
			
			goodsService.goodsSave(MYBATIS_PREFIX, goodsVo, file);
		}
		
		List<Integer> conditionOptList=goodsVo.getConditionOptList();
		Map parameter=new HashMap();
		parameter.put("goodsCode", goodsCode);
		parameter.put("creator", currUser.getId());
		if(!NullUtil.isNull(conditionOptList)){
			navicateService.deleteScMapping(parameter);
			
			for(Integer catScOptId:conditionOptList){
				Map opt=navicateService.queryOptById(catScOptId);
				parameter.put("catId", catId);
				parameter.put("scId", opt.get("scId"));
				parameter.put("catScOptId", catScOptId);
				navicateService.saveScMapping(parameter);
			}
		}
	}
	
	/**
	 * 商品信息删除
	 * @param id
	 */
	@RequestMapping("/product/goodsDelete")
	@ResponseBody
	public void goodsDelete(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class goodsDelete method.");
		}
		/*删除商品*/
		goodsService.delete(MYBATIS_PREFIX, id);
	}
	
	/**
	 * 查询所有商品信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/queryAllGoods")
	@ResponseBody
	public List<GoodsVo> queryAll(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryAll method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<GoodsVo> goodsList=goodsService.queryAll(requestMap);
		return goodsList;
	}
}
