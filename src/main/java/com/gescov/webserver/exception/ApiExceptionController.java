package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.security.GeneralSecurityException;
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

    @ExceptionHandler(GeneralSecurityException.class)
    public ResponseEntity<ApiError> handleGeneralSecurityException(GeneralSecurityException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiError> handleIOException(IOException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestAnsweredException.class)
    public ResponseEntity<ApiError> handleIOException(RequestAnsweredException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

}
