package com.milos.releasemicroservice.service.dto;

import com.milos.releasemicroservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ReleaseUpdateDTO
{
	private String name;
	private String itemType;
	private User contributor;
	private String format;
	private String country;
	private String notes;
	private String released;
	private ArtistInReleaseDTO artist;
	private LabelInReleaseDTO label;
	private List<GenreSaveItemDTO> genres;
	private List<StyleSaveItemDTO> styles;
	private List<TrackInReleaseDTO> tracks;

	public ReleaseUpdateDTO()
	{
		this.genres = new ArrayList<>();
		this.styles = new ArrayList<>();
		this.tracks = new ArrayList<>();
	}
}
