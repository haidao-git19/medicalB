package com.netbull.web.index.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.web.index.entity.HealthKnowlege;

@Repository
public class NewsDao extends BaseDao{

	private static final String MYBATIS_PREFIX=NewsDao.class.getName();
	@Autowired
	private SqlSession session;
	
	/**
	 * 分页查询健康常识
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){ 
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 查询健康常识
	 * @param id
	 * @return
	 */
	public HealthKnowlege queryOne(Integer id){
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("id", id);
		param.put("users", handleQueryOrgan());
		return session.selectOne(MYBATIS_PREFIX+".queryOne", param);
	}
	
	/**
	 * 新建健康常识
	 * @param healthKnowlege
	 */
	public void saveKnowlege(HealthKnowlege healthKnowlege){
		healthKnowlege.setUserID(this.queryCurrentShiroUser().getLoginName());
		session.insert(MYBATIS_PREFIX+".save", healthKnowlege);
	}
	
	/**
	 * 更新健康常识
	 * @param healthKnowlege
	 */
	public void updateKnowlege(HealthKnowlege healthKnowlege){
		session.update(MYBATIS_PREFIX+".update", healthKnowlege);
	}
	
	/**
	 * 分页查询健康常识动态总数
	 * @param paramter
	 * @return
	 */
	public Integer queryDetailCount(Map paramter) {		
		Integer count = session.selectOne(MYBATIS_PREFIX + ".queryDetailCount",paramter);
		return count;
	}
	
	/**
	 * 分页查询健康常识动态列表
	 * @param paramter
	 * @return
	 */
	public List<HealthKnowlege> queryDetailList(Map paramter) {		
		List<HealthKnowlege> list = session.selectList(MYBATIS_PREFIX + ".queryDetailList",paramter);
		return list;
	} 
}
