package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.manage.weixin.vo.ReservationVo;
@Repository
public class ReservationDao {
	private static final String MYBATIS_PREFIX = ReservationDao.class.getName();

	@Resource
	private SqlSession session;

	public List<Map> queryReservationList(Map paramter) {
		List<Map> list = session.selectList(MYBATIS_PREFIX
				+ ".queryReservationList", paramter);
		return list;
	}

	public Integer saveReservation(ReservationVo reservationVo) {
		return session.insert(MYBATIS_PREFIX + ".saveReservation",
				reservationVo);
	}

	public Map queryReservationDetail(Map<String, String> requestMap) {
		return session.selectOne(MYBATIS_PREFIX + ".queryConsultationDetail",
				requestMap);
	}

	public void modifyReservation(ReservationVo reservationVo) {
		session.update(MYBATIS_PREFIX + ".modifyReservation", reservationVo);
	}

}
