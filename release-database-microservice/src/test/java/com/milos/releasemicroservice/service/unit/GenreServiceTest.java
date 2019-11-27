package com.milos.releasemicroservice.service.unit;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.repo.GenreRepository;
import com.milos.releasemicroservice.service.GenreService;
import com.milos.releasemicroservice.service.dto.GenreSaveItemDTO;
import com.milos.releasemicroservice.service.impl.GenreServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.milos.releasemicroservice.service.mapper.GenreSaveItemDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreServiceTest
{

	@Mock
	GenreRepository genreRepository;
	@Mock
	GenreSaveItemDTOMapper genreSaveItemDTOMapper;

	GenreService genreService;

	@BeforeEach
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		this.genreService = new GenreServiceImpl(genreRepository, genreSaveItemDTOMapper);
	}

	@Test
	public void whenGetAllGenres_thenReturnAllGenres()
	{
		List<Genre> mockGenres = new ArrayList<>();
		mockGenres.add(new Genre());
		mockGenres.add(new Genre());
		mockGenres.add(new Genre());

		List<GenreSaveItemDTO> mockGenreDTOs = new ArrayList<>();
		mockGenreDTOs.add(new GenreSaveItemDTO(1l, "Mock Genre DTO"));
		mockGenreDTOs.add(new GenreSaveItemDTO(2l, "Mock Genre DTO Two"));
		mockGenreDTOs.add(new GenreSaveItemDTO(3l, "Mock Genre DTO Three"));

		when(genreRepository.findAll()).thenReturn(mockGenres);
		when(genreSaveItemDTOMapper.toDto(mockGenres)).thenReturn(mockGenreDTOs);
		List<GenreSaveItemDTO> genres = genreService.getAll();

		assertThat(genres.size()).isEqualTo(3);
		verify(genreRepository, times(1)).findAll();
	}

	@Test
	public void givenId_whenRetrievingGenre_thenReturnGenreWithId()
	{
		Long id = 1l;

		Genre genre = new Genre();
		genre.setId(id);
		Optional<Genre> optionalGenre = Optional.of(genre);

		when(genreRepository.findById(id)).thenReturn(optionalGenre);

		Genre retrievedGenre = genreService.getById(id);

		assertThat(retrievedGenre).isNotNull();
		assertThat(retrievedGenre.getId()).isEqualTo(id);
	}
}
