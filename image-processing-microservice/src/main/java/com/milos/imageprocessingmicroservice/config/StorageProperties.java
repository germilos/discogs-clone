package com.milos.imageprocessingmicroservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties
{

	/**
	 * Folder location for storing files
	 */
	private String location = "C:\\Users\\Milos\\Desktop\\discogs-clone\\image-processing-microservice\\src\\main\\resources\\static\\images";

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
}