package com.netbull.shop.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**io util
 * 
 * @author leo
 *
 */
public class IOUtil {
	
	private static int BYTESIZE = 2048;
	
	private static String starsWithClassPath = "classpath:";

	private static String starsWithFilePath = "file:";
	
	private static final Log log = LogFactory.getLog(IOUtil.class);
	
	private IOUtil(){}
	
	/**
	 * 解析出path并返回File对象
	 * 
	 * @param path 带file或classpath的路径 
	 * @return 返回File对象
	 */
	public static File getFileWithPath(String path){ 
		if (path.startsWith(starsWithClassPath)) {// classpath
			log.warn("the file["+path+"] under the classpath,so cannot return a File object.");
		} else if (path.startsWith(starsWithFilePath)) {// file path
			path = path.substring(starsWithFilePath.length());
		}
		
		return new File(path);
	}
	
	
	/**
	 * 根据路获取文件流
	 * 
	 * @param path -
	 *            文件路径，带classpath:或file:
	 * @return 文件输入流
	 * @throws FileNotFoundException
	 */
	public static InputStream getInputStream(String path)
			throws FileNotFoundException {
		InputStream in = null;
		log.debug("@@@@ The path is:" + path + " @@@@");
		if (path.startsWith(starsWithClassPath)) {// classpath
			path = path.substring(starsWithClassPath.length());
			if(!path.startsWith("/")){
				path = "/"+path;
			}
			in = IOUtil.class.getResourceAsStream(path);// 从类路径加载配置文件
		} else if (path.startsWith(starsWithFilePath)) {// file path
			path = path.substring(starsWithFilePath.length());
			in = new FileInputStream(new File(path));
		}else{
			throw new FileNotFoundException("The path must be start with [classpath: or file:].");
		}

		return in;
	}
	
	/**
	 * 根据路径生成输出流<br/>
	 * 如果指定路径为classpath，那类路径下必须存在指定文件,否则将报出 NullPointerException<br/>
	 * 如果指定路径为file,那将自动输出到文件，不管存在与否
	 * @param path
	 * @return 文件输出流
	 * @throws FileNotFoundException 
	 */
	public static OutputStream getOutputStream(String path) throws FileNotFoundException{
		OutputStream out = null;
		log.debug("@@@@ The path is:" + path + " @@@@");
		if (path.startsWith(starsWithClassPath)) {// classpath
			path = path.substring(starsWithClassPath.length());
			if(!path.startsWith("/")){
				path = "/"+path;
			}
			out = new FileOutputStream(IOUtil.class.getResource(path).getPath());// 从类路径加载配置文件
		} else if (path.startsWith(starsWithFilePath)) {// file path
			path = path.substring(starsWithFilePath.length());
			out = new FileOutputStream(new File(path));
		}else{
			throw new FileNotFoundException("The path must be start with [classpath: or file:].");
		}

		return out;
	}
	
	/**
	 * 二进制文件流复制文件
	 * 
	 * @param srcFile 源文件
	 * @param tarterFile 目标文件 
	 * @return 复制成功与否
	 * @throws IOException
	 */
	public static Boolean copyFile(File srcFile,File tarterFile)
			throws IOException {
		Boolean flag = false;
		byte[] bytearray = new byte[BYTESIZE];
		int len = 0;
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(srcFile);
			output = new FileOutputStream(tarterFile);
			while ((len = input.read(bytearray)) != -1) {
				output.write(bytearray, 0, len);
			}
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}
}
