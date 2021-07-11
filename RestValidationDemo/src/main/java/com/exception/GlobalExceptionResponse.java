package com.exception;

import lombok.Data;

@Data
public class GlobalExceptionResponse {
    private Integer errorCode;
    private String errorMessage;
}
