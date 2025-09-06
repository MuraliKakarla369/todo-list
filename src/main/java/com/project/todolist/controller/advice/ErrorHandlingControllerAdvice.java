package com.project.todolist.controller.advice;

import com.project.todolist.model.ErrorEntry;
import com.project.todolist.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class ErrorHandlingControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> onException(ResponseStatusException exception) {
        LOG.error("Response Status Exception", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorEntry errorEntry = new ErrorEntry();
        errorEntry.setMessage(exception.getReason());
        errorResponse.getErrors().add(errorEntry);
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }
}
