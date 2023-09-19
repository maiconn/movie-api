package com.example.movieapi.controller;

import com.example.movieapi.dto.ProducerAwardDTO;
import com.example.movieapi.dto.ProducerInvervalAwardDTO;
import com.example.movieapi.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
@Validated
public class ProducerController {
    private final ProducerService producerService;

    @GetMapping("award-interval")
    public ProducerAwardDTO listAwardInterval(){
        return producerService.listAwardInterval();
    }
}
