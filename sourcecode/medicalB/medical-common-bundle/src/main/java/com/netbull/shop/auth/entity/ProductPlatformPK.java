package com.netbull.shop.auth.entity;

import java.io.Serializable;

public class ProductPlatformPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -226408566869057719L;

	private String productId;
	private String platformId;

	public ProductPlatformPK() {

	}

	public ProductPlatformPK(String productId, String platformId) {
		this.productId = productId;
		this.platformId = platformId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((platformId == null) ? 0 : platformId.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
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
		ProductPlatformPK other = (ProductPlatformPK) obj;
		if (platformId == null) {
			if (other.platformId != null)
				return false;
		} else if (!platformId.equals(other.platformId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	

}
