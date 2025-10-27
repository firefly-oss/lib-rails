package com.firefly.rails.dtos.payments;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class SimulatePaymentRequest {
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private TransactionType transactionType;
    private SettlementSpeed settlementSpeed;
    private LocalDate requestedExecutionDate;
}
