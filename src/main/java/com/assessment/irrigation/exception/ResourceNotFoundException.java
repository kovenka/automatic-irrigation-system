package com.assessment.irrigation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String errorMessage;
    public ResourceNotFoundException(String message) {
        this.errorMessage = message;
    }

    public ResourceNotFoundException() {
    }
}
