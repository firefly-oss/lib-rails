package com.firefly.rails.exceptions;

public class SettlementException extends RailException {
    public SettlementException(String message) { super(message); }
    public SettlementException(String message, Throwable cause) { super(message, cause); }
}
