package com.milos.imageprocessingmicroservice.repo;

import com.milos.imageprocessingmicroservice.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>
{
}
