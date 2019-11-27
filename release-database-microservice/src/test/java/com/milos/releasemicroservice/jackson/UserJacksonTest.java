package com.milos.releasemicroservice.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milos.releasemicroservice.domain.User;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

public class UserJacksonTest
{
	User user;

	@Mock
	ObjectMapper objectMapper;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		user = new User();
	}

	@Test
	public void givenNewUser_whenSerializing_thenCorrect() throws JsonProcessingException
	{
		user.setId(1l);
		user.setUsername("jondoe");
		String json = "{\"id\":1,\"username\":\"jondoe\"}";

		when(objectMapper.writeValueAsString(user)).thenReturn(json);
		String result = objectMapper.writeValueAsString(user);

		assertThat(result).isEqualTo(result);
	}

	@Test
	public void givenJson_whenUsingJacksonDeserialization_thenCorrect() throws IOException
	{
		String json = "{\"id\":1,\"username\":\"jondoe\"}";
		user.setId(1l);
		user.setUsername("jondoe");

		when(objectMapper.readValue(json, User.class)).thenReturn(user);
		User readUser = objectMapper.readValue(json, User.class);

		assertThat(readUser).isEqualTo(user);
	}
}
