package com.firefly.rails.dtos.scheduled;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class CreateRecurringPaymentRequest {
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private String remittanceInformation;
    private RecurrenceFrequency frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfPayments;
    
    public enum RecurrenceFrequency {
        DAILY,
        WEEKLY,
        BIWEEKLY,
        MONTHLY,
        QUARTERLY,
        YEARLY
    }
}
