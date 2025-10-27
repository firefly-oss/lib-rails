package com.firefly.rails.dtos.webhook;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebhookTestResponse {
    private boolean success;
    private String message;
    private int httpStatus;
}
