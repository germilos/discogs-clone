package com.milos.releasemicroservice.controller;

import com.milos.releasemicroservice.service.ItemService;
import com.milos.releasemicroservice.service.dto.ArtistDetailDTO;
import com.milos.releasemicroservice.service.dto.ItemDetailDTO;
import com.milos.releasemicroservice.service.dto.ReleaseInItemDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;


@RestController
@RequestMapping("/v1/artists")
public class ArtistRestController
{
	private final ItemService itemService;

	public ArtistRestController(final ItemService itemService)
	{
		this.itemService = itemService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArtistDetailDTO> getById(@PathVariable Long id)
	{
		ItemDetailDTO itemToReturn = itemService.getById(id);
		if (!(itemToReturn instanceof ArtistDetailDTO))
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>((ArtistDetailDTO) itemToReturn, HttpStatus.OK);
	}

	@GetMapping("/{id}/releases")
	public ResponseEntity<EntityModel<List<ReleaseInItemDTO>>> getReleases(@PathVariable Long id)
	{
		List<ReleaseInItemDTO> releases = itemService.getReleasesByArtistId(id);
		releases.stream().map(release -> {
			release.add(linkTo(ReleaseRestController.class).slash(release.getReleaseId()).withSelfRel());
			return release;
		});
		return new ResponseEntity<>(new EntityModel<>(releases), HttpStatus.OK);
	}
}
