package com.milos.releasemicroservice.repo;

import com.milos.releasemicroservice.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository<T extends Item> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>
{
	List<T> findAll();

	List<T> findAll(Sort sort);

	List<T> findAll(Specification<T> specification);

	List<T> findAll(Specification<T> specification, Sort sort);

	Page<T> findAll(Pageable pageable);

	Page<T> findAll(Specification<T> specification, Pageable pageable);

	Optional<T> findById(Long id);

	T save(T item);
}
