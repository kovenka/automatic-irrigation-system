package com.assessment.irrigation.controller;

import com.assessment.irrigation.payload.converter.PlotConverter;
import com.assessment.irrigation.payload.dto.PlotDto;
import com.assessment.irrigation.payload.request.PlotRequest;
import com.assessment.irrigation.service.PlotService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/plots", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class PlotController {

    private PlotService plotService;

    @GetMapping("/{id}")
    public PlotDto getPlotById(@PathVariable Long id) {
        PlotDto plot = plotService.getPlotById(id);

        Link selfLink = linkTo(methodOn(PlotController.class)
                .updatePlot(plot)).withSelfRel();
        plot.add(selfLink);
        return plot;
    }

    @PutMapping
    public PlotDto updatePlot(@Valid @RequestBody PlotDto plotDto) {
        PlotDto plot = plotService.save(plotDto);
        setHateoasGetLandLink(plot);
        return plot;
    }

    @GetMapping()
    public CollectionModel<PlotDto> findAllPlots() {
        Collection<PlotDto> lands = plotService.findAll();

        for (PlotDto land : lands) {
            setHateoasGetLandLink(land);

        }
        Link link = linkTo(PlotController.class).withSelfRel();
        CollectionModel<PlotDto> result = CollectionModel.of(lands, link);
        return result;
    }

    @PostMapping
    public PlotDto addNewPlot(@Valid @RequestBody PlotRequest plotRequest) {
        PlotDto land = plotService.save(PlotConverter.toPlotDto(plotRequest));
        setHateoasGetLandLink(land);
        return land;
    }

    private void setHateoasGetLandLink(PlotDto plot) {
        Link selfLink = linkTo(methodOn(PlotController.class)
                .getPlotById(plot.getId())).withSelfRel();
        plot.add(selfLink);
    }

    @DeleteMapping("/{id}")
    public String deletePlot(@PathVariable Long id) {
        return plotService.deletePlot(id);
    }
}
