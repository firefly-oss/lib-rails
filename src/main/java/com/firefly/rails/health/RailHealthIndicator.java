/*
 * Copyright 2025 Firefly Software Solutions Inc
 */

package com.firefly.rails.health;

import com.firefly.rails.adapter.RailAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Health indicator for banking rail connectivity.
 * 
 * Integrates with Spring Boot Actuator to provide health check endpoints.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RailHealthIndicator implements HealthIndicator {

    private final RailAdapter railAdapter;

    @Override
    public Health health() {
        try {
            boolean isHealthy = railAdapter.isHealthy();
            
            Map<String, Object> details = new HashMap<>();
            details.put("railType", railAdapter.getRailType());
            details.put("timestamp", Instant.now());
            details.put("status", isHealthy ? "UP" : "DOWN");
            
            if (isHealthy) {
                return Health.up()
                        .withDetails(details)
                        .build();
            } else {
                return Health.down()
                        .withDetails(details)
                        .withDetail("reason", "Rail connectivity check failed")
                        .build();
            }
        } catch (Exception e) {
            log.error("Health check failed for rail: {}", railAdapter.getRailType(), e);
            
            return Health.down()
                    .withDetail("railType", railAdapter.getRailType())
                    .withDetail("error", e.getMessage())
                    .withException(e)
                    .build();
        }
    }
}
