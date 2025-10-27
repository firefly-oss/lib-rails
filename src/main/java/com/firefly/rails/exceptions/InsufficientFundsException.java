package com.firefly.rails.exceptions;

public class InsufficientFundsException extends RailException {
    public InsufficientFundsException(String message) { super(message); }
    public InsufficientFundsException(String message, Throwable cause) { super(message, cause); }
}
