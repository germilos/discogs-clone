package com.milos.releasemicroservice.service;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.domain.Style;

import java.util.List;

public interface StyleService
{
	List<Style> getAll();

	List<Style> getByGenreId(Genre genre);

	List<Style> getByGenreName(Genre genre);

	Style getById(Long id);
}
