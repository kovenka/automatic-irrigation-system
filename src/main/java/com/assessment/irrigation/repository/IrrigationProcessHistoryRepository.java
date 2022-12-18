package com.assessment.irrigation.repository;

import com.assessment.irrigation.entity.IrrigationProcessHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationProcessHistoryRepository extends JpaRepository<IrrigationProcessHistory, Long> {
}
