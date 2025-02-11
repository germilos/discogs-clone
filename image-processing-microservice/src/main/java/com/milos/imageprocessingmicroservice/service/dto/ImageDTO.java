package com.milos.imageprocessingmicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO
{
	private Long id;
	private LocalDateTime uploadDate;
	private Long itemId;
	private Long uploaderId;
}
