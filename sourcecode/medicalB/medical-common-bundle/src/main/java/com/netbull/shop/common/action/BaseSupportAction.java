package com.netbull.shop.common.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseSupportAction{

    private static final long serialVersionUID = -3255958138082860035L;

    /**
     * 获得当前请求的URL(带会话ID)
     * 
     * @throws UnsupportedEncodingException
     */
    public String getFullUrl(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        String file = request.getRequestURI();
        String url = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + file;
        url = response.encodeURL(url);

        return url;
    }

    /**
     * 
     * sendMes(组装删除是发送信息) (这里描述这个方法适用条件 – 可选)
     * 
     * @param state
     * @param msg
     * @return
     * @throws Exception String
     * @exception
     * @since 1.0.0
     */
    public String sendMessages(HttpServletResponse response, String json) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
        return null;
    }

}
