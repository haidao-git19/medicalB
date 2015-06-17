package com.netbull.shop.common.vo;

/**
 * 
 * @author elvis 常量
 */
public class Constant {
	/**
	 * 成功
	 */
	public final static String SUCCESS = "0";
	public final static String CONFIG_STATUS = "YES";
	public final static String SUCCESSCODE = "0000";
	public final static String OAUTH2_OPEN_FLAG = "0";

	/**
	 * 失败
	 */
	public final static String FAIL = "1";
	

	/**
	 * 安徽省本地网
	 */
	public final static String AH_LATN_ID = "888";

	/**
	 * 成功
	 */
	public final static String PCSUCCESS="1";
	/**
	 * 失败
	 */
	public final static String PCFAIL="0";
	/**
	 * 系统的换行符
	 */
	public static final String LINE_SEP = System.getProperty("line.separator");
	
	/**
	 * 请求包中的行缩进
	 */
	public static final String TAB = "\t";
	
	/**
	 * 企业发送流量类型：按时计算；
	 */
	public static final String ENT_SEND_COUNT_TYPE_1 = "1";
	
	/**
	 * 企业发送流量类型：按天计算；
	 */
	public static final String ENT_SEND_COUNT_TYPE_2 = "2";
	
	/**
	 * 企业发送流量类型：按月计算；
	 */
	public static final String ENT_SEND_COUNT_TYPE_3 = "3";
	
	// session中客户信息的key
	public static final String CUST_INFO_KEY = "custInfo";

	// 未登录或登录超时跳转至的页面
	public static final String LOGIN_PAGE = "tologin";
	
	// 发送方式；1：实时	2：定时
	public static final String SEND_METHOD_1 = "1";
	public static final String SEND_METHOD_2 = "2";
	
	// 任务状态；1：web 2：接口
	public static final String TASK_TYPE_1 = "1";
	public static final String TASK_TYPE_2 = "2";
	
	// 任务类型；1：已保存	2：待审核	3：待发送	4：正在发送	5：审核不通过	6：已暂停	7：发送完成	8：已完成	0：已删除
	public static final String TASK_STATE_DELETE = "0";
	public static final String TASK_STATE_SAVE = "1";
	public static final String TASK_STATE_READYING_VERIFY = "2";
	public static final String TASK_STATE_READYING_SEND = "3";
	public static final String TASK_STATE_PROCESS_SEND = "4";
	public static final String TASK_STATE_VERIFY_NOPASS = "5";
	public static final String TASK_STATE_STOP = "6";
	public static final String TASK_STATE_SEND_FINISH = "7";
	public static final String TASK_STATE_FINISHED = "8";
	public static final String NOT_CORRENT_VISITCODE = "notCorrectVisitCode";
	public static final String TO_WEB_CAST_PAGE = "toWebCast";
	public static final String TO_VOD_PLAY_PAGE = "toVodPlay";
	
	public static final String VOD_PREVIEW_MODE = "vodPreviewMode";
	public static final String LIVE_PREVIEW_MODE = "livePreviewMode";
	//任务单批条数
	public static final long TASK_BATCH_SINGLE = 200;
	
	public static final String FILE_CHARA_ENCODE = "GBK";
	public static final String LOCAL_SERVER_ADDRESS_PREFIX = "61.191";
	
	public static final String XML_ROOT_START = "<xt  version='4.0'>";
	public static final String XML_ROOT_END = "</xt>";
	
	public static final String XML_GROUP_LIST_START = "<actionlist>";
	public static final String XML_GROUP_LIST_END = "</actionlist>";
	
	/** 行为轨迹需要cookie信息常量 */
	public static final String USERTRACK_OPENID = "USERTRACK_OPENID";
	public static final String USERTRACK_LOGINCODE = "USERTRACK_LOGINCODE";
	public static final String MANAGE_DOMAIN = "manage_domain";
	public static final String DBS_DOMAIN = "dbs_domain";
	public static final String UEER_OPEN = "openId";
	public static final String USERTRACK_PRODTYPE = "USERTRACK_PRODTYPE";
	public static final String USERTRACK_LATNID = "USERTRACK_LATNID";
	public static final String USERTRACK_SESSIONID = "USERTRACK_SESSIONID";
	public static final String LEFT_MUNEU_VALUE = "左边菜单";
	public static final String XML_START_RECORD = "<actiongroup><action command=\"record\" container=\"20\" containerObjectId=\"20001\"></action></actiongroup>";
	
	public static final String AUTH2_OPEN = "0";
	
	public static final String USERGOODSCODE_SESSIONID = "USERGOODSCODE_SESSIONID";
	public static final String USERGOODSVERSION_SESSIONID = "USERGOODSVERSION_SESSIONID";
	
	/**
	 * 图片类型
	 */
	//病人
	public static final String IMAGE_PATIENT = "patient";
	public static final String IMAGE_PATIENT_DESC = "会员图片";
	//新生儿
	public static final String IMAGE_NEW_BORN = "newborn";
	public static final String IMAGE_NEW_BORN_DESC = "新生儿";
	//药店
	public static final String IMAGE_PHARMACY = "pharmacy";
	public static final String IMAGE_PHARMACY_DESC = "药店图片";
}
