package com.netbull.web.shop.index.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.web.shop.index.dao.IndexDao;
import com.netbull.web.shop.index.entity.Floor;
import com.netbull.web.shop.index.entity.FloorItem;

@Service
public class IndexService {
	
	@Resource
	private IndexDao indexDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return indexDao.page(iDisplayStart,iDisplayLength,requestMap);
	}
	
	public int deleteById(long id) {
		return indexDao.deleteById(id);
	}
	
	public List<Floor> queryFloors() {
		List<Floor> floors = indexDao.queryAllFloor();
		for(Floor floor : floors) {
			List<FloorItem> items = indexDao.queryFloorItems(floor.getFloor());
			floor.setItems(items);
		}
		
		return floors;
	}
	
}
