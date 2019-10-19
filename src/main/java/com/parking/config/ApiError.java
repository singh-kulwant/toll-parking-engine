package com.parking.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
class ApiError {

	private String message;

	private HttpStatus status;

	private String debugMessage;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	ApiError() {
		timestamp = LocalDateTime.now();
	}

	ApiError(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getCause().getMessage();
	}
}