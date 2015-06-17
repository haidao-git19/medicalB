package com.netbull.web.shop.index.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.netbull.web.shop.index.dao.PosterDao;

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
