package com.firefly.rails.dtos.scheduled;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class PaymentExecutionHistory {
    private String executionId;
    private String paymentId;
    private Money amount;
    private Instant executedAt;
    private PaymentStatus status;
    private String failureReason;
}
