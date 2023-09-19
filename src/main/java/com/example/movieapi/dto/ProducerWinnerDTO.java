package com.example.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerWinnerDTO {
    private Long idProducer;
    private String nameProducer;
    private Integer year;
}
