package com.milos.releasemicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StyleSaveItemDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
}
