package com.netbull.shop.common.dbutil;

import javax.naming.InitialContext;

import org.mortbay.naming.NamingUtil;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JettyJndiEvnLoad {
	
	
	public static void bindDataSource(String url,String user,String password) 

	{ 
		   DriverManagerDataSource dataSource = new DriverManagerDataSource();          
		   dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
		   dataSource.setUrl(url); 
		   dataSource.setUsername(user); 
		   dataSource.setPassword(password);
		   javax.naming.Context context; 
		
		   try { 
		          context = new InitialContext(); 
		          javax.naming.Context compCtx = (javax.naming.Context) context.lookup("java:comp");     
		          javax.naming.Context envCtx = compCtx.createSubcontext("env"); 
		          NamingUtil.bind(envCtx,"jdbc/micro", dataSource); 
		        } 
		   catch (Exception e) 
		   { 
			   e.printStackTrace();
		   } 
	}
	
	 /** * 
	  * 
	  * 绑定datasource到JNDI
	  * @author elvis
	  **/ 
	public static void bindDataSource() 

	{ 
		 /*  DriverManagerDataSource dataSource = new DriverManagerDataSource();          
		   dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver"); 
		   dataSource.setUrl("jdbc:oracle:thin:@180.97.81.219:1521:orcl"); 
		   //dataSource.setUrl("jdbc:oracle:thin:@220.178.7.178:1521:EMS"); 
		   //dataSource.setUrl("jdbc:oracle:thin:@192.168.2.251:1521:EMS");
		   dataSource.setUsername("sms"); 
		   dataSource.setPassword("sms"); 
		   javax.naming.Context context;*/
		
		  /* DriverManagerDataSource dataSource = new DriverManagerDataSource();          
		   dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver"); 
		   dataSource.setUrl("jdbc:oracle:thin:@61.190.35.50:1521:ems"); 
		   dataSource.setUsername("ems"); 
		   dataSource.setPassword("ems"); 
		   javax.naming.Context context;*/
		   
		   DriverManagerDataSource dataSource = new DriverManagerDataSource();          
		   dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
		   dataSource.setUrl("jdbc:mysql://180.97.81.219:3306/vmanage_test?useUnicode=true&characterEncoding=UTF-8"); 
		   dataSource.setUsername("root"); 
		   dataSource.setPassword("wzt360!@#123");
		   javax.naming.Context context; 
		
		/*   DriverManagerDataSource dataSource = new DriverManagerDataSource();          
		   dataSource.setDriverClassName("com.mysql.jdbc.Driver"); 
		   dataSource.setUrl("jdbc:mysql://61.190.35.53:3306/ems?useUnicode=true&characterEncoding=UTF-8"); 
		   dataSource.setUsername("chenyb"); 
		   dataSource.setPassword("ceb8795");
		   javax.naming.Context context; */

		   try { 
		          context = new InitialContext(); 
		          javax.naming.Context compCtx = (javax.naming.Context) context.lookup("java:comp");     
		          javax.naming.Context envCtx = compCtx.createSubcontext("env"); 
		          NamingUtil.bind(envCtx,"jdbc/micro", dataSource); 
		        } 
		   catch (Exception e) 
		   { 
			   e.printStackTrace();
		   } 
	}
}
