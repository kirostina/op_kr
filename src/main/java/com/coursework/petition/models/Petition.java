package com.coursework.petition.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Petition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Будь ласка, введіть назву петиції!")
    String title;

    @Column(length = 1000)
    @NotEmpty(message = "Будь ласка, введіть опис петиції!")
    String description;

    @Column(name = "voted")
    boolean voted;

}
