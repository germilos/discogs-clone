package com.milos.imageprocessingmicroservice.repo;

import com.milos.imageprocessingmicroservice.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long>
{
	List<Image> findByItemId(Long itemId);
}
