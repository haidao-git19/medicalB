package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.vo.Patient;
import com.netbull.shop.manage.weixin.vo.EMRecord;

@Repository
public class RecordDao {

	private static final String MYBATIS_PREFIX=RecordDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public Map queryDetail(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryDetail", parameter);
	}
	
	public List<Map> queryList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public void save(EMRecord parameter){
		sqlSession.insert(MYBATIS_PREFIX+".save", parameter);
	}
	
	public Integer updateRecord(Map parameter) {		
		return sqlSession.update(MYBATIS_PREFIX + ".updateRecord",parameter);
	}
	
	public void saveRecordRelation(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".saveRecordRelation", parameter);
	}
	
	public Integer updateAllRecord(EMRecord eMRecord) {		
		return sqlSession.update(MYBATIS_PREFIX + ".updateAllRecord",eMRecord);
	}
	
	public Integer deleteRecord(EMRecord eMRecord) {		
		return sqlSession.update(MYBATIS_PREFIX + ".deleteRecord",eMRecord);
	}
	
	public Long queryRecordRelationCount(Map parameter) {		
		return sqlSession.selectOne(MYBATIS_PREFIX + ".queryRecordRelationCount",parameter);
	}
}
