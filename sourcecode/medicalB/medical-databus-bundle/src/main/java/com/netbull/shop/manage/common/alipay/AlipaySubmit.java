package com.netbull.shop.manage.common.alipay;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.XMLParser;
import com.netbull.shop.manage.common.alipay.httpClient.HttpProtocolHandler;
import com.netbull.shop.manage.common.alipay.httpClient.HttpRequest;
import com.netbull.shop.manage.common.alipay.httpClient.HttpResponse;
import com.netbull.shop.manage.common.alipay.httpClient.HttpResultType;

/* *
 *类名：AlipaySubmit
 *功能：支付宝各接口请求提交类
 *详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 */

public class AlipaySubmit {
    
	
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
    	String mysign = "";
        if(AlipayConfig.sign_type.equals("MD5") ) {
        	mysign = MD5.sign(prestr, AlipayConfig.key, AlipayConfig.input_charset);
        }
        if(AlipayConfig.sign_type.equals("0001") ){
        	mysign = RSA.sign(prestr, AlipayConfig.private_key, AlipayConfig.input_charset);
        }
        return mysign;
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        if(! sPara.get("service").equals("alipay.wap.trade.create.direct") && ! sPara.get("service").equals("alipay.wap.auth.authAndExecute")) {
        	sPara.put("sign_type", AlipayConfig.sign_type);
        }

        return sPara;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @paramALIPAY_GATEWAY_NEW 支付宝网关地址
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(String ALIPAY_GATEWAY_NEW, Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    
    /**
     * 建立请求，以表单HTML形式构造，带文件上传功能
     * @paramALIPAY_GATEWAY_NEW 支付宝网关地址
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @param strParaFileName 文件上传的参数名
     * @return 提交表单HTML文本
     */
    public static String buildRequest(String ALIPAY_GATEWAY_NEW, Map<String, String> sParaTemp, String strMethod, String strButtonName, String strParaFileName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }
        
        sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

        return sbHtml.toString();
    }
    
    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值
     * 如：buildRequest("", "",sParaTemp)
     * @paramALIPAY_GATEWAY_NEW 支付宝网关地址
     * @param strParaFileName 文件类型的参数名
     * @param strFilePath 文件路径
     * @param sParaTemp 请求参数数组
     * @return 支付宝处理结果
     * @throws Exception
     */
    public static String buildRequest(String ALIPAY_GATEWAY_NEW, String strParaFileName, String strFilePath,Map<String, String> sParaTemp) throws Exception {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(AlipayConfig.input_charset);

        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW+"_input_charset="+AlipayConfig.input_charset);

        HttpResponse response = httpProtocolHandler.execute(request,strParaFileName,strFilePath);
        if (response == null) {
            return null;
        }
        
        String strResult = response.getStringResult();

