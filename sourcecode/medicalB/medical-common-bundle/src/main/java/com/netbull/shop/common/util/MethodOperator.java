package com.netbull.shop.common.util;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 增加annotation，为每一个方法提供注解，
 * 记录作者等相关信息；
 * @author elvis
 *
 */
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface MethodOperator {
	/**
	 * 操作名称
	 * @return
	 */
	String opName();
	
	/**
	 * 关键属性名称
	 * @return
	 */
	String[] keyProperties() default {};
}

