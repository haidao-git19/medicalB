package com.netbull.shop.common.wb;

public interface WBConstant {
	
	
	//设置请求对象中的本地网，用于2个大的分区的调用
	public static final String WSS_LATN_ID = "WSS_LATN_ID";
	/**
	 * 表示业务系统接口的ip与端口间的分割符
	 */
	public static final String IP_PORT = ":";

	/**
	 * 系统的换行符
	 */
	public static final String LINE_SEP = System.getProperty("line.separator");

	/**
	 * XML包中的行缩进
	 */
	public static final String XML_INDENT = " ";

	/**
	 * 请求包中的ID中的日期格式
	 */
	public static final String MSG_ID_DATE = "yyyyMMDDHHmmss";

	/**
	 * 日志中的日期格式
	 */
	public static final String LOG_DATE = "yyyy-MM-dd HH:mm:ss,SSS";

	/**
	 * 当前同mocs系统的接口版�?
	 */
	public static final String CURRENT_VERSION = "1.0.0";

	/**
	 * 请求包头的标�?在encode之前
	 */
	public static final String REQUEST_HEADER_PREFIX = "<?xml version='1.0' encoding='";

	/**
	 * 请求包头的标�?在encode之后
	 */
	public static final String REQUEST_HEADER_SUFFIX = "'?>";

	/**
	 * 请求包中的行缩进
	 */
	public static final String TAB = "\t";

	/**
	 * sgbiz.log日志格式内容之间使用“|”分�?
	 */
	public static final String LOG_SEP = "|";

	/**
	 * 从链接池取链接时采用轮循算法
	 */
	public static final int LINKPOOL_SELECT_ROUND = 0;

	/**
	 * 从链接池取链接时采用�?��负荷算法
	 */
	public static final int LINKPOOL_SELECT_BYLOAD = 1;

	public static final String USER_AUTH_REQ = "UserAuthReq";

	// ///////////////caowentao begin/////////
	// /////////////////CustService begin///////////////////////
	// �?��产品用户的登录合法�?
	public static final String PRD_LOGIN = "prdLogin";

	// �?��产品用户的登录合法�?
	public static final String PRD_LOGIN_NO_PWD = "prdLoginNoPwd";

	// �?��ADSL用户的登录合法�?
	public static final String ADSL_LOGIN_ACCT = "adslLoginAcct";

	// 根据宽带帐号取得客户信息
	//public static final String ADSL_LOGIN = "adslLogin";
	//只针对西藏的adsl登录
	public static final String ADSL_LOGIN_XZ = "adslLoginXZ";
	
	//�?��C网用户的登录合法�?
	public static final String CDMA_LOGIN = "cdmaLogin";

	// 根据客户标识码登�?
	public static final String CUST_LOGIN = "custLogin";

	public static final String _PRD_LOGIN = "_prdLogin";

	public static final String _ADSL_LOGIN = "_adslLogin";

	public static final String _CUST_LOGIN = "_custLogin";

	// 调用 CRM中的客户信息
	public static final String QUERY_CRM_USERINFO_CUST = "queryCrmUserInfo_CUST";
	// 调用 CRM中的客户信息(包含客户基本信息，客户个人信息，联系人信�?
	public static final String QUERY_CRM_CUST_INFO = "queryCrmCustInfo";
	//修改客户相关信息（基本信息�?个人信息、联系人信息�?
	public static final String UPDATE_CUST_INFO = "updateCustInfo";
	// 调用 CRM中的帐户信息
	public static final String QUERY_CRM_USERINFO_ACCT = "queryCrmUserInfo_ACCT";

	// 调用 CRM中的产品信息
	public static final String QUERY_CRM_USERINFO_PROD = "queryCrmUserInfo_PROD";

	// 调用 CRM中的联系人信�?
	public static final String QUERY_CRM_USERINFO_CONT = "queryCrmUserInfo_CONT";

	// 调用 CRM中客户信息的详细信息
	public static final String QUERY_CRM_USERINFO_DETAIL = "queryCrmUserInfoDetail";
	
	// 调用 CRM中客户消费信�?
	public static final String QUERY_CRM_USERINFO_CONSUMER = "queryCrmUserInfoConsumer";

