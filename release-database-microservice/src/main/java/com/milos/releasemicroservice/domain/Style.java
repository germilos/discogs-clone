package com.milos.releasemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "style")
@NoArgsConstructor
@Getter
@Setter
public class Style implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String description;

	@JoinColumn(name = "genere_id")
	@ManyToOne
	@JsonBackReference("genre_style")
	private Genre genre;

	@ManyToMany
	@JoinTable(name = "release_style", joinColumns = @JoinColumn(name = "style_id"), inverseJoinColumns = @JoinColumn(name = "release_id"))
	List<Release> releases;

	public Style(Long id, String name, Genre genre, List<Release> releases)
	{
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.releases = releases;
	}

	public Style(String name)
	{
		this.name = name;
	}

	@Override
	public int hashCode()
	{
		return Long.hashCode(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Style other = (Style) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
