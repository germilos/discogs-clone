package com.milos.releasemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "genre")
@NoArgsConstructor
@Getter
@Setter
public class Genre implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String description;

	@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	@JsonManagedReference("genre_style")
	List<Style> styles;

	@ManyToMany
	@JoinTable(name = "release_genre", joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn(name = "release_id"))
	@JsonBackReference
	List<Release> releases;

	public Genre(Long id, String name, List<Style> styles, List<Release> releases)
	{
		this.id = id;
		this.name = name;
		this.styles = styles;
		this.releases = releases;
	}

	public Genre(String name)
	{
		this.name = name;
	}
}
