package io.github.Guimaraes131.strong_api.controller.common;

import io.github.Guimaraes131.strong_api.controller.dto.ErrorResponse;
import io.github.Guimaraes131.strong_api.controller.dto.FieldError;
import io.github.Guimaraes131.strong_api.exception.DuplicateRecordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldErrors = e.getFieldErrors();

        List<FieldError> errors = fieldErrors.stream()
                .map(fe -> new FieldError(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error.", errors);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateRecordException(DuplicateRecordException e) {
        return ErrorResponse.conflictResponse(e.getMessage());
    }
}
