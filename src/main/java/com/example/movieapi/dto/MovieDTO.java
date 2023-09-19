package com.example.movieapi.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MovieDTO {
    private Long idMovie;
    private Integer year;
    private String title;
    private String studios;
    private Set<ProducerDTO> producers;
    private Boolean winner;
}
