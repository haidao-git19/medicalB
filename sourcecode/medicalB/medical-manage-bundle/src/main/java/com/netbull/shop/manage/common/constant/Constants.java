package com.netbull.shop.manage.common.constant;

public class Constants {
	public static final String SUCCESS = "0";
	public static final String SUCCESS_MSG = "成功";
	public static final String FAIL = "1";
	public static final String FAIL_MSG = "操作失败";
	public static final String REPEAT_MSG="重复操作";
	public static final String NONE_USER_MSG="没有注册";
	
	/** 登录*/
	public static final String ISLOGIN = "1";
	
	
	public static final String WEIXIN_TOKEN = "odchina";
	
	public static final String DSNAME = "100";
	
	/** 创建二维码ticket */
	public static final String CREATE_QRCODE_TICKET = "QR_LIMIT_SCENE";
	/** 订阅 */
	public static final String BASE_ACCESS_TOKEN = "client_credential";
	/** 文本类 */
	public static final String MSG_TYPE_TEXT = "text";
	/** 图片类 */
	public static final String MSG_TYPE_IMAGE = "image";
	/** 位置服务类 */
	public static final String MSG_TYPE_LOCATION = "location";
	/** 位置服务类 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	/** 地图位置 */
	public static final String EVENT_MAP_KEYWORD = "地图服务";
	/** 事件类 */
	public static final String MSG_TYPE_EVENT = "event";
	/** 链接类 */
	public static final String MSG_TYPE_LINK = "link";
	/** 图文 */
	public static final String MSG_TYPE_NEWS = "news";

	/** 音乐 */
	public static final String MSG_TYPE_MUSIC = "voice";
	
	/** 订阅 */
	public static final String EVENT_SUBSCRIBE = "subscribe";
	/** 取消订阅 */
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	/** 自定义菜单点击事件 */
	public static final String EVENT_CLICK = "CLICK";

	/** 默认消息 */
	public static final String TIPS_KEY_DEFAULT_MESSAGE = "TIPS_KEY_DEFAULT_MESSAGE";

	public static final String RESULT_FLAG = "__flag__"; // 执行结果代码变量名, 0-成功,
	// 其它-失败

	public static final String RESULT_DESC = "__desc__"; // 执行结果描述变量名
	
	/** 第三方用户唯一凭证*/
	public static final String APPID = "wx66457aabc6a28cbf";
	/** 第三方用户唯一凭证密钥，既appsecret*/
	public static final String SECRET = "d9ba59b6b294af9809cd7f41ef9bd897";
	
	/** 第三方用户唯一凭证*/
//	public static final String APPID = "wx66457aabc6a28cbf";
	/** 第三方用户唯一凭证密钥，既appsecret*/
//	public static final String SECRET = "15b3bfe92d88cdb29c1b9b2bbc267c90";
	
	/** 第三方token */
	public static final String ACCESS_TOKEN = "dd7cdedf9046f7c0fc3e1d9e4fac559c";
	
	/** 获取凭证*/
	public static final String  GET_TOKEN= "https://api.weixin.qq.com/cgi-bin/token";
	/** 创建二维码 */
	public static final String  GET_QRCODE_TICKET= "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	/** 获取二维码 */
	public static final String  GET_SHOW_QRCODE= "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
	/** 菜单创建*/
	public static final String  CREATE_WEIXIN_MENU= "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	/** 菜单查询*/
	public static final String  QUERY_WEIXIN_MENU= "https://api.weixin.qq.com/cgi-bin/menu/get";
	/*******/
	public static final String QUERY_SHEND_MENU = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	/** 向用户发送信息*/
	public static final String  SEND_WEIXIN_MSG= "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	/** 删除菜单*/
	public static final String  DELETE_WEIXIN_MENU= "https://api.weixin.qq.com/cgi-bin/menu/delete";
	/** 微信上传 */
	public static final String FILE_UPLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
	
	public static final String MATERIAL_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=";
	
	public static final String BROADCAST_MSG = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
	
	public static final String IMAGE_TEXT_MSG = "image_text_msg";
	public static final String IMAGE_MSG = "image_msg";
	public static final String AUDIO_MSG = "audio_msg";
	public static final String VEDIO_MSG = "vedio_msg";
	
	/**** 响应内容XML节点对象名称 *****/
	public static final String RESP_TO_USER = "ToUserName";
	public static final String RESP_FROM_USER = "FromUserName";
	public static final String RESP_CREATE_DATE = "CreateTime";
	public static final String RESP_MSG_TYPE = "MsgType";
	
	public static final String UPLOAD_FILE_TYPE = "thumb";
	
	public static final String SUBSCRITE_MSG_REPLY = "MENU_ATTENTION_REPLY";
	public static final String MENU_DEFAULT_REPLY = "MENU_DEFAULT_REPLY";
	
	public static final String INVOKEN_BUSINESS_METHOD1 = "/queryBespeakTypeList";
	public static final String INVOKEN_BUSINESS_METHOD2 = "/bindBespeakToWechat";
	
	public static final String WECHAT_ACCOUNT_TYPE = "HOTEL";
	public static final String KEYWORD_MATERIAL_TYPE = "1";
	
	public static final String SHOP_PRODUCT_KEY = "netbull123!@#";
	
	/****物流状态，3：已签收******/
	public static final String LOGISTICS_STATUS_1 = "3";
	/****物流状态，4：退回******/
	public static final String LOGISTICS_STATUS_2 = "4";
	public static final String LOGISTICS_SECERT_KEY = "2f66b56d542e5d4f001080ac2f367424";
}
