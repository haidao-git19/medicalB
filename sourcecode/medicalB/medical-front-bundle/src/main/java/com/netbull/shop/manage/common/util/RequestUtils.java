package com.netbull.shop.manage.common.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
   public static Map<String,String> parameterToMap(HttpServletRequest request){
       Map<String , String> params = new HashMap<String, String>();
       Enumeration<String> paramNames = request.getParameterNames();
       while (paramNames.hasMoreElements()) {
           String paramName = paramNames.nextElement();
           params.put(paramName, request.getParameter(paramName));
       }
       return params;
   }
}
