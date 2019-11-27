package com.milos.imageprocessingmicroservice.web;

import com.milos.imageprocessingmicroservice.service.StorageService;
import com.milos.imageprocessingmicroservice.service.dto.ImageMultipleSaveDTO;
import com.milos.imageprocessingmicroservice.service.dto.ImageSaveDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1")
public class ImageRestController
{
	private final StorageService storageService;

	public ImageRestController(final StorageService storageService)
	{
		this.storageService = storageService;
	}

	@PostMapping("/images")
	public Long storeImage(ImageSaveDTO imageSaveDTO)
	{
		Long returnedId = storageService.store(imageSaveDTO);
		return returnedId;
	}

	//	@PostMapping("/images/batch")
	//	public List<Long> storeImages(ImageMultipleSaveDTO imageMultipleSaveDTO)
	//	{
	//		log.info("REST request to batch-save images...");
	//		imageMultipleSaveDTO.getFiles().forEach(file -> {
	//			log.info("\tName: {}", file.getOriginalFilename());
	//		});
	//
	//		return imageMultipleSaveDTO.getFiles().stream()
	//				.map(file -> storageService.store(new ImageSaveDTO(file, imageMultipleSaveDTO.getUploaderId())))
	//				.collect(Collectors.toList());
	//	}

	//	@PostMapping(value = "/images/batch", consumes = "multipart/form-data")
	//	public List<Long> storeImages(@RequestPart("uploaderId") Long uploaderId,
	//			@RequestPart("images") MultipartFile[] images)
	//	{
	//		ImageMultipleSaveDTO imageMultipleSaveDTO = new ImageMultipleSaveDTO(Arrays.asList(images), uploaderId);
	//		log.info("REST request to batch-save images...");
	//		imageMultipleSaveDTO.getFiles().forEach(file -> {
	//			log.info("\tName: {}", file.getOriginalFilename());
	//		});
	//
	//		return imageMultipleSaveDTO.getFiles().stream()
	//				.map(file -> storageService.store(new ImageSaveDTO(file, imageMultipleSaveDTO.getUploaderId())))
	//				.collect(Collectors.toList());
	//	}

	@PostMapping(value = "/images/batch", consumes = "multipart/form-data")
	public List<Long> storeImages(@RequestPart("uploaderId") Long uploaderId,
			@RequestPart("images") MultipartFile[] images)
	{
		ImageMultipleSaveDTO imageMultipleSaveDTO = new ImageMultipleSaveDTO(Arrays.asList(images), 1l);
		log.info("REST request to batch-save images...");
		imageMultipleSaveDTO.getFiles().forEach(file -> {
			log.info("\tName: {}", file.getOriginalFilename());
		});

		return imageMultipleSaveDTO.getFiles().stream().map(file -> storageService.store(new ImageSaveDTO(file, 1l)))
				.collect(Collectors.toList());
	}
}
