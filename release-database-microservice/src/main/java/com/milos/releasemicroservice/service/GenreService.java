package com.milos.releasemicroservice.service;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.service.dto.GenreSaveItemDTO;

import java.util.List;

public interface GenreService
{
	List<GenreSaveItemDTO> getAll();

	Genre getById(Long id);
}
