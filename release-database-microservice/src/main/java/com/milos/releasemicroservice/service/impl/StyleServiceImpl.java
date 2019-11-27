package com.milos.releasemicroservice.service.impl;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.domain.Style;
import com.milos.releasemicroservice.repo.StyleRepository;
import com.milos.releasemicroservice.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleServiceImpl implements StyleService
{

	private final StyleRepository styleRepository;

	public StyleServiceImpl(final StyleRepository styleRepository)
	{
		this.styleRepository = styleRepository;
	}

	@Override
	public List<Style> getAll()
	{
		return styleRepository.findAll();
	}

	@Override
	public List<Style> getByGenreId(Genre genre)
	{
		return styleRepository.findByGenreId(genre);
	}

	@Override
	public List<Style> getByGenreName(Genre genre)
	{
		return styleRepository.findByGenreName(genre);
	}

	@Override
	public Style getById(Long id)
	{
		return styleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Could not find style with ID: " + id));
	}
}
