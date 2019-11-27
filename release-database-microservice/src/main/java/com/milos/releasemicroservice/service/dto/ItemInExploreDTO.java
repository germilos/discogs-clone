package com.milos.releasemicroservice.service.dto;

import com.milos.releasemicroservice.domain.Image;
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
public class ItemInExploreDTO extends RepresentationModel<ItemInExploreDTO> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long itemId;
	private String name;
	private ArtistInReleaseDTO artist;
	private Image thumbnail;
	private String itemType;
}
