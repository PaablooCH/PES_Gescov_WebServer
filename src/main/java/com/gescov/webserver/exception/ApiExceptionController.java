package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiError> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongCapacityException.class)
    public ResponseEntity<ApiError> handleWrongCapacityException(WrongCapacityException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlaceOutOfIndexException.class)
    public ResponseEntity<ApiError> handlePlaceOutOfIndexException(PlaceOutOfIndexException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ZeroInfectedAtSchoolException.class)
    public ResponseEntity<ApiError> handleZeroInfectedAtSchoolException(ZeroInfectedAtSchoolException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
