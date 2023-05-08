package com.coursework.petition.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "petition_votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petition_id", nullable = false)
    private Petition petition;

    private boolean isUpvote;

    public void setIsUpvote(boolean isUpvote) {
    }
}
