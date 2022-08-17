package com.lanh.projectweather.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ErrorMessage {
    private LocalDateTime timeStamp;
    private String message;


    private List<String> fieldErrors = new ArrayList<>();


    public ErrorMessage() {
    }

    public ErrorMessage(LocalDateTime timeStamp, String message, List<String> fieldErrors) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
