package com.akazimour.COMPANY_MS.exception;

import org.springframework.http.HttpStatus;

public class ModelErrorResponse {

    private String message;
    private HttpStatus httpStatus;

    public ModelErrorResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
