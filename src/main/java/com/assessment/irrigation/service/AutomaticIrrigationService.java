package com.assessment.irrigation.service;

import com.assessment.irrigation.entity.Irrigation;
import com.assessment.irrigation.payload.dto.IrrigationDto;

import java.util.Collection;

public interface AutomaticIrrigationService {
    IrrigationDto getIrrigationById(Long id);

    IrrigationDto update(IrrigationDto irrigationDto);

    IrrigationDto createNewIrrigation(IrrigationDto irrigationDto, Long landId);

    Collection<Irrigation> getAllReadyIrrigationTriggersAndUpdateToReadyStatus();

    Collection<Irrigation> getAllReadyIrrigationTriggers();

    Irrigation save(Irrigation irrigation);

}
