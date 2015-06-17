package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.vo.QuestionVo;
import com.netbull.shop.manage.weixin.dao.QuestionDao;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;
	
	public List<Map> queryQuestionList(Map parameter){
		return questionDao.queryList(parameter);
	}
	
	public Map queryQuestion(Map parameter){
		return questionDao.queryOne(parameter);
	}
	
	public Integer saveQuestion(QuestionVo questionVo){
		return questionDao.save(questionVo);
	}
	
	public Integer updateQuestion(Map parameter){
		return questionDao.update(parameter);
	}
	
	public Integer saveReply(Map parameter){
		return questionDao.saveReply(parameter);
	}
	
	public List<Map> queryReplyList(Map parameter){
		return questionDao.queryReplyList(parameter);
	}
}
