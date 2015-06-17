package com.netbull.shop.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AreaInfo")
public class AreaInfo implements Comparable<AreaInfo> {

	/** 顶级 */
	public static final int LEVEL_TOP = 1;

	/** 国家级 */
	public static final int LEVEL_COUNTRY = 1;

	/** 省份级 */
	public static final int LEVEL_PROVINCE = 2;

	/** 城市级 */
	public static final int LEVEL_CITY = 3;

	private Integer aiid;
	private Integer parentId;
	private Integer level;
	private Long userCount;
	private String name;
	private String description;
	private Date createdTime;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Aiid")
	public Integer getAiid() {
		return this.aiid;
	}

	public void setAiid(Integer aiid) {
		this.aiid = aiid;
	}

	@Column(name = "ParentId")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "UserCount")
	public Long getUserCount() {
		return this.userCount;
	}

	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}

	@Column(name = "Name", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Description", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedTime", nullable = false, length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aiid;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaInfo other = (AreaInfo) obj;
		if (aiid != other.aiid)
			return false;
		return true;
	}

	public int compareTo(AreaInfo o) {
		if (o instanceof AreaInfo) {
			AreaInfo be = (AreaInfo) o;
			if(this.level != null && be.getLevel() != null) {
				int levelRet = Integer.valueOf(this.level).compareTo(
						Integer.valueOf(be.getLevel()));
				if (levelRet == 0) {
					return Integer.valueOf(this.aiid).compareTo(
							Integer.valueOf(be.getAiid()));
				} else {
					return levelRet;
				}
			}else {
				return Integer.valueOf(this.aiid).compareTo(
						Integer.valueOf(be.getAiid()));
			}
		}
		return 0;
	}

	public String toString() {
		return this.name;
	}

}
