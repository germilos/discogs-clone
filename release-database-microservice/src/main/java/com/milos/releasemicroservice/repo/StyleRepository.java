package com.milos.releasemicroservice.repo;

import com.milos.releasemicroservice.domain.Genre;
import com.milos.releasemicroservice.domain.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long>
{
	List<Style> findAll();

	List<Style> findByGenreId(Genre genre);

	List<Style> findByGenreName(Genre genre);

	Optional<Style> findById(Long id);
}
