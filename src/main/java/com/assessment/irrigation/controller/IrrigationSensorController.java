package com.assessment.irrigation.controller;

import com.assessment.irrigation.payload.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is Mock for the Irrigation sensor which will be existed in external system
 */
@RequestMapping(value = "/sensor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class IrrigationSensorController {

    @PostMapping
    public Response triggerSensor() {
        return new Response(HttpStatus.OK.toString(), HttpStatus.OK.getReasonPhrase());
    }

}
