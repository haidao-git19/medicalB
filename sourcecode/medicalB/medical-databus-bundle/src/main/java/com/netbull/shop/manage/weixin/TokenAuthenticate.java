package com.netbull.shop.manage.weixin;
//package com.putdu.client.weixin;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.putdu.client.weixin.service.TokenAuthenticateService;
//
///**
// * 处理用户上行的多类型消息
// * 
// * @author yp
// * 
// */
//public class TokenAuthenticate extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	public TokenAuthenticate() {
//		super();
//	}
//
//	public void destroy() {
//		super.destroy();
//	}
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		this.doPost(request, response);
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/xml");
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//
//		/**
//		 * 加密/校验流程： 1. 将token、timestamp、nonce三个参数进行字典序排序 2.
//		 * 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
//		 */
//		String signature = request.getParameter("signature");
//		String timestamp = request.getParameter("timestamp");
//		String nonce = request.getParameter("nonce");
//		String echostr = request.getParameter("echostr");
//
//		// 获取HTTP请求的输入流
//		InputStream is = request.getInputStream();
//		StringBuilder sb = new StringBuilder();
//
//		String line;
//		try {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//			while ((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//		} finally {
//			is.close();
//		}
//		
//		TokenAuthenticateService tokenAuthenticateService = TokenAuthenticateService.getInstance();
//
//		String respXml = tokenAuthenticateService.responseXmlIntercept(sb.toString());
//		if (StringUtils.isEmpty(respXml)) {
//			out.println(echostr);
//		} else {
//			out.println(respXml);
//		}
//		out.flush();
//		out.close();
//	}
//
//	public void init() throws ServletException {
//
//	}
//
//}
