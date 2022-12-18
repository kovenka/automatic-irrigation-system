package com.assessment.irrigation.service;

import com.assessment.irrigation.entity.Irrigation;
import com.assessment.irrigation.entity.IrrigationProcessHistory;
import com.assessment.irrigation.enums.IrrigationProcessStatus;

public interface IrrigationProcessHistoryService {
    IrrigationProcessHistory saveIrrigationRequest(Irrigation irrigation, IrrigationProcessStatus irrigationProcessStatus);
}