        return strResult;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }
    
    /**
     * 解析远程模拟提交后返回的信息，获得token
     * @param text 要解析的字符串
     * @return 解析结果
     * @throws Exception 
     */
    public static Map<String, String> getRequestToken(String text) throws Exception {
    	String request_token = "";
    	//以“&”字符切割字符串
    	String[] strSplitText = text.split("&");
    	//把切割后的字符串数组变成变量与数值组合的字典数组
    	Map<String, String> paraText = new HashMap<String, String>();
        for (int i = 0; i < strSplitText.length; i++) {

    		//获得第一个=字符的位置
        	int nPos = strSplitText[i].indexOf("=");
        	//获得字符串长度
    		int nLen = strSplitText[i].length();
    		//获得变量名
    		String strKey = strSplitText[i].substring(0, nPos);
    		//获得数值
    		String strValue = strSplitText[i].substring(nPos+1,nLen);
    		//放入MAP类中
    		paraText.put(strKey, strValue);
        }

    	if (paraText.get("res_data") != null) {
    		String res_data = paraText.get("res_data");
    		//解析加密部分字符串（RSA与MD5区别仅此一句）
    		if(AlipayConfig.sign_type.equals("0001")) {
    			res_data = RSA.decrypt(res_data, AlipayConfig.private_key, AlipayConfig.input_charset);
    		}
    		//token从res_data中解析出来（也就是说res_data中已经包含token的内容）
    		Document document = DocumentHelper.parseText(res_data);
    		request_token = document.selectSingleNode( "//direct_trade_create_res/request_token" ).getText();
    		paraText.put("request_token", request_token);
    	}
    	if (paraText.get("res_error") != null) {
    		String res_error = paraText.get("res_error");
    		//解析加密部分字符串（RSA与MD5区别仅此一句）
    		//token从res_data中解析出来（也就是说res_data中已经包含token的内容）
    		Document document = DocumentHelper.parseText(res_error);
    		String sub_code = document.selectSingleNode( "//err/sub_code" ).getText();
    		String msg = document.selectSingleNode("//err/msg" ).getText();
    		String code = document.selectSingleNode("//err/code" ).getText();
    		String detail = document.selectSingleNode("//err/detail" ).getText();
    		paraText.put("code", code);
    		paraText.put("sub_code", sub_code);
    		paraText.put("msg", msg);
    		paraText.put("detail", detail);
    	}
    	return paraText;
    }
    
    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
	public static String query_timestamp() throws MalformedURLException,
                                                        DocumentException, IOException {

        //构造访问query_timestamp接口的URL串
        String strUrl = "https://mapi.alipay.com/gateway.do?service=query_timestamp&partner=" + AlipayConfig.partner;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }
	
	 /**
     * 解析远程模拟提交后返回的信息，获得token
     * @param text 要解析的字符串
     * @return 解析结果
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getNotifyData(Map<String,String> params) throws Exception {
    	//XML解析notify_data数据
		Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
		if(NullUtil.isNull(doc_notify_data)){
			return null;
		}
		
		String payment_type = doc_notify_data.selectSingleNode( "//notify/payment_type" ).getText();
		String subject = doc_notify_data.selectSingleNode( "//notify/subject" ).getText();
		String trade_no = doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();
		String buyer_email = doc_notify_data.selectSingleNode( "//notify/buyer_email" ).getText();
		String gmt_create = doc_notify_data.selectSingleNode( "//notify/gmt_create" ).getText();
		String notify_type = doc_notify_data.selectSingleNode( "//notify/notify_type" ).getText();
		String quantity = doc_notify_data.selectSingleNode( "//notify/quantity" ).getText();
		String out_trade_no = doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();
		String notify_time = doc_notify_data.selectSingleNode( "//notify/notify_time" ).getText();
		String seller_id = doc_notify_data.selectSingleNode( "//notify/seller_id" ).getText();
		String trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();
		String is_total_fee_adjust = doc_notify_data.selectSingleNode( "//notify/is_total_fee_adjust" ).getText();
		String total_fee = doc_notify_data.selectSingleNode( "//notify/total_fee" ).getText();
		String gmt_payment = doc_notify_data.selectSingleNode( "//notify/gmt_payment" ).getText();
		String seller_email = doc_notify_data.selectSingleNode( "//notify/seller_email" ).getText();
		String price = doc_notify_data.selectSingleNode( "//notify/price" ).getText();
		String buyer_id = doc_notify_data.selectSingleNode( "//notify/buyer_id" ).getText();
		String notify_id = doc_notify_data.selectSingleNode( "//notify/notify_id" ).getText();
		String use_coupon = doc_notify_data.selectSingleNode( "//notify/use_coupon" ).getText();
		
		Map resultMap = new HashMap();
		resultMap.put("payment_type", payment_type);
		resultMap.put("subject", subject);
		resultMap.put("trade_no", trade_no);
		resultMap.put("buyer_email", buyer_email);
		resultMap.put("gmt_create", gmt_create);
		resultMap.put("notify_type", notify_type);
		resultMap.put("quantity", quantity);
		resultMap.put("out_trade_no", out_trade_no);
		resultMap.put("notify_time", notify_time);
		resultMap.put("seller_id", seller_id);
		resultMap.put("trade_status", trade_status);
		resultMap.put("is_total_fee_adjust", is_total_fee_adjust);
		resultMap.put("total_fee", total_fee);
		resultMap.put("gmt_payment", gmt_payment);
		resultMap.put("seller_email", seller_email);
		resultMap.put("price", price);
		resultMap.put("buyer_id", buyer_id);
		resultMap.put("notify_id", notify_id);
		resultMap.put("use_coupon", use_coupon);
    	return resultMap;
    }
}
