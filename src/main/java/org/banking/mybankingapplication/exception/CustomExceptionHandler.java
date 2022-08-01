package org.banking.mybankingapplication.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//        details.add(ex.getLocalizedMessage());
//        ErrorResponse error = new ErrorResponse("INTERNAL_SERVER_ERROR", details);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//    @ExceptionHandler(EntityNotFoundException.class)
//    public final ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception ex , WebRequest request){
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//        details.add(ex.getLocalizedMessage());
//        ErrorResponse error = new ErrorResponse("ENTITY_NOT_FOUND", details);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_local_message", exception.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseMap);
    }

    @ExceptionHandler(CustomJwtException.class)
    public ResponseEntity<Map<String, String>> handleCustomJwtException(CustomJwtException exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_local_message", exception.getLocalizedMessage());
        errorResponseMap.put("error_status", exception.getHttpStatus().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseMap);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_local_message", exception.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponseMap);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(Exception exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_local_message", exception.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseMap);
    }
    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEntityException(Exception exception){
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_local_message", exception.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseMap);
    }



}
