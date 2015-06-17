package com.netbull.shop.manage.common.alipay.android.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088411558756163";
	// 商户的私钥
	public static String private_key = "MIICXgIBAAKBgQDSGEDCAyc7iij+RhiRsGd6oK8WUhqFFd7Xq0DEjLFPUWsWEL1x+U8WfJR9fwnnbrLrq5L+6zX6SBfOWLEjQfD7ZNt2uRf0hfyPdDToZpjMcGN+KfBk9iV6waD2gbkM1Dm1W6UuI5e/u6dT8qAmrDDyJxeprDtr2ySFoSvFnPwIlQIDAQABAoGANF2lLnZ4vedm5lH0Z8PPVwpb+bVHNOrJm3ns79CdlUaW6W4Mui7I3rL4k/spLc0yvXkLb4yS7KoxfNYweLhD5wEX8Wzoatqa9l1HugKGHyKjpzkAFhTYfSUogPA+OYM0il1mI/qafpL2fSiEkAC42/ATs6tEuqiQzae9Uz1sNEECQQD+Q76DdkRzi+MEFV0RRfB2YQY2h3WXYDJD5Tky1VlcKuBH5rTWyYhWDM1Swj8Fnxd2gs6/UH5hwl0tosCJxidxAkEA04dVlrimmxoKOvZ6u5yl1YNfMdd/sYpfbLQbE7G6L3sNp01tPJfpG9XLhJYinpRJ6f5AouisChsdLLrjVmOJZQJBANTBbsqlzYU5yg2CqstnUFxdt0stcbHmpmi1h3UHnKxInD7Nxwfqvo/ySmQzcXSMuJ9LuR/QhM/cSQeEpcMi8aECQQCaHnLn3CXXwENP7qJB68KQLKXBZOgu7UjVpOxNjyK+0YODMFC042H+2+jq85DJxfWMvalOiQRAYL0DCC/3k5JZAkEAwG/utDX+vjuP6DQJPf56lXz2OUu0VFQ+O8j+ZEuvCehKc98wtGbk0alDiXTcGj2ugacZPVFNNE7Ks2voU2PrlA==";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
