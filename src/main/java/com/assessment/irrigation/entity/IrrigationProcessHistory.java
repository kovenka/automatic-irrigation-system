package com.assessment.irrigation.entity;

import com.assessment.irrigation.enums.IrrigationProcessStatus;
import com.assessment.irrigation.enums.IrrigationType;
import com.assessment.irrigation.enums.LiquidUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity(name = "irrigation_process_history")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IrrigationProcessHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "irrigation_history_generator")
    @SequenceGenerator(name = "irrigation_history_generator", sequenceName = "IRRIGATION_HISTORY_SEQ", allocationSize = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private Long irrigationId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IrrigationProcessStatus processStatus;

    @Enumerated(EnumType.STRING)
    private IrrigationType irrigationType;

    private Double amountOfWater;

    @Enumerated(EnumType.STRING)
    private LiquidUnit liquidUnit;

    private Integer duration;

    @CreationTimestamp
    @NotNull
    private LocalDateTime createdAt;

    @Version
    private Long version;
}
