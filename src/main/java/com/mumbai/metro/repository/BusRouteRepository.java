package com.mumbai.metro.repository;

import com.mumbai.metro.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
}