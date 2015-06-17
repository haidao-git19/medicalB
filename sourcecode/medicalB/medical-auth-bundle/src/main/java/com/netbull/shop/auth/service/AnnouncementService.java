package com.netbull.shop.auth.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.dao.HDao;
import com.netbull.shop.auth.entity.AnnouncementInfo;

@Service("announcementService")
public class AnnouncementService {

	@Resource
	private HDao authDao;
	
	/**
	 * 查询公告
	 * @param map
	 * @return
	 */
	public List<AnnouncementInfo> queryAnnouncements(String wechatId){
		String hql="from AnnouncementInfo where wechatId=? order by createTime desc";
		return authDao.find(hql, wechatId);
	}
}
