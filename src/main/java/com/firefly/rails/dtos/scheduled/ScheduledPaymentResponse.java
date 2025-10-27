package com.firefly.rails.dtos.scheduled;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ScheduledPaymentResponse {
    private String scheduledPaymentId;
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private LocalDate executionDate;
    private ScheduledPaymentStatus status;
    
    public enum ScheduledPaymentStatus {
        PENDING,
        EXECUTING,
        EXECUTED,
        FAILED,
        CANCELLED
    }
}
