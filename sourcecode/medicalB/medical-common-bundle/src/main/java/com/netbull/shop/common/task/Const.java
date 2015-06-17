package com.netbull.shop.common.task;

public class Const {

	/** 入参类型 */
	public static final int INMSG = 1;
	public static final String INMSG_SQL = "insert into live_instance_file_attr(live_instance_attr_id,live_id,attr_id  ,file_name,ower_type,ower_id,file_size,file_desc,file_path,create_date,state,state_date) values ( seq_live_inst_attr_id.nextval, ?,17,?,4,'',replace(?, 'KB',null),'录制视频文件','/opt/adobe/fms/applications/broadcast_fms/streams',sysdate,'S0A',sysdate )";

	public static final String UPDATE_MSG_SQL = "update live_instance_file_attr set file_size=replace(?, 'KB',null),create_date=sysdate, state_date=sysdate	where  live_id = ? and file_name=?";

	public static final String GET_PARAMTER_SQL = "select A.LIVE_ID||'_'||A.LIVE_ATTR_ID as streamName from live_instance_attr a where a.Value3=?";
	/** 日志分隔符 */
	public static final String SPLIT_FLAG = "\\|\\|\\|";

	public static final String TIME_OUT = "/inc/timeout.jsp";
	public static final String NO_AUTH = "/inc/noauth.html";
	public static final String USER_SESSION_KEY = "userInfo";
	public static final String USER_NAME_SESSION_KEY = "user.name";
	public static final String STYLE_BASE_PATH = "style_base_path";
	public static final String RANDNUM_KEY = "RANDNUM_KEY";
	public static final String PARAMETER = "action";
	public static final String SYSTEM_SWITCH = "switch";
	public static final String SYSTEM_ON = "on";
	public static final String SYSTEM_OFF = "off";
	public static final String ERROR_KEY = "org.apache.struts.action.ERROR";
	public static final String MESSAGE_KEY = "org.apache.struts.action.ACTION_MESSAGE";
	public static final String SIGN = "!";
	public static final String ROOT_MENU_URL = "ROOT_MENU_URL";
	public static final String FILE_SEP = System.getProperty("file.separator");
	public static final String NEXT_LINE = System.getProperty("line.separator");
	public static final String LOCALNET_KEY = "areaCode";
	public static final String AREA_CODE = "areaCode";
	public static final String VC_RECHARGE = "recharge";
	public static final String VC_SERVICE = "vc_card";
	public static final String LATN_ID_PROVINCE = "999";
	public static final String PARAMS_KEY = "params.key";
	public static final String SHOP_PRODUCT_KEY = "netbull123!@#";
	public static final String NETBULL_LOGIN_CODE = "netbull";
	public static final String AUTH2_OPEN = "0";

}
