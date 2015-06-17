package com.netbull.shop.common.activeload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 可自动加载属性变化的属性类;
 * @author elvis
 */
public class PropertiesEx {

	/**  
     *   
     */
	private static final long serialVersionUID = -6708397622206255544L;
	private static final Logger logger = Logger.getLogger("microLog");
	
	private MonitorThread monitor;
	private Properties p;

	/**
	 * 
	 * @param intervalSecond
	 * 监听变化间隔
	 */
	public PropertiesEx(long intervalSecond) {
		monitor = MonitorThread.getInstance(intervalSecond);
	}

	/**
	 * 默认更新间隔为2分钟；
	 */
	public PropertiesEx() {
		this(2*60*1000);
	}

	/**
	 * 加载配置文件
	 * 
	 * @param f
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void load(File f) throws FileNotFoundException, IOException {
		p = new Properties();
		p.load(new FileInputStream(f));
		
		MonitorEvent event = new FileChangeMonitorEvent(f);
		ReloadPropertiesListener listener = new ReloadPropertiesListener();
		event.addListener(listener);
		monitor.addEvent(event);
		if (!monitor.isMonitor()) {
			monitor.start();
		}

	}

	public String getProperty(String key) {
		return p.getProperty(key);
	}

	public Properties getProperties() {
		return p;
	}

	/**
	 * 当发生属性文件改变时重新加载属性文件<br>
	 * listener为内部类，为了访问包含类中p成员变量和静止外部访问该类
	 * 
	 */
	private class ReloadPropertiesListener implements IMonitorListener {

		public void update(MonitorEvent event) {
			FileChangeMonitorEvent fcmEvent = (FileChangeMonitorEvent) event;
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("检查到属性文件发生改变，开始重新加载属性文件！");
				}
				p.load(new FileInputStream(fcmEvent.getF()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}
}
