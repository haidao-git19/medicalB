package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.ImageCompressUtil;
import com.netbull.shop.common.vo.ImageVo;
import com.netbull.shop.manage.weixin.dao.ImageDao;
import com.netbull.shop.util.MyBatisDao;
import com.netbull.shop.util.UploadFileUtil;

@Service
public class ImageService extends MyBatisDao<ImageVo, Integer>{

	private static Integer width=Integer.parseInt(ConfigLoadUtil.loadConfig().getPropertie("imageWidth"));
	private static Integer height=Integer.parseInt(ConfigLoadUtil.loadConfig().getPropertie("imageHeight"));
	
	@Autowired
	private ImageDao imageDao;
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	/**
	 * 查询商品展示图片
	 * @param map
	 * @return
	 */
	public List<ImageVo> queryImageList(Map<Object,Object> map){
		return imageDao.queryList(map);
	}
	
	/**
	 * 保存图片
	 * @param MYBATIS_PREFIX
	 * @param imageVo
	 * @param file
	 */
	public void imageSave(String MYBATIS_PREFIX,ImageVo imageVo,CommonsMultipartFile file){
		
		String fileName=uploadFileUtil.createFile(file, null);
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		imageVo.setBigImgPath(realPath+fileName);
		
		String uploadPath=ConfigLoadUtil.loadConfig().getPropertie("materialUploadPath");
		ImageCompressUtil.getInstance().compressPic(uploadPath, uploadPath, fileName, "S_"+fileName, width, height, false);
		imageVo.setSmallImgPath(realPath+"S_"+fileName);
		save(MYBATIS_PREFIX, imageVo);
	}
}
