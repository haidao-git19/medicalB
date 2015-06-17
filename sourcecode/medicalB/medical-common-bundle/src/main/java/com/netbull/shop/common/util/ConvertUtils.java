package com.netbull.shop.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author luye
 *
 */
public class ConvertUtils {
	
	public static <T> List<List<?>> convertClazz(List<T> list,String... fields){
		List<List<?>> aaData = new ArrayList<List<?>>();
		for(T t:list){
			List<Object> list1 = new ArrayList<Object>();			
				for (String objField : fields){
					if("".equals(objField)){
					list1.add("");
					}
					else{
					list1.add(invokeGetterMethod(t, objField));
					}
				}
			aaData.add(list1);
		}
		return aaData;
	}
	/**
	 * 调用Getter方法.
	 */
	private static Object invokeGetterMethod(Object obj, String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		return invokeMethod(obj, getterMethodName, new Class[] {}, new Object[] {});
	}
	
	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 * 用于一次性调用的情况.
	 */
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}
	
	/**
	 * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 
	 * 用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 */
	private static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {

		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Method method = superClass.getDeclaredMethod(methodName, parameterTypes);

				method.setAccessible(true);

				return method;

			} catch (NoSuchMethodException e) {//NOSONAR
				// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}
	
	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	private static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Reflection Exception.", e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException("Reflection Exception.", ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String,String> toMap(List entityList,String valueAttr,String textAttr){
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<entityList.size();i++){
			Object obj = entityList.get(i);
			map.put(invokeGetterMethod(obj, valueAttr).toString(), invokeGetterMethod(obj, textAttr).toString());
		}
		return map;
	}
	
	public static String createRandomNumber(String param){
		int len=8;
		if(param.matches("\\d{1,}")){
			len=Integer.parseInt(param);
		}
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<len;i++){
			sb.append((char)(Math.random()*10+'0'));
		}
		return sb.toString();
	}
}
