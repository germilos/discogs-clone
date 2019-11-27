package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Track;
import com.milos.releasemicroservice.service.dto.TrackInReleaseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrackInReleaseDTOMapper extends EntityMapper<TrackInReleaseDTO, Track>
{
	TrackInReleaseDTO toDto(Track entity);

	default Track toEntity(TrackInReleaseDTO dto)
	{
		return null;
	}
}
