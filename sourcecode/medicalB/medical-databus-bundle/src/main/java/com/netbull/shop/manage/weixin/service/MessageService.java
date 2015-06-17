package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.MessageDao;

@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;
	
	public List<Map> queryReplyedMessageList(Map parameter){
		return messageDao.queryReplyedList(parameter);
	}
	
	public Integer saveMessage(Map parameter){
		return messageDao.save(parameter);
	}
	
	public Integer updateMessage(Map parameter){
		return messageDao.update(parameter);
	}
}