	// 修改ADSL客户密码信息
	public static final String UPDATE_PASSWORD_ADSL = "updatePasswordADSL";
	//只针对西藏修改ADSL密码
	public static final String UPDATE_PASSWORD_ADSL_XZ = "updatePasswordADSLXZ";

	// 修改产品密码信息
	public static final String UPDATE_PASSWORD_PROD = "updatePasswordProd";
	
	//重置产品密码
	public static final String RESET_PASSWORD_PROD="resetPasswordPrd";
	
	//重置产品密码
	public static final String RESET_PROD_PASSWORD="resetPrdPassword";

	// 修改客户密码信息
	public static final String UPDATE_PASSWORD_CUST = "updatePasswordCust";
	
	//查询行业属�?列表
	public static final String QUERY_INDU_ATTR_LIST = "queryInduAttrList";
	//查询联系人�?联系电话和行业属性信�?
	public static final String QUERY_INDU_ATTR_INFO = "queryInduAttrInfo";
	//修改行业属�?信息
	public static final String UPDATE_INDU_ATTR_INFO = "updateInduAttrInfo";
	//修改联系人和联系电话信息
	public static final String UPDATE_CONTACT_INFO = "updateContactInfo";

	// 查询联创的ADSL上网清单(昨日)
	public static final String QUERY_ADSL_BILL = "queryADSLBill";
	// 查询联创的ADSL上网清单(昨日)只针对西�?
	public static final String QUERY_ADSL_BILL_XZ = "queryADSLBillXZ";
	
	public static final String QUERY_TEL_INSTID_BY_ADSL = "queryTelInstIdByAdsl";
	
	public static final String QUERY_PUK_CODE = "queryPukCode";
	
	public static final String CHECK_COMBS_EXIST_MORE = "checkCombsExistMore";
	
	public static final String CHECK_COMBS_EXIST = "checkCombsExist";

	// /////////////////CustService end///////////////////////
	// /////////////////BillingChgService begin///////////////////////
	// 通过产品查询缴费记录
	public static final String QUERY_FEE_BY_PRO = "queryFeeByPro";
	//根据产品查询客户ID
	public static final String QUERY_CUST_ID = "queryCustID";

	// 通过帐户查询用户缴费记录
	public static final String QUERY_FEE_BY_ACCOUNT = "queryFeeByAccount";

	// 通过产品查询欠费
	public static final String QUERY_Q_FEE_BY_PRO = "queryQFeeByPro";

	// 通过帐户查询欠费
	public static final String QUERY_Q_FEE_BY_ACCOUNT = "queryQFeeByAccount";
	
	//通过产品查询帐户
	public static final String QUERY_ACCT = "queryAcct";

	// 查询余额
	public static final String QUERY_BALANCE = "queryBalance";

	// 帐单查询 - 根据帐户查询
	public static final String QUERY_ACCT_FEE = "queryAcctFee";

	// 帐单查询 - 根据帐户查询1
	public static final String QUERY_ACCT_FEE1 = "queryAcctFee1";

	// 帐单查询 - 根据产品查询
	public static final String QUERY_ACCT_FEE_BY_PROD = "queryAcctFeebyprod";

	// 帐单查询 - 根据帐户查询
	public static final String QUERY_ACCT_FEE_CUST = "queryAcctFeeCust";

	// 客户帐单查询 - 根据产品查询
	public static final String QUERY_ACCT_FEE_BY_PROD1 = "queryAcctFeebyprod1";

	// 话费的清单查�?
	public static final String QUERY_TEL_LIST = "queryTelList";
	
	// 西藏话费清单查询
	public static final String QUERY_TEL_LIST_XZ = "queryTelListXZ";
	
	// CDMA清单查询
	public static final String QUERY_CDMA_LIST = "queryCDMAList";
	
	//CDMA实时话费查询
	public static final String QUERY_CDMA_FEE="queryCDMAFee";
	
	public static final String QUERY_REFILL_INFO = "queryRefillInfo";

	// 网上支付—�?�?��
	public static final String PAR_FOR = "payFor";

	// 网上支付—�?对账
	public static final String CHECK_RECORD = "checkRecord";

