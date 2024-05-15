package fr.uga.l3miage.integrator.exceptions.rest;

import lombok.Getter;

@Getter
public class ConstraintViolationEntityRestException extends RuntimeException {
    public ConstraintViolationEntityRestException(String message) {
        super(message);
    }
}
