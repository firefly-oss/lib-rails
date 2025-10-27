package com.firefly.rails.exceptions;

public class RailCommunicationException extends RailException {
    public RailCommunicationException(String message) { super(message); }
    public RailCommunicationException(String message, Throwable cause) { super(message, cause); }
}
