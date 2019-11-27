package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Release;
import com.milos.releasemicroservice.service.dto.ArtistInReleaseDTO;
import com.milos.releasemicroservice.service.dto.LabelInReleaseDTO;
import com.milos.releasemicroservice.service.dto.ReleaseDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.stream.Collectors;

@Mapper(imports = Collectors.class, componentModel = "spring", uses = { TrackInReleaseDTOMapper.class,
		LabelInReleaseDTO.class, ArtistInReleaseDTO.class })
public interface ReleaseDetailDTOMapper extends EntityMapper<ReleaseDetailDTO, Release>
{
	default Release toEntity(ReleaseDetailDTO releaseDetailDTO)
	{
		return new Release();
	};

	@Mappings({
			@Mapping(target = "genres", expression = "java( release.getGenres().stream().map(el -> el.getName()).collect(Collectors.toList()) )"),
			@Mapping(target = "styles", expression = "java( release.getStyles().stream().map(el -> el.getName()).collect(Collectors.toList()) )") })
	ReleaseDetailDTO toDto(Release release);
}
