package com.sa.healntrack.notification_service.common.infrastructure.exception;

import com.sa.healntrack.notification_service.common.application.error.ErrorCode;
import com.sa.healntrack.notification_service.common.application.exception.DomainException;
import com.sa.healntrack.notification_service.common.application.exception.PermanentInfrastructureException;
import com.sa.healntrack.notification_service.common.application.exception.TransientInfrastructureException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    ProblemDetail handleDomain(DomainException e) {
        return ProblemDetailsFactory.of(HttpStatus.BAD_REQUEST, "Illegal Argument", "Domain", e.getCode());
    }

    @ExceptionHandler(PermanentInfrastructureException.class)
    ProblemDetail handlePermanentInfra(PermanentInfrastructureException e) {
        return ProblemDetailsFactory.of(HttpStatus.SERVICE_UNAVAILABLE, "Permanent Infrastructure Error", "Infrastructure", e.getCode());
    }

    @ExceptionHandler(TransientInfrastructureException.class)
    ProblemDetail handleTransientInfra(TransientInfrastructureException e) {
        return ProblemDetailsFactory.of(HttpStatus.SERVICE_UNAVAILABLE, "Transient Infrastructure Error", "Infrastructure", e.getCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraintViolation(ConstraintViolationException e) {
        return ProblemDetailsFactory.of(HttpStatus.BAD_REQUEST, "Validation Error", "Validation", ErrorCode.INVALID_REQUEST_ID);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            errors.put(field, msg);
        });

        String detail = errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle("Validation Error");
        pd.setDetail(detail);
        pd.setProperty("error_category", "Validation");
        pd.setProperty("timestamp", java.time.Instant.now());
        pd.setProperty("code", ErrorCode.INVALID_REQUEST_ID.name());
        pd.setProperty("errors", errors);

        return new ResponseEntity<>(pd, headers, status);
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleUnknown(Exception e) {
        return ProblemDetailsFactory.of(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Error", "Infrastructure", ErrorCode.UNKNOWN_ERROR);
    }
}
