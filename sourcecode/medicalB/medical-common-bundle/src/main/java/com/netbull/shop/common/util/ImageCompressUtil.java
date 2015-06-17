package com.netbull.shop.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Component
public class ImageCompressUtil {

	private static ImageCompressUtil icu = null;

	public static synchronized ImageCompressUtil getInstance() {
		if (icu == null) {
			icu = new ImageCompressUtil();
		}
		return icu;
	}

	// 图片处理
	public String compressPic(String inputPath, String outputPath,
			String inputFileName, String outputFileName, int width, int height,
			boolean proportion) {
		File file = null;
		try {
			// 获得源文件
			file = new File(inputPath + File.separator + inputFileName);
			if (!file.exists()) {
				return "图片文件没找到";
			}
			Image img = ImageIO.read(file);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
				return "文件格式不正确";
			} else {
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) width + 0.1;
					double rate2 = ((double) img.getHeight(null))
							/ (double) height + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = width; // 输出的图片宽度
					newHeight = height; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);
				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
				tag.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(outputPath
						+ File.separator + outputFileName);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
		return ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>小图片生成成功>>>>>>>>>>>>>>>>>>>>>>";
	}
	
//	public static void main(String[] args) {
//		String inputPath="E:\\nginx-1.7.4\\resources\\upload";
//		String fileName="fox";
//		ImageCompressUtil.getInstance().compressPic(inputPath, inputPath, fileName+".jpg", fileName+"_l.jpg", 88, 88, false);
//	}
}
