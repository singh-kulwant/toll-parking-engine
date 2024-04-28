package com.kulsin.exception;

import com.kulsin.models.response.ParkUnparkResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ParkUnparkResponse> handleException(Exception exception) {
        String message = "Internal Server Error";

        log.error("Exception occurred!", exception);

        if (exception instanceof EntityNotFoundException ||
            exception instanceof EmptyResultDataAccessException) {
            message = String.format("EntityNotFoundException - %s", exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ParkUnparkResponse.builder()
                                .success(false)
                                .message(message)
                                .build()
                );

    }

}
