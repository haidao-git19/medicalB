package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.ReservationDao;
import com.netbull.shop.manage.weixin.vo.ReservationVo;

@Service
public class ReservationService {
	@Resource
	private ReservationDao reservationDao;

	public List<Map> queryReservationList(Map paramter) {
		return reservationDao.queryReservationList(paramter);
	}

	public Map queryReservationDetail(Map<String, String> requestMap) {
		return reservationDao.queryReservationDetail(requestMap);
	}

	public Integer saveReservation(ReservationVo reservationVo) {
		return reservationDao.saveReservation(reservationVo);

	}

	public void modifyReservation(ReservationVo reservationVo) {
		reservationDao.modifyReservation(reservationVo);
	}

}