	// 查询应缴记录
	public static final String SEARCH_FORPAY = "searchForPay";
	//查询历史账单(西藏)
	public static final String QUERY_ACCT_FEE_FOR_WAP = "queryAcctFeeForWAP";

	// /////////////////BillingChgService end///////////////////////
	// /////////////////AcceptQueryService begin///////////////////////
	// 查询子产�?
	public static final String QRY_SUBPRDINSTS_BY_PRDINSTID = "qrySubPrdInstsByPrdInstId";

	// 查询套餐
	public static final String QRY_SUITEPRD_BY_PRDINSTID = "qrySuitePrdByPrdInstId";

	// /////////////////AcceptQueryService end///////////////////////

	// /////////////////IAinterfaceService begin///////////////////////
	// 查询附属�?���?
	public static final String QUERY_SUB_OFR = "querysubOfr";

	// 查询套餐包含
	public static final String QUERY_ALL_OFR = "queryAllOfr";

	// 增加附属�?���?
	public static final String CREATE_SUB_PROD_SERVIEC = "createSubProdServiec";

	// 查询客户下的�?��套餐
	public static final String QUERY_ALL_OFR_BY_USER = "queryAllOfrByUser";

	// 查询客户下的�?��帐户
	public static final String QUERY_DROP_BILLSINFO = "queryDropBillsInfo";

	// 增加附属�?��品�?—只针对�?固网彩玲\七彩铃音\帐单推�?服务)
	public static final String CREATE_SUBPROD_SERVIEC_OTHER = "createSubProdServiec_Other";
	//查询产品实例
	public static final String QUERY_NEW_PRD_INFO = "queryNewPrdInfo";
	// 根据套餐标识查询产品
	public static final String QUERY_PRO_INFO = "queryProInfo";

	// 查询没有套餐的产�?
	public static final String QUERY_NOT_OFR_PRO_INFO = "queryNotOfrProInfo";

	// ///////////////likai begin add/////////

	// 充�?卡记录查�?
	public static final String QUERY_VCFILLRECORD_INFO = "queryFillRecord";

	// 充�?卡余额查�?
	public static final String QUERY_QUERYACCOUNT_INFO = "queryCardRecord";

	// 充�?
	public static final String VCCARD_RECHARGE_PAR = "recharge";

	public static final String VCCARD_BALANCETYPE = "qryBalanceType";

	public static final String VCCARD_BALANCETYPE_ALL = "qryBalanceTypeAll";

	public static final String QUERY_CRM_CUSTPOINT = "searchCustPoint";

	public static final String QUERY_CRM_CUSTPOINTACCT = "getCustPointAcct";

	public static final String QUERY_CRM_CUSTPOINTITEMS = "getCustPointItems";
	
	public static final String QUERY_CRM_USERPOINTITEMS = "getUserPointItems";	
	
	public static final String QUERY_CRM_USERINFO = "queryCrmUserInfo";
	
	// 可用积分查询
	public static final String QUERY_POINT_AVAILABLE = "queryAvailablePoint";

	// 积分明细查询
	public static final String QUERY_POINT_DETAIL = "queryDetailPoint";
	
	// 兑换记录查询
	public static final String QUERY_POINT_EXCHANGE = "queryExchangePoint";
	
	// 与互联星空的单点登陆，获取客户信�?
	public static final String CHECK_CRM_SSOLOGIN = "ssoLogin";

	// AppealService :调用华为接口存储过程
	public static final String SEND_APPEAL_PRE_SERV = "preServAccept";
	// AppealService :调用恒星10000电子流接口；
	public static final String SEND_BH_PRE_SERV = "preBhServAccept";

	public static final String SEND_APPEAL_STOCENTER = "createServicesToCenter";

	// 查询越级投诉
	public static final String QUERY_PASSSERVICE_HISTORY = "queryPassServiceHistory";
	
	// 查询地市投诉
	public static final String QUERY_CUSTOM_INFO = "queryCustomInfo";

	// 与互联星空的单点登陆
	public static final String SSO_VALIDATE_TICKET = "validateTicket";
	//根据当前号码获得产品类型(只针对西藏短信发�?
	public static final String  PRY_PRD_INST_TYPE_XZ= "pryPrdInstTypeXZ";
	// E8-2单点登陆
	public static final String SSO_E82_CROSS = "crossDomainSSO";

