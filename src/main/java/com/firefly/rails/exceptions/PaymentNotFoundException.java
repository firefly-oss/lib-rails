package com.firefly.rails.exceptions;

public class PaymentNotFoundException extends RailException {
    public PaymentNotFoundException(String message) { super(message); }
    public PaymentNotFoundException(String message, Throwable cause) { super(message, cause); }
}
