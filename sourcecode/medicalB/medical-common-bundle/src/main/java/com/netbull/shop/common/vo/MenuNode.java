package com.netbull.shop.common.vo;

import java.util.List;

/**
 *
 */
public class MenuNode
{
	private int userId;

	private String nodeCode;

	private String nodeName;

	private String parentCode;

	private String nodeType;
	
	private int checkVal;
	
	private String nodeUrl;
	
	private String latnId;
	
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private List<MenuNode> childNodes;

	public String getNodeCode()
	{
		return nodeCode;
	}

	public List<MenuNode> getChildNodes()
	{
		return childNodes;
	}

	public void setChildNodes(List<MenuNode> childNodes)
	{
		this.childNodes = childNodes;
	}

	public void setNodeCode(String nodeCode)
	{
		this.nodeCode = nodeCode;
	}

	public String getNodeName()
	{
		return nodeName;
	}

	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}

	public String getNodeUrl()
	{
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl)
	{
		this.nodeUrl = nodeUrl;
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getParentCode()
	{
		return this.parentCode;
	}

	public void setParentCode(String parentCode)
	{
		this.parentCode = parentCode;
	}

	public String getNodeType()
	{
		return this.nodeType;
	}

	public void setNodeType(String nodeType)
	{
		this.nodeType = nodeType;
	}

	public int getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(int checkVal) {
		this.checkVal = checkVal;
	}

	public String getLatnId() {
		return latnId;
	}

	public void setLatnId(String latnId) {
		this.latnId = latnId;
	}
}
