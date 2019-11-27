package com.milos.releasemicroservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "track")
@NoArgsConstructor
@Getter
@Setter
public class Track implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String duration;

	@Column(nullable = false)
	private String position;

	/* TODO: Consider if release needed - probably make it unidirectional
	@ManyToOne
	@JoinColumn(name = "release_id")
	private Release release;

	public Track(String title, String duration, String position, Release release) {
		this.title = title;
		this.duration = duration;
		this.position = position;
		this.release = release;
	}
	*/

	public Track(String title, String duration, String position) {
		this.title = title;
		this.duration = duration;
		this.position = position;
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
		Track other = (Track) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
