package com.netbull.shop.databus.questionnaire.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.PatientBind;
import com.netbull.shop.databus.questionnaire.model.Result;

/**
 * @author Ade
 */
@Repository
public class PatientBindDao {
	private static final Logger logger = Logger.getLogger("daoLog");

	private static final String NAMESPACE = PatientBind.class.getName();

	@Resource
	private SqlSession session;

	public int save(PatientBind bind) {
		return session.insert(NAMESPACE + ".save", bind);
	}

	public int deputeDoctor(Result rslt) {
		return session.update(NAMESPACE + ".setDeputeDoctor", rslt);
	}

	public PatientBind queryCurrentMapping(Result rslt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qnId", rslt.getQnId());
		map.put("doctorId", rslt.getDoctorId());
		map.put("patientId", rslt.getPatientId());
		return (PatientBind) session.get(NAMESPACE + ".queryCurrentMapping",
				map);
	}

	@SuppressWarnings("rawtypes")
	public List<Map> queryDeputeQuestionnaire(Map parameter) {
		return session.selectList(NAMESPACE + ".queryDeputeQuestionnaire",
				parameter);
	}

	public int queryDeputeQuestionnaireTotal(Map<String, String> requestMap) {
		return (Integer) session.get(NAMESPACE + ".queryDeputeQuestionnaireTotal",
				requestMap);
	}


}
