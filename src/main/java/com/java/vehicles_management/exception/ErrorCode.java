package com.java.vehicles_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum  ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    UNAUTHORIZED_UPDATE(1008, "You do not have permission to update others", HttpStatus.FORBIDDEN),
    UNAUTHORIZED_VIEW(1009, "You do not have permission to view", HttpStatus.FORBIDDEN),

    VEHICLE_EXISTED(2002, "Vehicle existed", HttpStatus.BAD_REQUEST),
    VEHICLE_NOT_EXISTED(2005, "Vehicle not existed", HttpStatus.NOT_FOUND),

    MAINTENANCE_RECORD_EXISTED(3002, "Maintenance existed", HttpStatus.BAD_REQUEST),
    MAINTENANCE_RECORD_NOT_EXISTED(3005, "Maintenance not existed", HttpStatus.NOT_FOUND),
    ;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
