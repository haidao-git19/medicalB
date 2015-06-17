package com.netbull.shop.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.auth.entity.AreaInfo;
import com.netbull.shop.auth.service.AreaService;

@Controller("areaController")
public class AreaController {

	@Resource
	private AreaService areaService;

	@RequestMapping("/area/topAreas")
	public List<AreaInfo> getTopAreas() {
		return areaService.getAreasByLevel(AreaInfo.LEVEL_TOP);
	}

	@RequestMapping("/area/provinces")
	@ResponseBody
	public List<AreaInfo> getProvinces() {
		List<AreaInfo> list = areaService.getAreasByLevel(AreaInfo.LEVEL_COUNTRY);
		list.addAll(areaService.getAreasByLevel(AreaInfo.LEVEL_PROVINCE));
		return list;
		
	}

	@RequestMapping("/area/citys")
	@ResponseBody
	public List<AreaInfo> getCitys(Integer provinceId) {
		List<AreaInfo> blank = new ArrayList<AreaInfo>();
		AreaInfo province = areaService.getAreaById(provinceId);
		if (province == null || province.getLevel() == null
				|| !province.getLevel().equals(AreaInfo.LEVEL_PROVINCE)) {
			return blank;
		}
		return areaService.getAreasByParent(provinceId);
	}

	@RequestMapping("/area/permitProvinces")
	@ResponseBody
	public List<AreaInfo> permitProvinces() {
		return areaService.getPermitProvinces();
	}

	@RequestMapping("/area/permitCitys")
	@ResponseBody
	public List<AreaInfo> permitCitys(Integer provinceId) {
		return areaService.getPermitCitys(provinceId);
	}

	@RequestMapping("/area/getParent")
	@ResponseBody
	public AreaInfo getParent(Integer areaId, Integer level) {
		return areaService.getParent(areaId, level);
	}

}
