package com.netbull.shop.common.util;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.log4j.Logger;

import com.netbull.shop.common.log.LoggerFactory;


public class DSAUtil {
	private static Logger logger = LoggerFactory.getLogger(DSAUtil.class);

	/**
	 * @param sigText
	 *            明文数据
	 * @param KeyPassword
	 *            证书库密�?
	 * @param KeystoreAlias
	 *            证书库别�?
	 * @param KeyStorePath
	 *            证书库文件路�?
	 * @return
	 */
	public static byte[] sig(byte[] sigText, String KeyPassword,
			String KeystoreAlias, String KeyStorePath) {
		char[] kpass;
		int i;
		FileInputStream ksfis=null;
		BufferedInputStream ksbufin=null;
		
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			ksfis = new FileInputStream(KeyStorePath);
			ksbufin = new BufferedInputStream(ksfis);
			kpass = new char[KeyPassword.length()];
			for (i = 0; i < KeyPassword.length(); i++)
				kpass[i] = KeyPassword.charAt(i);
			ks.load(ksbufin, kpass);
			PrivateKey priv = (PrivateKey) ks.getKey(KeystoreAlias, kpass);

			// decrypt(privatekey, "crypt.dat");

			// System.out.println("verified " + test);
			Signature rsa = Signature.getInstance("SHA1withDSA");
			rsa.initSign(priv);
			rsa.update(sigText);
			byte[] sig = rsa.sign();
			// System.out.println("sig is done");
			return sig;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				ksfis.close();
				ksbufin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @param updateData
	 *            明文数据
	 * @param sigedText
	 *            签名后数�?
	 * @param CertPath
	 *            证书路径（公钥保存文件）
	 */
	public static boolean veriSig(byte[] updateData, byte[] sigedText,
			String CertPath) {
		boolean verifies=false;
		try {
			CertificateFactory certificatefactory = CertificateFactory
					.getInstance("X.509");
			FileInputStream fin = new FileInputStream(CertPath);
			X509Certificate certificate = (X509Certificate) certificatefactory
					.generateCertificate(fin);
			PublicKey pub = certificate.getPublicKey();
			Signature dsa = Signature.getInstance("SHA1withDSA");
			dsa.initVerify(pub);
			dsa.update(updateData);
			verifies = dsa.verify(sigedText);			
		} catch (Exception e) {
			logger.error("veriSig::",e);
		}
		return verifies;
	}

	public static void main(String[] args) {
		String KeyStorePath = "n:\\ca\\uaKeystore";
		String CertPath = "n:\\ca\\ua.cer";
		String sigedData = "souce data";
		System.out.println("修改前，明文内容�?" + sigedData);
		byte[] res = sig(sigedData.getBytes(), "11111111", "mbossUa",
				KeyStorePath);
		if (res != null) {
			String strRes = HexUtils.toHexString(res);
			System.out.println("对明文签名后，数据格式为:" + strRes);
			veriSig(sigedData.getBytes(), HexUtils.fromHexString(strRes),
					CertPath);

			// 明文修改�?
			sigedData = sigedData + "1";
			System.out.println("修改内容后，明文内容�?" + sigedData);
			veriSig(sigedData.getBytes(), HexUtils.fromHexString(strRes),
					CertPath);
		}

	}

}
