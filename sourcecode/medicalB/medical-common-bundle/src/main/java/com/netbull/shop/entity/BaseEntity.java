package com.netbull.shop.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.netbull.shop.common.config.ConfigLoadUtil;

/**
 * 实体bean基类
 * 
 * @author <a href=mailto:pengwu2@iflytek.com>wu.peng</a> 2012-6-18
 * 
 */
@MappedSuperclass
public class BaseEntity implements Comparable<Object> {
	
	/** 有效状态 */
	public static final int STATUS_ENABLE = 1;
	
	/** 无效状态 */
	public static final int STATUS_DISABLE = 0;
	
	/** 删除状态 */
	public static final int STATUS_DELETE = -1;

	private Long id;
	
	@Id
	@GeneratedValue(generator = "g_entity")
	@GenericGenerator(name = "g_entity", strategy = "native", parameters = {
			@Parameter(name = "sequence", value = "seq_entity"),
			@Parameter(name = "parameters", value = "start with 1000") })
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int compareTo(Object o) {
		if (o instanceof BaseEntity) {
			BaseEntity be = (BaseEntity) o;
			if (this.id != null && be.getId() != null) {
				return this.id.compareTo(be.getId());
			}
		}
		return 0;
	}
}
