
package com.netbull.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.HttpXmlUtil;

public class BaseDao {

	public static ShiroUser queryCurrentShiroUser() {
		try {
			String url = ConfigLoadUtil.loadConfig().getPropertie("getShiroUser");
			url = url +"?loginName="+StringTools.queryCurrentShiroUser().getLoginName(); 
			String resp = HttpXmlUtil.doPost(url, null);
			if(NullUtil.isNull(resp)){
				return null;
			}
			return JSONObject.parseObject(resp, ShiroUser.class);
		} catch (Exception e) {
		return null;
		}
		}

	public static List<String> handleQueryOrgan() {
		List<String> list=new ArrayList<String>();
		ShiroUser shiroUser=queryCurrentShiroUser();
		String organs=shiroUser.getChildNodes();
		if(!StringUtil.isEmpty(organs)&&!" null".equals(organs)&&organs!=null){
			String[] os=organs.split(",");
			for (int i = 0; i < os.length; i++) {
				list.add(os[i]);
			}
			
		}
		//加当前机构
		list.add(shiroUser.getLoginName());
		return list;
	}
	
	
	
}
