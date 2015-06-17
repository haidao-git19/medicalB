package com.netbull.web.index.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.web.index.entity.Advise;

@Repository("adviseDao")
public class AdviseDao  extends BaseDao{
	private static final String MYBATIS_PREFIX = AdviseDao.class.getName();
	@Resource
	private SqlSession session;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		//设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".pageList", MYBATIS_PREFIX+".count",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public void save(Advise nursing) {
		// TODO Auto-generated method stub
		nursing.setUserID(this.queryCurrentShiroUser().getLoginName());
		session.insert(MYBATIS_PREFIX+".save", nursing);
	}
	
	public void update(Advise nursing) {
		// TODO Auto-generated method stub
		session.insert(MYBATIS_PREFIX+".update", nursing);
	}

	public Advise findById(Advise nursing) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("adviceID", nursing.getAdviceID());
		param.put("objectName", nursing.getObjectName());
		param.put("sectionName", nursing.getAdviceTitle());
		param.put("users", handleQueryOrgan());
		List<Advise> list= session.find(MYBATIS_PREFIX+".findByID", param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return new Advise();
	}

	public int del(int id) {
		// TODO Auto-generated method stub
		return session.delete(MYBATIS_PREFIX+".del",id);
	}

}
