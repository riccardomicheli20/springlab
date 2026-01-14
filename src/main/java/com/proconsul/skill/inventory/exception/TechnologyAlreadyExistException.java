package com.proconsul.skill.inventory.exception;

public class TechnologyAlreadyExistException extends RuntimeException {
    public TechnologyAlreadyExistException(String message) {
        super(message);
    }
}
