package com.netbull.shop.common.task;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.Util;



public class LoggingStoreDBTask  {
	
	private static final Logger log = Logger.getLogger("microLog");
	
	/**本地日志文件路径 */
	private String basePath;
	
	/**是否入库*/
	private boolean storeFlag;
	
	/**
	 * 执行入库任务
	 */
	public void run() {
		
		log.info("into the run of vodFileStoreDBTask  class.");
		if(!storeFlag){
			return ;
		}
		if(!NullUtil.isNull(basePath)){
			
			log.info("into the "+basePath+" stream vod folder.");
			File[] msgFiles = new File(basePath).listFiles(new FileFilter() {
				//@Override
				public boolean accept(File pathname) {
					String fileName = pathname.getName();
					if(Util.find("\\.flv$", fileName)){//只对未入库(未带.bak后缀的)文件进行扫描和入库
						if(!isCurrentDayLog(pathname.getAbsolutePath())){
							return false;
						}else{
							return true;
						}
					}else{
						log.info("查询文件："+fileName+" 已入库。");
						return false;
					}
				}
			});
			
			if(msgFiles == null ){
				log.info("录制视频所在的目录符合文件类型的文件为空，继续扫描...");
				return ;
			}
			
			for(File msgFile:msgFiles){
				//解析录制的视频文件，进行入库操作；
				try {
					parseAndIntoDB(msgFile);
				}catch (Exception e) {
					log.error("文件["+msgFile.getAbsolutePath()+"]入库失败",e);
				}
			}
		
			
		}
	}

	/**
	 * 判断该日志文件是否在今天生成的；
	 * @param fileName
	 * @return
	 */
	public boolean isCurrentDayLog(String fileName){
		try {   
			if(fileName == null){
				return false;
			}
			
			File f = new File(fileName);              
		    Calendar cal = Calendar.getInstance();  
		    long time = f.lastModified();  
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");         
		    cal.setTimeInMillis(time);    
		        
		    String str = formatter.format(cal.getTime());
		    
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");        
			Date date = null;   
		    date = format1.parse(str);  
		    
		    return getDayDiffFromToday(date,0);
		    
		} catch (Exception e) {  
			log.error(e.getMessage());
		}   
		return false;
	}
	
	/** 
	* 判断给定日期是否为当天， 

	*距离当前时间七天之内的日期，和七天之外的日期 

	* @param dt 
	* @param type 0--当天 1--7天之内的 2--7天之外的 
	* @return 
	*/ 
	public static boolean getDayDiffFromToday(Date dt, int type) {
		Date today = new Date();
		today.setHours(23);
		today.setMinutes(59);
		today.setSeconds(59);

		long diff = today.getTime() - dt.getTime();
		if (diff < 0)
			diff = 0;
		long days = diff / (1000 * 60 * 60 * 24);

		if (type == 0 && days == 0)
			return true;
		if (type == 1 && days > 0 && days <= 7)
			return true;
		if (type == 2 && days > 7)
			return true;

		return false;
	}

	@SuppressWarnings("unchecked")
	private <T> void parseAndIntoDB(File localFile) throws IOException{
		
		String fileName = localFile.getName();
		String roomNameTemp = fileName.substring(fileName.indexOf("_")+1);
		
		String roomName = null;
		if(roomNameTemp != null){
			roomName = roomNameTemp.substring(0,roomNameTemp.indexOf("."));
		}
		
		Connection conn = null;
		PreparedStatement update_pstmt = null;
		PreparedStatement insert_pstmt = null;
		try{
			try{
				log.info("读取视频录制文件:vod_"+ roomName);
				if(conn==null||conn.isClosed()){
					conn = ServiceLocator.getInstance().getDBConn();
				}
				try {
					update_pstmt = conn.prepareStatement(Const.UPDATE_MSG_SQL);
					insert_pstmt = conn.prepareStatement(Const.INMSG_SQL);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
				
				Map paramter = new HashMap();
				paramter.put("roomName", roomName);
				paramter.put("fileName", fileName);
				paramter.put("fileSize", localFile.length());
				int count = updateDB(paramter,update_pstmt,conn);
				if(count == 0){
					intoDB(paramter,insert_pstmt,conn);
				}
			}catch(Exception e){
				log.error(e.getMessage());
			}
		}finally{
			ClearDBResource.closeConnection(conn);
			ClearDBResource.closeStatment(insert_pstmt);
			ClearDBResource.closeStatment(update_pstmt);
		}
	}

	private <T> void intoDB(Map paramter,PreparedStatement pstmt,Connection conn) throws SQLException{
		pstmt.setInt(1,Integer.parseInt(paramter.get("roomName").toString()));
		pstmt.setString(2,paramter.get("fileName").toString());
		pstmt.setFloat(3,Float.parseFloat(paramter.get("fileSize").toString()));
		pstmt.execute();
	}
	
	private int updateDB(Map paramter,PreparedStatement pstmt,Connection conn) throws SQLException{
		pstmt.setFloat(1,Float.parseFloat(paramter.get("fileSize").toString()));
		pstmt.setInt(2,Integer.parseInt(paramter.get("roomName").toString()));
		pstmt.setString(3,paramter.get("fileName").toString());
		return pstmt.executeUpdate();
	}
	
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setStoreFlag(boolean storeFlag) {
		this.storeFlag = storeFlag;
	}

}
