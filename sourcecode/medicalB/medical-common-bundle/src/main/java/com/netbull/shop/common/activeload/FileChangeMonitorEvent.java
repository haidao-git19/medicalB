package com.netbull.shop.common.activeload;

import java.io.File;
import java.io.IOException;

/**
 * 文件改变监听类
 * @author elvis
 * 
 */
public class FileChangeMonitorEvent extends MonitorEvent {

	private File f;
	private long lastModifiedTime;
	private long lastLength;
	private boolean isChanged = false;
	private static boolean isOpen = true;

	/**
	 * 
	 * @param f
	 * 需要监听的文件
	 */
	public FileChangeMonitorEvent(File f) {
		if (!f.exists()) {
			try {
				throw new IllegalArgumentException("Path "
						+ f.getCanonicalPath() + " dose't exist.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.f = f;
		getFileInfo();
	}

	private void getFileInfo() {
		lastModifiedTime = f.lastModified();
		lastLength = f.length();
	}

	/**
	 * 如果文件已改变则触发事件
	 */
	@Override
	public void fireEvent() {
		try {
			f = f.getCanonicalFile();
			isChanged = lastModifiedTime != f.lastModified()
					|| lastLength != f.length();
			if (isChanged || isOpen) {
				super.fireEvent();
				getFileInfo();
				isChanged = false;
				isOpen = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得监听的文件
	 * 
	 * @return
	 */
	public File getF() {
		return f;
	}
}
