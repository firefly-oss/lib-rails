package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class SARRequest {
    private String customerId;
    private String transactionId;
    private String suspiciousActivity;
    private String description;
    private Instant detectedAt;
}
