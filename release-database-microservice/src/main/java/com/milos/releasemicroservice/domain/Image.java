package com.milos.releasemicroservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "image")
@NoArgsConstructor
@Getter
@Setter
public class Image implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "upload_date")
	private LocalDateTime uploadDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User uploader;

	//	@Column(nullable = false)
	//	private Integer width;
	//
	//	@Column(nullable = false)
	//	private Integer height;
	//
	//	@Column(nullable = false, length = 50)
	//	private String type;

	//	@Column
	//	private String uri;
	//
	//	@ManyToOne
	//	@JoinColumn(name = "user_id")
	//	private User uploader;

	public Image(Long id, LocalDateTime uploadDate, User uploader)
	{
		this.id = id;
		this.uploadDate = uploadDate;
		this.uploader = uploader;
	}

	//	public Image(Integer width, Integer height, String type, String uri, User uploader)
	//	{
	//
	//		this.uploader = uploader;
	//	}

	@Override
	public int hashCode()
	{
		return this.id.hashCode();
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
		Image other = (Image) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
