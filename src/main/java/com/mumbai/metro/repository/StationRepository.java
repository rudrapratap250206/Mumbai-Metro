package com.mumbai.metro.repository;

import com.mumbai.metro.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

}