package com.firefly.rails.dtos.webhook;

import com.firefly.rails.domain.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class WebhookEvent {
    private String eventId;
    private WebhookEventType eventType;
    private String paymentId;
    private PaymentStatus paymentStatus;
    private Instant timestamp;
    private Map<String, Object> data;
    private String railSpecificData;
    
    public enum WebhookEventType {
        PAYMENT_INITIATED,
        PAYMENT_AUTHORIZED,
        PAYMENT_CONFIRMED,
        PAYMENT_SETTLED,
        PAYMENT_FAILED,
        PAYMENT_REJECTED,
        PAYMENT_RETURNED,
        PAYMENT_CANCELLED,
        SCA_REQUIRED,
        SCA_COMPLETED,
        SETTLEMENT_REPORT_AVAILABLE,
        MANDATE_CREATED,
        MANDATE_CANCELLED,
        DISPUTE_OPENED,
        DISPUTE_RESOLVED
    }
}
