package com.netbull.shop.manage.common.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 验证码生成工具
 * @author Administrator
 *
 */
public class ValiCodeUtil {

	/**
	 * 生成验证码
	 * @return
	 */
	public static String createValiCode(){
		char[] chars=new char[]{'0','1','2','3','4','5','6','7','8','9'};
		String valiCode=RandomStringUtils.random(4, chars).trim();
		return valiCode;
	}
	
//	public static void main(String[] args) {
//		System.out.println(ValiCodeUtil.createValiCode());
//	}
}
