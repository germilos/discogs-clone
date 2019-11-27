package com.milos.releasemicroservice.enumeration;

public enum ItemType
{
	ARTIST("Artist"), LABEL("Label"), RELEASE("Release");

	private String itemTypeName;

	ItemType(String itemTypeName)
	{
		this.itemTypeName = itemTypeName;
	}

	public String getItemTypeName()
	{
		return itemTypeName;
	}
}
