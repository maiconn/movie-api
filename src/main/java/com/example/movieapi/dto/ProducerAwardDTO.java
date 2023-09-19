package com.example.movieapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProducerAwardDTO {
    private List<ProducerInvervalAwardDTO> min;
    private List<ProducerInvervalAwardDTO> max;
}
