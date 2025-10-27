package com.firefly.rails.dtos.compliance;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComplianceCheckRequest {
    private String customerId;
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private String destinationCountry;
    private TransactionType transactionType;
    private String purpose;
}
