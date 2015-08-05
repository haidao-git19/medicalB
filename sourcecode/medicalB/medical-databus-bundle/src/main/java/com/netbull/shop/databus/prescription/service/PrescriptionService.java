package com.netbull.shop.databus.prescription.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.databus.prescription.dao.PrescriptionDao;
import com.netbull.shop.databus.prescription.dto.DoctorCreateResp;
import com.netbull.shop.databus.prescription.dto.DoctorDetailResp;
import com.netbull.shop.databus.prescription.dto.DoctorQueryReq;
import com.netbull.shop.databus.prescription.dto.DoctorQueryResp;
import com.netbull.shop.databus.prescription.dto.DrugQueryReq;
import com.netbull.shop.databus.prescription.dto.DrugQueryResp;
import com.netbull.shop.databus.prescription.dto.NearShopBuyReq;
import com.netbull.shop.databus.prescription.dto.NearShopBuyResp;
import com.netbull.shop.databus.prescription.dto.PatientDetailResp;
import com.netbull.shop.databus.prescription.dto.PatientPrescription;
import com.netbull.shop.databus.prescription.dto.PatientPrescriptionDto;
import com.netbull.shop.databus.prescription.dto.PatientQueryReq;
import com.netbull.shop.databus.prescription.dto.PatientQueryResp;
import com.netbull.shop.databus.prescription.dto.PrescriptionDto;
import com.netbull.shop.databus.prescription.model.Drug;
import com.netbull.shop.databus.prescription.model.OneShopBuy;
import com.netbull.shop.databus.prescription.model.Prescription;
import com.netbull.shop.databus.prescription.model.PrescriptionBuyRecord;
import com.netbull.shop.databus.prescription.model.PrescriptionItem;
import com.netbull.shop.databus.prescription.model.PrescriptionRelation;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.manage.weixin.service.ShopService;

/**
 * 处方Service
 * 
 * @author Ade
 */
@Service
public class PrescriptionService {
	private static final Logger logger = Logger.getLogger("serviceLog");
	
	@Resource
	private ShopService shopService;
	
	@Resource
	private PrescriptionDao prescriptionDao;
	
	public int savePrescriptionRelation(PrescriptionRelation relation) {
		try{
			return prescriptionDao.savePrescriptionRelation(relation);
		}catch(Exception e) {
			logger.error("savePrescriptionRelation error.", e);
			return 0;
		}
	}
	
