package com.milos.releasemicroservice.service.unit;

import com.milos.releasemicroservice.domain.Artist;
import com.milos.releasemicroservice.domain.Item;
import com.milos.releasemicroservice.domain.Label;
import com.milos.releasemicroservice.domain.Release;
import com.milos.releasemicroservice.repo.ItemRepository;
import com.milos.releasemicroservice.repo.ReleaseRepository;
import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import com.milos.releasemicroservice.repo.criteria.specification.SearchSpecification;
import com.milos.releasemicroservice.service.ItemService;
import com.milos.releasemicroservice.service.dto.*;
import com.milos.releasemicroservice.service.impl.ItemServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.milos.releasemicroservice.service.mapper.ItemInExploreDTOMapper;
import com.milos.releasemicroservice.service.mapper.ReleaseDetailDTOMapper;
import com.milos.releasemicroservice.service.mapper.ReleaseInItemDTOMapper;
import com.milos.releasemicroservice.service.mapper.factory.ArtistMapperFactory;
import com.milos.releasemicroservice.service.mapper.factory.LabelMapperFactory;
import com.milos.releasemicroservice.service.mapper.factory.ReleaseMapperFactory;
import com.milos.releasemicroservice.util.ImageProcessingServiceProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

public class ItemServiceTest
{
	@Mock
	private ItemRepository itemRepository;
	@Mock
	private ReleaseRepository releaseRepository;
	@Mock
	private ArtistMapperFactory artistMapperFactory;
	@Mock
	private LabelMapperFactory labelMapperFactory;
	@Mock
	private ReleaseMapperFactory releaseMapperFactory;
	@Mock
	private ReleaseDetailDTOMapper releaseDetailDTOMapper;
	@Mock
	private ReleaseInItemDTOMapper releaseInItemDTOMapper;
	@Mock
	private ItemInExploreDTOMapper itemInExploreDTOMapper;
	@Mock
	private ImageProcessingServiceProxy imageProcessingServiceProxy;

	private SearchCriteria searchCriteria;
	private SearchSpecification searchSpecification;
	private ItemService itemService;

