package com.milos.releasemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "itemType")
@JsonSubTypes({@JsonSubTypes.Type(value = Artist.class, name = "Artist"),
        @JsonSubTypes.Type(value = Label.class, name = "Label"),
        @JsonSubTypes.Type(value = Release.class, name = "Release")})
@Getter
@Setter
public abstract class Item {
    @Id
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_generator")
    //    @SequenceGenerator(name = "items_generator", sequenceName = "item_id_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(length = 150, nullable = false)
    protected String name;

    @Column(name = "date_added", nullable = false)
    protected LocalDate dateAdded;

    @Column(name = "date_last_changed", nullable = false)
    protected LocalDate dateLastChanged;

    @Column
    protected String uri;

    @Column(name = "item_type", length = 10, insertable = false, updatable = false)
    private String itemType;

    //	@ManyToMany
//	@JoinTable(name = "item_user", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	protected List<User> contributors;
    @OneToMany(
            mappedBy = "item",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference("contributions_item_mr")
    protected List<Contribution> contributions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_id")
    protected Set<Image> images;

    protected Item() {
        contributions = new ArrayList<>();
        images = new HashSet<>();
    }

    protected Item(String name, LocalDate dateAdded, LocalDate dateLastChanged, String uri, List<Contribution> contributions,
                   Set<Image> images) {
        this.name = name;
        this.dateAdded = dateAdded;
        this.dateLastChanged = dateLastChanged;
        this.uri = uri;
        this.contributions = contributions;
        this.images = images;
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
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[id = " + this.id + ", name = " + this.name + "]";
    }

    public void addContributor(User contributor) {
        Contribution contribution = new Contribution(this, contributor);
        contributions.add(contribution);
    }

    public void removeContributor(User contributor) {
        for (Iterator<Contribution> iterator = contributions.iterator(); iterator.hasNext(); ) {
            Contribution contribution = iterator.next();
            if (contribution.getItem().equals(this) && contribution.getUser().equals(contributor)) {
                iterator.remove();
                contribution.getUser().getContributions().remove(contribution);
                contribution.setItem(null);
                contribution.setUser(null);
            }
        }
    }
}
