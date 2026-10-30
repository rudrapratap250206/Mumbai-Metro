package com.mumbai.metro.repository;

import com.mumbai.metro.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey, Long> {
}