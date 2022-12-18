package com.assessment.irrigation.service.impl;

import com.assessment.irrigation.entity.Irrigation;
import com.assessment.irrigation.entity.Plot;
import com.assessment.irrigation.enums.IrrigationStatus;
import com.assessment.irrigation.payload.converter.IrrigationConverter;
import com.assessment.irrigation.payload.dto.IrrigationDto;
import com.assessment.irrigation.repository.IrrigationRepository;
import com.assessment.irrigation.repository.PlotRepository;
import com.assessment.irrigation.service.AutomaticIrrigationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AutomaticIrrigationServiceImpl implements AutomaticIrrigationService {

    private final IrrigationRepository irrigationRepository;
    private final PlotRepository plotRepository;

    private final EntityManager entityManager;

    private Irrigation getById(Long id) {
        return irrigationRepository.getReferenceById(id);
    }

    @Override
    public IrrigationDto getIrrigationById(Long id) {
        return IrrigationConverter.toIrrigationDto(this.getById(id));
    }

    @Override
    public IrrigationDto createNewIrrigation(IrrigationDto irrigationDto, Long landId) {
        LocalDateTime now = LocalDateTime.now();
        Plot plot = plotRepository.getReferenceById(landId);
        plot.setIrrigation(IrrigationConverter.toIrrigation(irrigationDto));
        plot.getIrrigation().setCreatedAt(now);
        plot.getIrrigation().setUpdatedAt(now);
        plot.setUpdatedAt(now);
        if (plot.getIrrigation().getNextIrrigationAt() == null) {
            plot.getIrrigation().setNextIrrigationAt(LocalDateTime.now());
        }
        return IrrigationConverter.toIrrigationDto(plotRepository.save(plot).getIrrigation());
    }

    @Override
    public IrrigationDto update(IrrigationDto irrigationDto) {
        Irrigation currentIrrigation = getById(irrigationDto.getId());
        Irrigation irrigation = IrrigationConverter.toIrrigation(irrigationDto);
        irrigation.setUpdatedAt(LocalDateTime.now());

        //Not allowed to update created At field
        irrigation.setCreatedAt(currentIrrigation.getCreatedAt());
        return IrrigationConverter.toIrrigationDto(this.save(irrigation));
    }

    @Override
    public Collection<Irrigation> getAllReadyIrrigationTriggersAndUpdateToReadyStatus() {
        return getAllReadyIrrigationTriggers().stream()
                .filter(irrigation ->
                        updateIrrigationStatusWithConditionalUpdate(irrigation, IrrigationStatus.ACTIVE, IrrigationStatus.PROCESSING))
                .collect(Collectors.toList());

    }

    boolean updateIrrigationStatusWithConditionalUpdate(Irrigation irrigation, IrrigationStatus currentStatus, IrrigationStatus newStatus) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Irrigation> q = criteriaBuilder.createCriteriaUpdate(Irrigation.class);
        Root<Irrigation> root = q.from(Irrigation.class);

        Collection<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("id"), irrigation.getId()));
        predicates.add(criteriaBuilder.equal(root.get("irrigationStatus"), currentStatus));

        q.set(root.get("irrigationStatus"), newStatus).set(root.get("updatedAt"), LocalDateTime.now())
                .where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(q).executeUpdate() > 0;
    }

    @Override
    public Collection<Irrigation> getAllReadyIrrigationTriggers() {
        return irrigationRepository.findAllByIrrigationStatusIn(List.of(IrrigationStatus.ACTIVE)).stream()
                .filter(irrigation -> irrigation.getNextIrrigationAt() != null &&
                        (irrigation.getNextIrrigationAt().isBefore(LocalDateTime.now()) || irrigation.getNextIrrigationAt().isEqual(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    public Irrigation save(Irrigation irrigation) {
        return irrigationRepository.save(irrigation);
    }
}
