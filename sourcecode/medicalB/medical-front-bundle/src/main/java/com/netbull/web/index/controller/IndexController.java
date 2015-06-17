
package com.netbull.web.index.controller;

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
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.section.entity.ChildSection;
import com.netbull.shop.section.entity.Section;
import com.netbull.shop.section.service.SectionService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.web.index.entity.Article;
import com.netbull.web.index.entity.Consult;
import com.netbull.web.index.entity.Doctor;
import com.netbull.web.index.entity.HealthKnowlege;
import com.netbull.web.index.entity.HospitalList;
import com.netbull.web.index.service.ArticleService;
import com.netbull.web.index.service.ConsultService;
import com.netbull.web.index.service.DoctorService;
import com.netbull.web.index.service.NewsService;
import com.netbull.web.index.service.NurseService;
import com.netbull.web.index.service.ShopService;
@Controller("webIndexController")
public class IndexController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Resource
	private AreaService areaService;
	@Resource
	private SectionService sectionService;
	@Resource
	private DoctorService doctorService;
	@Resource
	private ShopService shopService;
	@Resource
	private NewsService newsService;
	@Resource
	private NurseService nurseService;
	
	@Resource
	private ConsultService consultService;
	
	@Resource
	private ArticleService articleService;
	/**
	 * 查询首页海报图
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/web/posterList")
	@ResponseBody
	public List<Map> posterList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class posterList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String posterUrl = ConfigLoadUtil.loadConfig().getPropertie("queryPoster");
		String posterResp = HttpXmlUtil.doPost(posterUrl, requestMap);
		Map<String,Object> posterResultMap=JsonUtils.json2Map(posterResp);
		String posterCode=(String) posterResultMap.get("code");
		List<Map> posterList=null;
		if(StringTools.equals(posterCode, "0")){
			posterList=(List<Map>) posterResultMap.get("posterList");
		}
		return posterList;
	}
	
	/**
	 * 进入首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anon/web")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		List<Area> areaList=areaService.handleAllArea();
		request.setAttribute("areaList",areaList);
		
		//查询医院
		Map<String,String> requestMap = new HashMap<String, String>();
		requestMap.put("type", "1");
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryHospitalList"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		HospitalList hospitalList=JSON.parseObject(resp,HospitalList.class);
		request.setAttribute("hospitalList",hospitalList);
		
		return "web/index/index";
	}
	
	/**
	 * 查询科室信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anon/web/section")
	@ResponseBody
	public List<Section> section(HttpServletRequest request, HttpServletResponse response) {
		List<Section> fistlevellist=sectionService.findFirstLevelSections();
		return fistlevellist;
	}
	
	/**
	 * 查询大类科室下推荐医生信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anon/web/secdoc")
	@ResponseBody
	public List<Doctor> findsecdoclist(HttpServletRequest request, HttpServletResponse response) {
		Integer secid=Integer.parseInt(request.getParameter("secid"));
		List<Doctor> findsecdoclist=doctorService.findDoclistBybigsec(secid);
		return findsecdoclist;
	}
	
	/**
	 * 查询入驻药房数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anon/web/countshop")
	@ResponseBody
	public long countshop(HttpServletRequest request, HttpServletResponse response) {
		long l=shopService.getcount();
		return l;
	}
	
	/**
	 * 查询健康常识动态列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/web/notice")
	@ResponseBody
	public List<HealthKnowlege> queryHealthKnowlegeDynamics(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryHealthKnowlegeDynamics method.");
		}
		Map paramter = new HashMap();
		paramter.put("start",1);
		paramter.put("limit", 4);
		paramter.put("sortKey", "desc");
		List<HealthKnowlege> detailList = this.newsService.queryDetailList(paramter);
		
		/*for (Iterator iterator = detailList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			if(!NullUtil.isNull(map.get("coverImage"))){
				map.put("coverImage", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") +map.get("coverImage"));
			}
		}*/
		
		//resultMap.put("detailList", detailList);
		return detailList;
	}
	
	/**
	 * 查询体验服务数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anon/web/countNurse")
	@ResponseBody
	public long countNurse(HttpServletRequest request, HttpServletResponse response) {
		long l=nurseService.getcount();
		return l;
	}
	
	/**
	 * 查询网上咨询动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/web/consult")
	@ResponseBody
	public List<Consult> queryCoultDynamics(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryCoultDynamics method.");
		}
		Map paramter = new HashMap();
		paramter.put("start",1);
		paramter.put("limit", 5);
		paramter.put("sortKey", "desc");
		paramter.put("doctorID", 0);
		List<Consult> detailList = this.consultService.queryDetailList(paramter);
		return detailList;
	}
	
	/**
	 * 查询网上某科室咨询信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/web/ksconsult")
	@ResponseBody
	public List<Consult> queryKsCoultDynamics(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryKsCoultDynamics method.");
		}
		String secid=request.getParameter("secid");
		Map paramter = new HashMap();
		paramter.put("start",1);
		paramter.put("limit", 5);
		paramter.put("sortKey", "desc");
		paramter.put("doctorID", 0);
		paramter.put("sectionID", secid);
		List<Consult> wsdetailList = this.consultService.queryDetailList(paramter);
		return wsdetailList;
	}
	
	/**
	 * 查询网上咨询数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anon/web/countWszx")
	@ResponseBody
	public long countWszx(HttpServletRequest request, HttpServletResponse response) {
		Map paramter = new HashMap();
		long l=consultService.countWsConsult(paramter);
		return l;
	}
	
	/**
	 * 查询专家文章
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/web/zjarcticle")
	@ResponseBody
	public List<Article> queryArcticleList(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryArcticleList method.");
		}
		Map paramter = new HashMap();
		paramter.put("start",1);
		paramter.put("limit", 9);
		paramter.put("sortKey", "desc");
		List<Article> detailList = this.articleService.queryDetailList(paramter);
		return detailList;
	}
	
	/**
	 * 查询按科室找大夫
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/anon/web/sectionandse")
	@ResponseBody
	public List<Section> sectionandse(HttpServletRequest request, HttpServletResponse response) {
		List<Section> fistlevellist=sectionService.findFirstLevelSections();
		List <Section> list=new ArrayList<Section>();
		for(int i=0;i<fistlevellist.size();i++)
		{
			Section section=fistlevellist.get(i);
			List<Section> secondlist=sectionService.findSecondLevelSections(section.getId());
			List<ChildSection> chilsection=new ArrayList<ChildSection>();
			for(int j=0;j<secondlist.size();j++)
			{
				Section sectionchild= secondlist.get(j);
				ChildSection child=new ChildSection();
				child.setAttrname(sectionchild.getAttrname());
				child.setAttrvalue(sectionchild.getAttrname());
				child.setId(sectionchild.getId());
				chilsection.add(child);
			}
			section.setChildlist(chilsection);
			list.add(section);
		}
		return list;
	}
}
