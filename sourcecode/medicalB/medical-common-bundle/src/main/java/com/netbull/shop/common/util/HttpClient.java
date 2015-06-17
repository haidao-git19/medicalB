package com.netbull.shop.common.util;

import java.io.IOException;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;


public class HttpClient {
	 private static Logger logger = Logger.getLogger(HttpClient.class);
	/*
	 * HttpClient发送请求
     * @param url 请求url
     * @param bodyContent 消息体
     * @return 服务器返回内容
     */
    @SuppressWarnings("deprecation")
    public static String httpSendMsg(String url, String bodyContent) throws IOException{
        PostMethod post=null;
        org.apache.commons.httpclient.HttpClient client=null;
        try{
            client = HttpClientFactory.getHttpClient();
            client.setConnectionTimeout(20*1000);
            post = new PostMethod(url);
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8"); 
            post.setRequestBody(bodyContent);
            long start = System.currentTimeMillis();
            logger.info("请求报文：" + bodyContent);
            client.executeMethod(post);
            String result = new String(post.getResponseBody(),"UTF-8");
            
            if(logger.isInfoEnabled()){
                logger.info("响应的编码是："+post.getResponseCharSet());
                long end = System.currentTimeMillis();
                logger.info("后端接口响应时间:" + (end-start) / 1000 + "秒");
                logger.info("响应结果:"+result);
            }
            return result;
        }finally{
            if(post!=null){
                post.releaseConnection();
                client=null;
            }
        }
    }
    
    public static void main(String[] args) {
		String url="https://api.weixin.qq.com/sns/oauth2/access_token";
		String bodyContent="appid=wx521b22f09a453984&secret=87332aa1cc6c8895c5b015ed9b3e2776&code=02b5de0e601a290b7566fee003cbc31b&grant_type=authorization_code";
		try {
			System.out.println(httpSendMsg(url,bodyContent));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
