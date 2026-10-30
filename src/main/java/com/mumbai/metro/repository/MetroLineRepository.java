package com.mumbai.metro.repository;

import com.mumbai.metro.entity.MetroLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetroLineRepository extends JpaRepository<MetroLine, Long> {
}