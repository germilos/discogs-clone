package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Label;
import com.milos.releasemicroservice.service.dto.LabelInReleaseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelInReleaseDTOMapper extends EntityMapper<LabelInReleaseDTO, Label>
{
	LabelInReleaseDTO toDto(Label entity);

	default Label toEntity(LabelInReleaseDTO dto)
	{
		return null;
	}
}
