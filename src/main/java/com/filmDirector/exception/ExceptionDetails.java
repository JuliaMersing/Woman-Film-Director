package com.filmDirector.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected LocalDateTime timestamp;
    protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
}
