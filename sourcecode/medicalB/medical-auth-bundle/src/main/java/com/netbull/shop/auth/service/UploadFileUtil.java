package com.netbull.shop.auth.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.util.PropertiesUtils;

/**
 * 
 * @author luye
 *
 */
@Component
public class UploadFileUtil {
	private static final Log logger = LogFactory.getLog(UploadFileUtil.class);
	
	public String createFile(CommonsMultipartFile file){
		if(null!=file){
        String originalFilename = file.getOriginalFilename();  

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + "." + suffix;
        logger.info("文件原名: " + originalFilename);  
        logger.info("文件名称: " + fileName);  
        logger.info("文件长度: " + file.getSize());  
        logger.info("文件类型: " + file.getContentType());
        String realPath = PropertiesUtils.getProperty("upload.url");
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
}
