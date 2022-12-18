package com.assessment.irrigation.service.impl;

import com.assessment.irrigation.payload.dto.IrrigationDto;
import com.assessment.irrigation.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${automatic-irrigation-system.mail.from}")
    String from;

    @Value("${automatic-irrigation-system.mail.to}")
    String to;

    @Override
    public void sendSensorFailureAlarm(IrrigationDto irrigationDto, String errorMessage) {
        sendSimpleMessage(from, to, "Error Occurred while processing irrigation Id=" + irrigationDto.getId(), "Error Occurred while process irrigation Id=" + irrigationDto.getId() + ", at=" + irrigationDto.getNextIrrigationAt() + "with error=" + errorMessage);
    }


    private void sendSimpleMessage(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
