package com.netbull.shop.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.util.StringUtils;

import com.netbull.shop.util.PropertiesUtils;

public class MsgSender {
    /**ip*/
    private String ip;
    
    /**端口号*/
    private int port;

    /**接口调用协议格式*/
    private String protocolFormat;
    
    public MsgSender() {
    	
       /* String ipStr = PropertiesUtils.getProperty("sms.ip");
        ipStr = StringUtils.trimAllWhitespace(ipStr);
        ip = ipStr;

        String portStr = PropertiesUtils.getProperty("sms.port");
        portStr= StringUtils.trimAllWhitespace(portStr);
        port = Integer.parseInt(portStr);*/
        
        protocolFormat = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><atpacket domain=\"web\" type=\"event\"><cmd id=\"send_sm\" node=\"192.168.27.217\"><para name=\"appid\" value='%s'/><para name=\"src\" value='13812121212'/><para name=\"dst\" value='%s'/><para name=\"context\" value='%s[ossp]'/></cmd></atpacket>";
    
    }
    
    /**
     * 发送短信的方法
     * 
     * @param telNum
     *            用户号码
     * @param content
     *            短信内容
     * @param encode
     *            编码方式
     * @param timeout
     *            Socket超时时间，毫秒为单位
     */
    public void sendMsg(String telNum, String content, String encode, int timeout) {
    	
        Socket socket = null;
        OutputStream out = null;
        
        try {
            String msg = String.format(protocolFormat, "100IME", telNum, content);
            byte[] data = msg.getBytes(encode);
            String length = String.format("0x%08x", data.length);
            byte[] header = length.getBytes(encode);
            
            socket = new Socket(ip, port);
            socket.setSoTimeout(timeout);
            
            out = socket.getOutputStream();
            out.write(header);
            out.write(data);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMsgFormat() {
        return protocolFormat;
    }

    public void setProtocolFormat(String protocolFormat) {
        this.protocolFormat = protocolFormat;
    }
    
}

