package com.netbull.web.shop.index.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.web.shop.index.entity.Floor;
import com.netbull.web.shop.index.entity.FloorItem;
import com.netbull.web.shop.index.entity.Index;

@Repository
public class IndexDao {
	
	private static final String NAMESPACE = Index.class.getName();
	private static final String FLOOR_NS = Floor.class.getName();

	@Resource
	private SqlSession session;

	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return session.page(NAMESPACE + ".query_for_pagination", NAMESPACE + ".totalCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public int deleteById(long id) {
		return session.update(NAMESPACE+".delete_by_id", id);
	}
	
	public int deleteDiseaseByDoctorID(long doctorID) {
		return session.delete(NAMESPACE+".delete_disease_by_doctorID", doctorID);
	}
	
	public List<Floor> queryAllFloor() {
		return session.selectList(FLOOR_NS + ".queryAllFloor");
	}
	
	public List<FloorItem> queryFloorItems(int floor) {
		return session.selectList(FLOOR_NS + ".queryFloorItems", floor);
	}
}
