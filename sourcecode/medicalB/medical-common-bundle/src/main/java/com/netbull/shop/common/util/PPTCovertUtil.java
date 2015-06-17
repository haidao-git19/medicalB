package com.netbull.shop.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public class PPTCovertUtil {
	
	private static final Logger logger = Logger.getLogger("microLog");
	
	final static String UNOCONV_PROG = "unoconv";
	final static String UNOCON_OPTS = "-f pdf";
	//final static String OPENOFFICE_CONV_PROG = "/opt/openoffice.org3/program/python /opt/openoffice.org3/program/DocumentConverter.py";
	final static String OPENOFFICE_CONV_PROG = "python /usr/bin/DocumentConverter.py";
	final static String CONVERT_PROG = "convert";
	final static String IM_OPTS = "-density 300x300";
	
	final static String POWERPOINT_FILE_EXTENSION_1 = "ppt";
	final static String POWERPOINT_FILE_EXTENSION_2 = "pptx";
	final static String PDF_FILE_EXTENSION = "PDF";
	final static String IMAGE_DEFAULT_NAME = "slide";
	final static String IMAGE_TYPE = ".jpg";
	
	public final static int BAD_FILE_COUNT = -1;
	public final static int GOOD_FILE_COUNT = 1;
	
	public static int converPpt2Img(String fileName, int width, int height,
			int quality) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("---------------------------------------------------");
			logger.debug("enter the PPTCovertUtil class converPpt2Img method.");
		}

		String inputFileExt = FilenameUtils.getExtension(fileName);
		
		if (logger.isDebugEnabled()) {
			logger.debug("inputFileExt=" + inputFileExt);
		}
		
		if (!(inputFileExt.equalsIgnoreCase(POWERPOINT_FILE_EXTENSION_1)
				|| inputFileExt.equalsIgnoreCase(POWERPOINT_FILE_EXTENSION_2) || inputFileExt
				.equalsIgnoreCase(PDF_FILE_EXTENSION))) {
			return BAD_FILE_COUNT;
		}
		
		
		String pdfFile = null;
		if (!inputFileExt.equalsIgnoreCase(PDF_FILE_EXTENSION)) {
			if (convertPpt2Pdf(fileName) != true) {
				
				if (logger.isDebugEnabled()) {
					logger.debug("* ********convertPpt2Pdf " + fileName	+ " failed");
				}
				
				return BAD_FILE_COUNT;
			} else {
				
				if (logger.isDebugEnabled()) {
					logger.debug("* ********convertPpt2Pdf " + fileName	+ " succeed");
				}
				
			}
			if (inputFileExt.equalsIgnoreCase(POWERPOINT_FILE_EXTENSION_1)) {
				pdfFile = fileName.replaceAll("\\.ppt", ".pdf");
			} else if (inputFileExt
					.equalsIgnoreCase(POWERPOINT_FILE_EXTENSION_2)) {
				pdfFile = fileName.replaceAll("\\.pptx", ".pdf");
			}
		} else {
			pdfFile = fileName;
		}
		int lastIndex = fileName.lastIndexOf(".");
		String outputPathStr = fileName.substring(0, lastIndex);
		File inFile = new File(pdfFile);
		if (!convertPdf2Img(inFile, width, height, quality, outputPathStr)) {
			
			if (logger.isDebugEnabled()) {
				logger.debug("*  ****convertPdf2Img " + pdfFile + " failed");
			}
			
			return BAD_FILE_COUNT;
		} else {
			
			if (logger.isDebugEnabled()) {
				logger.debug("* ********convertPdf2Img " + pdfFile + " succeed");
			}
			
		}
		File fl = new File(outputPathStr);
		String[] files = fl.list();
		File f = null;
		String filename = "";
		int filesnum = files.length - 2;
		for (String file : files) {
			f = new File(fl, file);
			filename = f.getName();
			if (filename.startsWith("slide-") && filename.endsWith(".jpg")) {
				if (filesnum > 10 && filename.length() == 11) {
					filename = filename.replace("slide-", "slide-0");
				} else if (filesnum > 100) {
					if (filename.length() == 11) {
						filename = filename.replace("slide-", "slide-00");
					} else if (filename.length() == 12) {
						filename = filename.replace("slide-", "slide-0");
					}
				}
				f.renameTo(new File(fl.getAbsolutePath() + "/" + filename));
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("exit the PPTCovertUtil class converPpt2Img method.");
			logger.debug("---------------------------------------------------");
		}
		
		return GOOD_FILE_COUNT;
	}
	
	private static boolean convertPpt2Pdf(String inputFile) {
		String outputFile = inputFile.substring(0,inputFile.lastIndexOf(".")).concat(".pdf");
		ArrayList<String> command = new ArrayList<String>(10);
		command.add(OPENOFFICE_CONV_PROG);
		command.add(inputFile);
		command.add(outputFile);
		
		if (logger.isDebugEnabled()) {
			logger.debug("*  **********convertPpt2Pdf command=" + command + "**********");
		}
		
		return exec(command);
	}
	
	private static boolean exec(List<String> command) {
		StringBuilder sb = new StringBuilder();
		for (String str : command) {
//			sb.append(str + " ");
			sb.append(str.replaceAll(" ", "\\ ")+" ");
		}
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec(sb.toString());
		} catch (IOException e) {
			return false;
		}
		int exitStatus = 0;
		while (true) {
			try {
				exitStatus = proc.waitFor();
				break;
			} catch (java.lang.InterruptedException e) {
			}
		}
		if (exitStatus != 0) {
		}
		return (exitStatus == 0);
	}
	
	private static boolean convertPdf2Img(File in, int width, int height,
			int quality, String outputPathStr) {
		File outputPath = new File(outputPathStr);
		if (!outputPath.exists()) {
			outputPath.mkdir();
		}
		String imagePath = outputPathStr + File.separator + IMAGE_DEFAULT_NAME
				+ IMAGE_TYPE;
		if (quality < 0 || quality > 100) {
			quality = 75;
		}
		ArrayList<String> command = new ArrayList<String>(10);
		command.add(CONVERT_PROG);
		command.add(IM_OPTS);
		command.add("-resize");
		command.add(width + "x" + height + "!"); // force size
		command.add("-quality");
		command.add("" + quality);
		command.add(in.getAbsolutePath());
		command.add(imagePath);
		System.out.println("*  *********convertPdf2Img command=" + command
				+ "**********");
		return exec(command);
	}
}
