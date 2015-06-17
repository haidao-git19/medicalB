package com.netbull.shop.common.util;

import java.io.File;

import javax.xml.ws.Dispatch;

import org.apache.poi.hpsf.Variant;

public class PPTSaveAsImage {/*
	public static final int ppSaveAsJPG = 17;
	private ActiveXComponent ppt;
	private ActiveXComponent presentation;

	public void  JacobPptUtil(String filePath, boolean isVisble) throws Exception {
		if (null == filePath || "".equals(filePath)) {
			throw new Exception("文件路径为空!");
		}
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("文件不存在!");
		}
		ComThread.InitSTA();// 初始化com的线程，非常重要，否则第二次创建com对象的时候会出现can’t co-create
							// object异常
		ppt = new ActiveXComponent("PowerPoint.Application");
		setIsVisble(ppt, isVisble);
		// 打开一个现有的 Presentation 对象
		ActiveXComponent presentations = ppt
				.getPropertyAsComponent("Presentations");
		presentation = presentations.invokeGetComponent("Open", new Variant(filePath), new Variant(true));
	}

	*//**
	 * 将ppt转化为图片
	 * 
	 * @param pptfile
	 * @param saveToFolder
	 * @author liwx
	 *//*
	public void PPTToJPG(String pptfile, String saveToFolder) {
		try {
			saveAs(presentation, saveToFolder, ppSaveAsJPG);
			if (presentation != null) {
				Dispatch.call(presentation, "Close");
				presentation = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ComThread.Release();
		} finally {
			if (presentation != null) {
				Dispatch.call(presentation, "Close");
			}
			ppt.invoke("Quit", new Variant[] {});
			ComThread.Release(); // 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理
		}
	}

	*//**
	 * ppt另存为
	 * 
	 * @param presentation
	 * @param saveTo
	 * @param ppSaveAsFileType
	 * @date 2009-7-4
	 * @author YHY
	 *//*
	public void saveAs(Dispatch presentation, String saveTo,
			int ppSaveAsFileType) throws Exception {
		Dispatch.call(presentation, "SaveAs", saveTo, new Variant(ppSaveAsFileType));
	}

	*//**
	 * 设置是否可见
	 * 
	 * @param visble
	 * @param obj
	 *//*
	private void setIsVisble(Dispatch obj, boolean visble) throws Exception {
		Dispatch.put(obj, "Visible", new Variant(visble));
	}

*/}
