package com.firefly.rails.exceptions;

public class RailAuthenticationException extends RailException {
    public RailAuthenticationException(String message) { super(message); }
    public RailAuthenticationException(String message, Throwable cause) { super(message, cause); }
}
