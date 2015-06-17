package com.netbull.shop.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.netbull.shop.common.util.RequestUtil;

public class UrlFilter implements Filter {

	private static final Logger logger = Logger.getLogger(UrlFilter.class);


	private String whiteIP = null;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String url = request.getRequestURL().toString();
		this.logger.info("url="+url);
		
		if(url.indexOf("http://person:80")>=0){
			this.logger.info("不过滤，直接跳转到目标地址...");
			chain.doFilter(req, resp);
			return;
		}
		
		String isProduct = "N";//SystemConfig.getValue("isProduct").toUpperCase();
		//如果是生产环境，就不做判断；
		if(isProduct!= null && "Y".equalsIgnoreCase(isProduct)){
			this.logger.info("生产环境，不做跳转...");
			chain.doFilter(req, resp);
			return;
		}
		
		//获取请求的IP；
		String ip = RequestUtil.getIp(request);
		this.logger.info("ip="+ip);
		
		if(null == ip){
			this.logger.info("没有获取到正确的IP地址，不进行跳转...");
			chain.doFilter(req, resp);
			return;
		}
		
		//如果是F5的探测地址，则不做过滤跳转 
		if(ip.equals("192.168.0.22")||ip.equals("192.168.0.21")){
			this.logger.info("F5请求，不做跳转...");
			chain.doFilter(req, resp);
			return;
		}

		String subIp = ip.substring(0,ip.lastIndexOf("."));
		String servletUrl = request.getServletPath();
		
		
		if(whiteIP.indexOf(subIp)>=0){
			this.logger.info("允许开放IP段访问测试环境，不进行跳转...");
			chain.doFilter(req, resp);
			return;
		}else{
			this.logger.info("地址为"+ip+"不是测试库开放的ip地址，直接跳转到生产环境:"+"http://ah.189.cn"+servletUrl);
			response.sendRedirect("http://ah.189.cn"+servletUrl);
			return;
		}
	}

	
	public String getWhiteIP() {
		return whiteIP;
	}

	public void setWhiteIP(String whiteIP) {
		this.whiteIP = whiteIP;
	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		whiteIP = config.getInitParameter("whiteIP");
	}
	
	public static void main(String[] args) {
		System.out.println("134.64.36.*".indexOf("134.64.36")>=0);
	}

}
