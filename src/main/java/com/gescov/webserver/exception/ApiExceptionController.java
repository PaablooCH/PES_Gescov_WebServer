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
    public ResponseEntity<ApiError> handleRequestAnsweredException(RequestAnsweredException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ChatAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleChatAlreadyExistsException(ChatAlreadyExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassroomDateException.class)
    public ResponseEntity<ApiError> handleClassroomDateException(ClassroomDateException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IsNotAnAdministratorException.class)
    public ResponseEntity<ApiError> handleIsNotAnAdministratorException(IsNotAnAdministratorException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotInSchool.class)
    public ResponseEntity<ApiError> handleNotInSchool(NotInSchool ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChatBetweenStudentsNotPermitedException.class)
    public ResponseEntity<ApiError> handleChatBetweenStudentsNotPermitedException(ChatBetweenStudentsNotPermitedException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UNeedToWait.class)
    public ResponseEntity<ApiError> handleUNeedToWait(UNeedToWait ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IsNotTheCreatorException.class)
    public ResponseEntity<ApiError> handleIsNotTheCreatorException(IsNotTheCreatorException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotTeacherException.class)
    public ResponseEntity<ApiError> handleNotTeacherException(NotTeacherException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CreatorCantBeDeletedException.class)
    public ResponseEntity<ApiError> handleCreatorCantBeDeletedException(CreatorCantBeDeletedException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAParticipantException.class)
    public ResponseEntity<ApiError> handleNotAParticipantException(NotAParticipantException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IncorrectEntryCodeException.class)
    public ResponseEntity<ApiError> handleIncorrectEntryCodeException(IncorrectEntryCodeException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClassroomInUseException.class)
    public ResponseEntity<ApiError> handleClassroomInUseException(ClassroomInUseException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotEqualsException.class)
    public ResponseEntity<ApiError> handleNotEqualsException(NotEqualsException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ChatWithSamePersonNotAllowedException.class)
    public ResponseEntity<ApiError> handleChatWithSamePersonNotAllowedException(ChatWithSamePersonNotAllowedException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAnyTextException.class)
    public ResponseEntity<ApiError> handleNotAnyTextException(NotAnyTextException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UAreInfected.class)
    public ResponseEntity<ApiError> handleUAreInfected(UAreInfected ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

}
