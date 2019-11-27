package com.milos.releasemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Release")
@Getter
@Setter
public class Release extends Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(length = 200)
    private String format;

    @Column(length = 56)
    private String country;

    @Column(length = 200)
    private String notes;

    @Column(length = 20)
    private String released;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "artist_id")
    @JsonBackReference("artist_releases")
    private Artist artist;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "label_id")
    @JsonBackReference("label_releases")
    private Label label;

    @ManyToMany
    @JoinTable(name = "release_genre", joinColumns = @JoinColumn(name = "release_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "release_style", joinColumns = @JoinColumn(name = "release_id"), inverseJoinColumns = @JoinColumn(name = "style_id"))
    List<Style> styles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "release_id")
    List<Track> tracks;

    public Release() {
        super();
        this.tracks = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.styles = new ArrayList<>();
    }

    public Release(String name, LocalDate dateAdded, LocalDate dateLastChanged, String uri, List<Contribution> contributions,
                   Set<Image> images, String format, String country, String notes, String released, Artist artist, Label label,
                   List<Genre> genres, List<Style> styles, List<Track> tracks) {
        super(name, dateAdded, dateLastChanged, uri, contributions, images);
        this.format = format;
        this.country = country;
        this.notes = notes;
        this.released = released;
        this.artist = artist;
        this.label = label;
        this.genres = genres;
        this.styles = styles;
        this.tracks = tracks;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Release other = (Release) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
        artist.getReleases().add(this);
    }
}
