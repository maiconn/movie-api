package com.example.movieapi.controller;

import com.example.movieapi.dto.MovieDTO;
import com.example.movieapi.exceptions.BusinessException;
import com.example.movieapi.service.MovieService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@Validated
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> listAll(){
        return movieService.listAll();
    }

    @PostMapping(value = "/upload-data", consumes = {"multipart/form-data"})
    public List<MovieDTO> uploadData(@ModelAttribute @NotNull MultipartFile file) throws BusinessException {
        return movieService.processFile(file);
    }

    @DeleteMapping("/{idMovie}")
    public void remove(@PathVariable Long idMovie) {
        movieService.remove(idMovie);
    }

    @DeleteMapping("/delete-all")
    public void removeAll() {
        movieService.removeAll();
    }
}
