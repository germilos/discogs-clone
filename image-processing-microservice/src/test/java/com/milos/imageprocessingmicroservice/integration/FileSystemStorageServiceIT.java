package com.milos.imageprocessingmicroservice.integration;

import com.milos.imageprocessingmicroservice.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FileSystemStorageServiceIT
{
	@Autowired
	private StorageService fileSystemStorageService;

	@Test
	public void givenItemId_whenRetrievingImages_thenReturnImageBytesList()
	{
		Long itemId = 1l;
		List<byte[]> imagesByteList = fileSystemStorageService.loadImagesByItemId(itemId);

		imagesByteList.forEach(bytes -> {
			log.info("Bytes: " + bytes);
		});
		assertThat(imagesByteList).isNotNull();
		assertThat(imagesByteList.size()).isGreaterThan(0);
	}
}
