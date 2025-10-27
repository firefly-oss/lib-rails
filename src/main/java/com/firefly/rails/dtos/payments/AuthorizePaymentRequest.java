package com.firefly.rails.dtos.payments;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class AuthorizePaymentRequest {
    private Money amount;
    private BankAccount debtorAccount;
    private BankAccount creditorAccount;
    private String endToEndReference;
    private String remittanceInformation;
    private TransactionType transactionType;
    private SettlementSpeed settlementSpeed;
    private LocalDate requestedExecutionDate;
    
    /** Authentication context for SCA */
    private AuthenticationContext authenticationContext;
    
    /** Whether to skip SCA if exemption applies */
    private boolean requestScaExemption;
    
    /** Additional metadata */
    private Map<String, String> metadata;
}
