package com.milos.releasemicroservice.service.integration;

import com.milos.releasemicroservice.domain.*;
import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import com.milos.releasemicroservice.repo.criteria.specification.SearchSpecification;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.milos.releasemicroservice.service.ItemService;
import com.milos.releasemicroservice.service.dto.*;
import com.milos.releasemicroservice.service.mapper.factory.ArtistMapperFactory;
import com.milos.releasemicroservice.service.mapper.factory.LabelMapperFactory;
import com.milos.releasemicroservice.service.mapper.factory.ReleaseMapperFactory;
import feign.Feign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceIT
{

	@Autowired
	private ItemService itemService;

	@Autowired
	private ArtistMapperFactory artistMapperFactory;

	@Autowired
	private LabelMapperFactory labelMapperFactory;

	@Autowired
	private ReleaseMapperFactory releaseMapperFactory;

	private SearchCriteria searchCriteria;
	private SearchSpecification searchSpecification;

	@BeforeEach
	public void setUp() throws Exception
	{
		this.searchCriteria = new SearchCriteria();
	}

	@Test
	public void givenNewRelease_whenSavingToDB_thenCorrect() throws IOException
	{
		ReleaseSaveDTO releaseSaveDTO = new ReleaseSaveDTO();
		releaseSaveDTO.setName("Some release dto");
		releaseSaveDTO.setCountry("Some country");
		releaseSaveDTO.setFormat("Some format");
		releaseSaveDTO.setItemType("Release");
		releaseSaveDTO.setReleased("2001");
		releaseSaveDTO.setArtist(new ArtistInReleaseDTO(null, "Some artist name"));
		releaseSaveDTO.setLabel(new LabelInReleaseDTO(null, "Some label name"));
		releaseSaveDTO.getStyles().add(new StyleSaveItemDTO(1l, "West Side"));
		releaseSaveDTO.getGenres().add(new GenreSaveItemDTO(1l, "Hip Hop"));
		User contributor = new User(1l, "Milos");
		releaseSaveDTO.setContributor(contributor);

		Path imageOne = Paths.get("static\\images\\Capture.PNG");
		Path imageOneAbsolute = Paths.get(
				"C:\\java\\moje\\projekti\\discogs\\discogs-clone\\src\\main\\resources\\static\\images\\Capture.PNG");

		File imageOneAbsoluteFile = imageOneAbsolute.toFile();

		Path imageTwo = Paths.get("static\\images\\Capture2.PNG");

		//		List<MultipartFile> multipartFiles = new ArrayList<>();

		MultipartFile mockMultipartFileOne = new MockMultipartFile("firstImage", "Capture.PNG", null,
				Files.readAllBytes(imageOneAbsolute));

		MultipartFile mockMultipartFileOneWithInputStream = new MockMultipartFile("firstImage",
				new BufferedInputStream(new FileInputStream(imageOneAbsoluteFile)));
		//		MultipartFile mockMultipartFileTwo = new MockMultipartFile("secondImage", "Capture2.PNG", null,
		//				Files.readAllBytes(imageTwo));
		//		multipartFiles.add(mockMultipartFileOne);
		//		multipartFiles.add(mockMultipartFileTwo);

		releaseSaveDTO.setImageFiles(new MultipartFile[] { mockMultipartFileOne });
		//		Set<Image> images = new HashSet<>();
		//		Image someImage = new Image(600, 600, "primary", "Primaryimageuri", contributor);
		//		Image someOtherImage = new Image(600, 600, "secondary", "Secondaryimageuri", contributor);
		//		images.add(someImage);
		//		images.add(someOtherImage);
		//		releaseSaveDTO.setImages(images);

		ItemDetailDTO savedRelease = itemService.save(releaseSaveDTO);

		assertThat(savedRelease).isNotNull();
		assertThat(savedRelease.getName()).isNotNull();
		assertThat(savedRelease.getName()).isEqualTo("Some release dto");
	}

	@Test
	public void givenNewReleaseWithExistingArtistAndLabel_whenSavingToDB_thenCorrect()
	{
		ReleaseSaveDTO releaseSaveDTO = new ReleaseSaveDTO();
		releaseSaveDTO.setName("Some other release dto");
		releaseSaveDTO.setCountry("Some country");
		releaseSaveDTO.setFormat("Some format");
		releaseSaveDTO.setItemType("Release");
		releaseSaveDTO.setReleased("2001");
		releaseSaveDTO.setArtist(new ArtistInReleaseDTO(6l, "Metallica"));
		releaseSaveDTO.setLabel(new LabelInReleaseDTO(1l, "Atlanta Records"));
		releaseSaveDTO.getStyles().add(new StyleSaveItemDTO(1l, "West Side"));
		releaseSaveDTO.getGenres().add(new GenreSaveItemDTO(1l, "Hip Hop"));
		User contributor = new User(1l, "Milos");
		releaseSaveDTO.setContributor(contributor);
		Set<Image> images = new HashSet<>();
		Image someImage = new Image(600, 600, "primary", "Primaryimageuri", contributor);
		Image someOtherImage = new Image(600, 600, "secondary", "Secondaryimageuri", contributor);
		images.add(someImage);
		images.add(someOtherImage);
		//		releaseSaveDTO.setImages(images);

		ItemDetailDTO savedRelease = itemService.save(releaseSaveDTO);

		assertThat(savedRelease).isNotNull();
		assertThat(savedRelease.getName()).isNotNull();
		assertThat(savedRelease.getName()).isEqualTo("Some other release dto");
	}

	@Test
	public void givenReleaseId_whenRetrievingFromDB_thenReturnReleaseDetailDTO()
	{
		Long id = 17l;
		ItemDetailDTO itemFromDB = null;
		try
		{
			itemFromDB = itemService.getById(id);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		assertThat(itemFromDB).isNotNull();
		assertThat(itemFromDB).isInstanceOf(ReleaseDetailDTO.class);
	}

	@Test
	public void givenArtistId_whenRetrievingFromDB_thenReturnArtistDetailDTO()
	{
		Long id = 6l;
		ItemDetailDTO itemFromDB = null;
		try
		{
			itemFromDB = itemService.getById(id);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		assertThat(itemFromDB).isNotNull();
		assertThat(itemFromDB).isInstanceOf(ArtistDetailDTO.class);
	}

	@Test
	public void givenLabelId_whenRetrievingFromDB_thenReturnLabelDetailDTO()
	{
		Long id = 1l;
		ItemDetailDTO itemFromDB = null;
		try
		{
			itemFromDB = itemService.getById(id);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		assertThat(itemFromDB).isNotNull();
		assertThat(itemFromDB).isInstanceOf(LabelDetailDTO.class);
	}

	@Test
	public void givenNonExistentId_whenRetrievingItem_thenThrowRuntimeException()
	{
		Long id = 0l;

		assertThrows(RuntimeException.class, () -> {
			itemService.getById(id);
		});
	}

	@Test
	public void givenReleaseItemType_whenRetrievingItemByCriteria_thenReturnReleases()
	{
		searchCriteria.setItemType("Release");
		List<ItemInExploreDTO> foundItems = itemService.getByCriteria(searchCriteria);

		assertThat(foundItems).isNotNull();
		assertThat(foundItems.size()).isGreaterThan(0);
	}

	@Test
	public void givenArtistItemType_whenRetrievingItemByCriteria_thenReturnArtists()
	{
		searchCriteria.setItemType("Artist");
		List<ItemInExploreDTO> foundItems = itemService.getByCriteria(searchCriteria);

		assertThat(foundItems).isNotNull();
		assertThat(foundItems.size()).isGreaterThan(0);
	}

	@Test
	public void givenOnlyReleaseSearchCriteria_whenRetrievingItemsByCriteria_thenReturnOnlyReleases()
	{
		searchCriteria.setFormat("CD");
		searchCriteria.setCountry("America");

		List<ItemInExploreDTO> foundItems = itemService.getByCriteria(searchCriteria);
		assertThat(foundItems).isNotNull();
		assertThat(foundItems.size()).isGreaterThan(0);
	}

	@Test
	public void givenAscendingDateAddedSortCondition_whenRetrievingItems_thenReturnItemsSortedByDateAddedAsc()
	{
		Sort ascendingSort = Sort.by(Sort.Direction.ASC, "dateAdded");
		List<ItemInExploreDTO> sortedItems = itemService.getAll(ascendingSort);

		assertThat(sortedItems.size()).isGreaterThan(0);
	}

	@Test
	public void givenAscendingSortAndItemCriteria_whenRetrievingItems_thenReturnSortedItemsByCriteria()
	{
		Sort ascendingSort = Sort.by(Sort.Direction.ASC, "dateAdded");
		Pageable pageable = PageRequest.of(0, 10, ascendingSort);
		searchCriteria.setFormat("CD");
		searchCriteria.setCountry("America");

		Page<ItemInExploreDTO> items = itemService.getAll(searchCriteria, pageable);
		assertThat(items.getNumberOfElements()).isGreaterThan(0);
	}
}
