package com.netbull.shop.auth;

import java.io.File;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import com.netbull.shop.common.dbutil.JettyJndiEvnLoad;

public class RunJetty {
	public static void main(String[] args) throws Exception {
		
		//绑定dataSource到JNDI;
		JettyJndiEvnLoad.bindDataSource("jdbc:mysql://180.153.44.172:3306/vauth?useUnicode=true&characterEncoding=UTF-8","yiliao","yiliao1707");
	    Server server = new Server(8091); //也可以改成其它端口
	    File rootDir = new File(RunJetty.class.getResource("/").getPath()).getParentFile().getParentFile();

	    String webAppPath = new File(rootDir, "src/main/webapp").getPath();
	    new WebAppContext(server, webAppPath, "/sso");
	    server.start();
	}
 
}