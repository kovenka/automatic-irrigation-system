package com.assessment.irrigation.payload.converter;

import com.assessment.irrigation.entity.Plot;
import com.assessment.irrigation.payload.dto.PlotDto;
import com.assessment.irrigation.payload.request.PlotRequest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class PlotConverter {

    public static Plot toPlot(PlotDto plotDto) {
        return plotDto == null ? null : Plot.builder().id(plotDto.getId()).agriculturalCropType(plotDto.getAgriculturalCropType())
                .areaUnit(plotDto.getAreaUnit()).cultivatedArea(plotDto.getCultivatedArea())
                .description(plotDto.getDescription()).name(plotDto.getName()).latitude(plotDto.getLatitude())
                .longitude(plotDto.getLongitude()).irrigation(IrrigationConverter.toIrrigation(plotDto.getIrrigation())).build();
    }

    public static PlotDto toPlotDto(PlotRequest plotRequest) {
        return plotRequest == null ? null : PlotDto.builder().agriculturalCropType(plotRequest.getAgriculturalCropType())
                .areaUnit(plotRequest.getAreaUnit()).cultivatedArea(plotRequest.getCultivatedArea())
                .description(plotRequest.getDescription()).name(plotRequest.getName()).latitude(plotRequest.getLatitude())
                .longitude(plotRequest.getLongitude()).irrigation(IrrigationConverter.toIrrigationDto(plotRequest.getIrrigation()))
                .build();
    }

    public static PlotDto toPlotDto(Plot plot) {
        return plot == null ? null : PlotDto.builder().id(plot.getId()).agriculturalCropType(plot.getAgriculturalCropType())
                .areaUnit(plot.getAreaUnit()).cultivatedArea(plot.getCultivatedArea())
                .description(plot.getDescription()).name(plot.getName()).latitude(plot.getLatitude())
                .longitude(plot.getLongitude()).irrigation(IrrigationConverter.toIrrigationDto(plot.getIrrigation()))
                .createdAt(plot.getCreatedAt()).updatedAt(plot.getUpdatedAt()).build();
    }

    public static Collection<PlotDto> toPlotDto(Collection<Plot> plots) {
        return CollectionUtils.isEmpty(plots) ? new ArrayList<>() : plots.stream().map(PlotConverter::toPlotDto).collect(Collectors.toList());
    }
}