	// LOG信息发�?CRM
	public static final String SEND_CRM_MESSAGE = "sendQuene";

	// ///////////////likai end add/////////
	// ///////////CustOrderService begin///////////
	// 业务办理
	public static final String DEAL_CUST_ORDER = "dealCustOrder";
	//只针对西藏的业务办理
	public static final String DEAL_CUST_ORDER_XZ = "dealCustOrderXZ";
	public static final String CREAT_PROD = "createProd";
	
	public static final String UPDATE_PROD = "updateProd";
	
	public static final String DETELE_ORDER = "deleteOrder";
	
	public static final String GET_OFR_FEE = "getOfrFee";

	// ///////////CustOrderService end///////////
	// ///////////PointService end///////////
	//兑换历史查询接口
	public static final String QUERY_REDEEMHIS="queryRedeemHis";
	//验证客户是否具有积分活动的兑换条件接�?
	public static final String CHECK_AVAILABLE="checkAvailable";
	//预兑换功能接�?
	public static final String PRE_REDEEM="preRedeem";
	//积分消费记录查询-客户
	public static final String QUERY_POINT_COMSUME_FOR_CUST = "queryPointConsumeForCust";
	//积分消费记录查询-用户
	public static final String QUERY_POINT_COMSUME_FOR_USER = "queryPointConsumeForUser";
	//查询用户当前积分
	public static final String QUERY_USER_POINT_ACCT = "getUserPointAcct";
	// ///////////PointService begin///////////
	// 根据产品取得用户办理时签订的协议(主要为用户所有套餐的协议)
	public static final String QUERY_PROTOCOS_BY_PRDINST = "queryProtocosByPrdInst";

	// 业务办理纪录查询(查询客户下的订单)
	public static final String QUERY_CUST_ORDER = "queryCustOrder";
	
	//登录用户业务历史记录查询（�?接口，用于商务领航和尊享E8业务�?
	public static final String QUERY_WEB_HISTORY_LIST = "qryWEBHistoryList";
	
	//匿名用户业务历史记录查询（�?接口，用于商务领航和尊享E8业务�?
	public static final String QUERY_GUEST_WEB_HISTORY_LIST = "qryGuestWEBHistoryList";

	// 根据业务记录查询业务办理明细内容(查询订单详细)
	public static final String QUERY_CUST_ORDER_DETAIL = "queryCustOrderDetail";
	
	//查询订单记录
	public static final String QUERY_CT_ORDER_INFO = "queryCtOrderInfo";

	// 查询用户�?��的套�?
	public static final String QUERY_OFRINST_BY_PRDINST = "queryOfrInstByPrdInst";

	// 查询帐户�?��的套�?
	public static final String QUERY_OFRINST_BY_ACCT = "queryOfrInstByAcct";

	// 查询客户�?��的套�?
	public static final String QUERY_OFRINST_BY_CUST = "queryOfrInstByCust";

	// 查询套餐详细信息
	public static final String QUERY_OFRINST_DETAIL = "queryOfrInstDetail";

	// 查询套餐明细实例详细信息
	public static final String QUERY_OFR_DETAILINST_INFO = "queryOfrDetailInstInfo";
	
	public static final String HAS_ADSL_BOUND = "hasAdslBound";

	// 查询主产品对应的附属�?���?
	public static final String QUERY_SUBPRD_INFO = "qrySubPrdInfo";

	// 订单删除
	public static final String DEL_ORDER_BY_ORDERID = "delOrderByOrderId";
	// 查询客户服务的故障代�?
	public static final String QUERY_SUB_TYPE_4WEB = "querySubType4Web";

	// ///////////CustOrderService end///////////

	// /////////////////IAinterfaceService end///////////////////////
	public static final String ERROR_MSG_0000 = "0000";

	public static final String ERROR_MSG_0001 = "0001";

	public static final String ERROR_MSG_0002 = "0002";

	public static final String ERROR_MSG_0003 = "0003";

	// 修改密码错误
	public static final String ERROR_MSG_0004 = "0004";

	// WEB WERVICE接口调用错误
	public static final String ERROR_MSG_0005 = "0005";

