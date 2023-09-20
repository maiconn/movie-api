package com.example.movieapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "Producer")
@AllArgsConstructor
@NoArgsConstructor
public class ProducerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_producer")
    private Long idProducer;

    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "producers", cascade = CascadeType.ALL)
    private Set<MovieEntity> movies;
}
