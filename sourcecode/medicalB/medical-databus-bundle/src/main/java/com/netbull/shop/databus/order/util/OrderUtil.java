package com.netbull.shop.databus.order.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtil {
	
	private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random RND = new Random();
	
	public static String buildOrderNumber() {
		StringBuilder buf = new StringBuilder(20);
		buf.append(DATE_FMT.format(new Date()));
		for(int i=0; i<6; i++) {
			buf.append(RND.nextInt(10));
		}
		return buf.toString();
	}
	
}
