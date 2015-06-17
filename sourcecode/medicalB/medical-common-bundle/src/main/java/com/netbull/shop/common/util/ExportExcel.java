package com.netbull.shop.common.util;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.netbull.shop.common.log.LoggerFactory;

public class ExportExcel extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private static Logger logger = LoggerFactory.getLogger(ExportExcel.class);
	
	public ExportExcel() {
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
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException{

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ response.encodeURL("exportExcel.xls"));
		
		 
		 List<Map> detailListExc = new ArrayList();
		 
		 Map map1 = new HashMap();
		 map1.put("guzx1", "顾正贤1");
		 map1.put("guzx2", "顾正贤22");
		 map1.put("guzx3", "顾正贤33");
		 map1.put("guzx4", "顾正贤44");
		 
		 
		 detailListExc.add(map1);
		 
//		 List<String> keys = tools.getItemsKeyList("1");
//		 List<String> items = tools.getItemsList("1");
		 
		 HSSFWorkbook wb  = null;
		 
//		 wb = tools.excelExport(detailListExc, items, keys, "测试导出");
		 
		 OutputStream out;
			out = response.getOutputStream();
			if(wb != null) {
				wb.write(out);
			}
			out.close();
		
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

	private Color getRandColor(int fc,int bc){
         Random random = new Random();
         if(fc>250) fc=255;
         if(bc>250) bc=255;
         int r=fc+random.nextInt(bc-fc);
         int g=fc+random.nextInt(bc-fc);
         int b=fc+random.nextInt(bc-fc);
         return new Color(r,g,b);
      }
}
