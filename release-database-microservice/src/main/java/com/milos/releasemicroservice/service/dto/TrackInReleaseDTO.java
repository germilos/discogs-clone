package com.milos.releasemicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrackInReleaseDTO extends RepresentationModel<TrackInReleaseDTO> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String title;
	private String position;
	private String duration;
}
