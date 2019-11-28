package com.milos.releasemicroservice.util;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "image-processing-service", url = "localhost:8080/v1", configuration = ImageProcessingServiceProxy.MultipartSupportConfig.class)
public interface ImageProcessingServiceProxy
{
	@PostMapping(value = "/images/batch", consumes = "multipart/form-data")
	List<ImageDTO> storeImages(@RequestParam("uploaderId") Long uploaderId, @RequestParam("itemId") Long itemId,
			@RequestPart("images") MultipartFile[] images);

	class MultipartSupportConfig
	{

		@Autowired
		private ObjectFactory<HttpMessageConverters> messageConverters;

		@Bean
		public Encoder feignFormEncoder()
		{
			return new SpringFormEncoder(new SpringEncoder(messageConverters));
		}
	}
}
