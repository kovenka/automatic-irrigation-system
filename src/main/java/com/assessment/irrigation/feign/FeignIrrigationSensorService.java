package com.assessment.irrigation.feign;

import com.assessment.irrigation.entity.Irrigation;
import com.assessment.irrigation.payload.dto.IrrigationDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "sensor-service", url = "localhost:8080/automatic-irrigation-system")
public interface FeignIrrigationSensorService {

    @Retry(name = "retryTriggerSensor")
    @CircuitBreaker(name = "triggerSensorCircuitBreaker")
    @PostMapping(value = "/api/v1/sensor", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> triggerIrrigationSensor(IrrigationDto irrigationDto);

    default ResponseEntity<?> irrigationTriggerFallback(Irrigation irrigation, Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
