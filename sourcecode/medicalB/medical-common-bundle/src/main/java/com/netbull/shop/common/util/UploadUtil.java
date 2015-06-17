package com.netbull.shop.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.log.LoggerFactory;

public class UploadUtil {

	private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);
	private static String MATERIAL_UPLOAD_URL = "materialUploadUrl";
	public static final String DOWNLOAD_WEIXIN_IMAGE = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	private static UploadUtil smb = null; // 共享文件协议
	private static String url = null;

	// 3. 得到UploadUtil和连接的方法
	public static synchronized UploadUtil getInstance(String url) {
		if (smb == null)
			return new UploadUtil(url);
		return smb;
	}

	/**
	 * @param url服务器路径
	 */
	private UploadUtil(String url) {
		this.url = url;
	}

	public static void main(String[] args) {
		File targetFile = new File("F:\\Image\\Images\\302.jpg");
		// UploadUtil.getInstance("http://localhost:8080/resourcefileUpload").upload(targetFile);
	}

	/**
	 * 上传方法 返回上传完毕的文件名 *
	 */
	public void upload(File targetFile, String fileName) {
		// 服务器IP(这里是从属性文件中读取出来的)
		PostMethod filePost = null;
		try {

			url = ConfigLoadUtil.loadConfig().getPropertie(MATERIAL_UPLOAD_URL);
			filePost = new PostMethod(url);
			Part[] parts = { new FilePart(fileName, targetFile) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts,
					filePost.getParams()));
			/*
			 * if (null!=params&params.size()!=0) { //设置需要传递的参数，NameValuePair[]
			 * filePost.setRequestBody(buildNameValuePair(params)); }
			 */

			HttpClient client = new HttpClient();
			HttpConnectionManagerParams paramter = client
					.getHttpConnectionManager().getParams();
			paramter.setConnectionTimeout(5000);

			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				logger.info("上传成功");
			} else {
				logger.info("上传失败");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
	}

	public static InputStream getInputStream(String accessToken, String mediaId) {
		InputStream is = null;
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
				+ accessToken + "&media_id=" + mediaId;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			// 获取文件转化为byte流
			is = http.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	public static String saveImageToDisk(String accessToken,String mediaId) throws Exception {
		InputStream inputStream = getInputStream(accessToken,mediaId);
		byte[] data = new byte[1024];
		int len = 0;
		String fileName = null;
		FileOutputStream fileOutputStream = null;
		try {
			String savePath = ConfigLoadUtil.loadConfig().getPropertie("materialUploadPath");
			fileName = StringTools.getBillnoFor10();
			String filePath = savePath + File.separator + fileName + ".jpg";// 新生成的图片
			fileOutputStream = new FileOutputStream(new File(filePath));
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileName;
	}
}