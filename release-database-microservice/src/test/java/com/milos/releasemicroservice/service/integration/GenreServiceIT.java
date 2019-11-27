package com.milos.releasemicroservice.service.integration;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.service.GenreService;
import com.milos.releasemicroservice.service.dto.GenreSaveItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GenreServiceIT {

    @Autowired
    GenreService genreService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenGetAllGenres_thenReturnAllGenres() {
        List<GenreSaveItemDTO> genres = genreService.getAll();

        assertThat(genres).isNotNull();
        assertThat(genres.size()).isGreaterThan(0);
    }

    @Test
    public void givenId_whenRetrievingGenre_thenReturnGenreWithId() {
        Long id = 1l;

        Genre retrievedGenre = genreService.getById(id);

        assertThat(retrievedGenre).isNotNull();
        assertThat(retrievedGenre.getId()).isEqualTo(id);
    }
}
