package com.firefly.rails.exceptions;

public class RailConfigurationException extends RailException {
    public RailConfigurationException(String message) { super(message); }
    public RailConfigurationException(String message, Throwable cause) { super(message, cause); }
}
