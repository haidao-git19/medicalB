package com.netbull.shop.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.netbull.shop.common.log.LoggerFactory;

public class DispetorServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger("microLog");
	
	private static String[] usualExtention = new String[] { ".jsp", ".class",
			".jpeg", ".css", ".swf", ".js", ".htm", ".html", ".gif", ".jpg",
			".png", ".db", ".MF", ".xml", ".flv", ".wsdd", ".tld", ".log ",
			".jar", ".bak", ".action", ".do", ".properties", ".mex", ".class",
			".wsdl ", ".jmx", ".ico" };
	
	private static Set<String> usualExtentionSet = new HashSet<String>();
	{
		CollectionUtils.addAll(usualExtentionSet, usualExtention);
	}
	
	public DispetorServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 * @throws WSSUtilException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String uri = request.getRequestURI();
	    
	    if (log.isDebugEnabled()) {
			log.debug("enter the FilterDispetor class doFilter method.");
			log.debug("uri is :"+uri);
		}
		
		String urlAftssh = uri.contains("/ssh") ? uri.substring(5) : uri
				.substring(1);
		
		if (log.isDebugEnabled()) {
			log.debug("urlAftssh is: "+urlAftssh);
		}
		

		if (urlAftssh.contains(".")) {
			int pos = urlAftssh.lastIndexOf(".");
			String ext = urlAftssh.substring(pos);
			
			if (log.isDebugEnabled()) {
				log.debug("ext is : " + ext);
			}
			
			if (!usualExtentionSet.contains(ext)) {
				// 是livecode
				if (log.isDebugEnabled()) {
					log.debug("the liveCode path is : " + "zhibo/" + urlAftssh+".do");
				}
				
				request.getRequestDispatcher("zhibo/" + urlAftssh).forward(
						request, response);
			} 
		} else if (urlAftssh.equals("daobo")) {
//			request.getRequestDispatcher("jsp/live/loginDirector.jsp").forward(
//					request, response);
			response.sendRedirect("jsp/live/loginDirector.jsp");
			return;
		} else {
			// 是livecode
			if (log.isDebugEnabled()) {
				log.debug("the liveCode path is : " + "zhibo/" + urlAftssh);
			}
			request.getRequestDispatcher("zhibo/" + urlAftssh+".do").forward(
					request, response);
		}
	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
