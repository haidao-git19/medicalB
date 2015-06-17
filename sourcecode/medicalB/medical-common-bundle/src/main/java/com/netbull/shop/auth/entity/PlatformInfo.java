package com.netbull.shop.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OperationSystem")
public class PlatformInfo implements Comparable<PlatformInfo>{
	
	private String id;
	private String name;
	private String description;
	private Date createTime;
	private String pfNumber;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "OSID",length=16)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "OSName",length=32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "Description",length=512)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "OsNumber",length=16)
	public String getPfNumber() {
		return pfNumber;
	}
	public void setPfNumber(String bizNumber) {
		this.pfNumber = bizNumber;
	}
	
	public int compareTo(PlatformInfo o) {
		if(o != null) {
			if(pfNumber != null && o.getPfNumber() != null) {
				int _ret = pfNumber.compareTo(o.getPfNumber());
				if(_ret == 0) {
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
		PlatformInfo other = (PlatformInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
