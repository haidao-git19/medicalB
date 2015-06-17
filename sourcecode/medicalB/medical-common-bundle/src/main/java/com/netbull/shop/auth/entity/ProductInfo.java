package com.netbull.shop.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BizInfo")
public class ProductInfo implements Comparable<ProductInfo>{
	
	private String id;
	private String name;
	private String cnName;
	private Integer status;
	private Integer type;
	private String mspAppId;
	private String description;
	private String bizNumber;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "BizId",length=16)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "BizName",length=16)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "BizCnName",length=255)
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
	@Column(name = "Status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "BizType")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "MspAppId",length=16)
	public String getMspAppId() {
		return mspAppId;
	}
	public void setMspAppId(String mspAppId) {
		this.mspAppId = mspAppId;
	}
	
	@Column(name = "BizDesc",length=512)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "BizNumber",length=16)
	public String getBizNumber() {
		return bizNumber;
	}
	public void setBizNumber(String bizNumber) {
		this.bizNumber = bizNumber;
	}
	
	public int compareTo(ProductInfo o) {
		if(o != null) {
			if(bizNumber != null && o.getBizNumber() != null) {
				int _ret = bizNumber.compareTo(o.getBizNumber());
				if(_ret == 0 ) {
					return id.compareTo(o.getId());
				}else {
					return _ret;
				}
			}
			return id.compareTo(o.getId());
		}
		return 0;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInfo other = (ProductInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
