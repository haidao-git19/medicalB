package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.vo.UserAcceptInfoVo;

@Repository
public class AcceptInfoDao {
	private static final String MYBATIS_PREFIX = AcceptInfoDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 查询收货信息列表
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryAcceptInfoList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryAcceptInfoList",paramter);
		return list;
	}
	
	public UserAcceptInfoVo queryAcceptInfo(Map paramter) {		
		UserAcceptInfoVo obj = session.selectOne(MYBATIS_PREFIX + ".queryAcceptInfo",paramter);
		return obj;
	}
	
	/**
	 * 修改收货信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyAcceptInfo(UserAcceptInfoVo paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyAcceptInfo",paramter);
	}
	
	/**
	 * 添加收货信息
	 * @param paramter
	 * @return
	 */
	public Integer saveAcceptInfo(UserAcceptInfoVo paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveAcceptInfo",paramter);
	}
	
	/**
	 * 删除收货信息
	 * @param paramter
	 * @return
	 */
	public Integer deleteAcceptInfoById(Integer paramter) {		
		return session.update(MYBATIS_PREFIX + ".deleteAcceptInfoById",paramter);
	}
}
