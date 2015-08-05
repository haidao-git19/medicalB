package com.netbull.shop.manage.weixin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.util.DesEncrypt;
import com.netbull.shop.manage.weixin.vo.Account;
import com.netbull.shop.manage.weixin.vo.AccountLog;

@Repository
public class AccountDao {
	private static final String MYBATIS_PREFIX = AccountDao.class.getName();

	@Resource
	private SqlSession session;

	public Account queryAccountByPatient(Long patientId) {
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("patientId", patientId);
		return (Account) session.get(MYBATIS_PREFIX + ".queryAccountByPatient",
				param);
	}

	public Integer createAccount(Long patientId) {
		// 新用户开账户信息
		Account acc = new Account();
		acc.setPatientId(patientId);
		acc.setAvaildbalance(0L);
		acc.setBalance(0L);
		String accounttext = DesEncrypt.getEncryptString(Account.ACC_SALT_KEY
				+ "-" + 0 + "-" + 0);
		acc.setAccounttext(accounttext);
		return session.insert(MYBATIS_PREFIX + ".saveAccount", acc);
	}

	public Integer updateAccount(Account acc) {

		return session.update(MYBATIS_PREFIX + ".updateAccount", acc);
	}

	public Integer saveAccountLog(AccountLog acclog) {

		return session.insert(MYBATIS_PREFIX + ".saveAccountLog", acclog);
	}

	public int queryAccLogListTotalCount(Map<String, String> requestMap) {
		return (Integer) session.get(MYBATIS_PREFIX
				+ ".queryAccLogListTotalCount", requestMap);
	}

	public List<AccountLog> queryAccLogList(Map<String, String> requestMap) {
		return session.selectList(MYBATIS_PREFIX + ".queryAccLogList",
				requestMap);
	}

	public AccountLog queryAccLogReCharge(String orderNumber) {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("orderNumber", orderNumber);
		return (AccountLog) session.get(
				MYBATIS_PREFIX + ".queryAccLogReCharge", requestMap);
	}

	public Integer updateAccountLogState(AccountLog accLog) {
		return session
				.update(MYBATIS_PREFIX + ".updateAccountLogState", accLog);

	}

	public Integer updateAccountLogPay(AccountLog accLog) {
		return session.update(MYBATIS_PREFIX + ".updateAccountLogPay", accLog);

	}

	public AccountLog queryAccLogByOrderNumber(String orderNumber) {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("orderNumber", orderNumber);
		return (AccountLog) session.get(MYBATIS_PREFIX
				+ ".queryAccLogByOrderNumber", requestMap);
	}

	public Account queryAccountByAccId(Long accId) {
		Map<String, Long> requestMap = new HashMap<String, Long>();
		requestMap.put("accId", accId);
		return (Account) session.get(MYBATIS_PREFIX
				+ ".queryAccountByAccId", requestMap);
	}

}
