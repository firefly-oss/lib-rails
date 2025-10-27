/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.firefly.rails.controllers;

import com.firefly.rails.adapter.RailAdapter;
import com.firefly.rails.dtos.payments.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Abstract REST controller for payment rail operations.
 * 
 * Implementations inherit standard REST endpoints for payment operations.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractPaymentRailController {

    protected final RailAdapter railAdapter;

    @PostMapping("/payments")
    public Mono<ResponseEntity<PaymentResponse>> initiatePayment(@RequestBody InitiatePaymentRequest request) {
        return railAdapter.payments().initiatePayment(request);
    }

    @GetMapping("/payments/{paymentId}")
    public Mono<ResponseEntity<PaymentResponse>> getPayment(@PathVariable String paymentId) {
        return railAdapter.payments().getPayment(paymentId);
    }

    @DeleteMapping("/payments/{paymentId}")
    public Mono<ResponseEntity<PaymentResponse>> cancelPayment(@PathVariable String paymentId) {
        return railAdapter.payments().cancelPayment(paymentId);
    }

    @GetMapping("/payments")
    public Mono<ResponseEntity<List<PaymentResponse>>> listPayments(ListPaymentsRequest request) {
        return railAdapter.payments().listPayments(request);
    }

    @GetMapping("/payments/status/{reference}")
    public Mono<ResponseEntity<PaymentStatusResponse>> getPaymentStatus(@PathVariable String reference) {
        return railAdapter.payments().getPaymentStatus(reference);
    }
}
