package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.*;
import com.milos.releasemicroservice.service.dto.ReleaseSaveDTO;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ReleaseSaveDTOMapper extends EntityMapper<ReleaseSaveDTO, Release> {
    default Release toEntity(ReleaseSaveDTO dto) {
        Release release = new Release();

        release.setName(dto.getName());
        release.setItemType("Release");
        release.setFormat(dto.getFormat());
        release.setReleased(dto.getReleased());
        release.setCountry(dto.getCountry());
        release.setDateAdded(LocalDate.now());
        release.setDateLastChanged(LocalDate.now());
        release.setStyles(
                dto.getStyles().stream().map(styleDto -> new Style(styleDto.getId(), styleDto.getName(), null, null))
                        .collect(Collectors.toList()));
        release.setGenres(
                dto.getGenres().stream().map(genreDto -> new Genre(genreDto.getId(), genreDto.getName(), null, null))
                        .collect(Collectors.toList()));
//        release.setImages(dto.getImageFiles());
        release.addContributor(dto.getContributor());
        release.setTracks(dto.getTracks().stream()
                .map(trackDto -> new Track(trackDto.getTitle(), trackDto.getDuration(), trackDto.getPosition()))
                .collect(Collectors.toList()));

        Artist artist = new Artist();
        if (dto.getArtist() != null) {
            if (dto.getArtist().getId() != null) {
                artist.setId(dto.getArtist().getId());
            } else {
                artist.setDateAdded(LocalDate.now());
                artist.setDateLastChanged(LocalDate.now());
                artist.setName(dto.getArtist().getName());
                artist.setItemType("Artist");
            }
        }
        release.setArtist(artist);

        Label label = new Label();
        if (dto.getLabel() != null) {
            if (dto.getLabel().getId() != null) {
                label.setId(dto.getLabel().getId());
            } else {
                label.setDateAdded(LocalDate.now());
                label.setDateLastChanged(LocalDate.now());
                label.setName(dto.getLabel().getName());
                label.setItemType("Label");
            }
        }
        release.setLabel(label);

        return release;
    }
}
