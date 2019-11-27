package com.milos.releasemicroservice.repo.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchCriteria
{
	private String itemType;

	private String format;

	private String country;

	private String released;

	List<String> genreNames;

	List<String> styleNames;

	public boolean areCriteriaEmpty()
	{
		return (format == null && country == null && released == null && (genreNames == null || genreNames.isEmpty())
				&& (styleNames == null || styleNames.isEmpty()));
	}
}
