package com.firefly.rails.dtos.webhook;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebhookRegistrationResponse {
    private String webhookId;
    private String url;
    private String secret;
    private boolean active;
}
