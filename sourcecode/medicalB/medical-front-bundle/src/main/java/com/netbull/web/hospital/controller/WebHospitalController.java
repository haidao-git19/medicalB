
package com.netbull.web.hospital.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.area.service.AreaService;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.hospital.entity.Hospital;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.web.hospital.entity.HospitalDesease;
import com.netbull.web.hospital.entity.HospitalSection;
import com.netbull.web.hospital.entity.WebHospitalDetails;
import com.netbull.web.hospital.service.HospitalDeseaseService;
import com.netbull.web.hospital.service.HospitalSectionService;
import com.netbull.web.index.entity.Consult;
import com.netbull.web.index.entity.Doctor;
import com.netbull.web.index.entity.HospitalList;
import com.netbull.web.index.entity.UnitList;
import com.netbull.web.index.service.ConsultService;
import com.netbull.web.index.service.DoctorService;

@Controller("webHospitalController")
public class WebHospitalController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Resource
	private AreaService areaService;
	@Resource
	private HospitalSectionService hospitalSectionService;
	@Resource
	private HospitalDeseaseService hospitalDeseaseService;
	@Resource
	private ConsultService consultService;
	@Resource
	private DoctorService doctorService;
	@RequestMapping(value = "/anon/web/hospital")
	public String hospital(HttpServletRequest request, HttpServletResponse response) {

		 List<Area> areaList=areaService.handleAllArea();
			//Map<Integer,String> areaMap=areaService.handleAllAreaMap();
			
			//查询医院
			Map<String,String> requestMap = new HashMap<String, String>();
			requestMap.put("type", "1");
			String url = ConfigLoadUtil.loadConfig().getPropertie("queryHospitalList"); 
			String resp = HttpXmlUtil.doPost(url, requestMap);
			HospitalList hospitalList=JSON.parseObject(resp,HospitalList.class);
			
			List<Area> newareaList=new ArrayList<Area>();
			
			for(int i=0;i<areaList.size();i++)
			 {
				 Area ar=areaList.get(i);
				//设置本地网
					for (int k = 0; k < hospitalList.getHospitalList().size(); k++) {
						UnitList unitlist=hospitalList.getHospitalList().get(k);
						List<Hospital> hlist=new ArrayList<Hospital>();
						int countareahospital=0;
						for(int g=0;g<unitlist.getList().size();g++)
						{
							Hospital h=unitlist.getList().get(g);
							if(String.valueOf(ar.getAreaID()).equals(String.valueOf(h.getAreaID()))){
								hlist.add(h);
								countareahospital++;
							}
						}
						ar.setHospitalList(hlist);
						ar.setCounthospital(countareahospital);
					}
					newareaList.add(ar);
			 }
			
			
			request.setAttribute("newareaList",newareaList);
		return "web/hospital/hospitalList";
	}
	
	@RequestMapping(value = "web/hospitalDetais")
	public String hospitalDtail(HttpServletRequest request, HttpServletResponse response) {

		String hospitalID=request.getParameter("hospitalID");
		//查询医院详情
		Map<String,String> requestMap = new HashMap<String, String>();
		requestMap.put("hospitalID",hospitalID);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryHospitalDetails"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		WebHospitalDetails hospitalDetails=JSON.parseObject(resp,WebHospitalDetails.class);
		
		
		//根据医院id查询科室信息
		Map<String,Integer> reqintMap = new HashMap<String, Integer>();
		reqintMap.put("hospitalID",Integer.parseInt(hospitalID));
		List<HospitalSection> firstselist=hospitalSectionService.queryFirstSectionList(reqintMap);
		List<HospitalSection> allselist=hospitalSectionService.queryAllList(reqintMap);
		request.setAttribute("firstselist",firstselist);
		request.setAttribute("allselist",allselist);
		request.setAttribute("allcount",allselist.size());
		request.setAttribute("hospitalDetails",hospitalDetails);
		
		//根据医院查询病种及各医生对应病种 
		List<HospitalDesease> disbzlist= hospitalDeseaseService.queryDisDeseaList(reqintMap);
		List<HospitalDesease> hosdoclist=hospitalDeseaseService.queryHosAllList(reqintMap);
		request.setAttribute("disbzlist",disbzlist);
		request.setAttribute("hosdoclist",hosdoclist);
		
		return "web/hospital/hospitalDetails";
	}
	
	/**
	 * 查询某医院咨询动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/web/hospitalconsult")
	@ResponseBody
	public List<Consult> queryCoultDynamics(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryCoultDynamics method.");
		}
		String hospitalID=request.getParameter("hospitalID");
		Map paramter = new HashMap();
		paramter.put("start",1);
		paramter.put("limit", 5);
		paramter.put("sortKey", "desc");
		paramter.put("hospitalID", hospitalID);
		List<Consult> detailList = this.consultService.queryDetailList(paramter);
		return detailList;
	}
	
	/**
	 * 查询某医院推荐医生
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/web/hospitaltjdoctor")
	@ResponseBody
	public List<Doctor> hospitaltjdoctor(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class hospitaltjdoctor method.");
		}
		String hospitalID=request.getParameter("hospitalID");
		Map paramter = new HashMap();
		paramter.put("start",1);
		paramter.put("limit", 5);
		paramter.put("hospitalID", Long.parseLong(hospitalID));
		List<Doctor> doctorList = this.doctorService.query_by_hosid(paramter);
		return doctorList;
	}
	
}
