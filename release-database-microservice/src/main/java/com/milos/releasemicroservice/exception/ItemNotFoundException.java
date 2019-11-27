package com.milos.releasemicroservice.exception;

public class ItemNotFoundException extends RuntimeException
{
	public ItemNotFoundException(Long id, String itemType)
	{
		super(itemType + " with id: " + id + " not found!");
	}

	public ItemNotFoundException(Long id)
	{
		super("Item with id: " + id + " not found!");
	}
}
