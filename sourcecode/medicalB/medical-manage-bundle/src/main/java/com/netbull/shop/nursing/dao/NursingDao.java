package com.netbull.shop.nursing.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.nursing.entity.Nursing;

@Repository("nursingDao")
public class NursingDao  extends BaseDao{
	private static final String MYBATIS_PREFIX = NursingDao.class.getName();
	@Resource
	private SqlSession session;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		//设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".pageList", MYBATIS_PREFIX+".count",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public void save(Nursing nursing) {
		// TODO Auto-generated method stub
		nursing.setUserID(this.queryCurrentShiroUser().getLoginName());
		session.insert(MYBATIS_PREFIX+".save", nursing);
	}
	
	public void update(Nursing nursing) {
		// TODO Auto-generated method stub
		session.insert(MYBATIS_PREFIX+".update", nursing);
	}

	public Nursing findById(Nursing nursing) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("adviceID", nursing.getAdviceID());
		param.put("objectName", nursing.getObjectName());
		param.put("sectionName", nursing.getAdviceTitle());
		param.put("users", handleQueryOrgan());
		List<Nursing> list= session.find(MYBATIS_PREFIX+".findByID", param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return new Nursing();
	}

	public int del(int id) {
		// TODO Auto-generated method stub
		return session.delete(MYBATIS_PREFIX+".del",id);
	}

}
