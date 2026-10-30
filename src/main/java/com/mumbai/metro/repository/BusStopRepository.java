package com.mumbai.metro.repository;

import com.mumbai.metro.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {
}