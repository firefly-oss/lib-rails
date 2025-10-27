package com.firefly.rails.exceptions;

public class InvalidAccountException extends RailException {
    public InvalidAccountException(String message) { super(message); }
    public InvalidAccountException(String message, Throwable cause) { super(message, cause); }
}