	// 查询ADSL清单接口(联创)调用错误
	public static final String ERROR_MSG_0006 = "0006";

	// 返回的记录集为空�?
	public static final String ERROR_MSG_0007 = "0007";
	
	//重置密码错误
	public static final String ERROR_MSG_0008 = "0008";

	// ///////////////caowentao end/////////

	// ///////////////likai begin add/////////

	// 调用SOAP充�?接口异常（充值卡记录查询�?
	public static final String ERROR_MSG_9999 = "9999";

	// 调用SOAP充�?接口异常(充�?卡余额查�?
	public static final String ERROR_MSG_9998 = "9998";

	// 调用SOAP充�?接口异常
	public static final String ERROR_MSG_9997 = "9997";

	public static final String ERROR_MSG_9996 = "9996";

	public static final String ERROR_MSG_9995 = "9995";

	public static final String ERROR_MSG_9994 = "9994";

	public static final String ERROR_MSG_9993 = "9993";

	public static final String ERROR_MSG_9992 = "9992";

	public static final String ERROR_MSG_9991 = "9991";

	// 调用CRM接口（与互联星空的单点登陆，获取客户信息）异�?
	public static final String ERROR_MSG_9990 = "9990";

	// 投诉调用华为接口失败
	public static final String ERROR_MSG_9989 = "9989";

	// 投诉调用CRM接口失败
	public static final String ERROR_MSG_9988 = "9988";

	// 投诉调用省平台接口失�?
	public static final String ERROR_MSG_9987 = "9987";

	// 投诉调用省平台接口失�?
	public static final String ERROR_MSG_9986 = "9986";

	// 查询华为10000的投诉失�?
	public static final String ERROR_MSG_9985 = "9985";

	// 查询越级投诉失败
	public static final String ERROR_MSG_9984 = "9984";

	public static final String ERROR_MSG_9983 = "9983";

	public static final String ERROR_MSG_9982 = "9982";
	
	//查询积分兑换历史记录
	public static final String SEARCH_REDEEM_HIS = "SEARCH_REDEEM_HIS";

	// ///////////////likai end add/////////
	
	//支付查询用户信息
	public static final String QUERY_CUSTINFO_BY_TELNUM="queryCustInfoByTelnum";
	//支付查询账户编号
	public static final String QUERY_ACCTID_BY_PRDINSTID="queryAcctIdByPrdInstId";
	//支付查询客户编号
	public static final String QUERY_ACCTNBR_BY_CUSTID="queryAcctNbrByCustId";
	//获取支付跳转地址
	public static final String GET_PAY_REDIRECT_STRING="getPayRedirectString";
	//缴费
	public static final String PAY_FEE="payFee";
	//支付查询缴费员工�?
	public static final String QUERY_PAY_STAFFID_BY_LATNID="queryPayStaffIdByLatnId";
	//修改CDMA客户信息
	public static final String MODIFY_CUST_INFO="modifyCustInfo";
	//查询CDMA单一套餐 
	public static final String QUERY_BUY_SUITE_PRDS="queryBuySuitePrds";
	//查询欠费(西藏)
	public static final String QUERY_OWE_FEE_XZ="queryOweFeeXZ";
	//查询产品状�? 
	public static final String QUERY_PRD_STATE="queryPrdState";
	//订购单一套餐
	public static final String CREATE_SUITE_PROD="createSuiteProd";
	//取消单一套餐
	public static final String DELETE_SUITE_PROD="deleteSuiteProd";
	//查询号码信息
	public static final String QUERY_NUMBER="queryNumber";
	//�?��身份证号码是否可以继续预�?
	public static final String CHECK_ID_CARD="checkIdCard";
	//预约号码
	public static final String BOOKING_NUMBER="bookingNumber";
	//添加CDMA信息
	public static final String INSERT_CDMA="insertCDMA";
	//获取移动号码�?
	public static final String QUERY_NUMBER_SEGMENT="queryNumberSegment";
	//根据号码段号获取移动号码
	public static final String QUERY_NUM_BY_SEG="queryNumBySeg";
	//产品密码重置
	public static final String RESET_PASSWORD="resetPassword";
	//查询产品状�?
	public static final String QUERY_PRD_INST_STATUS="queryPrdInstStatus";
	//查询余额
	public static final String QUERY_BALANCE_XZ="queryBalanceXZ";
	//查询当月费用
	public static final String QUERY_MONTH_FEE="queryMonthFee";
	// 增�?业务订购
	public static final String CREATE_SUB_SCRIPTION = "createSubscription";
	// 取消增�?业务订购
	public static final String WITHDRAW_SUB_SCRIPTION = "withdrawSubscription";
	// 取消�?��增�?业务订购
	public static final String WITHDRAW_ALL_SUB_SCRIPTION = "withdrawAllSubscription";
	// 查询订购增�?业务
	public static final String GET_SUB_SCRIPTION = "getSubscription";
	//查询CDMA套餐使用情况
	public static final String QUERY_PACKAGE_USE = "queryPackageUse";
	// 请求CRM发�?验证�?
	public static final String SEND_SMS_VALIDATE = "sendSMSValidate";
	//请求CRM验证随机�?
	public static final String VALIDATE_RANDOMID = "valiDateRandomID";
	
