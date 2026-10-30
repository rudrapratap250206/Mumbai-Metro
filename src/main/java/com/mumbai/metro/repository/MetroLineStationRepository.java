package com.mumbai.metro.repository;

import com.mumbai.metro.entity.MetroLineStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetroLineStationRepository
        extends JpaRepository<MetroLineStation, Long> {

    List<MetroLineStation> findByMetroLineLineIdOrderByStationSequence(
            Long lineId);

    List<MetroLineStation> findByStationStationId(Long stationId);

    List<MetroLineStation> findByStationStationNameIgnoreCase(
            String stationName);
}