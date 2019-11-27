package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Release;
import com.milos.releasemicroservice.service.dto.ReleaseInItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ArtistInReleaseDTOMapper.class, LabelInReleaseDTOMapper.class })
public interface ReleaseInItemDTOMapper extends EntityMapper<ReleaseInItemDTO, Release>
{

	@Override
	default Release toEntity(ReleaseInItemDTO dto)
	{
		return null;
	}

	@Mappings({ @Mapping(target = "releaseId", source = "id") })
	ReleaseInItemDTO toDto(Release entity);

	List<ReleaseInItemDTO> toDto(List<Release> entityList);
}
