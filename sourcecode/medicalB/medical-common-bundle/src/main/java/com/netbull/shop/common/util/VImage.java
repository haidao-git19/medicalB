package com.netbull.shop.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.netbull.shop.common.log.LoggerFactory;

public class VImage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private static Logger logger = LoggerFactory.getLogger(VImage.class);
	
	public VImage() {
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

		/*TokenProcessor tokemProcessor = TokenProcessor.getInstance();
		if (tokemProcessor.isTokenValid(req)) {
			tokemProcessor.reset(req);// 一开始这个忘记写了，差点放弃这个。哎，不仔细啊
			System.out.println("没有重复提交");
		} else {
			tokemProcessor.saveToken(req);
			System.out.println("有重复提交");
		}*/

		System.setProperty("java.awt.headless","true");
		
		response.setHeader("Pragma","No-cache");
	    response.setHeader("Cache-Control","no-cache");
	    response.setDateHeader("Expires", 0);
	    
	    String randomStr = req.getParameter("random");
		
	    // 在内存中创建图象
	    int width=60, height=20;
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	    Graphics g = image.getGraphics();

	    Random random = new Random();

	    g.setColor(getRandColor(230,250));
	    g.fillRect(0, 0, width, height);

	    g.setFont(new Font("Arial, Helvetica, sans-serif",Font.PLAIN,18));

	    //g.setColor(new Color());
	    //g.drawRect(0,0,width-1,height-1);


	    g.setColor(getRandColor(200,255));
	    for (int i=0;i<155;i++)
	    {
	     int x = random.nextInt(width);
	     int y = random.nextInt(height);
	     int xl = random.nextInt(12);
	     int yl = random.nextInt(12);
	     g.drawLine(x,y,x+xl,y+yl);
	    }

	    String sRand="";
	    
	    //存在客户端传过来的随机码
	    if((randomStr != null) && (!"".equals(randomStr.trim()))){
	    	sRand = randomStr.trim();
	    	 for (int i=0;i<4;i++){
			        g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			        g.drawString(randomStr.substring(i, i+1),13*i+6,16);
			    }
	    }else{
		    for (int i=0;i<4;i++){
		        String rand=String.valueOf(random.nextInt(10));
		        sRand+=rand;
		        g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
		        g.drawString(rand,13*i+6,16);
		    }
	    }
	    HttpSession session = req.getSession();
	    session.setAttribute("rand",sRand);


	    if (logger.isDebugEnabled()) {
			logger.debug("request validCode computer Ip: " + req.getRemoteAddr()+" validCode value:"+sRand);
		}
	    g.dispose();

	    ImageIO.write(image, "JPEG", response.getOutputStream());
		
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
