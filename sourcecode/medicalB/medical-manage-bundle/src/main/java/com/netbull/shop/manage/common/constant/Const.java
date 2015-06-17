package com.netbull.shop.manage.common.constant;

public abstract interface Const
{
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
  
  public static final String ACTIVE_STATUS_READY="未开始";
  public static final String ACTIVE_STATUS_PROGRESS="进行中";
  public static final String ACTIVE_STATUS_END="已结束";
  
  public static final Integer QUERY_HEALTH_KNOWLEGE_PAGE_SIZE = 10;
}