	@BeforeEach
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		this.itemService = new ItemServiceImpl(itemRepository, releaseRepository, artistMapperFactory,
				labelMapperFactory, releaseMapperFactory, imageProcessingServiceProxy);
		this.searchCriteria = new SearchCriteria();
	}

	@Test
	public void whenGetAllItems_thenReturnAllItems()
	{
		List<Item> mockItems = new ArrayList<>();
		Release release = new Release();
		release.setId(1l);
		release.setItemType("Release");
		release.setName("Some release");
		Artist artist = new Artist();
		artist.setId(2l);
		artist.setItemType("Artist");
		artist.setName("Some artist");
		Label label = new Label();
		label.setId(3l);
		label.setItemType("Label");
		label.setName("Some label");

		mockItems.add(release);
		mockItems.add(label);
		mockItems.add(artist);

		List<ItemInExploreDTO> mockItemsDTOs = new ArrayList<>();
		mockItemsDTOs.add(new ItemInExploreDTO());
		mockItemsDTOs.add(new ItemInExploreDTO());
		mockItemsDTOs.add(new ItemInExploreDTO());

		when(itemRepository.findAll()).thenReturn(mockItems);
		when(releaseMapperFactory.resolveEntityMapper("Explore")).thenReturn(itemInExploreDTOMapper);
		when(labelMapperFactory.resolveEntityMapper("Explore")).thenReturn(itemInExploreDTOMapper);
		when(artistMapperFactory.resolveEntityMapper("Explore")).thenReturn(itemInExploreDTOMapper);
		when(itemInExploreDTOMapper.toDto(mockItems)).thenReturn(mockItemsDTOs);

		List<ItemInExploreDTO> items = itemService.getAll();

		assertThat(items.size()).isEqualTo(3);
		verify(itemRepository, times(1)).findAll();
	}

	@Test
	public void givenNewRelease_whenSaving_thenReturnSavedItem()
	{
		Release releaseToReturn = new Release();
		releaseToReturn.setId(1l);
		releaseToReturn.setName("Some release");
		releaseToReturn.setDateAdded(LocalDate.of(2019, 10, 25));
		releaseToReturn.setDateLastChanged(LocalDate.of(2019, 10, 25));
		releaseToReturn.setUri(null);
		releaseToReturn.setArtist(new Artist());
		releaseToReturn.setLabel(new Label());
		releaseToReturn.setCountry("Random country");
		releaseToReturn.setFormat("Vinyl 7'");
		releaseToReturn.setGenres(new ArrayList<>());
		releaseToReturn.setStyles(new ArrayList<>());
		releaseToReturn.setNotes(null);
		releaseToReturn.setReleased("2019");
		releaseToReturn.setTracks(new ArrayList<>());
		releaseToReturn.setContributions(new LinkedList<>());
		releaseToReturn.setImages(new HashSet<>());

		when(itemRepository.save(any(Release.class))).thenReturn(releaseToReturn);

		Item savedItem = itemRepository.save(releaseToReturn);

		assertThat(savedItem).isExactlyInstanceOf(Release.class);
		assertThat(savedItem.getId()).isEqualTo(1l);
		verify(itemRepository, times(1)).save(releaseToReturn);
	}

	@Test
	public void givenId_whenRetrievingItem_thenReturnItemWithId()
	{
		Long id = 1l;
		Release release = new Release();
		release.setId(id);
		release.setItemType("Release");

		Optional<Release> releaseOptional = Optional.of(release);

		ReleaseDetailDTO releaseDetailDTO = new ReleaseDetailDTO();
		releaseDetailDTO.setName("Some name");

		when(itemRepository.findById(id)).thenReturn(releaseOptional);
		when(releaseMapperFactory.resolveEntityMapper("Detail")).thenReturn(releaseDetailDTOMapper);
		when(releaseDetailDTOMapper.toDto(release)).thenReturn(releaseDetailDTO);

		ItemDetailDTO itemDTO = itemService.getById(id);

		assertThat(itemDTO).isExactlyInstanceOf(ReleaseDetailDTO.class);
		assertThat(itemDTO.getName()).isEqualTo("Some name");
		verify(itemRepository, times(1)).findById(id);
	}

	@Test
	public void givenNonExistentId_whenRetrievingItem_thenThrowRuntimeException()
	{
		Long id = 1l;
		Optional<Release> optionalRelease = Optional.ofNullable(null);

		when(itemRepository.findById(id)).thenReturn(optionalRelease);

		assertThrows(RuntimeException.class, () -> {
			itemService.getById(id);
		});
	}

	@Test
	public void givenReleaseItemType_whenRetrievingItemByCriteria_thenReturnOnlyReleases()
	{
		searchCriteria.setItemType("Release");
		searchSpecification = new SearchSpecification(searchCriteria);

		List<Item> itemsToReturn = new ArrayList<>();

		Release firstRelease = new Release();
		firstRelease.setId(1l);
		firstRelease.setItemType("Release");
		firstRelease.setName("Some release");
		Release secondRelease = new Release();
		secondRelease.setId(2l);
		secondRelease.setItemType("Release");
		secondRelease.setName("Some other release");

		itemsToReturn.add(firstRelease);
		itemsToReturn.add(secondRelease);

		List<ItemInExploreDTO> itemInExploreDTOS = new ArrayList<>();
		itemInExploreDTOS.add(new ItemInExploreDTO(1l, "some dto", null, null, "Release"));
		itemInExploreDTOS.add(new ItemInExploreDTO(2l, "some other dto", null, null, "Release"));

		when(itemRepository.findAll(searchSpecification)).thenReturn(itemsToReturn);
		when(releaseMapperFactory.resolveEntityMapper("Explore")).thenReturn(itemInExploreDTOMapper);
		when(itemInExploreDTOMapper.toDto(itemsToReturn)).thenReturn(itemInExploreDTOS);

		List<ItemInExploreDTO> itemsFromService = itemService.getByCriteria(searchCriteria);

		assertThat(itemsFromService).isNotNull();
		assertThat(itemsFromService.size()).isGreaterThan(0);
	}

	@Test
	public void givenOnlyReleaseSearchCriteria_whenRetrievingItemsByCriteria_thenReturnOnlyReleases()
	{
		searchCriteria.setFormat("CD");
		searchCriteria.setCountry("America");

		List<Release> releasesToReturn = new ArrayList<>();
		Release firstRelease = new Release();
		firstRelease.setId(1l);
		firstRelease.setItemType("Release");
		firstRelease.setFormat("CD");
		firstRelease.setCountry("America");
		firstRelease.setDateAdded(LocalDate.of(2011, 5, 5));
		firstRelease.setDateLastChanged(LocalDate.of(2011, 5, 5));
		Release secondRelease = new Release();
		secondRelease.setId(2l);
		secondRelease.setItemType("Release");
		secondRelease.setFormat("CD");
		secondRelease.setCountry("America");
		secondRelease.setDateAdded(LocalDate.of(2011, 5, 5));
		secondRelease.setDateLastChanged(LocalDate.of(2011, 5, 5));

		releasesToReturn.add(firstRelease);
		releasesToReturn.add(secondRelease);

		when(itemRepository.findAll(searchSpecification)).thenReturn(releasesToReturn);

		List<ItemInExploreDTO> foundItems = itemService.getByCriteria(searchCriteria);

		assertThat(foundItems).isNotNull();
		assertThat(foundItems.size()).isGreaterThan(0);
	}

	@Test
	public void givenLabelId_whenRetrievingReleases_thenReturnReleasesByLabelId()
	{
		Long labelId = 1l;

		List<Release> releasesToReturn = new ArrayList<>();
		Release firstRelease = new Release();
		firstRelease.setId(1l);
		firstRelease.setItemType("Release");
		firstRelease.setFormat("CD");
		firstRelease.setCountry("America");
		firstRelease.setDateAdded(LocalDate.of(2011, 5, 5));
		firstRelease.setDateLastChanged(LocalDate.of(2011, 5, 5));
		Release secondRelease = new Release();
		secondRelease.setId(2l);
		secondRelease.setItemType("Release");
		secondRelease.setFormat("CD");
		secondRelease.setCountry("America");
		secondRelease.setDateAdded(LocalDate.of(2011, 5, 5));
		secondRelease.setDateLastChanged(LocalDate.of(2011, 5, 5));
		releasesToReturn.add(firstRelease);
		releasesToReturn.add(secondRelease);

		List<ReleaseInItemDTO> releaseInItemDTOS = new ArrayList<>();
		LabelInReleaseDTO labelInReleaseDTO = new LabelInReleaseDTO(1l, "Given label");

		ReleaseInItemDTO firstDTO = new ReleaseInItemDTO();
		firstDTO.setName("First dto");
		firstDTO.setReleased("2014");
		firstDTO.setReleaseId(1l);
		firstDTO.setArtist(new ArtistInReleaseDTO(1l, "First artist DTO"));
		firstDTO.setLabel(labelInReleaseDTO);
		ReleaseInItemDTO secondDTO = new ReleaseInItemDTO();
		firstDTO.setName("Second dto");
		firstDTO.setReleased("2017");
		firstDTO.setReleaseId(2l);
		firstDTO.setArtist(new ArtistInReleaseDTO(2l, "Second artist DTO"));
		firstDTO.setLabel(labelInReleaseDTO);

		releaseInItemDTOS.add(firstDTO);
		releaseInItemDTOS.add(secondDTO);

		when(releaseRepository.findByLabelId(labelId)).thenReturn(releasesToReturn);
		when(releaseMapperFactory.resolveEntityMapper("InItem")).thenReturn(releaseInItemDTOMapper);
		when(releaseInItemDTOMapper.toDto(releasesToReturn)).thenReturn(releaseInItemDTOS);

		List<ReleaseInItemDTO> releasesFinal = itemService.getReleasesByLabelId(labelId);

		assertThat(releasesFinal).isNotNull();
		assertThat(releasesFinal.size()).isGreaterThan(0);
	}
}
