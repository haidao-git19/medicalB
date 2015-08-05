package com.netbull.shop.company.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.area.service.AreaService;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.company.entity.CompanyVo;
import com.netbull.shop.company.service.CompanyService;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("companyController")
public class CompanyController {

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private CompanyService companyService;
	@Resource
	private AreaService areaService;

	@RequestMapping(value = "/companyList.json")
	@ResponseBody
	public Map<String, Object> query(Integer sEcho, Integer iColumns,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request) {

		Map<String, String> requestMap = RequestUtils.parameterToMap(request);

		Page page = companyService.page(iDisplayStart, iDisplayLength,
				requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho,
				page.getTotal());
		List<Area> areaLst = areaService.handleAllArea();
		for (Object o : page.getResult()) {
			Map map = (Map) o;
			List<Object> list = new ArrayList<Object>();
			list.add(map.get("companyId"));
			list.add(map.get("companyName"));

			// 设置本地网
			String latnId = "";
			for (int i = 0; i < areaLst.size(); i++) {
				if (String.valueOf(areaLst.get(i).getAreaID()).equals(
						map.get("latnId").toString())) {
					latnId = areaLst.get(i).getAreaName();
				}
			}
			if (!StringTools.isEmpty(latnId)) {
				list.add(latnId);
			} else {
				list.add(map.get("latnId"));
			}
			list.add(map.get("linkPhone"));
			list.add(map.get("companyaddress"));
			list.add(map.get("state"));
			aaData.add(list);
		}

		resultMap.put("aaData", aaData);
		return resultMap;
	}

	@RequestMapping(value = "/company.do")
	public String initQuery(HttpServletRequest request,
			HttpServletResponse response) {
		return "company/companyList";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/company/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(Integer companyId, CompanyVo company,
			HttpServletRequest request, HttpServletResponse response) {

		ShiroUser currUser = BaseDao.queryCurrentShiroUser();
		company.setCreator(currUser.getId());

		JSONObject json = new JSONObject();
		int count = 0;
		if (companyId != null && companyId > 0) {
			count = companyService.update(company);
		} else {
			Map parameter = new HashMap();
			parameter.put("companyName", company.getCompanyName());
			CompanyVo ph = companyService.findByParam(parameter);
			if (!NullUtil.isNull(ph)) {
				json.put("msg", "该公司已存在不能重复添加!");
			} else {
				count = companyService.save(company);
			}
		}
		json.put("flag", count == 1);
		json.put("companyId", company.getCompanyId());
		return json.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/company/initAddOrUpdate")
	public String initAddOrUpdate(ModelMap model, Integer companyId,
			HttpServletRequest request) {
		CompanyVo ph = new CompanyVo();
		if (companyId != null && companyId > 0) {
			Map param = new HashMap();
			param.put("companyId", companyId);
			ph = companyService.findByParam(param);
		}
		model.addAttribute("ph", ph);
		return "company/_initAddOrUpdate";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/company/del")
	@ResponseBody
	public String del(Integer companyId, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int count = 0;
		CompanyVo company = new CompanyVo();
		if (companyId != null && companyId > 0) {
			Map param = new HashMap();
			param.put("companyId", companyId);
			company = companyService.findByParam(param);
		}
		if (!NullUtil.isNull(company)) {
			company.setState(-1);
			count = companyService.update(company);
		}

		json.put("flag", count == 1);
		return json.toString();
	}

}
