package com.kulsin.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}

	/**
	 * create response entity object with ApiError
	 * 
	 * @param apiError
	 * @return
	 */
	public ResponseEntity<Object> buildResponseEntity(ApiMessage apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	/**
	 * Generate error response entity object using status and message
	 * 
	 * @param status
	 * @param message
	 * @return
	 */
	public ResponseEntity<Object> buildErrorResponseEntity(HttpStatus status, String message) {
		return buildResponseEntity(new ApiMessage(status, message));
	}
}