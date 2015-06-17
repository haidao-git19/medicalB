package com.netbull.shop.manage.common.constant;

public class Constants {
	public static final String SUCCESS = "0";
	public static final String SUCCESS_MSG = "成功";
	public static final String FULL_MSG="预约已满";
	public static final String FAIL = "1";
	public static final String FAIL_0 = "2";
	public static final String FAIL_1 = "3";
	public static final String FAIL_2 = "4";
	public static final String FAIL_3 = "5";
	public static final String FAIL_SUBSCRIBE_0 = "1";
	public static final String FAIL_MSG = "操作失败";
	public static final String FAIL_LOCATION = "定位失败";
	public static final String FAIL_MSG_1 = "没有可用的特权";
	public static final String FAIL_MSG_2 = "购买商品库存不足";
	public static final String FAIL_MSG_3 = "用户不存在";
	public static final String FAIL_MSG_4="没有找到此用户的积分信息";
	public static final String FAIL_MSG_5 = "积分不足";
	public static final String FAIL_MSG_6 = "找不到积分兑换特权相关参数(convertPoint为空)";
	public static final String FAIL_MSG_7 = "非VIP用户";
	public static final String FAIL_MSG_8 = "没有你的预约信息";
	public static final String FAIL_MSG_9 = "该账号已注册";
	public static final String FAIL_SUBSCRIBE_MSG_0 = "预约时间还未到";
	public static final String REPEAT_MSG="您已经签过到了!";
	public static final String NONE_USER_MSG="请先登录! ";
	
	public static final Integer FAIL_CARD_0 = -1;
	public static final String FAIL_CARD_MSG_0 = "亲，您的卡还没激活哦";
	public static final Integer FAIL_CARD_1 = 1;
	public static final String FAIL_CARD_MSG_1 = "亲，您的卡已兑换喽";
	public static final Integer FAIL_CARD_2 = 2;
	public static final String FAIL_CARD_MSG_2 = "亲，您的卡已停用了";
	public static final String FAIL_CARD_4 = "3";
	public static final String FAIL_CARD_MSG_4 = "亲，您输入的卡号不存在哦";
	
	public static final String FAIL_PHONE_1 = "1";
	public static final String FAIL_PHONE_MSG_1 = "手机号码格式错误";
	
	public static final String FAIL_CARD_FORMAT_1 = "1";
	public static final String FAIL_CARD_MSG_FORMAT_1 = "卡格式错误";
	
	public static final String CARD_STATUS = "0";
	
	public static final String FAIL_NULL_RESULT = "0";
	public static final String FAIL_NULL_RESULT_MSG = "没查询到数据";
	
	public static final String FAIL_NULL_1 = "1";
	public static final String FAIL_NULL_MSG = "参数为空";
	
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
	
	public static final String QUERY_SHEND_MENU = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	/** 向用户发送信息*/
	public static final String  SEND_WEIXIN_MSG= "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	/** 删除菜单*/
	public static final String  DELETE_WEIXIN_MENU= "https://api.weixin.qq.com/cgi-bin/menu/delete";
	/** 微信上传 */
	public static final String FILE_UPLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
	
	public static final String MATERIAL_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=";
	
	public static final String BROADCAST_MSG = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
	
	public static final String PAY_WX_CREATE_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
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
	
	public static final Integer QUERY_GOODS_PAGE_SIZE = 10;
	public static final Integer QUERY_DIY_GOODS_PAGE_SIZE = 50;
	public static final String WX_PAY_TRAD_TYPE = "JSAPI";
	
	public static final String ALIPAY_SUCCESS = "success";
	public static final String ALIPAY_FAIL = "fail";
	public static final String ALIPAY_SIGN_TYPE="0001";
	public static final String ALIPAY_VERSION="2.0";
	public static final String ALIPAY_FORMAT="xml";
	
	public static final String SHOP_PRODUCT_KEY = "netbull123!@#";
	
	public static final String HOSTPITAL_TYPE_0 = "0";
	public static final String HOSTPITAL_TYPE_1 = "1";
	
	public static final String CONSULATOIN_PAY_STATE_FINISH = "1";
	public static final String CONSULATOIN_PAY_STATE_FAIL = "2";
	public static final String SUBSCRIBE_PAY_STATE_FINISH = "1";
	public static final String SUBSCRIBE_PAY_STATE_FAIL = "2";
	
	public static final String CONSULATOIN_PAY_TYPE = "1";
	public static final String SUBSCRIBE_PAY_TYPE = "2";
	public static final String PLUS_PAY_TYPE = "3";
}
