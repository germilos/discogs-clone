package com.milos.releasemicroservice.repo.criteria.specification;

import com.milos.releasemicroservice.domain.Artist;
import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ArtistSpecification implements Specification<Artist>
{
	private SearchCriteria searchCriteria;

	public ArtistSpecification(SearchCriteria searchCriteria)
	{
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Artist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
	{
		final List<Predicate> predicates = new ArrayList<>();

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
