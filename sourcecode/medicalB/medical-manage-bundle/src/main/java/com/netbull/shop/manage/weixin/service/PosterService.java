package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.PosterVo;
import com.netbull.shop.manage.weixin.dao.PosterDao;
import com.netbull.shop.util.MyBatisDao;
import com.netbull.shop.util.UploadFileUtil;

@Service
public class PosterService extends MyBatisDao<PosterVo, Integer>{
	
	@Autowired
	private PosterDao posterDao;
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	/**
	 * 分页查询海报信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPosterPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return posterDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 查询海报信息
	 * @param map
	 * @return
	 */
	public PosterVo queryPoster(Integer id){
		return posterDao.queryOne(id);
	}
	
	/**
	 * 保存海报信息
	 * @param mybatisPrefix
	 * @param posterVo
	 * @param file
	 */
	public void savePoster(String mybatisPrefix,PosterVo posterVo,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		posterVo.setImage(fileName);
		posterDao.save(posterVo);
	}
	
	/**
	 * 更新海报信息
	 * @param mybatisPrefix
	 * @param posterVo
	 * @param file
	 */
	public void updatePoster(String mybatisPrefix,PosterVo posterVo,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		posterVo.setImage(fileName);
		posterDao.update(posterVo);
	}
}
