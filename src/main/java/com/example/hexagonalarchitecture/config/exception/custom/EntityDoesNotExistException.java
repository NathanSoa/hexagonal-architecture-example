package com.example.hexagonalarchitecture.config.exception.custom;

public class EntityDoesNotExistException extends RuntimeException {

    public EntityDoesNotExistException(String message) {
        super(message);
    }
}
