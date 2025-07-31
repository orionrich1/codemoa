package com.codemoa.project.domain.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final int status;
    private final String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
