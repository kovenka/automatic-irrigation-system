package com.assessment.irrigation.entity;

import com.assessment.irrigation.enums.AgriculturalCropType;
import com.assessment.irrigation.enums.AreaUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity(name = "plot")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "land_generator")
    @SequenceGenerator(name = "land_generator", sequenceName = "PLOT_SEQ", allocationSize = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;
    private String description;

    @Max(90)
    @Min(-90)
    private Double latitude;

    @Max(180)
    @Min(-180)
    private Double longitude;

    @NotNull
    private Double cultivatedArea;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AreaUnit areaUnit;

    @Enumerated(EnumType.STRING)
    private AgriculturalCropType agriculturalCropType;

    @CreationTimestamp
    @NotNull
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @NotNull
    private LocalDateTime updatedAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "irrigation_id", referencedColumnName = "id")
    private Irrigation irrigation;

    @Version
    private Long version;
}
