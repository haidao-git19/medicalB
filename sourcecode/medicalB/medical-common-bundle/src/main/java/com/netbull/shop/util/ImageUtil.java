package com.netbull.shop.util;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {
	private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	public static BufferedImage scala(File src, FileOutputStream fos) {
		try {
			BufferedImage image = ImageIO.read(src);
			ImageIO.write(image, "jpg", fos); 
			return image;
		} catch (Exception e) {
			logger.error("scala error", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static void cutImage(InputStream src, OutputStream dest, int x, int y, int w, int h) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(src);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", dest);

	}
}
