package com.netbull.shop.common.util;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * 使用DES加密与解�?可对byte[],String类型进行加密与解�?密文可使用String,byte[]存储.
 *
 * 方法: void getKey(String strKey)从strKey的字条生成一个Key
 *
 * String getEncString(String strMing)对strMing进行加密,返回String密文 String
 * getDesString(String strMi)对strMin进行解密,返回String明文
 *
 * byte[] getEncCode(byte[] byteS)byte[]型的加密 byte[] getDesCode(byte[]
 * byteD)byte[]型的解密
 */

public class DesEncrypt {
 private static final String Algorithm = "DESede"; // 定义 加密算法,可用
 private static final String key="ahdxgs200801091645wsdlrz";// DES,DESede,Blowfish

 // keybyte为加密密钥，长度�?4字节
 // src为被加密的数据缓冲区（源�?
 public static byte[] encryptMode(byte[] keybyte, byte[] src) {
   try {
     // 生成密钥
     SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
     // 加密
     Cipher c1 = Cipher.getInstance(Algorithm);
     c1.init(Cipher.ENCRYPT_MODE, deskey);
     return c1.doFinal(src);
   } catch (java.security.NoSuchAlgorithmException e1) {
     e1.printStackTrace();
   } catch (javax.crypto.NoSuchPaddingException e2) {
     e2.printStackTrace();
   } catch (java.lang.Exception e3) {
     e3.printStackTrace();
   }
   return null;
 }

 // keybyte为加密密钥，长度�?4字节
 // src为加密后的缓冲区
 public static byte[] decryptMode(byte[] keybyte, byte[] src) {
   try {
     // 生成密钥
     SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
     // 解密
     Cipher c1 = Cipher.getInstance(Algorithm);
     c1.init(Cipher.DECRYPT_MODE, deskey);
     return c1.doFinal(src);
   } catch (java.security.NoSuchAlgorithmException e1) {
     e1.printStackTrace();
   } catch (javax.crypto.NoSuchPaddingException e2) {
     e2.printStackTrace();
   } catch (java.lang.Exception e3) {
     e3.printStackTrace();
   }
   return null;
 }

 // 转换成十六进制字符串
 public static String byte2hex(byte[] b) {
   String hs = "";
   String stmp = "";

   for (int n = 0; n < b.length; n++) {
     stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
     if (stmp.length() == 1)
       hs = hs + "0" + stmp;
     else
       hs = hs + stmp;
     if (n < b.length - 1)
       hs = hs + ":";
   }
   return hs.toUpperCase();
 }
 public static byte[] getEncCode(byte[] szSrc){
   // 添加新安全算�?如果用JCE就要把它添加进去
   Security.addProvider(new com.sun.crypto.provider.SunJCE());
   final byte[] keyBytes=key.getBytes();
   return encryptMode(keyBytes, szSrc);
 }
 /**
  * 加密String明文输入,String密文输出
  *
  * @param strMing
  * @return
  */
 public static String getEncryptString(String strMing) {
   BASE64Encoder base64en = new BASE64Encoder();
   //return URLEncoder.encode(base64en.encode(getEncCode(strMing.getBytes())));
   return base64en.encode(getEncCode(strMing.getBytes()));
 }
 /**
  * 解密 以String密文输入,String明文输出
  *
  * @param strMi
  * @return
  */
 public static String getDesString(String strMi) {
   BASE64Decoder base64De = new BASE64Decoder();
   byte[] byteMing = null;
   byte[] byteMi = null;
   String strMing = "";
   try {
     byteMi = base64De.decodeBuffer(strMi);
     byteMing = decryptMode(key.getBytes(), byteMi);
     strMing = new String(byteMing, "UTF8");
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     base64De = null;
     byteMing = null;
     byteMi = null;
   }
   return strMing;
 }
 /**
  * 解密 以String密文输入,String明文输出
  *
  * @param strMi
  * @return
  */
 public static String getDecryptString(String strMi) throws Exception {
		try {
			strMi = new String(getDesString(strMi).getBytes());
			return strMi;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 /**
	 * 将s进行SHA编码
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] getSHA(String s) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (md != null) {
			md.update(s.getBytes());
			return md.digest();
		} else {
			return null;
		}
	}
 /**
	 * �?s 进行 BASE64 编码
	 * 
	 * @author gongwenliang
	 * @return
	 * 
	 */
 public static String getBASE64(byte[] s) {
   if (s == null)
     return null;
   return (new sun.misc.BASE64Encoder()).encode(s);
 }

 /**
  * �?BASE64 编码的字符串 s 进行解码
  * @author gongwenliang
  * @return
  */
 public static String getFromBASE64(String s) {
   if (s == null)
     return null;
   BASE64Decoder decoder = new BASE64Decoder();
   try {
     byte[] b = decoder.decodeBuffer(s);
     return new String(b);
   } catch (Exception e) {
     return null;
   }
 }

 public static void main(String[] args) {
   String strEnc = DesEncrypt.getDesString("12$00000000000000000000$2007-09-22 18:24:58$dJKTvgDlUclukwTonfapf6IexAk=");
//   strEnc=DesEncrypt.getDecryptString(strEnc);
 }
}
