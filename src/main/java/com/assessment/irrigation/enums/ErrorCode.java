package com.assessment.irrigation.enums;

public enum ErrorCode {
    RESOURCE_NOT_FOUND( "404","Resource Not Found"),
    METHOD_NOT_ALLOWED( "404","Method Not Allowed"),
    UNEXPECTED_ERROR( "410","Unexpected Error"),
    UNSUPPORTED_MEDIA_TYPE( "415","Unsupported Media Type"),
    SENSOR_COMMUNICATION_FAILED( "500","Failure during the communication with sensor API"),
    BAD_GATEWAY( "502","Bad Gateway"),
    SERVICE_UNAVAILABLE( "503","Service Unavailable");

    private String statusCode;
    private String errorMessage;

    ErrorCode(String statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
