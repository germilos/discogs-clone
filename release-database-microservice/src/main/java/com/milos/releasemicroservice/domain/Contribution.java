package com.milos.releasemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "item_user")
@Getter
@Setter
@NoArgsConstructor
public class Contribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonBackReference("item_contribution_br")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "contribution_date")
    private LocalDateTime contributionDate;

    public Contribution(Item item, User user) {
        this.item = item;
        this.user = user;
        this.contributionDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contribution that = (Contribution) obj;
        return Objects.equals(item, that.item) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, user, contributionDate);
    }
}
