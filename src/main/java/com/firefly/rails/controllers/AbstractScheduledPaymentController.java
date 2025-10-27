/*
 * Copyright 2025 Firefly Software Solutions Inc
 */

package com.firefly.rails.controllers;

import com.firefly.rails.adapter.RailAdapter;
import com.firefly.rails.dtos.scheduled.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Abstract REST controller for scheduled and recurring payment operations.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractScheduledPaymentController {

    protected final RailAdapter railAdapter;

    @PostMapping("/scheduled-payments")
    public Mono<ResponseEntity<ScheduledPaymentResponse>> createScheduledPayment(
            @RequestBody CreateScheduledPaymentRequest request) {
        return railAdapter.scheduledPayments().createScheduledPayment(request);
    }

    @GetMapping("/scheduled-payments/{paymentId}")
    public Mono<ResponseEntity<ScheduledPaymentResponse>> getScheduledPayment(
            @PathVariable String paymentId) {
        return railAdapter.scheduledPayments().getScheduledPayment(paymentId);
    }

    @GetMapping("/scheduled-payments")
    public Mono<ResponseEntity<List<ScheduledPaymentResponse>>> listScheduledPayments(
            ListScheduledPaymentsRequest request) {
        return railAdapter.scheduledPayments().listScheduledPayments(request);
    }

    @DeleteMapping("/scheduled-payments/{paymentId}")
    public Mono<ResponseEntity<CancellationResponse>> cancelScheduledPayment(
            @PathVariable String paymentId) {
        return railAdapter.scheduledPayments().cancelScheduledPayment(paymentId);
    }

    @PutMapping("/scheduled-payments")
    public Mono<ResponseEntity<ScheduledPaymentResponse>> updateScheduledPayment(
            @RequestBody UpdateScheduledPaymentRequest request) {
        return railAdapter.scheduledPayments().updateScheduledPayment(request);
    }

    @PostMapping("/recurring-payments")
    public Mono<ResponseEntity<RecurringPaymentResponse>> createRecurringPayment(
            @RequestBody CreateRecurringPaymentRequest request) {
        return railAdapter.scheduledPayments().createRecurringPayment(request);
    }

    @GetMapping("/recurring-payments/{paymentId}")
    public Mono<ResponseEntity<RecurringPaymentResponse>> getRecurringPayment(
            @PathVariable String paymentId) {
        return railAdapter.scheduledPayments().getRecurringPayment(paymentId);
    }

    @DeleteMapping("/recurring-payments/{paymentId}")
    public Mono<ResponseEntity<CancellationResponse>> cancelRecurringPayment(
            @PathVariable String paymentId) {
        return railAdapter.scheduledPayments().cancelRecurringPayment(paymentId);
    }

    @GetMapping("/recurring-payments/{paymentId}/history")
    public Mono<ResponseEntity<List<PaymentExecutionHistory>>> getRecurringPaymentHistory(
            @PathVariable String paymentId) {
        return railAdapter.scheduledPayments().getRecurringPaymentHistory(paymentId);
    }
}
