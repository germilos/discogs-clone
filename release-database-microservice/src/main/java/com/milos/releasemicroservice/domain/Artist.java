package com.milos.releasemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Artist")
@Getter
@Setter
public class Artist extends Item implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(length = 200)
	private String profile;

	@Column(name = "contact_info", length = 200)
	private String contactInfo;

	@OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
	@JsonManagedReference("artist_releases")
	private List<Release> releases;

	public Artist()
	{
		super();
		this.releases = new ArrayList<>();
	}

	public Artist(String name, LocalDate dateAdded, LocalDate dateLastChanged, String uri, List<Contribution> contributions,
			Set<Image> images, String profile, String contactInfo, List<Release> releases)
	{
		super(name, dateAdded, dateLastChanged, uri, contributions, images);
		this.profile = profile;
		this.contactInfo = contactInfo;
		this.releases = releases;
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
}
