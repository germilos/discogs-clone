package com.milos.releasemicroservice.service.dto;

import com.milos.releasemicroservice.domain.Image;
import com.milos.releasemicroservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class ItemDetailDTO extends RepresentationModel<ItemDetailDTO>
{
	protected Long itemId;
	protected String name;
	protected Set<User> contributors;
	protected Set<Image> images;
	protected String itemType;
}