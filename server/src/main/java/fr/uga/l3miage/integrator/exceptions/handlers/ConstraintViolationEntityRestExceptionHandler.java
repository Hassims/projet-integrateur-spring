package fr.uga.l3miage.integrator.exceptions.handlers;

import fr.uga.l3miage.integrator.exceptions.BadRequestErrorResponse;
import fr.uga.l3miage.integrator.exceptions.rest.ConstraintViolationEntityRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ConstraintViolationEntityRestExceptionHandler {
    @ExceptionHandler(ConstraintViolationEntityRestException.class)
    public ResponseEntity<BadRequestErrorResponse> handle(HttpServletRequest httpServletRequest, Exception e){

        ConstraintViolationEntityRestException exception = (ConstraintViolationEntityRestException) e;

        final BadRequestErrorResponse response = BadRequestErrorResponse.
                builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();

        log.warn(exception.getMessage());

        return ResponseEntity.status(400).body(response);
    }
}
