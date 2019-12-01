package com.milos.releasemicroservice.controller;

import com.milos.releasemicroservice.service.ItemService;
import com.milos.releasemicroservice.service.dto.ItemDetailDTO;
import com.milos.releasemicroservice.service.dto.ReleaseDetailDTO;
import com.milos.releasemicroservice.service.dto.ReleaseSaveDTO;
import com.milos.releasemicroservice.service.dto.ReleaseSaveDTOTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/releases")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ReleaseRestController {
    private final ItemService itemService;

    public ReleaseRestController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseDetailDTO> getById(@PathVariable Long id) {
        ItemDetailDTO itemToReturn = itemService.getById(id);
        if (!(itemToReturn instanceof ReleaseDetailDTO)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        addLinksToReleaseDTO(itemToReturn);

        return new ResponseEntity<>((ReleaseDetailDTO) itemToReturn, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<ReleaseDetailDTO> save(@RequestPart("releaseSaveDTO") ReleaseSaveDTO releaseSaveDTO, @RequestPart("images") MultipartFile[] images) throws IOException {
        System.out.println("Release: " + releaseSaveDTO.getName() + " Type: " + releaseSaveDTO.getItemType() + " " + releaseSaveDTO.getArtist().getName());
        for (MultipartFile image : images) {
            System.out.println("Image: " + image.getName() + " " + image.getOriginalFilename() + " " + image.getBytes());
        }
        ReleaseDetailDTO releaseDetailDTO = (ReleaseDetailDTO) itemService.save(releaseSaveDTO, images);
        return new ResponseEntity<>(releaseDetailDTO, HttpStatus.CREATED);
    }

    private void addLinksToReleaseDTO(ItemDetailDTO itemDTO) {
        Link selfRef = linkTo(ReleaseRestController.class).slash(itemDTO.getItemId()).withSelfRel();
        Link linkToArtist = linkTo(
                methodOn(ArtistRestController.class).getById(((ReleaseDetailDTO) itemDTO).getArtist().getId()))
                .withRel("artist");
        Link linkToLabel = linkTo(
                methodOn(LabelRestController.class).getById(((ReleaseDetailDTO) itemDTO).getLabel().getId()))
                .withRel("label");
        itemDTO.add(selfRef, linkToArtist, linkToLabel);
    }
}
