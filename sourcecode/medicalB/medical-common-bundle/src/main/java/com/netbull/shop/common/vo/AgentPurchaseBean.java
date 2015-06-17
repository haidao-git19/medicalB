package com.netbull.shop.common.vo;

import java.util.ArrayList;
import java.util.List;

public class AgentPurchaseBean {

	List<AgentPurchaseVo> purchases=new ArrayList<AgentPurchaseVo>();

	public List<AgentPurchaseVo> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<AgentPurchaseVo> purchases) {
		this.purchases = purchases;
	}
	
	
}
