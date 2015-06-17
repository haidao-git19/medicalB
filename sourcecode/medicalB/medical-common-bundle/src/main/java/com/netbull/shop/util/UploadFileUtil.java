package com.netbull.shop.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Decoder;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;

/**
 * 
 * @author guzx
 *
 */
@Component
public class UploadFileUtil {
	private static final Log logger = LogFactory.getLog(UploadFileUtil.class);
	
	public String createFile(CommonsMultipartFile file,String fileName){
		if(null!=file){
	        String originalFilename = file.getOriginalFilename();  
	
	        String suffix = !NullUtil.isNull(originalFilename)?originalFilename.substring(originalFilename.lastIndexOf(".")):null;
	        if(NullUtil.isNull(fileName)){
	        	fileName = StringTools.getBillno()+suffix;
			}else{
				fileName = fileName+suffix;
			}
	        logger.info("文件原名: " + originalFilename);  
	        logger.info("文件名称: " + fileName);  
	        logger.info("文件长度: " + file.getSize());  
	        logger.info("文件类型: " + file.getContentType());
	        String realPath = ConfigLoadUtil.loadConfig().getPropertie("materialUploadPath");
	        logger.info("文件类型: " + realPath);
	        try {  
	        	FileUtils.copyInputStreamToFile(new ByteArrayInputStream(file.getBytes()),  new File(realPath, fileName));
	            return fileName;
	        } catch (IOException e) {  
	            logger.info("文件[" + file.getName() + "]上传失败,堆栈轨迹如下");  
	            logger.error("1`文件上传失败，请重试！！",e);  
	        } catch (Exception e) {
	        	 logger.info("文件[" + file.getName() + "]上传失败,堆栈轨迹如下");  
	             logger.error("1`文件上传失败，请重试！！",e);  
			} 
		}
        return "";
	}
	
	public String createFile(String picStr,String fileName) throws IOException{
		if(NullUtil.isNull(fileName)){
        	fileName = StringTools.getBillnoFor10();
		}
		
		OutputStream out = null;
		if (picStr != null){
			try {
				// Base64解码
				byte[] b = new BASE64Decoder().decodeBuffer(picStr);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				String realPath = ConfigLoadUtil.loadConfig().getPropertie("materialUploadPath");
				String imgFilePath = realPath +File.separator + fileName + ".jpg";// 新生成的图片
				out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
			} catch (Exception e) {
				logger.error("1`文件上传失败，请重试！！",e);  
			}finally{
				out.close();
			}
		}
		return fileName;
	}
	
}
