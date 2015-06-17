package com.netbull.shop.manage.common.alipay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 */

public class AlipayConfig {
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088411558756163";
	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
	public static String key = "";
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJ55/ZcfGcAbocTFnp3spmKCT0RJuKQjEkwSyFGPCMfB8SYaLzbfqxwbNiJZddaB2DVDWIJIumM/rULV00Mw0+R6MknvOjUzI32AbEcdS+i8e7NX6MKY4PAHW3CZka37OIZdW+obQB7FcUqpnQdotcHPKokCzIsRJrmCNVkUJWgPAgMBAAECgYAc8oWKzCiE+ccjzUE68ZY22jymaZzrhmh6MtgaZbkh2+AzqIbFTDfSK3pI7fRjWVZn7ERHkAdTx7bbRXmQhWO+x58e+CxbD/V3qoXViMThVxuUtz2Kv407ehr3fWKAEZhCbw3wnhFE1WfAf8XgN1xnF86LYxvAUTLPUJtt/5QXEQJBANBVg5ilcSGu2UVRbvhmNbxlgU1+Vl8MjqozoZ810caF3JqV0PFL3m9ghA6DzNfIw8GfHOop4QI+nU3PGCbjX4kCQQDCvDxKqadtSv3VlFpNQ2fwbz8W4K6f1ieYgsSDX3FzKBD1THIrDBOKo4WVjUObXqpY9LfJBBtQeG05POdH18zXAkEAjyxlns77+6j9Gj1HpSAhB9M+VHJhizrHBMMs7IyBGQy/ueEB0gJQ4HwU5SDA8v6/QV5G8cpMH03Gr0WGlJsn+QJBALDKh8oY3+F/VZdTSfAfI/vKY3J3pNGDcQsqnRHP0oWDeHHaZ0PhgNQ6xIoTZ69kcC62bSvXyJxz2GVAf5BfwJ0CQQC8HrQk4wH8RkZvNFkthowIbJqdSAkmSuKSeYrjabySdHJA+u9Ikc9JGmQ3BSFOKto9ooSx0G7r6EB2PBn3eiUa";
    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCItqD681/QLJ3MhBct7XoyIzgXgYyYH5MhIBwa cO8mm7u4duMwqCt6FLRy9tnAAc1jsLXmJW7KHkY0Njl+i9y6K/K63eQ/wJFHkW/rdducM5fGA8rK EaHuh2vIHG08zrNpczQODRdLK9+0lwddh1ucAuNRKlcy3Xl5W3Bleo4rewIDAQAB";
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";
	// 字符编码格式 目前支持  utf-8
	public static String input_charset = "utf-8";
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA
}
