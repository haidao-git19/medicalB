package com.netbull.shop.common.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ResourceUploadServlet extends GenericServlet implements Servlet {

	private String uploadPath = "/home/bill/mboss-cluster/resource-ngx-1.3.5/html/upload"; // 上传文件的目录
	private String tempPath = "/home/bill/mboss-cluster/resource-ngx-1.3.5/html/upload"; // 临时文件目录
	
//	private String uploadPath = "F:\\Image"; // 上传文件的目录
//	private String tempPath = "F:\\Image"; // 临时文件目录
	
	File tempPathFile;

	public void init() throws ServletException {
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String _fileName = request.getParameter("fileName");
		
		try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Set factory constraints
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			factory.setRepository(tempPathFile);// 设置缓冲区目录
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			Iterator<FileItem> i = items.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fileName = fi.getName();
				if (fileName != null) {
					File fullFile = new File(fi.getFieldName());
					File savedFile = new File(uploadPath, fullFile.getName());
					System.out.println(savedFile);
					fi.write(savedFile);
				}
			}
			System.out.print("upload succeed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// 可以跳转出错页面
			e.printStackTrace();
		}
	
		
	}

}
