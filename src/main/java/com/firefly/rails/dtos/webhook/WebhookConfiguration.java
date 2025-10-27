package com.firefly.rails.dtos.webhook;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class WebhookConfiguration {
    private String webhookId;
    private String url;
    private List<String> events;
    private boolean active;
    private String description;
}
