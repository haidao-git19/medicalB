package com.netbull.shop.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.GoodsVo;
import com.netbull.shop.common.vo.ImageVo;
import com.netbull.shop.common.vo.JSONMessage;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.weixin.dao.GoodsDao;
import com.netbull.shop.manage.weixin.dao.ImageDao;
import com.netbull.shop.manage.weixin.service.GoodsService;
import com.netbull.shop.manage.weixin.service.ImageService;

@Controller
public class ImageController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=ImageDao.class.getName();
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ImageService imageService;
	
	/********************************后台管理***************************************/
	
	/**
	 * 商品图片
	 * @param model
	 * @param goodsCode
	 * @return
	 */
	@RequestMapping("/product/goodsImageList")
	public String imageList(Integer goodsId,ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class imageList method.");
		}
		Map<String,String> map=RequestUtils.parameterToMap(request);
		GoodsVo goodsVo=goodsService.get(GoodsDao.class.getName(), goodsId);
		model.addAttribute("goodsCode", goodsVo.getGoodsCode());
		return "product/imageList";
	}
	
	/**
	 * 查询商品图片
	 * @param goodsCode
	 * @return
	 */
	@RequestMapping("/product/queryImageList")
	@ResponseBody
	public List<ImageVo> queryImageList(String goodsCode){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryImageList method.");
		}
		List<ImageVo> list=new ArrayList<ImageVo>();
		if(StringUtil.isEmpty(goodsCode)){
			Map<Object,Object> map=new HashMap<Object,Object>();
			map.put("goodsCode", goodsCode);
			list=imageService.queryImageList(map);
		}
		List<ImageVo> imageList=new ArrayList<ImageVo>();
		if(!NullUtil.isNull(list)){
			for(ImageVo imageVo:list){
				imageVo.setBigImgPath(imageVo.getBigImgPath());
				imageList.add(imageVo);
			}
		}
		return imageList;
	}
	
	/**
	 * 设置上传图片数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/setUploadImageData")
	@ResponseBody
	public String setUploadImageData(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class setUploadImageData method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String goodsCode=requestMap.get("goodsCode");
		HttpSession session = request.getSession(true);
		if(StringUtil.isEmpty(goodsCode)){
			List<ImageVo> imageList=queryImageList(goodsCode);
			if(NullUtil.isNull(imageList)){
				session.setAttribute("sort", 1);
			}else{
				session.setAttribute("sort", imageList.size()+1);
			}
			session.setAttribute("goodsCode", goodsCode);
			session.setAttribute("session_type", "goods");
		}
		
		JSONMessage jsonM = new JSONMessage();
	    jsonM.setFlag(JSONMessage.Flag.SUCCESS);
		jsonM.setMsg("发送消息成功");
		return jsonM.toString();
	}
	
	/**
	 * 商品图片保存
	 * @param imageVo
	 */
	@RequestMapping("/product/imageSave")
	public void imageSave(ImageVo imageVo,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class imageSave method.");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upload");
		imageService.imageSave(MYBATIS_PREFIX, imageVo, file);
	}
	
	/**
	 * 商品图片删除
	 * @param id
	 */
	@RequestMapping("/product/imageDelete")
	@ResponseBody
	public String imageDelete(Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class imageDelete method.");
		}
		imageService.delete(MYBATIS_PREFIX, id);
		JSONMessage jsonM = new JSONMessage();
	    jsonM.setFlag(JSONMessage.Flag.SUCCESS);
		jsonM.setMsg("发送消息成功");
		return jsonM.toString();
	}
}
