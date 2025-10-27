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

package com.firefly.rails.adapter.ports;

import com.firefly.rails.dtos.webhook.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Port interface for webhook management and processing.
 * 
 * Handles asynchronous notifications from banking rails about payment status changes,
 * settlements, returns, and other events.
 */
public interface WebhookPort {

    /**
     * Verify webhook signature/authentication.
     * Ensures the webhook request is legitimate and from the expected rail.
     *
     * @param request webhook verification request with signature/headers
     * @return reactive publisher with verification result
     */
    Mono<ResponseEntity<WebhookVerificationResponse>> verifyWebhook(WebhookVerificationRequest request);

    /**
     * Parse and process a webhook event.
     * Converts rail-specific webhook format to standardized event.
     *
     * @param request webhook payload and headers
     * @return reactive publisher with parsed event
     */
    Mono<ResponseEntity<WebhookEvent>> parseWebhook(ParseWebhookRequest request);

    /**
     * Register a webhook endpoint with the rail.
     * Configures the rail to send notifications to your endpoint.
     *
     * @param request webhook registration request
     * @return reactive publisher with registration response
     */
    Mono<ResponseEntity<WebhookRegistrationResponse>> registerWebhook(RegisterWebhookRequest request);

    /**
     * List registered webhooks.
     *
     * @return reactive publisher with list of registered webhooks
     */
    Mono<ResponseEntity<List<WebhookConfiguration>>> listWebhooks();

    /**
     * Delete a webhook registration.
     *
     * @param webhookId webhook identifier to delete
     * @return reactive publisher with deletion response
     */
    Mono<ResponseEntity<Void>> deleteWebhook(String webhookId);

    /**
     * Test webhook connectivity.
     * Triggers a test event to verify endpoint is reachable.
     *
     * @param webhookId webhook identifier to test
     * @return reactive publisher with test result
     */
    Mono<ResponseEntity<WebhookTestResponse>> testWebhook(String webhookId);
}
