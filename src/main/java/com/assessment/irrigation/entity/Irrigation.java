package com.assessment.irrigation.entity;

import com.assessment.irrigation.enums.IrrigationStatus;
import com.assessment.irrigation.enums.IrrigationType;
import com.assessment.irrigation.enums.LiquidUnit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity(name = "irrigation")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Irrigation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "irrigation_generator")
    @SequenceGenerator(name = "irrigation_generator", sequenceName = "IRRIGATION_SEQ", allocationSize = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private IrrigationType irrigationType;

    @NotNull
    private Double amountOfWater;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LiquidUnit liquidUnit;

    @NotNull
    private Integer duration;

    @NotNull
    @Column(name = "delay_interval")
    private Double interval;

    @Enumerated(EnumType.STRING)
    @NotNull
    private IrrigationStatus irrigationStatus;

    private LocalDateTime nextIrrigationAt;

    @CreationTimestamp
    @NotNull
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @NotNull
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "irrigation", fetch = FetchType.LAZY)
    private Plot plot;

    @Version
    private Long version;

}
