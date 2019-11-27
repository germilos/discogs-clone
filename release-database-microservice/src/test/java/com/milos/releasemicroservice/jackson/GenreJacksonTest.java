package com.milos.releasemicroservice.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milos.releasemicroservice.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreJacksonTest
{
	Genre genre;
	ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		genre = new Genre();
		objectMapper = new ObjectMapper();
	}

	@Test
	public void givenNewGenre_whenSerializing_thenCorrect() {

	}

}
