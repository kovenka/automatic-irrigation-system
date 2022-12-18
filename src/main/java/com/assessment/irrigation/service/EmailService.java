package com.assessment.irrigation.service;

import com.assessment.irrigation.payload.dto.IrrigationDto;

public interface EmailService {
    void sendSensorFailureAlarm(IrrigationDto irrigationDto, String errorMessage);
}
