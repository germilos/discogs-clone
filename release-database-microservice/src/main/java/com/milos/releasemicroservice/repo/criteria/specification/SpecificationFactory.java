package com.milos.releasemicroservice.repo.criteria.specification;

import com.milos.releasemicroservice.repo.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationFactory
{
	public static Specification getSpecification(SearchCriteria searchCriteria)
	{
		switch (searchCriteria.getItemType())
		{
		case "Release":
			return new ReleaseSpecification(searchCriteria);
		case "Artist":
			return searchCriteria.areCriteriaEmpty() ? new ArtistSpecification(searchCriteria) : null;
		case "Label":
			return searchCriteria.areCriteriaEmpty() ? new LabelSpecification(searchCriteria) : null;
		default:
			return searchCriteria.areCriteriaEmpty() ? null : new ReleaseSpecification(searchCriteria);
		}
	}
}
