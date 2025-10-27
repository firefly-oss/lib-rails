package com.firefly.rails.dtos.scheduled;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class CreateScheduledPaymentRequest {
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private String remittanceInformation;
    private TransactionType transactionType;
    private LocalDate executionDate;
    private LocalTime executionTime;
    private IdempotencyKey idempotencyKey;
}
