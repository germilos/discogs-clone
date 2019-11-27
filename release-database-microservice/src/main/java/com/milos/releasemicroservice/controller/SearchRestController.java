package com.milos.releasemicroservice.controller;

import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import com.milos.releasemicroservice.service.ItemService;
import com.milos.releasemicroservice.service.dto.ItemInExploreDTO;
import com.milos.releasemicroservice.util.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/v1/search")
public class SearchRestController
{
	private final ItemService itemService;

	public SearchRestController(final ItemService itemService)
	{
		this.itemService = itemService;
	}

	@GetMapping(value = "/")
	public ResponseEntity<EntityModel<Page<ItemInExploreDTO>>> getAll(SearchCriteria searchCriteria, Pagination pagination)
	{
		Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(),
				Sort.by(pagination.resolveDirection(), pagination.getOrderBy()));
		Page<ItemInExploreDTO> items = itemService.getAll(searchCriteria, pageable);
		addLinksToItems(items);

		return new ResponseEntity<>(new EntityModel<>(items), HttpStatus.OK);
	}

	private void addLinksToItems(Page<ItemInExploreDTO> items)
	{
		items.map(item -> {
			if (item.getArtist() != null)
			{
				Link artistLink = linkTo((ArtistRestController.class)).slash(item.getArtist().getId())
						.withRel("artist");
				item.add(artistLink);
			}
			Link selfRef = null;
			if (item.getItemType() == "Release")
			{
				selfRef = linkTo(ReleaseRestController.class).slash(item.getItemId()).withSelfRel();
			}
			else if (item.getItemType() == "Artist")
			{
				selfRef = linkTo(ArtistRestController.class).slash(item.getItemId()).withSelfRel();
			}
			else
			{
				selfRef = linkTo(LabelRestController.class).slash(item.getItemId()).withSelfRel();
			}
			item.add(selfRef);

			return item;
		});
	}
}
