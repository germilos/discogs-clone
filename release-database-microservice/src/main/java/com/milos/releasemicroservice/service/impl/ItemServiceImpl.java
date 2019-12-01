package com.milos.releasemicroservice.service.impl;

import com.milos.releasemicroservice.domain.*;
import com.milos.releasemicroservice.exception.ItemNotFoundException;
import com.milos.releasemicroservice.repo.ItemRepository;
import com.milos.releasemicroservice.repo.ReleaseRepository;
import com.milos.releasemicroservice.repo.UserRepository;
import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import com.milos.releasemicroservice.repo.criteria.specification.SearchSpecification;
import com.milos.releasemicroservice.service.ItemService;
import com.milos.releasemicroservice.service.dto.*;
import com.milos.releasemicroservice.service.mapper.factory.ArtistMapperFactory;
import com.milos.releasemicroservice.service.mapper.factory.LabelMapperFactory;
import com.milos.releasemicroservice.service.mapper.factory.ReleaseMapperFactory;
import com.milos.releasemicroservice.util.ImageDTO;
import com.milos.releasemicroservice.util.ImageMultipleSaveDTO;
import com.milos.releasemicroservice.util.ImageProcessingServiceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService
{
	private final ItemRepository itemRepository;
	private final ReleaseRepository releaseRepository;
	private final UserRepository userRepository;

	private final ReleaseMapperFactory releaseMapperFactory;
	private final ArtistMapperFactory artistMapperFactory;
	private final LabelMapperFactory labelMapperFactory;

	private final ImageProcessingServiceProxy imageProcessingServiceProxy;

	public ItemServiceImpl(final ItemRepository itemRepository, final ReleaseRepository releaseRepository,
			final UserRepository userRepository, final ArtistMapperFactory artistMapperFactory,
			final LabelMapperFactory labelMapperFactory, final ReleaseMapperFactory releaseMapperFactory,
			final ImageProcessingServiceProxy imageProcessingServiceProxy)
	{
		this.itemRepository = itemRepository;
		this.releaseRepository = releaseRepository;
		this.userRepository = userRepository;
		this.releaseMapperFactory = releaseMapperFactory;
		this.artistMapperFactory = artistMapperFactory;
		this.labelMapperFactory = labelMapperFactory;
		this.imageProcessingServiceProxy = imageProcessingServiceProxy;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemInExploreDTO> getAll()
	{
		List<Item> items = itemRepository.findAll();
		List<ItemInExploreDTO> itemsDTO = convertItemsToDTOs(items);

		return itemsDTO;
	}

	@Override
	public List<ItemInExploreDTO> getAll(Sort sort)
	{
		List<Item> sortedItems = itemRepository.findAll(sort);
		return convertItemsToDTOs(sortedItems);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemInExploreDTO> getByCriteria(SearchCriteria criteria)
	{
		SearchSpecification searchSpecification = new SearchSpecification(criteria);
		List<Item> items = itemRepository.findAll(searchSpecification);
		return createItemInExploreDTOs(items);
	}

	@Override
	public Page<ItemInExploreDTO> getAll(Pageable pageable)
	{
		Page<Item> itemPage = itemRepository.findAll(pageable);
		return itemPage.map(this::createItemInExploreDTO);
	}

	@Override
	public Page<ItemInExploreDTO> getAll(SearchCriteria searchCriteria, Pageable pageable)
	{
		SearchSpecification searchSpecification = new SearchSpecification(searchCriteria);
		Page<Item> itemPage = itemRepository.findAll(searchSpecification, pageable);

		return itemPage.map(this::createItemInExploreDTO);
	}

	@Override
	@Transactional(readOnly = true)
	public ItemDetailDTO getById(Long id)
	{
		Optional<Item> itemOptional = itemRepository.findById(id);
		if (!itemOptional.isPresent())
		{
			throw new ItemNotFoundException(id);
		}
		Item item = itemOptional.get();
		return createDetailDTO(item);
	}

	@Override
	public List<ReleaseInItemDTO> getReleasesByArtistId(Long artistId)
	{
		List<Release> releases = releaseRepository.findByArtistId(artistId);
		if (releases == null)
		{
			throw new RuntimeException("There was an error retrieving releases for label: " + artistId);
		}
		return createReleaseInItemDTOs(releases);
	}

	@Override
	public List<ReleaseInItemDTO> getReleasesByLabelId(Long labelId)
	{
		List<Release> releases = releaseRepository.findByLabelId(labelId);
		if (releases == null)
		{
			throw new RuntimeException("There was an error retrieving releases for label: " + labelId);
		}
		return createReleaseInItemDTOs(releases);
	}

	@Override
	public ItemDetailDTO save(ReleaseSaveDTO releaseSaveDTO, MultipartFile[] images)
	{
		Release releaseToSave = (Release) releaseMapperFactory.resolveEntityMapper("Save").toEntity(releaseSaveDTO);

		placeExistingEntitiesInRelease(releaseToSave);
		Item savedRelease = itemRepository.save(releaseToSave);

//		List<ImageDTO> imageDTOS = imageProcessingServiceProxy.storeImages(releaseSaveDTO.getContributor().getId(),
//				savedRelease.getId(), releaseSaveDTO.getImages());
		List<ImageDTO> imageDTOS = imageProcessingServiceProxy.storeImages(releaseSaveDTO.getContributor().getId(),
				savedRelease.getId(), images);
		savedRelease.getImages()
				.addAll(imageDTOS.stream().map(this::convertImageDTOToImage).collect(Collectors.toList()));

		itemRepository.save(savedRelease);

		return createDetailDTO(savedRelease);
	}

	private Image convertImageDTOToImage(ImageDTO imageDTO)
	{
		Optional<User> userOptional = userRepository.findById(imageDTO.getUploaderId());
		return new Image(imageDTO.getId(), imageDTO.getUploadDate(), userOptional.orElseThrow(RuntimeException::new));
	}

	@Override
	public Item update(Item item)
	{
		Optional<Item> itemOptional = itemRepository.findById(item.getId());

		if (!itemOptional.isPresent())
		{
			throw new ItemNotFoundException(item.getId(), item.getItemType());
		}
		Item retrievedItem = itemOptional.get();
		retrievedItem = item;

		return retrievedItem;
	}

	private void placeExistingEntitiesInRelease(Release release)
	{
		if (release.getArtist().getId() != null)
		{
			release.setArtist((Artist) addExistingItemToRelease(release.getArtist().getId()));
		}
		if (release.getLabel().getId() != null)
		{
			release.setLabel((Label) addExistingItemToRelease(release.getLabel().getId()));
		}
	}

	private Item addExistingItemToRelease(Long id)
	{
		Optional<Item> itemOptional = itemRepository.findById(id);
		if (itemOptional.isPresent())
		{
			return itemOptional.get();
		}
		else
		{
			throw new ItemNotFoundException(id);
		}
	}

	private List<ItemInExploreDTO> convertItemsToDTOs(List<Item> items)
	{
		return items.stream().map(this::createItemInExploreDTO).collect(Collectors.toList());
	}

	private List<ReleaseInItemDTO> createReleaseInItemDTOs(List<Release> releases)
	{
		return (List<ReleaseInItemDTO>) releaseMapperFactory.resolveEntityMapper("InItem").toDto(releases);
	}

	private ItemDetailDTO createDetailDTO(Item item)
	{
		switch (item.getItemType())
		{
		case "Release":
			return (ReleaseDetailDTO) releaseMapperFactory.resolveEntityMapper("Detail").toDto(item);
		case "Artist":
			return (ArtistDetailDTO) artistMapperFactory.resolveEntityMapper("Detail").toDto(item);
		case "Label":
			return (LabelDetailDTO) labelMapperFactory.resolveEntityMapper("Detail").toDto(item);
		default:
			throw new RuntimeException("Could not identify item type!");
		}
	}

	private ItemInExploreDTO createItemInExploreDTO(Item item)
	{
		switch (item.getItemType())
		{
		case "Release":
			return (ItemInExploreDTO) releaseMapperFactory.resolveEntityMapper("Explore").toDto(item);
		case "Artist":
			return (ItemInExploreDTO) artistMapperFactory.resolveEntityMapper("Explore").toDto(item);
		case "Label":
			return (ItemInExploreDTO) labelMapperFactory.resolveEntityMapper("Explore").toDto(item);
		default:
			throw new RuntimeException("Could not identify item type!");
		}
	}

	private List<ItemInExploreDTO> createItemInExploreDTOs(List<Item> items)
	{
		switch (items.get(0).getItemType())
		{
		case "Release":
			return (List<ItemInExploreDTO>) releaseMapperFactory.resolveEntityMapper("Explore").toDto(items);
		case "Artist":
			return (List<ItemInExploreDTO>) artistMapperFactory.resolveEntityMapper("Explore").toDto(items);
		case "Label":
			return (List<ItemInExploreDTO>) labelMapperFactory.resolveEntityMapper("Explore").toDto(items);
		default:
			throw new RuntimeException("Could not identify item type!");
		}
	}

}
