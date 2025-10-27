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

package com.firefly.rails.services;

import com.firefly.rails.adapter.RailAdapter;
import com.firefly.rails.config.RailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Abstract base service for banking rail implementations.
 * 
 * Provides common functionality like logging, error handling, and resilience patterns.
 * Implementations should extend this class to get standardized behavior.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractRailService {

    protected final RailAdapter railAdapter;
    protected final RailProperties railProperties;

    /**
     * Execute a rail operation with logging and error handling.
     */
    protected <T> Mono<ResponseEntity<T>> executeOperation(
            String operationName, 
            Mono<ResponseEntity<T>> operation) {
        
        log.debug("Executing {} operation for rail {}", operationName, railAdapter.getRailType());
        
        return operation
                .doOnSuccess(result -> log.debug("{} operation completed successfully", operationName))
                .doOnError(error -> log.error("{} operation failed: {}", operationName, error.getMessage(), error));
    }

    /**
     * Get the rail type.
     */
    public String getRailType() {
        return railAdapter.getRailType();
    }
}
