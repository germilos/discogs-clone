package com.milos.releasemicroservice.repo.criteria.specification;

import com.milos.releasemicroservice.domain.Item;
import com.milos.releasemicroservice.domain.Release;
import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class SearchSpecification implements Specification<Item>
{
	private SearchCriteria searchCriteria;

	public SearchSpecification(SearchCriteria searchCriteria)
	{
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
	{
		System.out.println("Root model: " + root.getModel().getName());
		final List<Predicate> predicates = new ArrayList<>();

		if (searchCriteria.getItemType() != null)
		{
			predicates.add(criteriaBuilder.equal(root.get("itemType"), searchCriteria.getItemType()));
		}
		if (searchCriteria.getFormat() != null)
		{
			Root<Release> rootRelease = criteriaBuilder.treat(root, Release.class);
			predicates.add(criteriaBuilder.equal(rootRelease.get("format"), searchCriteria.getFormat()));
		}
		if (searchCriteria.getCountry() != null)
		{
			Root<Release> rootRelease = criteriaBuilder.treat(root, Release.class);
			predicates.add(criteriaBuilder.equal(rootRelease.get("country"), searchCriteria.getCountry()));
		}
		if (searchCriteria.getReleased() != null)
		{
			Root<Release> rootRelease = criteriaBuilder.treat(root, Release.class);
			predicates.add(criteriaBuilder.equal(rootRelease.get("released"), searchCriteria.getReleased()));
		}
		if (searchCriteria.getGenreNames() != null)
		{
			Root<Release> rootRelease = criteriaBuilder.treat(root, Release.class);
			CriteriaBuilder.In<String> inClause = criteriaBuilder.in(rootRelease.get("genreNames"));
			for (String genreName : searchCriteria.getGenreNames())
			{
				predicates.add(inClause.value(genreName));
			}
		}
		if (searchCriteria.getStyleNames() != null)
		{
			Root<Release> rootRelease = criteriaBuilder.treat(root, Release.class);
			CriteriaBuilder.In<String> inClause = criteriaBuilder.in(rootRelease.get("styleNames"));
			for (String styleName : searchCriteria.getStyleNames())
			{
				predicates.add(inClause.value(styleName));
			}
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
