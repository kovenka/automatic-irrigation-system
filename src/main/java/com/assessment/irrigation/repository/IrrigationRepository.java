package com.assessment.irrigation.repository;

import com.assessment.irrigation.entity.Irrigation;
import com.assessment.irrigation.enums.IrrigationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface IrrigationRepository extends JpaRepository<Irrigation, Long> {

    Collection<Irrigation> findAllByIrrigationStatusIn(Collection<IrrigationStatus> irrigationStatus);
}
