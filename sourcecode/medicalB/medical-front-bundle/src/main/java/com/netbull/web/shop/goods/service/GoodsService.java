package com.netbull.web.shop.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.web.shop.goods.dao.GoodsDao;
import com.netbull.web.shop.goods.domain.Goods;
import com.netbull.web.shop.goods.domain.GoodsImg;
import com.netbull.web.shop.goods.vo.CataNavigation;
import com.netbull.web.shop.goods.vo.CatagoryOption;
import com.netbull.web.shop.goods.vo.GoodsVo;
import com.netbull.web.shop.goods.vo.OptionKey;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class GoodsService {

	@Resource
	private GoodsDao goodsDao;

	public GoodsVo getGoodsVo(String goodsCode) {
		GoodsVo vo = new GoodsVo();
		
		Goods goods = goodsDao.findGoods(goodsCode);
		List<GoodsImg> images = goodsDao.findGoodsImages(goodsCode);
		vo.setGoods(goods);
		vo.setImages(images);
		
		return vo;
	}
	
	public Map queryByCondition(Map param) {
		Map map = new HashMap();
		
		List records = goodsDao.queryRecordSizeByCondition(param);
		int recordSize = goodsDao.queryRecordsByCondition(param);
		
		map.put("records", records);
		map.put("recordSize", recordSize);
		return map;
	}
	
	public List<CataNavigation> queryNavigation() {
		List<CataNavigation> allCatas = goodsDao.queryCatalogTree();
		
		List<CataNavigation> firstLvl = new ArrayList<CataNavigation>();
		List<CataNavigation> secdLvl = new ArrayList<CataNavigation>();
		List<CataNavigation> thrdLvl = new ArrayList<CataNavigation>();
		
		for(CataNavigation cata : allCatas) {
			if(cata.getDepth() == 1) {
				firstLvl.add(cata);
			}else if(cata.getDepth() == 2) {
				secdLvl.add(cata);
			}else if(cata.getDepth() == 3) {
				thrdLvl.add(cata);
			}
		}
		
		List<CataNavigation> navis = new ArrayList<CataNavigation>();
		
		for(CataNavigation first : firstLvl) {
			navis.add(first);
			
			for(CataNavigation secd : secdLvl) {
				if(first.getId() == secd.getPid()) {
					first.getChilds().add(secd);
					
					for(CataNavigation thrd : thrdLvl) {
						if(secd.getId() == thrd.getPid()) {
							secd.getChilds().add(thrd);
						}
					}
				}
			}
		}
		
		return navis;
	}
	
	public Map<OptionKey, List<CatagoryOption>> queryCatalogOption(long catalogId) {
		List<CatagoryOption> all = goodsDao.queryCatalogOption(catalogId);
		
		Map<OptionKey, List<CatagoryOption>> map = new TreeMap<OptionKey, List<CatagoryOption>>();
		for(CatagoryOption cataOpt : all) {
			long catId = cataOpt.getCatId();
			String optionClsName = cataOpt.getOptionClsName();
			long scId = cataOpt.getScId();
			OptionKey key = new OptionKey(catId, optionClsName, scId);
			List<CatagoryOption> list = map.get(key);
			if(list == null) {
				list = new ArrayList<CatagoryOption>();
				list.add(cataOpt);
				map.put(key, list);
			}else{
				list.add(cataOpt);
			}
		}
		
		return map;
	}
}
