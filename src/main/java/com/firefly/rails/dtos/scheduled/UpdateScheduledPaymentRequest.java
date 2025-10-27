package com.firefly.rails.dtos.scheduled;

import com.firefly.rails.domain.Money;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class UpdateScheduledPaymentRequest {
    private String scheduledPaymentId;
    private Money newAmount;
    private LocalDate newExecutionDate;
    private String newRemittanceInfo;
}
