package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.service.dto.GenreSaveItemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreSaveItemDTOMapper extends EntityMapper<GenreSaveItemDTO, Genre>
{
	default GenreSaveItemDTO toDto(Genre entity)
	{
		return null;
	}

	Genre toEntity(GenreSaveItemDTOMapper dto);

	List<GenreSaveItemDTO> toDto(List<Genre> entityList);
}
