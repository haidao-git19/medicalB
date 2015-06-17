package com.netbull.shop.common.activeload;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

/**
 * 可自动加载属性变化的属性类;
 * @author elvis
 */
public class Log4jPropertiesEx {

	/**  
     *   
     */
	private static final long serialVersionUID = -6708397622206255544L;

	/**
	 * 加载配置文件
	 * 
	 * @param f
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void load(File f) throws FileNotFoundException, IOException {
//		PropertyConfigurator.configure("d:/Eka-test/src/config/log4j.properties");
		PropertyConfigurator.configureAndWatch(f.getAbsolutePath(), 5000);
	}
	
}
