package com.example.movieapi.service;

import com.example.movieapi.exceptions.BusinessException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class InitService {

    private final MovieService movieService;

    @PostConstruct
    public void init() throws URISyntaxException, BusinessException {
        URL resource = getClass().getClassLoader().getResource("movielist.csv");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            File file = new File(resource.toURI());
            movieService.processFile(file);
        }

    }
}
