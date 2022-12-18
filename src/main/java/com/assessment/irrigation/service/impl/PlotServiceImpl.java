package com.assessment.irrigation.service.impl;

import com.assessment.irrigation.entity.Plot;
import com.assessment.irrigation.exception.ResourceNotFoundException;
import com.assessment.irrigation.payload.converter.PlotConverter;
import com.assessment.irrigation.payload.dto.PlotDto;
import com.assessment.irrigation.repository.PlotRepository;
import com.assessment.irrigation.service.PlotService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collection;

@AllArgsConstructor
@Service
@Log4j2
public class PlotServiceImpl implements PlotService {
    private final PlotRepository plotRepository;

    @Override
    public PlotDto getPlotById(Long id) {
        Plot plot = plotRepository.getReferenceById(id);
        log.debug("plotId={}, with unit area={}", plot.getId(), plot.getAreaUnit());
        return PlotConverter.toPlotDto(plot);
    }

    @Override
    public Collection<PlotDto> findAll() {
        Collection<Plot> plots = plotRepository.findAll();
        if (CollectionUtils.isEmpty(plots)) {
            throw new ResourceNotFoundException("Invalid Plot Id");
        }
        return PlotConverter.toPlotDto(plots);
    }

    private Plot save(Plot plot) {
        return plotRepository.save(plot);
    }

    @Override
    public String deletePlot(Long id) {
        plotRepository.deleteById(id);
        return "Delete Successfully";
    }

    @Override
    public PlotDto save(PlotDto plotDto) {
        LocalDateTime now = LocalDateTime.now();
        Plot plot = PlotConverter.toPlot(plotDto);
        if (plot.getId() == null) {
            //create new plot
            plot.setCreatedAt(now);
            if (plot.getIrrigation() != null) {
                plot.getIrrigation().setCreatedAt(now);
                if (plot.getIrrigation().getNextIrrigationAt() == null) {
                    plot.getIrrigation().setNextIrrigationAt(LocalDateTime.now());
                }
            }
        } else {
            Plot currentPlot = plotRepository.getReferenceById(plot.getId());
            plot.setCreatedAt(currentPlot.getCreatedAt());
            if (plot.getIrrigation() != null) {
                plot.getIrrigation().setCreatedAt(currentPlot.getCreatedAt());
            }
        }
        plot.setUpdatedAt(now);
        if (plot.getIrrigation() != null) {
            plot.getIrrigation().setUpdatedAt(now);

        }
        return PlotConverter.toPlotDto(save(plot));
    }
}
