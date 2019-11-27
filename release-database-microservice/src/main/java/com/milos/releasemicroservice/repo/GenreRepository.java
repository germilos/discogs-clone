package com.milos.releasemicroservice.repo;

import com.milos.releasemicroservice.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>
{
	List<Genre> findAll();

	Optional<Genre> findById(Long id);
}
