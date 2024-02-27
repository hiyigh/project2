package org.example.util.exception;

import org.example.util.error.ErrorResponse;

public class CustomException extends RuntimeException{
    private ErrorCode errorCode;
    private ErrorResponse errorResponse;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorResponse = new ErrorResponse(errorCode);
    }
    public ErrorResponse getResponse(){
        return errorResponse;
    }
}
