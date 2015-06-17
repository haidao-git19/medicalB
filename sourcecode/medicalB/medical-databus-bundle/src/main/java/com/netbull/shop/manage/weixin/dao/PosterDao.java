package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.netbull.shop.common.dao.SqlSession;

@Repository
public class PosterDao {
	private static final String MYBATIS_PREFIX = PosterDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 查询海报列表
	 * @param paramter
	 * @return
	 */
	public List<Map> myPoster(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".myPosterList",paramter);
		return list;
	}
	
}
