package com.netbull.shop.common.util;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 /**
  * 
  * 手机用户管理系统WEB接口
  *
  */
public class Base64RC4ToHexString {
	private static final Log log = LogFactory.getLog(Base64RC4ToHexString.class);
	
	/**
	 * TODO 此函数最终需要迁移到yt-core StringEncoder中，目前暂时放在Controller中
	 * 计算RC4并转换为HEX字符串，
	 * @param message
	 * @param key
	 * @return
	 */
	public static String base64EncodeRC4EncryptToHexString(String message, String key) {
		if (message==null)message="";
		message=Base64.encodeBase64String(message.getBytes(Charset.forName("UTF-8")));
		Cipher rc4 = null;
		Charset charset = null;
		try {
			rc4 = Cipher.getInstance("RC4");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		charset = Charset.forName("UTF-8");
		try {
			rc4.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "RC4"));
		} catch (InvalidKeyException e) {
			
		}
		byte[] originalData = message.getBytes(charset);
        try {
			byte[] encryptedData = rc4.doFinal(originalData);
			return Bytes2HexString(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "";
	}
	
	public static String toBytesRc4DecryptBase64Decode(String message, String key) {
		byte[] encryptedData=HexString2Bytes(message);
		Cipher rc4 = null;
		Charset charset = null;
		try {
			rc4 = Cipher.getInstance("RC4");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		charset = Charset.forName("UTF-8");
		try {
			rc4.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "RC4"));
		} catch (InvalidKeyException e) {
			
		}
        try {
			byte[] originalData = rc4.doFinal(encryptedData);
			message=new String(Base64.decodeBase64(originalData), charset);
			return message;
			//return Bytes2HexString(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "";
	}
	
	static String Bytes2HexString(byte[] b) {
	    String ret = "";
	    for (int i = 0; i < b.length; i++) {
	      String hex = Integer.toHexString(b[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      ret += hex.toUpperCase();
	    }
	    return ret;
	}    
	
	public static byte uniteBytes(byte src0, byte src1) {  
	    byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();  
	    _b0 = (byte)(_b0 << 4);  
	    byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();  
	    byte ret = (byte)(_b0 ^ _b1);  
	    return ret;  
	    }   
	public static byte[] HexString2Bytes(String src){  
        byte[] ret = new byte[src.length()/2];  
        byte[] tmp = src.getBytes();  
        for(int i=0; i< tmp.length/2; i++){  
          ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]);  
        }  
        return ret;  
      }  
	
	/**
	 * test rc4
	 */
	public static void testrc4(){
		String rc4result=base64EncodeRC4EncryptToHexString("中文", "odchina");
		System.out.println(rc4result);
		System.out.println(toBytesRc4DecryptBase64Decode(rc4result,"odchina"));
	}
	
	public static void main(String[] args) {
		testrc4();
	}
	
}
