package com.netbull.shop.doctor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.netbull.shop.doctor.entity.Duty;

public class DutyHelper {
	
	private static final SimpleDateFormat FREEDATE_PTN = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String dutyLink(Map<String, Duty> dutys, long doctorId, int weekFlag, int weekNum, int dayFlag) {
		Duty duty = dutys.get(doctorId + "_" + weekFlag + "_" + weekNum + "_" + dayFlag);
		if(duty != null) {
			return "<a href='javascript:setting(" + weekFlag + ", " + weekNum + ", " + dayFlag + ", " + duty.getId() + ");'>修改</a> | "
			     + "<a href='javascript:cancel(" + weekFlag + ", " + weekNum + ", " + dayFlag + ", " + duty.getId() + ");'>取消</a>";
		}else{
			return "<a href='javascript:setting(" + weekFlag + ", " + weekNum + ", " + dayFlag + ", 0);'>新增</a>";
		}
	}
	
	public static Date calcFreeDate(int weekFlag, int weekNum) {
		Calendar c = Calendar.getInstance();
		int dayOfWeek = getDayOfWeek();
		
		if(weekFlag == 0) {
			c.add(Calendar.DAY_OF_YEAR, weekNum - dayOfWeek);
		}else if(weekFlag == 1) {
			c.add(Calendar.DAY_OF_YEAR, 7 - dayOfWeek + weekNum);
		}
		
		try {
			return FREEDATE_PTN.parse(FREEDATE_PTN.format(c.getTime()));
		} catch (ParseException e) {
			return null;
		}
	}
	
	private static int getDayOfWeek() {
		int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		switch(day) {
		case Calendar.MONDAY:
			return 1;
		case Calendar.TUESDAY:
			return 2;
		case Calendar.WEDNESDAY:
			return 3;
		case Calendar.THURSDAY:
			return 4;
		case Calendar.FRIDAY:
			return 5;
		case Calendar.SATURDAY:
			return 6;
		case Calendar.SUNDAY:
			return 7;
		default :
			return -1;
		}
	}
}
