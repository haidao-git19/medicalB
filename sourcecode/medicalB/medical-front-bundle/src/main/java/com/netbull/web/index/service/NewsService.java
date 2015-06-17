package com.netbull.web.index.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.util.MyBatisDao;
import com.netbull.shop.util.UploadFileUtil;
import com.netbull.web.index.dao.NewsDao;
import com.netbull.web.index.entity.HealthKnowlege;

@Service
public class NewsService extends MyBatisDao<HealthKnowlege, Integer>{

	@Autowired
	private NewsDao newsDao;
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	/**
	 * 分页查询健康常识
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryHealthKnowlegePage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return newsDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 查询健康常识
	 * @param id
	 * @return
	 */
	public HealthKnowlege queryHealthKnowlege(Integer id){
		return newsDao.queryOne(id);
	}
	
	/**
	 * 新建健康常识
	 * @param healthKnowlege
	 * @param file
	 */
	public void saveHealthKnowlege(HealthKnowlege healthKnowlege,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		healthKnowlege.setCoverImage(fileName);
		newsDao.saveKnowlege(healthKnowlege);
	}
	
	/**
	 * 更新健康常识
	 * @param healthKnowlege
	 * @param file
	 */
	public void updateHealthKnowlege(HealthKnowlege healthKnowlege,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		healthKnowlege.setCoverImage(fileName);
		newsDao.updateKnowlege(healthKnowlege);
	}
	
	/**
	 * 分页查询健康常识动态总数
	 * @param paramter
	 * @return
	 */
	public Integer queryDetailCount(Map paramter) {
		return newsDao.queryDetailCount(paramter);
	}
	
	/**
	 * 分页查询健康常识动态列表
	 * @param paramter
	 * @return
	 */
	public List<HealthKnowlege> queryDetailList(Map paramter) {		
		return newsDao.queryDetailList(paramter);
	}
}
