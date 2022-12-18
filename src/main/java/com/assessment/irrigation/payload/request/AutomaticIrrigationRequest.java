package com.assessment.irrigation.payload.request;

import com.assessment.irrigation.enums.IrrigationStatus;
import com.assessment.irrigation.enums.IrrigationType;
import com.assessment.irrigation.enums.LiquidUnit;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class AutomaticIrrigationRequest {

    @NotNull
    private Long plotId;
    private IrrigationType irrigationType;

    @NotNull
    private Double amountOfWater;

    @NotNull
    private LiquidUnit liquidUnit;

    @NotNull
    private Integer duration;

    @NotNull
    private Double interval;

    private IrrigationStatus irrigationStatus;

    private LocalDateTime nextIrrigationAt;
}
