package com.netbull.shop.common.log;

public class LogConstant {
	 
    /**
     * the suffix of backup log file for transferring
     */
    public final static String LOG_BACKUP_SUFFIX=".bak";
    
    /**
     * the mark in error message for substitute
     */
    public final static String REP_MARK="?";
    /**
     * the subscription ID for creating Durable Subscriber in JMS
     */
    public final static String JMS_SUBSID="myLog";
    
    /**
     * the biz log prefix
     */
    public final static String BIZ_LOG_TYPE="biz";
    
    /**
     * the prefix of all the code of us
     */
    public final static String TYDIC_LOG_TYPE="com.tydic";
    
    /**
     * the seperator of mobile ID and other log content
     * also seperate error code and error message
     */
    public final static String COLON_SEPERATOR=":";
}
