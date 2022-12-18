package com.assessment.irrigation.controller;

import com.assessment.irrigation.payload.converter.IrrigationConverter;
import com.assessment.irrigation.payload.dto.IrrigationDto;
import com.assessment.irrigation.payload.request.AutomaticIrrigationRequest;
import com.assessment.irrigation.service.AutomaticIrrigationService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/irrigations", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AutomaticIrrigationController {

    private AutomaticIrrigationService automaticIrrigationService;

    @PostMapping
    public IrrigationDto addNewIrrigation(@Valid @RequestBody AutomaticIrrigationRequest automaticIrrigationRequest) {
        IrrigationDto irrigation = automaticIrrigationService.createNewIrrigation(
                IrrigationConverter.toIrrigationDto(automaticIrrigationRequest), automaticIrrigationRequest.getPlotId());
        setHateoasGetIrrigationLink(irrigation);
        return irrigation;
    }

    @GetMapping("/{id}")
    public IrrigationDto getIrrigationById(@PathVariable Long id) {
        IrrigationDto irrigation = automaticIrrigationService.getIrrigationById(id);
        Link selfLink = linkTo(methodOn(AutomaticIrrigationController.class)
                .updateIrrigation(irrigation)).withSelfRel();
        irrigation.add(selfLink);
        return irrigation;
    }

    @PutMapping
    public IrrigationDto updateIrrigation(@Valid @RequestBody IrrigationDto irrigationDto) {
        IrrigationDto irrigation = automaticIrrigationService.update(irrigationDto);
        setHateoasGetIrrigationLink(irrigation);
        return irrigation;
    }

    private void setHateoasGetIrrigationLink(IrrigationDto irrigation) {
        Link selfLink = linkTo(methodOn(AutomaticIrrigationController.class)
                .getIrrigationById(irrigation.getId())).withSelfRel();
        irrigation.add(selfLink);
    }
}
