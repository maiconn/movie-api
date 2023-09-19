package com.example.movieapi.service;

import com.example.movieapi.dto.MovieDTO;
import com.example.movieapi.entity.MovieEntity;
import com.example.movieapi.entity.ProducerEntity;
import com.example.movieapi.exceptions.BusinessException;
import com.example.movieapi.helper.CSVHelper;
import com.example.movieapi.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ObjectMapper objectMapper;
    private final ProducerService producerService;

    public List<MovieDTO> listAll() {
        return movieRepository.findAll().stream()
                .map(entity -> objectMapper.convertValue(entity, MovieDTO.class))
                .toList();
    }

    public List<MovieDTO> processFile(MultipartFile file) throws BusinessException {
        if (!CSVHelper.hasCSVFormat(file)) {
            throw new BusinessException("The file is not a csv.");
        }
        List<MovieDTO> movies = new ArrayList<>();
        try (Scanner scan = new Scanner(file.getInputStream())) {
            processScanner(scan, movies);
        } catch (IOException e) {
            throw new BusinessException("Erro while read the .csv file: " + e.getMessage());
        }
        return movies;
    }


    public List<MovieDTO> processFile(File file) throws BusinessException {
        List<MovieDTO> movies = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileInputStream(file))) {
            processScanner(scan, movies);
        } catch (IOException e) {
            throw new BusinessException("Erro while read the .csv file: " + e.getMessage());
        }
        return movies;
    }

    private void processScanner(Scanner scan, List<MovieDTO> movies) {
        scan.nextLine(); // header
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] data = line.split(";");
            Set<ProducerEntity> producers = producerService.convertAndSaveProducers(data[3]);
            MovieEntity movieEntity = new MovieEntity(null, Integer.parseInt(data[0]), data[1], data[2], CSVHelper.retrieveBoolean(data, 4), producers);
            movieEntity = this.save(movieEntity);
            movies.add(objectMapper.convertValue(movieEntity, MovieDTO.class));
        }
    }

    public MovieEntity save(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    public void remove(Long idMovie) {
        movieRepository.deleteById(idMovie);
    }
}
