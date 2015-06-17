package com.netbull.shop.auth.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.util.PropertiesUtils;
import com.netbull.shop.util.RequestUtils;
import com.netbull.shop.auth.service.LogService;
import com.netbull.shop.auth.service.MenuService;
import com.netbull.shop.auth.service.UploadFileUtil;
import com.netbull.shop.auth.service.UserService;

@Controller("menuController")
public class MenuController {

	@Resource
	private MenuService menuService;
	
	@Resource
	UploadFileUtil uploadFileUtil;
	
	@Resource
	private LogService logService;

	@RequestMapping("/menu")
	public String menu() {
		return "auth/menu/menu";
	}
	
	@RequestMapping("/bind")
	public String bind(ModelMap model,HttpServletRequest request) {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("url", requestMap.get("url"));
		return "auth/bind";
	}

	@RequestMapping("/menu/subMenus")
	@ResponseBody
	public List<MenuInfo> getSubMenus(Long parentId) {
		return menuService.getMenusByParent(parentId);
	}

	@RequestMapping("/menu/permitSubMenus")
	@ResponseBody
	public List<MenuInfo> permitSubMenus(Long parentId) {
		return menuService.getVisibleMenusByUserAndParent(
				UserService.getCurrentUserId(), parentId);
	}

	@RequestMapping("/menu/delete")
	@ResponseBody
	public void deleteMenu(Long id) {
		menuService.deleteMenu(id);
	}

	@RequestMapping("/menu/save")
	@ResponseBody
	public MenuInfo saveMenu(MenuInfo menu) {
		menuService.saveMenu(menu);
		logService.log("保存菜单: " + menu.getName());
		return menu;
	}

	@RequestMapping("/menu/move")
	@ResponseBody
	public void moveMenu(Long id, Long toId, Integer order) {
		MenuInfo menuInfo = menuService.getMenuById(id);
		menuService.moveMenu(id, toId, order);
		logService.log("移动菜单: " + menuInfo.getName());
	}

	@RequestMapping("/menu/copy")
	@ResponseBody
	public void copyMenu(Long id, Long toId, Integer order) {
		MenuInfo menuInfo = menuService.getMenuById(id);
		menuService.copyMenu(id, toId, order);
		logService.log("复制菜单: " + menuInfo.getName());
	}

	@RequestMapping("/menu/uploadIcon")
	@ResponseBody
	public String uploadIcon(MultipartFile file) throws IOException {
		String uploadUrl = PropertiesUtils.getProperty("upload.url");
		String downloadUrl = PropertiesUtils.getProperty("download.url");
		// 保存临时文件，并返回文件名称
		InputStream input = null;
		// 文件完整名称
		String fileExt = StringUtils.getFilenameExtension(file
				.getOriginalFilename());
		String fileName = UUID.randomUUID().toString() + "." + fileExt;
		try {
			FileUtils.copyInputStreamToFile(new ByteArrayInputStream(file.getBytes()),  new File(uploadUrl, fileName));
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			IOUtils.closeQuietly(input);
		}
		String url = downloadUrl + fileName;
		return url;
	}

}
