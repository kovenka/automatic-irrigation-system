package com.assessment.irrigation.repository;

import com.assessment.irrigation.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository<Plot, Long> {
}
