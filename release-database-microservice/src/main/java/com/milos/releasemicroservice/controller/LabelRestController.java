package com.milos.releasemicroservice.controller;

import com.milos.releasemicroservice.service.ItemService;
import com.milos.releasemicroservice.service.dto.ItemDetailDTO;
import com.milos.releasemicroservice.service.dto.LabelDetailDTO;
import com.milos.releasemicroservice.service.dto.ReleaseInItemDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

@RestController
@RequestMapping("/v1/labels")
public class LabelRestController
{
	private final ItemService itemService;

	public LabelRestController(final ItemService itemService)
	{
		this.itemService = itemService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<LabelDetailDTO> getById(@PathVariable Long id)
	{
		ItemDetailDTO itemToReturn = itemService.getById(id);
		if (!(itemToReturn instanceof LabelDetailDTO))
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>((LabelDetailDTO) itemToReturn, HttpStatus.OK);
	}

	@GetMapping("/{id}/releases")
	public ResponseEntity<EntityModel<List<ReleaseInItemDTO>>> getReleases(@PathVariable Long id)
	{
		List<ReleaseInItemDTO> releases = itemService.getReleasesByLabelId(id);
		releases.stream().map(release -> {
			release.add(linkTo(ReleaseRestController.class).slash(release.getReleaseId()).withSelfRel());
			return release;
		});
		return new ResponseEntity<>(new EntityModel<>(releases), HttpStatus.OK);
	}
}
