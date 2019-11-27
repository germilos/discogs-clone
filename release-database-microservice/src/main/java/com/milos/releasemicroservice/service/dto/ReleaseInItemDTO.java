package com.milos.releasemicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReleaseInItemDTO extends RepresentationModel<ReleaseInItemDTO> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long releaseId;
	private String name;
	private ArtistInReleaseDTO artist;
	private LabelInReleaseDTO label;
	private String released;
}
