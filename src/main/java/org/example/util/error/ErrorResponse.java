package org.example.util.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.example.util.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String cause;
    private String code;
    private String message;
    private HttpStatus httpStatus;

    @Builder
    public ErrorResponse(String cause, String code, String message, HttpStatus httpStatus) {
        this.cause = cause;
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
    public ErrorResponse(ErrorCode errorCode) {
        this.cause = errorCode.getCause();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
    }
}
