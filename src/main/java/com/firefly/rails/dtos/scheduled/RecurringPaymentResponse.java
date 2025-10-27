package com.firefly.rails.dtos.scheduled;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class RecurringPaymentResponse {
    private String recurringPaymentId;
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private CreateRecurringPaymentRequest.RecurrenceFrequency frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate nextExecutionDate;
    private int executionCount;
    private boolean active;
}
