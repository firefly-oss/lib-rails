package com.firefly.rails.exceptions;

public class PaymentRejectedException extends RailException {
    public PaymentRejectedException(String message) { super(message); }
    public PaymentRejectedException(String message, Throwable cause) { super(message, cause); }
}
