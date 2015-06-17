package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.PosterDao;
import com.netbull.shop.util.UploadFileUtil;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PosterService {
	private static final Log log = LogFactory.getLog(PosterService.class);
	@Resource
	private PosterDao posterDao;

	/**
	 * 查询列表
	 * @param paramter
	 * @return
	 */
	public List<Map> myPoster(Map paramter) {		
		return posterDao.myPoster(paramter);
	}
	
}
