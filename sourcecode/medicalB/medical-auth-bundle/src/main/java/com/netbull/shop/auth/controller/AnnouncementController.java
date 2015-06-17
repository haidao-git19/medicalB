package com.netbull.shop.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.auth.entity.AnnouncementInfo;
import com.netbull.shop.auth.service.AnnouncementService;

@Controller
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;
	
	/**
	 * 查询公告
	 * @return
	 */
	@RequestMapping("/announcement/queryAnnouncements")
	@ResponseBody
	public List<AnnouncementInfo> queryAnnouncements(){
		String wechatId=StringUtil.getString(StringTools.queryWechatId());
		List<AnnouncementInfo> list= announcementService.queryAnnouncements(wechatId);
		
		List<AnnouncementInfo> announcements=new ArrayList<AnnouncementInfo>();
		if(list!=null){
			int n=(list.size()<5)?list.size():5;
			for(int i=0;i<n;i++){
				announcements.add(list.get(i));
			}
		}
		return announcements;
	}
}
