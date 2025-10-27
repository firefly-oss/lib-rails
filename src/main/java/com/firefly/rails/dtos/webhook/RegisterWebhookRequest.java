package com.firefly.rails.dtos.webhook;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RegisterWebhookRequest {
    private String url;
    private List<String> events;
    private String secret;
    private String description;
}
