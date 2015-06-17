package com.netbull.shop.common.log;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.FileAppender;
import com.netbull.shop.common.config.ServerInfo;

/**
 *
 */
public class SimpleFileAppender extends FileAppender {
	public SimpleFileAppender() {
	}

	public void setFile(String file) {
		String val = file.trim();
		File domain = new File(".");
		try {
			fileName = domain.getCanonicalPath() + ServerInfo.FILE_SEP + val;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
