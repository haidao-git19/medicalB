package com.netbull.shop.manage.controller;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Map> {

	public int compare(Map m1, Map m2) {
		Integer s1 = (Integer)m1.get("sort");
		Integer s2 = (Integer)m2.get("sort");
		
		return s1 == s2 ? 0 : (s1 > s2 ? 1 : -1);
	}
	
}