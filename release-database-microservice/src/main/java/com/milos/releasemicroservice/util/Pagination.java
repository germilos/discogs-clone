package com.milos.releasemicroservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination
{
	private int page = 0;
	private int size = 10;
	private String orderBy = "dateAdded";
	private String direction = "ASC";

	public Sort.Direction resolveDirection()
	{
		return this.direction == "ASC" ? Sort.Direction.ASC : Sort.Direction.DESC;
	}
}
