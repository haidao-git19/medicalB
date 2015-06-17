package com.netbull.shop.common.vo;

import java.io.Serializable;
import java.util.List;

public class GoodsVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String categoryCode;
	/****商品编号*****/
	private String goodsCode;
	private String longName;
	private String mediumName;
	private String shortName;
	private String secondName;
	/****商品品牌*****/
	private String brandId;
	private String style;
	private String model;
	private String coverImgPath;
	private String imgPath;
	private Integer content;
	/****封面图*****/
	private String coverImage;
	/****是否草稿,y为草稿，n为非草稿*****/
	private String isDraft;
	/****是否当前版本:y表示当前版本，n表示历史记录版本*****/
	private String isCurrentVersion;
	/****市场价，单位为元*****/
	private Float marketPrice;
	/****商品类型:配件，赠品，虚拟商品等，定义在字典表中。值以1,2,4,8的形式定义*****/
	private String goodsType;
	/****包装清单*****/
	private Integer packageList;
	/****商品销售特征：价保，七天退货，正品行货。值以1,2,4,8的形式定义。*****/
	private Integer sellCharacter;
	/****销售属性:预售.值以1,2,4,8的形式定义*****/
	private Integer sellAttr;
	/****商品标签：新品，促销.*****/
	private Integer label;
	/****商品状态.定义在字典表中. 初始状态、待审核状态、在售等*****/
	private String status;
	/****商品买点*****/
	private Integer sellingPoints;
	/****创建时间*****/
	private String createTime;
	/****创建人*****/
	private String createPerson;
	/****更新时间*****/
	private String updateTime;
	/****更新人*****/
	private String updatePerson;
	/****上市时间*****/
	private String saleTime;
	/****商城价，方案中最小定价*****/
	private Float shopPrice;
	/****购买须知*****/
	private Integer buyNote;
	/****是否显示 0 显示  1不显示*****/
	private String isShow;
	/****药店*****/
	private Integer shopId;
	/****所属导航****/
	private Integer navicatId;
	
	private List<Integer> conditionOptList;
	
	public List<Integer> getConditionOptList() {
		return conditionOptList;
	}
	public void setConditionOptList(List<Integer> conditionOptList) {
		this.conditionOptList = conditionOptList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(String isDraft) {
		this.isDraft = isDraft;
	}
	public String getIsCurrentVersion() {
		return isCurrentVersion;
	}
	public void setIsCurrentVersion(String isCurrentVersion) {
		this.isCurrentVersion = isCurrentVersion;
	}
	public Float getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Float marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public Integer getSellCharacter() {
		return sellCharacter;
	}
	public void setSellCharacter(Integer sellCharacter) {
		this.sellCharacter = sellCharacter;
	}
	public Integer getSellAttr() {
		return sellAttr;
	}
	public void setSellAttr(Integer sellAttr) {
		this.sellAttr = sellAttr;
	}
	public Integer getLabel() {
		return label;
	}
	public void setLabel(Integer label) {
		this.label = label;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdatePerson() {
		return updatePerson;
	}
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public Float getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(Float shopPrice) {
		this.shopPrice = shopPrice;
	}
	
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getMediumName() {
		return mediumName;
	}
	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCoverImgPath() {
		return coverImgPath;
	}
	public void setCoverImgPath(String coverImgPath) {
		this.coverImgPath = coverImgPath;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getContent() {
		return content;
	}
	public void setContent(Integer content) {
		this.content = content;
	}
	public Integer getPackageList() {
		return packageList;
	}
	public void setPackageList(Integer packageList) {
		this.packageList = packageList;
	}
	public Integer getSellingPoints() {
		return sellingPoints;
	}
	public void setSellingPoints(Integer sellingPoints) {
		this.sellingPoints = sellingPoints;
	}
	public Integer getBuyNote() {
		return buyNote;
	}
	public void setBuyNote(Integer buyNote) {
		this.buyNote = buyNote;
	}
	public Integer getNavicatId() {
		return navicatId;
	}
	public void setNavicatId(Integer navicatId) {
		this.navicatId = navicatId;
	}

}
