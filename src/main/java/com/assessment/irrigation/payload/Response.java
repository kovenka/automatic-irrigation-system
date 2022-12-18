package com.assessment.irrigation.payload;

import com.assessment.irrigation.enums.ErrorCode;
import lombok.Data;

@Data
public class Response {
    private String code;
    private String description;

    public Response() {

    }

    public Response(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Response(ErrorCode errorCode) {
        this.code = errorCode.getStatusCode();
        this.description = errorCode.getErrorMessage();
    }
}
