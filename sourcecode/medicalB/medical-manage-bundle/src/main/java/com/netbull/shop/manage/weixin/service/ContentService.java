package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.ContentVo;
import com.netbull.shop.manage.weixin.dao.ContentDao;
import com.netbull.shop.util.MyBatisDao;

@Service
public class ContentService extends MyBatisDao<ContentVo, Integer>{

	@Autowired
	private ContentDao contentDao;
	
	/**
	 * 分页查询内容记录
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryContentPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return contentDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 查询所有可用内容记录
	 * @return
	 */
	public List<ContentVo> queryAllContents(){
		return contentDao.queryAll();
	}
	
	/**
	 * 查询内容记录
	 * @param id
	 * @return
	 */
	public ContentVo queryContent(Integer id){
		return contentDao.queryOne(id);
	}
}
