package com.bne.postulaciones_service.infrastructure.handler;

import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.application.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> onValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = base("VALIDATION_ERROR", HttpStatus.BAD_REQUEST.value());
        Map<String, String> errors = new HashMap<>();
        for (FieldError f : ex.getBindingResult().getFieldErrors()) {
            errors.put(f.getField(), f.getDefaultMessage());
        }
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> onResponseStatus(ResponseStatusException ex) {
        Map<String, Object> body = base(ex.getStatusCode().toString(), ex.getStatusCode().value());
        body.put("message", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

    // ——— Tus excepciones de aplicación ———
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> onNotFound(NotFoundException ex) {
        Map<String, Object> body = base("NOT_FOUND", HttpStatus.NOT_FOUND.value());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> onConflict(ConflictException ex) {
        Map<String, Object> body = base("CONFLICT", HttpStatus.CONFLICT.value());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> onIllegalArg(IllegalArgumentException ex) {
        Map<String, Object> body = base("BAD_REQUEST", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> onIllegalState(IllegalStateException ex) {
        Map<String, Object> body = base("CONFLICT", HttpStatus.CONFLICT.value());
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    // ——— Fallback 500 para lo no previsto ———
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> onGeneric(Exception ex) {
        Map<String, Object> body = base("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("message", "Ocurrió un error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private Map<String, Object> base(String error, int status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status);
        body.put("error", error);
        return body;
    }
}
