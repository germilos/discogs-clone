package com.milos.imageprocessingmicroservice.service.impl;

import com.milos.imageprocessingmicroservice.config.StorageProperties;
import com.milos.imageprocessingmicroservice.domain.Image;
import com.milos.imageprocessingmicroservice.exception.StorageException;
import com.milos.imageprocessingmicroservice.exception.StorageFileNotFoundException;
import com.milos.imageprocessingmicroservice.repo.ImageRepository;
import com.milos.imageprocessingmicroservice.service.StorageService;
import com.milos.imageprocessingmicroservice.service.dto.ImageDTO;
import com.milos.imageprocessingmicroservice.service.dto.ImageSaveDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileSystemStorageService implements StorageService {

    private final ImageRepository imageRepository;
    private final Path rootLocation;
    private final StorageProperties storageProperties;

    public FileSystemStorageService(final ImageRepository imageRepository, final StorageProperties storageProperties) {
        this.imageRepository = imageRepository;
        this.storageProperties = storageProperties;
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public ImageDTO store(ImageSaveDTO imageSaveDTO) {
        BufferedImage originalImage = getBufferedImage(imageSaveDTO.getFile());

        String extension = imageSaveDTO.getFile().getOriginalFilename()
                .substring(imageSaveDTO.getFile().getOriginalFilename().lastIndexOf(".") + 1);
        String nameWithoutExtension = imageSaveDTO.getFile().getOriginalFilename().substring(0,
                imageSaveDTO.getFile().getOriginalFilename().lastIndexOf("."));

        Image savedImage = new Image(nameWithoutExtension, extension, originalImage.getWidth(),
                originalImage.getHeight(), null, imageSaveDTO.getUploaderId(), LocalDateTime.now(),
                imageSaveDTO.getItemId());
        savedImage = imageRepository.save(savedImage);

        Path path = generatePath(savedImage);
        storeInFileSystem(imageSaveDTO.getFile(), path);

        savedImage.setUri(path.toString());
        imageRepository.save(savedImage);

        ImageDTO imageDTO = new ImageDTO(savedImage.getId(), savedImage.getDateAdded(), savedImage.getItemId(),
                savedImage.getUploaderId());
        return imageDTO;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    private Path generatePath(Image image) {
        StringBuffer sb = new StringBuffer();
        sb.append(image.getId()).append(".").append(image.getExtension());

        return this.rootLocation.resolve(sb.toString());
    }

    private BufferedImage getBufferedImage(MultipartFile file) {
        BufferedImage bufferedImage = null;

        try (InputStream in = new ByteArrayInputStream(file.getBytes())) {
            bufferedImage = ImageIO.read(in);

            if (bufferedImage == null) throw new RuntimeException("There was an error buffering image!");
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void storeInFileSystem(MultipartFile file, Path path) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }
}
