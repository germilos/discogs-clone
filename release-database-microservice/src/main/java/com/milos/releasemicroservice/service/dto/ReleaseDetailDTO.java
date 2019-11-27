package com.milos.releasemicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReleaseDetailDTO extends ItemDetailDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String format;
	private String country;
	private String notes;
	private String released;
	private ArtistInReleaseDTO artist;
	private LabelInReleaseDTO label;
	private List<String> genres;
	private List<String> styles;
	private List<TrackInReleaseDTO> tracks;
}
