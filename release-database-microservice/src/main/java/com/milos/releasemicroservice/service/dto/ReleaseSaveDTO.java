package com.milos.releasemicroservice.service.dto;

import com.milos.releasemicroservice.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ReleaseSaveDTO
{
	private String name;
	private String itemType;
	private User contributor;
	//	private Set<Image> images;
	//	private List<MultipartFile> imageFiles;
	private MultipartFile[] imageFiles;
	//	private ImageMultipleSaveDTO images;
	private String format;
	private String country;
	private String notes;
	private String released;
	private ArtistInReleaseDTO artist;
	private LabelInReleaseDTO label;
	private List<GenreSaveItemDTO> genres;
	private List<StyleSaveItemDTO> styles;
	private List<TrackInReleaseDTO> tracks;

	public ReleaseSaveDTO()
	{
		this.genres = new ArrayList<>();
		this.styles = new ArrayList<>();
		this.tracks = new ArrayList<>();
		//		this.images = new HashSet<>();
	}
}