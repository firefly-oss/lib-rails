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
import com.firefly.rails.dtos.webhook.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * Abstract REST controller for webhook operations.
 * 
 * Implementations inherit standard REST endpoints for webhook handling.
 * Provides signature verification, event parsing, and webhook management.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractWebhookController {

    protected final RailAdapter railAdapter;

    /**
     * Webhook endpoint to receive notifications from rails.
     * Verifies signature and parses the event.
     */
    @PostMapping("/webhooks")
    public Mono<ResponseEntity<WebhookEvent>> handleWebhook(
            @RequestBody String payload,
            @RequestHeader Map<String, String> headers) {
        
        log.debug("Received webhook for rail: {}", railAdapter.getRailType());
        
        // First verify the webhook signature
        return railAdapter.webhooks()
                .verifyWebhook(WebhookVerificationRequest.builder()
                        .payload(payload)
                        .headers(headers)
                        .build())
                .flatMap(verificationResp -> {
                    if (!verificationResp.getBody().isVerified()) {
                        log.warn("Webhook signature verification failed");
                        return Mono.just(ResponseEntity.badRequest().build());
                    }
                    
                    // Parse the webhook event
                    return railAdapter.webhooks()
                            .parseWebhook(ParseWebhookRequest.builder()
                                    .payload(payload)
                                    .headers(headers)
                                    .build());
                });
    }

    /**
     * Register a new webhook endpoint with the rail.
     */
    @PostMapping("/webhooks/register")
    public Mono<ResponseEntity<WebhookRegistrationResponse>> registerWebhook(
            @RequestBody RegisterWebhookRequest request) {
        return railAdapter.webhooks().registerWebhook(request);
    }

    /**
     * List all registered webhooks.
     */
    @GetMapping("/webhooks")
    public Mono<ResponseEntity<List<WebhookConfiguration>>> listWebhooks() {
        return railAdapter.webhooks().listWebhooks();
    }

    /**
     * Delete a webhook registration.
     */
    @DeleteMapping("/webhooks/{webhookId}")
    public Mono<ResponseEntity<Void>> deleteWebhook(@PathVariable String webhookId) {
        return railAdapter.webhooks().deleteWebhook(webhookId);
    }

    /**
     * Test webhook connectivity.
     */
    @PostMapping("/webhooks/{webhookId}/test")
    public Mono<ResponseEntity<WebhookTestResponse>> testWebhook(@PathVariable String webhookId) {
        return railAdapter.webhooks().testWebhook(webhookId);
    }
}
