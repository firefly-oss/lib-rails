package com.firefly.rails.dtos.payments;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class CancellationResponse {
    private String authorizationId;
    private boolean cancelled;
    private String reason;
    private Instant cancelledAt;
}
