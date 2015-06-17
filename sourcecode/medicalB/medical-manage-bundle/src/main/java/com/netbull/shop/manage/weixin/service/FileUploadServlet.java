package com.netbull.shop.manage.weixin.service;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netbull.shop.manage.common.util.Uploader;

public class FileUploadServlet extends GenericServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		try {
			 request.setCharacterEncoding("utf-8");
			 response.setCharacterEncoding("utf-8");
			 Uploader up = new Uploader(request);
			 up.setSavePath("");
			 String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
			 up.setAllowFiles(fileType);
			 up.setMaxSize(10000); //单位KB设置文件上传的大小；
			 up.upload();
			 response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
		
	}

}
