package com.assessment.irrigation.service;

import com.assessment.irrigation.payload.dto.PlotDto;

import java.util.Collection;

public interface PlotService {

    PlotDto getPlotById(Long id);

    Collection<PlotDto> findAll();

    PlotDto save(PlotDto plotDto);

    String deletePlot(Long id);
}
