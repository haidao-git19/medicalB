package com.netbull.shop.common.util;

import com.netbull.shop.common.log.JLogger;
import com.netbull.shop.common.log.LoggerFactory;



//统计记录日志的工具类
public class StatisticUtil {
	
	private static final String PERFORM_SPE = "___";
	
	private static JLogger performLogger = LoggerFactory
	.getLogger("biz.wss.perform");
	
	/**
	 * 对系统的关键流程进行性能统计分析
	 */
	public static void performLog(String sessionid,String serviceType,int postion){
		if(sessionid==null ||serviceType==null ){
			return;
		}
		long time = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		//SessionID
		sb.append(sessionid);
		sb.append(PERFORM_SPE);
		//业务类型
		sb.append(serviceType);
		sb.append(PERFORM_SPE);
		//位置
		sb.append(postion);
		sb.append(PERFORM_SPE);
		//时间
		sb.append(time);
		performLogger.info(sb.toString());
	}
	

	

}
