package com.milos.imageprocessingmicroservice.service;

import com.milos.imageprocessingmicroservice.service.dto.ImageDTO;
import com.milos.imageprocessingmicroservice.service.dto.ImageSaveDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    Long store(ImageSaveDTO imageSaveDTO);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
