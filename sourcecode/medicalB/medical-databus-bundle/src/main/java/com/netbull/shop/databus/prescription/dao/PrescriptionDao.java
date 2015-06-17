package com.netbull.shop.databus.prescription.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.prescription.dto.DoctorQueryReq;
import com.netbull.shop.databus.prescription.dto.DrugQueryReq;
import com.netbull.shop.databus.prescription.dto.PatientPrescription;
import com.netbull.shop.databus.prescription.dto.PatientQueryReq;
import com.netbull.shop.databus.prescription.model.Drug;
import com.netbull.shop.databus.prescription.model.Prescription;
import com.netbull.shop.databus.prescription.model.PrescriptionBuyRecord;
import com.netbull.shop.databus.prescription.model.PrescriptionItem;
import com.netbull.shop.databus.prescription.model.PrescriptionRelation;

/**
 * 处方Dao
 * 
 * @author Ade
 */
@Repository
public class PrescriptionDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = Prescription.class.getName();

	@Resource
	private SqlSession session;

	public int savePrescriptionRelation(PrescriptionRelation relation) {
		return session.insert(NAMESPACE+".save_PrescriptionRelation", relation);
	}
	
	public int saveBuyRecord(PrescriptionBuyRecord buyRecord) {
		return session.insert(NAMESPACE+".save_PrescriptionBuyRecord", buyRecord);
	}
	
	public Prescription savePrescription(Prescription prescription) {
		session.insert(NAMESPACE+".save_Prescription", prescription);
		return prescription;
	}
	
	public int updatePrescription(Prescription prescription) {
		return session.update(NAMESPACE+".update_Prescription", prescription);
	}

	public long savePrescriptionItem(PrescriptionItem prescriptionItem) {
		return session.insert(NAMESPACE+".save_PrescriptionItem", prescriptionItem);
	}
	
	public long updatePrescriptionItem(PrescriptionItem prescriptionItem) {
		return session.update(NAMESPACE+".update_PrescriptionItem", prescriptionItem);
	}

	public Prescription findPrescriptionById(long id) {
		return session.selectOne(NAMESPACE+".query_Prescription_by_id", id);
	}
	
	public PatientPrescription findPatientPrescriptionById(long id) {
		return session.selectOne(NAMESPACE+".query_PatientPrescription_by_id", id);
	}
	
	public List<PrescriptionItem> findPrescriptionItemsByPrescriptionId(long prescriptionId) {
		return session.selectList(NAMESPACE+".query_PrescriptionItems_by_PrescriptionId", prescriptionId);
	}
	
	public int delPrescriptionById(long id) {
		return session.update(NAMESPACE+".del_Prescription_by_id", id);
	}
	
	public List<Prescription> doctorPrescriptionPagination(DoctorQueryReq query) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("doctorId", query.getDoctorId());
		param.put("start", (query.getPageNum() - 1) * query.getPageSize());
		param.put("pageSize", query.getPageSize());
		return session.selectList(NAMESPACE + ".query_for_doctorPagination", param);
	}
	
	public long doctorPrescriptionTotalCount(DoctorQueryReq query) {
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("doctorId", query.getDoctorId());
			param.put("start", (query.getPageNum() - 1) * query.getPageSize());
			param.put("pageSize", query.getPageSize());
			return session.selectOne(NAMESPACE + ".query_for_doctorTotalCount", param);
		}catch(Exception e) {
			logger.error("error.", e);
			return 0;
		}
	}
	
	public List<PatientPrescription> patientPrescriptionPagination(PatientQueryReq query) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("patientId", query.getPatientId());
		param.put("start", (query.getPageNum() - 1) * query.getPageSize());
		param.put("pageSize", query.getPageSize());
		return session.selectList(NAMESPACE + ".query_for_patientPagination", param);
	}
	
	public long patientPrescriptionTotalCount(PatientQueryReq query) {
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("patientId", query.getPatientId());
			param.put("start", (query.getPageNum() - 1) * query.getPageSize());
			param.put("pageSize", query.getPageSize());
			return session.selectOne(NAMESPACE + ".query_for_patientTotalCount", param);
		}catch(Exception e) {
			logger.error("error.", e);
			return 0;
		}
	}
	
	public List<Drug> drugQueryPagination(DrugQueryReq query) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("shortCode", query.getShortCode());
		param.put("start", (query.getPageNum() - 1) * query.getPageSize());
		param.put("pageSize", query.getPageSize());
		return session.selectList(NAMESPACE + ".query_drug_pagination", param);
	}
	
	public long drugQueryTotalCount(DrugQueryReq query) {
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("shortCode", query.getShortCode());
			param.put("start", (query.getPageNum() - 1) * query.getPageSize());
			param.put("pageSize", query.getPageSize());
			return session.selectOne(NAMESPACE + ".query_drug_totalCount", param);
		}catch(Exception e) {
			logger.error("error.", e);
			return 0;
		}
	}
	
	public Drug queryDrugSheapest(long shopId, String drugName, String spec, String producer) {
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("shopId", shopId);
			param.put("drugName", drugName);
			param.put("spec", spec);
			param.put("producer", producer);
			List<Drug> list = session.selectList(NAMESPACE + ".query_drug_for_nearshop", param);
			if(list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		}catch(Exception e) {
			logger.error("error.", e);
			return null;
		}
	}
	
}
