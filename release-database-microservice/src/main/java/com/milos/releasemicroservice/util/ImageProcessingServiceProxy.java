package com.milos.releasemicroservice.util;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "image-processing-service", url = "localhost:8888/v1", configuration = FeignSpringFormEncoder.class)
public interface ImageProcessingServiceProxy
{
	@PostMapping(value = "/images/batch", consumes = "multipart/form-data")
	List<Long> storeImages(@Param("uploaderId") Long uploaderId, @Param("images") MultipartFile[] images);
}
