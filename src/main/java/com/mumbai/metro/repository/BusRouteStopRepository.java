package com.mumbai.metro.repository;

import com.mumbai.metro.entity.BusRouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRouteStopRepository extends JpaRepository<BusRouteStop, Long> {

    List<BusRouteStop> findByBusRouteRouteIdOrderByStopSequence(Long routeId);

    List<BusRouteStop> findByBusStopStopId(Long stopId);

    List<BusRouteStop> findByBusStopStopNameIgnoreCase(String stopName);
}