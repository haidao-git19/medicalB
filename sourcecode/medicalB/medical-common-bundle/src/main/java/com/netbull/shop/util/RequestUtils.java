package com.netbull.shop.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.netbull.shop.common.util.NullUtil;
/**
 * 
 * @author kjhe
 *
 */
public class RequestUtils {
    /**
     * 将请求中的参数转化为Map
     * @param request
     * @return
     */
   @SuppressWarnings("unchecked")
   public static Map<String,String> parameterToMap(HttpServletRequest request){
       Map<String , String> params = new HashMap<String, String>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, request.getParameter(paramName));
       }
       return params;
   }
   
   public static Map<String,String> parameterToMapCheck(HttpServletRequest request,String... paramter){
       Map<String , String> params = new HashMap<String, String>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, request.getParameter(paramName));
       }
       if(NullUtil.isNull(paramter)){
    	   return null;
       }
       for (int i = 0; i < paramter.length; i++) {
		if(!params.containsKey(paramter[i])){
			return null;
		}
       }
       return params;
   }
}
