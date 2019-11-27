package com.milos.releasemicroservice.service.integration;

import com.milos.releasemicroservice.domain.*;
import com.milos.releasemicroservice.service.dto.*;

import static org.assertj.core.api.Assertions.*;

import com.milos.releasemicroservice.service.mapper.*;
import com.milos.releasemicroservice.service.mapper.factory.ReleaseMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemDTOTest
{
	@Autowired
	private ReleaseDetailDTOMapper releaseDetailDTOMapper;
	@Autowired
	private ReleaseMapperFactory releaseMapperFactory;
	@Autowired
	private ArtistDetailDTOMapper artistDetailDTOMapper;
	@Autowired
	private LabelDetailDTOMapper labelDetailDTOMapper;
	@Autowired
	private ItemInExploreDTOMapper itemInExploreDTOMapper;

	@BeforeEach
	public void setUp() throws Exception
	{
	}

	@Test
	public void givenReleaseDTOMapper_whenMappingReleaseToReleaseDetailDTO_thenReturnReleaseDetailDTO()
	{
		Release release = constructRelease();
		ReleaseDetailDTO releaseDetailDTO = releaseDetailDTOMapper.toDto(release);

		assertThat(releaseDetailDTO).isNotNull();
		assertThat(releaseDetailDTO.getName()).isEqualTo(release.getName());
		assertThat(releaseDetailDTO.getTracks().size()).isGreaterThan(0);
		assertThat(releaseDetailDTO.getStyles().size()).isGreaterThan(0);
		assertThat(releaseDetailDTO.getTracks().size()).isGreaterThan(0);
	}

	@Test
	public void givenLabelDTOMapper_whenMappingLabelToLabelDetailDTO_thenReturnLabelDetailDTO()
	{
		Label label = constructLabel();
		LabelDetailDTO labelDetailDTO = labelDetailDTOMapper.toDto(label);

		assertThat(labelDetailDTO).isNotNull();
		assertThat(labelDetailDTO.getName()).isEqualTo(label.getName());
		assertThat(labelDetailDTO.getReleases().size()).isGreaterThan(0);
		assertThat(labelDetailDTO.getProfile()).isEqualTo("Some profile");
		assertThat(labelDetailDTO.getReleases().size()).isGreaterThan(0);
		labelDetailDTO.getReleases().forEach(releaseDTO -> {
			assertThat(releaseDTO).isInstanceOf(ReleaseInItemDTO.class);
		});
	}

	@Test
	public void givenArtistDetailDTOMapper_whenMappingArtistToArtistDTO_thenReturnArtistDetailDTO()
	{
		Artist artist = constructArtist();
		ArtistDetailDTO artistDetailDTO = artistDetailDTOMapper.toDto(artist);

		assertThat(artistDetailDTO).isNotNull();
		assertThat(artistDetailDTO.getName()).isEqualTo(artist.getName());
		assertThat(artistDetailDTO.getReleases().size()).isGreaterThan(0);
		assertThat(artistDetailDTO.getProfile()).isEqualTo("Some profile");
		assertThat(artistDetailDTO.getReleases().size()).isGreaterThan(0);
		artistDetailDTO.getReleases().forEach(releaseDTO -> {
			assertThat(releaseDTO).isInstanceOf(ReleaseInItemDTO.class);
		});
	}

	@Test
	public void givenItemInExploreDTOMapper_whenMappingLabelToDTO_thenReturnItemInExploreDTO()
	{
		Label label = constructLabel();
		ItemInExploreDTO itemInExploreDTO = itemInExploreDTOMapper.toDto(label);

		assertThat(itemInExploreDTO).isNotNull();
		assertThat(itemInExploreDTO.getName()).isEqualTo(label.getName());
		assertThat(itemInExploreDTO.getThumbnail()).isNotNull();
		assertThat(itemInExploreDTO.getThumbnail().getType()).isEqualTo("primary");
	}

	@Test
	public void givenItemInExploreDTOMapper_whenMappingReleaseToDTO_thenReturnItemInExploreDTOWithArtist()
	{
		Release release = constructRelease();
		ItemInExploreDTO itemInExploreDTO = itemInExploreDTOMapper.toDto(release);

		assertThat(itemInExploreDTO).isNotNull();
		assertThat(itemInExploreDTO.getName()).isEqualTo(release.getName());
		assertThat(itemInExploreDTO.getThumbnail()).isNotNull();
		assertThat(itemInExploreDTO.getThumbnail().getType()).isEqualTo("primary");
		assertThat(itemInExploreDTO.getArtist().getName()).isEqualTo(release.getArtist().getName());
	}

	@Test
	public void givenReleaseSaveDTOMapper_whenMappingReleaseToDTO_thenReturnReleaseEntity()
	{
		ReleaseSaveDTO releaseSaveDTO = new ReleaseSaveDTO();
		releaseSaveDTO.setName("Some release dto");
		releaseSaveDTO.setCountry("Some country");
		releaseSaveDTO.setFormat("Some format");
		releaseSaveDTO.setItemType("Release");
		releaseSaveDTO.setReleased("2001");
		releaseSaveDTO.getStyles().add(new StyleSaveItemDTO(1l, "West Side"));
		releaseSaveDTO.getGenres().add(new GenreSaveItemDTO(1l, "Hip Hop"));

		User contributor = new User(1l, "Milos");

		Set<Image> images = new HashSet<>();
		Image someImage = new Image(600, 600, "primary", "Primaryimageuri", contributor);
		Image someOtherImage = new Image(600, 600, "secondary", "Secondaryimageuri", contributor);
		images.add(someImage);
		images.add(someOtherImage);
//		releaseSaveDTO.setImages(images);

		ArtistInReleaseDTO artistInReleaseDTO = new ArtistInReleaseDTO(1l, "Some artist name");
		releaseSaveDTO.setArtist(artistInReleaseDTO);

		LabelInReleaseDTO labelInReleaseDTO = new LabelInReleaseDTO(2l, "Some label name");
		releaseSaveDTO.setLabel(labelInReleaseDTO);

		Release release = (Release) releaseMapperFactory.resolveEntityMapper("Save").toEntity(releaseSaveDTO);

		assertThat(release).isNotNull();
		assertThat(release.getName()).isEqualTo(releaseSaveDTO.getName());
		assertThat(release.getImages().size()).isGreaterThan(0);
	}

	private Release constructRelease()
	{
		Release firstRelease = new Release();
		firstRelease.setName("Test with tracks");
		firstRelease.setItemType("Release");
		firstRelease.setDateAdded(LocalDate.of(2018, 2, 24));
		firstRelease.setDateLastChanged(LocalDate.of(2018, 2, 24));
		firstRelease.setFormat("CD");
		firstRelease.setCountry("Lebanon");
		firstRelease.setReleased("2018");

		Artist artist = new Artist();
		artist.setName("Aerosmith");
		artist.setDateAdded(LocalDate.of(2019, 5, 25));
		artist.setDateLastChanged(LocalDate.of(2019, 5, 25));
		artist.setItemType("Artist");

		Label label = new Label();
		label.setName("RunGee");
		label.setDateAdded(LocalDate.of(2019, 5, 25));
		label.setDateLastChanged(LocalDate.of(2019, 5, 25));
		label.setItemType("Label");

		Genre genre = new Genre();
		genre.setId(1l);
		genre.setDescription("Some desc");
		genre.setName("Some name");

		List<Style> styles = new ArrayList<>();
		Style firstStyle = new Style();
		firstStyle.setId(1l);
		firstStyle.setName("First style");
		firstStyle.setDescription("Some desc");
		Style secondStyle = new Style();
		secondStyle.setId(2l);
		secondStyle.setName("Second style");
		secondStyle.setDescription("Some desc");
		styles.add(firstStyle);
		styles.add(secondStyle);

		firstRelease.setGenres(Arrays.asList(genre));
		firstRelease.setStyles(styles);

		Track firstTrack = new Track();
		firstTrack.setDuration("5:02");
		firstTrack.setPosition("1");
		firstTrack.setTitle("First track");

		Track secondTrack = new Track();
		secondTrack.setDuration("2:49");
		secondTrack.setPosition("2");
		secondTrack.setTitle("Second track");

		firstRelease.getTracks().add(firstTrack);
		firstRelease.getTracks().add(secondTrack);

		firstRelease.setArtist(artist);
		firstRelease.setLabel(label);

		Image thumbnail = new Image();
		thumbnail.setId(1l);
		thumbnail.setType("primary");
		thumbnail.setHeight(500);
		thumbnail.setWidth(600);
		thumbnail.setUri("Someuri");

		firstRelease.getImages().add(thumbnail);
		return firstRelease;
	}

	private Artist constructArtist()
	{
		Artist artist = new Artist();
		artist.setName("Some artist");
		artist.setProfile("Some profile");
		artist.setContactInfo("Some contact info");
		artist.setDateAdded(LocalDate.of(2019, 2, 2));
		artist.setDateLastChanged(LocalDate.of(2019, 2, 2));

		List<Release> releases = new ArrayList<>();
		Release firstRelease = new Release();
		firstRelease.setName("First release name");
		firstRelease.setItemType("Release");
		firstRelease.setDateAdded(LocalDate.of(2019, 2, 2));
		firstRelease.setDateLastChanged(LocalDate.of(2019, 2, 2));
		firstRelease.setReleased("2018");
		firstRelease.setFormat("Vinyl");
		Release secondRelease = new Release();
		secondRelease.setName("First release name");
		secondRelease.setItemType("Release");
		secondRelease.setDateAdded(LocalDate.of(2019, 2, 2));
		secondRelease.setDateLastChanged(LocalDate.of(2019, 2, 2));
		secondRelease.setReleased("2018");
		secondRelease.setFormat("Vinyl");

		releases.add(firstRelease);
		releases.add(secondRelease);

		artist.setReleases(releases);

		return artist;
	}

	private Label constructLabel()
	{
		Label label = new Label();
		label.setItemType("Label");
		label.setName("Some label");
		label.setProfile("Some profile");
		label.setContactInfo("Some contact info");
		label.setDateAdded(LocalDate.of(2019, 2, 2));
		label.setDateLastChanged(LocalDate.of(2019, 2, 2));

		List<Release> releases = new ArrayList<>();
		Release firstRelease = new Release();
		firstRelease.setName("First release name");
		firstRelease.setItemType("Release");
		firstRelease.setDateAdded(LocalDate.of(2019, 2, 2));
		firstRelease.setDateLastChanged(LocalDate.of(2019, 2, 2));
		firstRelease.setReleased("2018");
		firstRelease.setFormat("Vinyl");
		Release secondRelease = new Release();
		secondRelease.setName("First release name");
		secondRelease.setItemType("Release");
		secondRelease.setDateAdded(LocalDate.of(2019, 2, 2));
		secondRelease.setDateLastChanged(LocalDate.of(2019, 2, 2));
		secondRelease.setReleased("2018");
		secondRelease.setFormat("Vinyl");

		releases.add(firstRelease);
		releases.add(secondRelease);

		label.setReleases(releases);

		Image thumbnail = new Image();
		thumbnail.setId(1l);
		thumbnail.setType("primary");
		thumbnail.setHeight(500);
		thumbnail.setWidth(600);
		thumbnail.setUri("Someuri");

		label.getImages().add(thumbnail);

		return label;
	}
}
