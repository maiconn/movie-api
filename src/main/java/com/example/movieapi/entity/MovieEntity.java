package com.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "Movie")
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_movie")
    private Long idMovie;

    @Column(name = "year_movie")
    private Integer year;

    private String title;

    private String studios;

    private Boolean winner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_producer",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_producer"))
    private Set<ProducerEntity> producers;

}
