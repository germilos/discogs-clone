package com.milos.releasemicroservice.service.impl;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.repo.GenreRepository;
import com.milos.releasemicroservice.service.GenreService;
import com.milos.releasemicroservice.service.dto.GenreSaveItemDTO;
import com.milos.releasemicroservice.service.mapper.GenreSaveItemDTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService
{
	private final GenreRepository genreRepository;
	private final GenreSaveItemDTOMapper genreSaveItemDTOMapper;

	public GenreServiceImpl(final GenreRepository genreRepository, final GenreSaveItemDTOMapper genreSaveItemDTOMapper)
	{
		this.genreRepository = genreRepository;
		this.genreSaveItemDTOMapper = genreSaveItemDTOMapper;
	}

	@Override
	public List<GenreSaveItemDTO> getAll()
	{
		List<Genre> genres = genreRepository.findAll();
		return genreSaveItemDTOMapper.toDto(genres);
	}

	@Override
	public Genre getById(Long id)
	{
		return genreRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Could not find genre with ID: " + id));
	}
}
