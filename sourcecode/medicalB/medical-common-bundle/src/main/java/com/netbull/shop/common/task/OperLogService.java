package com.netbull.shop.common.task;

import java.util.List;

import com.netbull.shop.common.vo.OperLogVO;

public interface OperLogService {


	/**
	 * @return: int
	 * @author: elvis
	 * @描述 ：向数据库中添加操作日志信息
	 */
	Long addOperLog(OperLogVO operLogVO);
	
	/**
	 * @return: List<OperLogVO>
	 * @author: elvis
	 * @描述 ：查询操作日志；
	 */
	List<OperLogVO> queryOperLogList(OperLogVO operLogVO);

	/**
	 * @return: List<OperLogVO>
	 * @author: elvis
	 * @描述 ：查询操作日志总记录；
	 */
	Integer getOperLogCount(OperLogVO operLogVO);

}
