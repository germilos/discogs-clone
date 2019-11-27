package com.milos.releasemicroservice.repo.criteria.specification;

import com.milos.releasemicroservice.domain.Release;
import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ReleaseSpecification implements Specification<Release>
{

	private SearchCriteria searchCriteria;

	public ReleaseSpecification(SearchCriteria searchCriteria)
	{
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Release> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
	{
		final List<Predicate> predicates = new ArrayList<>();
		if (root.get("format") != null)
		{
			predicates.add(criteriaBuilder.equal(root.get("format"), searchCriteria.getFormat()));
		}
		if (root.get("country") != null)
		{
			predicates.add(criteriaBuilder.equal(root.get("country"), searchCriteria.getCountry()));
		}
		if (root.get("released") != null)
		{
			predicates.add(criteriaBuilder.equal(root.get("released"), searchCriteria.getReleased()));
		}
		if (root.get("genreNames") != null)
		{
			CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("genreNames"));
			for (String genreName : searchCriteria.getGenreNames())
			{
				predicates.add(inClause.value(genreName));
			}
		}
		if (root.get("styleNames") != null)
		{
			CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("styleNames"));
			for (String styleName : searchCriteria.getStyleNames())
			{
				predicates.add(inClause.value(styleName));
			}
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
