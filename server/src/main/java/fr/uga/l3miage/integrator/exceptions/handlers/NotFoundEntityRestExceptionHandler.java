package fr.uga.l3miage.integrator.exceptions.handlers;

import fr.uga.l3miage.integrator.exceptions.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class NotFoundEntityRestExceptionHandler {
    @ExceptionHandler(NotFoundEntityRestException.class)
    public ResponseEntity<NotFoundErrorResponse> handle(HttpServletRequest httpServletRequest, Exception e){

        NotFoundEntityRestException exception = (NotFoundEntityRestException) e;

        final NotFoundErrorResponse response = NotFoundErrorResponse.
                builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();

        log.warn(exception.getMessage());

        return ResponseEntity.status(404).body(response);
    }
}
