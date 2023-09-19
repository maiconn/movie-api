package com.example.movieapi.repository;


import com.example.movieapi.dto.ProducerWinnerDTO;
import com.example.movieapi.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends JpaRepository<ProducerEntity, Long> {
    Optional<ProducerEntity> findByName(String name);

    @Query("      select p.idProducer" +
            "       from Producer p " +
            "       join p.movies m" +
            "      where m.winner = true" +
            "   group by p.idProducer" +
            "     having count (m.year) > 1")
    List<Long> findAllAtLeast2Wins();

    @Query("      select new com.example.movieapi.dto.ProducerWinnerDTO(p.idProducer," +
            "            p.name," +
            "            m.year)" +
            "       from Producer p " +
            "       join p.movies m" +
            "      where m.winner = true" +
            "        and p.idProducer in (?1)" +
            "   order by p.name, m.year")
    List<ProducerWinnerDTO> findProducersWinners(List<Long> ids);
}
