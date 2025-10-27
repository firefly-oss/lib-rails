package com.firefly.rails.dtos.status;
import lombok.Builder; import lombok.Data;
@Data @Builder
public class PaymentTrackingResponse { private String id; private String status; }
