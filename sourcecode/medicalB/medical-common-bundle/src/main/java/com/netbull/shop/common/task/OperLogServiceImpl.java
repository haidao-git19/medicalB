package com.netbull.shop.common.task;


import java.util.List;

import org.apache.log4j.Logger;

import com.netbull.shop.common.dao.EntityIbaDao;
import com.netbull.shop.common.dao.EntityManagerImpl;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.vo.OperLogVO;


/**
 * 日志操作服务类；
 * @author elvis
 */
public class OperLogServiceImpl extends EntityManagerImpl<OperLogVO, Integer> implements OperLogService {
	
	private static Logger logger = LoggerFactory.getLogger(OperLogServiceImpl.class);
	
	public OperLogServiceImpl() {
	}
	
	public OperLogServiceImpl(EntityIbaDao<OperLogVO, Integer> entityDao) {
		super(entityDao); 
	}
	
	/**
	 * @return: int
	 * @author: elvis
	 * @描述 ：向数据库中添加操作日志信息
	 */
	public Long addOperLog(OperLogVO operLogVO) {
		return entityDao.saveObj("visitLog.addOperLog", operLogVO);
	}

	/**
	 * @return: List<OperLogVO>
	 * @author: elvis
	 * @描述 ：查询操作日志；
	 */
	@SuppressWarnings("unchecked")
	public List<OperLogVO> queryOperLogList(OperLogVO operLogVO) {
		List<OperLogVO> operLogs = entityDao.find("visitLog.queryOperLogList", operLogVO);
		return operLogs;
	}

	public Integer getOperLogCount(OperLogVO operLogVO) {
		Object obj= entityDao.findObject("visitLog.getOperLogCount", operLogVO);
		return obj!=null?(Integer)obj:0 ;
	}

}