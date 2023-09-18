package com.example.movieapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Movie")
public class MovieEntity {
    @Id
    @Column(name = "id_movie")
    private Long idMovie;

    @Column(name = "year_movie")
    private Integer year;

    private String title;

    private String studios;

    private String producers;

    private Boolean winner;
}
