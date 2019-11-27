package com.milos.releasemicroservice.repo;

import com.milos.releasemicroservice.domain.Release;

import java.util.List;

public interface ReleaseRepository extends ItemRepository<Release>
{
	List<Release> findByArtistId(Long artistId);

	List<Release> findByLabelId(Long labelId);
}
