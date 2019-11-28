package com.milos.imageprocessingmicroservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "image")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "original_name")
	private String originalName;

	@Column(name = "extension")
	private String extension;

	@Column(name = "width")
	private Integer width;

	@Column(name = "height")
	private Integer height;

	@Column(name = "uri")
	private String uri;

	@Column(name = "uploader_id")
	private Long uploaderId;

	@Column(name = "upload_date")
	private LocalDateTime dateAdded;

	@Column(name = "item_id")
	private Long itemId;

	public Image(String originalName, String extension, Integer width, Integer height, String uri, Long uploaderId,
			LocalDateTime dateAdded, Long itemId)
	{
		this.originalName = originalName;
		this.extension = extension;
		this.width = width;
		this.height = height;
		this.uri = uri;
		this.uploaderId = uploaderId;
		this.dateAdded = dateAdded;
		this.itemId = itemId;
	}
}
