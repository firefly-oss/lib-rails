package com.firefly.rails.dtos.fees;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeeCalculationRequest {
    private Money amount;
    private Currency sourceCurrency;
    private Currency targetCurrency;
    private TransactionType transactionType;
    private SettlementSpeed settlementSpeed;
    private String destinationCountry;
}
