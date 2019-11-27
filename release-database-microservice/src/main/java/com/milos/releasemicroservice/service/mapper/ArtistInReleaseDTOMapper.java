package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Artist;
import com.milos.releasemicroservice.service.dto.ArtistInReleaseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistInReleaseDTOMapper extends EntityMapper<ArtistInReleaseDTO, Artist>
{
	ArtistInReleaseDTO toDto(Artist entity);

	default Artist toEntity(ArtistInReleaseDTO dto)
	{
		return null;
	}
}