	//C+W拨号密码修改
	public static final String MODIFY_CW_DIAL_UP_PASS = "modifyCWDialUpPass";
	
	/* crm中的网上营业厅用�?*/
	public static String CT10000_STAFFID = "263682"; // 263682 网上营业�?ct10000
	// /123456
	
	//service对象中错误信息封装Map中的key
	public static final String ERROR_RESULT_MAP_KEY = "error";
	
	//对应�?ervice返回前段的Map中的result
	public static final String SERVICE_RESULT_MAP_KEY = "result";
	
	////////////add by jiangzheng begin //////////////////
	//宽带登录
	public static final String USER_LOGIN_ADSL = "5";
	//用户登录
	public static final String LOGIN = "login";
	//根据客户获取账务关系
	public static final String QUERY_CUST_ACCOUNTS = "queryCustAccounts";
	//根据客户获取�?��品关�?
	public static final String QUERY_CUST_ORDERED_OFFERING = "queryCustOrderedOffering";
	//业务历史记录查询
	public static final String QUERY_HIS_ORDER_INFO = "queryHisOrderInfo";
	
	//代理商为客户充�?
	public static final String AGENT_RECHARGE = "agentRecharge";
	//查询代理商帐户余�?
	public static final String AGENT_ACCOUNT_QUERY = "agentAccountQuery";
	//代理商修改密�?
	public static final String AGENT_PASSWORD_MODIFY = "agentPasswdModify";
	//代理商充值记录查�?
	public static final String AGENT_QUERY_FILL_RECORD = "agentQueryFillRecord";
	//代理商交易统计查�?
	public static final String AGENT_QUERY_SATAT = "agentQuerySatat";
	//代理商返�?
	public static final String AGENT_SELFREFUND = "agentSelfRefund";
	//代理商登录验�?
	public static final String AGENT_CHECK_PASSWD="agentCheckPasswd";
	//查询资源系统可用的有效的手机号码�?
	public static final String QUERY_RESOURCE_AVAILABLENO= "getAvailableNo";
	//查询资源系统判断选择的号码是否被占用�?
	public static final String QUERY_RESOURCE_ISAVAILABLENO= "getIsAvailableNo";
	//断言查询接口�?
	public static final String QUERY_CT_SERVER= "authReq";
	//集中认证接口�?
	public static final String QUERY_UA_SERVER= "queryAssertion";
	// 计费返回信息Key
	public static final String RESULT = "RESULT";
	// 计费返回成功失败标识
	public static final String CONSTANT_RESULT_NO = "retno";
	
	// 计费服务类型
	public static final String OPT_TYPE = "optType";
	//计费服务调用成功
	public static final String SUCCESS = "0";
	//计费服务调用失败
	public static final String FAIL = "1";
	
	public static final String APP_LATN_ID = "WSS_LATN_ID";
	//附属�?���?
	public static final String CRM_PRODUCT = "1";
	//增�?业务
	public static final String ISMP_PRODUCT = "2";

	//系统ID 	网上营业�?1003  WAP:1004   短信平台:1005
	public final static String SYSID_VALUE = "1003";
	
}
