package com.milos.releasemicroservice.service.mapper;

import com.milos.releasemicroservice.domain.Item;
import com.milos.releasemicroservice.domain.Release;
import com.milos.releasemicroservice.service.dto.ArtistInReleaseDTO;
import com.milos.releasemicroservice.service.dto.ItemInExploreDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class, componentModel = "spring")
public interface ItemInExploreDTOMapper extends EntityMapper<ItemInExploreDTO, Item>
{
	default Item toEntity(ItemInExploreDTO dto)
	{
		return null;
	}

	default ItemInExploreDTO toDto(Item entity)
	{
		if (entity == null)
		{
			return null;
		}

		ItemInExploreDTO itemInExploreDTO = new ItemInExploreDTO();
		itemInExploreDTO.setItemId(entity.getId());
		itemInExploreDTO.setName(entity.getName());

		if (entity.getImages() != null && entity.getImages().size() > 0)
		{

			itemInExploreDTO.setThumbnail(entity.getImages().stream().filter(el -> "primary".equals(el.getType()))
					.collect(Collectors.toList()).get(0));
		}

		if (entity.getItemType() != null && entity.getItemType().equals("Release"))
		{
			ArtistInReleaseDTO artistInReleaseDTO = new ArtistInReleaseDTO(((Release) entity).getArtist().getId(),
					((Release) entity).getArtist().getName());
			itemInExploreDTO.setArtist(artistInReleaseDTO);
		}
		return itemInExploreDTO;
	}

	List<ItemInExploreDTO> toDto(List<Item> entityList);
}
