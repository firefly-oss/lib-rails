package com.firefly.rails.dtos.webhook;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class WebhookVerificationRequest {
    private String payload;
    private String signature;
    private Map<String, String> headers;
    private String secret;
}
