package com.netbull.shop.common.util;


import java.io.IOException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
	/**
	* @说明 字符�?DESede(3DES) 加密、解�?
	* @version v0.0.1
	* @author
	* @创建时间 2005-12-26 12:00
	*
	* @修改历史
	* <li>版本�?修改日期 修改�?影响�?修改说明
	* <li>v0.0.2 2005-10
	* <li>v0.0.3 2005-12-26
	*/
public class DesEncryptIm{
	//定义加密算法，在Java 下使用DESede
	private static final String Algorithm = "DESede";
	 private static final String key="A314BA5A3C85E86KK887WSWS";
	/**
	* 对输入的字串进行3DES 加密
	* @param keybyte[] 加密密钥，长度为24 字节
	* @param src[] 要加密的字串
	*/
	public byte[] encrypt3DES(byte[] keybyte, byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(keybyte,
			Algorithm);
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
	
	/**
	* 对输入的字串进行3DES 加密
	* @param keybyte[] 加密密钥，长度为24 字节
	* @param src[] 要加密的字串
	*/
	public byte[] encrypt3DES( byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(key.getBytes(),
			Algorithm);
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
	/**
	* 对输入的字串进行3DES 解密
	* @param keybyte[] 加密密钥，长度为24 字节
	* @param src[] 要解密的字串
	*/
	public byte[] decrypt3DES(byte[] keybyte, byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(keybyte,
			Algorithm);
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
	
	public byte[] decrypt3DES( byte[] src) {
		try {
			SecretKey deskey = new SecretKeySpec(key.getBytes(),
			Algorithm);
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
	/**
	* 转为base64 编码
	* @param btParm[] 要转换的字串
	*/
	public String encodeBase64(byte[] btParm) throws IOException {
		BASE64Encoder encoder=new BASE64Encoder();
		String sParm = encoder.encodeBuffer(btParm);
		return sParm;
	}
	/**
	* 反转base64 编码
	* @param sParm 要反转的字符�?
	*/
	public byte[] decodeBase64(String sParm) throws IOException {
		BASE64Decoder decoder=new BASE64Decoder();
		byte[] btParm = decoder.decodeBuffer(sParm);
		return btParm;
	}
	
	public static String byteToString(byte b) { 
		byte high, low; 
		byte maskHigh = (byte)0xf0; 
		byte maskLow = 0x0f; 

		high = (byte)((b & maskHigh) >> 4); 
		low = (byte)(b & maskLow); 

		StringBuffer buf = new StringBuffer(); 
		buf.append(findHex(high)); 
		buf.append(findHex(low)); 

		return buf.toString(); 
		} 

		private static char findHex(byte b) { 
		int t = Byte.valueOf(b); 
		t = t < 0 ? t + 16 : t; 

		if ((0 <= t) &&(t <= 9)) { 
		return (char)(t + '0'); 
		} 

		return (char)(t-10+'A'); 
	} 
	public static String getAsHexaDecimal(byte[] hash) {
		StringBuffer strHex = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			 strHex.append(byteToString(hash[i]));			 
		}
	    return strHex.toString();
	}
	
	public String byte2hex(byte[] b) //二行制转字符�?
    { 
     String hs=""; 
     String stmp=""; 
     for (int n=0;n<b.length;n++) 
      { 
       stmp=(java.lang.Integer.toHexString(b[n] & 0XFF)); 
       if (stmp.length()==1) 
    	   hs=hs+"0"+stmp; 
       else 
    	   hs=hs+stmp; 
       
      } 
     return hs.toUpperCase(); 
    } 



	public static void main(String[] args) throws IOException
	{
	//解密例子，加密类�?
		DesEncryptIm des = new DesEncryptIm();
		String after_3desbase64 =
		"E7kk0pvhKq7lB2lK98zXG+1YYPz+dxSCK2076U4g3BcUTrBI0TvEETk2aSHf6tWCw0CRI8Q6"+
"pABlBI3lclm9vdrmMuSyNNh4Ye1fqOxJLZ+MqYeWWE8OinE/8bUy5cU0o7hNxLo1fFQ=";
		byte[] btBy = des.decodeBase64(after_3desbase64);
		System.out.println("解密BASE64 后的字符�?" + new String(btBy));
		byte[] srcBytes = des.decrypt3DES("MengXiangTongXing2014".getBytes(), "Me4Qx3zKUgULk".getBytes());
		System.out.println("解密3DES 后的字符�?" + new String(srcBytes,"utf-8"));
	}
}
