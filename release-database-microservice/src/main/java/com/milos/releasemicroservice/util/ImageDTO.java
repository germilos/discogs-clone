package com.milos.releasemicroservice.util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
