package com.example.movieapi.service;

import com.example.movieapi.dto.ProducerAwardDTO;
import com.example.movieapi.dto.ProducerInvervalAwardDTO;
import com.example.movieapi.dto.ProducerWinnerDTO;
import com.example.movieapi.entity.ProducerEntity;
import com.example.movieapi.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;

    public Set<ProducerEntity> convertAndSaveProducers(String producer) {
        List<String> producers = new ArrayList<>(List.of(producer.split(",")));
        int last = producers.size() - 1;
        if (!producers.isEmpty() && producers.get(last).contains(" and ")) {
            String[] newProducers = producers.get(last).split(" and ");
            producers.remove(last);
            producers.addAll(List.of(newProducers));
        }

        return producers.stream()
                .filter(str -> !str.trim().isEmpty())
                .map(producerStr -> {
                    String producerTrim = producerStr.trim();
                    Optional<ProducerEntity> foundProducer = producerRepository.findByName(producerTrim);
                    return foundProducer.orElseGet(() -> producerRepository.save(new ProducerEntity(null, producerTrim, null)));
                })
                .collect(Collectors.toSet());
    }

    public ProducerAwardDTO listAwardInterval(){
        List<Long> ids = producerRepository.findAllAtLeast2Wins();
        List<ProducerWinnerDTO> winners = producerRepository.findProducersWinners(ids);
        if(winners.isEmpty()) {
            return null;
        }
        int previousYear = winners.get(0).getYear();
        String previousProducer = winners.get(0).getNameProducer();
        List<ProducerInvervalAwardDTO> producerReturnList = new ArrayList<>();
        int maxInterval = 0;
        int minInterval = Integer.MAX_VALUE;
        for(int i = 1 ; i < winners.size() ; i++){
            ProducerWinnerDTO producerWinnerDTO = winners.get(i);
            if(producerWinnerDTO.getNameProducer().equalsIgnoreCase(previousProducer)) {
                int interval = producerWinnerDTO.getYear() - previousYear;
                producerReturnList.add(new ProducerInvervalAwardDTO(producerWinnerDTO.getNameProducer(),
                        interval,
                        previousYear,
                        producerWinnerDTO.getYear()));
                if(maxInterval < interval) {
                    maxInterval = interval;
                }
                if(minInterval > interval) {
                    minInterval = interval;
                }
            }
            previousYear = producerWinnerDTO.getYear();
            previousProducer = producerWinnerDTO.getNameProducer();

        }
        return getProducerAwardDTO(producerReturnList, minInterval, maxInterval);
    }

    private ProducerAwardDTO getProducerAwardDTO(List<ProducerInvervalAwardDTO> producerReturnList, int minInterval, int maxInterval) {
        ProducerAwardDTO producerAwardDTO = new ProducerAwardDTO();

        Integer finalMaxInterval = maxInterval;
        producerAwardDTO.setMax(
                producerReturnList.stream()
                        .filter(p -> finalMaxInterval.equals(p.getInterval()))
                        .toList());
        Integer finalMinInterval = minInterval;
        producerAwardDTO.setMin(
                producerReturnList.stream()
                        .filter(p -> finalMinInterval.equals(p.getInterval()))
                        .toList());

        return producerAwardDTO;
    }

}
