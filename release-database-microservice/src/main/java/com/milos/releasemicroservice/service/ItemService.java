package com.milos.releasemicroservice.service;

import com.milos.releasemicroservice.domain.Item;
import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import com.milos.releasemicroservice.service.dto.ItemDetailDTO;
import com.milos.releasemicroservice.service.dto.ItemInExploreDTO;
import com.milos.releasemicroservice.service.dto.ReleaseInItemDTO;
import com.milos.releasemicroservice.service.dto.ReleaseSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ItemService
{
	List<ItemInExploreDTO> getAll();

	List<ItemInExploreDTO> getAll(Sort sort);

	List<ItemInExploreDTO> getByCriteria(SearchCriteria criteria);

	Page<ItemInExploreDTO> getAll(Pageable pageable);

	Page<ItemInExploreDTO> getAll(SearchCriteria searchCriteria, Pageable pageable);

	ItemDetailDTO getById(Long id);

	List<ReleaseInItemDTO> getReleasesByArtistId(Long artistId);

	List<ReleaseInItemDTO> getReleasesByLabelId(Long labelId);

	ItemDetailDTO save(ReleaseSaveDTO releaseSaveDTO);

	Item update(Item item);
}
