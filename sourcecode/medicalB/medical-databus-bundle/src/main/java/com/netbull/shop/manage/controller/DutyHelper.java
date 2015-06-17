package com.netbull.shop.manage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DutyHelper {
	
	private static final SimpleDateFormat FREEDATE_PTN = new SimpleDateFormat("yyyy-MM-dd");
	
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
	
	public static int getDayOfWeek() {
		return getDayOfWeek(Calendar.getInstance());
	}
	
	public static int getDayOfWeek(Calendar c) {
		int day = c.get(Calendar.DAY_OF_WEEK);
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
	
	public static String format(Date date) {
		return FREEDATE_PTN.format(date);
	}
}
