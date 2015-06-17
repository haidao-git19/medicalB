package com.netbull.shop.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadClient {
	// 字节数组头部长度
		private final int HeadLength = 8;

		// / <summary>
		// / 上传文件
		// / </summary>
		// / <param name="url">服务器url地址</param>
		// / <param name="dstFileName">保存在服务器端的文件名</param>
		// / <param name="srcFileName">要读取的本地文件名</param>
		public void upload(String url, String dstFileName, String srcFileName)
				throws Exception {
			byte[] buffer = fileToBytes(dstFileName, srcFileName);
			postBytes(url, buffer);
		}

		// / <summary>
		// / 上传文件
		// / </summary>
		// / <param name="url">服务器url地址</param>
		// / <param name="dstFileName">保存在服务器端的文件名</param>
		// / <param name="srcFileName">要读取的本地文件名</param>
		public void upload(String url, String dstFileName, byte[] bytes)
				throws Exception {
			byte[] buffer = fileToBytes(dstFileName, bytes);
			postBytes(url, buffer);
		}

		// / 上传文件
		// / <summary>
		// / 上传文件
		// / </summary>
		// / <param name="url">服务器端页面地址</param>
		// / <param name="buffer">要上传的字节数组</param>
		private void postBytes(String url, byte[] buffer) throws Exception {
			// HttpWebResponse response =null;
			HttpURLConnection connection = null;
			try {
				// HttpWebRequest request = null;
				// 创建一个HttpWebRequest对象
				URL _url = new URL(url);
//				connection = _url.openConnection();
				//HttpURLConnection conn=
				connection = (HttpURLConnection) _url.openConnection();
				connection.setRequestMethod("POST");
	            connection.setConnectTimeout(5000);//（单位：毫秒）
	            connection.setReadTimeout(5000);//（单位：毫秒）
	            //connection.setRequestProperty("Accept","image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-silverlight, */*");
	            connection.setRequestProperty("Content-type","multipart/form-data");
	            connection.setDoOutput(true);
	            
				OutputStream stream = connection.getOutputStream();
				stream.write(buffer,0,buffer.length);

				stream.flush();
				stream.close();
				
				InputStream input=connection.getInputStream();
				input.close();
			} catch (Exception err) {
				throw err;
			} finally {
			}

		}

		// / 将文件转换成字节数组
		// / <summary>
		// / 读取文件并转换成字节数组
		// / </summary>
		// / <param name="dstFileName">输出的文件名</param>
		// / <param name="srcFileName">要读取的文件名</param>
		// / <returns>字节数组</returns>
		private byte[] fileToBytes(String dstFileName, String srcFileName)
				throws Exception {
			InputStream stream = null;
			try {
				stream = new FileInputStream(srcFileName);

				byte[] bufferFileContent = streamToBytes(stream);
				byte[] bufferFileName = stringToBytes(dstFileName);
				byte[] bufferHead = getHead(bufferFileName);

				byte[] buffer = new byte[bufferHead.length + bufferFileName.length
						+ bufferFileContent.length];

				System.arraycopy(bufferHead, 0, buffer, 0, bufferHead.length);
				System.arraycopy(bufferFileName, 0, buffer, bufferHead.length,
						bufferFileName.length);
				System.arraycopy(bufferFileContent, 0, buffer, bufferHead.length
						+ bufferFileName.length, bufferFileContent.length);

				return buffer;
			} catch (Exception err) {
				throw err;
			} finally {
				if (stream != null) {
					stream.close();
					stream = null;
				}
			}
		}

		// / 将文件转换成字节数组
		// / <summary>
		// / 读取文件并转换成字节数组
		// / </summary>
		// / <param name="dstFileName">输出的文件名</param>
		// / <param name="srcFileName">要读取的文件名</param>
		// / <returns>字节数组</returns>
		private byte[] fileToBytes(String dstFileName, byte[] fileBytes)
				throws Exception {
			try {
				byte[] bufferFileContent = fileBytes;
				byte[] bufferFileName = stringToBytes(dstFileName);
				byte[] bufferHead = getHead(bufferFileName);

				byte[] buffer = new byte[bufferHead.length + bufferFileName.length
						+ bufferFileContent.length];

				System.arraycopy(bufferHead, 0, buffer, 0, bufferHead.length);
				System.arraycopy(bufferFileName, 0, buffer, bufferHead.length,
						bufferFileName.length);
				System.arraycopy(bufferFileContent, 0, buffer, bufferHead.length
						+ bufferFileName.length, bufferFileContent.length);

				return buffer;
			} catch (Exception err) {
				throw err;
			} finally {

			}
		}

		// / 获取字节数组长度并将对应的字符串转换成字节数组
		private byte[] getHead(byte[] bufferFileName) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < HeadLength / 2
					- String.valueOf(bufferFileName.length).length(); ++i) {
				str.append("0");
			}
			str.append(bufferFileName.length);

			return stringToBytes(str.toString());
		}

		// / 流转字节数组
		// / <summary>
		// / 流转字节数组
		// / </summary>
		// / <param name="stream">流</param>
		// / <returns>字节数组</returns>
		public byte[] streamToBytes(InputStream stream) throws IOException {
			byte[] bytes = new byte[stream.available()];
			int j = 0;
			int streamLength = (int) stream.available();// 流总长度
			int leaveLength = streamLength;// 流剩余长度
			int length = 200;// 每次读取长度

			while (true) {
				if (leaveLength < length) {
					length = leaveLength;
				}

				byte[] tempBytes = new byte[length];

				int read = stream.read(tempBytes, 0, tempBytes.length);

				if (read > 0) {
					leaveLength = leaveLength - length;

					System.arraycopy(tempBytes, 0, bytes, j, tempBytes.length);// 拷贝

					j += tempBytes.length;
				} else {
					break;
				}
			}
			stream.close();

			return bytes;
		}

		// / 字符串转换成字节数组
		private byte[] stringToBytes(String str) {
			byte[] buffer = null;
			try {
				buffer = str.getBytes("UTF-16LE");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Encoding.Unicode.GetBytes(str);
			return buffer;
		}
}
