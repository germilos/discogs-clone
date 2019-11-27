package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Artist;
import com.milos.releasemicroservice.service.dto.ArtistDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReleaseInItemDTOMapper.class)
public interface ArtistDetailDTOMapper extends EntityMapper<ArtistDetailDTO, Artist>
{
	default Artist toEntity(ArtistDetailDTO dto)
	{
		return new Artist();
	};

	ArtistDetailDTO toDto(Artist entity);

	List<ArtistDetailDTO> toDto(List<Artist> entityList);
}
