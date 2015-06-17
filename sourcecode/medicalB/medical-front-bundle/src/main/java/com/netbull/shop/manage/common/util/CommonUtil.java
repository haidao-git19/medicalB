package com.netbull.shop.manage.common.util;

import java.math.BigDecimal;

public class CommonUtil {

	public static int bitCalc(int i, int j) {
		return i & j;
	}
	
	public static String round(double d, int scale) {
		BigDecimal b = new BigDecimal(d);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();		
	}

}