	public Resp saveBuyRecord(PrescriptionBuyRecord buyRecord) {
		Resp resp = new Resp();
		try{
			int cnt = prescriptionDao.saveBuyRecord(buyRecord);
			resp.setCode(cnt > 0 ? Constants.SUCCESS : Constants.FAIL);
			resp.setMsg(cnt > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
		}catch(Exception e) {
			logger.error("saveBuyRecord error.", e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}

	public DoctorCreateResp savePrescriptionDto(PrescriptionDto dto) {
		DoctorCreateResp resp = new DoctorCreateResp();
		try{
			Prescription prescription = new Prescription();
			prescription.setDoctorId(dto.getDoctorId());
			prescription.setTitle(dto.getTitle());
			prescription.setApplication(dto.getApplication());
			prescription.setRemark(dto.getRemark());
			prescription.setStatus(Prescription.NORMAL);
			prescription.setIsShow(dto.getIsShow());
			prescription.setQrCodePath(dto.getQrCodePath());
			prescription = prescriptionDao.savePrescription(prescription);
			
			List<PrescriptionItem> prescriptionItems = dto.getItems();
			for(PrescriptionItem item : prescriptionItems) {
				item.setPrescriptionId(prescription.getId());
				prescriptionDao.savePrescriptionItem(item);
			}
			
			resp.setCode(prescription.getId() > 0 ? Constants.SUCCESS : Constants.FAIL);
			resp.setMsg(prescription.getId() > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
			resp.setId(prescription.getId());
		}catch(Exception e) {
			logger.error("savePrescriptionDto error.", e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
	
	public int updatePrescriptionDto(PrescriptionDto dto) {
		try{
			Prescription prescription = new Prescription();
			prescription.setId(dto.getId());
			prescription.setTitle(dto.getTitle());
			prescription.setApplication(dto.getApplication());
			prescription.setRemark(dto.getRemark());
			prescriptionDao.updatePrescription(prescription);
			
			List<PrescriptionItem> prescriptionItems = dto.getItems();
			for(PrescriptionItem item : prescriptionItems) {
				prescriptionDao.updatePrescriptionItem(item);
			}
			
			return 1;
		}catch(Exception e) {
			logger.error("updatePrescriptionDto error.", e);
			return 0;
		}
	}
	
	public DoctorDetailResp findPrescriptionDtoById(long id) {
		DoctorDetailResp resp = new DoctorDetailResp();
		try{
			Prescription prescription = prescriptionDao.findPrescriptionById(id);
			List<PrescriptionItem> prescriptionItems = prescriptionDao.findPrescriptionItemsByPrescriptionId(id);
			
			PrescriptionDto dto = new PrescriptionDto();
			dto.setId(prescription.getId());
			dto.setDoctorId(prescription.getDoctorId());
			dto.setTitle(prescription.getTitle());
			dto.setApplication(prescription.getApplication());
			dto.setRemark(prescription.getRemark());
			dto.setCreateTime(prescription.getCreateTime());
			dto.setStatus(prescription.getStatus());
			dto.setIsShow(prescription.getIsShow());
			dto.setQrCodePath(prescription.getQrCodePath());
			dto.setItems(prescriptionItems);
			resp.setData(dto);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			logger.error("error", e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	public int delPrescriptionById(long id) {
		try{
			return prescriptionDao.delPrescriptionById(id);
		}catch(Exception e) {
			logger.error("error", e);
			return 0;
		}
	}
	
	public DoctorQueryResp doctorPrescriptionPagination(DoctorQueryReq query) {
		try{
			DoctorQueryResp resp = new DoctorQueryResp();
			
			long totalCount = prescriptionDao.doctorPrescriptionTotalCount(query);
			List<Prescription> prescriptions = prescriptionDao.doctorPrescriptionPagination(query);
			
			resp.setCode(totalCount > 0 ? Constants.SUCCESS : Constants.FAIL);
			resp.setMsg(totalCount > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
			resp.setTotalCount(totalCount);
			resp.setPrescriptions(prescriptions);
			return resp;
		}catch(Exception e) {
			return new DoctorQueryResp();
		}
	}
	
	public PatientQueryResp patientPrescriptionPagination(PatientQueryReq query) {
		try{
			PatientQueryResp resp = new PatientQueryResp();
			
			long totalCount = prescriptionDao.patientPrescriptionTotalCount(query);
			List<PatientPrescription> prescriptions = prescriptionDao.patientPrescriptionPagination(query);
			
			resp.setCode(totalCount > 0 ? Constants.SUCCESS : Constants.FAIL);
			resp.setMsg(totalCount > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
			resp.setTotalCount(totalCount);
			resp.setPrescriptions(prescriptions);
			return resp;
		}catch(Exception e) {
			return new PatientQueryResp();
		}
	}
	
	public PatientDetailResp findPatientPrescriptionDto(long id) {
		PatientDetailResp resp = new PatientDetailResp();
		try{
			PatientPrescription prescription = prescriptionDao.findPatientPrescriptionById(id);
			List<PrescriptionItem> prescriptionItems = prescriptionDao.findPrescriptionItemsByPrescriptionId(prescription.getPrescriptionId());
			
			PatientPrescriptionDto dto = new PatientPrescriptionDto();
			dto.setId(id);
			dto.setTitle(prescription.getTitle());
			dto.setApplication(prescription.getApplication());
			dto.setRemark(prescription.getRemark());
			dto.setQrCodePath(prescription.getQrCodePath());
			dto.setBinder(prescription.getBinder());
			dto.setBindTime(prescription.getBindTime());
			dto.setItems(prescriptionItems);
			dto.setPayStatus(prescription.getPayStatus());
			dto.setFetchStatus(prescription.getFetchStatus());
			resp.setData(dto);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			logger.error("error", e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	public DrugQueryResp drugQueryPagination(DrugQueryReq query) {
		DrugQueryResp resp = new DrugQueryResp();
		try{
			long totalCount = prescriptionDao.drugQueryTotalCount(query);
			List<Drug> drugs = prescriptionDao.drugQueryPagination(query);
			
			resp.setCode(totalCount > 0 ? Constants.SUCCESS : Constants.FAIL);
			resp.setMsg(totalCount > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
			resp.setTotalCount(totalCount);
			resp.setDrugs(drugs);
			return resp;
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
	
	@SuppressWarnings("rawtypes")
	public NearShopBuyResp queryNearShopBuys(NearShopBuyReq query) {
		NearShopBuyResp resp = new NearShopBuyResp();
		try{
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("scope", query.getScope());
			parameter.put("locationX", query.getLocationX());
			parameter.put("locationY", query.getLocationY());
			List<Map> nearShopList = shopService.queryNearShopList(parameter);
			
			String json = JSON.toJSONString(nearShopList);
			List<OneShopBuy> list = JSON.parseArray(json, OneShopBuy.class);
			
			PatientDetailResp pdp = findPatientPrescriptionDto(query.getPrescriptionId());
			List<PrescriptionItem> items = pdp.getData().getItems();
			int drugCntOfPrescription = items.size();
			
			for(OneShopBuy shop : list) {
				int drugCntOfShop = 0;
				double totalPriceOfShop = 0;
				for(PrescriptionItem item : items) {
					Drug drug = prescriptionDao.queryDrugSheapest(shop.getShopId(), item.getDrugName(), item.getDrugSpec(), item.getProducer());
					if(drug != null) {
						drugCntOfShop++;
						totalPriceOfShop += drug.getPrice();
					}
				}
				
				if(drugCntOfShop == drugCntOfPrescription) {
					shop.setCanBuyAll(1);
					shop.setTotalPrice(totalPriceOfShop);
				}
			}
			
			resp.setCode(list.size() > 0 ? Constants.SUCCESS : Constants.FAIL);
			resp.setMsg(list.size() > 0 ? Constants.SUCCESS_MSG : Constants.FAIL_MSG);
			resp.setShops(list);
			return resp;
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
	
}
