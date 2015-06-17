package com.netbull.shop.manage.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.Base64Coder;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.util.JsonUtils;

/**
 * 图片上传工具
 * @author Administrator
 *
 */
public class UploadPicUtil {
	
	public static Map<String,String> uploadPic(Map<String,String> requestMap,CommonsMultipartFile file){
		Map<String,String> paramsMap=new HashMap<String,String>();
		paramsMap.put("userId", requestMap.get("userId"));
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			baos.write(file.getBytes());
			baos.flush();
			byte[] bytes=baos.toByteArray();
			paramsMap.put("picStr", StringUtil.getString(Base64Coder.encodeLines(bytes)));
			baos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String uploadUrl = ConfigLoadUtil.loadConfig().getPropertie("uploadUrl");
		String json=HttpXmlUtil.doPost(uploadUrl, paramsMap);
		
		Map<String,Object> resultMap=JsonUtils.json2Map(json);
		String code=(String) resultMap.get("code");
		String userId=(String) resultMap.get("userId");
		
		Map<String,String> map=new HashMap<String,String>();
		if(StringTools.equals(code, "0")){
			map.put("code", "1");
			map.put("userId", userId);
			map.put("info", "上传头像成功!");
		}else if(StringUtil.isEmpty(code)&&code!="0"){
			map.put("code", "2");
			map.put("userId", userId);
			map.put("info", "更新头像成功!");
		}else{
			map.put("code", "0");
			map.put("info", "上传头像失败!");
		}
		return map;
	}
}
