package com.firefly.rails.dtos.webhook;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class ParseWebhookRequest {
    private String payload;
    private Map<String, String> headers;
}
