package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.vo.UserAcceptInfoVo;
import com.netbull.shop.manage.weixin.dao.AcceptInfoDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AcceptInfoService {
	private static final Log log = LogFactory.getLog(AcceptInfoService.class);
    
	@Resource
	private AcceptInfoDao acceptInfoDao;

	/**
	 * 查询收货信息列表
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryAcceptInfoList(Map paramter) {		
		return acceptInfoDao.queryAcceptInfoList(paramter);
	}
	
	public UserAcceptInfoVo queryAcceptInfo(Map paramter) {	
		return acceptInfoDao.queryAcceptInfo(paramter);
	}
	/**
	 * 修改收货信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyAcceptInfo(UserAcceptInfoVo paramter) {		
		return acceptInfoDao.modifyAcceptInfo(paramter);
	}
	
	/**
	 * 添加收货信息
	 * @param paramter
	 * @return
	 */
	public Integer saveAcceptInfo(UserAcceptInfoVo paramter) {		
		return acceptInfoDao.saveAcceptInfo(paramter);
	}

	/**
	 * 删除收货信息
	 * @param paramter
	 * @return
	 */
	public Integer deleteAcceptInfoById(Integer paramter){
		return acceptInfoDao.deleteAcceptInfoById(paramter);
	}
	
}
