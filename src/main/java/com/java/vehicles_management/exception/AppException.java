package com.java.vehicles_management.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppException extends RuntimeException{
    ErrorCode errorCode;
}
